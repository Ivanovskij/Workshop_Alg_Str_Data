/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author IOAdmin
 */

class SelectArr {
    
    private long[] arr;
    private int nElems;

    public SelectArr(int maxElem) {
        arr = new long[maxElem];
        nElems = 0;
    }

    //--------------------------------------------------------
    public void insert(long value) {
        arr[nElems] = value;
        nElems++;
    }
    
    //--------------------------------------------------------
    // Сортировка выборкой
    // Выбираем минимальный элемент(запоминаем его индекс) из всего массива
    // и меняем его с левыми крайними(индексами) позициями
    // таким образом сортируем весь масив
    public void selectSort() {
        int min;
        
        for (int i = 0; i < nElems; i++) {
            min = i;                                            // минимальный эл-т[индекс] берем всегда слева
            // начинаем с позииции i
            // так как левая сторона до i уже отсортирована
            for (int j = i + 1; j < nElems; j++) {              
                if (arr[min] > arr[j]) { 
                    min = j;                                   // нашли новый минимальный элемент[индекс]
                }
            }
            swap(i, min);                                       // поменяли минимальный[индекс] элемент с левым эл-том[индексом]
        }
    }
    
    private void swap(int index1, int index2) {
        long tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
    
    //--------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
    
}

/*************************************************************************/
/*************************************************************************/
/*************************************************************************/
class SelectSortApp {
    
    public static void main(String[] args) {
        int maxElem = 50000;
        SelectArr selectArr = new SelectArr(maxElem);
//        selectArr.insert(100);
//        selectArr.insert(25);
//        selectArr.insert(1000);
//        selectArr.insert(4000);
//        selectArr.insert(130);
//        selectArr.insert(125);
//        selectArr.insert(1300);
//        selectArr.insert(42000);
//        selectArr.display();

        for (int i = 0; i < maxElem; i++) {
            long r = ThreadLocalRandom.current().nextLong(10,100);
            selectArr.insert(r);
        }
        
        double start = System.nanoTime();
        System.out.println("SelectSort:");
        selectArr.selectSort();
        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000));
        
        selectArr.display();
    }
    
}
    

