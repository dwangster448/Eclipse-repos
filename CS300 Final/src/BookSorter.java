import java.util.Arrays;

///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: DANIEL WANG
// Campus ID: 9084072710
// WiscEmail: dwang448@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// This file contains ONE utility class and ONE data type class. You will need to 
// complete the implementation of the utility class with respect to the provided 
// requirements in the TODO tags for full credit.
//
// You should not modify the data type class in any way.
// 
// You are not required to add error checking to any of these methods.
////////////////////////////////////////////////////////////////////////////////

/**
 *  This class contains a single public method: sortBooks(), along with 
 *  several helper methods and two tests.  This public method uses the heap sort
 *  algorithm to sort book titles in ascending order.
 *  
 *  // TODO #1-9 below ask you to finish implementing the following methods
 *  (only make edits below each of these numbered TODOs, as described for each):
 *  peekBook() - returns smallest title from the root position of the min-heap
 *  percolateDown() - recursively enforces heap ordering down from specified index
 *  heapify() - iteratively moves values from a randomly-ordered array into heap order
 *  removeBook() - removes book value from root position in heap, and returns new size
 *  sortBooks() - computes and returns a sorted array of books values
 *  
 *  Additionally, the following helper and test methods are provided:
 *  swap() - swaps the values at two different positions within an array
 *  bestOf() - returns the smallest of its three string/null arguments
 *  testHeapify() - returns true when this test passes and false otherwise
 *  testSort() - returns true when this test passes and false otherwise
 *  main() - calls and prints out the value returned from test()
 */
public class BookSorter {

  /**
   * Returns book title from the root position in the min-heap (lexicographically earliest title).
   * We suppose that the provided heap array is NOT empty.
   * 
   * @param heap a provided min-heap
   * @return book title from the root position in the min-heap
   */
  private static Book peekBook(Book[] heap) {
    // TODO #1: return element from the root position within the zero-indexed min-heap
    return heap[0]; // default return statement added to avoid compiler errors. Feel free to change it.
  }

  /**
   * Recursive helper method which implements percolateDown operation to restore the heap order
   * property if it is violated at a given index position of a provided min-heap.
   * 
   * @param index position of the element to start the percolate-down operation from
   * @param heap  a provided min-heap array
   * @param size  of the heap
   */
  private static void percolateDown(int index, Book[] heap, int size) {
    if (index >= size)
      return; // cannot percolate further below bottom of heap
    // compute indexes of left and right children positions within heap
    int leftChildIndex = 2 * index + 1;
    int rightChildIndex = 2 * index + 2;

    // store values from index, left, and right child positions into separate variables
    Book indexValue = heap[index];
    Book rightChildValueOrNull = null;
    if (rightChildIndex < size)
      rightChildValueOrNull = heap[rightChildIndex];
    Book leftChildValueOrNull = null;
    if (leftChildIndex < size)
      leftChildValueOrNull = heap[leftChildIndex];

    // compute highest priority value between index and its left and right children
    Book best = bestOf(indexValue, rightChildValueOrNull, leftChildValueOrNull);

    if (best.equals(leftChildValueOrNull)) {
      // TODO #2: implement percolate down through left subtree
      percolateDown(leftChildIndex, heap, size);
    } else if (best.equals(rightChildValueOrNull)) {
      // TODO #3: implement percolate down through right subtree
      percolateDown(rightChildIndex, heap, size);
    }
  }

  /**
   * Reorganizes an array of Book values into a valid min heap.
   * This heapify operation does NOT operate in-place.
   * 
   * @param values a provided oversize array of values, in no particular order
   * @param size number of values in the provided oversize array
   * @return a DEEP COPY of the values array, in min-heap order
   */
  private static Book[] heapify(Book[] values, int size) {
    Book[] heap = Arrays.copyOf(values, size);

    int heapifyIndex = 0; // TODO #4: replace with index of last internal node in values
    heapifyIndex = (size-2)/2;
    while (heapifyIndex >= 0) {
      // TODO #5: move the value at heapifyIndex to the correct location
      percolateDown(heapifyIndex, values, size);
      // TODO #6: update heapifyIndex to continue the process
      heapifyIndex--;
    }

    return heap;
  }

  /**
   * Removes book from root position in heap, and then returns heap's new size.
   * Assume that the provided array is NOT empty and is a valid min-heap.
   * 
   * @param heap a provided min-heap
   * @param size of heap before removeBook is called.
   * @return size is the new size of heap after removeBook is performed
   */
  private static int removeBook(Book[] heap, int size) {
    // TODO #7: remove the root element from the provided heap, correct
    // heap order violations, and then return the resulting heap size
    swap(heap, 0, size - 1);
    size--;
    percolateDown(size -1, heap, size);
    return size; // default return statement added to avoid compiler errors. Feel free to change it.
  }

