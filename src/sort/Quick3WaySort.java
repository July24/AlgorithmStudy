package sort;

/**
 * 三向切分快速排序
 * 适用于排序内容主键相同较多的情况
 * 维护一个指针lt使a[lo->lt-1]均小于v，维护一个指针gt使[gt+1->hi]中的元素都大于v
 * 一个指针i使a[lt->i-1]中的元素都等于v，a[i->gt]的元素未排序
 * 处理方法：
 *      a[i]<v，将a[lt]和a[i]交换，将lt和i加一
 *      a[i]>v，将a[gt]和a[i]交换，将a[gt]减一
 *      a[i]=v，将i加一
 * @author 叶皓宇
 */
public class Quick3WaySort extends BaseQuickSort {
    @Override
    void sort(Comparable[] comp, int low, int high) {
        if(low >= high) {
            return;
        }
        int lt = low;
        int i  = low;
        int gt = high;
        Comparable v = comp[low];
        while(i <= gt) {
            int result = v.compareTo(comp[i]);
            if(result > 0 ) {
                exchange(comp, lt++, i++);
            } else if(result < 0) {
                exchange(comp, gt--, i);
            } else {
                i++;
            }
        }
        sort(comp, low, lt - 1);
        sort(comp, gt + 1, high);
    }
}
