public class LinkedListDeque<Item> implements Deque<Item> {
    private class StuffNode {
        private Item item;
        private StuffNode prev;
        private StuffNode next;

        StuffNode(Item i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /* The first item (if it exists) is at sentinel.next.*/
    private StuffNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(Item item) {
        size += 1;
        sentinel.next = new StuffNode(item, sentinel, sentinel.next);
        sentinel.prev = sentinel.next;

    }

    @Override
    public void addLast(Item item) {
        size += 1;
        sentinel.prev.next = new StuffNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + "");
            p = p.next;
        }
    }

    @Override
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item i = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return i;
    }

    @Override
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Item res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return res;
    }

    @Override
    public Item get(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        StuffNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    public Item getRecursive(int index) {
        if (index < 0 || index > size - 1) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private Item getRecursiveHelper(StuffNode p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursiveHelper(p.next, index - 1);
    }
}
