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
    public long peekFrontN(int n) {                   // просмотр эл-та по индексу
        return arr[n];
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
    
    
    //--------------------------------------------------------
    /*
        4.1. Напишите метод класса Queue программы q�e�e.java (см. листинг 4.4) для
            вывода содержимого очереди. Учтите, что задача не сводится к простому выводу со-
            держимого базового массива. Содержимое очереди должно выводиться от первого
            вставленного элемента до последнего, а пользователь не должен видеть, что после-
            довательность прерывается на границе массива. Будьте внимательны и проследите
            за тем, чтобы один элемент и содержимое пустой очереди выводились корректно
            независимо от положения front и rear 
    */
    public void display() {
        // запоминаем позицию головы и двигаемся от нее
        // таким образом мы не будем зависеть от front и rear
        // и будем всегда выводить, то что находится в очереди
        int tmpFront = front;                       
        for (int i = 0; i < size(); i++) {
            System.out.print( peekFrontN(tmpFront++) );
            System.out.print(" ");
        }
        System.out.println();
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

        qa.insert(0);         
        qa.insert(1);         
        qa.insert(2);
        qa.insert(3);
        qa.insert(4);
        
        qa.remove();            
        qa.remove();            
        qa.remove();            
        qa.remove();            
        qa.remove();            
//        qa.remove();
        
       // возможен циклический перенос
        qa.insert(50);          
        qa.insert(60);
        qa.remove();
//        qa.insert(70);
//        qa.insert(80);
        
        qa.display();
        
//        while (!qa.isEmpty())   // Извлечение и вывод всех элементов
//        { 
//            long elem = qa.remove();       // (40, 50, 60, 70, 80)
//            System.out.print(elem);
//            System.out.print(" ");
//        }
//        System.out.println("");
    }
}
