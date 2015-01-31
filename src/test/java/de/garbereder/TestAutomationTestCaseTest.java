package de.garbereder;

import de.garbereder.util.CorrectClass;
import de.garbereder.util.DefectClass;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestAutomationTestCaseTest extends TestCase {

    TestAutomationTestCase<CorrectClass> testAutomationTestCaseA;
    TestAutomationTestCase<DefectClass> testAutomationTestCaseB;

    @Before
    public void setUp() throws Exception {
        testAutomationTestCaseA = new TestAutomationTestCase<CorrectClass>();
        HashSet<Pair<CorrectClass, CorrectClass>> systemsUnderTest = new HashSet<Pair<CorrectClass, CorrectClass>>();
        systemsUnderTest.add(new Pair<CorrectClass, CorrectClass>(new CorrectClass(), new CorrectClass()));
        testAutomationTestCaseA.setSystemsUnderTest(systemsUnderTest);

        testAutomationTestCaseB = new TestAutomationTestCase<DefectClass>();
        HashSet<Pair<DefectClass, DefectClass>> systemsUnderTestB = new HashSet<Pair<DefectClass, DefectClass>>();
        systemsUnderTestB.add(new Pair<DefectClass, DefectClass>(new DefectClass(), new DefectClass()));
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

    public void testEmptyConstructorHelper() throws Exception {
        TestAutomationTestCase<CorrectClass> sut = new TestAutomationTestCase<CorrectClass>();
        sut.emptyConstructorSetUp(CorrectClass.class);
        Set<Pair<CorrectClass, CorrectClass>> suts = sut.getSystemsUnderTest();
        Pair<CorrectClass, CorrectClass> expected = new Pair(new CorrectClass(), new CorrectClass());
        Object[] sutsArray = suts.toArray();
        Assert.assertEquals(1, suts.size());
        Assert.assertEquals(expected, sutsArray[0]);
    }
}