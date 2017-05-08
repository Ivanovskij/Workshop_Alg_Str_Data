package src.java.chapter5.doubleList;

class DoubleLinkList {
    
    private Link first;
    private Link last;

    public DoubleLinkList() {
        first = null;
        last = null;
    }
    
    
    //--------------------------------------------------------
    public void insertFarward(long data) {
        Link newLink = new Link(data);
        if (isEmpty()) {                // если нет эл-тов
            last = newLink;             // newLink <-- last
        } else {
            first.previous = newLink;   // newLink <-- старое значение first
        }
        
        newLink.next = first;           // newLink --> старое значение first
        first = newLink;
    }
    
    //--------------------------------------------------------
    public void insertBackward(long data) {
        Link newLink = new Link(data);
        if (isEmpty()) {
            first = newLink;
        } else {
            last.next = newLink;
            newLink.previous = last;
        }
        
        last = newLink;
    }
    
    //--------------------------------------------------------
    public boolean insertAfter(long key, long data) {
        if (isEmpty()) {
            insertFarward(data);
        }
        
        Link current = find(key);
        if (current == null) {
            return false;
        }
    
        Link newLink = new Link(data);
        if (current == last) {
            newLink.next = null;
            last = newLink;
        } else {
            newLink.next = current.next;
            current.next.previous = newLink;
        }
        
        newLink.previous = current;
        current.next = newLink;
        return true;
    }
    
    //--------------------------------------------------------
    public long deleteFarward() {
        long temp = first.data;
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }
        first = first.next;
        return temp;
    }
    
    //--------------------------------------------------------
    public long deleteBackward() {
        long temp = last.data;
        if (last.previous == null) {
            first = null;
        } else {
            last.previous.next = null;
        }
        last = last.previous;
        return temp;
    }
    
    //--------------------------------------------------------
    public boolean deleteKey(long key) {
        Link current = find(key);
        if (current == null) {
            return false;
        }
        
        if (current == first) {
            first = first.next;
        } else {
            current.previous.next = current.next;
        }
        
        if (current == last) {
            last = current.previous;
        } else {
            current.next.previous = current.previous;
        }
        
        
        return true;
    }
    
    //--------------------------------------------------------
    public Link find(long key) {
        Link current = first;
        while (key != current.data) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (first == null);
    }
    
    //--------------------------------------------------------
    public void displayFarward() {
        System.out.println("========DISPLAY FIRST========");
        Link current = first;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println();
    }
    
    //--------------------------------------------------------
    public void displayBackward() {
        System.out.println("========DISPLAY LAST========");
        Link current = last;
        while (current != null) {
            System.out.println(current);
            current = current.previous;
        }
        System.out.println();
    }
    
    private class Link {
        
        private long data;
        private Link next;
        private Link previous;

        public Link(long data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Link{" + "data=" + data + '}';
        }
    }
}

/**
 *
 * @author IOAdmin
 */
class DoubleLinkListApp {
    public static void main(String[] args) {
        DoubleLinkList list = new DoubleLinkList();
        
//        double start = System.nanoTime();
//        for (int i = 0; i < 2250000; i++) {
//            list.insertBackward(i);
//        }
//        System.out.println("TIME = " + ((System.nanoTime() - start) / 1000000000));

        list.insertFarward(0);
        list.insertFarward(1);
        list.deleteBackward();
        list.deleteBackward();
        list.displayFarward();
    }
}
