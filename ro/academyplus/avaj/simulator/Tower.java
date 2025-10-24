package ro.academyplus.avaj.simulator;

class Tower {
    private final List<Flyable> observers = new Arraylist<>();

    public void register(Flyable p_flyable) {
        observers.add(p_flyable);
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
    }

    protected void conditionChanged() {

    }
}