import java.util.Random;

public class SortingAlgorithm {

    public static void main(String[] args) {
        SortingAlgorithm Object = new SortingAlgorithm();
        /*
        to calculate the speed of the insertion, merge and heap sort in average
        you can use averageAll method it takes iteration, and size of the array
        the reason we use iteration is to calculate time in scientific way.
         */
        Object.averageAll(10, 100000);
        Object.averageAll(10, 10000);
        Object.averageAll(10, 1000);
        Object.averageAll(10, 100);
    }

 /* void printArray(int[] array){
        System.out.print("[");
        for(int i=0; i<array.length-1 ; i++){
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length-1]);
        System.out.println("]");
    }*/

    /*
    randomArrayCreator takes size of the array and it gives
    array with random integers which have value within 100.
    randomArrayCreator : sizeOfArray -> int[] unSortedArrayWithRandomIntegers
    */
    int[] randomArrayCreator(int n) {
        int[] array = new int[n];
        Random A = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = A.nextInt(100);
        }
        return array;
    }

    /*
    insertionSort takes unsortedArray and gives basically like
    every other algorithm. It has  1 for and inside of for loop 1 while loop.
    So,	it makes this algoritm slower than other algorithm like merge and
    heap.(and also Java L2 Cache Problem makes it slower)
    insertionSort : int[] unsortedArray -> int[] sortedArray
    */
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

    /*
    merge method takes array, beginning, middle and	final point.
    Firstly, this method cuts array two pieces array[q-p+1] and
    array[r-q]. after that, It combines these two sub array with
    under favour of taking these points.
    */
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

		/*
        mergeSort takes array, beginning and final point. It checks
		beginning's value lower than final or not. It divides to 
		pieces with
			 int q = (p+r) / 2; 
		line. Because we're using q in mergeSort.
		*/

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

    /*
    this method will be used unless it finds the maximum value. Because
    there is recursive call if it couldnt find largest value.
    */
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

    /*
    to create array with order as much as possible, it goes to every node
    which is important for us and swapping them in a greater way.But it does
    not sort the array. It just determine where is the largest value and
    smallest value.
    */
    int[] buildMaxHeap(int[] array) {
        for (int i = array.length / 2; i >= 0; i--) {
            maxHeapify(array, i, array.length);
        }
        return array;
    }

    /*
    averageAll method takes iteration(to make comparision in scientific way)
    and sizeOfUnsortedArray. It gives us report about to time which it takes
    to sort in String Type. We're using "for loop" for iterating algorithms.
    */
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
