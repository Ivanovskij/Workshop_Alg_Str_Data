/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion;

import java.util.Arrays;

/**
 * СОРТИРОВКА СЛИЯНИЕМ
 * @author IOAdmin
 */
class MergeApp {
    
    public static void main(String[] args) {
        int[] arrA = { 0, 5, 6, 10 };
        int[] arrB = { 1, 4, 5, 7, 8 };
        int[] arrC = new int[arrA.length + arrB.length];
        
        merge(arrA, arrA.length, arrB, arrB.length, arrC);
        display(arrC);
    }
    
    
    //-----------------------------------------------------------
    private static void merge(int[] arrA, int sizeA, 
            int[] arrB, int sizeB, 
            int[] arrC) 
    {
        int aDex = 0, bDex = 0, cDex = 0;
        
        while (aDex < sizeA && bDex < sizeB) {      // merge двух массивов в один
            if (arrA[aDex] > arrB[bDex]) {          // в каком массиве больше эл-т
                arrC[cDex++] = arrB[bDex++];
            } else {
                arrC[cDex++] = arrA[aDex++];
            }
        }
        
        while (aDex < sizeA) {                      // если остались эл-ты в первом массиве
            arrC[cDex++] = arrA[aDex++];
        }
        
        while (bDex < sizeB) {                      // если остались эл-ты во втором массиве
            arrC[cDex++] = arrB[bDex++];
        }
    }

    //-----------------------------------------------------------
    private static void display(int[] arrC) {
        System.out.println(Arrays.toString(arrC));
    }
    
}
