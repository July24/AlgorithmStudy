package basedatastructure;

import java.util.Iterator;

/**
 * 二叉搜索树实现的符号表
 * @author 叶皓宇
 */
public class BinarySearchTreeST<Key extends Comparable<Key>, Value> extends AbstractKeyOrderedSymbolTable<Key, Value> {
    private Node root;

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

    public Node max(Node node) {
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

    /**
     * 如果给定的键Key小于二叉查找树的根结点的键，那么小于等于Key的最大键floor（key）一定在根结点的左子树中；
     * 如果给定的键key大于二叉查找树的根节点，那么只有当根节点右子树中存在小于等于key的结点时，小于等于key的最大键才会出现在右子树中，
     * 否则根节点就是小于等于key的最大键。
     */
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

    /**
     * 将floor方法"左"变为"右"，小于变为大于
     * @see basedatastructure.BinarySearchTreeST#floor(Node, Key)
     */
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

    /**
     * 返回给定键的排名。如果给定的键和根节点的键相等，我们返回左子树中的结点总数t
     * 如果给定的键小于根节点，我们会返回该键在左子树中的排名
     * 如果给定的键大于根节点，我们会返回t+1+它在右子树中的排名
     */
    public int rank(Node node, Key key) {
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

    @Override
    public Key select(int i) {
        Node node = select(root, i);
        if(node == null) {
            return null;
        }
        return node.key;
    }

    /**
     * 想找到排名为k的键（树中正好有k个小于它的键）。如果左子树中的节点数t大于k，那么就继续（递归的）在左子树中查找排名为k的键；
     * 如果t等于k，返回根节点的键；
     * 如果t小于k，我们就（递归的）在右子树中查找排名为（k-t-1）的键。
     */
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
    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value value) {
        if(node == null) {
            return new Node(key, value, 1);
        }
        int cmp = node.key.compareTo(key);
        if(cmp < 0) {
            node.right = put(node.right, key, value);
        } else if (cmp > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public Value get(Key key) {
        Node node = get(root, key);
        if(node == null) {
            return null;
        } else {
            return node.value;
        }
    }

    private Node get(Node node, Key key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp < 0) {
            return get(node.right, key);
        } else if (cmp > 0) {
            return get(node.left, key);
        } else {
            return node;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if(node == null) {
            return 0;
        }
        return node.N;
    }

    @Override
    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 不断深入根节点的左子树直至遇到一个空连接，然后将指向该结点的链接指向该结点的右子树（递归中返回她的右子树）
     */
    private Node deleteMin(Node node) {
        if(node.left == null) {
            return node.right;
        } else {
            node.left = deleteMin(node.left);
            node.N = size(node.left) + size(node.right) + 1;
            return node;
        }
    }

    @Override
    public void deleteMax() {
        root = deleteMax(root);
    }

    /**
     * @see basedatastructure.BinarySearchTreeST#deleteMin(Node)
     */
    private Node deleteMax(Node node) {
        if(node.right == null) {
            return node.left;
        } else {
            node.right = deleteMax(node.right);
            node.N = size(node.left) + size(node.right) + 1;
            return node;
        }
    }

    @Override
    public void delete(Key key) {
        if(!contains(key)) {
            return;
        }
        root = delete(root, key);
    }

    /**
     * 在删除结点node后用它的后继节点填补它的位置，因为node有一个右子节点，因此它的后继结点就是其右子树中的最小结点，这样的替换能保证树的有序性
     * 删除没有子节点的结点，将指向它的链接置为null
     * 删除只有一个子节点的结点，将该子节点代替该结点
     * 删除右两个子节点的结点
     *      将指向即将被删除的结点的链接保存为t
     *      将该结点指向它的后继结点min（t.right）
     *      将该结点的右链接指向deleteMin（t.right）
     *      将该节点的左链接设为t.left
     */
    public Node delete(Node node, Key key) {
        if(node == null) {
            return null;
        }
        int cmp = node.key.compareTo(key);
        if(cmp < 0) {
            node.right = delete(node.right, key);
        } else if (cmp > 0) {
            node.left = delete(node.left, key);
        } else {
            if(node.right == null) {
                return node.left;
            }
            if(node.left == null) {
                return node.right;
            }
            Node t = node;
            node = min(t.right);
            node.right = deleteMin(t.right);
            node.left = t.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 前序遍历：根、左子树、右子树
     * 中序遍历：左子树、、根右子树
     * 后序遍历：左子树、右子树、根
     * 此处采用中序遍历
     */
    public void print() {
        print(root);
    }

    private void print(Node node) {
        if(node == null) {
            return;
        }
        print(node.left);
        System.out.println("key: " + node.key + "  value: " + node.value);
        print(node.right);
    }

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.N = size;
        }
    }
}
