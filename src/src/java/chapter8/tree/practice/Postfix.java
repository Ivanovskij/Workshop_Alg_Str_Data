/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter8.tree.practice;

// <editor-fold defaultstate="collapsed" desc="CLASS STACK">
final class Stack {

    private Entry top;

    public Stack() {
    }

    //--------------------------------------------------------
    public void push(Object value) {
        Entry newEntry = new Entry(value);
        newEntry.next = top;
        top = newEntry;
    }

    //--------------------------------------------------------
    public Object pop() {
        Object temp = top.data;
        top = top.next;
        return temp;
    }

    //--------------------------------------------------------
    public Object peek() {
        return top.data;
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (top == null);
    }

    //--------------------------------------------------------
    public void display(String msg) {
        System.out.print(msg + " | ");
        System.out.print("Stack (bottom-->top): ");
        Entry current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    private class Entry {

        private Object data;
        private Entry next;

        public Entry(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Entry{" + "data=" + data + '}';
        }
    }
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="CLASS TREE">
class Tree {

    private Node root;      // корень дерева

    public Tree() {
        root = null;        // Пока нет ни одного узла
    }

    //-----------------------------------------------------------
    public void setRoot(Object data) {
        Node newNode = new Node(data);      // Создание нового узла
        if (root == null) {         // Корневой узел не существует
            root = newNode;
        }
    }
    
    //-----------------------------------------------------------
    public void insertLeft(Tree data) {
        Node newNode = new Node(data);      // Создание нового узла

        if (root == null) {         // Корневой узел не существует
            setRoot(data);
        } else {                    // Корневой узел занят
            Node current = root;    // Начать с корневого узла
            Node parent = root;
            while (current != null) {
                parent = current;       // сохраняем предыдущий узел
                current = current.leftChild;
            }
            parent.leftChild = newNode;
        }
    }

    //-----------------------------------------------------------
    public void insertRight(Tree data) {
        Node newNode = new Node(data);      // Создание нового узла

        if (root == null) {         // Корневой узел не существует
            setRoot(data);
        } else {                    // Корневой узел занят
            Node current = root;    // Начать с корневого узла
            Node parent = root;
            while (current != null) {
                parent = current;       // сохраняем предыдущий узел
                current = current.rightChild;
            }
            parent.rightChild = newNode;
        }
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
            System.out.print(localRoot.data + " ");
            inOrder(localRoot.rightChild);
        }
    }

    //-----------------------------------------------------------
    /*
        Прямой обход
     */
    private void preOrder(Node localRoot) {
        if (localRoot != null) {
            System.out.print(localRoot.data + " ");
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
            System.out.print(localRoot.data + " ");
        }
    }

    //-----------------------------------------------------------
    public void traverse(int traverseType) {
        switch (traverseType) {
            case 1:
                System.out.print("\nPreorder traversal: ");
                preOrder(root);
                break;
            case 2:
                System.out.print("\nInorder traversal: ");
                inOrder(root);
                break;
            case 3:
                System.out.print("\nPostorder traversal: ");
                postOrder(root);
                break;
        }
        System.out.println();
    }

    //-----------------------------------------------------------
    public void display() {
        java.util.Stack globalStack = new java.util.Stack();
        globalStack.push(root);
        int nBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println(
                "............................................");
        while (!isRowEmpty) {
            java.util.Stack localStack = new java.util.Stack();
            isRowEmpty = true;
            for (int j = 0; j < nBlanks; j++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node temp = (Node) globalStack.pop();
                if (temp != null) {
                    System.out.print(temp.data);
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null
                            || temp.rightChild != null) {
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

    @Override
    public String toString() {
        return "Tree{" + "root=" + root + '}';
    }

    private class Node {

        private final Object data;
        private Node leftChild;
        private Node rightChild;

        public Node(Object data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" + "data=" + data + "}";
        }
    }
}
// </editor-fold>


class Postfix {

    private final String input;
    private final Stack stack;
    
    public Postfix(String input) {
        this.input = input;
        stack = new Stack();
    }

    
    /*
        Действия, вы-
        полняемые при обнаружении операнда:
        1. Создать дерево с единственным узлом, содержащим операнд.
        2. Занести дерево в стек.
        Действия при обнаружении оператора:
        1. Извлечь из стека два дерева операндов B и C.
        2. Создать новое дерево A, корнем которого является оператор.
        3. Присоединить B в качестве левого потомка A.
        4. Присоединить C в качестве левого потомка A.
        5. Занести полученное дерево обратно в стек.
    */
    public Tree getTree() {
        int lengthInput = input.length();

        for (int i = 0; i < lengthInput; i++) {
            char ch = input.charAt(i);

            switch (ch) {
                case '+':
                case '-':
                case '*':
                case '/': {
                    Tree operand2 = (Tree) stack.pop();
                    Tree operand1 = (Tree) stack.pop();
                    
                    Tree newTree = new Tree();
                    newTree.setRoot(ch);
                    newTree.insertLeft(operand1);
                    newTree.insertRight(operand2);
                    stack.push(newTree);
                    break;
                }
                default: {
                    Tree operandTree = new Tree();
                    operandTree.setRoot(ch);
                    stack.push(operandTree);
                    break;
                }
            }
        }

        return (Tree) stack.pop();
    }

}

/**
 * 
    8.4. Напишите программу, которая преобразует постфиксное выражение в дере-
        во, изображенное на рис. 8.11 этой главы. Вам придется внести изменения в класс
        Tree из программы tree.java (см. листинг 8.1) и класс ParsePost из программы
        postfix.java (см. листинг 4.8) главы 4. Более подробная информация приведена
        в комментариях к рис. 8.11.
        После того, как дерево будет сгенерировано, обход дерева позволит получить
        префиксный, инфиксный и постфиксный эквиваленты алгебраического выра-
        жения. В инфиксной версии потребуются круглые скобки для предотвращения
        неоднозначности генерируемых выражений. В методе inOrder() перед первым
        рекурсивным вызовом выводится открывающая круглая скобка, а после второго —
        закрывающая.
        * 
        * РАБОТАЕТ ПРАВИЛЬНО, НО ДЛЯ ПРАВИЛЬНОГО ОТОБРАЖЕНИЯ НУЖНО ВСТАВЛЯТЬ НЕ ДЕРЕВО, А NODE
        * В МЕТОДЕ Postfix->getTree() в case: +, -, *, /
        * ПЛЮС НУЖНО ДОБАВИТЬ СКОБКИ
        * И ВЫЧИСЛЕНИЕ ВЫРАЖЕНИЯ
        * 
 * @author IOAdmin
 */
class PostfixApp {

    public static void main(String[] args) {
        Postfix postfix = new Postfix("ABC*+");
        Tree result = postfix.getTree();
        result.display();
    }

}
