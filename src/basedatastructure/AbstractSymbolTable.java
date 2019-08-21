package basedatastructure;

import java.util.Iterator;

/**
 * 抽象符号表
 *  符号表保存key-value形式的数据
 */
public abstract class AbstractSymbolTable<Key,Value>{
    public abstract void put(Key key,Value val);

    public abstract Value get(Key key);

    public void delete(Key key) {
        put(key, null);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }


    public  boolean isEmpty() {
        return size() == 0;
    }

    public abstract int size();

    public abstract Iterator<Key> keys();
}
