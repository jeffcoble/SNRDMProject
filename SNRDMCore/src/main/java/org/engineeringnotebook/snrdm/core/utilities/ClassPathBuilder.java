/*
 * Copyright 2011 Jeff Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org.
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

package org.engineeringnotebook.snrdm.core.utilities;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Jeff Coble <jeffrey.a.coble@gmail.com> http://engineeringnotebook.org
 */
public class ClassPathBuilder {
    private static final Logger logger = Logger.getLogger(ClassPathBuilder.class.getName());
    
     /**
     * The simplejpa library must be provided with an absolute scan path to find 
     * the entity classes that we want to manage.  I consider this process to be 
     * a kludge when used within an EJB, but all efforts to get simplejpa to 
     * scan the relative EJB classpath reference the entity classes defined 
     * within the persistence.xml config file have been unsuccessful.  It 
     * appears that simplejpa must be provided with an absolute path to scan.
     * 
     * @param classList the classes for which to find class paths
     * 
     * @return the set of scan paths
     */
    public Set<String> getScanPaths(List<Class> classList) {
        Set<String> libPaths = new HashSet<String>();
        
        for(Class theClass : classList) {
            String classPath = getClassPath(theClass);
            libPaths.add(classPath); 
        }
        
        return libPaths;
    }
    
    /**
     * Constructs a string representation of the absolute path of a class
     * 
     * This method expects the class to reside within a jar file.
     */
    private String getClassPath(java.lang.Class aClass) {
        
        final String prefix = "jar:file:";
        final String suffix = "!";
        String classPath;
        String className;
        
        
        className = getClassName(aClass);
        classPath = aClass.getResource(className).toString();
        logger.log(Level.FINE, "URI for Class {0}: {1}", new Object[]{aClass, classPath});
        
        //get rid of all of the URI baggage
        int startIndex = classPath.indexOf(prefix) + prefix.length();
        int endIndex = classPath.indexOf(suffix);
        classPath = classPath.substring(startIndex, endIndex);
        logger.log(Level.FINE, "Classpath for Class {0}: {1}", new Object[]{aClass, classPath});
  
        return classPath;
    }
    
    /**
     * Returns a string representation of a class name after removing the
     * package name.
     */
    private String getClassName(java.lang.Class aClass) {
        
        String className;
        String packageName;
        
        className = aClass.getName();
        packageName = aClass.getPackage().getName();

        logger.log(Level.FINE, "Class Name: {0} Package Name: {1}", new Object[]{className, packageName});
     
        int pIndex = packageName.length();
        className = className.substring(pIndex + 1) + ".class";

        logger.log(Level.FINE, "Extracted Class Name: {0}", className);
        return className;
    }

    
}
