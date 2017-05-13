/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion;

/**
 *
 * @author IOAdmin
 */
public class Factorial {
    
    public static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
    
    public static void main(String[] args) {
        System.out.println(factorial(5));
    }
}
