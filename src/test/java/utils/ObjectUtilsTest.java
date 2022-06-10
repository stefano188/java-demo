package utils;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import models.Z3TBGERE;

public class ObjectUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testGetterMethods() {
//        System.out.println(ObjectUtils.generateSetterGetter(SampleClass.class));
        ObjectUtils.generateSetterGetterPrint(Z3TBGERE.class);

//        List<String> methodByInstance = testPrintGetterSetterMethods(new SampleClass());
//        System.out.println(methodByInstance);
//
//        List<String> methodByClass = testPrintGetterSetterMethods(SampleClass.class);
//        System.out.println(methodByClass);

//        ObjectUtils.loadFieldSetterMethodMap(SampleClass.class)
//                .forEach((k, v) -> System.out.println(k.getName() + " - " + v.getName()));

        Assert.assertTrue(true);
    }

    private <T> List<String> testPrintGetterSetterMethods(T instanceOfClass) {
        List<String> declaredMethods = new ArrayList<>();
        declaredMethods.addAll(ObjectUtils.filterGetterMethods(instanceOfClass).map(Method::getName).collect(Collectors.toList()));
        declaredMethods.addAll(ObjectUtils.filterSetterMethods(instanceOfClass).map(Method::getName).collect(Collectors.toList()));
        return declaredMethods;
    }
}