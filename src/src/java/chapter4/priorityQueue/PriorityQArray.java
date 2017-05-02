package src.java.chapter4.priorityQueue;

class PriorityQArray {

    private final int maxSize;
    private final long[] arr;
    private int nItems;

    public PriorityQArray(int size) {
        maxSize = size;
        arr = new long[maxSize];
        nItems = 0;
    }

    //--------------------------------------------------------
    public void insertSlower(long item) {            // Вставка мин эл-та на верх
        int j;
        if (isEmpty()) {                            // если приоретная очередь пустая
            arr[nItems++] = item;
        } else {
            for (j = nItems - 1; j >= 0; j--) {     // Перебор в обратном направлении
                if (item > arr[j]) {                // Если новый элемент больше
                    arr[j + 1] = arr[j];            // сдвинуть вверх
                } else {                            // Если меньше,
                    break;                          // сдвиг прекращается
                }
            }
            arr[j + 1] = item;                      // Вставка элемента
            nItems++;
        }
        
    }
    
    //--------------------------------------------------------
    /*
        4.4. Реализация приоритетной очереди из листинга 4.6 обеспечивает быстрое
            извлечение высокоприоритетных элементов, но вставка новых элементов выпол-
            няется относительно медленно. Напишите программу с видоизмененным классом
            �riorityQ, быстро выполняющим вставку (за время O(1)) с медленным извлече-
            нием высокоприоритетного элемента. Включите метод для вывода содержимого
            приоритетной очереди (см. п. 4.1).
    */
    public void insertQuick(long item) {
        arr[nItems++] = item;
    }

    public long removeSlower() {
        long min = arr[0];
        int imin = 0;
        for (int j = 0; j < nItems; j++) {
            if (arr[j] < min) {
                min = arr[j];
                imin = j;
            }
        }
        
        for (int i = imin; i < nItems; i++) {
            arr[i] = arr[i + 1];
        }
        
        nItems--;
        return min;
    }
    //------------ END TASK 4.4 ------------------------------

    //--------------------------------------------------------
    public long removeQuick() {
        return arr[--nItems];
    }

    //--------------------------------------------------------
    /*
        метод будет работать только когда при вставке
        мы сразу же сортируем массив
    */
    public long peekMin() {
        return arr[nItems - 1];
    }

    //--------------------------------------------------------
    public boolean isEmpty() {
        return (nItems == 0);
    }

    //--------------------------------------------------------
    public boolean isFull() {
        return (nItems == maxSize);
    }

}

/**
 * Приоритетная очередь - по возрастанию приоритетов 
 *
 * Поля front и rear в этой реализации не нужны (в отличие от обычной очереди),
 * потому что, как уже говорилось ранее, значение front всегда равно nItems-1 , а зна-
 * чение rear всегда равно 0.
 * 
 * @author IOAdmin
 */
class PriorityQArrayApp {

    public static void main(String[] args) {
        int size = 6;
        PriorityQArray pqa = new PriorityQArray(size);

        pqa.insertQuick(10);
        pqa.insertQuick(5);
        pqa.insertQuick(3);
        pqa.insertQuick(10);
        pqa.insertQuick(5);

        while(!pqa.isEmpty()) {
            System.out.println(pqa.removeSlower());
        }
    }
}
