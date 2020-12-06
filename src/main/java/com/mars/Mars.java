package com.mars;

import com.mars.enums.Command;
import com.mars.enums.Direction;

public class Mars {
    private int rangeOfX;

    private int rangeOfY;

    // 起点
    private Location startLocation;

    // 当前坐标位置
    private Location currentLocation;

    public Mars(int x, int y, Location startLocation, Location currentLocation) {
        this.rangeOfX = x;
        this.rangeOfY = y;
        this.startLocation = startLocation;
        this.currentLocation = currentLocation;
    }

    public Direction getNextDir(Command command){

        return null;
    }

    public Location getNextLocation(Command command){
        return null;
    }

    public Location getEndLocation(String commands){
        return null;
    }
}
