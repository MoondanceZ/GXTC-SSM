package com.rk.common.web;

public class SelectItem {
    private Object Value;
    private String Text;

    public SelectItem() {
    }

    public SelectItem(Object value, String text) {
        Value = value;
        Text = text;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object value) {
        Value = value;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }
}
