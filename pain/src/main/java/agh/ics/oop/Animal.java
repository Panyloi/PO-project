package agh.ics.oop;
import java.util.ArrayList;
import java.util.Random;

public class Animal implements IMapElement{
    private IWorldMap map;
    private int energy;
    public MapDirection direction;
    public ArrayList<IPositionChangeObserver> observers = new ArrayList<>();
    private Genes genes;
    private final int lengthOfGenes;
    private Vector2d position;
    private int age;
    private int children;
    private int eatenGrass;
    Random rnd = new Random();

    // losowy kierunek
    //
    public Animal(Animal strongerParent, Animal weakerParent, int energy, int minMutation, int maxMutation, int mutationVariant) {
        this.direction = MapDirection.NORTH.rotate(rnd.nextInt(8));
        this.position = strongerParent.getPosition();
        this.age = 0;
        this.children = 0;
        this.lengthOfGenes = strongerParent.getLengthOfGenes();
        this.eatenGrass = 0;
        this.energy = energy;

        int proportion = this.proportion(strongerParent.getEnergy(), weakerParent.getEnergy(), strongerParent.getLengthOfGenes());
        int side = rnd.nextInt(2);

        this.genes = new Genes(strongerParent.getGenotype(), weakerParent.getGenotype(), side, lengthOfGenes, proportion);
        if (mutationVariant == 0) this.genes.fullRandomness(minMutation, maxMutation);
        else this.genes.slightCorrection(minMutation, maxMutation);
    }

    public Animal(Vector2d position, int startEnergy, int genomeLength) {
        this.direction = MapDirection.NORTH.rotate(rnd.nextInt(8));
        this.lengthOfGenes = genomeLength;
        this.genes = new Genes(genomeLength);
        this.position = position;
        this.age = 0;
        this.children = 0;
        this.energy = startEnergy;
        this.eatenGrass = 0;
    }


    public Animal(Vector2d position, int startEnergy, int genomeLength, IWorldMap map){
        this(position, startEnergy, genomeLength);
        this.map = map;
    }

    public boolean isDead(){ // return true if this animal is dead
        return energy <= 0;
    }
    public int getLengthOfGenes(){
        return this.lengthOfGenes;
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
        return genes.getGenes();
    }
    public void increaseAge() {
        this.age += 1;
    }
    public void increaseEatenGrasses() {
        this.eatenGrass += 1;
    }
    public void increaseNumberOfChildren() {
        this.children += 1;
    }
    public int proportion(int energy1, int energy2, int length){
        return (int) (energy1 / (energy1 + energy2)) * length;
    }
}