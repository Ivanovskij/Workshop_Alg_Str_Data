/*
 * 4.3. Напишите реализацию стека на базе класса Deque из п. 4.2. Класс стека дол-
    жен поддерживать те же методы и возможности, что и класс StackX в программе
    stack.java (см. листинг 4.1).
 */
package src.java.chapter4.deque;


class StackOnDeque extends DequeArray {
    
    public StackOnDeque(int size) {
        super(size);
    }
    
    
    //--------------------------------------------------------
    public void push(long item) {
        super.insertLeft(item);
    }
    
    //--------------------------------------------------------
    public long pop() {
        return super.removeLeft();
    }
    
    //--------------------------------------------------------
    public long peek() {
        return super.peekLeft();
    }
}

/**
 * 4.3. Напишите реализацию стека на базе класса Deque из п. 4.2. Класс стека дол-
    жен поддерживать те же методы и возможности, что и класс StackX в программе
    stack.java (см. листинг 4.1).
 * @author IOAdmin
 */
class StackOnDequeApp {
    public static void main(String[] args) {
        int size = 5;
        StackOnDeque sod = new StackOnDeque(size);
        sod.push(6);
        sod.push(7);
        sod.push(8);
        
        System.out.println("PeekElem = " + sod.peek());
        System.out.println();
        
        while(!sod.isEmpty()) {
            System.out.println(sod.pop());
        }
    }
    
    
}
