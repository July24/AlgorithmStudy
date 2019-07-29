package sort;

import Util.SortUtil;

/**
 * 自上而下的归并排序优化算法。
 * 1.剩余数组长度小于7时，采用插入排序
 * 2.排序过程中,每次迭代排序交换原数组和辅助数组的身份，减少从原数组复制到辅助数组的过程
 */
public class MergeUpdateImp extends BaseMergeSort  {
    private static int CUTOFF = 7;

    @Override
    public void sort(Comparable[] comp) {
        aux = new Comparable[comp.length];
        System.arraycopy(comp, 0, aux, 0, comp.length);
        recursionSort(comp, aux,0, comp.length - 1);
    }

    private void recursionSort(Comparable[] comp, Comparable[] aux, int i, int j) {
        if(j - i <= CUTOFF - 1) {
            SortUtil.sortSelfPart(comp, i ,j);
            return;
        }
        int mid = i + (j - i)/2;
        recursionSort(aux, comp, i, mid);
        recursionSort(aux, comp, mid + 1, j);
        merge(comp, aux, i, mid, j);
    }

    void merge(Comparable[] comp, Comparable[] aux, int low , int mid, int high) {
        int i = low;
        int j = mid + 1;
        for(int k = low; k <= high; k++) {
            if(i > mid) {
                comp[k] = aux[j++];
            } else if (j > high) {
                comp[k] = aux[i++];
            } else if(less(aux[i], aux[j])) {
                comp[k] = aux[i++];
            } else {
                comp[k] = aux[j++];
            }
        }
    }
}
