package com.redhat.insurance.claims.config;

import java.util.Properties;

/**
 * 
 * This is a java front end to the property file in
 * src/test/resources/config/test.config
 *
 */
public class Configuration {

    private static Properties PROPS = null;

    public static boolean isAuditLogEnabled() {
        if ( PROPS == null ) {
            try {
                init();
            }
            catch ( Exception e ) {
                System.err.println( "unable to load properties, failing test" );
                System.err.println( "root cause : " + e.getMessage() );
            }

        }
        return Boolean.valueOf( (String) PROPS.get( "kie.audit.log.enabled" ) );

    }

    private static void init() throws Exception {
        PROPS = new Properties();
        PROPS.load( Configuration.class.getClassLoader().getResourceAsStream( "config/test.config" ) );
        System.err.println( PROPS );
    }
}
