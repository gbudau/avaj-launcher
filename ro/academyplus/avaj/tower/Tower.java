package ro.academyplus.avaj.tower;

import ro.academyplus.avaj.aircraft.Flyable;

import java.util.ArrayList;
import java.util.List;

class Tower {
    private final List<Flyable> observers = new ArrayList<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionChanged() {
        for (Flyable flyable : new ArrayList<>(observers)) {
            flyable.updateConditions();
        }
    }
}