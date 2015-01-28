package de.garbereder;

import java.lang.reflect.Method;
import org.junit.Assert;
import org.mockito.Mockito;

/**
 * @author gerritgarbereder
 * @version 1.0
 */
public class EqualsTestAutomation {

    public static final <A> void testEqualsAutomated(A o1, A o2) throws Exception {
        Assert.assertNotEquals(o1, null);
        Assert.assertNotEquals(o1, new Object());
        Assert.assertEquals(o1, o2);

        Method[] methods = o1.getClass().getDeclaredMethods();
        for( int i = 0; i < methods.length; ++i ) {
            Method method = methods[i];
            if( !method.getName().startsWith("set") ) {
                continue;
            }
            if( method.getParameterCount() != 1 ) {
                continue;
            }

            Class<?>[] params = method.getParameterTypes();
            Class<?> paramType = params[0];

            Object param1 = null;
            Object param2 = null;
            if( paramType == long.class || paramType == Long.class ) {
                param1 = 1L;
                param2 = 2L;
            } else if( paramType == int.class || paramType == Integer.class ) {
                param1 = 1;
                param2 = 2;
            } else if( paramType == float.class || paramType == Float.class ) {
                param1 = 1.0f;
                param2 = 2.0f;
            } else if( paramType == double.class || paramType == Double.class ) {
                param1 = 1.0d;
                param2 = 2.0d;
            } else if( paramType == byte.class || paramType == Byte.class ) {
                param1 = 1;
                param2 = 2;
            } else if( paramType == short.class || paramType == Short.class ) {
                param1 = 1;
                param2 = 2;
            } else if( paramType == boolean.class || paramType == Boolean.class ) {
                param1 = true;
                param2 = false;
            } else if( paramType == char.class || paramType == Character.class ) {
                param1 = 'a';
                param2 = 'b';
            } else {
                param1 = Mockito.mock(paramType);
                param2 = Mockito.mock(paramType);
            }

            method.invoke(o1,param1);
            Assert.assertNotEquals(o1, o2);
            Assert.assertNotEquals(o2, o1);
            method.invoke(o2, param2);
            Assert.assertNotEquals(o1, o2);
            Assert.assertNotEquals(o2, o1);
            method.invoke(o2,param1);
            Assert.assertEquals(o1, o2);
            Assert.assertEquals(o2, o1);
        }
    }

}
