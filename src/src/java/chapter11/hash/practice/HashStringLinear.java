/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter11.hash.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/******************************/
class DataItemString {
    
    private final String sData;

    public DataItemString(String sData) {
        this.sData = sData;
    }

    public String getKey() {
        return sData;
    }
}

/******************************/
class HashTableString {
    
    private DataItemString[] hashArray;
    private final int arrSize;

    public HashTableString(int size) {
        this.arrSize = size;
        hashArray = new DataItemString[size];
    }
    
    // -------------------------------------------------------------
    public void displayTable() {
        System.out.println("Table: ");
        for (int j = 0; j < arrSize; j++) {
            if (hashArray[j] != null) {
                System.out.print(hashArray[j].getKey() + " ");
            } else {
                System.out.print("** ");
            }
        }
        System.out.println();
    }
    
    // -------------------------------------------------------------
    /*
        Формула Горнера позволяет включить оператор % в каждый шаг вычисления.
        В результате мы получим такой же результат, как при однократном применении
        оператора в конце, но избежим промежуточного переполнения. (При этом в цикле
        добавляется новая операция.)
    */
    // 27 - ПОТОМУ ЧТО ПРЕДПОЛАГАЕТСЯ, 
    // ЧТО ТОЛЬКО НИЖНИЙ РЕГИСТР АНГ СИМВОЛОВ + ПРОБЕЛ
    public int hashFunc(String key) {
        int hashVal = 0;
        for (int j = 0; j < key.length(); j++) {
            // получние кода символа
            // код символа равен:
            // a - 1, b - 2, c - 3, d - 4 ....
            int letter = key.charAt(j) - 96;    
            hashVal = (hashVal * 27 + letter) % arrSize;
        }
        return hashVal;
    }
    
    // -------------------------------------------------------------
    // Если массив будет заполнен
    // Произойдет зацикливание
    // Нужно делать rehash
    public void insert(String key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            ++hashVal;
            hashVal %= arrSize;
        }
        
        DataItemString item = new DataItemString(key);
        hashArray[hashVal] = item;
    }
    
    // -------------------------------------------------------------
    public boolean find(String key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {    // пока есть эл-ты
            if (hashArray[hashVal].getKey().equals(key) ) { // если нашли
                return true;
            }
            ++hashVal;
            hashVal %= arrSize;
        }
        return false;
    }
}


/**
 * 11.2. Реализуйте хеш-таблицу с линейным пробированием, в которой хранят-
        ся строки. Вам понадобится хеш-функция, преобразующая строку в индекс; см.
        раздел «Хеширование строк» этой главы. Предполагается, что строки записаны
        символами нижнего регистра, поэтому 26 символов английского алфавита должно
        быть достаточно.
    * ПРЕДПОЛАГАЕТСЯ, 
    * ЧТО ТОЛЬКО НИЖНИЙ РЕГИСТР АНГ СИМВОЛОВ + ПРОБЕЛ
    * В ИТОГЕ 27 СИМВОЛОВ
 * @author IOAdmin
 */
class HashStringLinearApp {
    
    public static void main(String[] args) throws IOException {
        int size;
        String aString;

        System.out.print("Enter size of hash table: ");
        size = getInt();
        
        HashTableString theHashTable = new HashTableString(size);
        
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
                    aString = getString();
                    theHashTable.insert(aString);
                    break;
                case 'd':
                    System.out.print("Enter key value to delete: ");
                    aString = getString();
                    //theHashTable.delete(aKey);
                    break;
                case 'f':
                    System.out.print("Enter key value to find: ");
                    aString = getString();
                    double start = System.nanoTime();
                    if (theHashTable.find(aString)) {
                        System.out.println("Found " + aString);
                    } else {
                        System.out.println("Could not find " + aString);
                    }
                    System.out.println("TIME FOUNDING: " + ((System.nanoTime() - start) / 1000000000));
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
