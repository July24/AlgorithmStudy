package sort;

/**
 * Shell sort.
 * Increment sequence:
 * Shell:2^n-1          1,3,7,15,...
 * Knuth:x=3*x+1        1,4,13,40,...
 * Sedgewick:
 * merge
 * odd      9 * 4^i - 9 * 2^i + 1   1,19,109,505,2161
 * even     4^i - 3 * 2^i + 1       -1,-1,5,41,209
 * 1,5,19,41,109,209,505...
 * @author yehaoyu
 */
public class ShellSort extends BaseSortAlgorithm {
    @Override
    public void sort(Comparable[] comp) {
        int length = comp.length;
        int h = 1;
        while(h < length/3) {
            h = h * 3 + 1;
        }
        while(h > 0) {
            for(int i = h; i < length; i++) {
                for(int j = i; j >= h && less(comp[j], comp[j-h]); j -= h) {
                    exchange(comp, j, j-h);
                }
            }
            h = h/3;
        }
    }
}
