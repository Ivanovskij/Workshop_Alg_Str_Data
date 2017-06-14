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
class DataItemLinear {

    private final int iData;

    public DataItemLinear(int iData) {
        this.iData = iData;
    }

    public int getiData() {
        return iData;
    }
}

/*-------------------------------*/
class HashTableLinear {

    private DataItemLinear[] hashArray;   // Массив ячеек хеш-таблицы
    private final int arrSize;
    private final DataItemLinear nonItem;       // Ключ удаленного элемента -1

    public HashTableLinear(int size) {
        this.arrSize = size;
        hashArray = new DataItemLinear[arrSize];
        nonItem = new DataItemLinear(-1);
    }

    // -------------------------------------------------------------
    public void displayTable() {
        System.out.println("Table: ");
        for (int i = 0; i < arrSize; i++) {
            if (hashArray[i] != null) {
                System.out.print(hashArray[i].getiData() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }

    // -------------------------------------------------------------
    public int hashFunc(int key) {
        return key % arrSize;   // Хеш-функция
    }

    // -------------------------------------------------------------
    // Вставка элемента данных
    // (Метод предполагает, что таблица не заполнена)
    public void insert(DataItemLinear item) {
        int key = item.getiData();      // Получение ключа
        int hashVal = hashFunc(key);    // Хеширование ключа

        while (hashArray[hashVal] != null
                && // Пока не будет найдена
                hashArray[hashVal] != nonItem) {  // пустая ячейка или -1,
            ++hashVal;              // Переход к следующей ячейке
            // При достижении конца таблицы
            // происходит возврат к началу
            hashVal %= arrSize;
        }
        hashArray[hashVal] = item;
    }

    // -------------------------------------------------------------
    // Удаление элемента данных
    public DataItemLinear delete(int key) {
        int hashVal = hashFunc(key);        // Хеширование ключа

        // Пока не будет найдена
        // пустая ячейка
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getiData() == key) { // Ключ найден?
                DataItemLinear tmp = hashArray[hashVal];  // Временное сохранение
                hashArray[hashVal] = nonItem;       // Удаление элемента
                return tmp;
            }
            ++hashVal;      // Переход к следующей ячейке
            hashVal %= arrSize;
        }
        return null;    // Элемент не найден
    }

    // -------------------------------------------------------------
    // Поиск элемента с заданным ключом
    // (Метод предполагает, что таблица не заполнена)
    public DataItemLinear find(int key) {
        int hashVal = hashFunc(key);    // Хеширование ключа
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getiData() == key) {
                return hashArray[hashVal];      // Да, вернуть элемент
            }
            ++hashVal;  // Переход к следующей ячейке
            hashVal %= arrSize;
        }
        return null;    // Элемент не найден
    }
}

/**
 *
 * @author IOAdmin
 */
class HashLinearApp {

    public static void main(String[] args) throws IOException {
        DataItemLinear aDataItem;
        int aKey, size, n, keysPerCell;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;

        HashTableLinear theHashTable = new HashTableLinear(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (Math.random() * keysPerCell * size);
            aDataItem = new DataItemLinear(aKey);
            theHashTable.insert(aDataItem);
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
                    aDataItem = new DataItemLinear(aKey);
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
