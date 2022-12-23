package agh.ics.oop;

import java.util.ArrayList;

public class Animal implements IMapElement{
    public IWorldMap map;
    public int energy;
    public MapDirection direction;
    public int startEnergy;
    public ArrayList<IPositionChangeObserver> observerList = new ArrayList<>();
    public Genes genes;
    public Vector2d position;

    // Constructor
    public Animal(){
        this.direction = MapDirection.NORTH;
        this.genes = new Genes();
        this.position = new Vector2d(1, 1);
    }

    public Animal(IWorldMap map){
        this();
        this.map = map;
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this(map);
        this.position = initialPosition;
    }

    public Animal(IWorldMap map, Vector2d initialPosition, int energy){
        this(map, initialPosition);
        this.energy = energy;
    }

    


    @Override
    public Vector2d getPosition() {
        return null;
    }

    public void addObserver(WorldMap WorldMap) {

    }

    public int getEnergy() {
        return 0;
    }

    public void turnBack() {
    }

    public void changeEnergy(int i) {
    }

    public int getAge() {
        return 0;
    }

    public int getChildren() {
        return 0;
    }

    public void move() {
    }


}
