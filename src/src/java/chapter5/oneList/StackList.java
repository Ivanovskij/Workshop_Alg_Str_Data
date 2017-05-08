package src.java.chapter5.oneList;

class StackList {
    
    private final DoubleEndedList dEndedList;

    public StackList() {
        dEndedList = new DoubleEndedList();
    }
    
    
    //--------------------------------------------------------
    public void push(int id) {
        dEndedList.insertFirst(id);
    }
    
    //--------------------------------------------------------
    public int pop() {
        return dEndedList.deleteFirst();
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return dEndedList.isEmpty();
    }
    
    //--------------------------------------------------------
    public void display() {
        dEndedList.display();
    }
}

/**
 *
 * @author IOAdmin
 */
class StackListApp {
    public static void main(String[] args) {
        StackList stackList = new StackList();
        stackList.push(5);
        stackList.push(6);
        stackList.push(7);
        stackList.display();
        
        stackList.pop();
        stackList.pop();
        stackList.display();
    }
}
