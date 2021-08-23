package deque;

public class LinkedListDeque<Misc> {
    // what classes should be nested inside of the DLL class?
    private class Node {
        Node prev;
        Misc item;
        Node next;

        public Node(Node p, Misc i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private Node head;
    private Node tail;
    private int size;

    // LinkedListDeque constructor for an empty list
    public LinkedListDeque() {
//        Node start = new Node();
        // initialize the sentinel, review this later
        //sentinel = new Node(sentinel.next, null, sentinel.prev); first try, can't do this because the sentinel hasn't been initialized yet
        sentinel = new Node(null, null, null);
        head = sentinel;
        tail = sentinel;
        size = 0;
    }

    // addFirst, no looping or recursion, increment size
    public void addFirst(Misc i) {
        // head = new Node(sentinel.prev, i, sentinel.next); // where does next point too?
        sentinel.next = new Node(head, i, tail); // this is gonna get messy i think
        // what about sentinel.prev?
        size += 1;
    }

    // addLast, no looping or recursion, increment size
    public void addLast(Misc i) {

        size += 1;
    }

    // isEmpty, returns true if deque is empty (check size)
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // size, returns number of items in list
    public int size() {
        return size;
    }

    // printDeque(), prints items from 1st to last separated by space
    // print out new line once all items have been printed

    // removeFirst, remove and return 1st item, if none then return null
    public Node removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        Node firstNode = sentinel.next;
        sentinel.next = sentinel.next.next; // this might not work as intended
        return firstNode;
    }

    // removeLast, remove and return last item, if none then return null

    // get(int index), get item at given index where 0 == front item

    // getRecursive(int index)

    // LinkedListDeque(LinkedListDeque other) create a copy of other

    public static void main(String[] args) {
        LinkedListDeque<Integer> dL = new LinkedListDeque<>();
        dL.addFirst(1);
        dL.addFirst(2);
    }
}
