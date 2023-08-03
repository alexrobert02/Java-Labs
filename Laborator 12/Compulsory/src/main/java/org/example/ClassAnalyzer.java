package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.testng.annotations.Test;

// javac -cp "org/example/testng-7.7.0.jar" org/example/*.java
// java -cp "org/example/testng-7.7.0.jar;" org.example.ClassAnalyzer "path/example"

public class ClassAnalyzer {
    public static void main(String[] args) throws ClassNotFoundException {
        if (args.length < 1) {
            System.out.println("Usage: ClassAnalyzer <classPath>");
            return;
        }

        // Print classpath
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
        System.out.println();

        String classPath = args[0];
        Class<?> clazz = Class.forName(classPath);

        // Print package information
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            System.out.println("Package: " + pkg.getName());
        }

        // Print class information
        System.out.println("Class: " + clazz.getSimpleName());
        System.out.println();

        // Analyze methods
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // Print method name
            System.out.println("Method: " + method.getName());

            // Print modifiers
            int modifiers = method.getModifiers();
            System.out.println("Modifiers: " + Modifier.toString(modifiers));

            // Check for @Test annotation
            if (method.isAnnotationPresent(Test.class)) {
                // Invoke static methods with no arguments
                if (Modifier.isStatic(modifiers) && method.getParameterCount() == 0) {
                    try {
                        method.invoke(null);
                        System.out.println("Test passed!");
                    } catch (Exception e) {
                        System.out.println("Test failed: " + e.getMessage());
                    }
                }
            }

            System.out.println();
        }
    }
}