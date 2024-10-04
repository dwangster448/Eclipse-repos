///////////////////////////////////////////////////////////////////////////////
//
// Title: Password Cracking
// Course: CS 300 Summer 2023
//
// Author: Daniel Wang
// Email: dwang448@wisc.edu
// Lecturer: Michelle Jensen
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Class to represent a binary search tree (BST) node that contains only Password objects.
 * 
 * @author Michelle & Daniel Wang
 */
public class PasswordNode {

  private Password password; // the password data this node stores
  private PasswordNode left; // a reference to node that is the left child
  private PasswordNode right; // a reference to the node that is the right child

  /**
   * 1-argument constructor that sets the only data of the node.
   * 
   * @param password the password for this node to store
   * 
   * @author Michelle
   */
  public PasswordNode(Password password) {
    this.password = password;
  }

  /**
   * 3-argument constructor that sets all three data field
   * 
   * @param password, password the password for this node to store
   * @param left,     the reference to the node that is the left child
   * @param right,    the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode(Password password, PasswordNode left, PasswordNode right) {
    this(password);
    this.left = left;
    this.right = right;
  }

  /**
   * Setter for left data field
   * 
   * @param left, the reference to the node to be the left child
   * 
   * @author Michelle
   */
  public void setLeft(PasswordNode left) {
    this.left = left;
  }

  /**
   * Setter for right data field
   * 
   * @param right, the reference to the node to be the right child
   * 
   * @author Michelle
   */
  public void setRight(PasswordNode right) {
    this.right = right;
  }

  /**
   * Getter for left data field
   * 
   * @return the reference to the node that is the left child
   * 
   * @author Michelle
   */
  public PasswordNode getLeft() {
    return this.left;
  }

  /**
   * Getter for right data field
   * 
   * @return the reference to the node that is the right child
   * 
   * @author Michelle
   */
  public PasswordNode getRight() {
    return this.right;
  }

  /**
   * Getter for password data field
   * 
   * @return the password object that this node stores
   * 
   * @author Michelle
   */
  public Password getPassword() {
    return this.password;
  }

  /**
   * Determines if the current node is a leaf node
   * 
   * @return true if this node is a leaf, false otherwise
   * 
   * @author Daniel Wang
   */
  public boolean isLeafNode() {
    return this.left == null && this.right == null;
  }

  /**
   * Determines if the current node has a right child
   * 
   * @return true if this node has a right child, false otherwise
   * 
   * @author Daniel Wang
   */
  public boolean hasRightChild() {
    return this.right != null;
  }

  /**
   * Determines if the current node has a left child
   * 
   * @return true if this node has a left child, false otherwise
   * 
   * @author Daniel Wang
   */
  public boolean hasLeftChild() {
    return this.left != null;
  }

  /**
   * Determines how many children nodes this node has. RECALL: Nodes in a binary tree can have AT
   * MOST 2 children
   * 
   * @return The number of children this node has
   * 
   * @author Daniel Wang
   */
  public int numberOfChildren() {
    if (hasLeftChild() && hasRightChild()) {
      return 2;
    } else if (hasLeftChild() || hasRightChild()) {
      return 1;
    } else {
      return 0;
    }
  }
}
