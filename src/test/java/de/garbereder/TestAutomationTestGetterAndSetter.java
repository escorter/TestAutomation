package de.garbereder;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestAutomationTestGetterAndSetter extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private class DummyClass {
        protected int myInt;

        public int getMyInt() {
            return myInt;
        }

        public void setMyInt(int myInt) {
            this.myInt = myInt;
        }
    }

    private class WrongGetter extends DummyClass {
        @Override
        public int getMyInt() {
            return 0;
        }

    }

    @Test
    public void testTestHashCodeAutomated() throws Exception {
        DummyClass o = new DummyClass();
        TestAutomation.testGetterAndSetterAutomated(o);
    }

    @Test
    public void testTestHashCodeAutomatedFails() throws Exception {
        WrongGetter o = new WrongGetter();
        try {
            TestAutomation.testGetterAndSetterAutomated(o);
        } catch (Error e) {
            return;
        }
        fail("testGetterAndSetterAutomated should have thrown an error");

    }

}