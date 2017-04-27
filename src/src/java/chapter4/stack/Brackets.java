/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/***********************************************************************/
/***********************************************************************/
/************************************************************************/
class StackArr2 {
    
    private final char[] arr;
    private final int nElems;
    private int top;

    public StackArr2(int maxSize) {
        nElems = maxSize;
        arr = new char[maxSize];
        top = -1;                           // нет ни одного эл-та
    }
    
    //--------------------------------------------------------
    public void push(char value) {
        arr[++top] = value;
    }
    
    //--------------------------------------------------------
    public char pop() {
        return arr[top--];
    }
    
    //--------------------------------------------------------
    public char peek() {                // чтение эл-та вершины
        return arr[top];
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (top == -1);
    }
    
    //--------------------------------------------------------
    public boolean isFull() {
        return (top == nElems - 1);
    }
}


/***********************************************************************/
/***********************************************************************/
/************************************************************************/
class BracketChecker {
    
    private final String input;

    public BracketChecker(String in) {
        this.input = in;
    }
    
    public void check() {
        int sizeInput = input.length();
        StackArr2 stack = new StackArr2(sizeInput);
        
        for (int i = 0; i < sizeInput; i++) {               // начинаем парсить строку
            char ch = input.charAt(i);                      // парсим по символу
            switch (ch) {
                case '{':                                   // Открывающие скобки
                case '[':
                case '(':
                {
                    stack.push(ch);                         // Добавляем в стэк
                    break;
                }
                case '}':                                   // парсим закрывающуюся скобку
                case ']':
                case ')': 
                {
                    if (!stack.isEmpty()) {                 // если стэк не пуст
                        char pop = stack.pop();             // берем открывающую скобку
                        if (                                // проверяем на ошибки пропущенных скобок
                                (ch == '}' && pop != '{') 
                                ||
                                (ch == ']' && pop != '[')
                                ||
                                (ch == ')' && pop != '(')
                            ) {
                            // выводим что пропущено и на какой позиции
                            System.out.println("Invalid argument: " + ch + " at " + i); 
                        }
                    // если стэк пуст, значит открытытой скобки не было 
                    // или такой открытой скобки, как закрытая нет
                    } else {            
                        System.out.println("Invalid argument: " + ch + " at " + i);
                    }
                    break;
                }
                default: break;     // иначе пропускаем, любые другие символы
            }
        }
        // если открытая скобка есть а закрытой не нашлось
        if (!stack.isEmpty()) {
            System.out.println("Error: missing right delimeter");
        }
    }
}


/***********************************************************************/
/***********************************************************************/
/************************************************************************/
class BracketsApp {
    public static void main(String[] args) throws IOException {
        String input;
        BracketChecker bc;
        
        while (true) {
            System.out.print("Enter string for parse: ");
            System.out.flush();
            input = getString();
            if (input.equals("")) {
                break;
            }
            bc = new BracketChecker(input);
            bc.check();
        }
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }
}
