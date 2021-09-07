package deque;

public class Main {
    public static void main(String[] args) {
//        LinkedListDeque<Integer> dL = new LinkedListDeque<>();
//        dL.addLast(1);
//        dL.addLast(2);
//        dL.removeFirst();
//        dL.addLast(4);
//        dL.addLast(5);
//        dL.printDeque();
//        System.out.println(dL.removeFirst()); // expect 3
//        System.out.println(dL.removeLast()); // expect 5
        //System.out.println(dL.get(2));
        ArrayDeque<Integer> aL = new ArrayDeque<>();
        aL.addFirst(1);
        aL.addFirst(2);
        aL.addFirst(3);
        aL.addFirst(4);
        aL.addLast(5);
        aL.addLast(6);
        aL.addLast(7);
        aL.addLast(8);
        aL.addLast(9);
    }
}
