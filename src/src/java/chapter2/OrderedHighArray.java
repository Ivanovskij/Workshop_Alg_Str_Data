/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter2;

/**
 *
 * @author IOAdmin
 */
public class OrderedHighArray {

    /*
        Обычно логарифм вычисляет по основанию 10, 
        но результат легко преобразуется к основанию
        2 — достаточно умножить его на 3,322
    */
    private static final double LOG2 = 3.322;
    
    private final long[] arr;
    private int nElems;         // кол-во элементов в массиве

    public OrderedHighArray(int maxElem) {
        arr = new long[maxElem];
        nElems = 0;
    }
    
    //--------------------------------------------------------
    // Операция, обратная возведению в степень - log2(r)
    /**
     * Размер диапазона известен, требуется определить, 
     * сколько сравнений понадобится для завершения поиска. 
     * Иначе говоря, по известному r требуется определить количество шагов 
     * @param range - r - дипазон поиска
     * @return возвращает количество шагов для поиска заданного диапазона
     */
    public double log2r(int range) {
        return Math.log10(range) * LOG2;
    }

    //--------------------------------------------------------
    public int size() {
        return nElems;
    }

    //--------------------------------------------------------
    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {      // определение позиции вставки
            if (arr[j] > value) {           // линейный поиск
                break;
            }
        }
        for (int k = nElems; k > j; k--) {       // перемещение последующих элементов
            arr[k] = arr[k - 1];
        }
        arr[j] = value;                         // вставка значения
        nElems++;
    }
    
    //--------------------------------------------------------
    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems) {
            return false;                           // элемент не найден
        } else {
            for (int k = j; k < nElems; k++) {      // перемещение элементов
                arr[k] = arr[k + 1];
            }
            nElems--;                               // уменьшаем размер массива
            return true;                            // элемент удален
        }
    }

    //--------------------------------------------------------
    /**
     *
     * @param searchKey
     * @return curIn - current Index
     */
    public int find(long searchKey) {
        int lower_bound = 0;
        int upper_bound = nElems - 1;
        int curIn;

        while (true) {
            // делим пополам массив
            // нижняя и верхняя границы
            curIn = (lower_bound + upper_bound) / 2;                
            if (arr[curIn] == searchKey) {                          // элемент найден
                return curIn;
            } else if (lower_bound > upper_bound) {                 // элемент не найден
                return nElems;                                      
            } else {                                                // деление диапазона
                if (arr[curIn] < searchKey) {                           
                    lower_bound = curIn + 1;                        // в верхней границе
                } else {
                    upper_bound = curIn - 1;                        // в нижней границе
                }
            }
        }
    }
    
    //--------------------------------------------------------
    /**
     *   Добавьте в класс OrdArray программы orderedArray.java (листинг 2.4) метод
     *   merge(), объединяющий два упорядоченных исходных массива в один упорядочен-
     *   ный приемный массив. Включите в main() фрагмент кода, который заполняет два
     *   исходных массива случайными числами, вызывает merge() и выводит содержимое
     *   полученного массива. Исходные массивы могут содержать разное количество эле-
     *   ментов. Ваш алгоритм должен сравнивать ключи исходных массивов и копировать
     *   меньший в приемный массив. Также необходимо предусмотреть ситуацию, когда
     *   элементы в одном исходном массиве заканчиваются раньше, чем в другом.
     * @param a - первый упорядоченный массив
     * @param b - второй упорядоченный массив
     * @return result - один упорядоченный массив
     */
    public long[] merge(long[] a, long[] b) {
        int sizeArr1 = a.length;
        int sizeArr2 = b.length;
        long[] result = new long[sizeArr1 + sizeArr2];
                
        // Заведем два индекса, указывающих
        // на первый необработанный элемент
        // первого и второго массивов
        int aIndex = 0, bIndex = 0;                         // просматриваемые индексы
        
        int pos = 0;                                        // позиция вставки в результирующий массив
        
        // Будем повторять сравнение элементов
        // массивов a и b до тех пор, пока
        // в каждом из них есть хотя бы один
        // необработанный элемент
        while (aIndex < sizeArr1 && bIndex < sizeArr2) {    // просматриваем все индексы
            // В соответствии с тем, текущий элемент
            // какого массива меньше, мы записываем
            // этот элемент в массив-результат и
            // обновляем нужный индекс (aIndex или bIndex)
            if (a[aIndex] < b[bIndex]) {                    // сравниваем элементы
                result[pos] = a[aIndex];                         
                aIndex++;
            } else {
                result[pos] = b[bIndex];
                bIndex++;
            }
            pos++;
        }
        
        // После выполнения предыдущего цикла
        // все элементы одного из массивов будут
        // обработаны, тогда оставшиеся элементы
        // другого массива нужно просто дописать
        // в массив-результат
        
        // Заметим, что одно из условий (aIndex < sizeArr1)
        // или (bIndex < sizeArr2) будет гарантированно ложно
        while (aIndex < sizeArr1) {
            result[pos] = a[aIndex];
            aIndex++;
            pos++;
        }

        while (bIndex < sizeArr2) {
            result[pos] = b[bIndex];
            bIndex++;
            pos++;
        }
        
        return result;
    }

    //--------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
