package basedatastructure;

import sort.CompareOperator;

/**
 * 抽象优先队列
 */
public abstract class AbstractPriorityQueue<E extends Comparable<E>> extends CompareOperator {
    protected QueueType queueType;
    protected E[] elements;
    protected int N = 0;
    protected static int DEFAULT_CAPACITY = 16;
    protected static int SHRINK_FACTOR = 4;

    public abstract void insert(E e);

    public boolean isEmpty() {
        return N == 0;
    }

    public abstract E takeOut();

    protected boolean compare(Comparable a, Comparable b) {
        switch (queueType) {
            case MAX:
                return less(a, b);
            case MIN:
                return greater(a, b);
            default:
                return false;
        }
    }

    protected void adjustArraySize(int arraylength, int copyLength) {
        E[] newArray = (E[])new Comparable[arraylength];
        System.arraycopy(elements, 0, newArray, 0, copyLength);
        elements = newArray;
    }
}
