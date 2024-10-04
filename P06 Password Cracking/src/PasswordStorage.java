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

import java.util.NoSuchElementException;

public class PasswordStorage {

  protected PasswordNode root; // the root of this BST that contains passwords
  private int size; // how many passwords are in the BST
  private final Attribute COMPARISON_CRITERIA; // what password information to use to determine
                                               // order in the tree

  /**
   * Constructor that creates an empty BST and sets the comparison criteria.
   * 
   * @param comparisonCriteria, the Attribute that will be used to determine order in the tree
   */
  public PasswordStorage(Attribute comparisonCriteria) {
    this.COMPARISON_CRITERIA = comparisonCriteria;
    size = 0;
  }

  /**
   * Getter for this BST's criteria for determining order in the three
   * 
   * @return the Attribute that is being used to make comparisons in the tree
   */
  public Attribute getComparisonCriteria() {
    return this.COMPARISON_CRITERIA;
  }

  /**
   * Getter for this BST's size.
   * 
   * @return the size of this tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Determines whether or not this tree is empty.
   * 
   * @return true if it is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0 && root == null;
  }

  /**
   * Provides in-order String representation of this BST, with each Password on its own line. The
   * String representation ends with a newline character ('\n')
   * 
   * @return this BST as a string
   */
  @Override
  public String toString() {
    if (isEmpty()) {
      return "";
    }
    return toStringHelper(this.root);
  }

  /**
   * Recursive method the uses an in-order traversal to create the string representation of this
   * tree.
   * 
   * @param currentNode, the root of the current tree
   * @return the in-order String representation of the tree rooted at current node
   */
  private String toStringHelper(PasswordNode currentNode) {
    String toReturn = "";


    if (currentNode == null) {
      return "";
    }
    toReturn += toStringHelper(currentNode.getLeft());

    toReturn += currentNode.getPassword().toString() + "\n";

    toReturn += toStringHelper(currentNode.getRight());

    return toReturn;
  }

