package basedatastructure;

public class Test {
    public static void main(String[] args) {
        BinaryHeapImpPQ traverseImpPQ = new BinaryHeapImpPQ(QueueType.MIN);
        traverseImpPQ.insert(1);
        traverseImpPQ.insert(60);
        traverseImpPQ.insert(55);
        traverseImpPQ.insert(40);
        traverseImpPQ.insert(2);
        traverseImpPQ.insert(34);
        traverseImpPQ.insert(76);
        traverseImpPQ.insert(88);
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
        System.out.println(traverseImpPQ.takeOut());
//        EasyQueue<Integer> queue = new ResizingArrayQueue<>();
//        queue.enqueue(1);
//        queue.enqueue(3);
//        queue.enqueue(10);
//        queue.enqueue(13);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        queue.enqueue(2);
//        queue.enqueue(8);
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
//        System.out.println(queue.dequeue());
    }
}
