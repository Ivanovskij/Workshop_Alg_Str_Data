/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.java.chapter2;

/**
 * Неупорядоченный массив
 * Предпологается, что в массиве всегда хранятся: элементы > 0
 * @author IOAdmin
 */
public class HighArray {

    private final long[] arr;     // ссылка на массив
    private int nElems;           // количество элементо в массиве
    
    //--------------------------------------------------------
    public HighArray(int maxElem) {
        arr = new long[maxElem];
        nElems = 0;
    }
    
    //--------------------------------------------------------
    public boolean find(long key) {
        for (int i = 0; i < nElems; i++) {      // поиск заданного значения
            if (arr[i] == key) {                // значение найдено
                return true;
            }
        }
        return false;       // значение не было найдено
    }
    
    //--------------------------------------------------------
    public void insert(long value) {
        arr[nElems] = value;
        nElems++;
    }

    //--------------------------------------------------------
    public boolean delete(long value) {
        int posDel;
        for (posDel = 0; posDel < nElems; posDel++) {    // поиск заданного значения
            if (arr[posDel] == value) {
                break;
            }
        }
        
        if (posDel == nElems) {                          // если не удалось найти
            return false;
        } else {
            for (int j = posDel; j < nElems; j++) {      // если нашли, то смещаем элементы в массиве
                arr[j] = arr[j + 1];
            }
            nElems--;                                    // уменьшаем количество элементов в массиве
            return true;
        }
    }
    
    //--------------------------------------------------------
    public long getMax() {
        if (nElems == 0) {
            return -1;
        }
        
        long max = arr[0];
        for (int i = 0; i < nElems; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
    
    //--------------------------------------------------------
    public boolean removeMax() {
        long max = getMax();
        if (max == -1) {                        // массив пуст - такого элемента нет 
            return false;           
        }
        
        int j;
        for (j = 0; j < nElems; j++) {          // находим позицию макс элемента
            if (arr[j] == max) {
                break;
            }
        }
        for (int i = j; i < nElems; i++) {      // делаем сдвиг последующих элементов
            arr[i] = arr[i + 1];
        }
        nElems--;
        return true;
    }
    
    //--------------------------------------------------------

    /**
     *   Добавьте в класс HighArray программы highArray.java (листинг 2.3) метод
     *   noDups(), удаляющий все дубликаты из массива. Другими словами, если массив
     *   содержит три элемента с ключом 17, метод noDups() должен удалить два из них. Не
     *   беспокойтесь о сохранении порядка элементов. Одно из возможных решений —
     *   сравнить каждый элемент со всеми остальными элементами и заменить все ду-
     *   бликаты null (или другим значением, не встречающимся среди реальных ключей),
     *   после чего удалить из массива все вхождения null . Конечно, размер массива при
     *   этом уменьшится.
     */
    public void noDups() {
        for (int i = 0; i < nElems; i++) {
            long curElem = arr[i];                      // текущий элемент
            if (curElem == -1) {                        // если уже заменен, то пропускаем
                continue;
            }
            for (int j = i + 1; j < nElems; j++) {      // начинаем с позиции этого элемента + 1
                if (curElem == arr[j]) {        
                    arr[j] = -1;                        // если дубликат, присваиваем -1 (null)
                }
            }
        }
        
        for (int i = 0; i < nElems; i++) {
            while (arr[i] == -1) {                      // находим дубль, и удаляем эл-ты
                for (int j = i; j < nElems; j++) {
                    arr[j] = arr[j + 1];                // сдвигаем элементы (удаление)
                } 
                nElems--;                               // уменьшаем массив
            }
        }
    }
    
    //--------------------------------------------------------
    /**
     *   Метод removeMax() из пункта 2.2 может использоваться для сортировки
     *   содержимого массива по ключу. Реализуйте алгоритм сортировки, который не из-
     *   меняет класса HighArray (а изменяет только код main() ). Вам потребуется второй
     *   массив для хранения отсортированных данных. (Этот алгоритм представляет со-
     *   бой крайне примитивную разновидность сортировки методом выбора, описанной
     *   в главе 3, «Простая сортировка».)
     * СДЕЛАН НАВЕРНО НЕ ВЕРНО, ПОТОМУ ЧТО НЕ ПОНЯТНО 
     * ЧТО НУЖНО СДЕЛАТЬ (ЭТО ОПИСАНИЕ ОН ДАВАЛ В КНИГЕ)
     * @return
     */
    public long[] sort() {
        long[] arrTmp = new long[nElems];

        int oldNElems = nElems;
        for (int i = 0; i < oldNElems; i++) {
            arrTmp[i] = getMax();
            removeMax();
        }
        
        System.arraycopy(arrTmp, 0, arr, 0, oldNElems);
        nElems = oldNElems;
        
        return arrTmp;
    }
    
    //--------------------------------------------------------
    public void display() {
        for (int i = 0; i < nElems; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}
