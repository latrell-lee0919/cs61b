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
    private int size;

    // LinkedListDeque constructor for an empty list
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // addFirst, no looping or recursion, increment size
    public void addFirst(Misc i) {
        Node currFirst = sentinel.next;
        sentinel.next = new Node(sentinel, i, currFirst);
        currFirst.prev = sentinel.next;
        size += 1;
    }

    // addLast, no looping or recursion, increment size
    public void addLast(Misc i) {
        Node currLast = sentinel.prev;
        sentinel.prev = new Node(currLast, i, sentinel);
        currLast.next = sentinel.prev;
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
        sentinel.next = sentinel.next.next;
        // need to update the prev pointer of the node after the first node
        sentinel.next.prev = sentinel;

        size -= 1;
        return firstNode; // how do i return the value?
    }

    // removeLast, remove and return last item, if none then return null
    public Node removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        Node lastNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;

        return lastNode; // review with hunter
    }

    // get(int index), get item at given index where 0 == front item
    public Misc get(int index){
        Node curr = sentinel.next;
        int counter = 0;
        Misc found = null;
        while (curr != null) {
            if (counter == index) {
                found = curr.item;
                break;
            }
            curr = curr.next;
            counter += 1;
        }
        return found;
    }

    // getRecursive(int index)
//    public Misc getRecursive(int index) { // review with Hunter
//        Node curr = sentinel.next;
//        if (index == 0) {
//            return curr.item;
//        }
//
//        return ;
//    }

    // LinkedListDeque(LinkedListDeque other) create a copy of other

    public static void main(String[] args) {
        LinkedListDeque<Integer> dL = new LinkedListDeque<>();
        dL.addFirst(1);
        dL.addFirst(2);
    }
}
