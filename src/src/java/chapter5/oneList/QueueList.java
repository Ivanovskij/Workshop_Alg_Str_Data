/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter5.oneList;

class QueueList {
    
    DoubleEndedList dEndedList;

    public QueueList() {
        dEndedList = new DoubleEndedList();
    }
    
    //--------------------------------------------------------
    public void insert(int data) {
        dEndedList.insertLast(data);
    }
    
    //--------------------------------------------------------
    public int remove() {
        return dEndedList.deleteFirst();
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return dEndedList.isEmpty();
    }
    
    //--------------------------------------------------------
    public void display() {
        dEndedList.display();
    }
}

/**
 *
 * @author IOAdmin
 */
class QueueListApp {
    public static void main(String[] args) {
        QueueList ql = new QueueList();
        ql.insert(0);
        ql.insert(1);
        ql.insert(2);
        
        ql.remove();
        
        ql.display();
    }
}
