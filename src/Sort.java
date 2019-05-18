/*****************************************************/
/*** Purpose:                                      ***/
/***                                               ***/
/***     Initial Author: Jason Steggles 20/09/17   ***/
/***     Extended by: Ivan Mykolenko 25/10/17      ***/
/*****************************************************/

import java.io.*;
import java.text.*;
import java.util.*;

public class Sort {

	/** Array of integers to sort **/
	private int[] A;

	/** Size of the array **/
	private int size;

	/** Number of elements actually used in array **/
	private int usedSize;

	/** Global variables for counting sort comparisons **/
	private int compIS;
	/** Global comparison count for Insertion Sort **/
	private int compQS;
	/** Global comparison count for Quicksort **/
	private int compNewS;

	/** Global comparison count for new sort **/

	/*****************/
	/** Constructor **/
	/*****************/

	Sort(int max) {
		/** Initialiase global sort count variables **/
		compIS = 0;
		compQS = 0;
		compNewS = 0;
		/** Initialise size variables **/
		usedSize = 0;
		size = max;
		/** Create Array of Integers **/
		A = new int[size];
	}

	/* Getter for the array */
	public int[] getA() {
		return A;
	}

	/*********************************************/
	/*** Read a file of integers into an array ***/
	/*********************************************/
	public void readIn(String file) {
		try {
			/** Initialise loop variable **/
			usedSize = 0;
			/** Set up file for reading **/
			FileReader reader = new FileReader(file);
			Scanner in = new Scanner(reader);
			/** Loop round reading in data while array not full **/
			while (in.hasNextInt() && (usedSize < size)) {
				A[usedSize] = in.nextInt();
				usedSize++;
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Error processing file " + file);
		}
	}

	public void insertion() { /* Insertion Sort */
		int j, key;
		for (int i = 1; i < size /*-1*/; i++) { /* For all except first element... */
			key = A[i];
			j = i;
			// compIS++;/* Increase comparison counter */
			while (j > 0 && key < A[j - 1]) { /* Find a correct position for key */
				compIS++; /* Increase comparison counter: successful comparison */
				A[j] = A[j - 1]; /* Crate space */
				j = j - 1;
			}
			compIS++; /* Increase comparison counter: unsuccessful comparison */

			A[j] = key; /* Insert key */
		}
	}

	private int partition(int left, int right) {
		int pivot = A[right];
		int pL = left;
		int pR = right;
		int tmp; /* Temporary value for swaping */

		while (pL < pR) {
			while (A[pL] < pivot) { /* Move left pointer untill value greater than pivot found */
				pL = pL + 1;
				compQS++; /* Increase comparison counter */
			}
			compQS++; /* Increase comparison counter: unsuccessful comparison */
			while (A[pR] >= pivot && pR > left) { /* Move left pointer untill value smaller than pivot found */
				pR = pR - 1;
				compQS++; /* Increase comparison counter */
			}
			compQS++; /* Increase comparison counter: unsuccessful comparison */
			if (pL < pR) { /* If pointers havent crossed, swap values */
				tmp = A[pL];
				A[pL] = A[pR];
				A[pR] = tmp;
			}
		}
		tmp = A[pL]; /* Place pivot into correct position */
		A[pL] = A[right];
		A[right] = tmp;
		return pL;
	}

	public void quick(int left, int right) {
		int pivot;

		if (right > left) { /* If there is more than 1 element in partition */
			pivot = partition(left, right);
			quick(left, pivot - 1);
			quick(pivot + 1, right);

		}
	}

	public void newSort() {
		int pos = 0;
		int min, tmp, i;
		while (pos < size) { /* Through all elements */
			min = findMinFrom(pos); /* Find next minimal value */
			for (i = pos; i < size; i++) { /* Through the remaining elements */
				compNewS++;/* Successful/Unsuccessful case increment counter */
				if (A[i] == min) { /* Put the next minimal value into a correct possition */
					tmp = A[i];
					A[i] = A[pos];
					A[pos] = tmp;
					pos++;

				}

			}
		}

	}

	private int findMinFrom(int pos) {
		int min = A[pos];
		int i;
		for (i = pos + 1; i < size; i++) { /* Thought the rest of the array elements */
			compNewS++; /* Successful/Unsuccessful case increment counter */
			if (A[i] < min) { /* If found smaller value - override min */
				min = A[i];
			}
		}
		return min;
	}

	/**********************/
	/*** Display array ***/
	/**********************/
	public void display(int line, String header) {
		/*** Integer Formatter - three digits ***/
		NumberFormat FI = NumberFormat.getInstance();
		FI.setMinimumIntegerDigits(3);

		/** Print header string **/
		System.out.print(header);

		/** Display array data **/
		for (int i = 0; i < usedSize; i++) {
			/** Check if new line is needed **/
			if (i % line == 0) {
				System.out.println();
			}
			/** Display an array element **/
			// System.out.print(FI.format(A[i]) + " ");
			System.out.print(A[i] + " ");
		}
	}

	/* Getters */
	public int getCompIS() {
		return compIS;
	}

	public int getCompQS() {
		return compQS;
	}

	public int getCompNewS() {
		return compNewS;
	}
} /** End of Sort Class **/