package basedatastructure;

import java.util.Iterator;

/**
 * 2-3树实现的符号表
 *      一棵2-3树由以下结点组成
 *      2-结点：含有一个键（和其对应的值）和两条链接，左链接指向的2-3树中的键都小于该结点，右链接指向的2-3树中的键都大于该结点
 *      3-结点：含有两个键（和其对应的值）和三条链接，左链接指向的2-3树种的键都小于该结点，中链接指向的2-3树中的键都位于该结点的两个键
 *      之间，右链接指向的2-3树种的键都大于该结点
 */
public class TwoThreeSearchTreeST<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value>{
    private Node root;
    private int N;

    @Override
    /**
     * 向2-结点插入新键
     *      将2-结点替换为3-结点，将要插入的键保存在其中即可
     * 向3-结点插入新键
     *      先临时将改键插入，使之成为一个4-结点，在将之转化为一棵由3个2-结点组成的2-3树，根结点含有中键，左链接指向最小者，右链接指向最大者。
     *      再将分解后含有中键的结点和原结点的父结点合并，如果依旧为4-结点则递归此过程
     */
    public void put(Key key, Value val) {
        if(root == null) {
            root = new TwoNode(new KeyValue(key, val));
            N++;
        } else {
            Node node = root;
            while(true) {
                KeyValue kv = node.equal(key);
                if(kv != null) {
                    kv.val = val;
                    return;
                }
                Node next = node.find(key);
                if(next == null) {
                    node = updateNode(node, new TwoNode(new KeyValue(key, val)));
                    break;
                } else {
                    node = next;
                }
            }
            Node parent = node.parent;
            if(parent == null) {
                root = node;
            } else {
                parent.resetChild(node);
            }
            N++;
        }
    }

    private Node updateNode(Node node, TwoNode merge) {
        Node update = node.update(merge);
        if(update instanceof TwoThreeSearchTreeST.FourNode) {
            TwoNode n = split((FourNode)update);
            if(update.parent != null) {
                return updateNode(update.parent, n);
            } else {
                return n;
            }
        } else {
            return update;
        }
    }

    private TwoNode split(FourNode node) {
        TwoNode ret   = new TwoNode(node.keyValue2);
        TwoNode left  = new TwoNode(node.keyValue);
        TwoNode right = new TwoNode(node.keyValue3);
        ret.left    = left;
        ret.right   = right;
        left.left   = node.left;
        left.right  = node.midLeft;
        right.left  = node.midRight;
        right.right = node.right;
        ret.resetParent();
        left.resetParent();
        right.resetParent();
        return ret;
    }

    @Override
    public Value get(Key key) {
        Node node = root;
        while(node != null) {
            KeyValue kv = node.equal(key);
            if(kv != null) {
                return kv.val;
            }
            node = node.find(key);
        }
        return null;
    }

    @Override
    public int size() {
        return N;
    }

    private enum SubType {
        LEFT,MID,RIGHT
    }

    private enum ValueType {
        LEFT,RIGHT
    }

