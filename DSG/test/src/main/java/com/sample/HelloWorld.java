package com.sample;

import java.util.Collection;
import java.util.List;


import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

public class HelloWorld {
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		final KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();


		// this will parse and compile in one step

		kbuilder.add(ResourceFactory.newClassPathResource("HelloWorld.drl",

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

        final Message message = new Message();
        message.setMessage( "Hello World" );
        message.setStatus( Message.HELLO );
        ksession.insert( message );

        ksession.fireAllRules();

        // Remove comment if using logging
        // logger.close();

        ksession.dispose();
    }

    public static class Message {
        public static final int HELLO   = 0;
        public static final int GOODBYE = 1;

        private String          message;

        private int             status;

        public Message() {

        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(final String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(final int status) {
            this.status = status;
        }

        public static Message doSomething(Message message) {
            return message;
        }

        public boolean isSomething(String msg,
                                   List<Object> list) {
            list.add( this );
            return this.message.equals( msg );
        }
    }

}
