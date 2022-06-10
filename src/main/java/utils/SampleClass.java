package utils;

import java.util.List;

public class SampleClass {
    private int intVal = 1;
    private long longVal = 1L;
    private double doubleVal = 1.1D;
    private boolean boolVal;

    private Integer integerVal;
    private String strVal = "aString";
    private String strVal2;

    private FirstChildClass firstChild;

    public int getIntVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public long getLongVal() {
        return longVal;
    }

    public void setLongVal(long longVal) {
        this.longVal = longVal;
    }

    public double getDoubleVal() {
        return doubleVal;
    }

    public void setDoubleVal(double doubleVal) {
        this.doubleVal = doubleVal;
    }

    public Integer getIntegerVal() {
        return integerVal;
    }

    public void setIntegerVal(Integer integerVal) {
        this.integerVal = integerVal;
    }

    public String getStrVal() {
        return strVal;
    }

    public void setStrVal(String strVal) {
        this.strVal = strVal;
    }

    public String getStrVal2() {
        return strVal2;
    }

    public void setStrVal2(String strVal2) {
        this.strVal2 = strVal2;
    }

    public boolean isBoolVal() {
        return boolVal;
    }

    public void setBoolVal(boolean boolVal) {
        this.boolVal = boolVal;
    }

    public FirstChildClass getFirstChild() {
        return firstChild;
    }

    public void setFirstChild(FirstChildClass firstChild) {
        this.firstChild = firstChild;
    }
}

class FirstChildClass {

    private String name;
    private Double doubleVal;
    private Boolean titled;

    private List<String> stringList;
    private List<SecondChildClass> objList;

    public String getName() {
        return name;
    }

    public Double getDoubleVal() {
        return doubleVal;
    }

    public void setDoubleVal(Double doubleVal) {
        this.doubleVal = doubleVal;
    }

    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public Boolean getTitled() {
        return name != null && name.length() > 0;
    }

    public void setTitled(Boolean titled) {
        this.titled = titled;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SecondChildClass> getObjList() {
        return objList;
    }

    public void setObjList(List<SecondChildClass> objList) {
        this.objList = objList;
    }

}

class SecondChildClass {

    private String sVal;
    private Long longVal;

    public String getsVal() {
        return sVal;
    }

    public void setsVal(String sVal) {
        this.sVal = sVal;
    }

    public Long getLongVal() {
        return longVal;
    }

    public void setLongVal(Long longVal) {
        this.longVal = longVal;
    }

}
