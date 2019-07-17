package basedatastructure;

public class Test {
    public static void main(String[] args) {
        EasyQueue<Integer> queue = new ResizingArrayQueue<>();
        queue.enqueue(1);
        queue.enqueue(3);
        queue.enqueue(10);
        queue.enqueue(13);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.enqueue(2);
        queue.enqueue(8);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
}
