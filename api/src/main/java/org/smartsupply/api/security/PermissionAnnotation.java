package org.smartsupply.api.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * This is used for annotating Permission constants in the system
 * 
 * @author ctumwebaze
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionAnnotation {

    /**
     * system assigned id for the permission
     * 
     * @return
     */
    String id();

    /**
     * the description of the permission
     * 
     * @return
     */
    String description();
}
