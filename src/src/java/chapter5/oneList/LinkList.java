package src.java.chapter5.oneList;

class LinkList {

    private Link first;         // Ссылка на первый элемент списка

    public LinkList() {         // Список пока не содержит элементов,
        first = null;           // явно указываем куда указывает first
    }

    //--------------------------------------------------------
    public void insertFirst(int id, double dd) {
        Link newLink = new Link(id, dd);     // создаем новую область памяти
        newLink.next = first;                // присваиваем полю next ссылку на first (newLink --> старое значение first)
        first = newLink;                     // first --> newLink
    }

    //--------------------------------------------------------
    public Link deleteFirst() {     // Удаление первого элемента, (предполагается, что список не пуст)
        Link temp = first;          // Сохранение ссылки
        first = first.next;         // Удаление: first-->ссылка на второй элемент
        return temp;                // Метод возвращает ссылку на удаленный эл-т
    }

    //--------------------------------------------------------
    // Удаление элемента с заданным ключом
    // (предполагается, что список не пуст)
    public Link deleteElem(int searchKey) {     
        Link current = first;
        Link previous = first;

        while (current.iData != searchKey) {    // поиск эл-та
            if (current.next == null) {         // Элемент не найден
                return null;
            } else {
                previous = current;             // Перейти к следующему элементу
                current = current.next;
            }
        }

        if (current == first) {             // Если первый элемент,
            first = first.next;             // изменить first
        } else {                            // В противном случае,
            previous.next = current.next;   // обойти его в списке
        }
        
        return current;
    }

    //--------------------------------------------------------
    public Link find(int searchKey) {
        Link current = first;
        while (current.iData != searchKey) {
            if (current.next == null) {
                return null;
            } else {
                current = current.next;
            }
        }
        return current;
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
        private final double dData;
        private Link next;

        public Link(int id, double dd) {
            this.iData = id;
            this.dData = dd;
        }

        public int getiData() {
            return iData;
        }

        public double getdData() {
            return dData;
        }

        public Link getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Link{" + "iData=" + iData + ", dData=" + dData + '}';
        }
    }
}

/**
 *
 * @author IOAdmin
 */
class LinkListApp {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();

        linkList.insertFirst(0, 0);
        linkList.insertFirst(1, 0.1);
        linkList.insertFirst(2, 0.2);
        linkList.insertFirst(3, 0.3);

        linkList.display();

        int search_key = 3;
        System.out.println("Search key = " + search_key);
        System.out.println(linkList.deleteElem(search_key));
        linkList.display();
    }
}
