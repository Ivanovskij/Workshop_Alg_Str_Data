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
class DataItemDouble {

    private final int iData;

    public DataItemDouble(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }
}

/*-------------------------------*/
class HashTableDouble {

    private DataItemDouble[] hashArray;   // Массив ячеек хеш-таблицы
    private final int arrSize;
    private final DataItemDouble nonItem;       // Ключ удаленного элемента -1

    private static final int HASH_RANDOM_VALUE = 5;

    public HashTableDouble(int size) {
        this.arrSize = size;
        hashArray = new DataItemDouble[arrSize];
        nonItem = new DataItemDouble(-1);
    }

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.println("Table: ");
        for (int i = 0; i < arrSize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    public int hashfunc1(int key) {
        return key % arrSize;
    }

    // -------------------------------------------------------------
    public int hashfunc2(int key) {
        // Возвращаемое значение отлично от нуля, меньше размера массива,
        // функция отлична от хеш-функции 1
        // Размер массива должен быть простым по отношению к 5, 4, 3 и 2
        return HASH_RANDOM_VALUE - (key % HASH_RANDOM_VALUE);
    }

    // -------------------------------------------------------------
    // Вставка элемента данных
    public void insert(int key, DataItemDouble item) {
        int hashVal = hashfunc1(key);   // Хеширование ключа
        int stepSize = hashfunc2(key);  // Вычисление смещения

        while (hashArray[hashVal] != null
                && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize;
            hashVal %= arrSize;
        }
        hashArray[hashVal] = item;
    }

    // -------------------------------------------------------------
    // Удаление элемента данных
    public DataItemDouble delete(int key) {
        int hashVal = hashfunc1(key);        // Хеширование ключа
        int stepSize = hashfunc2(key);      // Вычисление смещения

        // Пока не будет найдена
        // пустая ячейка
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) { // Ключ найден?
                DataItemDouble tmp = hashArray[hashVal];  // Временное сохранение
                hashArray[hashVal] = nonItem;       // Удаление элемента
                return tmp;
            }
            hashVal += stepSize;    // Прибавление смещения
            hashVal %= arrSize;     // Возврат к началу
        }
        return null;    // Элемент не найден
    }

    // -------------------------------------------------------------
    // Поиск элемента с заданным ключом
    // (Метод предполагает, что таблица не заполнена)
    public DataItemDouble find(int key) {
        int hashVal = hashfunc1(key);   // Хеширование ключа
        int stepSize = hashfunc2(key);  // Вычисление смещения
        while (hashArray[hashVal] != null) { 
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];  // Да, метод возвращает элемент
            }
            hashVal += stepSize;    // Прибавление смещения
            hashVal %= arrSize;     // Возврат к началу
        }
        return null;    // Элемент не найден
    }
}

/**
 *
 * @author IOAdmin
 */
public class HashDouble {

    public static void main(String[] args) throws IOException {
        DataItemDouble aDataItem;
        int aKey, size, n, keysPerCell;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        HashTableDouble theHashTable = new HashTableDouble(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (Math.random() * keysPerCell * size);
            aDataItem = new DataItemDouble(aKey);
            theHashTable.insert(aKey, aDataItem);
        }

        while (true)
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
                    aDataItem = new DataItemDouble(aKey);
                    theHashTable.insert(aKey, aDataItem);
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
