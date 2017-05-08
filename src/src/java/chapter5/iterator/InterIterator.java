package src.java.chapter5.iterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Link {

    public final long data;
    public Link next;

    public Link(long data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Link{" + "data=" + data + '}';
    }
}

class LinkListImpl {

    private Link first;

    public LinkListImpl() {
        first = null;
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (first == null);
    }

    //--------------------------------------------------------
    public void setFirst(Link first) {
        this.first = first;
    }

    //--------------------------------------------------------
    public Link getFirst() {
        return first;
    }

    //--------------------------------------------------------
    public ListIterator getListIterator() {
        return new ListIterator(this);
    }

    //--------------------------------------------------------
    public void display() {
        System.out.println("=======DISPLAY=========");
        Link current = first;
        while (current != null) {
            System.out.print(current + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class ListIterator {

    private Link current;
    private Link previous;
    private LinkListImpl list;

    public ListIterator(LinkListImpl list) {
        this.list = list;
        reset();
    }

    //--------------------------------------------------------
    public final void reset() {
        current = list.getFirst();
        previous = null;
    }

    //--------------------------------------------------------
    public boolean atEnd() {
        return (current.next == null);
    }

    //--------------------------------------------------------
    public void nextLink() {
        previous = current;
        current = current.next;
    }

    //--------------------------------------------------------
    public Link getCurrent() {
        return current;
    }

    //--------------------------------------------------------
    public void insertAfter(long data) {
        Link newLink = new Link(data);
        if (list.isEmpty()) {
            list.setFirst(newLink);
            current = newLink;
        } else {
            newLink.next = current.next;
            current.next = newLink;
            nextLink();
        }
    }

    //--------------------------------------------------------
    public void insertBefore(long data) {
        Link newLink = new Link(data);
        if (previous == null) {
            newLink.next = list.getFirst();
            list.setFirst(newLink);
            reset();
        } else {
            newLink.next = previous.next;
            previous.next = newLink;
            current = newLink;
        }
    }

    //--------------------------------------------------------
    public long deleteCurrent() {
        long temp = current.data;

        if (previous == null) {
            list.setFirst(current.next);
            reset();
        } else {
            previous.next = current.next;
            if (atEnd()) {
                reset();
            } else {
                current = current.next;
            }
        }

        return temp;
    }
}

/**
 *
 * @author IOAdmin
 */
class InterIteratorApp {

    public static void main(String[] args) throws IOException {
        LinkListImpl list = new LinkListImpl();
        ListIterator it = list.getListIterator();
        long value;

        it.insertAfter(1);
        it.insertAfter(2);
        it.insertAfter(3);
        it.insertAfter(4);

        while (true) {
            System.out.print("Enter first letter of show, reset, ");
            System.out.print("next, get, before, after, delete: ");
            System.out.flush();
            int choice = getChar(); // Ввод команды
            switch (choice) {
                case 's':                    // show list
                    if (!list.isEmpty()) {
                        list.display();
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'r':                    // reset (to first)
                    it.reset();
                    break;
                case 'n':                    // advance to next item
                    if (!list.isEmpty() && !it.atEnd()) {
                        it.nextLink();
                    } else {
                        System.out.println("Can't go to next link");
                    }
                    break;
                case 'g':                    // get current item
                    if (!list.isEmpty()) {
                        value = it.getCurrent().data;
                        System.out.println("Returned " + value);
                    } else {
                        System.out.println("List is empty");
                    }
                    break;
                case 'b':                    // insert before current
                    System.out.print("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    it.insertBefore(value);
                    break;
                case 'a':                    // insert after current
                    System.out.print("Enter value to insert: ");
                    System.out.flush();
                    value = getInt();
                    it.insertAfter(value);
                    break;
                case 'd':                    // delete current item
                    if (!list.isEmpty()) {
                        value = it.deleteCurrent();
                        System.out.println("Deleted " + value);
                    } else {
                        System.out.println("Can't delete");
                    }
                    break;
                default:
                    System.out.println("Invalid entry");
            }
        }
    }

    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }

    //-------------------------------------------------------------
    public static char getChar() throws IOException {
        String s = getString();
        return s.charAt(0);
    }

    //-------------------------------------------------------------
    public static int getInt() throws IOException {
        String s = getString();
        return Integer.parseInt(s);
    }
}
