package sort;

public abstract class BaseQuickSort extends AbstractSortAlgorithm {
    @Override
    public void sort(Comparable[] comp) {
        shuffle(comp);
        sort(comp, 0, comp.length - 1);
    }

    abstract void sort(Comparable[] comp, int low, int high);

    void shuffle(Comparable[] comp) {
        int length = comp.length;
        for(int i = 0; i < length; i++) {
            generateRandom(i);
            exchange(comp, i, generateRandom(i));
        }
    }

    private int generateRandom(int i) {
        return (int)(Math.random() * i);
    }
}
