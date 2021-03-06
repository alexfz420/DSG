package com.dicks.engine;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.ClassObjectFilter;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.dicks.dao.RuleDAO;
import com.dicks.dao.StoreDAO;
import com.dicks.pojo.Product;
import com.dicks.pojo.Rule;
import com.dicks.pojo.Store;
import com.opensymphony.xwork2.inject.util.Strings;

public class Split {
	private EngineLog stage2;
	private EngineLog stage3;
	private Collection<PackageTestResult> newAllocatedResults;
	
	public Collection<PackageTestResult> getNewAllocatedResults() {
		return newAllocatedResults;
	}

	public void setNewAllocatedResults(
			Collection<PackageTestResult> newAllocatedResults) {
		this.newAllocatedResults = newAllocatedResults;
	}

	public EngineLog getStage3() {
		return stage3;
	}

	public void setStage3(EngineLog stage3) {
		this.stage3 = stage3;
	}

	
	@SuppressWarnings("restriction")
	public Split(Collection<PackageE> packages, Collection<Store> stores, Collection<PackageTestResult> allocatedResults) throws Exception {
		SplitGenerater.cache(10);
		SplitGenerater.buildIndex(10);
		
		this.stage2 = new EngineLog(2);
		this.stage3 = new EngineLog(3);
		
		ArrayList<Rule> rules = RuleDAO.getInstance().getRuleByType("6");
		Rule rule = rules.get(0);
		synchronized (Util.operator) {
			Util.operator = rule.getOperator();
			System.out.println("operator: " + Util.operator);
		}
		synchronized (Util.attribute) {
			Util.attribute = rule.getAttribute();
			System.out.println("attribute: " + Util.attribute);
		}

		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		kbuilder.add(ResourceFactory.newFileResource(new File("src/com/dicks/rules/evaluate.drl")), ResourceType.DRL);

		// Check the builder for errors
		if (kbuilder.hasErrors()) {
			System.out.println(kbuilder.getErrors().toString());
			throw new RuntimeException("Unable to compile \"evaluate.drl\".");
		}

		// get the compiled packages (which are serializable)
		final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();

		// add the packages to a KnowledgeBase (deploy the knowledge packages).
		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
		kbase.addKnowledgePackages(pkgs);

		final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
		// setup the audit logging
		// Remove comment to use FileLogger
		KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger( ksession, "./smallTest" );

		// Remove comment to use ThreadedFileLogger so audit view reflects events whilst debugging
		//KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger( ksession, "./helloworld", 1000 );
		
//		ksession.insert(stage2);
//		ksession.insert(stage3);
		
		ksession.setGlobal("stage2", stage2);
		ksession.setGlobal("stage3", stage3);
		
		for (PackageE pack : packages) {
			ksession.insert(pack);
		}
		
		for (Store store : stores) {
			ksession.insert(store);
			System.out.println(store);
		}

		System.out.println("----------------------");
		
		Util.percentage = "55";

		ksession.fireAllRules();

		// Remove comment if using logging
		
		newAllocatedResults = (Collection<PackageTestResult>) ksession.getObjects( new ClassObjectFilter(PackageTestResult.class) );
		
		logger.close();
		ksession.dispose();
		
//		for (PackageTestResult r : allocatedResults) {
//			stage3.addLog("Allocated Results", r.toString());
//		}
//		
//		for (PackageTestResult r : newAllocatedResults) {
//			stage3.addLog("Allocated Results", r.toString());
//		}
		
		JSONObject stage2Logs = new JSONObject();
		JSONArray packageJson = new JSONArray();
		
		for (PackageE pack : packages) {
			packageJson.add(pack.getJson());
		}
		
		stage2Logs.put("packages", packageJson);
		stage2Logs.put("remainingStores", stores.size());
		stage2Logs.put("totalStores", StoreDAO.getInstance().getTotalStoreNum());
		
		StringWriter out = new StringWriter();
		stage2Logs.writeJSONString(out);
		String jsonText = out.toString();	
		System.out.println("stage2Logs: " + jsonText);
		stage2.addLog("Cost Calculation", jsonText);	
		
		JSONObject package3Obj = new JSONObject();
		JSONArray packageJson3 = new JSONArray();
		double totalCosts = 0;
		
		for (PackageE pack : packages) {
			packageJson3.add(pack.getStage3Json());
			if (!pack.isUnable() && pack.getBestResults() != null && pack.getBestResults().size() > 0) {
				PackageTestResult best = pack.getBestResults().get(0);
				totalCosts += best.getCost();
			}
		}
		package3Obj.put("totalCosts", Math.round(totalCosts * 100) / 100.0);
		package3Obj.put("stage3Arrays", packageJson3);
		package3Obj.put("rankOption", Util.getOperator());
		package3Obj.put("attribute", Util.getAttribute());
		
		
		out = new StringWriter();
		package3Obj.writeJSONString(out);
		jsonText = out.toString();	
		System.out.println("stage3Logs: " + jsonText);
		stage3.addLog("Evaluation", jsonText);
		
		Util.percentage = "75";
	}

