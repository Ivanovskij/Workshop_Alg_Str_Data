/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion.practice;

/**
 * 6.3. Реализуйте рекурсивное возведение числа в степень (см. раздел «Возведение
        числа в степень» этой главы). Напишите рекурсивную функцию power() и метод
        main() для ее тестирования.
 * @author IOAdmin
 */
public class Power {
    
    static long result = 0;
    
    public static void main(String[] args) {
        long x = 3, y = 3;
        long res = power(x, y);
        System.out.println("Result = " + res);
    }
    
    private static long power(long x, long y) {
        System.out.println("x=" + x + ", y=" + y);
        if (y == 1) {
            System.out.println("Returning " + x + ", x=" + x + ", y=" + y);
            return x;
        } else {
            result = power(x * x, y / 2);
           /*   Модификация алгоритма для
                нечетных y выглядит так: в рекурсии используется только целочисленное деление,
                а при делении y на 2 остаток игнорируется. Однако в процессе возврата каждый
                раз, когда y является нечетным числом, выполняется дополнительное умножение
                на x.
            */
            if (y % 2 != 0) {
                result *= x;
            }
            System.out.println("Returning " + result + ", x=" + x + ", y=" + y);
            return result;
        } 
    }
}
