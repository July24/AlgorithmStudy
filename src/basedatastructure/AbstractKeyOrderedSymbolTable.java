package basedatastructure;

import java.util.Iterator;

/**
 * 抽象键有序符号表
 */
public abstract class AbstractKeyOrderedSymbolTable<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    public abstract Key min();

    public abstract Key max();

    public abstract Key floor(Key key);

    public abstract Key ceiling(Key key);

    public abstract int rank(Key key);

    public abstract Key select(int i);

    public void deleteMin() {
        delete(min());
    }

    public void deleteMax() {
        delete(max());
    }

    public abstract Iterator<Key> keys(Key low, Key high);

    @Override
    public Iterator<Key> keys() {
        return keys(min(), max());
    }

    public int size(Key low, Key high) {
        if(high.compareTo(low) < 0) {
            return 0;
        } else if(contains(high)) {
            return rank(high) - rank(low) + 1;
        } else {
            return rank(high) - rank(low);
        }
    }

}
