package utils;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ObjectUtils {

    private static final String INSTANCE_VAR_NAME = "instance";

    public static <T> List<Field> loadDeclaredFields(T instanceOfClass) {
        return Arrays.asList(toClass(instanceOfClass).getDeclaredFields());
    }

    public static <T> List<Method> loadDeclaredMethods(T instanceOfClass) {
//        return instanceOfClass instanceof Class
//                ? Arrays.asList(((Class<?>) instanceOfClass).getDeclaredMethods())
//                : Arrays.asList(instanceOfClass.getClass().getDeclaredMethods());
        return Arrays.asList(toClass(instanceOfClass).getDeclaredMethods());
    }

//    public static <T> Map<Field, Method> loadFieldSetterMethodMap(T instanceOfClass) {
//        Map<Field, Method> fieldMethodMap = new HashMap<>();
//
//        List<Method> setterMethods = filterSetterMethods(instanceOfClass).collect(Collectors.toList());
//        loadDeclaredFields(instanceOfClass).stream().map(f -> {
//            Optional<Method> found = setterMethods.stream()
//                .filter(m -> m.getName().toLowerCase().endsWith(f.getName().toLowerCase())).findFirst();
//                    return found.map(method -> new AbstractMap.SimpleEntry<>(f, method)).orElse(null);
//            }).filter(Objects::nonNull).collect(Collectors.toList())
//            .forEach(e -> fieldMethodMap.put(e.getKey(), e.getValue()));
//
//        return fieldMethodMap;
//    }
//
//    public static <T> Map<Field, Method> loadFieldGetterMethodMap(T instanceOfClass) {
//        Map<Field, Method> fieldMethodMap = new HashMap<>();
//
//        List<Method> getterMethods = filterGetterMethods(instanceOfClass).collect(Collectors.toList());
//        loadDeclaredFields(instanceOfClass).stream().map(f -> {
//            Optional<Method> found = getterMethods.stream()
//                .filter(m -> m.getName().toLowerCase().endsWith(f.getName().toLowerCase())).findFirst();
//                    return found.map(method -> new AbstractMap.SimpleEntry<>(f, method)).orElse(null);
//                }).filter(Objects::nonNull).collect(Collectors.toList())
//                .forEach(e -> fieldMethodMap.put(e.getKey(), e.getValue()));
//
//        return fieldMethodMap;
//    }

    public static <T> Map<Field, Method> loadFieldMethodMap(T instanceOfClass, List<Method> methodList) {
        Map<Field, Method> fieldMethodMap = new HashMap<>();

//        List<Method> setterMethods = filterSetterMethods(instanceOfClass).collect(Collectors.toList());
        loadDeclaredFields(instanceOfClass).stream().map(f -> {
                    Optional<Method> found = methodList.stream()
                            .filter(m -> m.getName().toLowerCase().endsWith(f.getName().toLowerCase())).findFirst();
                    return found.map(method -> new AbstractMap.SimpleEntry<>(f, method)).orElse(null);
                }).filter(Objects::nonNull).collect(Collectors.toList())
                .forEach(e -> fieldMethodMap.put(e.getKey(), e.getValue()));

        return fieldMethodMap;
    }

    public static <T> Stream<Method> filterGetterMethods(T instanceOfClass) {
        return loadDeclaredMethods(instanceOfClass)
                .stream().filter(m -> m.getName().startsWith("get") || m.getName().startsWith("is"));
    }

    public static <T> Stream<Method> filterSetterMethods(T instanceOfClass) {
        return loadDeclaredMethods(instanceOfClass)
                .stream().filter(m -> m.getName().startsWith("set"));
    }

    public static <T> void generateSetterGetterPrint(T instanceOfClass) {
        printToFile(generateSetterGetterMethods(instanceOfClass));
    }

    public static <T> List<String> generateSetterGetterMethods(T instanceOfClass) {
        List<String> strList = new ArrayList<>();
        Class<?> toClass = toClass(instanceOfClass);
        String className = getClassName(toClass(instanceOfClass));
        strList.add("@Test\n");
        strList.add("public void testMethod() {");
        strList.add(className + " " + INSTANCE_VAR_NAME + " = new " + className + "();");

        List<String> methods = new ArrayList<>();
        loadFieldMethodMap(instanceOfClass, filterSetterMethods(instanceOfClass).collect(Collectors.toList()))
            .forEach((k, v) -> {
                try {
                    String typeName = toClass.getDeclaredField(k.getName()).getType().getName();
                    String stmt = INSTANCE_VAR_NAME+"."+v.getName()+"("+valueByType(typeName)+");";

                    methods.add(stmt);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            });

        loadFieldMethodMap(instanceOfClass, filterGetterMethods(instanceOfClass).collect(Collectors.toList()))
                .forEach((k, v) -> {
                    try {
                        String typeName = toClass.getDeclaredField(k.getName()).getType().getName();
                        String expectedVal = valueByType(typeName);
                        String stmt = "assertEquals("+expectedVal+", " + INSTANCE_VAR_NAME+"."+v.getName()+"());";

                        methods.add(stmt);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                });

        strList.addAll(methods);

//        strList.add("\nassertNotNull(model.toString());");
        strList.add("}");
        return strList;
    }

    private static <T> Class<?> toClass(T instanceOfClass) {
        return instanceOfClass instanceof Class
                ? ((Class<?>) instanceOfClass)
                : instanceOfClass.getClass();
    }

    private static String getClassName(Class<?> aClass) {
        String str = aClass.getName();
        int idx = str.lastIndexOf(".");
        int i = idx == -1 ? 0 : idx + 1;
        return str.substring(i);
    }

    private static String valueByType(String type) {
        String s = "";
        String typ = type.toLowerCase();
        if (typ.contains("int")) {
            s += "0";
        } else if (typ.contains("double")) {
            s += "0D";
        } else if (typ.contains("long")) {
            s += "0L";
        } else if (typ.contains("string")) {
            s += "\"\"";
        } else if (typ.contains("bool")) {
            s += false;
        } else {
            s += "null";
        }
        return s;
    }

    private static void printToFile(List<String> stringList) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("generatedSource.txt");
            FileWriter finalFw = fw;
            stringList.forEach(s -> {
                try {
                    finalFw.write(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//    public static void main() {
//        SampleClass sampleClass = new SampleClass();
//        ObjectUtils.filterGetterMethods(sampleClass).map(m -> {
//            System.out.println(m.getName());
//            return m.getName();
//        }).collect(Collectors.toList());
//    }
}
