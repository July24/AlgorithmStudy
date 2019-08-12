package sort;

/**
 * 堆排序
 *      1.堆的构造: 将给定数组变为堆有序，从右至左用sink函数构造子堆，跳过没有子结点的结点
 *      2.下沉排序: 将堆中最大元素删除，然后放入堆缩小后数组中空出的位置，循环N-1次
 * @author 叶皓宇
 */
public class HeapSort extends AbstractSortAlgorithm{
    @Override
    public void sort(Comparable[] comp) {
        int length = comp.length;
        for(int i = length/2; i > 0; i--) {
            sink(comp, i, length);
        }
        while(length > 1) {
            exchange(comp, --length, 0);
            sink(comp, 1, length);
        }
    }

    private void sink(Comparable[] elements, int i, int N) {
        while(2*i <= N) {
            int sub = 2*i;
            if(sub < N && less(elements[sub - 1], elements[sub])) {
                sub++;
            }
            if(!less(elements[i - 1], elements[sub - 1])) {
                break;
            }
            exchange(elements, sub - 1, i - 1);
            i = sub;
        }
    }
}
