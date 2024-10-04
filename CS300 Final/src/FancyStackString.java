//////////////////////////// UNIT QUIZ FILE HEADER /////////////////////////////
// Full Name: (fill this out)
// Campus ID: (fill this out)
// WiscEmail: (fill this out)
////////////////////////////////////////////////////////////////////////////////

// TODO fill the above UNIT QUIZ FILE HEADER out!!!

import java.util.EmptyStackException;
// NO Additional imports are allowed.

////////////////////////////////////////////////////////////////////////////////
//
// This file contains ONE class named FancyStackString. The implementation of this class
// requires the use of a provided generic class named DoublyLinkedNode defined in a
// separate file. You will need to complete the implementation of the FancyStackString
// class with respect to the provided requirements in the TODO tags for full credit.
////////////////////////////////////////////////////////////////////////////////
//
// DO NOT make changes to the provided method signatures.
// DO NOT make changes to the the fully implemented provided methods.
// String comparisons if applicable are CASE SENSITIVE.
//
// No variables outside of any method should be added to this class.
//
// No additional methods should be added to this class.
//
// You are NOT required to submit a perfect solution. Do your best to submit a
// source file with no compiler errors within the allotted time.
//
// SIX methods SHOULD be completed in this file:
// Constructor of the class FancyStackString
// isEmpty()
// size()
// peek()
// push()
// pop()
//
// BE SURE TO SAVE YOUR SOURCE FILE BEFORE SUBMITTING IT TO GRADESCOPE
//
////////////////////////////////////////////////////////////////////////////////


/**
 * This class models a doubly linked stack implemented as a connection of DoublyLinkedNodes.
 * Elements stored in this fancy stack are of type String, and the top of this stack is its head.
 * 
 * This fancy stack is defined by its head and tail references of type DoublyLinkedNode, and an int
 * primitive type field to keep track of the size of the stack.
 * 
 * The head of the linked stack represents its top, meaning that elements are pushed and popped from
 * the head of the stack. In addition, only the element at the head of the stack is directly
 * accessible by calling the peek method.
 * 
 * This fancy stack is implemented using a dummy node at its head (the top of the stack).
 * 
 * The head of the stack always refers to the dummy node.
 * 
 * The data stored in the dummy node is NOT part of the contents of the stack.
 * 
 * The size of the stack does NOT include the dummy node.
 * 
 * Elements are pushed after the dummy node.
 * 
 * The String stored at the dummy node is "dummy".
 * 
 * The peek method returns the next of the dummy node.
 * 
 * For instance, if "A", "B", and "C" are pushed in this order to a FancyStackString named stack,
 * the linked node representation of the connected nodes in the stack is as follows:
 * 
 * (top of the stack) head -> dummy <-> C <-> B <-> A <- tail
 * 
 * Note carefully that: head does not have a previous node and tail does not have a next node.
 */
public class FancyStackString {

  private DoublyLinkedNode<String> head; // head of the linked list implementing this stack.
  private DoublyLinkedNode<String> tail; // tail of the linked list implementing this stack.
  // The head reference represents the top of this stack, meaning the extremity of the list from
  // where elements are pushed in and popped off the stack.

  private int size; // Total number of elements stored in this stack. This excludes the dummy node

  /**
   * Creates a new empty FancyStackString
   */
  public FancyStackString() {
    // TODO complete the implementation of this constructor
    DoublyLinkedNode<String> dummy = new DoublyLinkedNode<String>("dummy");
    this.head = dummy;
    this.tail = dummy;
    // Create a new DoublyLinkedNode whose data is "dummy" and has no next or previous nodes

    // Set both head and tail to reference the created node
    size = 0;
    // Set size to zero [OPTIONAL since zero is the default value for int]
  }


