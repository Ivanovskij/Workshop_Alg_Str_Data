/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter7;

class PartitionArr {
    
    private long[] arr;
    private int nElems;

    public PartitionArr(int size) {
        this.arr = new long[size];
        nElems = 0;
    }
    
    //-----------------------------------------------------------
    public void insert(long value) {
        arr[nElems++] = value;
    }
    
    //-----------------------------------------------------------
    // supportingElem - опорный элемент
    public int partition(int leftPos, int rightPos, long supportingElem) {
        int leftPtr = leftPos - 1;      // Справа от первого элемента
        int rightPtr = rightPos + 1;    // Слева от опорного элемента
        
        while (true) {
            while (leftPtr < rightPos &&    
                    arr[++leftPtr] < supportingElem) {      // Поиск большего элемента
                // NOP;
            }
            while (rightPtr > leftPos &&    
                    arr[--rightPtr] > supportingElem) {      // Поиск меньшего элемента
                // NOP;
            }
            if (leftPtr >= rightPtr) {      // Если указатели сошлись
                break;                      // разбиение закончено
            } else {                        // В противном случае
                swap(leftPtr, rightPtr);    // поменять элементы местами
            }
        }
        return leftPtr;             // Позиция разбиения
    }
    
    //-----------------------------------------------------------
    private void swap(int dex1, int dex2) {
        long tmp = arr[dex1];
        arr[dex1] = arr[dex2];
        arr[dex2] = tmp;
    }
    
    //--------------------------------------------------------------
    public int size() {     // Получение количества элементов
        return nElems; 
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
class PartitionApp {
    
    public static void main(String[] args) {
        int maxSize = 16;
        PartitionArr pa = new PartitionArr(maxSize);
        
        for(int j=0; j< maxSize; j++) { 
            long n = (int)(java.lang.Math.random()*199);
            pa.insert(n);
        }
        
        pa.display(); // Вывод несортированного массива
        
        long supportingElem = 99;   // Опорное значение
        System.out.print("supportingElem is " + supportingElem);
        int size = pa.size();
        
        // Разбиение массива
        int partDex = pa.partition(0, size-1, supportingElem);
        System.out.println(", Partition is at index " + partDex);
        pa.display(); // Вывод массива после разбиения
    }
}
