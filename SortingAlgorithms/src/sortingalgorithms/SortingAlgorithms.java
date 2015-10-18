package sortingalgorithms;

import java.util.Random;

public class SortingAlgorithms {

    public static void main(String[] args) {
        SortingAlgorithms Object = new SortingAlgorithms();
        /*
            1000 sized array
        */
        System.out.println("-------------------------------------------- ");
        System.out.println("    1000 sized array sorting times.");
        System.out.println("-------------------------------------------- ");
        // insertion
        int[] arrayOne = Object.randomArrayCreator(1000);
        long timeArrayOneInsertionStart = System.nanoTime()/1000;
        int[] insertionSortedArrayOne = Object.insertionSort(arrayOne);
        long timeArrayOneInsertionEnd = System.nanoTime()/1000;
        Object.printArray(insertionSortedArrayOne);
        System.out.println("It took " + (timeArrayOneInsertionEnd - 
                timeArrayOneInsertionStart) + 
                " miliseconds to sort arrayOne with insertionSort Algorithm.");
        long timeArrayOneMergeStart = System.nanoTime()/1000;
        // merge
        int[] mergeSortedArrayOne = Object.mergeSort(arrayOne, 1, 
                arrayOne.length);
        long timeArrayOneMergeEnd = System.nanoTime()/1000;
        Object.printArray(mergeSortedArrayOne);
        System.out.println("It took " + (timeArrayOneMergeEnd - 
                timeArrayOneMergeStart) + 
                " miliseconds to sort arrayOne with mergeSort Algorithm.");
        // heap
        long timeArrayOneHeapStart = System.nanoTime()/1000;
        int[] heapSortedArrayOne = Object.heapSort(arrayOne);
        long timeArrayOneHeapEnd = System.nanoTime()/1000;
        Object.printArray(heapSortedArrayOne);
        System.out.println("It took " + (timeArrayOneHeapEnd - 
                timeArrayOneHeapStart) + 
                " miliseconds to sort arrayOne with heapSort Algorithm.");
        /*
            10000 sized array
        */
        System.out.println("-------------------------------------------- ");
        System.out.println("    10000 sized array sorting times.");
        System.out.println("-------------------------------------------- ");
        //insert
        int[] arrayTwo = Object.randomArrayCreator(10000);
        long timeArrayTwoInsertionStart = System.nanoTime()/1000;
        int[] insertionSortedArrayTwo = Object.insertionSort(arrayTwo);
        long timeArrayTwoInsertionEnd = System.nanoTime()/1000;
        Object.printArray(insertionSortedArrayTwo);
        System.out.println("It took " + (timeArrayTwoInsertionEnd - 
                timeArrayTwoInsertionStart) + 
                " miliseconds to sort arrayTwo with insertionSort Algorithm.");
        // merge
        long timeArrayTwoMergeStart = System.nanoTime()/1000;
        int[] mergeSortedArrayTwo = Object.mergeSort(arrayTwo, 1, 
                arrayTwo.length);
        long timeArrayTwoMergeEnd = System.nanoTime()/1000;
        Object.printArray(mergeSortedArrayTwo);
        System.out.println("It took " + (timeArrayTwoMergeEnd - 
                timeArrayTwoMergeStart) + 
                " miliseconds to sort arrayTwo with mergeSort Algorithm.");
        // heap
        long timeArrayTwoHeapStart = System.nanoTime()/1000;
        int[] heapSortedArrayTwo = Object.heapSort(arrayTwo);
        long timeArrayTwoHeapEnd = System.nanoTime()/1000;
        Object.printArray(heapSortedArrayTwo);
        System.out.println("It took " + (timeArrayTwoHeapEnd - 
                timeArrayTwoHeapStart) + 
                " miliseconds to sort arrayTwo with heapSort Algorithm.");
        
    }
    
    void printArray(int[] array){
        System.out.print("[");
        for(int i=0; i<array.length-1 ; i++){
            System.out.print(array[i] + ",");
        }
        System.out.print(array[array.length-1]);
        System.out.println("]");
    }  
    
    int[] randomArrayCreator(int n){
        int[] array = new int[n];
        Random A = new Random();
        for (int i = 0; i < n; i++) {
            array[i] = A.nextInt(100);
        }
        return array;
    }
    
    int[] insertionSort(int[] unsortedArray){
        int temp;
        int i;
        for(int j=1; j < unsortedArray.length ;j++){
            temp = unsortedArray[j];
            i = j - 1;
            while(i>=0 && unsortedArray[i]>temp){
                unsortedArray[i+1] = unsortedArray[i];
                i = i - 1;
            }
            unsortedArray[i+1] = temp;
        }
        return unsortedArray;
    }
    
    int[] merge(int[] array,int p, int q, int r){
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1+1];
        int[] R = new int[n2+1];
        for (int i = 0; i < n1; i++) {
            L[i] = array[p+i-1];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = array[q+j];
        }
        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;
        int i = 0;
        int j = 0;
        for (int k = p-1; k < r; k++) {
            if(L[i] <= R[j]){
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
        }
        return array;
    }
    
    int[] mergeSort(int[] array,int p,int r){
        if (p<r) {
             int q = (p+r)/2;
             mergeSort(array,p,q);
             mergeSort(array,q+1,r);
             merge(array,p,q,r);
        }
        return array;
    }
    
    int[] heapSort(int[] array){
        int size = array.length;
        buildMaxHeap(array);
         for (int i = array.length-1; i > 0; i--) {
             int temp = array[0];
             array[0] = array[i];
             array[i] = temp;      
             size--;
             maxHeapify(array, 0,size);
         }
         return array;
    }
    
    int[] maxHeapify(int[] array, int i,int size){
        int left = (i+1) * 2 - 1;
        int right = (i+1) * 2;
        int largest = i;
        if(left < size && array[left]>array[i]){
            largest = left;
        }
        if(right < size && array[right]>array[largest]){
            largest = right;
        }
        if(largest != i){
            int temp = array[i];
            array[i] = array[largest];
            array[largest] = temp;
            maxHeapify(array,largest,size);
       }
        return array;
    }
    
    int[] buildMaxHeap(int[] array){
        for(int i=array.length/2; i>=0; i--){
            maxHeapify(array, i,array.length);
        }
        return array;
    }
    
    
}