	public static ArrayList<PackageTest> getTests(PackageE pack) {
		Product[] products = pack.getProducts().toArray(new Product[pack.getProducts().size()]);
		Combination[][] matrix = setUpMatrix(products);
		System.out.println("split num: " + pack.getSplitNum());
		for (int i = 1; i < pack.getSplitNum() + 1; i++) {
//			System.out.println("i: " + i);
			printCombinations(products.length, i + 1, products, matrix);
		}

		Combination c = matrix[products.length - 1][pack.getSplitNum()];
//		System.out.println("i: " + (products.length - 1) + ", j: " + pack.getSplitNum());
		ArrayList<Bag> list = c.list;

		ArrayList<PackageTest> packageTests = new ArrayList<PackageTest>();

		for (int i = 0; i < list.size(); i++) {
			PackageTest packageTest = new PackageTest(pack);
			Bag bag = list.get(i);
			ArrayList<ArrayList<Product>> allocations = bag.list;

			for (int j = 0; j < allocations.size(); j++) {
				Parcel parcel = new Parcel(pack);
				for (Product product : allocations.get(j)) {
					parcel.addProduct(product);
				}
				packageTest.addParcel(parcel);
			}
			packageTests.add(packageTest);
//			System.out.println(packageTest);
		}		
		return packageTests;
	}


	public static Combination[][] setUpMatrix(Product[] a) {
		Combination[][] matrix = new Combination[a.length][a.length];
		matrix[0][0] = new Combination();
		Bag origin = new Bag();
		origin.addToBag(a[0], 0);
		matrix[0][0].list.add(origin);
		//System.out.println(matrix[0][0]);

		for (int i = 1; i < a.length; i++) {
			//System.out.println(i);
			Combination c = new Combination();
			Bag b = new Bag();
			b.copyBag(matrix[i - 1][0].list.get(0));
			b.addToBag(a[i], 0);
			c.list.add(b);
			matrix[i][0] = c;
		}

		for (int i = 1; i < a.length; i++) {
			Combination c = new Combination();
			Bag b = new Bag();
			//System.out.println(i);
			b.copyBag(matrix[i - 1][i - 1].list.get(0));
			b.addToBag(a[i], i);
			c.list.add(b);
			matrix[i][i] = c;
		}
		return matrix;
	}

	public static void printCombinations(int n, int m, Product[] a, Combination[][] matrix) {
		if (m == 1) {
			return;
		}	
//		System.out.println("m: " + m + ", n: " + n);		
		for (int i = m; i < n; i++) {
			//System.out.println("i : " + i);
			Combination c = new Combination();	
			c.addNewItem(a[i], matrix[i-1][m-2], matrix[i-1][m-1]);
			matrix[i][m-1] = c;
		}

		System.out.println();
		printMatrix(matrix);
	}


