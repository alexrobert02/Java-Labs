package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

// javac -cp "org/example/testng-7.7.0.jar" org/example/*.java
// java -cp "org/example/testng-7.7.0.jar;" org.example.ClassAnalyzer "path/example"

public class ClassAnalyzer {
    private static int totalTests = 0;
    private static int passedTests = 0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: ClassAnalyzer <classPath>");
            return;
        }

        String classPath = args[0];

        try {
            // Load classes from the specified classPath
            List<Class<?>> classes = loadClasses(classPath);

            // Analyze the loaded classes
            analyzeClasses(classes);

            // Print the test statistics
            printStatistics();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    // Load classes from the specified classPath
    private static List<Class<?>> loadClasses(String classPath) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        File file = new File(classPath);

        if (file.isDirectory()) {
            // If classPath is a directory, recursively walk through it to find class files
            Files.walk(Path.of(classPath))
                    .filter(p -> p.toString().endsWith(".class"))
                    .forEach(p -> {
                        try {
                            String className = getClassName(classPath, p.toString());
                            Class<?> clazz = Class.forName(className);
                            classes.add(clazz);
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    });
        } else if (file.isFile() && classPath.endsWith(".jar")) {
            // If classPath is a .jar file, load classes from the jar file
            URLClassLoader classLoader = new URLClassLoader(new URL[]{file.toURI().toURL()});
            classes.addAll(loadClassesFromJar(file, classLoader));
        } else {
            System.out.println("Invalid classPath. Please provide a folder or a .jar file.");
        }

        return classes;
    }

    // Load classes from a .jar file
    private static List<Class<?>> loadClassesFromJar(File file, URLClassLoader classLoader) throws IOException, ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();

        try (var jarFile = new java.util.jar.JarFile(file)) {
            var entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                var entry = entries.nextElement();
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace('/', '.');
                    className = className.substring(0, className.length() - ".class".length());
                    Class<?> clazz = classLoader.loadClass(className);
                    classes.add(clazz);
                }
            }
        }

        return classes;
    }

    // Get the fully qualified class name from the file path
    private static String getClassName(String classPath, String filePath) {
        String separator = System.getProperty("file.separator");
        String className = filePath.substring(classPath.length(), filePath.length() - ".class".length());
        return className.replace(separator, ".");
    }

    // Analyze the loaded classes
    private static void analyzeClasses(List<Class<?>> classes) {
        for (Class<?> clazz : classes) {
            analyzeClass(clazz);
        }
    }

    // Analyze a single class
    private static void analyzeClass(Class<?> clazz) {
        Package pkg = clazz.getPackage();
        if (pkg != null) {
            System.out.println("Package: " + pkg.getName());
        }

        System.out.println("Class: " + clazz.getSimpleName());

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("Method: " + method.getName());
            int modifiers = method.getModifiers();
            System.out.println("Modifiers: " + Modifier.toString(modifiers));

            // Check if the method is annotated with @Test and is public
            if (method.isAnnotationPresent(Test.class) && Modifier.isPublic(modifiers)) {
                totalTests++;

                try {
                    // Invoke the test method
                    if (Modifier.isStatic(modifiers) && method.getParameterCount() == 0) {
                        // If the method is static with no parameters
                        invokeTestMethod(method, null);
                    } else {
                        // If the method is non-static or has parameters
                        Object instance = clazz.getDeclaredConstructor().newInstance();
                        invokeTestMethod(method, instance);
                    }

                    System.out.println("Test passed!");
                    passedTests++;
                } catch (Exception e) {
                    System.out.println("Test failed: " + e.getMessage());
                }
            }

            System.out.println();
        }
    }

    // Invoke a test method
    private static void invokeTestMethod(Method method, Object instance) throws InvocationTargetException, IllegalAccessException {
        Object[] args = generateMockArguments(method.getParameterTypes());
        method.invoke(instance, args);
    }

    // Generate mock arguments for a method
    private static Object[] generateMockArguments(Class<?>[] parameterTypes) {
        Object[] args = new Object[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> type = parameterTypes[i];

            if (type.equals(int.class)) {
                args[i] = 0; // Mock int value as 0
            } else if (type.equals(String.class)) {
                args[i] = "mock"; // Mock String value as "mock"
            } else {
                args[i] = null; // For other types, mock as null
            }
        }

        return args;
    }

    // Print the test statistics
    private static void printStatistics() {
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed tests: " + passedTests);
        System.out.println("Failed tests: " + (totalTests - passedTests));
    }
}