/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter6.recursion.practice;

/**
 * 6.2. В главе 8, «Двоичные деревья», рассматриваются двоичные деревья, у ко-
        торых каждая ветвь имеет (теоретически) ровно две подветви. Если попытаться
        изобразить двоичное дерево на экране в символьном режиме, в первой строке будет
        один узел, во второй — два, затем 4, 8, 16 и т. д. Например, при длине в 16 символов
        вывод будет выглядеть так:
        --------X-------
        ----X-------X---
        --X---X---X---X-
        -X-X-X-X-X-X-X-X
        XXXXXXXXXXXXXXXX
        (Конечно, нижнюю строку следовало бы сдвинуть на пол-символа вправо, но
        в символьном режиме вывода это невозможно.) Для вывода дерева можно вос-
        пользоваться рекурсивным методом makeBranches() с аргументами left и right ,
        определяющими конечные точки горизонтального диапазона. При первом входе в
        метод left содержит 0, а right — количество символов (включая дефисы) в каждой
        строке минус один. Метод выводит X в центре диапазона, после чего вызывает
        себя дважды: для левой и для правой половины диапазона. Когда диапазон умень
        шается ниже некоторого предела, метод возвращает управление. Все дефисы и X
        сохраняются в массиве, содержимое которого выводится за один раз — скажем, при
        вызове метода display() . Напишите метод main() , который выводит дерево, вызы-
        вая makeBranches() и display() . Метод main() должен сам определять длину строки
        вывода в символах (32, 64 и т. д.) Проследите за тем, чтобы массив для хранения
        выводимых символов был не больше необходимого. Каким образом количество
        строк (5 на приведенном рисунке) связано с длиной строки?
 * @author IOAdmin
 */
public class Branches {
    
    static String[] tree;
    
    public static void main(String[] args) {
        int size_tree = 16;
        tree = new String[size_tree];
        makeBranches(0, size_tree);
        display();
    }
    
    public static void makeBranches(int left, int right) {
    }
    
    public static void display() {
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null) {
                System.out.print(tree[i]);
            }
            System.out.print("-");
        }
        System.out.println();
    }
}
