/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter4.stack;

/*
    STACK - НА ОСНОВЕ МАССИВА
    ПРЕПОЛАГАЕТСЯ ЧТО СТЭК НЕ БОЛЬШЕ 10 ЭЛЕМЕНТОВ
*/

class StackArr {
    
    private final long[] arr;
    private final int nElems;
    private int top;

    public StackArr(int maxSize) {
        nElems = maxSize;
        arr = new long[maxSize];
        top = -1;                           // нет ни одного эл-та
    }
    
    //--------------------------------------------------------
    public void push(long value) {
        arr[++top] = value;
    }
    
    //--------------------------------------------------------
    /*
        Метод pop() возвращает значение, находящееся на вершине стека, после чего
        уменьшает top . В результате элемент, находящийся на вершине стека, фактически
        удаляется; он становится недоступным, хотя само значение остается в массиве (до
        тех пор, пока в ячейку не будет занесен другой элемент).
    */
    public long pop() {
        return arr[top--];
    }
    
    //--------------------------------------------------------
    public long peek() {                // чтение эл-та вершины
        return arr[top];
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (top == -1);
    }
    
    //--------------------------------------------------------
    public boolean isFull() {                   // если голова равна макс кол-ву эл-тов
        return (top == nElems-1);
    }
}


/***********************************************************************/
/***********************************************************************/
/************************************************************************/
class StackArrApp {
    public static void main(String[] args) {
        int stackSize = 10;
        StackArr stackArr = new StackArr(stackSize);
        
        for (int i = 0; i < stackSize; i++) {
            stackArr.push(i);
        }
        
        while (!stackArr.isEmpty()) {
            System.out.print(stackArr.pop() + " ");
        }
        System.out.println();
    }
}
