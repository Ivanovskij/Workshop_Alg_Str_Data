/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class MergeSort {
    
    private final long[] arr;
    private int nElems;

    public MergeSort(int size) {
        arr = new long[size];
        nElems = 0;
    }
    
    
    //-----------------------------------------------------------
    public void insert(long value) {
        arr[nElems++] = value;
    }
    
    
    //-----------------------------------------------------------
    public void mergeSort() {
        long[] workSpace = new long[nElems];
        recMergeSort(workSpace, 0, nElems - 1);
    }
    
    //-----------------------------------------------------------
    private void recMergeSort(long[] workSpace, 
                               int lower_bound,
                               int upper_bound) {
        if (lower_bound == upper_bound) {               // Если только один элемент,
            return;                                     // сортировка не требуется.
        } else {        
                                                        // Поиск середины
            int middle = (lower_bound + upper_bound) / 2;
                                                        // Сортировка нижней половины
            recMergeSort(workSpace, lower_bound, middle);           
                                                        // Сортировка верхней половины
            recMergeSort(workSpace, middle + 1, upper_bound);
                                                        // слияние
            merge(workSpace, lower_bound, middle + 1, upper_bound);
        }
    }
    
    //-----------------------------------------------------------
    private void merge(long[] workSpace, int lowPtr,
                                         int highPtr, int upper_bound)
    {
        int wDex = 0;
        int lower_bound = lowPtr;
        int mid = highPtr - 1;
        int n = upper_bound - lower_bound + 1;
        
        while (lowPtr <= mid && highPtr <= upper_bound) {
            if (arr[highPtr] > arr[lowPtr]) {
                workSpace[wDex++] = arr[lowPtr++];
            } else {
                workSpace[wDex++] = arr[highPtr++];
            }
        }
        
        while (lowPtr <= mid) {
            workSpace[wDex++] = arr[lowPtr++];
        }
        
        while (highPtr <= upper_bound) {
            workSpace[wDex++] = arr[highPtr++];
        }
        
        for (int i = 0; i < n; i++) {
            arr[lower_bound + i] = workSpace[i];
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
class MergeSortApp {
    public static void main(String[] args) {
        int maxSize = 11;
        MergeSort obj = new MergeSort(maxSize);
        
        for (int i = 0; i < maxSize; i++) {
            long r = ThreadLocalRandom.current().nextLong(10,100);
            obj.insert(r);
        }
        
        //obj.display();
        double start = System.nanoTime();
        obj.mergeSort();
        System.out.println("TIME = " + ((System.nanoTime() - start) / 1000000000));
        //obj.display();
        
    }
}