  /**
   * Sorts an array of books in lexicographic order using a min-heap. This sort operation
   * does NOT operate in-place. Assume that the provided array is NOT empty.
   * 
   * @param titles an array of Book objects, not necessarily in heap order.
   * @return reference to the sorted array
   */
  public static Book[] sortBooks(Book[] titles, int size) {
    int heapSize = titles.length;
    // Sorted is an over sized array that will be filled with Books
    // in sorted order. When it is full, it will be returned.
    Book[] heap = heapify(titles, size);
    Book[] sorted = new Book[heap.length];
    int sortedSize = 0;

    while (heapSize > 0) {
      // TODO #8: insert best element from heap into earliest free position in sorted array
      sorted[sortedSize] = heap[sortedSize];
      sortedSize++;
      heapSize--;
      // TODO #9: remove the best value from the heap
      removeBook(titles, size);
    }

    return sorted;
  }

  //////////////////////////// UTILITY METHODS, PROVIDED ///////////////////////

  /**
   * Swaps the elements at indexes i and j in the provided array
   * 
   * @param array in which elements are being moved
   * @param i     the first index being changed in that array
   * @param j     the second index being changed in that array
   */
  private static void swap(Book[] array, int i, int j) {
    Book temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

  /**
   * Returns the lexicographically earliest Book title between the provided arguments. Null arguments
   * are ignored (considered lexicographically later than Books).
   * 
   * @param a, b, c are the Books being compared
   * @return the lexicographically earliest of those Books
   * @throws IllegalArgumentException when all Books are null
   */
  private static Book bestOf(Book a, Book b, Book c) {
    if (a != null && 
        (b == null || a.compareTo(b) <= 0) && 
        (c == null || a.compareTo(c) <= 0))
      return a;
    else if (b != null && 
        (a == null || b.compareTo(a) <= 0) && 
        (c == null || b.compareTo(c) <= 0))
      return b;
    else if (c != null && 
        (a == null || c.compareTo(a) <= 0) && 
        (b == null || c.compareTo(b) <= 0))
      return c;
    else
      throw new IllegalArgumentException("All of the Books were null");
  }
  
  /**
   * One short and simple test for the heapify method.
   * 
   * @return true when this test passes, otherwise false
   */
  private static boolean testHeapify() {
    try {
      Book[] randomValues = new Book[] {
          new Book("Big-O Analysis"),
          new Book("OO Design"),
          new Book("DataStructures"),
          new Book("Algorithms"),
          new Book("Debugging"), 
          new Book("Abstract Data Types")
      };
      Book[] heapOfBooks = new Book[] {
          new Book("Abstract Data Types"), 
          new Book("Algorithms"), 
          new Book("Big-O Analysis"),
          new Book("OO Design"),
          new Book("Debugging"),
          new Book("DataStructures")
      };
      
      System.out.println("Expected: "+Arrays.deepToString(heapOfBooks));
      System.out.println("Actual:   "+
             Arrays.deepToString(heapify(randomValues, randomValues.length)));

      if(!Arrays.deepEquals(heapify(randomValues, randomValues.length), heapOfBooks))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }

  /**
   * One short and simple test for the sortBooks method.
   * 
   * @return true when this test passes, otherwise false
   */
  private static boolean testSort() {
    try {
      Book[] heapOfBooks = new Book[] {
          new Book("Abstract Data Types"), 
          new Book("Big-O Analysis"), 
          new Book("Algorithms"),
          new Book("OO Design"), 
          new Book("Debugging"), 
          new Book("DataStructures")
      };
      Book[] sortedBooks = new Book[] {
          new Book("Abstract Data Types"), 
          new Book("Algorithms"), 
          new Book("Big-O Analysis"),
          new Book("DataStructures"), 
          new Book("Debugging"), 
          new Book("OO Design")
      };
      
      System.out.println("Expected: "+Arrays.deepToString(sortedBooks));
      System.out.println("Actual:   "+
             Arrays.deepToString(sortBooks(heapOfBooks, heapOfBooks.length)));

      if(!Arrays.deepEquals(sortBooks(heapOfBooks, heapOfBooks.length), sortedBooks))
        return false;

    } catch (Exception e) {
      return false;
    }
    return true;
  }
  
  /**
   * Driver for the test method above.
   * 
   * @param args is unused
   */
  public static void main(String[] args) {
    System.out.println("testHeapify(): " + testHeapify());
    System.out.println("testSort(): " + testSort());
  }

}

/**
 * Object being sorted in our heapsort methods above
 */
class Book implements Comparable<Book> {
  private String title;
  public Book(String t) { this.title = t; }
  @Override
  public String toString() { return ""+this.title; }
  @Override
  public int compareTo(Book o) { return this.title.compareTo( ((Book)o).title ); }
  @Override
  public boolean equals(Object o) { 
    if (o instanceof Book) return ((Book)o).title.equals(this.title); 
    return false;
  }
}