package basedatastructure;

import java.util.Iterator;

/**
 * 用两个数组实现符号表
 */
public class ParallelArrayST<Key extends Comparable<Key>, Value> extends AbstractSymbolTable<Key, Value> {
    Key[] keys;
    Value[] values;
    int size = 0;
    private static int DEFAULT_CAPACITY = 16;
    private static int SHRINK_FACTOR    = 4;

    public ParallelArrayST() {
        keys   = (Key[])new Comparable[DEFAULT_CAPACITY];
        values = (Value[])new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void put(Key key, Value val) {
        if(size >= keys.length) {
            Key[] newKeyArray = (Key[])new Comparable[keys.length*2];
            System.arraycopy(keys, 0, newKeyArray, 0, size);
            keys = newKeyArray;
            Value[] newValArray = (Value[])new Comparable[values.length*2];
            System.arraycopy(values, 0, newValArray, 0, size);
            values = newValArray;
        }
        int pos = find(key);
        if(pos < 0) {
            keys[size] = key;
            values[size++] = val;
            for(int i = size - 1; i > 0; i--) {
                if(keys[i].compareTo(keys[i - 1]) < 0) {
                    Key temp = keys[i - 1];
                    keys[i - 1] = keys[i];
                    keys[i] = temp;
                    Value tempV = values[i - 1];
                    values[i - 1] = values[i];
                    values[i] = tempV;
                } else {
                    break;
                }
            }
        } else {
            values[pos] = val;
        }
    }

    @Override
    public Value get(Key key) {
        int pos = find(key);
        if(pos < 0) {
            return null;
        }
        return values[pos];
    }

    @Override
    public void delete(Key key) {
        int pos = find(key);
        if(pos < 0) {
            return;
        }
        for(int i = pos + 1; i < size; i++) {
            keys[i - 1] = keys[i];
        }
        for(int i = pos + 1; i < size; i++) {
            values[i - 1] = values[i];
        }
        keys[size - 1] = null;
        values[size - 1] = null;
        size--;
        if(size > DEFAULT_CAPACITY && size <= keys.length/SHRINK_FACTOR) {
            Key[] newKeyArray = (Key[])new Comparable[keys.length/2];
            System.arraycopy(keys, 0, newKeyArray, 0, size);
            keys = newKeyArray;
            Value[] newValArray = (Value[])new Comparable[values.length/2];
            System.arraycopy(values, 0, newValArray, 0, size);
            values = newValArray;
        }
    }


    @Override
    public boolean contains(Key key) {
        return find(key) > 0;
    }

    private int find(Key key) {
        int low  = 0;
        int high = size - 1;
        while(low <= high) {
            int mid = (high + low)/2;
            int res = key.compareTo(keys[mid]);
            if(res > 0) {
                low = mid + 1;
            } else if (res < 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Key> keys() {
        return new Iterator<Key>() {
            int i = 0;
            @Override
            public boolean hasNext() {
                return i < size;
            }

            @Override
            public Key next() {
                return keys[i++];
            }
        };
    }
}
