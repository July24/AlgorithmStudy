package basedatastructure;

import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        TwoThreeSearchTreeST<String, Integer> symbolTable = new TwoThreeSearchTreeST<>();
        symbolTable.put("a", 1);
        symbolTable.put("f", 6);
        symbolTable.put("b", 2);
        symbolTable.put("h", 8);
        symbolTable.put("i", 9);
        symbolTable.put("c", 3);
        symbolTable.put("d", 4);
        symbolTable.put("j", 10);
        symbolTable.put("k", 11);
        symbolTable.put("z", 99);
//        System.out.println(symbolTable.get("j"));
//        System.out.println(symbolTable.get("h"));
//        System.out.println(symbolTable.get("b"));
//        System.out.println("总数" + symbolTable.size());
//        symbolTable.put("j", 66);
//        System.out.println(symbolTable.get("j"));
//        System.out.println("总数" + symbolTable.size());
        Iterator iter = symbolTable.keys();
        while(iter.hasNext()) {
            System.out.println(iter.next());
        }
//        Iterator iterator = symbolTable.keys("d","i");
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        symbolTable.print();
//        System.out.println(symbolTable.select(2) + "  size:" + symbolTable.size());
////        symbolTable.delete("z");
////        System.out.println(symbolTable.select(10) + "  size:" + symbolTable.size());
//        symbolTable.delete("c");
//        System.out.println(symbolTable.select(2) + "  size:" + symbolTable.size());
//        symbolTable.delete("e");
//        System.out.println(symbolTable.select(3) + "  size:" + symbolTable.size());
//        symbolTable.delete("j");
//        System.out.println(symbolTable.select(7) + "  size:" + symbolTable.size());

//        Iterator<String> keys = symbolTable.keys();
//        while(keys.hasNext()) {
//            String key = keys.next();
//            System.out.println("key:" + key + "  value:" + symbolTable.get(key));
//        }
//        symbolTable.put("c", 30);
//        System.out.println("value:" + symbolTable.get("b"));
//        System.out.println("size:" + symbolTable.size());
//        System.out.println("isEmpty:" + symbolTable.isEmpty());
//        System.out.println("value:" + symbolTable.get("c"));
//        System.out.println("contain c:" + symbolTable.contains("c"));
//        symbolTable.delete("c");
//        System.out.println("contain c:" + symbolTable.contains("c"));
//        keys = symbolTable.keys();
//        while(keys.hasNext()) {
//            String key = keys.next();
//            System.out.println("key:" + key + "  value:" + symbolTable.get(key));
//        }
//        System.out.println("size:" + symbolTable.size());
//        symbolTable.delete("c");
//        symbolTable.delete("d");
//        symbolTable.delete("e");
//        symbolTable.delete("f");
//        symbolTable.delete("g");
//        symbolTable.delete("h");
//        symbolTable.delete("i");
//        symbolTable.delete("jjjj");
//        System.out.println("size:" + symbolTable.size());

//        IndexPQ<String> traverseImpPQ = new IndexPQ(20, QueueType.MIN);
//        traverseImpPQ.insert(1, "a");
//        traverseImpPQ.insert(7,"g");
//        traverseImpPQ.insert(2, "b");
//        traverseImpPQ.insert(8,"h");
//        traverseImpPQ.insert(3, "c");
//        traverseImpPQ.insert(5,"e");
//        traverseImpPQ.insert(6,"f");
//        traverseImpPQ.insert(4, "d");
//
//
//        traverseImpPQ.change(3,"u");
//        traverseImpPQ.change(5,"w");
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
