package com.sample;

import java.util.Collection;

import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

import com.sample.HelloWorld.Message;

public class FibonacciTest {
	public static void main(String[] args) {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		// this will parse and compile in one step

		kbuilder.add(ResourceFactory.newClassPathResource("fibonacci.drl",

		        HelloWorld.class), ResourceType.DRL);


		// Check the builder for errors

		if (kbuilder.hasErrors()) {

		    System.out.println(kbuilder.getErrors().toString());

		    throw new RuntimeException("Unable to compile \"HelloWorld.drl\".");

		}


		// get the compiled packages (which are serializable)

		final Collection<KnowledgePackage> pkgs = kbuilder.getKnowledgePackages();


		// add the packages to a KnowledgeBase (deploy the knowledge packages).

		final KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();

		kbase.addKnowledgePackages(pkgs);


		final StatefulKnowledgeSession ksession = kbase.newStatefulKnowledgeSession();
        // setup the audit logging
        // Remove comment to use FileLogger
        // KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger( ksession, "./helloworld" );
        
        // Remove comment to use ThreadedFileLogger so audit view reflects events whilst debugging
        // KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger( ksession, "./helloworld", 1000 );

        ksession.insert( new Fibonacci(1000) );

        ksession.fireAllRules();

        // Remove comment if using logging
        // logger.close();

        ksession.dispose();
	}
	
	public static class Fibonacci {
		private int sequence;
		private long value;
		
		public Fibonacci(final int sequence) {
			this.sequence = sequence;
			this.value = -1;
		}
		
		public int getSequence() {
			return sequence;
		}

		public void setSequence(int sequence) {
			this.sequence = sequence;
		}

		public long getValue() {
			return value;
		}

		public void setValue(final long value) {
			this.value = value;
		}

        public String toString() {
            return "Fibonacci(" + this.sequence + "/" + this.value + ")";
        }
	}
}
