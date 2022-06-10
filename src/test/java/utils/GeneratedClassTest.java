package utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GeneratedClassTest {
    public void testMethod() {
        SampleClass instance = new SampleClass();
        instance.setIntVal(0);
        instance.setDoubleVal(0D);
        instance.setStrVal("");
        instance.setFirstChild(null);
        instance.setLongVal(0L);
        instance.setBoolVal(false);
        instance.setIntegerVal(0);
        instance.setStrVal2("");
        assertEquals(0, instance.getIntVal());
        assertEquals(0D, instance.getDoubleVal());
        assertEquals("", instance.getStrVal());
        assertEquals(null, instance.getFirstChild());
        assertEquals(0L, instance.getLongVal());
        assertEquals(false, instance.isBoolVal());
        assertEquals(0, instance.getIntegerVal(),0);
        assertEquals("", instance.getStrVal2());
    }

}
