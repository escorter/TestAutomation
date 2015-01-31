package de.garbereder;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * This test case provides a basic structure to test a bunch of systems under test.
 * Do not use this without checking to code coverage.
 * This class can support you by avoiding writing duplicate code but can not provide 100% code coverage!
 *
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

    /**
     * Use this method to setup the TestAutomationTestCase in the most simple way.
     * The given class needs an empty constructor.
     * The given class will be tested on equality, hashCode and getters and setters.
     *
     * @param clazz The class to test
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    protected void emptyConstructorSetUp(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T sut = clazz.newInstance();
        T sut2 = clazz.newInstance();

        Set<Pair<T, T>> suts = new HashSet<Pair<T, T>>();
        suts.add(new Pair<T, T>(sut, sut2));
        this.setSystemsUnderTest(suts);
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
