package src.java.chapter5.oneList;


class SortedLinkList {
    
    private Link first;

    public SortedLinkList() {   
        first = null;           // Ссылка на первый элемент списка
    }
    
    //--------------------------------------------------------
    public void insert(long key_data) {     // Вставка в порядке сортировки
        Link previous = null;               // От начала списка
        // first всегда будет указывать 
        // на первый созданный эл-т в списке
        Link current = first;               
                                                
        // До конца списка
        // и если key > current.data
        while (current != null && key_data > current.data) {
            previous = current;
            current = current.next;         // Перейти к следующему элементу
        }
        
        Link newLink = new Link(key_data);  
        if (previous == null) {     // В начале списка
            first = newLink;        // first --> newLink
        } else {                        // Не в начале
            previous.next = newLink;    // старое значение prev --> newLink
        }
        newLink.next = current;         // newLink --> старое значение current
    }
    
    //--------------------------------------------------------
    public long remove() {
        long temp = first.data;
        first = first.next;
        return temp;
    }
    
    //--------------------------------------------------------
    public boolean isEmpty() {
        return (first == null);
    }
    
    //--------------------------------------------------------
    public void display() {
        System.out.println("=========DISPALY================");
        Link current = first;
        while(current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println();
    }
    
    private class Link {
        
        private long data;
        private Link next;

        public Link(long data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Link{" + "data=" + data + '}';
        }
    }
}

/**
 *
 * @author IOAdmin
 */
class SortedLinkListApp {
    public static void main(String[] args) {
        SortedLinkList sll = new SortedLinkList();
        
        sll.insert(0);
        sll.insert(1);
        sll.insert(2);
        
        sll.insert(1);

        sll.display();
        
        System.out.println("Remove[" + sll.remove() + "]");
        System.out.println("Remove[" + sll.remove() + "]");
        sll.display();
    }
}
