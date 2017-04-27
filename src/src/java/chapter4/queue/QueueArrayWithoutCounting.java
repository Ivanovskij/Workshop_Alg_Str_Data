/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.queue;

/*
    Также может возникнуть одна странная проблема: при заполненной очереди
    указатели front и rear могут занимать точно такие же позиции, как и при пустой
    очереди. Очередь выглядит заполненной и пустой одновременно. Для решения этой
    проблемы создается массив с количеством ячеек, на единицу большим максималь-
    ного количества элементов, которые в нем будут размещаться. Пример реализации
    класса очереди без счетчика элементов представлен в листинге 4.5.
 */
class QueueArrayWithoutCounting {

    private final int maxSize;
    private int rear;
    private int front;
    private final long[] arr;

    public QueueArrayWithoutCounting(int size) {
        maxSize = size + 1;                 // Массив на одну ячейку больше
        arr = new long[maxSize];            // требуемого размера
        rear = -1;
        front = 0;
    }

    //--------------------------------------------------------
    public void insert(long value) {
        if (rear == maxSize - 1) {
            rear = -1;
        }
        arr[++rear] = value;
    }

    //--------------------------------------------------------
    public long remove() {
        long temp = arr[front++];
        if (front == maxSize) {
            front = 0;
        }
        return temp;
    }

    //--------------------------------------------------------
    public long peekFront() {
        return arr[front];
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (rear + 1 == front || front + maxSize - 1 == rear);
    }

    //--------------------------------------------------------
    public boolean isFull() {
        return (rear + 2 == front || front + maxSize - 2 == rear);
    }

    //--------------------------------------------------------
    public int size() {
        if (rear >= front) {            // Непрерывная последовательность
            return rear - front + 1;
        } else {                        // Несвязная последовательность
            return (maxSize - front) + (rear + 1);
        }
    }
}

/**
 * ЗДЕСЬ МЫ НЕ ИСПОЛЬЗУЕМ СЧЕТЧИК ЭЛ-ТОВ nItems
 *
 * @author IOAdmin
 */
class QueueArrayWithoutCountingApp {

    public static void main(String[] args) {
        int size = 5;
        QueueArrayWithoutCounting qawc = new QueueArrayWithoutCounting(size);

        qawc.insert(0);
        qawc.insert(1);
        qawc.insert(2);
        qawc.insert(3);
        qawc.insert(3);

        long elem;
        while (!qawc.isEmpty()) {
            elem = qawc.remove();
            System.out.print(elem + " ");
        }
        System.out.println();

        System.out.println(qawc.isEmpty());
    }
}
