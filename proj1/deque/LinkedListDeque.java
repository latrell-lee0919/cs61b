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
    public void printDeque() {
        Node curr = sentinel.next;
        while (curr != sentinel) {
            System.out.println(curr.item);
            System.out.println(" ");
            curr = curr.next;
        }
        System.out.println("All items printed");
    }

    // removeFirst, remove and return 1st item, if none then return null
    public Misc removeFirst() {
        if (size == 0) {
            return null;
        }
        Misc first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;

        size -= 1;
        return first;
    }

    // removeLast, remove and return last item, if none then return null
    public Misc removeLast() {
        if (size == 0) {
            return null;
        }
        Misc last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;

        return last;
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
//    public Misc getRecursive(int index, Node curr) { // review with Hunter
//        if (index == 0) {
//            return sentinel.next.item;
//        }
//
//        return curr.getRecursive(index - 1);
//    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> dL = new LinkedListDeque<>();
        dL.addFirst(1);
        dL.addFirst(2);
    }
}
