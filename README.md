# TestAutomation
This project provides some helpers to make testing less annoying and let you focus on the important tests. At the current early development state it provides a helper to test the equals() method and the hashCode().
Note: The automated testing works only for POJOs that have no fancy magic since it relies on the permise that the object is only modified by setter methods.

## Example:

```java

    private TestAutomation testAutomation;

    @Before
    public void setUp() {
        testAutomation = new TestAutomation();
    }

    @Test
    public void testAutomatedEquals() throws Exception {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        testAutomation.testEqualsAutomated(myObject1, myObject2);
    }
    
    @Test
    public void testAutomatedHash() throws Exception {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        testAutomation.testHashCodeAutomated(myObject1, myObject2);
    }
    
    @Test
    public void testAutomatedGetterAndSetter() throws Exception {
        MyObject myObject = new MyObject();
        testAutomation.testGetterAndSetterAutomated(myObject);
    }
```

## TestAutomationTestCase
todo

## Handling final classes
todo
