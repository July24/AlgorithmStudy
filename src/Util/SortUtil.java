package Util;

import sort.*;

/**
 * 排序工具类
 * @author 叶皓宇
 */
public class SortUtil {
    private static Sortible sortAlg;

    public static void useSelectionSort() {
        sortAlg = new SelectionSort();
    }

    public static void useInsertionSort() {
        sortAlg = new InsertionSort();
    }

    public static void useShellSort() {
        sortAlg = new ShellSort();
    }

    public static void useClassicMergeSort() {
        sortAlg = new MergeClassicImp();
    }

    public static void useBottomUpMergeSort() {
        sortAlg = new BottomUpMergeImp();
    }

    public static void sortSelf(Comparable[] comp) {
        if(!empty(comp)) {
            return;
        }
        if(sortAlg == null) {
            useShellSort();
        }
        sortAlg.sort(comp);
    }

    public static void sortSelfPart(Comparable[] comp, int i, int j) {
        if (empty(comp) || i < 0 || j >= comp.length) {
            return;
        }
        if (sortAlg == null) {
            useInsertionSort();
        }
        int length = j - i + 1;
        Comparable[] copy = new Comparable[length];
        System.arraycopy(comp, i, copy, 0, length);
        sortAlg.sort(copy);
        System.arraycopy(copy, 0, comp, i, length);
    }

    public static Comparable[] getSortArray(Comparable[] comp) {
        if(!empty(comp)) {
            return null;
        }
        if(sortAlg == null) {
            useShellSort();
        }
        int length = comp.length;
        Comparable[] aux = new Comparable[length];
        System.arraycopy(comp, 0, aux, 0, length);
        sortAlg.sort(aux);
        return aux;
    }

    private static boolean empty(Comparable[] comp) {
        return comp == null || comp.length == 0;
    }
}
