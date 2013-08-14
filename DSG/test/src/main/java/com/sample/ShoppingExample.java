/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sample;

import java.util.Collection;

import org.kie.api.KieServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.logger.KnowledgeRuntimeLogger;
import org.kie.internal.logger.KnowledgeRuntimeLoggerFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class ShoppingExample {

    public static final void main(String[] args) {
        final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

       kbuilder.add(ResourceFactory.newClassPathResource("Shopping.drl",

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
    		
       

        Customer mark = new Customer( "mark",
                                      0 );
        ksession.insert( mark );

        Product shoes = new Product( "shoes",
                                     60 );
        ksession.insert( shoes );

        Product hat = new Product( "hat",
                                   60 );
        ksession.insert( hat );

        ksession.insert( new Purchase( mark,
                                       shoes ) );

        FactHandle hatPurchaseHandle = ksession.insert( new Purchase( mark,
                                                                      hat ) );

        ksession.fireAllRules();

        ksession.delete( hatPurchaseHandle );
        System.out.println( "Customer mark has returned the hat" );
        ksession.fireAllRules();
    }

    public static class Customer {
        private String name;

        private int    discount;

        public Customer(String name,
                        int discount) {
            this.name = name;
            this.discount = discount;
        }

        public String getName() {
            return name;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }

    }

    public static class Discount {
        private Customer customer;
        private int      amount;

        public Discount(Customer customer,
                        int amount) {
            this.customer = customer;
            this.amount = amount;
        }

        public Customer getCustomer() {
            return customer;
        }

        public int getAmount() {
            return amount;
        }

    }

    public static class Product {
        private String name;
        private float  price;

        public Product(String name,
                       float price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public float getPrice() {
            return price;
        }

    }

    public static class Purchase {
        private Customer customer;
        private Product  product;

        public Purchase(Customer customer,
                        Product product) {
            this.customer = customer;
            this.product = product;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Product getProduct() {
            return product;
        }
    }
}
