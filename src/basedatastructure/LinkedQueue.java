package basedatastructure;

/**
 * Linked list implement queue.
 * @author yehaoyu
 */
public class LinkedQueue<E> implements EasyQueue<E> {

    private Node<E> first;
    private Node<E> last;

    @Override
    public void enqueue(E e) {
        Node<E> node  = new Node<>(e);
        if(this.first == null) {
            this.first = node;
            this.last  = node;
        } else {
            Node<E> last = this.last;
            this.last = node;
            last.setNext(node);
        }
    }

    @Override
    public E dequeue() {
        Node<E> first = this.first;
        if(first != null) {
            this.first = first.getNext();
            return first.getElement();
        } else {
            this.last = null;
            return null;
        }
    }

    @Override
    public int size() {
        int i = 0;
        Node<E> e = first;
        while(e != null) {
            i++;
            e = first.next;
        }
        return i;
    }

    private class Node<E> {
        E element;
        Node<E> next;

        Node(E e) {
            element = e;
        }

        private E getElement() {
            return element;
        }

        private void setNext(Node<E> next) {
            this.next = next;
        }

        private void setElement(E e) {
            element = e;
        }

        private Node<E> getNext() {
            return next;
        }
    }
}
