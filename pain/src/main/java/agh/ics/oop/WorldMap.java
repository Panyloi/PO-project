package agh.ics.oop;

import java.util.*;

public class WorldMap implements IWorldMap, IPositionChangeObserver{
    private final int width;
    private final int height;
    private final int mapVariant;
    private final int startGrass;
    private final int grassProfit;
    private final int dailyGrass;
    private final int requiredEnergy;
    private final int reproductionCost;
    private final int minMutation;
    private final int maxMutation;
    private final int mutationVariant;
    private final int genomLength;
    private final Vector2d lowerLeft = new Vector2d(0, 0);
    private final Vector2d upperRight;
    private final AnimalComparator comparator = new AnimalComparator();
    private final Map<Vector2d, TreeSet<Animal>> animalsMap = new HashMap<>();
    private final LinkedList<Animal> animals = new LinkedList<>();
    private final LinkedList<Grass> grasses = new LinkedList<>();
    private final TreeSet<Vector2d> deathMap = new TreeSet<>();


    public WorldMap(int width, int height, int mapVariant, int startGrass, int grassProfit, int dailyGrass, int requiredEnergy, int reproductionCost, int minMutation, int maxMutation, int mutationVariant, int genomLength){
        this.width = width;
        this.height = height;
        this.mapVariant =  mapVariant;
        this.startGrass = startGrass;
        this.grassProfit =  grassProfit;
        this.dailyGrass = dailyGrass;
        this.requiredEnergy = requiredEnergy;
        this.reproductionCost = reproductionCost;
        this.minMutation = minMutation;
        this.maxMutation = maxMutation;
        this.mutationVariant = mutationVariant;
        this.genomLength = genomLength;
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(this.lowerLeft) && position.precedes(this.upperRight);
    }

    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (canMoveTo(position)) {
            animals.add(animal);
            TreeSet<Animal> treeSet = animalsMap.computeIfAbsent(position, k -> new TreeSet<>(comparator));
            treeSet.add(animal);
            animal.addObserver(this);
            return true;
        }
        return false;
    }

    @Override
    public void removeDeadAnimals() {
        for (Animal animal: animals){
            if (animal.getEnergy() <= 0) {
                animals.remove(animal);
                animalsMap.get(animal.getPosition()).remove(animal);
            }
        }
    }

    @Override
    public void moveAnimals() {
        for (Animal animal: animals){
            animal.move();
        }
    }

    @Override
    public void eatGrass() {
        for (Grass grass: grasses){
            Vector2d position = grass.getPosition();
            TreeSet<Animal> treeSet = animalsMap.get(position);
            if (treeSet != null){
                treeSet.first().changeEnergy(grassProfit);
                grasses.remove(grass);
            }
        }
    }

    @Override
    public void reproduce() {

    }

    @Override
    public void spawnGrass() {

    }

    @Override
    public Vector2d positionChanged(Animal animal, Vector2d oldPosition, Vector2d potentialPosition) {
        Vector2d newPosition;
        if (canMoveTo(potentialPosition)) {
            newPosition = potentialPosition;
        } else if (mapVariant == 0) {
            if (potentialPosition.y > upperRight.y || potentialPosition.y < lowerLeft.y) {
                animal.turnBack();
                newPosition = oldPosition;
            } else {
                newPosition = new Vector2d(potentialPosition.x % width, potentialPosition.y);
            }
        } else {
            animal.changeEnergy(-reproductionCost);
            Random generator = new Random();
            int x = generator.nextInt(width);
            int y = generator.nextInt(height);
            newPosition = new Vector2d(x, y);
        }

        animalsMap.get(oldPosition).remove(animal);
        animalsMap.get(newPosition).add(animal);
        return newPosition;
    }
}
