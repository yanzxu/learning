package com.mars;

import com.mars.enums.Direction;

public class MarsApplication {
    public static void main(String[] args) {
        final Mars mars = new Mars(5,5,new Location(1,2, Direction.N),null);
    }
}
