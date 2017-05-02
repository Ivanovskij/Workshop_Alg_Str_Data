/*
 * *************************************************
 * Преобразование инфиксной строки в постфиксную
 * *************************************************
 */
package src.java.chapter4.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// <editor-fold defaultstate="collapsed" desc="CLASS STACK ON ARRAY">

final class StackInfix {

    private final int maxSize;
    private int top;
    private final char[] arr;

    public StackInfix(int size) {
        maxSize = size;
        arr = new char[maxSize];
        top = -1;
    }

    //--------------------------------------------------------
    public void push(char item) {
        arr[++top] = item;
    }

    //--------------------------------------------------------
    public char pop() {
        return arr[top--];
    }

    //--------------------------------------------------------
    public char peek() {                // чтение эл-та без удаления
        return arr[top];
    }

    //--------------------------------------------------------
    public char peekN(int n) {          // чтение элеменста с индексом n без удаления
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
        System.out.println(msg);
        System.out.print("Stack (bottom-->top): ");
        for (int i = 0; i < size(); i++) {
            System.out.print( peekN(i) );
            System.out.print(' ');
        }
        System.out.println("");
    }

}
// </editor-fold>


// <editor-fold defaultstate="collapsed" desc="CLASS INFIX TO POSTFIX">
class InToPost {

    private final String in_infix;                      // входная строка в инфиксной форме
    private String out_postfix;                         // выходящая строка в постфиксной форме         
    private final StackInfix stack;                     // стэк для операций

    public InToPost(String in) {
        this.in_infix = in;
        int stackSize = in_infix.length();
        stack = new StackInfix(stackSize);
    }

    public String doTrans() {
        int lengthInInfix = in_infix.length();                  // берем длину входной строки

        out_postfix = "";
        for (int i = 0; i < lengthInInfix; i++) {               // парсим строку посимвольно
            char opThis = in_infix.charAt(i);                   // текущая операция в input_infix(in_infix) строке
            stack.display("For " + opThis + " ");               // для наглядности текущего эл-та
            
            switch (opThis) {                                   
                case '(':                                       // если открывающаяся скобка то просто кладем ее в стэк
                {
                    stack.push(opThis);
                    break;
                }
                case ')':                                       // если закрывающаяся, то вызываем функцию и парсим
                {
                    gotParen(opThis);
                    break;
                }
                case '+':                                       // если операторы (+, -) то ставим приоритет 1
                case '-':
                {
                    gotOper(opThis, 1);
                    break;
                }
                case '*':                                       // если операторы (*, /) то ставим приоритет 2
                case '/': 
                {
                    gotOper(opThis, 2);
                    break;
                }
                default:                                        // если операнд, то записываем его сразу в выходную строку
                {       
                    out_postfix = out_postfix + opThis;
                    break;
                } 
            }
        }
        
        while (!stack.isEmpty()) {                    // все что осталось в стэке записываем в выходящую
            out_postfix += stack.pop();
        }
        
        return out_postfix;                          // возвращаем выходную постфиксную строку
    }

    //--------------------------------------------------------
    // Получить(обработать) скобку
    private void gotParen(char opThis) {
        while (!stack.isEmpty()) {                  // пока стэк не пуст
            opThis = stack.pop();                   // читаем верхний эл-т
            if (opThis == '(') {                    // если в top лежит открывающаяся скобка выходим
                break;
            } else {                                // иначе записываем в выходящую строку оператор
                out_postfix += opThis;
            }
        }
    }

    //--------------------------------------------------------
    // обработать операцию
    // @param opThis - текущий символ в строке
    // @param prec1 - приоритет
    private void gotOper(char opThis, int prec1) {
        while (!stack.isEmpty()) {                              // пока стэк не пуст
            char opTop = stack.pop();                           // верхняя(top) операция в стэке
            if (opTop == '(') {                                 // елси открывающая скобка
                stack.push(opTop);                              // то закидываем ее назад в стэк
                break;                                          // выходим
            } else {
                int prec2;                                      // второй приоритет
                if (opTop == '+' || opTop == '-') {             // если в стэке в top лежит (+, -) приоритет 1
                    prec2 = 1;
                } else {                                        // иначе(в top лежит (*, /)) приоритет 2
                    prec2 = 2;
                }
                
                if (prec2 < prec1) {                            // проверяем приоритеты
                    stack.push(opTop);                          
                    break;
                } else {
                    out_postfix += opTop;
                }
            }
        }
        stack.push(opThis);                    // кладем в стэк оператор если не вышли из ф-ции ранее
    }

}
// </editor-fold>

/**
 * *************************************************
 * Преобразование инфиксной строки в постфиксную
 * ************************************************* 
 * @author IOAdmin
 */
class InfixApp {

    public static void main(String[] args) throws IOException {
        String input, output;
        InToPost inToPost;
        
        while (true) {
            System.out.print("Input infix expression: ");
            input = getString();
            inToPost = new InToPost(input);
            output = inToPost.doTrans();
            System.out.println("Out Postfix: " + output);
        }
    }

    private static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        return input;
    }
}
