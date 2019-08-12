package basedatastructure;

import java.util.Iterator;

/**
 * 基于链表的符号表
 * @author 叶皓宇
 */
public class LinkedListST<Key, Value> extends AbstractSymbolTable<Key, Value> {
    private Node first;

    @Override
    public void put(Key key, Value val) {
        Node node = find(key);
        if(node == null) {
            Node newNode  = new Node(key, val);
            Node oldFirst = first;
            if(oldFirst != null) {
                oldFirst.setPrev(newNode);
                newNode.setNext(oldFirst);
            }
            first = newNode;
        } else {
            node.setVal(val);
        }
    }

    @Override
    public Value get(Key key) {
        Node node = find(key);
        return node == null ? null : node.getValue();
    }

    private Node find(Key key) {
        Node node = first;
        while(node != null) {
            if(node.getKey().equals(key)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    @Override
    public void delete(Key key) {
        Node node = find(key);
        if(node == null) {
            return;
        }
        Node next = node.getNext();
        if(first == node) {
            next.setPrev(null);
            first = next;
        } else {
            Node prev = node.getPrev();
            prev.setNext(next);
            next.setPrev(prev);
        }
    }

    @Override
    public boolean contains(Key key) {
        return find(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        int i = 0;
        Node node = first;
        while(node != null) {
            i++;
            node = node.getNext();
        }
        return i;
    }

    @Override
    public Iterator<Key> keys() {
        return new Iterator<Key>() {
            private Node node = first;
            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Key next() {
                if(node != null) {
                    Key ret = node.getKey();
                    node = node.getNext();
                    return ret;
                }
                return null;
            }
        };
    }

    private class Node {
        Key key;
        Value val;
        Node prev;
        Node next;

        Node(Key k, Value v) {
            key = k;
            val = v;
        }

        private void setVal(Value val) {
            this.val = val;
        }

        private Key getKey() {
            return key;
        }

        private Value getValue() {
            return val;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private Node getNext() {
            return next;
        }


        private void setPrev(Node prev) {
            this.prev = prev;
        }

        private Node getPrev() {
            return prev;
        }
    }
}
