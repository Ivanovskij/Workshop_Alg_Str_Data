/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion.practice;

/**
 * 6.1. Предположим, вы купили дешевый карманный компьютер, который не
        умеет выполнять операцию умножения — только сложение. Чтобы выйти из по-
        ложения, вы пишете рекурсивный метод mult() , который выполняет умножение
        x на y посредством сложения x с самим собой y раз. Метод получает x и y в аргу-
        ментах, и возвращает их произведение. Напишите такой метод и программу main() ,
        в которой он вызывается. Когда выполняется сложение — когда метод вызывает
        сам себя или когда он возвращает управление?
 * @author IOAdmin
 */
public class Addition {
    
    static long res = 0;
    
    public static void main(String[] args) {
        long x = 3, y = 4;
        long result = mult(x, y);
        System.out.println("result(" + x + "," + y + ") = " + result);
    }
    
    private static long mult(long x, long y) {
        if (y == 1) {
            return x;
        } else {
            res = x + mult(x, y - 1);
            return res;
        }
        //return (y == 0) ? 0 : x + mult(x, y - 1);
    }
    
}
