package basedatastructure;

import java.util.Iterator;

/**
 * 抽象符号表
 *  符号表保存key-value形式的数据
 */
public abstract class AbstractSymbolTable<Key,Value>{
    public abstract void put(Key key,Value val);

    public abstract Value get(Key key);

    public abstract void delete(Key key);

    public abstract boolean contains(Key key);

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract Iterator<Key> keys();
}
