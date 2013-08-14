package com.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

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

public class Split {
	public static void main(String[] args) {	
		Product shoes = new Product(1+"", "shoes", 50 , 10, 2);
		Product hat = new Product(2+"", "hat", 10 , 4, 2);
		Product shirt = new Product(3+"", "shirt", 20 , 8 ,5);
		Product[] a = {shoes, hat, shirt};
		Combination[][] matrix = setUpMatrix(a);
		//printMatrix(matrix);
				
		printMatrix(matrix);
		
//		for (int i = 1; i < a.length; i++) {
//			//System.out.println(i);
//			printCombinations(a.length, i, a, matrix);
//		}
		
		Order order = new Order(2);
		order.addProducts(shoes, 1);
		order.addProducts(hat, 1);
		order.addProducts(shirt, 1);
		
//		Package p1 = new Package(order);
//		p1.addProduct(shoes);
		Package p2 = new Package(order);
		p2.addProduct(hat);
		p2.addProduct(shirt);
		p2.addProduct(shoes);
		
		Store s1 = new Store(1, 2);
		Store s2 = new Store(2, 4);
		Store s3 = new Store(3, 5);
		Store s4 = new Store(4, 6);
		Store s5 = new Store(5, 8);
	
		s1.addItem(shoes, 5, 5);
		s1.addItem(hat, 5, 5);
		s1.addItem(shirt, 5, 5);
		s2.addItem(shoes, 7, 5);
		s2.addItem(hat, 5, 5);
		s2.addItem(shirt, 6, 5);
		s3.addItem(shoes, 7, 5);
		s3.addItem(hat, 6, 5);
		s3.addItem(shirt, 5, 5);
		s4.addItem(shoes, 7, 5);
		s4.addItem(hat, 5, 5);
		s4.addItem(shirt, 5, 5);
		s5.addItem(shoes, 7, 5);
		s5.addItem(hat, 5, 5);
		s5.addItem(shirt, 7, 5);
		
//		order.addStore(s1);
//		order.addStore(s2);
//		order.addStore(s3);
//		order.addStore(s4);
//		order.addStore(s5);
		
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		// this will parse and compile in one step

		kbuilder.add(ResourceFactory.newClassPathResource("evaluate.drl",

				Split.class), ResourceType.DRL);


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

		ksession.insert(s1);
		ksession.insert(s2);
		ksession.insert(s3);
		ksession.insert(s4);
		ksession.insert(s5);
//		ksession.insert(p1);
		ksession.insert(p2);
		
		System.out.println("----------------------");
		
		ksession.fireAllRules();
		
		// Remove comment if using logging
		logger.close();

		ksession.dispose();
				
//		ArrayList<PackageTestResult> results = getTestResult(p1, order);
//		
//		for (PackageTestResult r : results) {
//			System.out.println(r);
//		}		
	}
	
	public static ArrayList<PackageTest> getTests(Package pack) {
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
			PackageTest packageTest = new PackageTest(pack, pack.getOrder().getZoneID());
			Bag bag = list.get(i);
			ArrayList<ArrayList<Product>> allocations = bag.list;
			
			for (int j = 0; j < allocations.size(); j++) {
				Parcel parcel = new Parcel(pack.getZoneID());
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
	
//	public static ArrayList<PackageTestResult> getTestResult(Package p, Order order) {
//		Product[] products = p.getProducts().toArray(new Product[p.getProducts().size()]);
//		Combination[][] matrix = setUpMatrix(products);
//		for (int i = 1; i < p.getSplitNum(); i++) {
//			//System.out.println(i);
//			printCombinations(products.length, i, products, matrix);
//		}
//		
//		ArrayList<Store> stores = order.getStores();
//		
//		//System.out.println("Store size: " + stores.size());
//		Combination c = matrix[products.length - 1][p.getSplitNum()];
//		
//		ArrayList<Bag> list = c.list;
//		double lowestCost = Double.MAX_VALUE;
//		ArrayList<PackageTestResult> testResults = null;
//		for (int i = 0; i < list.size(); i++) {
//			Bag bag = list.get(i);
//			ArrayList<ArrayList<Product>> allocations = bag.list;
////			ArrayList<ArrayList<Store>> testStores = filterStores(allocations, stores);
//			
//			if (stores == null || stores.size() == 0) continue;
//			
//			//System.out.println(testStores.size());
//			
//			double cost = 0;
//			ArrayList<PackageTestResult> results = new ArrayList<PackageTestResult>();
//			for (int j = 0; j < allocations.size(); j++) {
//				Parcel test = new Parcel(order.getZoneID());
//				for (Product product : allocations.get(j)) {
//					test.addProduct(product);
//				}
////				System.out.println("j: " + j);
//				PackageTestResult r = getTestResult(order, test, stores);
//				if (r != null) {
//					results.add(r);
//					cost += r.getCost();
//				}
//			}
//			if (cost < lowestCost) {
//				lowestCost = cost;
//				testResults = results;
//			}
//		}	
//		
//		return testResults;
//	}
	
//	public static ArrayList<ArrayList<Store>> filterStores(ArrayList<ArrayList<Product>> allocations, ArrayList<Store> stores) {
//		ArrayList<ArrayList<Store>> r = new ArrayList<ArrayList<Store>>();
//		//System.out.println("possible combination size: " + allocations.size());
//		for (int i = 0; i < allocations.size(); i++) {
//			ArrayList<Product> products = allocations.get(i);
//			LinkedList<Store> testStores = new LinkedList<Store>();
//			for (int j = 0; j < stores.size(); j++) {
//				Store s = stores.get(j);
//				if (s.containProducts(products)) {
//					testStores.add(s);
//				} else {
//					System.out.println("filter out: " + s.getZoneID());
//				}
//			}
//			//System.out.println("test Store size: " + testStores.size());
//			if(testStores.size() == 0) return null;			
//			ArrayList<Store> newStores = new ArrayList<Store>(testStores);
//			r.add(newStores);
//		}
//		
//		return r;
//	}
	
	public static PackageTestResult getTestResult(final Order order, Parcel test, ArrayList<Store> stores) {
		ArrayList<Store> testStores = new ArrayList<Store>();
		for (int j = 0; j < stores.size(); j++) {
			Store s = stores.get(j);
			if (s.containProducts(test.getProductList())) {
				testStores.add(s);
			} else {
				System.out.println("filter out: " + s.getZoneID());
			}
		}
		
		if (testStores.size() == 0) return null;
				
		// possible problem
		PackageTestResult r = new PackageTestResult(test);
		Collections.sort(testStores, new Comparator<Store>() {
			@Override
			public int compare(Store arg0, Store arg1) {
				return (int) (Util.getShippingCosts(arg0.getZoneID(), order.getZoneID()) 
								- Util.getShippingCosts(arg1.getZoneID(), order.getZoneID())); 
			}
			
		});
		
		System.out.println("stores: " + Arrays.toString(stores.toArray()));
		
		Store source = testStores.get(0);
		//System.out.println("source: " + source.getZoneID());
		r.setSource(source);
		r.setCost(Util.getShippingCosts(source.getZoneID(), order.getZoneID()));
		return r;
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
