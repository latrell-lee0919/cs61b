package deque;

public class Main {
    public static void main(String[] args) {
        LinkedListDeque<Integer> dL = new LinkedListDeque<>();
        dL.addFirst(1);
        dL.addFirst(2);
        dL.addFirst(3);
        dL.addLast(4);
        dL.addLast(5);
        dL.removeFirst();
        dL.removeLast();
        System.out.println(dL.get(2));
    }
}
