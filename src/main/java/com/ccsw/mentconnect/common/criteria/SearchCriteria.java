package com.ccsw.mentconnect.common.criteria;

public class SearchCriteria {

    private String key;
    private String operation;
    private Object firstValue;
    private Object secondValue;

    public SearchCriteria(String key, String operation, Object firstValue, Object secondValue) {
        this.key = key;
        this.operation = operation;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(Object firstValue) {
        this.firstValue = firstValue;
    }

    public Object getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(Object secondValue) {
        this.secondValue = secondValue;
    }
    
}
