package unionfind;

/**
 * Updated quick union algorithm.
 * Using weighting and path compress.
 */
public class UpdateQuickUnion extends BaseUnionFindAlgorithm{
    private int[] sz;

    public UpdateQuickUnion() {
        super(DEFAULT_CAPACITY);
        sz = new int[DEFAULT_CAPACITY];
        for(int i = 0; i < DEFAULT_CAPACITY; i++) {
            sz[i] = 1;
        }
    }

    private int root(int i) {
        int root = i;
        while(ids[root] != root) {
            root = ids[root];
        }
        while(root != i) {
            int temp = ids[i];
            ids[i] = root;
            i = temp;
        }
        return root;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if(pRoot != qRoot) {
            if(sz[pRoot] >= sz[qRoot]) {
                ids[qRoot] = pRoot;
                sz[pRoot] += sz[qRoot];
            } else {
                ids[pRoot] = qRoot;
                sz[qRoot] += sz[pRoot];
            }
        }
    }
}
