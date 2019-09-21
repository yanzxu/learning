package com.game.chat.server.function;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public class CatBean implements Serializable {
    private int type;

    private HashSet<String> clients;

    private HashSet<String> to;

    public HashMap<String, ClientBean> onlines;

    private String info;

    private String timer;

    private String name;

    private String fileName;

    private int size;

    private String ip;

    private int port;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public HashSet<String> getTo() {
        return to;
    }

    public void setTo(HashSet<String> to) {
        this.to = to;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public HashSet<String> getClients() {
        return clients;
    }

    public void setClients(HashSet<String> clients) {
        this.clients = clients;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public HashMap<String, ClientBean> getOnlines() {
        return onlines;
    }

    public void setOnlines(HashMap<String, ClientBean> onlines) {
        this.onlines = onlines;
    }


}
