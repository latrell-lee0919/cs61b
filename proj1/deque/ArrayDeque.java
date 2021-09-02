package deque;

public class ArrayDeque<Misc> {
    private Misc[] items;
    private int first;
    private int last;
    private int size;
    private int capacity;

    // constructor
    public ArrayDeque() {
        //circular array
        // in the beginning, first and back will be in the same position
        items = (Misc[]) new Object[8];
        first = 0;
        last = 0;
        size = 0;
        capacity = items.length;
    }

    // helper function, int minusOne(int index), compute array indices

    // addFirst, add to beginning on the array
    public void addFirst(Misc i) {
        items[first] = i;
        last += 1;
        size += 1;
    }

    // [0, 0, 0, 0], first = 0, last = 0
    // addFirst(1), items[0] == 1, last = 1;
    // [1, 0, 0, 0], first = 0, last = 1;
    // addLast(2), items[1] == 2
    // [1, 2, 0, 0]
    // addLast(3), items[2] == 3
    // [1, 2, 3, 0]
    // addLast(4), items[3] == 4
    // [1, 2, 3, 4]

    // addLast, add to the end of the array
    public void addLast(Misc i){
        if(size == capacity) {
            // resize the array
        }

        items[last] = i;
        last = (last + 1) % capacity;
        size += 1;
    }

    // isEmpty
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // size
    public int size() {
        return size;
    }

    // removeFirst, remove first item in array
    public Misc removeFirst() {
        Misc temp = items[first];
        items[first] = null;
        first = first + 1;
        // if size is less than 25% of capacity, resize array
        size -= 1;
        return temp;
    }

    // removeLast, remove last item in array
    public Misc removeLast() {
        Misc temp = items[last];
        items[last] = null;
        last = last - 1;
        // if size is less than 25% of capacity, resize array
        size -= 1;
        return temp;
    }

    // get, needs to be constant time so no looping
    public Misc get(int index) {
        return items[(index + first) % capacity]; // using mod is good for resetting back to the beginning
    }

    // resize helper method

    // printDeque
    public void printDeque() {
        for(int start = first; start != last; start = (start + 1) % capacity) {
            System.out.println(items[start]);
        }
    }


}
