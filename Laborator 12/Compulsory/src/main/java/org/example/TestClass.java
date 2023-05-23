package org.example;

import org.testng.annotations.Test;

public class TestClass {

    @Test
    public static void testMethod1() {
        System.out.println("Test method 1");
    }

    public static void nonTestMethod() {
        System.out.println("Non-test method");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test method 2");
        throw new RuntimeException("Test method 2 failed");
    }
}