package unionfind;

/**
 * Union find algorithm.
 * @author yehaoyu
 */
public abstract class BaseUnionFindAlgorithm {
    protected int[] ids;

    protected static int DEFAULT_CAPACITY = 10;

    public BaseUnionFindAlgorithm() {
    }

    public BaseUnionFindAlgorithm(int n) {
        ids = new int[n];
        for(int i = 0; i < n; i++) {
            ids[i] = i;
        }
    }

    public abstract boolean connected(int p, int q);

    public abstract  void union(int p, int q);
}
