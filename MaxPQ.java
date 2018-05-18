//import java.io.FileNotFoundException;
import java.util.Arrays;
//import java.util.InputMismatchException;

public class MaxPQ<E extends Comparable<E>> implements PriorityQueueADT<E>{

	private E[] items;
	private static final int INITIAL_SIZE = 10;
	private int itemsCount = 0; 


	public MaxPQ() {
		this.items = (E[]) new Comparable[INITIAL_SIZE];
	}

	/**
	 * This method is used to retrieve the parent of a given node. 
	 * @param childIndex
	 * @return parentIndex
	 **/
	private int getParent(int childIndex) {
		// return the parent index of the given child index 
		return childIndex/2;
	}
	
	/**
	 * This method is used to retrieve the left child of a given node. 
	 * @param parentIndex
	 * @return leftChildIndex
	 **/
	private int getLeftChild(int parentIndex) {
		// return the left child index of the given parent index
		return parentIndex*2;
	}

	/**
	 * This method is used to retrieve the right child of a given node. 
	 * @param parentIndex
	 * @return rightChildIndex
	 **/
	private int getRightChild(int parentIndex) {
		// return the right child index of the given parent index
		return (parentIndex*2) + 1;
	}

	/**
	 * This method swaps the elements of specified indices of the items array 
	 * @param itemOneIndex
	 * @param itemTwoIndex
	 **/
	private void swap(int itemOneIndex, int itemTwoIndex) {
		// swaps the Item references at itemOneIndex and itemTwoIndex in the items array
		E temp = this.items[itemOneIndex];
		this.items[itemOneIndex] = this.items[itemTwoIndex];
		this.items[itemTwoIndex] = temp;
	}
	
	/**
	 * This method checks if the priority queue is empty. Basically, it checks all the indices of the array for any 
	 * items and returns false if it found anything.
	 *@return isEmpty
	 **/
	@Override
	public boolean isEmpty() {
		boolean isEmpty = true;
		for(int i = 0; i < items.length ; i++) {
			if(items[i] != null) {
				isEmpty = false;
				break;
			}
		}
		return isEmpty;
	}

	/**
	 * This method is the helper method for the insert function. This method acts as a percolation tool. It checks if the item in the 
	 * current node is larger than the item in the parent node and swaps. By doing so it preserves the order property. It uses recursion to 
	 * do accomplish this task.
	 * @param index
	 * @return void
	 **/
	private void insertHelper(int index) {
		int parentIndex = getParent(index);
		if(index == 1 || items[parentIndex].compareTo(items[index]) > 0) {
			return;
		}
		else {
			swap(parentIndex,index);
			insertHelper(parentIndex);
		}
	}
	
	/**
	 * This method inserts new items into the priority queue. The first item is inserted into index 1 instead of index 0 to follow
	 * the convention. Moreover, this method triples the array size after it is completely filled. This method makes use of the helper method 
	 * insertHelper() to preserve the heap properties. 
	 * @param item
	 * @return void
	 * @throws IllegalArgumentException - This happens when the item being inserted is null
	 **/
	@Override
	public void insert(E item) {
		if(item != null) {
			if(itemsCount < this.items.length - 1) {
				this.itemsCount ++;
				this.items[itemsCount] = item;
				insertHelper(itemsCount);
			}
			else {
				E[] tempItems = Arrays.copyOf(items, itemsCount*3);
				items = tempItems;
				this.itemsCount++;
				this.items[itemsCount] = item;
				insertHelper(itemsCount); 		
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * This method returns the maxValue of the priority queue. It does so by returning the element in index number 1 of the array.
	 * @return E item
	 * @throws EmptyQueueException - This happens when trying to getMax of an empty PQ
	 **/
	@Override
	public E getMax() throws EmptyQueueException {
		if(!isEmpty()) {
			return items[1];
		}
		else {
			throw new EmptyQueueException("Warning: The PQ is empty. Cannot return the maxValue in the Queue");
		}

	}
	
	/**
	 * This method is the helper method for the remove function. This method acts as a percolation down tool. It checks if the item in the 
	 * current node is smaller than the item in the child node and swaps. By doing so it preserves the order property. It uses recursion to 
	 * do accomplish this task.
	 * @param index
	 * @return void
	 **/
	private void removeMaxHelper(int index) {
		//Decide which child to use
		if(getLeftChild(index) <= itemsCount){
			int childIndexToUse;
			if(getRightChild(index) > itemsCount || this.items[getLeftChild(index)].compareTo(this.items[getRightChild(index)]) > 0) {
				childIndexToUse = getLeftChild(index);
			}
			else {
				childIndexToUse = getRightChild(index);
			}
			//recursively swap the parent and child
			if(this.items[childIndexToUse].compareTo(this.items[index]) < 0) {
				return;
			}
			else {
				swap(childIndexToUse, index);
				removeMaxHelper(childIndexToUse);
			}
		}
	}
	
	/**
	 * This method removes and returns the maxValue in the priority queue. It basically copies the element in the index 1 of the 
	 * array in a temp variable, assigns the first index to value in the last index, makes the last index null and uses the helper method to
	 * preserve the heap order property. Finally, it return the temp variable. 
	 * @return E item - Max Value
	 * @throws EmptyQueueException - This happens when trying to remove Max value from an empty PQ
	 **/
	@Override
	public E removeMax() throws EmptyQueueException {
		if(isEmpty() == false) {
			E itemToRemove = items[1];
			items[1] = items[itemsCount];
			items[itemsCount] = null;
			itemsCount --;
			removeMaxHelper(1);
			return itemToRemove;
		}
		else {
			throw new EmptyQueueException("The queue you are trying to remove from is empty");
		}
	}

	/**
	 * This method returns the size of PQ. It does this easily as we keep track of the number of items inserted. 
	 * @return size
	 **/
	@Override
	public int size() {

		return itemsCount;
	}



}