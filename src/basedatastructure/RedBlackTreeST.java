package basedatastructure;

import java.util.Iterator;

/**
 * 红黑二叉树实现的符号表.
 *      红黑树背后的思想是用标准的二叉查找树加上一些额外的信息（通过标识结点的颜色）来表示2-3树
 *      等价定义：
 *          1.红链接均为左链接
 *          2.没有任何一个结点同时和两条红链接相连
 *          3.该树是完美黑色平衡的，既任意空链接到根链接的路径上黑链接的数量相同
 */
public class RedBlackTreeST<Key extends Comparable<Key>, Value> extends AbstractKeyOrderedSymbolTable<Key, Value> {
    private Node root;

    @Override
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.red = false;
    }

    /**
     * 在沿着插入点到根节点的路径向上移动时，依次完成以下操作
     *      1.如果右子结点是红色而左子结点是黑色，进行左旋转
     *          如果不判断左子结点是黑色的话，当左右都为红结点时，1、2步为冗余操作
     *      2.如果左子结点是红色且它的左子结点还是是红色，进行右旋转
     *      3.如果左右子结点都是红色，进行颜色转换
     */
    private Node put(Node node, Key key, Value val) {
        if(node == null) {
            return new Node(key, val, 1, true);
        }
        int cmp = node.key.compareTo(key);
        if(cmp > 0) {
            node.left = put(node.left, key, val);
        } else if (cmp < 0) {
            node.right = put(node.right, key, val);
        } else {
            node.val = val;
        }
        if(isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)) {
            flipColor(node);
        }
        node.N = size(node.right) + size(node.left) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        Node node = get(root, key);
        return node != null ? node.val : null;
    }

    private Node get(Node node, Key key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp > 0) {
            return get(node.left, key);
        } else if (cmp < 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public void deleteMin() {
        if(isEmpty()) {
            return;
        }
        root = deleteMin(root);
        if(!isEmpty()) {
            root.red = false;
        }
    }

    /**
     * 保证当前node.left不为2-结点，循环到最后，最小值也不为2-结点，此时直接删除即可, 然后依次把经过的结点复原
     */
    private Node deleteMin(Node node) {
        if(node.left == null) {
            return null;
        }
        if(!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    /**
     * 修复node结点为左倾红黑树
     */
    private Node fixUp(Node node) {
        if(isRed(node.right)) {
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if(isRed(node.right) && isRed(node.left)) {
            flipColor(node);
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 保证node.left不是2-结点
     *      分为两种情况
     *          当node.left的兄弟结点node.right是黑结点时，则直接用flipcolor 借用父结点node，加上node.left,node.right合成一个4-结点
     *          当node.left的兄弟结点node.right是红结点时，则从node.right兄弟结点处的借用一个最小值补上父结点，用原父结点和node.left合成一个3-结点
     */
    private Node moveRedLeft(Node node) {
        flipColor(node);
        if(isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColor(node);
        }
        return node;
    }

    @Override
    public void deleteMax() {
        if(isEmpty()) {
            return;
        }
        root = deleteMax(root);
        if(!isEmpty()) {
            root.red = false;
        }
    }

    /**
     * 首先如果左旋则变为右旋，因为要将最大值置于最右边便于删除
     * 其余参照删除最小值
     * @see basedatastructure.RedBlackTreeST#deleteMin(Node)
     */
    private Node deleteMax(Node node) {
        if(isRed(node.left)) {
            node = rotateRight(node);
        }
        if(node.right == null) {
            return null;
        }
        if(!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }
        node.right = deleteMax(node.right);
        return fixUp(node);
    }

    /**
     * @see basedatastructure.RedBlackTreeST#moveRedLeft(Node)
     */
    private Node moveRedRight(Node node) {
        flipColor(node);
        if(isRed(node.left.left)) {
            node = rotateRight(node);
            flipColor(node);
        }
        return node;
    }

    @Override
    /**
     * 找结点的过程中，保证当前结点不是2-结点（为了删除所选的key在底部的情况）
     * 找到结点后，把结点和左子树的最大结点或者右子树的最小结点交换，然后在删除左子树的最大值或者右子树的最小值即可
     */
    public void delete(Key key) {
        root = delete(root, key);
        if(!isEmpty()) {
            root.red = false;
        }
    }

    private Node delete(Node h, Key key)
    {
        if (key.compareTo(h.key) < 0)
        {
            if (!isRed(h.left) && !isRed(h.left.left)) {
                h = moveRedLeft(h);
            }
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left)) {
                h = rotateRight(h);
            }
            if (key.compareTo(h.key) == 0 && (h.right == null)) {
                return null;
            }
            if (!isRed(h.right) && !isRed(h.right.left)) {
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key) == 0)
            {
                h.key = min(h.right).key;
                h.val = get(h.right, h.key).val;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return fixUp(h);
    }

    @Override
    public Key min() {
        if(isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    public Node min(Node node) {
        if(node.left == null) {
            return node;
        } else  {
            return min(node.left);
        }
    }

    @Override
    public Key max() {
        if(isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    private Node max(Node node) {
        if(node.right == null) {
            return node;
        } else  {
            return max(node.right);
        }
    }

    @Override
    public Key floor(Key key) {
        Node node = floor(root, key);
        if(node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private Node floor(Node node, Key key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp > 0) {
            return floor(node.left, key);
        } else if(cmp == 0) {
            return node;
        } else {
            Node t = floor(node.right, key);
            if(t == null) {
                return node;
            } else {
                return t;
            }
        }
    }

    @Override
    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if(node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private Node ceiling(Node node, Key key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp < 0) {
            return ceiling(node.right, key);
        } else if(cmp == 0) {
            return node;
        } else {
            Node t = ceiling(node.left, key);
            if(t == null) {
                return node;
            } else {
                return t;
            }
        }
    }

    @Override
    public int rank(Key key) {
        if(!contains(key)) {
            return -1;
        }
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }
        int cmp = node.key.compareTo(key);
        if(cmp > 0) {
            return rank(node.left, key);
        } else if(cmp < 0) {
            return size(node.left) + 1 + rank(node.right, key);
        } else {
            return size(node.left);
        }
    }

    private int size(Node node) {
        if(node == null) {
            return 0;
        }
        return node.N;
    }

    @Override
    public Key select(int i) {
        Node node = select(root, i);
        if(node == null) {
            return null;
        }
        return node.key;
    }

    private Node select(Node node, int i) {
        if(node == null) {
            return null;
        }
        int n = size(node.left);
        if(n > i) {
            return select(node.left, i);
        } else if(n < i) {
            return select(node.right, i - n - 1);
        } else {
            return node;
        }
    }

    @Override
    public Iterator<Key> keys(Key low, Key high) {
        return new Iterator<Key>() {
            EasyQueue<Key> queue = new ResizingArrayQueue<>();

            {
                keys(root, low, high);
            }

            private void keys(Node node, Key low, Key high) {
                if(node == null) {
                    return;
                }
                int cmp1 = node.key.compareTo(low);
                int cmp2 = node.key.compareTo(high);
                if(cmp1 > 0) {
                    keys(node.left, low, high);
                }
                if(cmp1 >= 0 && cmp2 <= 0) {
                    queue.enqueue(node.key);
                }
                if(cmp2 < 0) {
                    keys(node.right, low, high);
                }
            }

            @Override
            public boolean hasNext() {
                return queue.size() > 0;
            }

            @Override
            public Key next() {
                return queue.dequeue();
            }
        };
    }

    @Override
    public Iterator<Key> keys() {
        return new Iterator<Key>() {
            EasyQueue<Key> queue = new LinkedQueue<>();
            {
                keys(root);
            }

            private void keys(Node node) {
                if(node == null) {
                    return;
                }
                keys(node.left);
                queue.enqueue(node.key);
                keys(node.right);
            }

            @Override
            public boolean hasNext() {
                return queue.size() > 0;
            }

            @Override
            public Key next() {
                return queue.dequeue();
            }
        };
    }

    private Node rotateLeft(Node node) {
        Node rSub  = node.right;
        node.right = rSub.left;
        rSub.left  = node;
        rSub.red   = node.red;
        node.red   = true;
        rSub.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;

        return rSub;
    }

    private Node rotateRight(Node node) {
        Node lSub  = node.left;
        node.left  = lSub.right;
        lSub.right = node;
        lSub.red   = node.red;
        node.red   = true;
        lSub.N = node.N;
        node.N = size(node.left) + size(node.right) + 1;

        return lSub;
    }

    /**
     * 4-结点分解为3个2-结点或者从父结点接一个元素加上两个2-子结点合成一个4-结点
     * @param node
     */
    private void flipColor(Node node) {
        node.red = !node.red;
        node.left.red = !node.left.red;
        node.right.red = !node.right.red;
    }

    private boolean isRed(Node node) {
        if(node == null) {
            return false;
        }
        return node.red;
    }

    private class Node {
        private Key key;
        private Value val;
        private boolean red;
        private Node left;
        private Node right;
        private int N;


        public Node(Key key, Value val, int n, boolean red) {
            this.key = key;
            this.val = val;
            this.N   = n;
            this.red = red;
        }
    }
}
