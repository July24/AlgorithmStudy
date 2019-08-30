package basedatastructure;
import java.util.Iterator;

/**
 * hashcode+单向链表的数组实现的符号表
 * 当Hashcode冲突时，使用单向列表解决
 * 此为JDK1.7之前系统HashMap的实现方法
 */
public class SeparateChainingST<Key, Value> extends AbstractSymbolTable<Key,Value> {
    private int capacity;
    private Node[] nodes;
    private int size;

    public SeparateChainingST(int capacity) {
        this.capacity = capacity/5;
        nodes = new Node[this.capacity];
        size = 0;
    }

    public SeparateChainingST() {
        this(15);
    }

    @Override
    public void put(Key key, Value val) {
        int n = hash(key);
        Node node = nodes[n];
        while(node != null) {
            if(node.key.equals(key)) {
               node.val = val;
               return;
            }
            node = node.next;
        }
        nodes[n] = new Node(key, val, nodes[n]);
        size++;
    }

    /**
     * 求key所在数组中的位置，使用Math.abs的话当key的hashcode为Interger.MIN_VALUE时会出现BUG，
     * 所以使用hashcode & 0x7fffffff 取低7位后求余数
     */
    private int hash(Key key) {
        int code = key.hashCode();
        return (code & 0x7fffffff) % this.capacity;
    }

    @Override
    public Value get(Key key) {
        int n = hash(key);
        Node node = nodes[n];
        while(node != null) {
            if(node.key.equals(key)) {
                return (Value) node.val;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> keys() {
        return new Iterator<Key>() {
            EasyQueue<Key> queue = new LinkedQueue<>();

            {
                init();
            }

            private void init() {
                for(Node n : nodes) {
                    for(Node first = n; n!=null; n = n.next) {
                        queue.enqueue((Key) n.key);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                return queue.size() != 0;
            }

            @Override
            public Key next() {
                return queue.dequeue();
            }
        };
    }

    private static class Node {
        private Object key;
        private Object val;
        private Node next;

        public Node(Object key, Object val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
