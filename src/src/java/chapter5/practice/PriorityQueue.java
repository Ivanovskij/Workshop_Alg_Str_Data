/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter5.practice;

class SortedLinkList {

    private Link first;

    public SortedLinkList() {
        first = null;
    }
    
    //--------------------------------------------------------
    public void insert(long key_data) {
        Link current = first;
        Link previous = null;
        while (current != null && key_data > current.data) {
            previous = current;
            current = current.next;
        }
        
        Link newLink = new Link(key_data);
        if (previous == null) {
            first = newLink;
        } else {
            previous.next = newLink;
        }
        newLink.next = current;
    }
    
    //--------------------------------------------------------
    public long remove() {
        long temp = first.data;
        first = first.next;
        return temp;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (first == null);
    }
    
    //--------------------------------------------------------
    public void display() {
        System.out.println("=====DISPLAY=====");
        Link current = first;
        while(current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println();
    }
    
    
    private class Link {

        private final long data;
        private Link next;

        public Link(long data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Link{" + "data=" + data + '}';
        }
    }
}

class PriorityQueue {

    private final SortedLinkList list;

    public PriorityQueue() {
        list = new SortedLinkList();
    }
    
    //--------------------------------------------------------
    public void insert(long data) {
        list.insert(data);
    }
    
    //--------------------------------------------------------
    public long remove() {
        return list.remove();
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    //--------------------------------------------------------
    public void display() {
        list.display();
    }
}

/**
 * 5.1. Реализуйте приоритетную очередь на базе сортированного связанного
 * списка. Операция извлечения из приоритетной очереди должна извлекать элемент
 * с наименьшим ключом.
 *
 * @author IOAdmin
 */
class PriorityQueueApp {

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.insert(0);
        pq.insert(-1);
        pq.insert(5);
        pq.insert(1);
        
        pq.display();
        
        System.out.println("Elem with min key:");
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        System.out.println(pq.remove());
        
        pq.display();
    }
}
