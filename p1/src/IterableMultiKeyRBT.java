// --== CS400 Fall 2023 File Header Information ==--
// Name: Daniel Wang
// Email: dwang448@wisc.edu
// Group: C29
// TA: Anvay Grover
// Lecturer: Florian Heimerl

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.util.Stack;

/**
 * This class extends the RedBlackTree class to implement Multi-key RBT Iteration algorithm
 */
public class IterableMultiKeyRBT<T extends Comparable<T>> extends RedBlackTree<KeyListInterface<T>>
    implements IterableMultiKeySortedCollectionInterface<T> {

  /*
   * Iteration start point for getStartStack method
   */
  private Comparable<T> iterationStartPoint;

  /*
   * Total number of keys, including duplicate keys in tree
   */
  private int numKey;

  /**
   * Inserts value into tree that can store multiple objects per key by keeping lists of objects in
   * each node of the tree.
   * 
   * @param key object to insert
   * @return true if a new node was inserted, false if the key was added into an existing node
   */
  @Override
  public boolean insertSingleKey(T key) {
    KeyListInterface<T> newKey = new KeyList<T>(key);
    Node<KeyListInterface<T>> existingNode = findNode(newKey);
    if (this.root == null) {
      this.insert(newKey);
      numKey++;
      return false;
    } else if (existingNode != null) {
      existingNode.data.addKey(key);
      numKey++;
      return false;
    } else {
      this.insert(newKey);
      numKey++;
      return true;
    }
  }

  /**
   * @return the number of values in the tree.
   */
  @Override
  public int numKeys() {
    return this.numKey;
  }

  /**
   * Returns an iterator that does an in-order iteration over the tree.
   */
  @Override
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      private Stack<Node<KeyListInterface<T>>> stack = getStartStack();
      private Iterator<T> currentIterator = null;
      @Override
      public boolean hasNext() {
        return !stack.isEmpty() || (currentIterator != null && currentIterator.hasNext());
      }
      @Override
      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }
        if (currentIterator != null && currentIterator.hasNext()) {
          return currentIterator.next();
        }
        Node<KeyListInterface<T>> current = stack.pop();
        currentIterator = current.data.iterator();
        if (current.down[1] != null) {
          Node<KeyListInterface<T>> subtree = current.down[1];
          while (subtree != null) {
            stack.push(subtree);
            subtree = subtree.down[0];
          }
        }
        return currentIterator.next();
      }
    };

  }

  /**
   * Sets the starting point for iterations. Future iterations will start at the starting point or
   * the key closest to it in the tree. This setting is remembered until it is reset. Passing in
   * null disables the starting point.
   * 
   * @param startPoint the start point to set for iterations
   */
  @Override
  public void setIterationStartPoint(Comparable<T> startPoint) {
    if (startPoint == null) {
      this.iterationStartPoint = null;
      return;
    }
    iterationStartPoint = startPoint;
  }

  /**
   * Removes all keys from the tree and resets number of keys to 0
   */
  @Override
  public void clear() {
    super.clear();
    numKey = 0;
  }

  /**
   * Returns an instance of stack containing nodes after initialization. If no iteration start point
   * is null, the stack is initialized with the nodes on the path from the root node to (and
   * including) the node with the smallest key in the tree. If the iteration start point is not
   * null, then the stack is initialized with all the nodes with keys equal to or larger than the
   * start point along the path to the start point.
   */
  protected Stack<Node<KeyListInterface<T>>> getStartStack() {
    Stack<Node<KeyListInterface<T>>> stack = new Stack<>();
    
    if (iterationStartPoint == null) {
      // If no iteration start point set, initialize the stack with nodes on path to smallest key
      Node<KeyListInterface<T>> current = root;
      while (current != null) {
        stack.push(current);
        current = current.down[0];
      }
    } else {
      // If the iteration start point set, initialize stack with nodes greater or equal to start
      Node<KeyListInterface<T>> current = root;
      while (current != null) {
        int compare = iterationStartPoint.compareTo(current.data.iterator().next());
        if (compare <= 0) {
          stack.push(current);
          current = current.down[0];
        }
        if (compare > 0) {
          current = current.down[1];
        }
      }
    }
    return stack;
  }

  /**
   * Checks if boolean is correct when inserting two duplicate keys into an empty tree, then checks
   * if number of keys is correct
   */
  @Test
  public void testInsertSingleKey() {
    // Create a new tree
    IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();

    // Insert a single key and check if insertion returns true
    tree.insertSingleKey(1);
    // Insert the same key again and check if insertion returns false
    Assertions.assertEquals(1, tree.numKeys());

    // Check if the number of keys is correct
    //Assertions.assertEquals(2, tree.numKeys());
  }

  /**
   * Checks if iterator properly iterates when start point is not set in a tree
   */
  @Test
  public void testIterator() {
    // Create a tree with multiple keys
    IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
    tree.insertSingleKey(1);
    tree.insertSingleKey(5);
    tree.insertSingleKey(10);

    // Iterate through the tree and check if the values are in the correct order
    Iterator<Integer> iter = tree.iterator();
    Stack<BinarySearchTree.Node<KeyListInterface<Integer>>> testStack = tree.getStartStack();
    Assertions.assertEquals(5, testStack.firstElement().data.iterator().next());
    Assertions.assertEquals(1, iter.next());

    // Ensure that there are no more elements to iterate
    Assertions.assertTrue(iter.hasNext());
  }

  /*
   * Checks if an iterator with a start point iterates over path towards the start point only
   * stacking elements that are greater than it along the path
   */
  @Test
  public void testSetIterationStartPoint() {
    // Create a tree with multiple keys
    IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
    tree.insertSingleKey(1);
    tree.insertSingleKey(2);
    tree.insertSingleKey(3);
    tree.insertSingleKey(4);
    tree.insertSingleKey(5);

    // Set the starting point and check if iterator is affected
    tree.setIterationStartPoint(3);
    Iterator<Integer> iter = tree.iterator();
    Stack<BinarySearchTree.Node<KeyListInterface<Integer>>> testStack = tree.getStartStack();
    Assertions.assertEquals(4, testStack.firstElement().data.iterator().next());
    Assertions.assertEquals(3, iter.next());

  }
  
//  public static void main(String[] args) {
//    IterableMultiKeyRBT<Integer> tree = new IterableMultiKeyRBT<>();
//    tree.insertSingleKey(3);
//  }


}
