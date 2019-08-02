package com.spring.rpc;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private static final long serialVersionUID = -2393333111247658778L;
    private Object result;
}
