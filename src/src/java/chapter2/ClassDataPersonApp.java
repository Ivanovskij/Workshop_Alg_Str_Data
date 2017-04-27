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
public class ClassDataPersonApp {
    
    public static void main(String[] args) {
        int maxElem = 10;
        ClassDataPerson cdp = new ClassDataPerson(maxElem);
        cdp.insert("Iva", "Oleg", 10);
        cdp.insert("Az", "Evg", 20);
        cdp.insert("Iva", "Oleg", 10);
        cdp.insert("Az", "Evg", 20);
        cdp.insert("Iva", "Oleg", 10);
        cdp.insert("Az", "Evg", 20);
        
        cdp.display();
        
        String searchLName = "Iva";
        Person findPerson = cdp.find(searchLName);
        if (findPerson != null) {
            System.out.println("Person[" + searchLName + "] found");
        } else {
            System.out.println("Person[" + searchLName + "] not found");
        }
        
        cdp.delete("Az");
        cdp.display();
    }
    
}
