package de.garbereder;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashSet;

public class TestAutomationTestCaseTest extends TestCase {

    private class A {
        protected int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof A)) return false;

            A a = (A) o;

            if (i != a.i) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return i;
        }
    }

    private class B {
        protected int i;

        public int getI() {
            return i;
        }

        public void setI(int i) {
        }
    }

    TestAutomationTestCase<A> testAutomationTestCaseA;
    TestAutomationTestCase<B> testAutomationTestCaseB;

    @Before
    public void setUp() throws Exception {
        testAutomationTestCaseA = new TestAutomationTestCase<A>();
        HashSet<Pair<A, A>> systemsUnderTest = new HashSet<Pair<A, A>>();
        systemsUnderTest.add(new Pair<A, A>(new A(), new A()));
        testAutomationTestCaseA.setSystemsUnderTest(systemsUnderTest);

        testAutomationTestCaseB = new TestAutomationTestCase<B>();
        HashSet<Pair<B, B>> systemsUnderTestB = new HashSet<Pair<B, B>>();
        systemsUnderTestB.add(new Pair<B, B>(new B(), new B()));
        testAutomationTestCaseB.setSystemsUnderTest(systemsUnderTestB);
    }

    @Test
    public void testTestAutomationEqualsTest() throws Exception {
        testAutomationTestCaseA.testEquals();
    }

    @Test
    public void testTestAutomationHashTest() throws Exception {
        testAutomationTestCaseA.testHash();
    }

    @Test
    public void testTestAutomationGetterAndSetterTest() throws Exception {
        testAutomationTestCaseA.testGetterAndSetter();
    }

    @Test
    public void testTestAutomationEqualsTestB() throws Exception {
        try {
            testAutomationTestCaseB.testEquals();
        } catch (AssertionError e) {
            return;
        }
        fail("Expected Assertion Error, but got none");
    }

    @Test
    public void testTestAutomationHashTestB() throws Exception {
        try {
            testAutomationTestCaseB.testHash();
        } catch (AssertionError e) {
            return;
        }
        fail("Expected Assertion Error, but got none");
    }

    @Test
    public void testTestAutomationGetterAndSetterTestB() throws Exception {
        try {
            testAutomationTestCaseB.testGetterAndSetter();
        } catch (AssertionError e) {
            return;
        }
        fail("Expected Assertion Error, but got none");
    }
}