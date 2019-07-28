package sort;

/**
 * 自下而上的归并排序
 * 从长度为1的数组开始合并，每次增长2倍
 * @author 叶皓宇
 */
public class BottomUpMergeImp extends BaseMergeSort {
    @Override
    public void sort(Comparable[] comp) {
        int length = comp.length;
        aux = new Comparable[length];
        for(int sz = 1; sz < length; sz += sz) {
            for(int low = 0; low < length - sz; low += sz + sz) {
                merge(comp, low, low + sz - 1, Math.min(low + sz + sz - 1, length - 1));
            }
        }
    }
}
