package assign05;

import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSorter {
	
	private int insertionSortThreshold = 0;

	/*
	 * Performs a Mergesort on the given generic ArrayList and swaps to Insertion sort
	 * when the sub-arrays are split to a given size.
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		if(arr.size() <= 1) {
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
		
		//Runs while the sub-array size is less than the insertion sort threshold.
		if(leftIndex < rightIndex) {
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

		//Finds the length of the left and right sub-arrays.
		int leftLength = middle - left + 1;
	    int rightLength = right - middle;
		
		//Creates a temporary array to store the merged elements.
		ArrayList<T> tempArr = new ArrayList<T>();
		
		int i = left, j = middle, k = 0;
		
		//Checks that objects remain in both the left and right sub-arrays.
		while (i <= leftLength && j <= rightLength) {
			//If the next item in the left side of the array is larger than the right side, add it to the temp array next.
	        if (arr.get(i).compareTo(arr.get(j)) <= 0) {
	            tempArr.add(arr.get(i));
	            i++;
	        } else { //If not add the right side array item to the temp array next.
	            tempArr.add(arr.get(j));
	            j++;
	        }
	    }

	    // Copy any remaining elements from the left sub-array.
	    while (i <= leftLength) {
	        tempArr.add(arr.get(i));
	        i++;
	    }

	    // Copy any remaining elements from the right sub-array.
	    while (j <= rightLength) {
	        tempArr.add(arr.get(j));
	        j++;
	    }

	    // Copy the merged elements back into the original array
	    for(i = left; i < tempArr.size(); i++) {
	    	arr.set(i, tempArr.get(i-left));
	    }
//	    for (i = left, k = 0; k < tempArr.size(); i++, k++) {
//	        arr.set(i, tempArr.get(k));
//	    }
//		
		
		//		int leftSize = middle - left + 1;
//	    int rightSize = right - middle;
//	    
//	    ArrayList<T> leftArr = new ArrayList<T>();
//	    ArrayList<T> rightArr = new ArrayList<T>();
//	
//
//	
//	    //Builds the left subarray
//	    for (int i = 0; i < leftSize; i++) {
//	    	leftArr.add(arr.get(left + i));
//	    }
//	    
//	    //Builds the right subarray
//	    for (int j = 0; j < rightSize; j++) {
//	    	rightArr.add(arr.get(middle + 1 + j));
//	    }
//	
//	    int i = 0, j = 0, k = left;
//	
//	    //Checks if items remain in both the left and right subarrays
//	    while (i < leftSize && j < rightSize) {
//	    	//If the current index of the left subarray is larger, add it next to the array. If not
//	    	//add the current item on the right subarray next and increase the respective index.
//	    	if(leftArr.get(i).compareTo(rightArr.get(j)) < 0) {
//	    		arr.set(k, leftArr.get(i));
//	    		i++;
//	    	}
//	    	else {
//	    		arr.set(k, rightArr.get(j));
//	    		j++;
//	    	}
//	        k++;
//	    }
//	
//	    //If any remaining items exist in the left subarray, add them to the array
//	    while (i < leftSize) {
//	    	arr.set(k, leftArr.get(i));
//	        i++;
//	        k++;
//	    }
//	
//	    //If any remaining items exist in the right subarray, add them to the array
//	    while (j < rightSize) {
//	    	arr.set(k, rightArr.get(j));
//	        j++;
//	        k++;
//	    }
//	}
//	
//	/*
//	 * Performs insertion sort on the merge sort sub-arrays once the threshold has been
//	 * reached.
//	 */
//	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int leftIndex, int rightIndex) {
//		
//		//Runs insertion sort on the given array just between the given indices.
//		for(int i = leftIndex + 1; i < rightIndex; i++) {
//			T curr = arr.get(i); 
//			
//			int j = i-1;//Acts as a tracker for the previous index.
//			
//			//Finds the correct spot for the current item while iterating through the list.
//			while(j >= leftIndex && arr.get(j).compareTo(curr) > 0) {
//				arr.set(j+1, arr.get(j));
//				j = j-1;
//			}
//			arr.set(j+1,curr);
//		}
	}
	
	/*
	 * Performs insertion sort on the merge sort sub-arrays once the threshold has been
	 * reached.
	 */
	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int leftIndex, int rightIndex) {
		
		//Runs insertion sort on the given array just between the given indices.
		for(int i = leftIndex + 1; i < rightIndex; i++) {
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
	
	
	
	/*
	 * Performs Quicksort on a given generic ArrayList. The pivot is choosen by using one
	 * of three strategies, either using the middle, one of the ends, or choosing an index
	 * around the first or third quarter of the given array.
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		
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
		for(int i = size; i > 1; i--) {
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