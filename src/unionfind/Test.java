package unionfind;

/**
 * Test union find algorithm.
 * @author yehaoyu
 */
public class Test {
    public static void main(String[] args) {
        BaseUnionFindAlgorithm unionFindAlgorithm = buildUpdateQuickUnion();
        assignment(unionFindAlgorithm);
        System.out.println(unionFindAlgorithm.connected(2,6));
    }

    private static void assignment(BaseUnionFindAlgorithm unionFind) {
        unionFind.union(1,2);
        unionFind.union(1,3);
        unionFind.union(5,6);
        unionFind.union(6,1);
        unionFind.union(8,9);
    }

    private static BaseUnionFindAlgorithm buildQuickFind() {
        return new QuickFind();
    }

    private static BaseUnionFindAlgorithm buildQuickUnion() {
        return new QuickUnion();
    }

    private static BaseUnionFindAlgorithm buildUpdateQuickUnion() {
        return new UpdateQuickUnion();
    }
}
