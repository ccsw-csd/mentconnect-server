package com.ccsw.mentconnect.user.logic;

public class SearchCriteria {
    private String operation;
    private Object datos;

    private String filterKey;

    public SearchCriteria(String filterKey, String operation, Object datos) {

        this.operation = operation;

        this.datos = datos;
        this.filterKey = filterKey;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public void setFilterKey(String filterKey) {
        this.filterKey = filterKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

}
