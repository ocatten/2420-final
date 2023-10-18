package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ArrayListSorter {
	
	//Field for the threshold to switch to Insertion Sort in Mergesort.
	private static int insertionThreshold = 0;
	
	/*
	 * Sets the Insertion Sort Threshold
	 */
	public void setInsertionThreshold(int threshold) {
		insertionThreshold = threshold;
	}

	/*
	 * Performs a Mergesort on the given generic ArrayList and swaps to Insertion sort
	 * when the sub-arrays are split to a given size.
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		if(arr.size() < 2) {
			System.out.println("The given array is too small to be sorted!");
		}
		else {
			sort(arr,0,arr.size()-1);
		}
	}
	
	/*
	 * Splits the given ArrayList into two smaller ArrayLists. If the size of the given
	 * ArrayList is smaller than the insertion sort threshold, then it calls insertion
	 * sort on the given ArrayList.
	 */
	public static <T extends Comparable<? super T>> void sort(ArrayList<T> arr, int leftIndex, int rightIndex) {
		//Finds the middle index of the given sub-array.
		int midIndex = leftIndex + (rightIndex - leftIndex) /2;
		
		if ((rightIndex - leftIndex) <= insertionThreshold) {
	        insertionSort(arr, leftIndex, rightIndex);
		}
		//Runs while the sub-array size is less than the insertion sort threshold.
		else if(leftIndex < rightIndex) {
			//Divides the given arr into left and right sub-arrays.
			sort(arr,leftIndex,midIndex);
			sort(arr,midIndex+1,rightIndex);
			
			//Merges the two subarrays into a combined array, the combined array is sorted as it is created.
			merge(arr,leftIndex,midIndex,rightIndex);
		}
	}
	
	/*
	 * Merges the given Arraylist by finding 
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int left, int middle, int right) {

		// Finds the length of the left and right sub-arrays.
		int leftLength = middle - left + 1;
		int rightLength = right - middle;

		// Creates a temporary array to store the merged elements.
		ArrayList<T> tempArr = new ArrayList<T>();

		int i = left, j = middle + 1, k = 0; 

		// Checks that objects remain in both the left and right sub-arrays.
		while (i <= middle && j <= right) { 
		    // If the next item in the left side of the array is larger than the right side, add it to the temp array next.
		    if (arr.get(i).compareTo(arr.get(j)) <= 0) {
		        tempArr.add(arr.get(i));
		        i++;
		    } else { // If not, add the right side array item to the temp array next.
		        tempArr.add(arr.get(j));
		        j++;
		    }
		}

		// Copy any remaining elements from the left sub-array.
		while (i <= middle) { 
		    tempArr.add(arr.get(i));
		    i++;
		}

		// Copy any remaining elements from the right sub-array.
		while (j <= right) { 
		    tempArr.add(arr.get(j));
		    j++;
		}

		// Copy the temp array of merged elements back into the original array
		for (i = left; i <= right; i++) { 
		    arr.set(i, tempArr.get(i - left));
		}
	}
	
	
	
	/*
	 * Performs insertion sort on the merge sort sub-arrays once the threshold has been
	 * reached.
	 */
	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int leftIndex, int rightIndex) {
		
		//Runs insertion sort on the given array just between the given indices.
		for(int i = leftIndex + 1; i <= rightIndex; i++) {
			T curr = arr.get(i); 
			
			int j = i-1;//Acts as a tracker for the previous index.
			
			//Finds the correct spot for the current item while iterating through the list.
			while(j >= leftIndex && arr.get(j).compareTo(curr) > 0) {
				arr.set(j+1, arr.get(j));
				j = j-1;
			}
			arr.set(j+1,curr);
		}
	}
	
	
	
	public enum PivotMethod {
        HIGH, MID, RANDOM
    }

    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> list) {
        sortQuick(list, 0, list.size() - 1, PivotMethod.HIGH);
    }

    /* This function takes last element as pivot,
    places the pivot element at its correct
    position in the sorted list, and places all
    smaller (smaller than pivot) to the left of
    pivot and all greater elements to the right
    of pivot */
 <T> int partition(List<T> list, int low, int high) {
     T pivot = list.get(high);
     int i = low - 1; // index of smaller element

     for (int j = low; j < high; j++) {
         // If current element is smaller than or
         // equal to pivot
         if (list.get(j).compareTo(pivot) <= 0) {
             i++;

             // swap list[i] and list[j]
             T temp = list.get(i);
             list.set(i, list.get(j));
             list.set(j, temp);
         }
     }

     // swap list[i+1] and list[high] (or pivot)
     T temp = list.get(i + 1);
     list.set(i + 1, list.get(high));
     list.set(high, temp);

     return i + 1;
 }

 /* The main function that implements QuickSort()
    list --> List to be sorted,
    low  --> Starting index,
    high  --> Ending index */
 <T> void sortQuick(List<T> list, int low, int high) {
     if (low < high) {
         /* pi is the partitioning index, list[pi] is
           now at the right place */
         int pi = partition(list, low, high);

         // Recursively sort elements before
         // partition and after partition
         sort(list, low, pi - 1);
         sort(list, pi + 1, high);
     }
 }
	
	
	
	
	//---------------------------METHODS TO GENERATE LISTS----------------------------------//
	
	/*
	 * Returns an ascending ArrayList of 1 to the given size of Integers.
	 */
	public static ArrayList<Integer> generateAscending(int size){
		ArrayList<Integer> ascendingList = new ArrayList<Integer>();
		
		//Adds Integers from 1 to the size to the ascendingList.
		for(int i = 1; i < size+1; i++) {
			ascendingList.add(i);
		}
		
		return ascendingList;
	}
	
	/*
	 * Returns an ArrayList of descending Integers from the given size to 1.
	 */
	public static ArrayList<Integer> generateDescending(int size){
		ArrayList<Integer> descendingList = new ArrayList<Integer>();
		
		//Adds Integers to the descendingList from size to 1.
		for(int i = size; i > 0; i--) {
			descendingList.add(i);
		}
		
		return descendingList;
	}
	
	/*
	 * Returns an ArrayList with randomly ordered integers from 1 to size.
	 */
	public static ArrayList<Integer> generatePermuted(int size){
		ArrayList<Integer> permutedList = new ArrayList<Integer>();
		
		//Adds Integers from 1 to size in ascending order
		for(int i = 1; i < size+1; i++) {
			permutedList.add(i);
		}
		
		//Collections.shuffle shuffles the list from ascending order to a random order.
		Collections.shuffle(permutedList);
		
		return permutedList;	
	}
}
