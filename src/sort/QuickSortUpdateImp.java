package sort;

import util.SortUtil;

/**
 * 快速排序优化版
 * 1.小数组采用插入排序，不再递归
 * 2.切分元素的选择采用三取样切分
 *      比较数组的low，high，（low+high）/2取其中位数放到low当作切分元素
 * @author 叶皓宇
 */
public class QuickSortUpdateImp extends QuickSort {
    private static int CUTOFF = 10;
    @Override
    void sort(Comparable[] comp, int low, int high) {
        if(high - low <= CUTOFF) {
            SortUtil.useInsertionSort();
            SortUtil.sortSelfPart(comp, low, high);
            return;
        }
        int m = median3(comp, low, (low + high)/2, high);
        exchange(comp, low, m);
        int partition = partition(comp, low, high);
        sort(comp, low, partition - 1);
        sort(comp, partition + 1, high);
    }

    private int median3(Comparable[] comp, int i, int j, int k) {
        return less(comp[i], comp[j]) ? (less(comp[j], comp[k]) ? j : less(comp[i], comp[k]) ? i : k ) :
                                 less(comp[i], comp[k]) ? i : less(comp[j], comp[k]) ? j : k;

    }
}
