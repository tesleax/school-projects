import java.util.Random;

public class SortingAlgorithm {

    public static void main(String[] args) {
        SortingAlgorithm Object = new SortingAlgorithm();
        Object.averageAll(10, 100000);
        Object.averageAll(10, 10000);
        Object.averageAll(10, 1000);
        Object.averageAll(10, 100);
    }
  /*
    void printArray(int[] array){
        System.out.print("[");
        for(int i=0; i<array.length-1 ; i++){
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length-1]);
        System.out.println("]");
    }
  */

    int[] randomArrayCreator(int n) {
        int[] array = new int[n];
        Random A = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = A.nextInt(100);
        }
        return array;
    }

    int[] insertionSort(int[] unsortedArray) {
        int temp;
        int i;
        for (int j = 1; j < unsortedArray.length; j++) {
            temp = unsortedArray[j];
            i = j - 1;
            while (i >= 0 && unsortedArray[i] > temp) {
                unsortedArray[i + 1] = unsortedArray[i];
                i = i - 1;
            }
            unsortedArray[i + 1] = temp;
        }
        return unsortedArray;
    }

    int[] merge(int[] array, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];
        for (int i = 0; i < n1; i++) {
            L[i] = array[p + i - 1];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[q + j];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = p - 1; k < r; k++) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
        }
        return array;
    }

    int[] mergeSort(int[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(array, p, q);
            mergeSort(array, q + 1, r);
            merge(array, p, q, r);
        }
        return array;
    }

    /*
    Firstly,it swaps first with last element with favour of temp
    decreasing size to hold maximum value and we will not apply
    anything on max value.
    */
    int[] heapSort(int[] array) {
        int size = array.length;
        buildMaxHeap(array);
        for (int i = array.length - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            size--;
            maxHeapify(array, 0, size);
        }
        return array;
    }

    int[] maxHeapify(int[] array, int i, int size) {
        int left = (i + 1) * 2 - 1;
        int right = (i + 1) * 2;
        int largest = i;
        if (left < size && array[left] > array[i]) {
            largest = left;
        }
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(array, largest, size);
        }
        return array;
    }

    int[] buildMaxHeap(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        return array;
    }

    void averageAll(int n, int size) {
        System.out.println("-------------------------------------------- ");
        System.out.println("    " + size + " sized array sorting times.");
        System.out.println("-------------------------------------------- ");
        long resultInsert = 0;
        long resultMerge = 0;
        long resultHeap = 0;
        for (int i = 0; i < n; i++) {
            int[] arrayOne = randomArrayCreator(size);
            int[] arrayOneCp1 = arrayOne;
            int[] arrayOneCp2 = arrayOne;
            long tempStartInsert = System.nanoTime();
            insertionSort(arrayOne);
            long tempEndInsert = System.nanoTime();
            long tempStartMerge = System.nanoTime();
            mergeSort(arrayOneCp1, 1, arrayOneCp1.length);
            long tempEndMerge = System.nanoTime();
            long tempStartHeap = System.nanoTime();
            heapSort(arrayOneCp2);
            long tempEndHeap = System.nanoTime();
            resultInsert = resultInsert + (tempEndInsert - tempStartInsert);
            resultMerge = resultMerge + (tempEndMerge - tempStartMerge);
            resultHeap = resultHeap + (tempEndHeap - tempStartHeap);
        }
        System.out.println("It took " + (resultInsert / (1000 * n)) +
                " microseconds to sort arrayOne with insertionSort Algorithm in average.");
        System.out.println("It took " + (resultMerge / (1000 * n)) +
                " microseconds to sort arrayOne with mergeSort Algorithm in average.");
        System.out.println("It took " + (resultHeap / (1000 * n)) +
                " microseconds to sort arrayOne with heapSort Algorithm in average.");
    }
}
