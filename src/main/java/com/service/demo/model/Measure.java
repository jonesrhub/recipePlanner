package com.service.demo.model;

public enum Measure {

    HALF (189 , 0.5),
    QUARTER (188 , 0.25);

    private int unicodeValue;
    private double integerValue;

    Measure(int unicodeValue, double integerValue){
        this.unicodeValue = unicodeValue;
        this.integerValue = integerValue;
    }

    public int getUnicodeValue() {
        return unicodeValue;
    }

    public void setUnicodeValue(int unicodeValue) {
        this.unicodeValue = unicodeValue;
    }

    public double getIntegerValue() {
        return integerValue;
    }

    public void setIntegerValue(double integerValue) {
        this.integerValue = integerValue;
    }

    public static Double getIntegerByUnicode(final int unicodeValue){
        for(Measure measure : values()){
            if(measure.getUnicodeValue() == unicodeValue){
                return measure.getIntegerValue();
            }
        }
        return null;
    }
}

/*    private static final int HALF_MEASURE = 189;
private static final int QUARTER_MEASURE = 188;
    private static final double HALF_MEASURE_INT = 0.5;
    private static final double QUARTER_MEASUR_INT = 0.25;
    */
