// -- RedBlackTree --
// Name: Daniel Wang
// Email: dwang448@wisc.edu
// Group: C29
// TA: Anvay Grover
// Lecturer: Florian Heimerl

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
 * This class extends the BinarySearchTree class to implement RBT insertion algorithm
 * 
 */
public class RedBlackTree<T extends Comparable<T>> extends BinarySearchTree<T>
    implements SortedCollectionInterface<T> {

  /**
   * This class represents a RBT node holding a single value within a RBT tree.
   * 
   */
  protected static class RBTNode<T> extends Node<T> {
    public int blackHeight = 0;

    public RBTNode(T data) {
      super(data);
    }

    public RBTNode<T> getUp() {
      return (RBTNode<T>) this.up;
    }

    public RBTNode<T> getDownLeft() {
      return (RBTNode<T>) this.down[0];
    }

    public RBTNode<T> getDownRight() {
      return (RBTNode<T>) this.down[1];
    }
  }

  /**
   * Resolves any RBT violations after inserting a new red node
   * 
   */
  protected void enforceRBTreePropertiesAfterInsert(RBTNode<T> newNode) {
    if (newNode == null) {
      return;
    }

    // 1st violation: root note is not black
    if (newNode.getUp() == null) { // base case: there is no nodes above this
      newNode.blackHeight = 1;
      return;
    }

    RBTNode<T> grandparent = newNode.getUp().getUp();
    RBTNode<T> parent = newNode.getUp();
    // tree isn't larger than height of 2, proceed to work on aunt related violation
    if (grandparent != null) {
      // parent is on the left subtree of grandparent
      if (parent.equals(grandparent.getDownLeft())) {
        // case 2: aunt (on the right side) is black
        if (grandparent.getDownRight() == null || grandparent.getDownRight().blackHeight == 1) {
          if (newNode.equals(parent.getDownRight())) {
            grandparent.down[0] = newNode;
            newNode.up = grandparent;
            newNode.down[0] = parent;
            parent.up = newNode;
            parent.down[1] = null;
            rotate(newNode, grandparent);
            newNode.blackHeight = 1;
            grandparent.blackHeight = 0;
          } else {
            rotate(parent, grandparent);
            // relocated parent node (now root of current tree) changed to black
            parent.blackHeight = 1; 
            grandparent.blackHeight = 0; // relocated grandparent node changed to red
          }
          // case 1: aunt (on the right side) is red
        } else if (grandparent.getDownRight().blackHeight == 0) {
          RBTNode<T> aunt = grandparent.getDownRight();
          aunt.blackHeight = 1;
          parent.blackHeight = 1;
          enforceRBTreePropertiesAfterInsert(grandparent); // check violations at root of this tree
        }

      } else { // parent is on the right subtree of grandparent
        // case 2: aunt (on the left side) is black
        if (grandparent.getDownLeft() == null || grandparent.getDownLeft().blackHeight == 1) {
          if (newNode.equals(parent.getDownLeft())) {
            grandparent.down[1] = newNode;
            newNode.up = grandparent;
            newNode.down[1] = parent;
            parent.up = newNode;
            parent.down[0] = null;
            rotate(newNode, grandparent);
            newNode.blackHeight = 1;
            grandparent.blackHeight = 0;
          } else {
            rotate(parent, grandparent);
            // relocated parent node (now root of current tree) changed to black
            if (root == parent) {
              parent.up = null;
            }
            parent.blackHeight = 1;
            grandparent.blackHeight = 0; // relocated grandparent node changed to red
          }
          // case 1: aunt (on the left side) is red
        } else if (grandparent.getDownLeft().blackHeight == 0) {
          RBTNode<T> aunt = grandparent.getDownLeft();
          aunt.blackHeight = 1;
          parent.blackHeight = 1;
          enforceRBTreePropertiesAfterInsert(grandparent); // check violations at root of this tree
        }
      }

    } else { // there is only the node's parent above this node;
      enforceRBTreePropertiesAfterInsert(parent);
    }


  }
  
  /**
   * Inserts a new data value into the tree.
   * This tree will not hold null references, nor duplicate data values.
   * @param data to be added into this RBT tree
   * @return true if the value was inserted, false if is was in the tree already
   * @throws NullPointerException when the provided data argument is null
   */
  @Override
  public boolean insert(T data) throws NullPointerException {
    if (data == null)
      throw new NullPointerException("Cannot insert data value null into the tree.");
    RBTNode<T> newNode = new RBTNode<>(data); // Create a new red node
    insertHelper(newNode); // Insert the new node using the helper method
    enforceRBTreePropertiesAfterInsert(newNode); // Enforce Red-Black Tree properties

    // After enforcing properties, set the root node to black
    if (newNode.up == null) {
      // newNode.setColor("Black");
    }
    return true;
  }

  /**
   * Tester method checks if root node is black and children nodes are red
   * 
   */
  @Test
  public void testCase1() {
    RBTNode<Integer> root = new RBTNode<>(12);
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.root = root;
    tree.insert(10);
    tree.insert(15);
    // Check that the root node all nodes have the correct color
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownLeft().blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownRight().blackHeight);
  }

  /**
   * This is a tester method to test that there are no consecutive red nodes
   * 
   */
  @Test
  public void testCase2() {
    Node<Integer> root = new RBTNode<>(12);
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.root = root;
    tree.insert(15);
    tree.insert(10);
    tree.insert(3);
    // Check that no consecutive red nodes exist
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).getDownRight().blackHeight);
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).getDownLeft().blackHeight);
    Assertions.assertEquals(0,
        ((RBTNode<Integer>) tree.root).getDownLeft().getDownLeft().blackHeight);
  }

  /**
   * This is a tester method to test that tree is balanced in an only-decreasing RBT tree
   * 
   */
  @Test
  public void testCase3() {
    Node<Integer> root = new RBTNode<>(11);
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.root = root;
    tree.insert(9);
    tree.insert(10);
    // Check that the tree structure is balanced
    Assertions.assertEquals(1, ((RBTNode<Integer>) tree.root).blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownRight().blackHeight);
    Assertions.assertEquals(0, ((RBTNode<Integer>) tree.root).getDownLeft().blackHeight);
  }

}
