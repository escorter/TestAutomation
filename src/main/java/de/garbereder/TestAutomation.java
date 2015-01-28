package de.garbereder;

import java.lang.reflect.Method;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 * A collection of helpers to get an automated basic testing for basic methods.
 *
 * @todo give detailed feedback if testfails
 *
 * @author Gerrit Garbereder
 * @version 0.1
 */
public class TestAutomation {

    private interface Callback {
        public <A> boolean invoke(A o1, A o2);
    }

    /**
     * Call automated all setters with one argument to modify both objects and compare
     * the equality of the objects.
     *
     * @param o1 An instance of the system under test (SUT)
     * @param o2 A second instance of the SUT
     * @param <A> The class of the SUT
     * @return true if test was successful, false otherwise
     * @throws Exception
     */
    public static final <A> boolean testEqualsAutomated(A o1, A o2) throws Exception {
        boolean ok = true;
        ok &= !o1.equals(null);
        ok &= !o1.equals(new Object());
        ok &= o1.equals(o2);

        ok &= testAutomated(o1, o2,
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        boolean ok = true;
                        ok &= !o1.equals(o2);
                        ok &= !o2.equals(o1);
                        return ok;
                    }
                },
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        boolean ok = true;
                        ok &= !o1.equals(o2);
                        ok &= !o2.equals(o1);
                        return ok;
                    }
                },
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        boolean ok = true;
                        ok &= o1.equals(o2);
                        ok &= o2.equals(o1);
                        return ok;
                    }
                }
        );

        return ok;
    }

    /**
     * Call automated all setters with one argument to modify both objects and compare
     * the hash codes of the objects.
     *
     * @param o1 An instance of the system under test (SUT)
     * @param o2 A second instance of the SUT
     * @param <A> The class of the SUT
     * @return true if test was successful, false otherwise
     */
    public static final <A> boolean testHashCodeAutomated(A o1, A o2) throws Exception {
        boolean ok = true;
        ok &= o1.hashCode() == o2.hashCode();

        ok &= testAutomated(o1, o2,
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        return o1.hashCode() != o2.hashCode();
                    }
                },
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        return o1.hashCode() != o2.hashCode();
                    }
                },
                new Callback() {
                    @Override
                    public <A> boolean invoke(A o1, A o2) {
                        return o1.hashCode() == o2.hashCode();
                    }
                }
        );
        return ok;
    }

    /**
     * Call automated all setters with one argument to modify both objects.
     * The algorithm has three phases, after each phase a callback is called to
     * handle the assertions.
     *
     * 1. Object 1's setter is called
     * 2. Object 2's setter is called with a different value
     * 3. Object 2's setter is called with the same value as in 1.
     *
     * @param o1 An instance of the system under test (SUT)
     * @param o2 A second instance of the SUT
     * @param <A> The class of the SUT
     * @param c1 Callback 1
     * @param c2 Callback 1
     * @param c3 Callback 1
     * @return true if test was successful, false otherwise
     */
    private static final <A> boolean testAutomated(A o1, A o2, Callback c1, Callback c2, Callback c3) throws Exception {

        boolean ok = true;

        Method[] methods = o1.getClass().getMethods();
        for( int i = 0; i < methods.length; ++i ) {
            Method method = methods[i];
            if( !method.getName().startsWith("set") ) {
                continue;
            }
            if( method.getParameterCount() != 1 ) {
                continue;
            }

            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?> paramType = parameterTypes[0];

            Object[] params = createObjectFromType(paramType);
            method.invoke(o1, params[0]);
            ok &= c1.invoke(o1, o2);
            method.invoke(o2, params[1]);
            ok &= c2.invoke(o1, o2);
            method.invoke(o2, params[0]);
            ok &= c3.invoke(o1, o2);

        }
        return ok;
    }

    /**
     * Get a dummy value for the given type.
     * If possible, use Mockito to create a mock.
     *
     * @param type The class descriptor for the type to generate
     * @return Object with to different values of the given type
     */
    private static final Object[] createObjectFromType(Class<?> type) {

        Object param1 = null;
        Object param2 = null;
        if( type == long.class || type == Long.class ) {
            param1 = 1L;
            param2 = 2L;
        } else if( type == int.class || type == Integer.class ) {
            param1 = 1;
            param2 = 2;
        } else if( type == float.class || type == Float.class ) {
            param1 = 1.0f;
            param2 = 2.0f;
        } else if( type == double.class || type == Double.class ) {
            param1 = 1.0d;
            param2 = 2.0d;
        } else if( type == byte.class || type == Byte.class ) {
            param1 = (byte) 1;
            param2 = (byte) 2;
        } else if( type == short.class || type == Short.class ) {
            param1 = (short) 1;
            param2 = (short) 2;
        } else if( type == boolean.class || type == Boolean.class ) {
            param1 = true;
            param2 = false;
        } else if( type == char.class || type == Character.class ) {
            param1 = 'a';
            param2 = 'b';
        } else {
            param1 = Mockito.mock(type);
            param2 = Mockito.mock(type);
        }

        Object[] result = {param1, param2};
        return result;
    }

}
