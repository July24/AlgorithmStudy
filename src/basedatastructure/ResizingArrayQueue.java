package basedatastructure;

/**
 * Resizing array implement queue.
 */
public class ResizingArrayQueue<E> implements EasyQueue<E>{
    private E[] elements;
    private int headPtr;
    private int tailPtr;

    private static int SHRINK_FACTOR = 4;

    @SuppressWarnings("unchecked")
    public ResizingArrayQueue() {
        this.elements = (E[]) new Object[1];
        this.headPtr  = 0;
        this.tailPtr  = 0;
    }

    @Override
    public void enqueue(E e) {
        if(tailPtr >= elements.length) {
            expandArray(2 * elements.length);
        }
        elements[tailPtr++] = e;
    }

    @Override
    public E dequeue() {
        int length = elements.length;
        if(headPtr > length/SHRINK_FACTOR) {
            arrangeArray(headPtr, tailPtr);
        }
        if(tailPtr < length/SHRINK_FACTOR) {
            reduceArray(length/2);
        }
        if(headPtr < tailPtr) {
            E e = elements[headPtr];
            elements[headPtr++] = null;
            return e;
        } else {
            return null;
        }
    }

    @Override
    public int size() {
        return tailPtr - headPtr;
    }

    private void arrangeArray(int bgn, int end) {
        for(int i = bgn, j = 0; i < end; i++,j++) {
            elements[j] = elements[i];
            elements[i] = null;
        }
        headPtr = 0;
        tailPtr = end - bgn;
    }

    private void reduceArray(int capacity) {
        resize(capacity, capacity);
    }

    private void expandArray(int capacity) {
        resize(capacity, elements.length);
    }

    private void resize(int capacity, int length) {
        @SuppressWarnings({"unchecked"})
        E[] copy = (E[])new Object[capacity];
        for(int i = 0; i < length; i++) {
            copy[i] = elements[i];
        }
        elements = copy;
    }
}
