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
final class StackArr1 {
    
    private final char[] arr;
    private final int nElems;
    private int top;

    public StackArr1(int maxSize) {
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
class Reverse {

    private final String input;
    private String output;
    
    public Reverse(String input) {
        this.input = input;
    }
    
    public String doRev() {
        int sizeInput = input.length();
        
        StackArr1 stackArr1 = new StackArr1(sizeInput);
        for (int i = 0; i < sizeInput; i++) {
            char ch = input.charAt(i);
            stackArr1.push(ch);
        }
        
        output = "";
        while (!stackArr1.isEmpty()) {
            char ch = stackArr1.pop();
            output = output + ch;
        }
        return output;
    }
}

/***********************************************************************/
/***********************************************************************/
/************************************************************************/
class ReverseStringApp {
    public static void main(String[] args) throws IOException {
        String input, output;
        while (true) {
            System.out.print("Enter a string: ");
            System.out.flush();
            input = getString();
            if ( input.equals("") ) { break; }
            
            Reverse reverse = new Reverse(input);
            output = reverse.doRev();
            System.out.println("Reversed: " + output);
        }
        
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}