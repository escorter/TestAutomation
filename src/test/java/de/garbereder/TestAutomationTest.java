package de.garbereder;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestAutomationTest extends TestCase {

    private DummyClassWithAllBasicDatatypes dummy1;
    private DummyClassWithAllBasicDatatypes dummy2;
    private CorrectEqualsAndHashCode correct1;
    private CorrectEqualsAndHashCode correct2;
    private WrongEqualsAndHashCode wrong1;
    private WrongEqualsAndHashCode wrong2;

    @Before
    public void setUp() throws Exception {
        dummy1 = new DummyClassWithAllBasicDatatypes();
        dummy2 = new DummyClassWithAllBasicDatatypes();
        correct1 = new CorrectEqualsAndHashCode();
        correct2 = new CorrectEqualsAndHashCode();
        wrong1 = new WrongEqualsAndHashCode();
        wrong2 = new WrongEqualsAndHashCode();
        System.out.println("INIT INIT INIT");
    }

    private class DummyClassWithAllBasicDatatypes {
        protected int myInt;
        protected Integer myIntObject;
        protected float myFloat;
        protected Float myFloatObject;
        protected double myDouble;
        protected Double myDoubleObject;
        protected long myLong;
        protected Long myLongObject;
        protected byte myByte;
        protected Byte myByteObject;
        protected short myShort;
        protected Short myShortObject;
        protected char myChar;
        protected Character myCharObject;
        protected boolean myBoolean;
        protected Boolean myBooleanObject;
        protected String myString;

        public int setCounter = 0;

        public String getMyString() {
            return myString;
        }

        public void setMyString(String myString) {
            setCounter++;
            this.myString = myString;
        }

        public int getMyInt() {
            return myInt;
        }

        public void setMyInt(int myInt) {
            setCounter++;
            this.myInt = myInt;
        }

        public Integer getMyIntObject() {
            return myIntObject;
        }

        public void setMyIntObject(Integer myIntObject) {
            setCounter++;
            this.myIntObject = myIntObject;
        }

        public float getMyFloat() {
            return myFloat;
        }

        public void setMyFloat(float myFloat) {
            setCounter++;
            this.myFloat = myFloat;
        }

        public Float getMyFloatObject() {
            return myFloatObject;
        }

        public void setMyFloatObject(Float myFloatObject) {
            setCounter++;
            this.myFloatObject = myFloatObject;
        }

        public double getMyDouble() {
            return myDouble;
        }

        public void setMyDouble(double myDouble) {
            setCounter++;
            this.myDouble = myDouble;
        }

        public Double getMyDoubleObject() {
            return myDoubleObject;
        }

        public void setMyDoubleObject(Double myDoubleObject) {
            setCounter++;
            this.myDoubleObject = myDoubleObject;
        }

        public long getMyLong() {
            return myLong;
        }

        public void setMyLong(long myLong) {
            setCounter++;
            this.myLong = myLong;
        }

        public Long getMyLongObject() {
            setCounter++;
            return myLongObject;
        }

        public void setMyLongObject(Long myLongObject) {
            setCounter++;
            this.myLongObject = myLongObject;
        }

        public byte getMyByte() {
            return myByte;
        }

        public void setMyByte(byte myByte) {
            setCounter++;
            this.myByte = myByte;
        }

        public Byte getMyByteObject() {
            return myByteObject;
        }

        public void setMyByteObject(Byte myByteObject) {
            setCounter++;
            this.myByteObject = myByteObject;
        }

        public short getMyShort() {
            return myShort;
        }

        public void setMyShort(short myShort) {
            setCounter++;
            this.myShort = myShort;
        }

        public Short getMyShortObject() {
            return myShortObject;
        }

        public void setMyShortObject(Short myShortObject) {
            setCounter++;
            this.myShortObject = myShortObject;
        }

        public char getMyChar() {
            return myChar;
        }

        public void setMyChar(char myChar) {
            setCounter++;
            this.myChar = myChar;
        }

        public Character getMyCharObject() {
            return myCharObject;
        }

        public void setMyCharObject(Character myCharObject) {
            setCounter++;
            this.myCharObject = myCharObject;
        }

        public boolean isMyBoolean() {
            return myBoolean;
        }

        public void setMyBoolean(boolean myBoolean) {
            setCounter++;
            this.myBoolean = myBoolean;
        }

        public Boolean getMyBooleanObject() {
            return myBooleanObject;
        }

        public void setMyBooleanObject(Boolean myBooleanObject) {
            setCounter++;
            this.myBooleanObject = myBooleanObject;
        }

        @Override
        public String toString() {
            return "DummyClassWithAllBasicDatatypes{" +
                    "myInt=" + myInt +
                    ", myIntObject=" + myIntObject +
                    ", myFloat=" + myFloat +
                    ", myFloatObject=" + myFloatObject +
                    ", myDouble=" + myDouble +
                    ", myDoubleObject=" + myDoubleObject +
                    ", myLong=" + myLong +
                    ", myLongObject=" + myLongObject +
                    ", myByte=" + myByte +
                    ", myByteObject=" + myByteObject +
                    ", myShort=" + myShort +
                    ", myShortObject=" + myShortObject +
                    ", myChar=" + myChar +
                    ", myCharObject=" + myCharObject +
                    ", myBoolean=" + myBoolean +
                    ", myBooleanObject=" + myBooleanObject +
                    ", myString=" + myString +
                    ", setCounter=" + setCounter +
                    '}';
        }
    }

    private class CorrectEqualsAndHashCode extends DummyClassWithAllBasicDatatypes {

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CorrectEqualsAndHashCode)) return false;

            CorrectEqualsAndHashCode that = (CorrectEqualsAndHashCode) o;

            if (myBoolean != that.myBoolean) return false;
            if (myByte != that.myByte) return false;
            if (myChar != that.myChar) return false;
            if (Double.compare(that.myDouble, myDouble) != 0) return false;
            if (Float.compare(that.myFloat, myFloat) != 0) return false;
            if (myInt != that.myInt) return false;
            if (myLong != that.myLong) return false;
            if (myShort != that.myShort) return false;
            if (myBooleanObject != null ? !myBooleanObject.equals(that.myBooleanObject) : that.myBooleanObject != null)
                return false;
            if (myByteObject != null ? !myByteObject.equals(that.myByteObject) : that.myByteObject != null)
                return false;
            if (myCharObject != null ? !myCharObject.equals(that.myCharObject) : that.myCharObject != null)
                return false;
            if (myDoubleObject != null ? !myDoubleObject.equals(that.myDoubleObject) : that.myDoubleObject != null)
                return false;
            if (myFloatObject != null ? !myFloatObject.equals(that.myFloatObject) : that.myFloatObject != null)
                return false;
            if (myIntObject != null ? !myIntObject.equals(that.myIntObject) : that.myIntObject != null) return false;
            if (myLongObject != null ? !myLongObject.equals(that.myLongObject) : that.myLongObject != null)
                return false;
            if (myShortObject != null ? !myShortObject.equals(that.myShortObject) : that.myShortObject != null)
                return false;
            if (myString != null ? !myString.equals(that.myString) : that.myString != null)
                return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = myInt;
            result = 31 * result + (myIntObject != null ? myIntObject.hashCode() : 0);
            result = 31 * result + (myFloat != +0.0f ? Float.floatToIntBits(myFloat) : 0);
            result = 31 * result + (myFloatObject != null ? myFloatObject.hashCode() : 0);
            temp = Double.doubleToLongBits(myDouble);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            result = 31 * result + (myDoubleObject != null ? myDoubleObject.hashCode() : 0);
            result = 31 * result + (int) (myLong ^ (myLong >>> 32));
            result = 31 * result + (myLongObject != null ? myLongObject.hashCode() : 0);
            result = 31 * result + (int) myByte;
            result = 31 * result + (myByteObject != null ? myByteObject.hashCode() : 0);
            result = 31 * result + (int) myShort;
            result = 31 * result + (myShortObject != null ? myShortObject.hashCode() : 0);
            result = 31 * result + (int) myChar;
            result = 31 * result + (myCharObject != null ? myCharObject.hashCode() : 0);
            result = 31 * result + (myBoolean ? 1 : 0);
            result = 31 * result + (myBooleanObject != null ? myBooleanObject.hashCode() : 0);
            result = 31 * result + (myString != null ? myString.hashCode() : 0);
            return result;
        }
    }

    private class WrongEqualsAndHashCode extends DummyClassWithAllBasicDatatypes {

        @Override
        public int hashCode() {
            return myInt;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WrongEqualsAndHashCode)) return false;

            WrongEqualsAndHashCode that = (WrongEqualsAndHashCode) o;

            if (myBoolean != that.myBoolean) return false;

            return true;
        }
    }

    @Test
    public void testTestEqualsAutomated() throws Exception {
        try {
            TestAutomation.testEqualsAutomated(dummy1, dummy2);
        } catch (Error e) {
            return;
        }
        fail("Expected AssertionError in testEqualsAutomated().");
    }

    @Test
    public void testTestHashCodeAutomated() throws Exception {
        try {
            TestAutomation.testHashCodeAutomated(dummy1, dummy2);
        } catch (Error e) {
            return;
        }
        fail("Expected AssertionError in testHashCodeAutomated().");
    }

    @Test
    public void testTestEqualsAutomatedCorrect() throws Exception {
        System.out.println(correct1);
        System.out.println(correct2);
        TestAutomation.testEqualsAutomated(correct1, correct2);
        Assert.assertEquals(17, correct1.setCounter);
        Assert.assertEquals(34, correct2.setCounter);
    }

    @Test
    public void testTestHashCodeAutomatedCorrect() throws Exception {
        TestAutomation.testHashCodeAutomated(correct1, correct2);
        Assert.assertEquals(17, correct1.setCounter);
        Assert.assertEquals(34, correct2.setCounter);
    }

    @Test
    public void testTestEqualsAutomatedWrong() throws Exception {
        try {
            TestAutomation.testEqualsAutomated(wrong1, wrong2);
        } catch (Error e) {
            return;
        }
        fail("Expected AssertionError in testEqualsAutomated().");
    }

    @Test
    public void testTestHashCodeAutomatedWrong() throws Exception {
        try {
            TestAutomation.testHashCodeAutomated(wrong1, wrong2);
        } catch (Error e) {
            return;
        }
        fail("Expected AssertionError in testHashCodeAutomated().");
    }
}