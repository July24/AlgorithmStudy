package unionfind;

/**
 * Quick find algorithm.
 * @author yehaoyu
 */
public class QuickFind extends BaseUnionFindAlgorithm{

    public QuickFind() {
        super(DEFAULT_CAPACITY);
    }

    @Override
    public boolean connected(int p, int q) {
        return ids[p] == ids[q];
    }

    @Override
    public void union(int p, int q) {
        if(!connected(p, q)) {
            int pVal = ids[p];
            int qVal = ids[q];
            for(int i = 0; i < ids.length; i++) {
                if(ids[i] == pVal) {
                    ids[i] = qVal;
                }
            }
        }
    }
}
