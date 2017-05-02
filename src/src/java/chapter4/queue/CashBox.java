/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class QueueCashBox {
    
    private final int maxSize;
    private final char[] arr;
    private int head;
    private int tail;
    private int nItems;

    public QueueCashBox(int size) {
        maxSize = size;
        arr = new char[maxSize];
        head = -1;
        tail = 0;
        nItems = 0;
    }
    
    
    //--------------------------------------------------------
    public void insert(char person) {
        arr[++head] = person;
        nItems++;
    }
    
    //--------------------------------------------------------
    public char remove() {
        nItems--;
        return arr[tail++];
    }
    
    //--------------------------------------------------------
    public char peekN(int n) {
        return arr[n];
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
    public void display(String msg) {
        System.out.println(msg);
        System.out.println("Queue tail--->head");
        for (int i = 0; i < nItems; i++) {
            System.out.print( peekN(i) );
            System.out.print(' ');
        }
        System.out.println("");
    }
}


/**
 * 4.5. Очереди часто используются для моделирования потока людей, машин,
        самолетов, банковских операций и т. д. Напишите программу, моделирующую
        очередь покупателей в кассы в магазине, на базе класса Queue из программы queue.
        java (см. листинг 4.4). Программа должна отображать содержимое сразу нескольких
        очередей; воспользуйтесь методом display() из п. 4.1. Новый покупатель помеща-
        ется в очередь нажатием клавиши. Вы должны самостоятельно определить, каким
        образом он будет выбирать очередь. Обслуживание каждого покупателя имеет
        случайную продолжительность (в зависимости от количества товаров в корзине).
        Обслуженные покупатели удаляются из очереди. Для простоты течение времени
        можно моделировать нажатиями клавиш — например, каждое нажатие клавиши
        соответствует одной минуте. (Конечно, в Java есть и более совершенные средства
        для работы со временем.)
 * @author IOAdmin
 */
class CashBoxApp {
    public static void main(String[] args) throws IOException {
        int size = 5;
        // Делаем 3 очереди по 5 человек
        QueueCashBox cash1 = new QueueCashBox(size);
        QueueCashBox cash2 = new QueueCashBox(size);
        QueueCashBox cash3 = new QueueCashBox(size);
        
        char person;
        while (true) {
            System.out.print("Input first character person name: ");
            person = nextPerson();          // заносим в очередь первую букву имени человека
            if (person == 'e') {            // выход
                break;
            }
            
            // вставляем человека где очередь в кассе наименьшая
            insertPersonToCash(cash2, cash1, person, cash3);          
            // показываем все очереди к кассам
            displayCashes(cash1, cash2, cash3);
            
            System.out.print("Input 1,2,3 from where cash remove person: ");
            // берем человека из кассы(тоесть берем head из QueueCashBox)
            // и в зависимости от 1, 2 или 3 удаляем
            // либо, если значения не равны 1, 2 или 3 то никого не удаляем
            // очередь осталась такая же
            int num_cash = getPersonFromCash();
            delPersonFromCash(num_cash, cash1, cash2, cash3);
            displayCashes(cash1, cash2, cash3);
        }
    }

    private static void insertPersonToCash(QueueCashBox cash2, QueueCashBox cash1, char person, QueueCashBox cash3) {
        if (cash2.size() > cash1.size()) {
            cash1.insert(person);
        } else if (cash3.size() > cash2.size()) {
            cash2.insert(person);
        } else {
            cash3.insert(person);
        }
    }

    private static void delPersonFromCash(int num_cash, QueueCashBox cash1, QueueCashBox cash2, QueueCashBox cash3) {
        switch (num_cash) {
            case 1:
                cash1.remove();
                break;
            case 2:
                cash2.remove();
                break;
            case 3:
                cash3.remove();
                break;
            default:
                System.out.println("Cash dont determinated, person not deleted!");
                break;
        }
    }

    private static void displayCashes(QueueCashBox cash1, QueueCashBox cash2, QueueCashBox cash3) {
        cash1.display("cash1");
        cash2.display("cash2");
        cash3.display("cash3");
    }
    
    private static char nextPerson() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        char namePerson = (char) br.read();
        return namePerson;
    }

    private static int getPersonFromCash() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        int num_cash = (br.read()-'0');
        return num_cash;
    }
}
