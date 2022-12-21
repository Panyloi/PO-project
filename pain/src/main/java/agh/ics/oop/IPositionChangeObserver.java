package agh.ics.oop;

public interface IPositionChangeObserver {
    Vector2d positionChanged(Animal animal, Vector2d oldPosition, Vector2d potentialPosition);
}
