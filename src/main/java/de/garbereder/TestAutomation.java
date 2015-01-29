package de.garbereder;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.mockito.Mockito;

/**
 * A collection of helpers to get an automated basic testing for basic methods.
 *
 * @author Gerrit Garbereder
 * @version 0.1
 */
public class TestAutomation {

    private interface Callback {
        public <A> void call(A o1, A o2);
    }

    /**
     * Call automated all setters with one argument to modify both objects and compare
     * the equality of the objects.
     *
     * @param o1  An instance of the system under test (SUT)
     * @param o2  A second instance of the SUT
     * @param <A> The class of the SUT
     * @return true if test was successful, false otherwise
     * @throws Exception      If the testing it self failed
     * @throws AssertionError If the test failed
     */
    public static final <A> void testEqualsAutomated(A o1, A o2) throws Exception, AssertionError {
        Assert.assertNotEquals(o1, null);
        Assert.assertNotEquals(o2, new Object());
        Assert.assertEquals(o1, o1);
        Assert.assertEquals(o1, o2);

        testAutomated(o1, o2,
                new Callback() {
                    @Override
                    public <A> void call(A o1, A o2) {
                        Assert.assertNotEquals(o1, o2);
                        Assert.assertNotEquals(o2, o1);
                    }
                },
                new Callback() {
                    @Override
                    public <A> void call(A o1, A o2) {
                        Assert.assertEquals(o1, o2);
                        Assert.assertEquals(o2, o1);
                    }
                }
        );

    }

    /**
     * Call automated all setters with one argument to modify both objects and compare
     * the hash codes of the objects.
     *
     * @param o1  An instance of the system under test (SUT)
     * @param o2  A second instance of the SUT
     * @param <A> The class of the SUT
     * @throws Exception      If the testing it self failed
     * @throws AssertionError If the test failed
     */
    public static final <A> void testHashCodeAutomated(A o1, A o2) throws Exception, AssertionError {
        Assert.assertEquals(o1.hashCode(), o2.hashCode());

        testAutomated(o1, o2,
                new Callback() {
                    @Override
                    public <A> void call(A o1, A o2) {
                        Assert.assertNotEquals(o1.hashCode(), o2.hashCode());
                    }
                },
                new Callback() {
                    @Override
                    public <A> void call(A o1, A o2) {
                        Assert.assertEquals(o1.hashCode(), o2.hashCode());
                    }
                }
        );
    }