    @Override
    public Iterator<Key> keys() {
        return new Iterator<Key>() {
            private EasyQueue<Key> queue = new LinkedQueue<>();

            {
                getKeys(root);
            }

            private void getKeys(Node node) {
                if(node == null) {
                    return;
                }
                getKeys(node.getSub(SubType.LEFT));
                Key key = node.getVal(ValueType.LEFT);
                if(key != null) {
                    queue.enqueue(key);
                }
                getKeys(node.getSub(SubType.MID));
                Key key2 = node.getVal(ValueType.RIGHT);
                if(key2 != null) {
                    queue.enqueue(key2);
                }
                getKeys(node.getSub(SubType.RIGHT));
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

    @Override
    public void delete(Key key) {
        throw new UnsupportedOperationException("delete");
    }

    private class KeyValue{
        private Key key;
        private Value val;

        KeyValue(Key key, Value value) {
            this.key = key;
            this.val = value;
        }
    }

    private abstract class Node{
        Node parent;

        abstract Node update(TwoNode node);

        abstract Node find(Key key);

        abstract KeyValue equal(Key key);

        abstract void resetChild(Node node);

        abstract Key minKey();

        abstract void resetParent();

        abstract Node getSub(SubType subType);

        abstract Key getVal(ValueType valueType);
    }

    private class TwoNode extends Node {
        KeyValue keyValue;
        Node left;
        Node right;

        TwoNode(KeyValue keyValue) {
            this.keyValue = keyValue;
        }

        @Override
        Node update(TwoNode node) {
            int cmp = keyValue.key.compareTo(node.keyValue.key);
            Node ret =  cmp > 0 ? new ThreeNode(node.keyValue, keyValue, node.left,
                                           node.right == null ? this.left : node.right, this.right, this.parent) :
                                  new ThreeNode(keyValue, node.keyValue, this.left,
                                           node.left == null ? this.right : node.left, node.right, this.parent);
            ret.resetParent();
            return ret;
        }

        @Override
        public Node find(Key key) {
            int cmp = keyValue.key.compareTo(key);
            if(cmp > 0) {
                return left;
            } else if (cmp < 0) {
                return right;
            }
            return null;
        }

        @Override
        KeyValue equal(Key key) {
            if(keyValue.key.compareTo(key) == 0) {
                return keyValue;
            }
            return null;
        }

        @Override
        void resetChild(Node node) {
            int cmp = this.keyValue.key.compareTo(node.minKey());
            if(cmp < 0) {
                this.right = node;
            } else {
                this.left = node;
            }
        }

        @Override
        Key minKey() {
            return this.keyValue.key;
        }

        @Override
        void resetParent() {
            if(this.left != null) {
                this.left.parent = this;
            }
            if(this.right != null) {
                this.right.parent = this;
            }
        }

        @Override
        Node getSub(SubType subType) {
            switch (subType) {
                case LEFT:
                    return left;
                case MID:
                    break;
                case RIGHT:
                    return right;
            }
            return null;
        }

        @Override
        Key getVal(ValueType valueType) {
            switch (valueType) {
                case LEFT:
                    return keyValue.key;
            }
            return null;
        }
    }

    private class ThreeNode extends Node {
        KeyValue keyValue;
        KeyValue keyValue2;
        Node left;
        Node mid;
        Node right;

        public ThreeNode(KeyValue keyValue, KeyValue keyValue2, Node left, Node mid, Node right, Node parent) {
            this.keyValue = keyValue;
            this.keyValue2 = keyValue2;
            this.left = left;
            this.mid = mid;
            this.right = right;
            this.parent = parent;
        }

        @Override
        Node update(TwoNode node) {
            int cmp = keyValue.key.compareTo(node.keyValue.key);
            int cmp2 = keyValue2.key.compareTo(node.keyValue.key);
            FourNode ret = new FourNode();
            ret.parent = this.parent;
            if(cmp > 0) {
                ret.keyValue = node.keyValue;
                ret.keyValue2 = this.keyValue;
                ret.keyValue3 = this.keyValue2;
                if(this.left != null) {
                    ret.left = node.left;
                    ret.midLeft = node.right;
                    ret.midRight = this.mid;
                    ret.right = this.right;
                }
            } else if(cmp <= 0 && cmp2 > 0) {
                ret.keyValue = this.keyValue;
                ret.keyValue2 = node.keyValue;
                ret.keyValue3 = this.keyValue2;
                if(this.mid != null) {
                    ret.left = this.left;
                    ret.midLeft = node.left;
                    ret.midRight = node.right;
                    ret.right = this.right;
                }
            } else {
                ret.keyValue = this.keyValue;
                ret.keyValue2 = this.keyValue2;
                ret.keyValue3 = node.keyValue;
                if(this.right != null) {
                    ret.left = this.left;
                    ret.midLeft = this.right;
                    ret.midRight = node.left;
                    ret.right = node.right;
                }
            }
            ret.resetParent();
            return ret;
        }

        @Override
        public Node find(Key key) {
            int cmp = keyValue.key.compareTo(key);
            int cmp2 = keyValue2.key.compareTo(key);
            if(cmp > 0) {
                return left;
            } else if (cmp < 0 && cmp2 > 0) {
                return mid;
            } else if (cmp2 < 0) {
                return right;
            }
            return null;
        }

        @Override
        KeyValue equal(Key key) {
            if(keyValue.key.compareTo(key) == 0) {
                return keyValue;
            }
            if(keyValue2.key.compareTo(key) == 0) {
                return keyValue2;
            }
            return null;
        }

        @Override
        void resetChild(Node node) {
            int cmp = keyValue.key.compareTo(node.minKey());
            int cmp2 = keyValue2.key.compareTo(node.minKey());
            if(cmp > 0) {
                this.left = node;
            } else if (cmp < 0 && cmp2 > 0) {
                this.mid = node;
            } else if (cmp2 < 0) {
                this.right = node;
            }
        }

        @Override
        Key minKey() {
            return this.keyValue.key;
        }

        @Override
        void resetParent() {
            if(this.left != null) {
                this.left.parent = this;
            }
            if(this.mid != null) {
                this.mid.parent = this;
            }
            if(this.right != null) {
                this.right.parent = this;
            }
        }

        @Override
        Node getSub(SubType subType) {
            switch (subType) {
                case LEFT:
                    return left;
                case MID:
                    return mid;
                case RIGHT:
                    return right;
            }
            return null;
        }

        @Override
        Key getVal(ValueType valueType) {
            switch (valueType) {
                case LEFT:
                    return keyValue.key;
                case RIGHT:
                    return keyValue2.key;
            }
            return null;
        }
    }

    private class FourNode extends Node {
        KeyValue keyValue;
        KeyValue keyValue2;
        KeyValue keyValue3;
        Node left;
        Node midLeft;
        Node midRight;
        Node right;

        @Override
        Node update(TwoNode node) {
            return null;
        }

        @Override
        public Node find(Key key) {
            return null;
        }

        @Override
        KeyValue equal(Key key) {
            return null;
        }

        @Override
        void resetChild(Node node) {
        }

        @Override
        Key minKey() {
            return null;
        }

        @Override
        void resetParent() {
            if(this.left != null) {
                this.left.parent = this;
            }
            if(this.midLeft != null) {
                this.midLeft.parent = this;
            }
            if(this.midRight != null) {
                this.midRight.parent = this;
            }
            if(this.right != null) {
                this.right.parent = this;
            }
        }

        @Override
        Node getSub(SubType subType) {
            return null;
        }

        @Override
        Key getVal(ValueType valueType) {
            return null;
        }
    }
}
