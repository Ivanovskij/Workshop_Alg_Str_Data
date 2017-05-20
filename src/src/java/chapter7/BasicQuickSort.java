/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter7;

class BasicQuickSort {
    
    private final long[] arr;
    private int nElems;

    public BasicQuickSort(int size) {
        this.arr = new long[size];
        nElems = 0;
    }
    
    //-----------------------------------------------------------
    public void insert(long value) {
        arr[nElems++] = value;
    }
    
    //-----------------------------------------------------------
    public void LowQuickSort() {
        LowReqQuickSort(0, nElems - 1);
    }
    
    //-----------------------------------------------------------
    private void LowReqQuickSort(int left, int right) {
        if (right - left <= 0) {    // Если размер <= 0
            return;                 // массив отсортирован
        } else {            // Для размера 2 и более
            long supportingElem = arr[right];       // Крайний правый элемент
                                                    // Разбиение диапазонa
            int partition = partitionIt(left, right, supportingElem);
            LowReqQuickSort(left, partition - 1);       // Сортировка левой части
            LowReqQuickSort(partition + 1, right);      // Сортировка правой части
        }
    }
    
    //-----------------------------------------------------------
    private int partitionIt(int left, int right, long supportElem) {
        int leftPtr = left - 1;     // Левая граница (после ++)
        int rightPtr = right + 1;   // Правая граница-1 (после --)
        
        while (true) {  
                            // Поиск большего элемента
            while (arr[++leftPtr] < supportElem) {
                // NOP
            }
                            // Поиск меньшего элемента
            while (rightPtr > 0 && arr[--rightPtr] > supportElem) {
                // NOP
            }
            
            if (leftPtr >= rightPtr) {  // Если указатели сошлись,
                break;                  // разбиение закончено.
            } else {                
                swap(leftPtr, rightPtr); // поменять элементы местами.
            }
        }
        swap(leftPtr, right);           // Перестановка опорного элемента
        return leftPtr;                 // Возврат позиции опорного элемента
    }
    
    //-----------------------------------------------------------
    private void swap(int dex1, int dex2) {
        long tmp = arr[dex1];
        arr[dex1] = arr[dex2];
        arr[dex2] = tmp;
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
class BasicQuickSortApp {
    public static void main(String[] args) {
        int maxSize = 16;       // Размер массива
        BasicQuickSort arr;
        arr = new BasicQuickSort(maxSize);      // Создание массива
        
        for(int j=0; j < maxSize; j++) { 
            long n = (int)(java.lang.Math.random()*99);
            arr.insert(n);
        }
        
        arr.display();      // Вывод элементов
        
        double start = System.nanoTime();
        arr.LowQuickSort();     // Быстрая сортировка
        System.out.println("TIME = " + ((System.nanoTime() - start) / 1000000000));
        
        arr.display();      // Повторный вывод элементов
    }
}
