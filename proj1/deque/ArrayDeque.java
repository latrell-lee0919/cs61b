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
        first = 0; // first pointer is so you can remove and add to the beginning
        last = 0;
        size = 0;
        capacity = items.length; // max length of items length
    }

    // resize helper method
    private void resize(int newCapacity) {
        // create a new array with length of capacity
        Misc[] a = (Misc[]) new Object[newCapacity];
        // copy old array into the new array
        int indexA = 0;
        // loop through items, from first to last, setting element equal to i of a
        for(int start = first; start != last; start = (start + 1) % capacity){
            // we start at the first element of the items array
            // we continue as long as start doesn't reach the last element
            // we do this since the array is cyclical
            // the point of the last condition is that it gets us back to the
            // position that we essentially started at
            a[indexA] = items[start];
            indexA += 1;
        }
        a[indexA] = items[last];
        // point items to new array
        items = a;
        first = 0;
        last = size - 1;
        capacity = a.length;
    }

    // addFirst, add to beginning on the array
    public void addFirst(Misc i) {
        if(size == capacity) {
            resize(size * 2);
        }

        if(size != 0) {
            first = (first - 1) % capacity;
            if (first == - 1) {
                first = capacity - 1;
            }
        }
        items[first] = i;
        size += 1;

        //last += 1;
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
            resize(size * 2);
        }

        if(size != 0) {
            last = (last + 1) % capacity;
        }
        items[last] = i;
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
        size -= 1;
        // if size is less than 25% of capacity, resize array
        if(size < (capacity * .25)){
            resize(size * 2);
        }
        return temp;
    }

    // removeLast, remove last item in array
    public Misc removeLast() {
        Misc temp = items[last];
        items[last] = null;
        last = last - 1;
        size -= 1;
        // if size is less than 25% of capacity, resize array
        if(size < (capacity * .25)) {
            resize(size * 2);
        }
        return temp;
    }

    // get, needs to be constant time so no looping
    public Misc get(int index) {
        return items[(index + first) % capacity]; // using mod is good for resetting back to the beginning
    }

    // printDeque
    public void printDeque() {
        for(int start = first; start != last; start = (start + 1) % capacity) {
            System.out.println(items[start]);
        }
        System.out.println(items[last]);
    }


}
