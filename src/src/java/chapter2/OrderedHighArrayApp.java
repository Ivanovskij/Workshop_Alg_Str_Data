/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter2;

/**
 *
 * @author IOAdmin
 */
public class OrderedHighArrayApp {
    
    public static void main(String[] args) {
        OrderedHighArray ordHighArr = new OrderedHighArray(10);
        ordHighArr.insert(0);
        ordHighArr.insert(5);
        ordHighArr.insert(6);
        ordHighArr.insert(6);
        ordHighArr.insert(8);
        ordHighArr.insert(1);
        ordHighArr.insert(3);
        
        // выводим элементы
        ordHighArr.display();
        
        // поиск элементов
        long searchKey = 5;
        int index = ordHighArr.find(searchKey);
        if (index != ordHighArr.size()) {
            System.out.println("key[" + searchKey + "] found");
        } else {
            System.out.println("key[" + searchKey + "] not found");
        }
        
        // удаляем элементы
        ordHighArr.delete(10);
        ordHighArr.delete(5);
        ordHighArr.display();
        
        System.out.println(ordHighArr.log2r(128));
        
        // merge двух упорядоченных массива
        merge(ordHighArr);
    }
    
    public static void merge(OrderedHighArray orderedHighArray) {
        long[] arr1 = { 0, 1, 2, 3, 3, 200 };
        long[] arr2 = { 0, 5, 110 };
        
        long[] res = orderedHighArray.merge(arr1, arr2);
        for (Long elem : res) {
            System.out.print(elem + " ");
        }
    }
    
}
