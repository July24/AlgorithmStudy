package basedatastructure;

/**
 * Queue interface.
 */
public interface EasyQueue<E> {
    public void enqueue(E e);

    public E dequeue();

    public int size();
}
