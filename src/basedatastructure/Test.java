package basedatastructure;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        AbstractSymbolTable<String, Integer> symbolTable = new ParallelArrayST<>();
        symbolTable.put("f", 6);
        symbolTable.put("g", 7);
        symbolTable.put("a", 1);
        symbolTable.put("b", 2);
        symbolTable.put("h", 8);
        symbolTable.put("i", 9);
        symbolTable.put("c", 3);
        symbolTable.put("d", 4);
        symbolTable.put("e", 5);
        symbolTable.put("j", 10);
        symbolTable.put("k", 11);
        Iterator<String> keys = symbolTable.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            System.out.println("key:" + key + "  value:" + symbolTable.get(key));
        }
        symbolTable.put("c", 30);
        System.out.println("value:" + symbolTable.get("b"));
        System.out.println("size:" + symbolTable.size());
        System.out.println("isEmpty:" + symbolTable.isEmpty());
        System.out.println("value:" + symbolTable.get("c"));
        System.out.println("contain c:" + symbolTable.contains("c"));
        symbolTable.delete("c");
        System.out.println("contain c:" + symbolTable.contains("c"));
        keys = symbolTable.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            System.out.println("key:" + key + "  value:" + symbolTable.get(key));
        }
        System.out.println("size:" + symbolTable.size());
        symbolTable.delete("c");
        symbolTable.delete("d");
        symbolTable.delete("e");
        symbolTable.delete("f");
        symbolTable.delete("g");
        symbolTable.delete("h");
        symbolTable.delete("i");
        symbolTable.delete("jjjj");
        System.out.println("size:" + symbolTable.size());


//        BinaryHeapImpPQ traverseImpPQ = new BinaryHeapImpPQ(QueueType.MIN);
//        traverseImpPQ.insert(1);
//        traverseImpPQ.insert(60);
//        traverseImpPQ.insert(55);
//        traverseImpPQ.insert(40);
//        traverseImpPQ.insert(2);
//        traverseImpPQ.insert(34);
//        traverseImpPQ.insert(76);
//        traverseImpPQ.insert(88);
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
//        System.out.println(traverseImpPQ.takeOut());
    }
}
