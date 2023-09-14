/**
 * This class sorts arrayLists that are permuted or sorted in descending or ascending order and compares mergesort
 * and quicksort against one another to find which is faster. No built-in sorting methods can be used unless otherwise
 * specified.
 * 
 * @author Parker Catten @u0580588 & Everett Oglesby
 * @version 06:12:23
 * CS 2420-001_SMR-2023
 */

package assignment05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListSorter {
	
	private static int insertionSortThreshold = 0;
	
	/*
	 * Sets the size of the array for mergesort to switch to insertion sort
	 */
	public int setInsertionThreshold(int threshold) {
		insertionSortThreshold = threshold;
		return insertionSortThreshold;
	}
	
	/*
	 * Preforms insertion sort for mergesort when a given threshold is reached
	 */
	public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int left, int right) {
	    
		int j;
		
		for(int i = left + 1; i < right; i++) {
			T key = arr.get(i);
			j = i - 1;
			
			while(j >= left && arr.get(j).compareTo(key) > 0) {
				arr.set(j+1, arr.get(j));
				j = j - 1;
			}
			arr.set(j+1, key);
		}
	}
	

	
	
	/**
	 * Performs a mergesort on the arrayList of the generic objects.
	 * 
	 * @param arr: ArrayList of generic items to be sorted
	 */
	public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
		
		sort(arr,0, arr.size() - 1);
	}

	/*
	 * Sorts the given array into smaller subarrays or calls insertion sort on the given array if
	 * its size is smaller than a given threshold.
	 */
	private static <T extends Comparable<? super T>> void sort(ArrayList<T> arr, int left, int right) {
		
		//If the size of the subarray is smaller than the threshold, run an insertion sort on the list
		if (right - left <= insertionSortThreshold) {
	        insertionSort(arr, left, right);
	        
	    //Else split the array in two halves then call sort on each half and merge the two sorted halves
	    } else if (left < right) {
	        int middle = left + (right - left) / 2;
	       
	        sort(arr, left, middle);
	        sort(arr, middle + 1, right);

	        merge(arr, left, middle, right);
	    }
	}




	/*
	 * Merges the given array with both of its halves already sorted. It creates two subarrays and sorts
	 * the given array by taking the top item from each subarray and adding the smaller one to the array.
	 * It repeats this process until the entire array has been sorted.
	 */
	private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, int left, int middle, int right) {
	    
		int leftSize = middle - left + 1;
	    int rightSize = right - middle;
	    
	    ArrayList<T> leftArr = new ArrayList<T>();
	    ArrayList<T> rightArr = new ArrayList<T>();
	

	
	    //Builds the left subarray
	    for (int i = 0; i < leftSize; i++) {
	    	leftArr.add(arr.get(left + i));
	    }
	    
	    //Builds the right subarray
	    for (int j = 0; j < rightSize; j++) {
	    	rightArr.add(arr.get(middle + 1 + j));
	    }
	
	    int i = 0, j = 0, k = left;
	
	    //Checks if items remain in both the left and right subarrays
	    while (i < leftSize && j < rightSize) {
	    	//If the current index of the left subarray is larger, add it next to the array. If not
	    	//add the current item on the right subarray next and increase the respective index.
	    	if(leftArr.get(i).compareTo(rightArr.get(j)) < 0) {
	    		arr.set(k, leftArr.get(i));
	    		i++;
	    	}
	    	else {
	    		arr.set(k, rightArr.get(j));
	    		j++;
	    	}
	        k++;
	    }
	
	    //If any remaining items exist in the left subarray, add them to the array
	    while (i < leftSize) {
	    	arr.set(k, leftArr.get(i));
	        i++;
	        k++;
	    }
	
	    //If any remaining items exist in the right subarray, add them to the array
	    while (j < rightSize) {
	    	arr.set(k, rightArr.get(j));
	        j++;
	        k++;
	    }
	}


	/**
	 * Takes an arrayList of generic class objects and sorts it through the quicksort algorithm, that splits a
	 * larger array into sub arrays and sorts those.
	 * 
	 * @param arr: ArrayList of generic objects to be sorted.
	 */
	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
		int low = 0;
		int high = arr.size() - 1;
		
		findPartition(arr,low,high);
	}
	
	/*
	 *Divides the array into smaller subarrays 
	 */
	private static <T extends Comparable<? super T>> void findPartition(ArrayList<T> arr, int low, int high) {
		if (low < high) {
            int partitionIndex = partition(arr, low, high);

            findPartition(arr, low, partitionIndex - 1);
            findPartition(arr, partitionIndex + 1, high);
        }
	}



	/*
	 * Swaps the items according to the pivot location
	 */
	private static <T extends Comparable<? super T>> int partition(ArrayList<T> arr, int low, int high) {
		T pivot = arr.get(high);//Pivot able to be changed
		
		//Set the i index at 1 less than the low
        int i = low - 1;
        for(int j = low; j < high; j++) {
        	if(arr.get(j).compareTo(pivot) < 0) {
        		i++;
        		swap(arr, i, j);
        	}
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

	/*
	 * Swaps the indices at i and j
	 */
    public static <T extends Comparable<? super T>> void swap(ArrayList<T> arr, int i, int j) {
        T temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
	
	
	
	
	
//	/**
//	 * Takes an arrayList of generic class objects and sorts it through the quicksort algorithm, that splits a
//	 * larger array into sub arrays and sorts those.
//	 * 
//	 * @param arr: ArrayList of generic objects to be sorted.
//	 */
//	public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
//		
//		// Catch case for small array.
//		//if(arr.size() < 2) {
//			//return; // If there is only one element, it is already sorted.
//		//}
//		
//		// Print out the original array.
//		//System.out.print(arr);
//		//System.out.println(" unsorted");
//		
//		// Set up relevant fields.
//		int leftBound = 0;
//		int rightBound = arr.size()-1;
//		int low = leftBound;
//		
//		partition(leftBound, rightBound, arr);
//	}
//	
//	
//	
//	/**
//	 * Helper method that actually sorts the partitions for quicksort
//	 * 
//	 * @param leftBound: index of the array that bounds the partition's lowest index
//	 * @param rightBound: index of the array that bounds the partition's highest index
//	 * @param arr: Array the partition is sorting.
//	 */
//	public static <T extends Comparable<? super T>> void partition(int low, int high, ArrayList<T> arr) {
//		
//		// Base case, tracks when the partitions are tracking sub arrays that don't exist
//		if (low < 0) {
//			return;
//		} else if (high >= arr.size()) {
//			return;
//		} else if (high <= 0) {
//			return;
//		}
//		
//		// Set up relevant fields.
//		int mid = pivotAtMid(arr, high, low);
//		
//		// Group of test statements.
//		System.out.println("Partition: " + arr);
//		System.out.println("low: " + arr.get(low));
//		System.out.println("high: " + arr.get(high));
//		System.out.println("pivot: " + arr.get(mid));
//		
//		// Comparator object to use
//		Comparator<T> cmp = new Comparator<T>() { // Makes the comparator to make comparisons with.
//			public int compare(T e1, T e2) { return e1.compareTo(e2); } };
//		
//		// While the algorithm hasn't sorted through the two ends of the partition:
//		while (low < mid || mid <= high) {
//			
//			// If the high is in the right place, decrement its position
//			if (cmp.compare(arr.get(high), arr.get(mid)) < 0) {
//				
//				System.out.println("high not found " + arr.get(high) + " against " + arr.get(mid)); // Test statement
//				high--;
//			}
//			
//			// If the low is in the right place, increment the position
//			if (cmp.compare(arr.get(low), arr.get(mid)) < 0) {
//				
//				System.out.println("low not found " + arr.get(low) + " against " + arr.get(mid)); // Test statement
//				low++;
//			}
//			
//			// If the low is higher than the pivot, print as such
//			if (cmp.compare(arr.get(low), pivot) > 0) {
//				System.out.println("low found " + arr.get(low) + " against " + pivot); // Test statement
//			}
//			
//			
//			// If the high is still higher than the pivot, decrement the position
//			if (cmp.compare(arr.get(high), pivot) > 0) {
//				
//				System.out.println("high not found " + arr.get(high) + " against " + pivot);
//				high--;
//			}
//			
//			// If the high is lower than the pivot, print as such
//			if (cmp.compare(arr.get(high), pivot) < 0) {
//				
//				System.out.println("high found " + arr.get(high) + " against " + pivot); // Test statement
//				
//				// If the low has also been found, swap the two and then move on
//				if (cmp.compare(arr.get(low), pivot) > 0) {
//					
//					System.out.println("SWAPPING " + arr.get(low) + " LOW WITH " + arr.get(high) + " HIGH"); // Test statement
//					Collections.swap(arr, high, low);
//				}
//			}
//		}
//		
//		// Now put the pivot back into its place IF IT BELONGS THERE
//		int pivotIndex = high;
//		
//		// Test statements
//		System.out.println("current pivotIndex " + pivotIndex);
//		System.out.println("current rightBound " + rightBound);
//		
//		// If the position of the pivot isn't right next to the rightBound:
//		if ( pivotIndex != rightBound-1) {
//			
//			System.out.println(rightBound);
//			System.out.println("SWAPPING " + arr.get(pivotIndex) + " WITH " + pivot); // Test statement
//			Collections.swap(arr, pivotIndex, rightBound); // Swap the two values.
//		
//		} else if (pivotIndex != rightBound-1) { 
//			
//			
//		} else {
//			
//			return;
//		}
//		
//		System.out.println(rightBound);
//		System.out.println("NEXT PARTITION");
//		partition(pivotIndex, rightBound, arr);
//		partition(leftBound, pivotIndex-1, arr);
//	}
	
	
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the center of the array.
	 * 
	 * @param arr: List that the method will compute the pivot point for.
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtMid(ArrayList<T> arr, int high, int low) {
		return (high + low) / 2;
	}
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the low of the array, or 0.
	 *
	 * @param high: Highest index the method will be sorting for
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtLow(ArrayList<T> arr) {
		return 0;
	}
	
	/**
	 * Helper method for determining the pivot, so that the code can easily switch between
	 * three different strategies for determining the pivot point on quicksort.
	 * This method returns the center of the array.
	 * 
	 * @param arr: List that the method will compute the pivot point for.
	 * @return int: Index of the pivot.
	 */
	private static <T> int pivotAtHigh(ArrayList<T> arr) {
		return arr.size() - 1;
	}
	
	
	
	/**
	 * Generates an arrayList of 1 to size in ascending order.
	 * 
	 * @param size: Size of the returned array.
	 * @return ArrayList<Integer>: The sorted arrayList in ascending order from 1 to size
	 */
	public static ArrayList<Integer> generateAscending(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, 1 to size to the returned arrayList.
		for (int i = 1; i <= size; i++) {
			returnArr.add(i);
		}
		
		return returnArr;
	}
	
	
	
	/**
	 * Generates a permuted(random) series of numbers from 1 to size and returns an arrayList of them
	 * 
	 * @param size: Greatest value of the values and the size of the returned ArrayList
	 * @return ArrayList<Integer>: Permuted arrayList of 1 to size
	 */
	public static ArrayList<Integer> generatePermuted(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, 1 to size to the returned arrayList.
		for (int i = 1; i <= size; i++) {
			returnArr.add(i);
		}
		
		// Now, the sorted list needs to be shuffled:
		Collections.shuffle(returnArr);
		
		return returnArr;
	}
	
	
	
	/**
	 * Generates an arrayList of 1 to size in descending order.
	 * 
	 * @param size: Size of the returned array.
	 * @return ArrayList<Integer>: The sorted arrayList in descending order from 1 to size
	 */
	public static ArrayList<Integer> generateDescending(int size) {
		
		ArrayList<Integer> returnArr = new ArrayList<Integer>();
		
		// Add a new element, size to 1 to the returned arrayList.
		for (int i = size; 0 < i; i--) {
			returnArr.add(i);
		}
		return returnArr;
	}
}