    /**
     * Call automated all getters and setters with one argument and compares the results
     *
     * @param o1  An instance of the system under test (SUT)
     * @param <A> The class of the SUT
     */
    public static final <A> void testGetterAndSetterAutomated(A o1) throws Exception, AssertionError {

        Method[] methods = o1.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method setter = methods[i];
            if (!setter.getName().startsWith("set")) {
                continue;
            }
            if (setter.getParameterCount() != 1) {
                continue;
            }
            Method getter = o1.getClass().getMethod(setter.getName().replaceFirst("set", "get"));
            if (getter == null) {
                continue;
            }

            Class<?>[] parameterTypes = setter.getParameterTypes();
            Class<?> paramType = parameterTypes[0];
            Object param = createObjectFromType(paramType);

            // assure not to set the current value
            Assert.assertNotEquals(param, getter.invoke(o1));
            // call the setter on the given object
            setter.invoke(o1, param);
            // assure that the values match after calling setter
            Assert.assertEquals(param, getter.invoke(o1));
        }
    }

    /**
     * Call automated all setters with one argument to modify both objects.
     * The algorithm has three phases, after each phase a callback is called to
     * handle the assertions.
     * <p/>
     * 1. Object 1's setter is called
     * 2. Object 2's setter is called with a different value
     * 3. Object 2's setter is called with the same value as in 1.
     *
     * @param o1  An instance of the system under test (SUT)
     * @param o2  A second instance of the SUT
     * @param <A> The class of the SUT
     * @param notEqualsCallback  Callback 1
     * @param equalsCallback  Callback 1
     * @throws Exception      If the testing it self failed
     * @throws AssertionError If the test failed
     */
    private static final <A> void testAutomated(A o1, A o2, Callback notEqualsCallback, Callback equalsCallback) throws Exception, AssertionError {
        Method[] methods = o1.getClass().getMethods();
        for (int i = 0; i < methods.length; ++i) {
            Method method = methods[i];
            if (!method.getName().startsWith("set")) {
                continue;
            }
            if (method.getParameterCount() != 1) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> paramType = parameterTypes[0];

            Object[] params = createTwoObjectsFromType(paramType);

            equalsCallback.call(o1, o2);
            if( !paramType.isPrimitive() ) {
                method.invoke(o1, new Object[]{ null });
                method.invoke(o2, new Object[]{ null });
            }
            equalsCallback.call(o1, o2);
            invoke(o1, o2, method, params[0], notEqualsCallback);
            invoke(o2, o1, method, params[1], notEqualsCallback);
            invoke(o2, o1, method, params[0], equalsCallback);
        }
    }

    /**
     * Invoke the method on o1 and check if the callback is true, if not display an error message
     *
     * @param o1       Object on which the method is called
     * @param o2       Object to compare
     * @param method   Method to call on o1
     * @param param    Parameter transmitted to method
     * @param callback The callback to check the result
     * @throws Exception      If the testing it self failed
     * @throws AssertionError If the test failed
     */
    private static final void invoke(Object o1, Object o2, Method method, Object param, Callback callback) throws Exception, AssertionError {
        method.invoke(o1, param);
        try {
            callback.call(o1, o2);
        } catch (Error e) {
            printErrorMsg(method, o1, param);
            throw e;
        }
    }

    /**
     * Print an error message on the screen with information about the failure location
     *
     * @param invokedMethod The method called on targetObject
     * @param targetObject  The object on which the method was called on
     * @param setValue      The value which was used in the method as parameter
     */
    private static final void printErrorMsg(Method invokedMethod, Object targetObject, Object setValue) {
        System.out.println("Test failed when invoking " + targetObject + "." + invokedMethod.getName() + "(" + setValue + ")");
    }

    /**
     * Get a dummy value for the given type.
     * If possible, use Mockito to create a mock.
     *
     * @param type The class descriptor for the type to generate
     * @return Object a value of the given type
     */
    private static final Object createObjectFromType(Class<?> type) {
        Object param1 = null;
        if (type == long.class || type == Long.class) {
            param1 = 1L;
        } else if (type == int.class || type == Integer.class) {
            param1 = 1;
        } else if (type == float.class || type == Float.class) {
            param1 = 1.0f;
        } else if (type == double.class || type == Double.class) {
            param1 = 1.0d;
        } else if (type == byte.class || type == Byte.class) {
            param1 = (byte) 1;
        } else if (type == short.class || type == Short.class) {
            param1 = (short) 1;
        } else if (type == boolean.class || type == Boolean.class) {
            param1 = true;
        } else if (type == char.class || type == Character.class) {
            param1 = 'a';
        } else if (type == String.class) {
            param1 = "abc";
        } else {
            param1 = Mockito.mock(type);
        }
        return param1;
    }

    /**
     * Get a dummy value for the given type.
     * If possible, use Mockito to create a mock.
     *
     * @param type The class descriptor for the type to generate
     * @return Object with to different values of the given type
     */
    private static final Object[] createTwoObjectsFromType(Class<?> type) {

        Object param1 = null;
        Object param2 = null;
        if (type == long.class || type == Long.class) {
            param1 = 1L;
            param2 = 2L;
        } else if (type == int.class || type == Integer.class) {
            param1 = 1;
            param2 = 2;
        } else if (type == float.class || type == Float.class) {
            param1 = 1.0f;
            param2 = 2.0f;
        } else if (type == double.class || type == Double.class) {
            param1 = 1.0d;
            param2 = 2.0d;
        } else if (type == byte.class || type == Byte.class) {
            param1 = (byte) 1;
            param2 = (byte) 2;
        } else if (type == short.class || type == Short.class) {
            param1 = (short) 1;
            param2 = (short) 2;
        } else if (type == boolean.class || type == Boolean.class) {
            param1 = true;
            param2 = false;
        } else if (type == char.class || type == Character.class) {
            param1 = 'a';
            param2 = 'b';
        } else if (type == String.class) {
            param1 = "abc";
            param2 = "xyz";
        } else {
            param1 = Mockito.mock(type);
            param2 = Mockito.mock(type);
        }

        Object[] result = {param1, param2};
        return result;
    }

}
