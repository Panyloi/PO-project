package agh.ics.oop;
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

    // to fill constructors
    public Animal(Animal strongerParent, Animal weakerParent, int energy, int minMutation, int maxMutation, int mutationVariant) {
    }

    public Animal(Vector2d position, int startEnergy, int genomeLength) {
        this.direction = MapDirection.NORTH;
        this.lengthOfGenes = genomeLength;
        this.genes = new Genes(genomeLength);
        this.position = position;
        this.age = 0;
        this.children = 0;
        this.energy = startEnergy;
    }


    public Animal(Vector2d position, int startEnergy, int genomeLength, IWorldMap map){
        this(position, startEnergy, genomeLength);
        this.map = map;
    }

    public boolean isDead(){ // return true if this animal is still alive
        return energy <= 0;
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
        this.direction = this.direction.rotate(4);
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
        this.position = newPosition;
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