package Util;

import sort.InsertionSort;
import sort.SelectionSort;
import sort.ShellSort;
import sort.Sortible;

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

    public static void sortSelf(Comparable[] comp) {
        if(!notEmpty(comp)) {
            return;
        }
        if(sortAlg == null) {
            useShellSort();
        }
        sortAlg.sort(comp);
    }

    public static Comparable[] getSortible(Comparable[] comp) {
        if(!notEmpty(comp)) {
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

    private static boolean notEmpty(Comparable[] comp) {
        return comp == null || comp.length == 0;
    }
}
