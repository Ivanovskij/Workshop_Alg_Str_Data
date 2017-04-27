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
public class ClassDataPerson {

    private Person[] p;
    private int nElems;
    
    public ClassDataPerson(int maxElem) {
        p = new Person[maxElem];
        nElems = 0;
    }
    
    //--------------------------------------------------------
    public int size() {
        return nElems;
    }
    
    //--------------------------------------------------------
    public void insert(String lastName, String firstName, int age) {
        p[nElems] = new Person(lastName, firstName, age);
        nElems++;
    }
    
    //--------------------------------------------------------
    public boolean delete(String searchLName) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (p[j].getLastName().equals(searchLName)) {       // линейный поиск значения
                break;
            }
        }
        
        if (j == nElems) {
            return false;                               // значение не найдено
        } else {
            for (int i = j; i < nElems; i++) {
                p[i] = p[i + 1];                        // сдвиг последующих элементов
            }
            nElems--;
            return true;
        }
    }
    
    //--------------------------------------------------------
    public Person find(String searchLname) {
        for (int i = 0; i < nElems; i++) {
            if (p[i].getLastName().equals(searchLname)) {
                return p[i];
            }
        }
        return null;
    }
        
    //--------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.println(p[i]);
        }
    }
}
