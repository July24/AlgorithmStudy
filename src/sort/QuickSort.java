package sort;

/**
 * 快速排序
 * 1.洗牌
 * 2.分片
 *      选取a[low]作为被排序的元素，从数组左端向右扫描找到一个大于它的元素
 *      从数组右端向左找到一个小于它的元素，交换他俩的位置，
 *      当两个扫描指针相遇时，将待排序元素与从右向左的指针位置元素交换并返回指针位置
 * 3.递归排序左侧，右侧
 * 平均时间复杂度:NlogN
 */
public class QuickSort extends BaseQuickSort {
    @Override
    void sort(Comparable[] comp, int low, int high) {
        if(high <= low) {
            return;
        }
        int partition = partition(comp, low, high);
        sort(comp, low, partition - 1);
        sort(comp, partition + 1, high);
    }

    int partition(Comparable[] comp, int low, int high) {
        int i = low;
        int j = high + 1;
        while(true) {
            while (less(comp[++i], comp[low])) {
                if(i == high) {
                    break;
                }
            }
            while(less(comp[low], comp[--j])) {
                if(j == low) {
                    break;
                }
            }
            if(i >= j) {
                break;
            }
            exchange(comp, i, j);
        }
        exchange(comp, low, j);
        return j;
    }
}
