/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter7;

import java.util.concurrent.ThreadLocalRandom;


class ShellArray {
    
    private final long[] arr;
    private int nElems;

    public ShellArray(int size) {
        this.arr = new long[size];
        nElems = 0;
    }
    
    //-----------------------------------------------------------
    public void insert(long value) {
        arr[nElems++] = value;
    }
    
    //-----------------------------------------------------------
    public void shellSort() {
        int outer, inner;
        int h = 1;
        long next;
                        // вычисление исходного значения h
                        // делим на 3, чтобы
                        // сделать хотя бы 3 перестановки в массиве
        while (h <= nElems / 3) {
            h = h * 3 + 1;
        }
        
        while (h > 0) {
                                // сортируем последовательностью
            for (outer = h; outer < nElems; outer++) {
                next = arr[outer];
                inner = outer;
                               // h-сортировка
                while (inner > h - 1 && arr[inner - h] >= next) {
                    arr[inner] = arr[inner - h];
                    inner -= h;
                }
                arr[inner] = next;
            }
            h = (h - 1) / 3;        // уменьшаем h
        }
        
    }
    
    //-----------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}


/**
 *
 * @author IOAdmin
 */
class ShellSortApp {
    public static void main(String[] args) {
        int maxSize = 550000;   // Размер массива
        ShellArray shArr = new ShellArray(maxSize); // Создание массива
        
        for(int j=0; j < maxSize; j++)  {
            long n = ThreadLocalRandom.current().nextLong(10,100);
            shArr.insert(n);
        }
        
        shArr.display(); // Вывод несортированного массива
        
        double start = System.nanoTime();
        shArr.shellSort(); // Сортировка массива по алгоритму Шелла
        System.out.println("TIME = " + ((System.nanoTime() - start) / 1000000000));
        
        shArr.display(); // Вывод отсортированного массива
    }
}
