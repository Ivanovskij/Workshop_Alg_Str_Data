/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//-----------------------------------
// ПАРАМЕТРЫ СОХРАНЯЕМЫЕ В СТЭКЕ
//-----------------------------------
class Params {
    
    private final int n;
    private final int returnAddress;

    public Params(int n, int returnAddress) {
        this.n = n;
        this.returnAddress = returnAddress;
    }

    public int getN() {
        return n;
    }

    public int getReturnAddress() {
        return returnAddress;
    }

    @Override
    public String toString() {
        return "(" + n + ", " + returnAddress + ')';
    }
}

//-----------------------------------
// СТЭК ДЛЯ КЛАССА PARAMS
//-----------------------------------
class Stack {
    
    private final int maxSize;
    private final Params[] arr;
    private int top;

    public Stack(int size) {
        maxSize = size;
        arr = new Params[size];
        top = -1;
    }
    
    
    //-----------------------------------------------------------
    public void push(Params param) {
        arr[++top] = param;
    }
    
    //-----------------------------------------------------------
    public Params pop() {
        return arr[top--];
    }
    
    //-----------------------------------------------------------
    public Params peek() {
        return arr[top];
    }
    
    //-----------------------------------------------------------
    private Params peekN(int n) {
        return arr[n];
    }
    
    //-----------------------------------------------------------
    public boolean isEmpty() {
        return (top == 0);
    }
    
    //-----------------------------------------------------------
    public int size() {
        return top;
    }
    
    //-----------------------------------------------------------
    public void displayStack() {
        System.out.print("Stack: ");
        for (int i = 0; i < size() + 1; i++) {
            System.out.print( peekN(i) );
        }
        System.out.println();
    }
   
}



/**
 * Программа, которая считает сумму треугольников
 * С использованием импровизированного стэка
 * возврата параметров и аргументов функции 
 * 
 * @author IOAdmin
 */
class StackTriangleApp {
    
    static int theNumber;
    static int theAnswer;
    static Stack theStack;
    static int codePart;
    static Params theseParams;
    
    //-----------------------------------------------------------
    public static void main(String[] args) throws IOException {
        System.out.print("Enter a number: ");
        theNumber = getInt();
        recTriangle();
        System.out.println("Triangle = " + theAnswer);
    }

    //-----------------------------------------------------------
    private static void recTriangle() {
        int sizeStack = 10000;
        theStack = new Stack(sizeStack);
        codePart = 1;
        while (step() == false) {       // вызывать, пока step не вернет true
            // NOP                      // просто задержка, ничего не делаем
        }
    }

    //-----------------------------------------------------------
    private static boolean step() {
        switch (codePart) {
            case 1:     // исходный вызов
            {   
                display("1");
                theseParams = new Params(theNumber, 6);
                theStack.push(theseParams);
                codePart = 2;       
                break;
            }
            case 2:     // вход в метод
            {   
                display("2");
                theseParams = theStack.peek();
                if (theseParams.getN() == 1) {  // Проверка
                    theAnswer = 1;
                    codePart = 5;           // Выход
                } else {
                    codePart = 3;           // Рекурсивный вызов
                }
                break;
            }
            case 3:     // вызов метода
            {
                display("3");
                Params newParams = new Params(theseParams.getN() - 1, 4);
                theStack.push(newParams);
                codePart = 2;   // вход в метод
                break;
            }
            case 4:         // Вычисление
            {
                display("4");
                theseParams = theStack.peek();
                theAnswer = theAnswer + theseParams.getN();
                codePart = 5;
                break;
            }
            case 5:         // Выход из метода
            {
                display("5");
                theseParams = theStack.peek();
                codePart = theseParams.getReturnAddress();      // (4 или 6)
                theStack.pop();
                break;
            }
            case 6:         // Точка возврата
            {
                display("6");
                return true;    
            }
        }
        return false;
    }
    
    //-----------------------------------------------------------
    private static void display(String num_case) {
        System.out.print("case " + num_case + ". theAnswer=" + theAnswer);
        System.out.print(" ");
        theStack.displayStack();
    }
    
    //-----------------------------------------------------------
    private static int getInt() throws IOException {
        String num = getString();
        return Integer.parseInt(num);
    }

    //-----------------------------------------------------------
    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }
}
