package src.java.chapter8.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Tree {
    
    private Node root;      // корень дерева

    public Tree() {
        root = null;        // Пока нет ни одного узла
    }
    
    
    //-----------------------------------------------------------
    // Поиск узла с заданным ключом
    // (предполагается, что дерево не пустое)
    public boolean find(int key) {  
        Node current = root;                // Начать с корневого узла
        while (key != current.iData) {      // Пока не найдено совпадение
            if (key < current.iData) {      // Двигаться налево?
                current = current.leftChild;
            } else {                        // Или направо?
                current = current.rightChild;
            }
            if (current == null) {          // Если потомка нет,
                return false;               // поиск завершился неудачей
            }
        }
        return true;                        // Элемент найден
    }
    
    //-----------------------------------------------------------
    public void insert(int iData, double dData) {
        Node newNode = new Node(iData, dData);      // Создание нового узла
        
        if (root == null) {         // Корневой узел не существует
            root = newNode;
        } else {                    // Корневой узел занят
            Node current = root;    // Начать с корневого узла
            Node parent;
            while (true) {
                parent = current;   // сохраняем предыдущий узел
                if (iData < current.iData) {        // Двигаться налево?
                    current = current.leftChild;
                    if (current == null) {          // Если достигнут конец цепочки,
                        parent.leftChild = newNode; // вставить слева
                        return;
                    }
                } else {            // Или направо?
                    current = current.rightChild;
                    if (current == null) {              // Если достигнут конец цепочки,
                        parent.rightChild = newNode;    // вставить справа
                        return;
                    }
                }
            }
        }
    }
    
    //-----------------------------------------------------------
    // Удаление узла с заданным ключом
    // (предполагается, что дерево не пусто)
    public boolean delete(int key) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = true;
        
        while (current.iData != key) {          // Поиск узла
            parent = current;
            if (key < current.iData) {          // Двигаться налево?
                isLeftChild = true;
                current = current.leftChild;
            } else {                            // Или направо?
                isLeftChild = false;
                current = current.rightChild;
            }
            
            if (current == null) {          // Конец цепочки
                return false;               // Узел не найден
            }
        }
        
        // Если узел не имеет потомков, он просто удаляется
        if (current.leftChild == null && 
                current.rightChild == null) {
            if (current == root) {      // Если узел является корневым,
                root = null;            // дерево очищается
            } else if (isLeftChild) {   
                parent.leftChild = null;        // Узел отсоединяется
            } else {                            // от родителя
                parent.rightChild = null;
            }
        // Если нет правого потомка, узел заменяется левым поддеревом
        } else if (current.rightChild == null) {    
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {               // Левый потомок родителя
                parent.leftChild = current.leftChild;
            } else {                                // Правый потомок родителя
                parent.rightChild = current.leftChild;
            }
        // Если нет левого потомка, узел заменяется правым поддеревом
        } else if (current.leftChild == null) {     
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {           // Левый потомок родителя
                parent.leftChild = current.rightChild;
            } else {                            // Правый потомок родителя
                parent.rightChild = current.rightChild;
            }
        // Два потомка, узел заменяется преемником    
        } else {
            // Поиск преемника для удаляемого узла (current)
            Node successor = getSuccessor(current);
            
            // Родитель current связывается с посредником
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
        }
        
        // Преемник связывается с левым потомком current
        // (преемник не может иметь левого потомка)
        return true;        // Признак успешного завершения
    }   
    
    //-----------------------------------------------------------
    /*
        Метод возвращает узел со следующим значением после delNode.
        Для этого он сначала переходит к правому потомку, а затем
        отслеживает цепочку левых потомков этого узла.
        -- Нахождение приемника
    */
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;      // Переход к правому потомку
        while (current != null) {               // Пока остаются левые потомки
            successorParent = successor;
            successor = current;
            current = current.leftChild;        // Переход к левому потомку
        }
                                                // Если преемник не является
        if (successor != delNode.rightChild) {  // правым потомком,
            successorParent.leftChild = successor.rightChild;   // создать связи между узлами
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }
    
    //-----------------------------------------------------------
    /*
        Простейший способ обхода основан на использовании рекурсии (см. главу 6,
        «Рекурсия»). При вызове рекурсивного метода для обхода всего дерева в аргументе
        передается узел. В исходном состоянии этим узлом является корень дерева. Метод
        должен выполнить только три операции:
        1. Вызов самого себя для обхода левого поддерева узла.
        2. Посещение узла.
        3. Вызов самого себя для обхода правого поддерева узла.
        Не забудьте, что посещение узла подразумевает выполнение некоторой опера-
        ции: вывод данных, запись в файл и т. д.
    СИММЕТРИЧНЫЙ ОБХОД
    */
    private void inOrder(Node localRoot) {
        if (localRoot != null) {
            inOrder(localRoot.leftChild);    
            System.out.print(localRoot.iData + " ");
            inOrder(localRoot.rightChild);
        }
    }
    
    //-----------------------------------------------------------
    /*
        Прямой обход
    */
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.iData + " ");
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
        }
    }
    
    //-----------------------------------------------------------
    /*
        Обратный обход
    */
    private void postOrder(Node localRoot) {
        if (localRoot != null) {
            preOrder(localRoot.leftChild);
            preOrder(localRoot.rightChild);
            System.out.print(localRoot.iData + " ");
        }
    }
    
    //-----------------------------------------------------------
    public void traverse(int traverseType) {
        switch(traverseType) {
            case 1: System.out.print("\nPreorder traversal: ");
                    preOrder(root);
                    break;
            case 2: System.out.print("\nInorder traversal: ");
                    inOrder(root);
                    break;
            case 3: System.out.print("\nPostorder traversal: ");
                    postOrder(root);
                    break;
        }
        System.out.println();
    }
    
    //-----------------------------------------------------------
    // возвращает мин элемент
    /*
        Чтобы получить минимальное значение ключа в дереве, перейдите от корня
        к левому потомку; затем перейдите к левому потомку этого потомка и т. д., пока не
        доберетесь до узла, не имеющего левого потомка. Этот узел и содержит минималь-
        ное значение ключа 
    */
    public int min() {
        Node current = root;
        while (current.leftChild != null) {
            current = current.leftChild;
        }
        return current.iData;
    }
    
    //-----------------------------------------------------------
    // возвращает макс элемент
    /*
        Чтобы получить макс значение ключа в дереве, перейдите от корня
        к правому потомку; затем перейдите к правому потомку этого потомка и т. д., пока не
        доберетесь до узла, не имеющего правому потомка. Этот узел и содержит макс-
        ное значение ключа 
    */
    public int max() {
        Node current = root;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current.iData;
    }
    
    public void display() {
        Stack globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "............................................");
        while (!isRowEmpty) {
            Stack localStack = new Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }
            
            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.iData);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);
                    
                    if (temp.leftChild != null ||
                            temp.rightChild != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            
            nBlanks /= 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
        }
        System.out.println(
                "............................................");
    }
    
    private class Node {
        
        private final int iData;
        private final double dData;
        private Node leftChild;
        private Node rightChild;

        public Node(int iData, double dData) {
            this.iData = iData;
            this.dData = dData;
        }

        @Override
        public String toString() {
            return "Node{" + "iData=" + iData + ", dData=" + dData + '}';
        }
    }
}


