/***********************************
* 
* 
* ДВУСТОРОННИЙ СПИСОК 
* 
* 
* 
***********************************/
package src.java.chapter5.oneList;


class DoubleEndedList {
    
    private Link first;     
    private Link last;

    public DoubleEndedList() {      // Список пока не содержит элементов
        first = null;
        last = null;
    }
    
    
    //--------------------------------------------------------
    public void insertFirst(int iData) {    // Вставка элемента в начало списка
        Link newLink = new Link(iData);     // Создание нового элемента       
        if (isEmpty()) {                    // если список пуст
            last = newLink;                 // newLink <-- last
        }
        newLink.next = first;               // newLink --> старое значение first
        first = newLink;                    // first --> newLink
    }   
    
    //--------------------------------------------------------
    public void insertLast(int iData) {     // Вставка элемента в конец списка
        Link newLink = new Link(iData);
        if (isEmpty()) {
            first = newLink;                // first --> newLink
        } else {
            last.next = newLink;            // Старое значение last --> newLink
        }
        last = newLink;                     // newLink <-- last
    }
    
    //--------------------------------------------------------
    // Удаление первого элемента списка
    // (предполагается, что список не пуст)
    public int deleteFirst() {
        int temp = first.iData;
        if (first.next == null) {       // Если только один элемент
            last = null;                // null <-- last
        }
        first = first.next;             // first --> старое значение next
        return temp;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (first == null);
    }
    
    //--------------------------------------------------------
    public void display() {
        System.out.println("===================DISPLAY===================");
        Link current = first;
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
    }
    
    private class Link {

        private final int iData;
        private Link next;

        public Link(int id) {
            this.iData = id;
        }

        @Override
        public String toString() {
            return "Link{" + "iData=" + iData + "}";
        }
    }
}

/**
 *
 * @author IOAdmin
 */
class DoubleEndedListApp {
    public static void main(String[] args) {
        DoubleEndedList endedList = new DoubleEndedList();
        
        endedList.insertFirst(4);
        endedList.insertFirst(5);
        endedList.insertFirst(6);
//        
        endedList.insertLast(0);
        endedList.insertLast(1);
        endedList.insertLast(2);
        
        endedList.deleteFirst();
        endedList.deleteFirst();
        
        endedList.display();
    }
}
