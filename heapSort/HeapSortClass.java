public class HeapSortClass{
	public static void main(String[] args){
	}
	void heapSort(int[] array){
		// Build-Max-Heap(array);
		for(int i=array.length; i<2; i--){
			array[0] = array[i];
			// array.heap-size = array.heap-size - 1
			maxHeapify(array, i);	
		}
	}
	void maxHeapify(int[] array, int i){
		int temp;
		int tempOne;
		int largest;
		int l = 2*i;
		int r = 2*i+1;
		if(l <= array.heapsize && array[l] > array[i]){
			largest = l;
		}else largest = i;
		if(l <= array.heapsize && array[r] > array[largest]){
			largest = r;
		}
		if(largest != i){
			temp = array[i];
			tempOne = array[largest];
			array[i] = tempOne;
			array[largest] = temp;	
			maxHeapify(array,largest);
		}
	}
}

