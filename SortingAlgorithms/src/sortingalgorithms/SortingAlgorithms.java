package sortingalgorithms;

import java.util.Random;

public class SortingAlgorithms {

    public static void main(String[] args) {
        SortingAlgorithms Object = new SortingAlgorithms();
        int[] arrayOne = Object.randomArrayCreator(4);
        int[] arrayTwo = Object.randomArrayCreator(4);
        int[] insertionSortedArrayOne = Object.insertionSort(arrayOne);
        int[] mergeSortedArrayOne = Object.mergeSort(arrayTwo, 1, 
                arrayOne.length);
        Object.printArray(insertionSortedArrayOne);
        Object.printArray(mergeSortedArrayOne);
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
}
