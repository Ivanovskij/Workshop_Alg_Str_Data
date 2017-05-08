package src.java.chapter5.practice;

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
        
        if (isEmpty()) {
            last = newLink;
        } else {
            first.previous = newLink;
        }
        
        newLink.next = first;
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
    public long removeFarward() {
        long temp = last.data;
        
        if (first.next == null) {
            last = null;
        } else {
            first.next.previous = null;
        }

        first = first.next;
        return temp;
    }
    
    //--------------------------------------------------------
    public long removeBackward() {
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
    public boolean isEmpty() {
        return (first == null);
    }
    
    //--------------------------------------------------------
    public void displayFarward() {
        System.out.println("=====DISPLAY FARWARD=====");
        Link current = first;
        while(current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println();
    }
    
    //--------------------------------------------------------
    public void displayBackward() {
        System.out.println("=====DISPLAY BACKWARD=====");
        Link current = last;
        while(current != null) {
            System.out.println(current);
            current = current.previous;
        }
        System.out.println();
    }
    
    
    private class Link {
        
        private Link next;
        private Link previous;
        private long data;

        public Link(long data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Link{" + "data=" + data + '}';
        }
    }
}


class Deque {
    
    private DoubleLinkList list;

    public Deque() {
        list = new DoubleLinkList();
    }
    
    //--------------------------------------------------------
    public void insertLeft(long data) {
        list.insertFarward(data);
    }
    
    //--------------------------------------------------------
    public void insertRight(long data) {
        list.insertBackward(data);
    }
    
    //--------------------------------------------------------
    public long removeLeft() {
        return list.removeFarward();
    }
    
    //--------------------------------------------------------
    public long removeRight() {
        return list.removeBackward();
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    //--------------------------------------------------------
    public void displayFarward() {
        list.displayFarward();
    }
    
    //--------------------------------------------------------
    public void displayBackward() {
        list.displayBackward();
    }
}

/**
 * 5.2. Реализуйте дек на базе двусвязного списка (см. программный проект 4.2 из
        предыдущей главы). Дек должен поддерживать все стандартные операции.
 * @author IOAdmin
 */
class DequeApp {
    public static void main(String[] args) {
        Deque deque = new Deque();
        deque.insertLeft(0);
        deque.insertLeft(1);
        deque.insertRight(3);
        deque.insertRight(4);
        
        deque.removeLeft();
        deque.removeRight();
        
        deque.displayBackward();
        deque.displayFarward();
    }
}
