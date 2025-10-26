package ro.academyplus.avaj.tower;

import java.util.ArrayList;
import java.util.List;
import ro.academyplus.avaj.aircraft.Aircraft;
import ro.academyplus.avaj.aircraft.Flyable;

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