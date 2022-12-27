package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements Runnable{

    private final WorldMap map;
    private final int delay;
    private final int dailyGrass;
    private final List<IRefreshObserver> observers = new ArrayList<>();
    private boolean stopped = false;

    public SimulationEngine(WorldMap map, int delay, int startGrass, int dailyGrass, int startAnimals, int startEnergy, int genomeLength) {
        this.map = map;
        this.delay = delay;
        this.dailyGrass = dailyGrass;
        this.map.spawnGrass(startGrass);
        this.map.spawnAnimals(startAnimals, startEnergy, genomeLength);
    }

    @Override
    public void run() {
        stopped = false;
        while (!stopped) {
            try{
                Thread.sleep(delay);
            }
            catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }

            map.removeDeadAnimals();
            map.moveAnimals();
            map.eatGrass();
            map.reproduce();
            map.spawnGrass(dailyGrass);
            map.nextDay();
            notifyObservers();
        }
    }

    public void stop(){
        stopped = true;
    }

    public void addObserver(IRefreshObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IRefreshObserver observer){
        observers.remove(observer);
    }

    private void notifyObservers(){
        for (IRefreshObserver observer : this.observers) {
            observer.refresh();
        }
    }
}
