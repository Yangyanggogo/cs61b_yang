public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    private int minusOne(int i) {
        if (i <= 1) {
            return items.length -1;
        }
        return i -1;
    }
    private int plusOne(int i) {
        if (i >= items.length - 1) {
            return 0;
        }
        return i + 1;
    }
    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int temp = items.length - nextFirst;
        System.arraycopy(items, nextFirst, a, 0, temp);
        if (temp < items.length) {
            System.arraycopy(items, 0, a, temp, items.length - temp);
        }
        items = a;
        nextFirst = a.length - 1;
        nextLast = size;


    }
    public void addFirst(T item){
        if (size == items.length) {
            resize(2 * size);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (T item : items) {
            System.out.print(item + "");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextFirst = plusOne(nextFirst);
        T res = items[nextFirst];
        if (items.length > 8 && (float)size/ items.length < 0.25) {
            resize(items.length/2);
        }
        return res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextFirst = minusOne(nextFirst);
        T res = items[nextLast];
        if (items.length > 8 && (float)size/ items.length < 0.25) {
            resize(items.length/2);
        }
        return res;
    }

    public T get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        index = (index + nextFirst + 1) % items.length;
        return items[index];
    }

}
