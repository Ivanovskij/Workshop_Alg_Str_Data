/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter3;

import java.util.Random;

/**
 * Сложность пузырьковой сортировки
 * В общем виде, если массив состоит из N элементов, на первом проходе выпол-
 * няются N–1 сравнений, на втором — N–2 и т. д. Сумма такого ряда вычисляется
 * по формуле:
 * (N–1) + (N–2) + (N–3) + ... + 1 = N × (N–1)/2
 * @author IOAdmin
 */
class ArrayBub {

    private long[] arr;
    private int nElems;

    public ArrayBub(int maxElem) {
        arr = new long[maxElem];
        nElems = 0;
    }

    //--------------------------------------------------------
    public void insert(long value) {
        arr[nElems] = value;
        nElems++;
    }

    //--------------------------------------------------------
    // Мой алгоритм
    public void bubbleSort() {
        for (int i = 0; i < nElems; i++) {
            // отсекаем элементы с правой стороны
            // так как они уже отсортированы
            // самый большой элемент (тяжелый пузырек) уже справа
            for (int j = 0; j < nElems - i - 1; j++) {           
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                }
            }
        }
    }

    //--------------------------------------------------------
    // Пример из книги
    public void bubbleSort1() {
        int out, in;
        for (out = nElems - 1; out > 1; out--) {        // Внешний цикл (обратный)
            for (in = 0; in < out; in++) {              // Внутренний цикл (прямой)
                if (arr[in] > arr[in + 1]) {            // Порядок нарушен?
                    swap(in, in + 1);                   // Поменять местами]
                }
            }
        }
    }
    
    //--------------------------------------------------------
    /*
        3.1. В программе bubbleSort.java (листинг 3.1) и в приложении B�bble�ort Work-
        shop индекс in всегда перемещается слева направо, находит наибольший элемент
        и перемещает его к позиции out справа. Измените метод bubbleSort() так, чтобы
        в нем выполнялись двусторонние перемещения, иначе говоря, индекс in сначала,
        как и прежде, переносит наибольший элемент слева направо, но затем он меняет
        направление и переносит наименьший элемент справа налево. Вам понадобятся
        два внешних индекса: справа (старый индекс out) и слева.
    */
    public void bubbleSort3_1() {
        int out_left = 0;                                                   // левая граница
        int out_right;                                                      // правая граница
        int in;                                                             // inner - смещение
        
        // двигаемся пока правая граница больше левой
        // цикл остановится в центре
        for (out_right = nElems - 1; out_right > out_left; out_right--) {   // правая граница
            for (in = 0; in < out_right; in++) {                            // двигаемся к правой границе, больший эл-т направо
                if (arr[in] > arr[in + 1]) {                                // поиск макс эл-та
                    swap(in, in + 1);
                }
            }
            
            for (in = out_right - 1; in > out_left; in--) {                 // начинаем от правой границы до левой, меньший эл-т влево
                if (arr[in] < arr[in - 1]) {                                // поиск мин эл-та
                    swap(in, in - 1);
                }
            }
            out_left++;                                                     // сдвигаем границу слева к центру
        }
    }
    
    
    //--------------------------------------------------------
    /*
        3.4. Еще один простой алгоритм сортировки — сортировка методом четно-нечет-
        ных перестановок — основан на многократном выполнении двух проходов по мас-
        сиву. На первом проходе ищутся все пары элементов a[j] и a[j+1] , где j — нечетное
        число ( j = 1, 3, 5, …). Если ключи следуют в неверном порядке, элементы меняются
        местами. На втором проходе то же самое делается для всех четных значений ( j = 2,
        4, 6, …). Двухпроходная обработка выполняется многократно до тех пор, пока мас-
        сив не будет полностью отсортирован. Замените метод bubbleSort() в bubbleSort.
        java (листинг 3.1) методом четно-нечетных перестановок oddEvenSort() . Убедитесь
        в том, что он работает для произвольных объемов данных. Требуется определить,
        сколько раз будет выполняться двухпроходная обработка.
    */
    // Сложность данного алгоритма - O(n^2)
    public void oddEvenSort() {
        for (int i = 0; i < nElems; i++) {   
            if (i % 2 == 0) {                                       // если четный индекс
                for (int j = 0; j < nElems - 1; j += 2) {           // просматриваем пары
                    if (arr[j] > arr[j + 1]) {
                        swap(j, j + 1);                             // меняем
                    }
                }
            } else {                                                // если нечетный индекс
                for (int j = 1; j < nElems - 1; j += 2) {
                    if (arr[j] > arr[j + 1]) {                      // просматриваем пары
                        swap(j, j + 1);                             // меняем
                    }
                }
            }
        }
    }

    private void swap(int index1, int index2) {
        long tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    //--------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}

class BubbleSortApp {
    
    public static void main(String[] args) {
//        int maxElem = 50000;
        int maxElem = 8;
        ArrayBub arrayBub = new ArrayBub(maxElem);
        
        arrayBub.insert(100);
        arrayBub.insert(25);
        arrayBub.insert(1000);
        arrayBub.insert(4000);
        arrayBub.insert(130);
        arrayBub.insert(125);
        arrayBub.insert(1300);
        arrayBub.insert(42000);

//        for (int i = 0; i < 50000; i++) {
//            long r = (new Random().nextInt() / 10000000);
//            arrayBub.insert(r);
//        }
//        arrayBub.display();
        
        //---- сравнивал тут время алгоритма
        double start;

        //---------------------------------------------------
        start = System.nanoTime();
        System.out.println("BubleSort3_1:");
        arrayBub.bubbleSort3_1();
        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000));
        arrayBub.display();
        
//        start = System.nanoTime();
//        System.out.println("BubleSort:");
//        arrayBub.bubbleSort();
////        arrayBub.bubbleSort1();
//        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000));
//        arrayBub.display();
        
        //---------------------------------------------------
//        start = System.nanoTime();
//        System.out.println("oddEvenSort:");
//        arrayBub.oddEvenSort();
//        System.out.println("Time: " + ((System.nanoTime() - start) / 1000000000));
//        arrayBub.display();
    }
}
