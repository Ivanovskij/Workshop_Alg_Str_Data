
package src.java.chapter4.deque;

import java.util.ArrayDeque;


public class DequeArray {

    private final int maxSize;
    private int head;
    private int tail;
    private int nItems;
    private final long[] arr;
    
    public DequeArray(int size) {
        maxSize = size;
        arr = new long[maxSize];
        head = maxSize - 1;             // голова в конце массив
        tail = -1;                      // хвост в начале
        nItems = 0;                     // счетчик всех вставленных эл-тов
    }
 
    
    //--------------------------------------------------------
    // addFirst
    public void insertLeft(long item) {         // вставка слева (сначала)
        if (tail == maxSize-1) {                // циклический перенос
            tail = -1;
        }
        arr[++tail] = item;
        nItems++;
    }
    
    //--------------------------------------------------------
    // addLast
    public void insertRight(long item) {        // вставка справа (сконца)
        if (head == -1) {                       // циклический перенос
            head = maxSize - 1;
        }
        arr[head--] = item;
        nItems++;
    }
    
    //--------------------------------------------------------
    // polFirst
    public long removeLeft() {              // удаление слева (сначала)        
        if (tail == -1) {                   // циклический перенос
            tail = maxSize - 1;
        }
        nItems--;
        return arr[tail--];
    }
    
    //--------------------------------------------------------
    // polLast
    public long removeRight() {             // удаление справа (сконца)
        if (head == maxSize-1) {            // циклический перенос
            head = -1;  
        }
        nItems--;
        return arr[++head];
    }
    
    //--------------------------------------------------------
    protected long peekLeft() {
        return arr[tail];
    }
    
    //--------------------------------------------------------
    public int size() {
        return nItems;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (nItems == 0);
    }
    
    //--------------------------------------------------------
    public boolean isFull() {           
        // если nItems больше или 
        // равно maxSize то дек заполнен(переполнен)
        return (nItems >= maxSize);
    }
    
    
    /**
    * 4.2. Создайте класс Deque по описанию деков (двусторонних очередей) в этой
    *       главе. Класс должен содержать методы insertLeft() , insertRight() , removeLeft() ,
    *       removeRight() , isEmpty() и isFull() . Также в нем должна быть реализована поддержка
    *       циклического переноса индексов, по аналогии с очередями.
    * @author IOAdmin
    */
    public static void main(String[] args) {
        int size = 5;
        DequeArray da = new DequeArray(size);
        
        da.insertRight(10);
        da.insertRight(20);
        da.insertRight(30);
        da.insertRight(40);
        da.insertRight(50);
        da.insertRight(60);
//        da.insertRight(5);
//        da.insertRight(6);
        
        while (!da.isEmpty()) {
            System.out.print(da.removeLeft()+ " ");
        }
        
        
        // ЗДЕСЬ Я ТЕСТИЛ ArrayDeque чтобы понять как написать свой дек
//        ArrayDeque q = new ArrayDeque(size);
//        q.addFirst(10);
//        q.addLast(20);
//        q.addFirst(220);
//        System.out.println(q.poll());
//        System.out.println(q.poll());
//        System.out.println(q.poll());
//        System.out.println(q.poll());
    }
}
