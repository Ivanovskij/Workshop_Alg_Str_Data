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
public class HighArrayApp {

    private static final int MAX_ELEMENT = 20;

    public static void main(String[] args) {
        // создание объекта класса и добавление в его элементов 
        HighArray highArr = new HighArray(MAX_ELEMENT);
        for (int i = 0; i < 10; i++) {
            highArr.insert(i);
        }

        //------ Работы с массивом
        // просмотр элементов
        highArr.display();

        // Удаление значений из массива
        highArr.delete(7);
        highArr.delete(5);
        highArr.display();

        // Поиск по ключу
        int key = 5;
        if (highArr.find(key)) {
            System.out.println("Key[" + key + "] - found");
        } else {
            System.out.println("Key[" + key + "] - not found");
        }

        // поиск и удаление макс элемента
        long maxElem = highArr.getMax();
        if (highArr.removeMax()) {
            System.out.println("MaxElem[" + maxElem + "] has removed");
        } else {
            System.out.println("MaxElem[" + maxElem + "] hasn't removed or not found");
        }
        highArr.display();

        // сортировка на основе removeMax
        System.out.println("Sorting arr:");
//        long[] sortArr = highArr.sort();
        highArr.sort();
        highArr.display();
        
        // удаляем дубли
        System.out.println("With dups:");
        highArr.insert(1);
        highArr.insert(2);
        highArr.insert(8);
        highArr.insert(8);
        highArr.insert(8);
        highArr.insert(8);
        highArr.insert(8);
        highArr.insert(8);
        highArr.insert(18);
        
        highArr.display();
        
        System.out.println("No dups:");
        highArr.noDups();
        highArr.display();
        

    }

}
