package agh.ics.oop;

import java.util.Random;

public class Genes {
    public int[] genes;
    private int iterator;
    Random rnd;
    public Genes(int size){
        this.genes = new int[size];
        this.iterator = 0;
        this.rnd = new Random();

        for(int i = 0; i<size; i++){
            int genotype = this.randomGene();
            this.genes[i] = genotype;
        }

    }

    public int getGene(){
        return genes[iterator];
    }

    public int nextGene(){
        iterator = (iterator + 1) % 8;
        return genes[iterator];
    }

    public int randomGene(){
        return rnd.nextInt(8);
    }

}
