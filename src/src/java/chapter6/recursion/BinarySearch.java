/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion;

/**
 *
 * @author IOAdmin
 */
public class BinarySearch {

    private final int[] arr;
    private int nElems;

    public BinarySearch(int max) {
        arr = new int[max];
        nElems = 0;
    }

    //-----------------------------------------------------------
    public int find(long searchKey) {
        return find_recursion(searchKey, 0, nElems - 1);
    }

    //-----------------------------------------------------------
    private int find_recursion(long key, int lowerBound,
                                         int upperBound) {
        int curIn;

        curIn = (lowerBound + upperBound) / 2;
        if (arr[curIn] == key) {
            return curIn;
        } else if (lowerBound > upperBound) {
            return nElems;
        } else { 
            if (key > arr[curIn]) {
                return find_recursion(key, curIn + 1, upperBound);
            } else {
                return find_recursion(key, lowerBound, curIn - 1);
            }
        }
    }

    //-----------------------------------------------------------
    public void insert(int value)       // Сохранение элемента в массиве
    {
        int j;
        for (j = 0; j < nElems; j++)        // Определение позиции
        {
            if (arr[j] > value)         // (линейный поиск)
            {
                break;
            }
        }
        for (int k = nElems; k > j; k--)        // Перемещение элементов
        {
            arr[k] = arr[k - 1];            // с большим значением ключа
        }
        arr[j] = value;             // Вставка
        nElems++;               // Увеличение размера
    }
    
    //-----------------------------------------------------------
    public void display()       // Вывод содержимого массива
    {
        for (int j = 0; j < nElems; j++)        // Для каждого элемента
        {
            System.out.print(arr[j] + " ");       // Вывод текущего элемента
        }
        System.out.println();
    }
    
    //-----------------------------------------------------------
    public int size() {
        return nElems;
    }
    

    public static void main(String[] args) {
        int maxSize = 100;
        BinarySearch bs = new BinarySearch(maxSize);

        for (int i = 0; i < 100; i++) {
            bs.insert(i);
        }
        
        bs.display();
        
        int searchKey = 27;         // Поиск элемента
        if (bs.find(searchKey) != bs.size()) {
            System.out.println("Elem[" + searchKey + "] found!");
        } else {
            System.out.println("Elem[" + searchKey + "] not found!");
        }
    }
}
