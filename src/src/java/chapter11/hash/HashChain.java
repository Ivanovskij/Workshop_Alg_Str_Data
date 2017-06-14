/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter11.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*-------------------------------*/
class Link {

    private final int iData;
    private Link next;

    public Link(int iData) {
        this.iData = iData;
    }

    public void displayLink() {
        System.out.print("Link{" + "iData=" + iData + '}' + "; ");
    }

    public int getKey() {
        return iData;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }
}


/*-------------------------------*/
class SortedList {

    private Link first;

    public SortedList() {
        first = null;
    }

    // -------------------------------------------------------------
    public void insert(Link theLink) {
        int key = theLink.getKey();

        Link current = first;
        Link previous = null;

        while (current != null && key > current.getKey()) {
            previous = current;
            current = current.getNext();
        }
        if (previous == null) {
            first = theLink;
        } else {
            previous.setNext(theLink);
        }
        theLink.setNext(current);
    }

    // -------------------------------------------------------------
    public void delete(int key) {
        Link current = first;
        Link previous = null;

        while (current != null && key != current.getKey()) {
            previous = current;
            current = current.getNext();
        }

        if (previous == null) {
            first = first.getNext();
        } else {
            previous.setNext(current.getNext());
        }
    }

    // -------------------------------------------------------------
    public Link find(int key) {
        Link current = first;
        while (current != null && current.getKey() <= key) {
            if (current.getKey() == key) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    // -------------------------------------------------------------
    public void displayList() {
        System.out.print("List (first-->last): ");
        Link current = first;
        while (current != null) {
            current.displayLink();
            current = current.getNext();
        }
        System.out.println();
    }
}


/*-------------------------------*/
class HashTableChain {

    private final SortedList[] hashArray;
    private final int arrSize;

    public HashTableChain(int size) {
        this.arrSize = size;
        hashArray = new SortedList[arrSize];
        for (int j = 0; j < arrSize; j++) {
            hashArray[j] = new SortedList();
        }
    }

    // -------------------------------------------------------------
    public void displayTable() {
        for (int j = 0; j < arrSize; j++) {
            System.out.print(j + ". ");     // Вывод номера ячейки
            hashArray[j].displayList();// Вывод списка
        }
    }

    // -------------------------------------------------------------
    public int hashFunc(int key) {
        return key % arrSize;
    }

    // -------------------------------------------------------------
    public void insert(Link theLink) {
        int key = theLink.getKey();
        int hashVal = hashFunc(key);
        hashArray[hashVal].insert(theLink);     // Вставка в позиции hashVal
    }

    // -------------------------------------------------------------
    public void delete(int key) {
        int hashVal = hashFunc(key);
        hashArray[hashVal].delete(key);
    }

    // -------------------------------------------------------------
    public Link find(int key) {
        int hashVal = hashFunc(key);
        Link theLink = hashArray[hashVal].find(key);
        return theLink;
    }
}

/**
 *
 * @author IOAdmin
 */
class HashChainApp {

    public static void main(String[] args) throws IOException {
        int aKey;
        Link aDataItem;
        int size, n, keysPerCell = 100;
        
        // Ввод размеров
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        
        // Создание таблицы
        HashTableChain theHashTable = new HashTableChain(size);
        for (int j = 0; j < n; j++) // Вставка данных
        {
            aKey = (int) (java.lang.Math.random()
                    * keysPerCell * size);
            aDataItem = new Link(aKey);
            theHashTable.insert(aDataItem);
        }

        while (true) // Взаимодействие с пользователем
        {
            System.out.print("Enter first letter of ");
            System.out.print("show, insert, delete, or find: ");
            char choice = getChar();
            switch (choice) {
                case 's':
                    theHashTable.displayTable();
                    break;
                case 'i':
                    System.out.print("Enter key value to insert: ");
                    aKey = getInt();
                    aDataItem = new Link(aKey);
                    theHashTable.insert(aDataItem);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                    theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aKey = getInt();
                    aDataItem = theHashTable.find(aKey);
                    if (aDataItem != null) {
                        System.out.println("Found " + aKey);
                    } else {
                        System.out.println("Could not find " + aKey);
                    }
                    break;
                default:
                    System.out.print("Invalid entry\n");
            }
        }
    }

    //--------------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //--------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }

}
