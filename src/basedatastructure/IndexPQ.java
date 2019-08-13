package basedatastructure;

import java.util.Arrays;

/**
 * 索引优先队列.
 *  用一个索引数组保存元素在数组中的位置
 *  插入队列中时，可看作将一个整数和一个对象相关联，使得我们可以引用队列中的元素
 * @author 叶皓宇
 */
public class IndexPQ<E extends Comparable<E>> {
    protected QueueType queueType;
    Integer[] qp;
    Integer[] pq;
    E[] elements;
    int N;

    public IndexPQ(int capacity, QueueType type) {
        queueType = type;
        N = 0;
        qp = new Integer[capacity];
        pq = new Integer[capacity];
        Arrays.fill(pq, -1);
        elements = (E[])new Comparable[capacity];
    }

    public boolean contain(int i) {
        return pq[i] > 0;
    }

    public void insert(int i, E e) {
        if(contain(i)) {
            return;
        }
        N++;
        qp[N] = i;
        pq[i] = N;
        elements[i] = e;
        swim(N);
        sink(N);
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public E takeOut() {
        if(isEmpty()) {
            return null;
        }
        exchange(1, N);
        int index = qp[N];
        E e = elements[index];
        elements[index] = null;
        qp[N] = null;
        pq[index] = -1;
        N--;
        sink(1);
        return e;
    }

    public void change(int i, E e) {
        int n = pq[i];
        if(n < 0) {
            return;
        }
        elements[i] = e;
        swim(n);
        sink(n);
    }

    private boolean compare(Comparable a, Comparable b) {
        switch (queueType) {
            case MAX:
                return less(a, b);
            case MIN:
                return greater(a, b);
            default:
                return false;
        }
    }

    private boolean less(Comparable first, Comparable second) {
        return first.compareTo(second) < 0;
    }

    private boolean greater(Comparable first, Comparable second) {
        return first.compareTo(second) > 0;
    }

    private void exchange(int i, int j) {
        int temp = qp[i];
        qp[i] = qp[j];
        qp[j] = temp;
        pq[qp[i]] = i;
        pq[qp[j]] = j;
    }

    private void swim(int i) {
        while (i > 1 && compare(elements[qp[i/2]], elements[qp[i]])) {
            exchange(i/2, i);
            i = i/2;
        }
    }

    private void sink(int i) {
        while(2*i <= N) {
            int sub = 2*i;
            if(sub < N && compare(elements[qp[sub]], elements[qp[sub+1]])) {
                sub++;
            }
            if(!compare(elements[qp[i]], elements[qp[sub]])) {
                break;
            }
            exchange(sub, i);
            i = sub;
        }
    }
}
