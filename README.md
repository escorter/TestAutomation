# TestAutomation
This project provides some helpers to make testing less annoying and let you focus on the important tests. At the current early development state it provides a helper to test the equals() method and the hashCode().
Note: The automated testing works only for POJOs that have no fancy magic since it relies on the permise that the object is only modified by setter methods.

## Example:

```java
    @Test
    public void testAutomated() throws Exception {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        Assert.assertTrue(TestAutomation.testEqualsAutomated(myObject1, myObject2));
        Assert.assertTrue(TestAutomation.testHashCodeAutomated(myObject1, myObject2));
    }
```
