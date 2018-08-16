package com.example.neeru.architecturecomponent.data_wrapper;

public class DataWrapper<T> {
    private T object;
    private String error;

    public DataWrapper(T object, String error) {
        this.object = object;
        this.error = error;
    }

    public T getData() {
        return object;
    }

    public String getError() {
        return error;
    }


}
