package agh.ics.oop;

public class World {
    public static void main(String[] args){
        MapDirection direction = MapDirection.WEST;
        System.out.println(direction.rotate(7));
    }
}
