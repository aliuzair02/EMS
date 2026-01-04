package org.template.common.models;

public class QueryParameter {

    private String key;
    private Object value;

    public QueryParameter(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public QueryParameter(){

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
