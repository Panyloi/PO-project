package agh.ics.oop;

<<<<<<< HEAD
import java.util.ArrayList;

public class Animal implements IMapElement{
    public IWorldMap map;
    public int energy;
    public MapDirection direction;
    public int startEnergy;
    public ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    public Genes genes;
    public int lengthOfGenes;
    public Vector2d position;
    public int age;
    public int children;

    public Animal(int size){
        this.direction = MapDirection.NORTH;
        this.lengthOfGenes = size;
        this.genes = new Genes(size);
        this.position = new Vector2d(1, 1);
        this.age = 0;
        this.children = 0;
    }

    // to fill constructors
    public Animal(Animal strongerParent, Animal weakerParent, int energy, int minMutation, int maxMutation, int mutationVariant) {
    }

    public Animal(Vector2d position, int startEnergy, int genomeLength) {
    }


    public Animal(int size, IWorldMap map){
        this(size);
        this.map = map;
    }

    public Animal(int size, IWorldMap map, Vector2d initialPosition){
        this(size, map);
        this.position = initialPosition;
    }

    public Animal(int size, IWorldMap map, Vector2d initialPosition, int energy){
        this(size, map, initialPosition);
        this.energy = energy;
    }

    public boolean isAlive(){ // return true if this animal is still alive
        return energy > 0;
    }

    @Override
    public Vector2d getPosition() {
        return this.position;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public int getEnergy() {
        return energy;
    }

    public void turnBack() {
        switch (this.direction){
            case NORTH -> this.direction = MapDirection.SOUTH;
            case NORTHEAST -> this.direction = MapDirection.SOUTHWEST;
            case NORTHWEST -> this.direction = MapDirection.SOUTHEAST;
            case SOUTH -> this.direction = MapDirection.NORTH;
            case SOUTHEAST -> this.direction = MapDirection.NORTHWEST;
            case SOUTHWEST -> this.direction = MapDirection.NORTHEAST;
        }
    }

    public void changeEnergy(int i) {
        this.energy = this.energy + i;
    }

    public int getAge() {
        return age;
    }

    public int getChildren() {
        return children;
    }

    public void move() {
        int gene = genes.nextGene();
        MapDirection geneDirection = direction.rotate(gene);

        Vector2d toMove = position.add(geneDirection.toUnitVector());

        if (map.canMoveTo(toMove)){
            Vector2d positionToDelete = new Vector2d(position.x, position.y);
            this.position = this.position.add(geneDirection.toUnitVector());
            this.positionChanged(this, positionToDelete, this.position);
        }
    }

    public void positionChanged(Animal animal, Vector2d oldPosition, Vector2d newPosition) {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(animal, oldPosition, newPosition);
        }
    }
    public void changePosition(Vector2d newPosition) {
    }
    public int[] getGenotype() {
        return new int[0];
    }
    public void increaseAge() {
    }
    public void increaseEatenGrasses() {
    }
    public void increaseNumberOfChildren() {
    }

}