  /**
   * Checks whether this stack is empty, meaning contains zero elements.
   * 
   * @return true if the size of this stack is zero
   */
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }

    return false; // default return statement added to resolve compiler errors
  }

  /**
   * Gets the number of elements stored in this stack. The dummy node is not included in the size of
   * the stack.
   * 
   * @return the size of this stack
   */
  public int size() {
    return this.size; // default return statement added to resolve compiler errors
  }

  /**
   * Returns the element at the top of this fancy stack, meaning the data at the next node of the
   * dummy node.
   * 
   * @return the string at the top of this fancy stack, meaning at the next of the head.
   * 
   * @throws EmptyStackException if this stack is empty
   */
  public String peek() {
    if (isEmpty())
      throw new EmptyStackException();

    return this.head.getNext().getData(); // default return statement added to resolve compiler
                                          // errors
  }

  /**
   * Adds toAdd to the top (head) of this fancy doubly linked stack.
   * 
   * @param toAdd new element to be pushed at the top of this stack.
   * @throws IllegalArgumentException if toAdd equals "dummy"
   * @throws NullPointerException     if toAdd is null or blank
   */
  public void push(String toAdd) {
    if (toAdd == null || toAdd.isBlank()) {
      throw new NullPointerException(
          "It is NOT allowed to push null references or blank strings " + "into this stack.");
    }
    if (toAdd.equals("dummy")) {
      throw new IllegalArgumentException(
          "It is NOT allowed to push the string \"dummy\" into " + "this stack.");
    }
    // TODO complete the implementation of this method
    DoublyLinkedNode<String> toAddNode = new DoublyLinkedNode<String>(toAdd);
    toAddNode.setNext(this.head.getNext());
    this.head.setNext(toAddNode);
    // insert toAdd at the top (head) of this stack and connect nodes correctly
    // Keep in mind that the head always refers to the dummy node.
    // Drawing diagrams of doubly connected nodes helps implementing this behavior
    if (isEmpty()) {
      size = 1;
    } else {
      size++;
    }
    // increment size


  }

  /**
   * Returns and removes the element at the top (head) of this stack, meaning the next of the dummy
   * node.
   * 
   * The dummy node must not be removed from the stack!
   * 
   * @return the string at the top (head) of this fancy stack, excluding the dummy node.
   * 
   * @throws EmptyStackException if this stack is empty
   */
  public String pop() {
    if (isEmpty())
      throw new EmptyStackException();
    // TODO complete the implementation of this method
    // Keep in mind that the top of this stack is its head .
    // The head always refers to the dummy node. The element to be removed is at the next of the
    // head.

    // 1. save the data to be returned
    String toReturn = this.head.getNext().getData();
    // 2. remove the node storing the element at the top of the stack and connect nodes accordingly.
    // The head is always referencing the dummy node.
    this.head.setNext(this.head.getNext().getNext());
    // Drawing diagrams of doubly connected nodes helps implementing this behavior
    if (size == 1) {
      size = 0;
    } else {
      size--;
    }

    // 3. decrement size

    // 4. return the saved data

    return toReturn; // default return statement added to resolve compiler errors
  }

  /**
   * Returns a string representation of the data stored in all the nodes connected in this fancy
   * stack starting from its top (head) including the dummy node. Strings are separated by one
   * space.
   * 
   * @return a string representation of the data stored in all the nodes connected in this fancy
   *         stack.
   * 
   */
  @Override
  public String toString() {
    String s = "";
    DoublyLinkedNode<String> current = head; // current points to the top of this stack
    // traverse the stack from its head to its head
    while (current != null) {
      s += current.getData() + " "; // add the data at the current node
      current = current.getNext(); // advance current
    }
    return s.trim();
  }

  /**
   * Demo for this class
   */
  public static void demo() {
    FancyStackString stack = new FancyStackString();
    System.out.println("Empty newly created stack: " + stack.isEmpty());
    stack.push("e0"); // e0 pushed at the head of stack
    stack.push("e1"); // e1 pushed at the head of stack
    stack.push("e2"); // e2 pushed at the head of stack
    stack.push("e3"); // e3 pushed at the head of stack
    System.out.println("stack.peek(): " + stack.peek());
    System.out.println("stack.size(): " + stack.size());
    System.out
        .println("String representation of the nodes connected in this stack starting from head:");
    System.out.println(stack);

    // Expected displayed output:
    // Empty newly created stack: true
    // stack.peek(): e3
    // stack.size(): 4
    // String representation of the nodes connected in this stack starting from head:
    // dummy e3 e2 e1 e0

  }

  /**
   * Main method to run the demo
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    demo();
  }
}