/**
 *
 * @author IOAdmin
 */
class TreeApp {
    public static void main(String[] args) throws IOException {
        Tree theTree = new Tree();
         
        theTree.insert(50, 1.5);
        theTree.insert(25, 1.2);
        theTree.insert(75, 1.7);
        theTree.insert(12, 1.5);
        theTree.insert(37, 1.2);
        theTree.insert(43, 1.7);
        theTree.insert(30, 1.5);
        theTree.insert(33, 1.2);
        theTree.insert(87, 1.7);
        theTree.insert(93, 1.5);
        theTree.insert(97, 1.5);
        
        
        int value;
        while(true) {
            System.out.print("Enter first letter of show, ");
            System.out.print("insert, find, delete, or traverse: ");
            int choice = getChar();
            switch(choice) {
                case 's':
                    theTree.display();
                    break;
                case 'i':
                    System.out.print("Enter value to insert: ");
                    value = getInt();
                    theTree.insert(value, value + 0.9);
                    break;
                case 'f':
                    System.out.print("Enter value to find: ");
                    value = getInt();
                    boolean found = theTree.find(value);
                    if(found) {
                        System.out.print("Found[" + value + "]");
                        System.out.println();
                    } else {
                        System.out.print("Not Found[" + value + "]");
                        System.out.println();
                    }
            }
        }
    }

    // -------------------------------------------------------------
    public static String getString() throws IOException
    {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
    // -------------------------------------------------------------
    public static char getChar() throws IOException
    {
        String s = getString();
        return s.charAt(0);
    }
    //-------------------------------------------------------------
    public static int getInt() throws IOException
    {
        String s = getString();
        return Integer.parseInt(s);
    }
}
