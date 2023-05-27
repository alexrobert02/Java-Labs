package org.example;

import org.testng.annotations.Test;

public class TestClass {

    @Test
    public static void testMethod1() {
        System.out.println("Test method 1");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test method 2");
        throw new RuntimeException("Test method 2 failed");
    }

    @Test
    public void nonStaticTestMethod() {
        System.out.println("Non-static test method");
    }

    @Test
    public static void testMethodWithIntArgument(int value) {
        System.out.println("Test method with int argument: " + value);
    }

    @Test
    public static void testMethodWithStringArgument(String text) {
        System.out.println("Test method with String argument: " + text);
    }

    public static void nonTestMethod() {
        System.out.println("Non-test method");
    }
}
