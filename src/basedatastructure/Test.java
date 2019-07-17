package basedatastructure;

public class Test {
    public static void main(String[] args) {
        EasyStack<String> test = new LinkedStack<>();
        String a1 = "1";
        String a2 = "2";
        String a3 = "3";
        String a4 = "4";
        String a5 = "5";
        String a6 = "6";
        test.push(a1);
        test.push(a2);
        test.push(a3);
        System.out.println(test.pop());
        test.push(a4);
        System.out.println(test.pop());
        System.out.println(test.pop());
        test.push(a5);
        test.push(a6);
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());
        System.out.println(test.pop());

    }
}
