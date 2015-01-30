package de.garbereder;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * This test case provides a basic structure to test a bunch of systems under test.
 *
 * @author gerritgarbereder
 * @version 1.0
 */
public class TestAutomationTestCase<T> extends TestCase {

    protected TestAutomation testAutomation;
    protected Set<Pair<T, T>> systemsUnderTest;

    public TestAutomationTestCase() {
        testAutomation = new TestAutomation();
    }

    public TestAutomation getTestAutomation() {
        return testAutomation;
    }

    public void setTestAutomation(TestAutomation testAutomation) {
        this.testAutomation = testAutomation;
    }

    public Set<Pair<T, T>> getSystemsUnderTest() {
        return systemsUnderTest;
    }

    public void setSystemsUnderTest(Set<Pair<T, T>> systemsUnderTest) {
        this.systemsUnderTest = systemsUnderTest;
    }

    public void addInstance(Object o) {
        testAutomation.addInstance(o);
    }

    @Test
    public void testEquals() throws Exception {
        Assert.assertNotEquals(null, testAutomation);
        Assert.assertNotEquals(null, systemsUnderTest);
        for (Pair<T, T> sut : systemsUnderTest) {
            testAutomation.testEqualsAutomated(sut.first, sut.second);
        }
    }

    @Test
    public void testHash() throws Exception {
        Assert.assertNotEquals(null, testAutomation);
        Assert.assertNotEquals(null, systemsUnderTest);
        for (Pair<T, T> sut : systemsUnderTest) {
            testAutomation.testHashCodeAutomated(sut.first, sut.second);
        }
    }

    @Test
    public void testGetterAndSetter() throws Exception {
        Assert.assertNotEquals(null, testAutomation);
        Assert.assertNotEquals(null, systemsUnderTest);
        for (Pair<T, T> sut : systemsUnderTest) {
            testAutomation.testGetterAndSetterAutomated(sut.first);
        }
    }
}
