package de.garbereder;

import junit.framework.TestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Instant;

public class TestAutomationTestFinal extends TestCase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected TestAutomation testAutomation = new TestAutomation();

    private class DummyClass {
        protected Instant instant;

        public Instant getInstant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }
    }

    @Test
    public void testInstant() throws Exception {
        DummyClass o = new DummyClass();
        testAutomation.addInstance(Instant.now());
        testAutomation.addInstance(Instant.now());
        testAutomation.testGetterAndSetterAutomated(o);
    }
}