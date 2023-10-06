package assign05;

import java.util.ArrayList;
import java.util.Collections;
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
	
	
	
	/**
	 * Performs Quicksort on a given generic ArrayList. The pivot is choosen by using one
	 * of three strategies, either using the middle, one of the ends, or choosing an index
	 * around the first or third quarter of the given array.
	 * 
	 * @param <T>
	 * @param arr: ArrayList to be sorted.
	 */
	public static <T extends Comparable<? super T>> void quicksort (ArrayList<T> list) {
		
		sortQuick(list, 0, list.size()-1);
	}
	
	
	
	/**
	 * Partitioning phase. Takes the given low and high window and swaps certain values depending on the pivot.
	 * Acts recursively in tandom with the sortQuick method.
	 * 
	 * @param list: List to be sorted
	 * @param high: High index for the window to be sorted
	 * @param low: Low index
	 */
	public static <T extends Comparable<? super T>> int partition (ArrayList<T> list, int low, int high) {
		
		// Takes the element at the pivot index (which can be changed) to make comparisons with.
		T pivot = list.get(choosePivot("high", low, high)); 
		
		// Takes the element before the one we're iterating with.
        int prev = low - 1; 
        
        // Iterate through the list for all elements less than or equal to the pivot
        for (int i = low; i < high; i++) {
            
        	// If the current element is less than the pivot
            if (list.get(i).compareTo(pivot) <= 0) {
                
            	// Move the previous value up
            	prev++;
                
            	// Swap positions on the list using a temporary value 
                T temp = list.get(prev);
                list.set(prev, list.get(i));
                list.set(i, temp);
            }
        }
        
        // Swap positions on the list using a temporary value 
        T temp = list.get(prev + 1);
        list.set(prev + 1, list.get(high));
        list.set(high, temp);
 
        return prev+1; // Return the updated tail-end tracker.
    }
	
	
	
	/**
	 * Takes three parameters to either choose low, random, or high pivot values.
	 * 
	 * @param method: Method for choosing pivot
	 * @param lo: Low index
	 * @param hi: High index
	 * @return: Index of the values to be chosen as pivot
	 */
	public static int choosePivot (String method, int low, int high) {

		if (method == "high") { // Uses end of list
			
			return high;
		
		} if (method == "mid") { // Uses middle
			
			return (low + high) / 2;
		
		} if (method == "random") { // Uses random index
			
			Random r = new Random();
			return r.nextInt((high - low) + 1) + low;
		}
		
		// Print an error message.
		System.out.println("Invalid method.");
		return 0;
		
	}
	
	
	
	/**
	 * Quicksort helper method that essentially acts as a funnel for the recursive process, same as mergesort.
	 * 
	 * @param <T>
	 * @param list: list to be sorted
	 * @param lo: low index
	 * @param high: High index
	 */
	public static <T extends Comparable<? super T>> void sortQuick (ArrayList<T> list, int lo, int hi) {
		
		// Since it's recursive, this essentially functions as a while loop and acts as the base case
        if (lo < hi) {
        	
            // Finds the partition index as a result of the partition function, which it uses for more statements
            int partitionIndex = partition(list, lo, hi);
            
            // Recursively sort the two pieces of the list around the pivot.
            sortQuick(list, lo, partitionIndex - 1); // Before partition is the hig
            sortQuick(list, partitionIndex + 1, hi); // After partition is the low
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
