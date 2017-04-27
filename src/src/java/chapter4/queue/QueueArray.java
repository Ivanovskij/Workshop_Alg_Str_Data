/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.queue;

/**
 * ********************************************************************
 */
/**
 * ********************************************************************
 */
/**
 * *********************************************************************
 */
class QueueArr {

    private final int maxSize;          // макс кол-во возможных эл-тов
    private final long[] arr;           
    private int nItems;                 // сколько эл-тов было вставлено
    private int rear;                   // последний эл-т в очереди
    private int front;                  // первый эл-т в очереди

    public QueueArr(int size) {
        maxSize = size;
        arr = new long[maxSize];
        front = 0;                      // первым вышел, первым выйдет
        rear = -1;                      // поледним пришел, последним выйдет
        nItems = 0;
    }

    //--------------------------------------------------------
    public void insert(long value) {            // вставка эл-та в конец очереди
        if (rear == maxSize - 1) {              // циклический перенос
            rear = -1;
        }
        arr[++rear] = value;                    // вставка эл-та
        nItems++;                               // увеличение кол-ва всех вставленных эл-тов
    }

    //--------------------------------------------------------
    public long peekFront() {                   // просмотр первого эл-та без удаления
        return arr[front];
    }

    //--------------------------------------------------------
    public long remove() {                      // просмотр с циклическим удалением
        long temp = arr[front++];               // сохраняем первый эл-ты
        if (front == maxSize) {                 // проверяем если первый равен maxSize
            front = 0;                          // делаем циклический сдвиг
        }
        nItems--;                               // уменьшаем кол-во всех вставленных эл-тов
        return temp;                            // возвращаем сохраненный первый эл-т
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (nItems == 0);
    }
    
    //--------------------------------------------------------
    public boolean isFull() {
        return (nItems == maxSize);
    }

    //--------------------------------------------------------
    public int size() {
        return nItems;
    }
}

/**
 * ********************************************************************
 */
/**
 * ********************************************************************
 */
/**
 * *********************************************************************
 */
class QueueArrayApp {

    public static void main(String[] args) {
        int maxSize = 5;
        QueueArr qa = new QueueArr(maxSize);

        qa.insert(10);          // Вставка 4 элементов
        qa.insert(20);
        qa.insert(30);
        qa.insert(40);
        
        qa.remove();            // Извлечение 3 элементов
        qa.remove();            // (10, 20, 30)
        qa.remove();
        
        qa.insert(50);          // Вставка еще 4 элементов
        qa.insert(60);          // (с циклическим переносом)
        qa.insert(70);
        qa.insert(80);
        
        while (!qa.isEmpty())   // Извлечение и вывод всех элементов
        { 
            long elem = qa.remove();       // (40, 50, 60, 70, 80)
            System.out.print(elem);
            System.out.print(" ");
        }
        System.out.println("");
    }
}
