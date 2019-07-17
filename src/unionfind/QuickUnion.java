package unionfind;

/**
 * Quick union algorithm.
 * @author yehaoyu
 */
public class QuickUnion extends BaseUnionFindAlgorithm {

    public QuickUnion() {
        super(DEFAULT_CAPACITY);
    }

    private int root(int i) {
        while(ids[i] != i) {
            i = ids[i];
        }
        return i;
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
            ids[pRoot] = qRoot;
        }
    }
}
