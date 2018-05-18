//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           P01: MaxPQ
// Files:           MaxPQ.java, TestMaxPQ.java
// Course:          CS400, Spring Semester, 2018
//
// Author:          Venkata Raghu Vamsi Yadala
// Email:           yadala@wisc.edu
// Lecturer's Name: Debra Deppeler
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    -
// Partner Email:   -
// Lecturer's Name: -
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully
// acknowledge and credit those sources of help here.  Instructors and TAs do
// not need to be credited here, but tutors, friends, relatives, room mates
// strangers, etc do.  If you received no outside help from either type of
// source, then please explicitly indicate NONE.
// Persons:         NONE
// Online Sources:  NONE
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
//import java.io.File;
import java.util.Arrays;

public class TestMaxPQ {

	public static void main(String[] args) {
		int passed = 0;
		int testNumber = 1;
		System.out.println("===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		if(test01_Insert01_RemoveMany() == true) {
			passed ++; 
			testNumber ++;
			System.out.println("TEST01 PASSED: EmptyQueueException was thrown");
		}
		else {
			testNumber++;
			System.out.println("TEST01 FAILED: EmptyQueueException was not thrown");
		}
		System.out.println("\n" + "===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		if(test02_InsertRemoveMany() == true) {
			passed ++;
			testNumber ++;
			System.out.println("TEST02 PASSED: Items were correctly inserted and removed");
		}
		else {
			testNumber ++;
			System.out.println("TEST02 FAILED: Items described above were not correctly inserted and removed");
		}
		System.out.println("\n" + "===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		if(test03_getMax() == true) {
			passed ++;
			testNumber ++;
			System.out.println("TEST03 PASSED: getMax returned the correct item");
		}
		else {
			testNumber ++;
			System.out.println("TEST03 FAILED: getMax returned an incorrect item.");
		}
		System.out.println("\n" + "===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		if(test04_isEmpty() == true) {
			passed ++;
			testNumber ++;
			System.out.println("TEST04 PASSED: The functionality of isEmpty is correct");
		}
		else {
			testNumber ++;
			System.out.println("TEST04 FAILED: The functionality of isEmpty is incorrect");
		}
		//System.out.println();
		System.out.println("\n" + "===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		//System.out.println();
		if(test05_size() == true) {
			passed ++;
			testNumber ++;
			System.out.println("TEST05 PASSED: The functionality of size is correct");
		}
		else {
			testNumber ++;
			System.out.println("TEST05 FAILED: The functionality of size is incorrect");
		}
		//System.out.println();
		System.out.println("\n" + "===============" + "TEST NUMBER:" + testNumber + "===============" + "\n");
		//System.out.println();
		if(test06_getMaxEmptyPQ() == true) {
			passed ++;
			testNumber ++;
			System.out.println("TEST06 PASSED: getMax has correctly returned an exception");
		}
		else {
			testNumber ++;
			System.out.println("TEST06 FAILED: getMax failed to throw an exception");
		}
		System.out.println("\n" + "===============" + "===RESULTS===" + "===============" + "\n");
		System.out.println("Tests passed: " + passed);
		System.out.println("Total Tests: " + (testNumber-1));
	}

	/**
	 * This test method creates a priority queue and inserts just one random number into the queue but attempts to remove the max 
	 * more than once. This checks if the removeMax() is throwing an exception properly after the queue has been emptied
	 * @return boolean passed
	 **/
	private static boolean test01_Insert01_RemoveMany() {
		System.out.println("TEST01: This test inserts 1 item into the priority queue and tries to remove more than one");	
		boolean passed = false;
		int [] unsorted = {(int)(Math.random()*101)};
		PriorityQueueADT<Integer> pq = new MaxPQ<Integer>();
		pq.insert(unsorted[0]); 
		try {
			for(int i = 0; i < (int)(Math.random()*11 + 2); i++) {
				pq.removeMax();
			}
		}
		catch(EmptyQueueException e) {
			passed = true;	
			System.out.println("Initial Array : {" + unsorted[0] + "}");
			e.printStackTrace();
		}
		finally {	
			return passed;
		}	
	}

	/**
	 * This test method creates a priority queue and inserts a random number of random numbers into the queue and attempts to remove the max.
	 * This not only checks if the insertion is correct but also checks if the removeMax() is working properly and checks if the array is being 
	 * expanded needed. Because of the randomness of this method, acceptance of duplicates are also checked!
	 * @return boolean passed
	 **/
	private static boolean test02_InsertRemoveMany() {
		String removeMaxReturnValueStore = "";       //String Variable to concatenate the values returned by removeMax 
		String unsortedValueStore = "";				//String Variable to concatenate the values of the unsorted Array 
		String sortedValueStore = "";				//String Variable to concatenate the values of the sorted Array 
		System.out.println("TEST02: This test inserts a random number of items in the priority queue and removes the max "
				+ "and checks the correctness");
		boolean passed = true;
		int [] unsorted = new int[(int)(Math.random() * 101 + 1)];
		for(int i = 0; i < unsorted.length; i++) {
			unsorted[i] = (int)(Math.random() *101);
			unsortedValueStore = unsortedValueStore + Integer.toString(new Integer(unsorted[i])) + ",";
		}
		int [] sorted = unsorted.clone();
		Arrays.sort(sorted);
		PriorityQueueADT<Integer> pq2 = new MaxPQ<Integer>();
		for (int u : unsorted) pq2.insert(new Integer(u));
		for (int i = sorted.length-1; i >= 0; i--) {
			sortedValueStore = sortedValueStore + Integer.toString(sorted[i]) + "," ;
			Integer expected = sorted[i];
			Integer actual = pq2.removeMax();
			if (expected.equals(actual)) {
				removeMaxReturnValueStore = removeMaxReturnValueStore + Integer.toString(actual) + "," ;
			} 
			else {
				passed = false;
				System.out.println("insert and remove operation failed:");
				System.out.println("       expected: " + expected);
				System.out.println("         actual: " + actual + "\n");
			}
		}
		System.out.println("    input values: " + "{" + unsortedValueStore + "}");
		System.out.println(" inserted values: " + "{" + removeMaxReturnValueStore + "}");
		System.out.println(" expected values: " + "{" + sortedValueStore + "}");
		return passed;
	}

	/**
	 * This test method checks if the getMax() method is returning the correct value by comparing the answer with a sorted array. 
	 * @return boolean passed
	 **/
	private static boolean test03_getMax() {
		String getMaxReturnValueStore = "";
		String unsortedValueStore = "";
		String sortedValueStore = "";
		System.out.println("Test03: This test inserts a random number of items,"
				+ " removes the maxValue periodically and checks for the new maxValue");
		boolean passed = true;
		int [] unsorted = new int[(int)(Math.random() * 101 + 1)];
		for(int i = 0; i < unsorted.length; i++) {
			unsorted[i] = (int)(Math.random() *101);
			unsortedValueStore = unsortedValueStore + Integer.toString(new Integer(unsorted[i])) + ",";
		}
		int [] sorted = unsorted.clone();
		Arrays.sort(sorted);
		PriorityQueueADT<Integer> pq3 = new MaxPQ<Integer>();

		for (int u : unsorted) pq3.insert(new Integer(u));
		for (int i = sorted.length-1; i >= 0; i--) {
			sortedValueStore = sortedValueStore + Integer.toString(sorted[i]) + "," ;
			Integer expected = sorted[i];
			Integer actual = pq3.getMax();
			pq3.removeMax();

			if (expected.equals(actual)) {
				getMaxReturnValueStore = getMaxReturnValueStore + Integer.toString(actual) + "," ;
			} 
			else {
				passed = false;
				System.out.println("insert and remove operation failed:");
				System.out.println("       expected: " + expected);
				System.out.println("         actual: " + actual + "\n");
			}
		}
		System.out.println("    input values: " + "{" + unsortedValueStore + "}");
		System.out.println(" inserted values: " + "{" + getMaxReturnValueStore + "}");
		System.out.println(" expected values: " + "{" + sortedValueStore + "}");
		return passed;
	}

	/**
	 * This test method creates a priority queue and calls the isEmpty. Next, it creates another PQ and inserts random 
	 * numbers in it and calls the isEmpty(). 
	 * @return boolean passed
	 **/
	private static boolean test04_isEmpty(){
		System.out.println("Test04: This test checks the functionality of isEmpty() by calling the method on an empty and a non"
				+ "emepty queue");
		boolean passed;
		PriorityQueueADT<Integer> pq4 = new MaxPQ<Integer>();
		PriorityQueueADT<Integer> pq5 = new MaxPQ<Integer>();
		for(int i =0; i < 10; i++) {
			pq5.insert((int)(Math.random()*11));
		}

		if(pq4.isEmpty() == true && pq5.isEmpty() != true) {
			passed = true;
			System.out.println("isEmpty() operation has passed:");
			System.out.println("       expected for empty queue: " + true);
			System.out.println("         actual for empty queue: " + pq4.isEmpty());
			System.out.println("   expected for non-empty queue: " + false);
			System.out.println("     actual for non-empty queue: " + pq5.isEmpty());
			
			
		}
		else {
			passed = false;
			System.out.println("isEmpty() operation has failed:");
			System.out.println("       expected for empty queue: " + true);
			System.out.println("         actual for empty queue: " + pq4.isEmpty());
			System.out.println("   expected for non-empty queue: " + false);
			System.out.println("     actual for non-empty queue: " + pq5.isEmpty());
		}
		
		return passed;

	}

	/**
	 * This test method tests if the size() method of the priority queue is returning the right size by comapring it against the length of the 
	 * array with unsorted data. Because the length of the array itself is randomized and the range has been set b/w 0 - 10, 
	 * every possibility is tested including 0 elements.  
	 * numbers in it and calls the isEmpty(). 
	 * @return boolean passed
	 **/
	private static boolean test05_size() {
		String unsortedValueStore = "";
		System.out.println("Test05: This test inserts a random number of items into the PQ and checks the functionality of size");
		boolean passed;
		int [] unsorted = new int[(int)(Math.random() * 11)];
		for(int i = 0; i < unsorted.length; i++) {
			unsorted[i] = (int)(Math.random() *101);
			unsortedValueStore = unsortedValueStore + Integer.toString(new Integer(unsorted[i])) + ",";
		}
		PriorityQueueADT<Integer> pq5 = new MaxPQ<Integer>();
		for (int u : unsorted) pq5.insert(new Integer(u));
		if(pq5.size() == unsorted.length) {
			passed = true;
			System.out.println("size() operation has passed:");
			System.out.println("          Input Values:" + "{" + unsortedValueStore + "}");
			System.out.println("Number of Input Values:" + unsorted.length);
			System.out.println("         actual: " + pq5.size());
		}
		else {
			passed = false;
			System.out.println("size() operation hhas failed:");
			System.out.println("          Input Values:" + "{" + unsortedValueStore + "}");
			System.out.println("Number of Input Values:" + unsorted.length);
			System.out.println("         actual: " + pq5.size());

		}

		return passed;
	}

	/**
	 * This test method checks if the getMax() method is throwing an exception for an empty PQ. 
	 * @return boolean passed
	 **/
	private static boolean test06_getMaxEmptyPQ() {
		System.out.println("Test06: This test creates an empty queue and calls getMax on it");
		boolean passed = false;
		PriorityQueueADT<Integer> pq6 = new MaxPQ<Integer>();
		try {
			pq6.getMax();
		}
		catch(EmptyQueueException e) {
			passed = true;
			System.out.println("Initial Array:" + "{}");
			e.printStackTrace();
		}
		finally {
			return passed;
		}
	}



} 