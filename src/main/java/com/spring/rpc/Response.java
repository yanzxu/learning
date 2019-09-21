package com.spring.rpc;

import java.io.Serializable;

public class Response implements Serializable {
    private static final long serialVersionUID = -2393333111247658778L;
    private Object result;

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
