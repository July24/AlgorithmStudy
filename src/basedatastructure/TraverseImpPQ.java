package basedatastructure;

import sort.CompareOperator;

/**
 * 基于遍历实现的优先队列.
 * @author 叶皓宇
 */
public class TraverseImpPQ<E extends Comparable<E>> extends AbstractPriorityQueue<E> {


    public TraverseImpPQ(QueueType queueType) {
        this.queueType = queueType;
        elements = (E[])new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E e) {
        if(N >= elements.length) {
            adjustArraySize(N*2, N);
        }
        elements[N++] = e;
    }

    @Override
    public E takeOut() {
        if(isEmpty()) {
            return null;
        }
        for(int i = 0; i < N - 1; i++) {
            if(compare(elements[N - 1], elements[i])) {
                exchange(elements, i, N - 1);
            }
        }
        E e = elements[N - 1];
        N--;
        int length = elements.length;
        if(length > DEFAULT_CAPACITY && N <= length/SHRINK_FACTOR) {
            adjustArraySize(length / 2, N);
        }
        return e;
    }
}
