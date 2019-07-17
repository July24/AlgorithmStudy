package basedatastructure;

/**
 * Queue interface.
 * @author yehaoyu
 */
public interface EasyQueue<E> {
    public void enqueue(E e);

    public E dequeue();
}
