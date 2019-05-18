import java.util.Scanner;

/*************************************************/
/*** Simple test class for Sort class          ***/
/***                                           ***/
/*** Author:  Ivan Mykolenko 25/10/17          ***/
/*************************************************/

public class TestSort {
	private Scanner scanner = new Scanner(System.in);
	private int testNumber, arrayLength;
	private final int NO_OF_ELEMENTS_IN_ONE_LINE = 25;

	private void requestInput() {
		System.out.println("\nEnter the test you would like to run (1-5) or enter 'exit' to terminate.");
		inputCheck();
	}

	private void inputCheck() {
		if (!scanner.hasNextInt()) {/*If input value is not an integer*/
			if (scanner.hasNext("exit")) { /*Terminate if "exit"*/
				System.exit(0);
			} else {
				System.err.println("Incorrect input!");/*Else incorrect"*/
				scanner.nextLine(); // Discard junk entries
				requestInput();
			}
		} else {
			testNumber = scanner.nextInt();
			if (testNumber < 1 || testNumber > 5) { /*Check appropriateness of input"*/
				System.out.println("Test " + testNumber + " does not exist!");
				requestInput();

			} else { 
				if (testNumber > 2) {  /*Assign array length*/
					arrayLength = 100;
				} else {
					arrayLength = 15;
				}
				runTest();
				scanner.close();
			}
		}
	}

	private void runTest() {
		Sort test = new Sort(arrayLength);
		System.out.println("===============Test-" + testNumber + "==============");
		readIn(test); // Read in test data
		if (testNumber < 3) {
			doInsertionSort(test);/*Launch sorting algorithm*/
			readIn(test);/** Reset array **/
			doQuickSort(test);/*Launch sorting algorithm*/
		} else if (testNumber == 5) {
			doInsertionSort(test);/*Launch sorting algorithm*/
			readIn(test);/** Reset array **/
			doNewSort(test);/*Launch sorting algorithm*/
		} else {
			doInsertionSort(test);/*Launch sorting algorithm*/
			readIn(test);/** Reset array **/
			doQuickSort(test);/*Launch sorting algorithm*/
			readIn(test);/** Reset array **/
			doNewSort(test);/*Launch sorting algorithm*/
		}
		requestInput();
	}

	private void readIn(Sort test) { /** Reset array **/
		test.readIn("test" + testNumber + ".txt"); /** Read in test data into array **/
	}

	private void doInsertionSort(Sort test) {
		test.insertion(); /*Launch sorting algorithm*/
		System.out.println("Insertion sort comparison counter: " + test.getCompIS());
		test.display(NO_OF_ELEMENTS_IN_ONE_LINE, "Insertion sort output array:");/** Display array **/
	}

	private void doQuickSort(Sort test) {
		test.quick(0, test.getA().length - 1);/*Launch sorting algorithm*/
		System.out.println("\n\nQuicksort comparison counter: " + test.getCompQS());
		test.display(NO_OF_ELEMENTS_IN_ONE_LINE, "Quicksort output array:");/** Display array **/
	}

	private void doNewSort(Sort test) {
		test.newSort();/*Launch sorting algorithm*/
		System.out.println("\n\nNewSort comparison counter: " + test.getCompNewS());
		test.display(NO_OF_ELEMENTS_IN_ONE_LINE, "New sort output array:");/** Display array **/
	}

	public static void main(String[] args) {
		TestSort main = new TestSort();
		main.requestInput(); /*Acessing non-static method through an instance of TestSort class*/
	}
}