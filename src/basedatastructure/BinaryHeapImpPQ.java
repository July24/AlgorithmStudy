package basedatastructure;

/**
 * 二叉堆实现的优先队列
 *      数组0位不用，从1位开始
 *      数组中的第i个元素，2i，2i+1为他的子元素，i/2为他的父元素
 */
public class BinaryHeapImpPQ <E extends Comparable<E>> extends AbstractPriorityQueue<E> {
    public BinaryHeapImpPQ(QueueType queueType) {
        this.queueType = queueType;
        elements = (E[])new Comparable[DEFAULT_CAPACITY];
    }

    @Override
    public void insert(E e) {
        if(N+1 >= elements.length) {
            adjustArraySize((N+1)*2, N + 1);
        }
        elements[++N] = e;
        swim(N);
    }

    @Override
    public E takeOut() {
        if(isEmpty()) {
            return null;
        }
        E out = elements[1];
        exchange(elements, 1, N);
        elements[N--] = null;
        sink(1);
        int length = elements.length;
        if(length > DEFAULT_CAPACITY && N+1 <= length/SHRINK_FACTOR) {
            adjustArraySize(length / 2, N + 1);
        }
        return out;
    }

    private void swim(int i) {
        while (i > 1 && compare(elements[i/2], elements[i])) {
            exchange(elements, i/2, i);
            i = i/2;
        }
    }

    private void sink(int i) {
        while(2*i <= N) {
            int sub = 2*i;
            if(sub < N && compare(elements[sub], elements[sub+1])) {
                sub++;
            }
            if(!compare(elements[i], elements[sub])) {
                break;
            }
            exchange(elements, sub, i);
            i = sub;
        }
    }
}