  /**
   * Determines whether or not this tree is actually a valid BST.
   * 
   * @return true if it is a BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(this.root, Password.getMinPassword(), Password.getMaxPassword());
  }

  /**
   * Recurisvely determines if the the tree rooted at the current node is a valid BST. That is,
   * every value to the left of currentNode is "less than" the value in currentNode and every value
   * to the right of it is "greater than" it.
   * 
   * @param currentNode, the root node of the current tree
   * @param lowerBound,  the smallest possible password
   * @param upperBound,  the largest possible password
   * @return true if the tree rooted at currentNode is a BST, false otherwise
   */
  private boolean isValidBSTHelper(PasswordNode currentNode, Password lowerBound,
      Password upperBound) {
    // MUST BE IMPLEMENTED RECURSIVELY
    if (currentNode == null) {
      return true;
    }
    // BASE CASE 1: the tree rooted at currentNode is empty, which does not violate any BST rules
    else if (currentNode.getPassword().compareTo(upperBound, COMPARISON_CRITERIA) > 0
        || currentNode.getPassword().compareTo(lowerBound, COMPARISON_CRITERIA) < 0) {
      return false;
    }
    // BASE CASE 2: the current Password is outside of the upper OR lower bound for this subtree,
    // which is against
    // the rules for a valid BST

    // If we do not have a base case situation, we must use recursion to verify currentNode's child
    // subtrees
    if (currentNode.hasLeftChild()) {
      if (isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword()) == false) {
        return isValidBSTHelper(currentNode.getLeft(), lowerBound, currentNode.getPassword());
      }
    }
    // RECURSIVE CASE 1: Check that the left subtree contains only Passwords greater than lowerBound
    // and less than
    // currentNode's Password; return false if this property is NOT satisfied
    if (currentNode.hasRightChild()) {
      if (isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(),
          upperBound) == false) {
        return isValidBSTHelper(currentNode.getRight(), currentNode.getPassword(), upperBound);
      }
      // if (currentNode.getRight().getPassword().compareTo(currentNode.getPassword(),
      // COMPARISON_CRITERIA) < 0) {
      // return false;
      // }
      // toReturn = isValidBSTHelper(currentNode.getRight(), lowerBound, upperBound);
      // if (toReturn = false) {
      // return false;
      // }
    }
    // RECURSIVE CASE 2: Check that the right subtree contains only Passwords greater than
    // currentNode's Password
    // and less than upperBound; return false if this property is NOT satisfied

    // COMBINE RECURSIVE CASE ANSWERS: this is a valid BST if and only if both case 1 and 2 are
    // valid
    return true;
  }

  /**
   * Returns the password that matches the criteria of the provided key. Ex. if the COMPARISON
   * CRITERIA is OCCURRENCE and the key has an occurrence of 10 it will return the password stored
   * in the tree with occurrence of 10
   * 
   * @param key, the password that contains the information for the password we are searching for
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  public Password lookup(Password key) {
    return lookupHelper(key, root);
  }

  /**
   * Recursive helper method to find the matching password in this BST
   * 
   * @param key,         password containing the information we are searching for
   * @param currentNode, the node that is the current root of the tree
   * @return the Password that matches the search criteria, if it does not exist in the tree it this
   *         will be null
   */
  private Password lookupHelper(Password key, PasswordNode currentNode) {
    if (currentNode == null) {
      return null;
    }
    if (currentNode.getPassword().compareTo(key, COMPARISON_CRITERIA) == 0) {
      return currentNode.getPassword();
    } else if (key.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) < 0) {
      return (lookupHelper(key, currentNode.getLeft()));
    } else if (key.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) > 0) {
      return (lookupHelper(key, currentNode.getRight()));
    }
    return null;
  }

  /**
   * Returns the best (max) password in this BST
   * 
   * @return the best password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getBestPassword() {
    if (isEmpty()) {
      throw new NoSuchElementException("BST is empty");
    }
    // Base case

    PasswordNode toReturn = root.getRight();
    while (toReturn.hasRightChild()) {
      if (toReturn.getPassword().compareTo(toReturn.getRight().getPassword(),
          COMPARISON_CRITERIA) < 0) {
        toReturn = toReturn.getRight();
      }
    }
    // Recursive case 2: check the right subtree and compare it to toReturn
    return toReturn.getPassword();
  }

  /**
   * Returns the worst password in this BST
   * 
   * @return the worst password in this BST
   * @throws NoSuchElementException if the BST is empty
   */
  public Password getWorstPassword() {
    if (isEmpty()) {
      throw new NoSuchElementException("BST is empty");
    }
    // Base case

    PasswordNode toReturn = root.getLeft();
    while (toReturn.hasLeftChild()) {
      if (toReturn.getPassword().compareTo(toReturn.getLeft().getPassword(),
          COMPARISON_CRITERIA) > 0) {
        toReturn = toReturn.getLeft();
      }
    }
    // Recursive case 2: check the right subtree and compare it to toReturn
    return toReturn.getPassword();
  }

  /**
   * Adds the Password to this BST.
   * 
   * @param toAdd, the password to be added to the tree
   * @throws IllegalArgumentException if the (matching) password object is already in the tree
   */
  public void addPassword(Password toAdd) {
    boolean toReturn = addPasswordHelper(toAdd, this.root);
    if (toReturn == false) {
      throw new IllegalArgumentException("password is already in the tree");
    }
    size++;
  }

  /**
   * Recursive helper that traverses the tree and adds the password where it belongs
   * 
   * @param toAdd,       the password to add to the tree
   * @param currentNode, the node that is the current root of the (sub)tree
   * @return true if it was successfully added, false otherwise
   */
  private boolean addPasswordHelper(Password toAdd, PasswordNode currentNode) {
    boolean toReturn = true;
    if (isEmpty()) {
      this.root = new PasswordNode(toAdd);
      return true;
    }
    if (toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) == 0) {
      return false;
    }

    // Base case 1: object already exists in list
    if (toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) < 0
        && currentNode.hasLeftChild() == false) {
      currentNode.setLeft(new PasswordNode(toAdd));
      return true;
    } else if (toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) > 0
        && currentNode.hasRightChild() == false) {
      currentNode.setRight(new PasswordNode(toAdd));
      return true;
    }
    // Base case 2: there is no child left
    else {
      if (toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) < 0) {
        toReturn = addPasswordHelper(toAdd, currentNode.getLeft());
        if (toReturn == false) {
          return false;
        }
      }
      // case 1: there is left child
      else if (toAdd.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) > 0) {
        toReturn = addPasswordHelper(toAdd, currentNode.getRight());
        if (toReturn == false) {
          return false;
        }
      }
    }
    return toReturn;
  }

  /**
   * Removes the matching password from the tree
   * 
   * @param toRemove, the password to be removed from the tree
   * @throws NoSuchElementException if the password is not in the tree
   */
  public void removePassword(Password toRemove) {
    this.root = removePasswordHelper(toRemove, this.root);
    if (this.root == null) {
      throw new NoSuchElementException("password is not in the tree");
    }
    size--;
  }

  /**
   * Recursive helper method to that removes the password from this BST.
   * 
   * @param toRemove,    the password to be removed from the tree
   * @param currentNode, the root of the tree we are removing from
   * @return the PasswordNode representing the NEW root of this subtree now that toRemove has been
   *         removed. This may still be currentNode, or it may have changed!
   */
  private PasswordNode removePasswordHelper(Password toRemove, PasswordNode currentNode) {
    if (isEmpty()) {
      return null;
    }
    // BASE CASE: current tree is empty

    if (toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) < 0) {
      currentNode.setLeft(removePasswordHelper(toRemove, currentNode.getLeft()));
    }
    // RECURSIVE CASE: toRemove is in the left subtree, continue searching

    else if (toRemove.compareTo(currentNode.getPassword(), COMPARISON_CRITERIA) > 0) {
      currentNode.setRight(removePasswordHelper(toRemove, currentNode.getRight()));
    }
    // RECURSIVE CASE: toRemove is in the right subtree, continue searching

    else {
      // otherwise we found the node to remove!

      if (currentNode.hasLeftChild() == false && currentNode.hasRightChild() == false) {
        currentNode = null;
      }
      // BASE CASE: current node has no children

      else if (currentNode.hasLeftChild() && currentNode.hasRightChild() == false) {
        currentNode = currentNode.getLeft();
      } else if (currentNode.hasLeftChild() == false && currentNode.hasRightChild()) {
        currentNode = currentNode.getRight();
      }
      // BASE CASE(S): current node has one child (one for the left and right respectively)

      else {
        PasswordNode predecessor = getPredecessor(currentNode);
        currentNode = new PasswordNode(predecessor.getPassword(), currentNode.getLeft(),
            currentNode.getRight());
        currentNode.setLeft(removePasswordHelper(predecessor.getPassword(), currentNode.getLeft()));
        // RECURSIVE CASE: currentNode has 2 children


        // 1)Find the predecessor password [HINT: Write a private helper method!]
        // 2)Make new node for the predecessor password. It should have same left and right subtree
        // as the current node.
        // 3)Replace currentNode with the new predecessor node
        // 4)Remove the (duplicate) predecessor from the current tree and update the left subtree
      }
    }
    return currentNode; // LEAVE THIS LINE AS IS
  }

  private PasswordNode getPredecessor(PasswordNode currentNode) {
    currentNode = currentNode.getLeft();
    while (currentNode.getRight() != null) {
      currentNode = currentNode.getRight();
    }
    return currentNode;

  }

}
