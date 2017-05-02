/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// <editor-fold defaultstate="collapsed" desc="CLASS STACK ON ARRAY">
final class StackPostfix {

    private final int maxSize;
    private int top;
    private final int[] arr;

    public StackPostfix(int size) {
        maxSize = size;
        arr = new int[maxSize];
        top = -1;
    }

    //--------------------------------------------------------
    public void push(int item) {
        arr[++top] = item;
    }

    //--------------------------------------------------------
    public int pop() {
        return arr[top--];
    }

    //--------------------------------------------------------
    public int peek() {                // чтение эл-та без удаления
        return arr[top];
    }

    //--------------------------------------------------------
    public int peekN(int n) {          // чтение элеменста с индексом n без удаления
        return arr[n];
    }

    //--------------------------------------------------------
    public int size() {
        return top + 1;
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (top == -1);
    }

    //--------------------------------------------------------
    public void display(String msg) {               // для наглядности, что лежит в стэке
        System.out.print(msg);
        System.out.print("Stack (bottom-->top): ");
        for (int i = 0; i < size(); i++) {
            System.out.print( peekN(i) );
            System.out.print(' ');
        }
        System.out.println();
    }

}
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="CLASS PARSE POSTFIX EXPRESSION">
class ParsePostfix {
    
    private final String in_postfix;
    private final StackPostfix stack;
    
    public ParsePostfix(String in) {
        this.in_postfix = in;
        int stackSize = in.length();
        stack = new StackPostfix(stackSize);
    }
    
    public int doParse() {
        char opThis;
        int num1, num2, interAns;
        int lengthInPostfix = in_postfix.length();
        
        for (int i = 0; i < lengthInPostfix; i++) {
            opThis = in_postfix.charAt(i);
            stack.display("" + opThis + " ");
            
            if (isDigit(opThis)) {
                stack.push((int)(opThis-'0'));
            } else {
                num2 = stack.pop();
                num1 = stack.pop();
                switch(opThis) {
                    case '+': 
                    {
                        interAns = num1 + num2;
                        break;
                    }
                    case '-': 
                    {
                        interAns = num1 - num2;
                        break;
                    }
                    case '*':
                    {
                        interAns = num1 * num2;
                        break;
                    }
                    case '/':
                    {
                        interAns = num1 / num2;
                        break;
                    }
                    default:
                    {
                        interAns = 0;
                        break;
                    }
                }
                stack.push(interAns);
            }
        }
        interAns = stack.pop();
        return interAns;
    }

    private static boolean isDigit(char opThis) {
        return opThis >= '0' && opThis <= '9';
    }
}
// </editor-fold>


/**
 * Наша программа вычисляет и выводит результат постфиксного выражения.
 * Не забывайте, что операнды содержат не более одной цифры. Пример простого
 * взаимодействия с программой
 * Входная строка содержит только цифры и операторы без пробелов. Программа
 * находит числовой эквивалент выражения. Хотя операнды могут состоять только
 * из одной цифры, на результаты (в том числе и промежуточные) это ограничение
 * не распространяется.
 * @author IOAdmin
 */
class PostfixApp {
    public static void main(String[] args) throws IOException {
        String inputPostfix, outResult;
        ParsePostfix pp;
                
        while (true) {
            System.out.print("Input postfix expression: ");
            inputPostfix = getString();
            if (inputPostfix.equals("")) {
                break;
            }
            pp = new ParsePostfix(inputPostfix); 
            int result = pp.doParse();
            System.out.println("Result = " + result);
        }
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }
}
