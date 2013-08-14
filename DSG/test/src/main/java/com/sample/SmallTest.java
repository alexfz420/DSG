package com.sample;

import java.util.Arrays;
import java.util.Collection;

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

import com.sample.FibonacciTest.Fibonacci;

public class SmallTest {

	public static void main(String[] args) {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		// this will parse and compile in one step

		kbuilder.add(ResourceFactory.newClassPathResource("newRule_joe.drl",

				HelloWorld.class), ResourceType.DRL);


		// Check the builder for errors

		if (kbuilder.hasErrors()) {

			System.out.println(kbuilder.getErrors().toString());

			throw new RuntimeException("Unable to compile \"newRule_joe.drl\".");

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
		
		Product shoes = new Product("1", "shoes", 50 ,10, 2);
		Product shoes2 = new Product("1", "shoes", 50 ,10, 2);
		Product shoes3 = new Product("1", "shoes", 50 ,10, 2);
		Product hat = new Product(2+"", "hat", 10 , 4, 2);
		Product shirt = new Product(3+"", "shirt", 20 , 8 ,6);
		//Product shirts = new Product(5, "hahahah", 20 , 8 ,5);
		shirt.setFlag("TH-B,ST-A,SP-A");
		Store s1 = new Store(1,2);
		Store s2 = new Store(2,4);
		Store s3 = new Store(3,5);
		Store s4 = new Store(4,6);
		Store s5 = new Store(5,8);
	
		s1.addItem(shoes, 5, 5);
		s1.addItem(hat, 7, 5);
		s1.addItem(shirt, 7, 5);
		s2.addItem(shoes, 7, 5);
		s2.addItem(hat, 7, 5);
		s2.addItem(shirt, 7, 5);
		s3.addItem(shoes, 7, 4);
		s3.addItem(hat, 7, 4);
		s3.addItem(shirt, 7, 4);
		s4.addItem(shoes, 7, 4);
		s4.addItem(hat, 7, 4);
		s4.addItem(shirt, 7, 4);
		s5.addItem(shoes, 7, 4);
		s5.addItem(hat, 7, 4);
		s5.addItem(shirt, 7, 4);
		
		
		Order order = new Order(2);
		order.addProducts(shoes, 5);
		//order.addProducts(shoes2, 5);
//		order.addProducts(shoes3, 5);
		order.addProducts(hat, 1);
		order.addProducts(shirt, 1);	
		//order.addProducts(shirts,1);
		
		
		ksession.insert(shoes2);
		ksession.insert(shoes3);
		ksession.insert(s1);
		ksession.insert(s2);
		ksession.insert(s3);
		ksession.insert(s4);
		ksession.insert(s5);
		ksession.insert(order);

		ksession.fireAllRules();
		
		Collection<Package> packages = (Collection<Package>) ksession.getObjects( new ClassObjectFilter(Package.class) );
		Collection<Store> stores = (Collection<Store>) ksession.getObjects( new ClassObjectFilter(Store.class) );

		System.out.println("---------------------------------");
		System.out.println("package size: " + packages.size());
		System.out.println(Arrays.toString(packages.toArray()));
		System.out.println("store list: " + stores.size());
		System.out.println(Arrays.toString(stores.toArray()));
			
		System.out.println("end");
		
		// Remove comment if using logging
		logger.close();

		ksession.dispose();
	}
	
	private static void setUpProduct(Product p, String name, double price) {
		p.setPrice(price);
		p.setProductName(name);
	}
}
