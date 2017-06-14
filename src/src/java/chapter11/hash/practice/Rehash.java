/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter11.hash.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*-------------------------------*/
class DataItemRehash {

    private final int iData;

    public DataItemRehash(int iData) {
        this.iData = iData;
    }

    public int getKey() {
        return iData;
    }
}

class HashTableRehash {
    
    private DataItemRehash[] hashArray;
    private int arrSize;
    private int nElems;
    
    public HashTableRehash(int size) {
        this.arrSize = size;
        hashArray = new DataItemRehash[arrSize];
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
    public int hashFunc(int key) {
        return key % arrSize;
    }
    
    // -------------------------------------------------------------
    public void insert(int key) {
        int hashVal = hashFunc(key);    // Хеширование ключа

        while (hashArray[hashVal] != null) {  // пустая ячейка или -1,
            ++hashVal;              // Переход к следующей ячейке
            // При достижении конца таблицы
            // происходит возврат к началу
            hashVal %= arrSize;
        }
        
        DataItemRehash item = new DataItemRehash(key);
        hashArray[hashVal] = item;
        
        nElems++;
        if (nElems >= arrSize) {
            rehash();
        }
    }
    
    // -------------------------------------------------------------
    // Поиск элемента с заданным ключом
    public DataItemRehash find(int key) {
        int hashVal = hashFunc(key);    // Хеширование ключа
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                return hashArray[hashVal];      // Да, вернуть элемент
            }
            ++hashVal;  // Переход к следующей ячейке
            hashVal %= arrSize;
        }
        return null;    // Элемент не найден
    }
    
    // -------------------------------------------------------------
    /*
        Расширенный массив обычно вдвое больше исходного массива. 
        Вообще говоря, поскольку размер массива должен быть 
        простым числом, размер нового массива должен возрасти 
        немного более, чем вдвое. Вычисление нового размера массива
        является частью процесса перехеширования.
    */
    public int rehash() {
        /* Изменяем размеры */
        int oldSize = arrSize;
        int newSize = getPrime(arrSize);
        // меняем на новый размер, 
        // чтобы хеш-функция делала новый хэш
        arrSize = newSize;  
        /***************************/
        
        DataItemRehash[] newHashArray = new DataItemRehash[newSize];
        int hashVal, key;
        DataItemRehash item;
        
        for (int j = 0; j < oldSize; j++) {
            key = hashArray[j].getKey();
            hashVal = hashFunc(key);
            item = new DataItemRehash(key);
            newHashArray[hashVal] = item;
        }
        
        hashArray = newHashArray;
        
        System.out.println("old size: " + oldSize);
        System.out.println("new size: " + newSize);
        return 0;
    }
    
    // -------------------------------------------------------------
    /*
        Ниже приведена пара вспомогательных методов, 
        которые пригодятся для вычисления нового размера массива 
        (или исходного размера массива, если вы не уверены в том, 
        что пользователь введет простое число). 
        Алгоритм начинает с заданного размера и ищет первое простое число, 
        большее заданного. Метод getPrime() находит
        следующее простое число, большее своего аргумента. 
        Для проверки каждого числа,
        превышающего заданный размер, вызывается метод isPrime() 
    */
    private int getPrime(int size) {        // Возвращает первое простое число > size
        for (int j = size + 1; true; j++) { // Для всех j > size
            if (isPrime(j)) {               // Число j простое?
                return j;                   // Да, вернуть ег
            }
        }
    }
    // -------------------------------------------------------------
    private boolean isPrime(int n) {        // Число n простое?
        for (int j = 2; (j*j <= n); j++) {  // Для всех j
            if (n % j == 0) {               // Делится на j без остатка?
                return false;               // Да, число не простое
            }
        }
        return true;                        // Нет, число простое
    }
}


/**
 *
 * @author IOAdmin
 */
class RehashApp {
    
    public static void main(String[] args) throws IOException {
        DataItemRehash aDataItem;
        int aKey, size, n, keysPerCell;
        
        System.out.print("Enter size of hash table: ");
        size = getInt();
        System.out.print("Enter initial number of items: ");
        n = getInt();
        keysPerCell = 10;
        
        HashTableRehash theHashTable = new HashTableRehash(size);

        for (int j = 0; j < n; j++) {
            aKey = (int) (Math.random() * keysPerCell * size);
            theHashTable.insert(aKey);
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
                    theHashTable.insert(aKey);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aKey = getInt();
                   // theHashTable.delete(aKey);
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
