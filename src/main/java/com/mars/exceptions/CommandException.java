package com.mars.exceptions;

public class CommandException extends RuntimeException{
    private String msg;

    public CommandException() {
        super("指令非法！");
    }

    public CommandException(String msg) {
        super(msg);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
