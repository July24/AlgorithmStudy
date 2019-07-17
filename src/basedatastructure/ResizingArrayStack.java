package basedatastructure;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Resizing array implement Stack.
 * @author yehaoyu
 */
public class ResizingArrayStack<E> implements EasyStack<E>{

    private E[] elements;
    private int pointer;

    private static int SHRINK_FACTOR = 4;

    @SuppressWarnings("unchecked")
    public ResizingArrayStack() {
        elements = (E[])new Object[1];
        pointer = 0;
    }

    @Override
    public void push(E e) {
        if(pointer == elements.length) {
            expandArray(2 * elements.length);
        }
        elements[pointer++] = e;
    }

    @Override
    public E pop() {
        if(pointer == 0) {
            return elements[0];
        } else {
            E item = elements[--pointer];
            elements[pointer] = null;
            int length = elements.length;
            if(pointer == length/SHRINK_FACTOR) {
                reduceArray(length/2);
            }
            return item;
        }
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