	public static void printMatrix(Combination[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print((matrix[i][j]==null? 0 : matrix[i][j].list.size()) + ", ");
			}
			System.out.println();
		}
	}

	public EngineLog getStage2() {
		return stage2;
	}

	public void setStage2(EngineLog stage2) {
		this.stage2 = stage2;
	}

	public static class Combination {
		ArrayList<Bag> list = new ArrayList<Bag>();

		public void addNewItem(Product item, Combination c1, Combination c2) {
			for (int i = 0; i < c1.list.size(); i++) {
				Bag b = new Bag();
				b.addNewItemAsPackage(item, c1.list.get(i));
				list.add(b);
			}

			for (int i = 0; i < c2.list.size(); i++) {
				Bag from = c2.list.get(i);
				for (int j = 0; j < from.list.size(); j++) {
					Bag b = new Bag();
					b.addNewItemTo(item, from, j);
					list.add(b);
				}
			}
		}		

		@Override
		public String toString() {
			return Arrays.toString(list.toArray());
		}
	}

	public static class Bag {
		ArrayList<ArrayList<Product>> list = new ArrayList<ArrayList<Product>>();

		public void copyBag(Bag bag) {
			ArrayList<ArrayList<Product>> from = bag.list;
			for (int i = 0; i < from.size(); i++) {
				ArrayList<Product> sub = new ArrayList<Product>();
				sub.addAll(from.get(i));
				list.add(sub);
			}
		}

		public void addNewItemAsPackage(Product item, Bag bag) {
			ArrayList<ArrayList<Product>> from = bag.list;
			list = new ArrayList<ArrayList<Product>>();
			for (int i = 0; i < from.size(); i++) {
				ArrayList<Product> sub = new ArrayList<Product>();
				sub.addAll(from.get(i));
				list.add(sub);
			}
			ArrayList<Product> newItem = new ArrayList<Product>();
			newItem.add(item);
			list.add(newItem);
		}		

		public void addNewItemTo(Product item, Bag bag, int index) {
			ArrayList<ArrayList<Product>> from = bag.list;
			list = new ArrayList<ArrayList<Product>>();
			for (int i = 0; i < from.size(); i++) {
				ArrayList<Product> sub = new ArrayList<Product>();
				sub.addAll(from.get(i));
				list.add(sub);
			}
			list.get(index).add(item);
		}

		public void addToBag(Product item, int bag) {
			if (list.size() <= bag) {
				ArrayList<Product> newBag = new ArrayList<Product>();
				newBag.add(item);
				list.add(newBag);
				//System.out.println("in create");
			} else {
				list.get(bag).add(item);
			}			
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < list.size(); i++) {
				ArrayList<Product> sub = list.get(i);
				for (int j = 0; j < sub.size(); j++) {
					sb.append(sub.get(j));
					if (j != sub.size() - 1) {
						sb.append(", ");
					}
				}
				if (i != list.size() - 1) sb.append(" / ");
			}
			return sb.toString();
		}
	}

	// File
//  try {
//		for (int i = 0; i < a.length; i++) {
//			BufferedWriter out = new BufferedWriter(new FileWriter("file" + i + ".txt"));
//          for (int j = 0; j < a.length; j++) {
//          	out.write(matrix[i][j].toString());
//          	out.newLine();
//          	out.newLine();
//          }
//          
//          out.close();
//		}        
//  } catch (IOException e) {
//  	System.out.println(e.getMessage());
//  }

//	for (int i = 0; i < a.length; i++) {
//      for (int j = 0; j <= i; j++) {
//      	ArrayList<Bag> list = matrix[i][j].list;
//      	for (int k = 0; k < list.size(); k++) {
//      		System.out.println(list.get(k));
//      	}
//      }
//	}  

}
