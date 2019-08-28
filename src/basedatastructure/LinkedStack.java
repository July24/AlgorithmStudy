package basedatastructure;

/**
 * Linked list implement stack.
 */
public class LinkedStack<E> implements EasyStack<E> {

    private Node<E> first;

    @Override
    public void push(E e) {
        Node<E> node = new Node<>(e);
        Node<E> current = first;
        first = node;
        first.setNext(current);
    }

    @Override
    public E pop() {
        Node<E> current = first;
        if(current != null) {
            Node<E> next = current.getNext();
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
