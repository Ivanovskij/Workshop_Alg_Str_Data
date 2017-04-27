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
    public void insert(long item) {             // Вставка мин эл-та на верх
        int j;
        if (isEmpty()) {                        // если приоретная очередь пустая
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
    public long remove() {
        return arr[--nItems];
    }

    //--------------------------------------------------------
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

        pqa.insert(10);
        pqa.insert(5);
        pqa.insert(3);
        pqa.insert(10);
        pqa.insert(5);
        pqa.insert(3);

        System.out.println(pqa.peekMin());
    }
}
