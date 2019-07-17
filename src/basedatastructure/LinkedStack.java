package basedatastructure;

/**
 * Linked list implement stack.
 * @author yehaoyu
 */
public class LinkedStack<E> implements EasyStack<E> {

    private Node first;

    @Override
    public void push(E e) {
        Node node = new Node(e);
        Node current = first;
        first = node;
        first.setNext(current);
    }

    @Override
    public E pop() {
        Node current = first;
        if(current != null) {
            Node next = current.getNext();
            first = next;
            Object obj = current.getElement();
            if(obj != null)  {
                return (E)obj;
            } else {
                return null;
            }
        }
        return null;
    }

    private static class Node<E> {
        E element;
        Node next;

        Node(E e) {
            element = e;
        }

        private E getElement() {
            return element;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private void setElement(E e) {
            element = e;
        }

        private Node getNext() {
            return next;
        }
    }
}
