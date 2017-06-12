/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter8.tree.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

// <editor-fold defaultstate="collapsed" desc="CLASS TREE">

class Node {

    private Character symbol;
    private Integer frequnce;
    private Node leftChild;
    private Node rightChild;

    public Node(Character symbol, Integer frequnce) {
        this.symbol = symbol;
        this.frequnce = frequnce;
    }

    public Node(MyTree left, MyTree right) {
        this.frequnce = left.getRoot().frequnce + right.getRoot().frequnce;
        this.leftChild = left.getRoot();
        this.rightChild = right.getRoot();
    }

    public Character getSymbol() {
        return symbol;
    }

    public void setSymbol(Character symbol) {
        this.symbol = symbol;
    }

    public Integer getFrequnce() {
        return frequnce;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setFrequnce(Integer frequnce) {
        this.frequnce = frequnce;
    }

    @Override
    public String toString() {
        return "Node{" + "symbol=" + symbol + ", frequnce=" + frequnce + '}';
    }
}

final class MyTree implements Comparable<MyTree> { 
    
    private final Node root;      // корень дерева

    public MyTree(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    // переопредялем компаратор
    // чтобы класть в очередь по наименьшей частоте
    @Override
    public int compareTo(MyTree mt) {
        return this.root.getFrequnce() - mt.getRoot().getFrequnce();
    }
    
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="CLASS MyHUFFMAnTREE">
class MyHuffmanTree {
    
    private final int[] frequences;

    public MyHuffmanTree(int[] frequences) {
        this.frequences = frequences;
    }

    /**
     * Алгоритм построения дерева выглядит так:
        1. Создать объект Node (см. программу tree.java ) для каждого символа, исполь-
            зуемого в сообщении. В сообщении из нашего примера будет 9 узлов. Каждый
            узел состоит из двух элементов данных: символа и частоты этого символа в со-
            общении. Информация для сообщения «message» приведена
        в табл. 8.4.
        2. Создать объект дерева для каждого из этих узлов. Узел становится корнем
            дерева.
        3. Вставить эти деревья в приоритетную очередь (см. главу 4). Деревья упорядо-
            чиваются по частоте, при этом наименьшая частота обладает наибольшим при-
            оритетом. Таким образом, при извлечении всегда выбирается дерево с наименее
            часто используемым символом.
            Далее происходит следующее:
        4. Извлечь два дерева из приоритетной очереди и сделать их потомками нового
            узла. Частота нового узла является суммой частот потомков; поле символа
            может остаться пустым.
        5. Вставить новое дерево из трех узлов обратно в приоритетную очередь.
        6. Продолжить выполнение шагов 4 и 5. Деревья постепенно увеличиваются, а их
            количество постепенно сокращается. Когда в очереди останется только одно
            дерево, оно представляет собой дерево Хаффмана. Работа алгоритма на этом
            завершается.
    
    *   @param frequnces - массив содержащий частоту символов в сообщении. 
    *       Номер ячейки соответствует  коду символа в ASCII.
    */
    public MyTree build() {
        PriorityQueue<MyTree> pQueue = new PriorityQueue<>();
        int lengthFreq = frequences.length;
        
        // 1 - 3 пункты
        for (int i = 0; i < lengthFreq; i++) {
            if (frequences[i] > 0) {
                char symbol = (char)i;  // преобразуем int i в char - это и будет являться символом из ascii
                Node newNode = new Node(symbol, frequences[i]);     // создаем новый узел
                pQueue.add(new MyTree(newNode));                    // кладем его в очередь
            }
        }
        
        // 4 - 6
        while (pQueue.size() > 1) {
            MyTree a = (MyTree) pQueue.poll();
            MyTree b = (MyTree) pQueue.poll();
            pQueue.add(new MyTree(new Node(a, b)));
        }
        
        return (MyTree) pQueue.poll();
    }
    
}
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="CLASS ENCODE_HUFFMAN">
class EncodeHuffman {
    
    private static final int COUNT_ASCII = 256;
    private static final int SIZE_CODETABLE = 256;
    
    private static final char ZEROW_CHARACTER = '0';        // обход дерева налево 0
    private static final char ONE_CHARACTER = '1';          // обход дерева направо 1
    
    private final String input;
    private MyTree huffmanTree;

    public EncodeHuffman(String input) {
        this.input = input;
    }
    
    /** 
     * 1. Cчитываем символы и считаем их частоту
     * 2. Строим дерево хаффмана 
     * 3. Для кодирования сообщения необходимо создать кодовую таблицу, 
     *    в которой для каждого символа приводится соответствующий код Хаффмана.
     * 4. Коды Хаффмана раз за разом присоединяются к кодированному сообщению, 
     *    пока оно не будет завершено.
     */
    public String encrypt() {
        // 1 - 2 пункты
        huffmanTree = new MyHuffmanTree(getFrequences()).build();
        
        // 3 - 4 пункты
        String[] codeTable = getCodeTable();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            result.append(codeTable[ch]);
        }
        return result.toString();
    }
    
    
    /**
     *   Дешифрование по дереву Хаффмана
     */
    public String decrypt(String encodeInput) {
        if (huffmanTree == null) {
            throw new IllegalArgumentException("Huffman tree is null.");
        }
        
        if (!checkEncodeInput(encodeInput)) {
            throw new IllegalArgumentException("Encode input text contains wrong characters!");
        }
        
        String result = decryptEncodeInput(encodeInput);
        return result;
    }
    
     /**
     *   Как использовать дерево Хаффмана для декодирования сообщения? Декодирование
     *   каждого символа начинается с корневого узла. Если в потоке обнаружен бит 0,
     *   вы переходите налево к следующему узлу, а если бит 1 — то направо. Попробуйте
     *   проделать это для кода A (010). Налево, направо, потом снова налево — и вы ока-
     *   зываетесь у узла A.
     * 
     *   При достижении листового узла начинается поиск нового символа.
     */
    private String decryptEncodeInput(String encodeInput) {
        Node curSymbol;
        StringBuilder decryptMsg = new StringBuilder();
        
        for (int pos = 0; pos < encodeInput.length(); pos++) {
            curSymbol = huffmanTree.getRoot();
            while (curSymbol.getSymbol() == null) {
                char chInput = encodeInput.charAt(pos);
                if (chInput == ZEROW_CHARACTER) {
                    curSymbol = curSymbol.getLeftChild();
                } else {
                    curSymbol = curSymbol.getRightChild();
                }
                // смещаем позицию во входящей строке
                // чтобы просматривать следующий символ
                pos++;
                if (pos >= encodeInput.length()) { break; }
            }
            decryptMsg.append(curSymbol.getSymbol());
            // смещаемся на одну позицию назад
            // так как до того как while стало false
            // мы сдвинулись на одну позицию вперед (можно сказать зря)
            pos--;
        }
        return decryptMsg.toString();
    }
    
    
    /**
     * Нахождение частоты символов во входящей строке
    */
    private int[] getFrequences() {
        int[] frequences = new int[COUNT_ASCII];
        
        for (char ch : input.toCharArray()) {
            frequences[ch]++;
        }
        
        return frequences;
    }
    
    
    /** 
     *  Для кодирования сообщения необходимо создать кодовую таблицу, 
     *  в которой для каждого символа приводится соответствующий код Хаффмана.
     */
    private String[] getCodeTable() {
        String[] table = new String[SIZE_CODETABLE];
        codeTable(table, huffmanTree.getRoot(), new StringBuilder());
        return table;
    }
    
    
    /**
     * Постороение кодовой таблицы реализовано посредством вызова метода, 
     * который начинается от корня таблицы, а затем рекурсивно вызывает себя 
     * для каждого потомка. Через некоторое время алгоритм обойдет все пути 
     * ко всем листовым узлам, и кодовая таблица будет построена. 
     * @param curSymbol текущий узел 
     * @param code код из 0(лево) и 1(право), отражающий путь от корня до текущего узла
     * @param table кодовая таблица
     */
    private void codeTable(String[] table, Node curSymbol, StringBuilder code) {
        if (curSymbol.getSymbol() != null) {
            table[curSymbol.getSymbol()] = code.toString();     
            return;
        }
        
        codeTable(table, curSymbol.getLeftChild(), code.append(ZEROW_CHARACTER));   // создаем путь из 0
        code.deleteCharAt(code.length() - 1);       // при выходе удаляем символ
        codeTable(table, curSymbol.getRightChild(), code.append(ONE_CHARACTER));    // создаем путь из 1
        code.deleteCharAt(code.length() - 1);       // при выходе удаляем символ
    }

    
    /**
     * Вывод таблицы кодов
     */
    public void printCodeTable() {
        System.out.println("char\t frequency\t binary code");
        printCodes(huffmanTree.getRoot(), new StringBuilder());
        System.out.println();
    }
    
    
    /**
     * Рекурсивный метод печати кодовой таблицы
     * @param current текущий узел
     * @param path код пути до текущего узла
     */
    private void printCodes(Node current, StringBuilder path) {
        if (current.getSymbol() != null) {
            // выводим символ, частоту и код пути от корня до текущкго узла
            System.out.println(current.getSymbol() + "\t " + current.getFrequnce() + "\t\t " + path); 
        } else {
            // обходим левое поддерево
            printCodes(current.getLeftChild(), path.append(ZEROW_CHARACTER));
            path.deleteCharAt(path.length() - 1); 
            // обходим правое поддерево
            printCodes(current.getRightChild(), path.append(ONE_CHARACTER));
            path.deleteCharAt(path.length() - 1); 
        }
    }
    
    
    /***
     * В закодированной входящей строке должны быть 
     * только 0 и 1
     */
    private boolean checkEncodeInput(String encodeInput) {
        for (int i = 0; i < encodeInput.length(); i++) {
            char cur = encodeInput.charAt(i);
            if (cur != ZEROW_CHARACTER && cur != ONE_CHARACTER) {
                return false;
            }
        }
        return true;
    }
}
// </editor-fold>


/**
 * 8.5. Напишите программную реализацию кодирования и декодирования Хафф-
        мана. Программа должна делать следующее:
        * Получать текстовое сообщение — возможно, состоящее из нескольких строк.
        * Создавать дерево Хаффмана для этого сообщения.
        * Создавать кодовую таблицу.
        * Кодировать сообщение в двоичную форму.
        * Декодировать сообщение из двоичной формы обратно в текстовую.
 * @author IOAdmin
 */
class HuffmanApp {
    
    public static void main(String[] args) throws IOException {
        String input;
        EncodeHuffman encodeHuffman;
                
        while (true) {
            System.out.print("Input msg: ");
            input = getString();
            if (input.equals("")) {
                break;
            }
            
            // шифруем
            encodeHuffman = new EncodeHuffman(input);
            String encodeInput = encodeHuffman.encrypt();
            
            // выводим кодовую таблицу
            encodeHuffman.printCodeTable();
            
            // выводим зашифрованное сообщение
            System.out.println("Encode input text: " + encodeInput);
            System.out.println();
            
            // дешифруем и выводим
            String decodeText = encodeHuffman.decrypt(encodeInput);
            System.out.println("Decode text: " + decodeText);
            
            System.out.println("========================================");
        }
    } 
    
     //-----------------------------------------------------------
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String input = br.readLine();
        System.out.println();
        return input;
    }
    
}
