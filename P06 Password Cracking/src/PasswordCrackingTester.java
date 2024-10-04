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

import java.util.NoSuchElementException; // (Tester & PasswordStorage)
import java.math.BigInteger; // (Password)
import java.security.MessageDigest; // (Password
import java.security.NoSuchAlgorithmException; // (Password)

public class PasswordCrackingTester {

  /**
   * Validates the constructor and accessor methods of PasswordStorage, specifically the
   * getComparisonCriteria(), size(), and isEmpty() methods, as well as accessing the protected data
   * field root.
   * 
   * Be sure to try making multiple PasswordStorage objects with different Attributes.
   * 
   * @return true if the basic accessor methods work as expected, false otherwise
   */
  public static boolean testBasicPasswordStorageMethods() {
    {
      PasswordStorage test = new PasswordStorage(Attribute.OCCURRENCE);
      if (test.size() != 0) {
        return false;
      }
      if (test.getComparisonCriteria() != Attribute.OCCURRENCE) {
        return false;
      }
      if (test.isEmpty() != true) {
        return false;
      }
      if (test.toString() != "") {
        return false;
      }
    }
    return true;
  }

  /**
   * Validates the Password class compareTo() method. Create at least two DIFFERENT Password objects
   * and compare them on each of the Attribute values. See the writeup for details on how the
   * various comparisons are expected to work.
   * 
   * @return true if Password's compareTo() works as expected, false otherwise
   */
  public static boolean testPasswordCompareTo() {
    {
      Password password1 = new Password("pa$$w0rd", 100);
      Password password2 = new Password("longUniquePass", 2);
      int comparison = password1.compareTo(password2, Attribute.OCCURRENCE);
      if (comparison <= 0) {
        return false;
      }
    }
    {
      Password password1 = new Password("pa$$w0rd", 100);
      Password password2 = new Password("longUniquePass", 2);
      int comparison = password1.compareTo(password2, Attribute.STRENGTH_RATING);
      if (comparison > 0) {
        return false;
      }
    }
    {
      Password password1 = new Password("pa$$w0rd", 100);
      Password password2 = new Password("longUniquePass", 2);
      int comparison = password1.compareTo(password2, Attribute.HASHED_PASSWORD);
      if (comparison <= 0) {
        return false;
      }
    }
    return true; // TODO
  }

  /**
   * Validates the incomplete methods in PasswordNode, specifically isLeafNode(),
   * numberOfChildren(), hasLeftChild() and hasRightChild(). Be sure to test all possible
   * configurations of a node in a binary tree!
   * 
   * @return true if the status methods of PasswordNode work as expected, false otherwise
   */
  public static boolean testNodeStatusMethods() {
    {
      PasswordNode node1 = new PasswordNode(new Password("pa$$w0rd", 100));
      PasswordNode node2 = new PasswordNode(new Password("longUniquePass", 2));
      node1.setLeft(node2);
      boolean testLeaf = node1.isLeafNode();
      if (testLeaf != false) {
        return false;
      }
      boolean testLeftChild = node1.hasLeftChild();
      if (testLeftChild != true) {
        return false;
      }
      int testChildren = node1.numberOfChildren();
      if (testChildren != 1) {
        return false;
      }
    }
    {
      PasswordNode node1 = new PasswordNode(new Password("pa$$w0rd", 100));
      PasswordNode node2 = new PasswordNode(new Password("longUniquePass", 2));
      node1.setRight(node2);
      boolean testLeaf = node1.isLeafNode();
      if (testLeaf != false) {
        return false;
      }
      boolean testRightChild = node1.hasRightChild();
      if (testRightChild != true) {
        return false;
      }
      int testChildren = node1.numberOfChildren();
      if (testChildren != 1) {
        return false;
      }
    }
    {
      PasswordNode node1 = new PasswordNode(new Password("pa$$w0rd", 100));
      boolean testLeaf = node1.isLeafNode();
      if (testLeaf != true) {
        return false;
      }
      int testChildren = node1.numberOfChildren();
      if (testChildren != 0) {
        return false;
      }
    }
    return true; // TODO
  }

  /**
   * Indirectly validates the toString method in Passowrd by checking if nodes were properly added
   * into the BST with scenarios: empty BST, root node, and addition of passwords
   * 
   * @return true if the toString method work as expected, false otherwise
   */
  public static boolean testToString() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty is empty string
      String expected = "";
      String actual = bst.toString();
      if (!actual.equals(expected)) {
        System.out.println("toString() does not return the proper value on an empty tree!");
        return false;
      }

      // size one only returns 1 thing
      Password p = new Password("1234567890", 15000);
      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      expected = p.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;


      // big tree returns in-order traversal
      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      PasswordNode p4Node = new PasswordNode(p4);
      PasswordNode p3Node = new PasswordNode(p3);
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p6Node = new PasswordNode(p6, p7Node, null);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      expected = p4.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p.toString()
          + "\n" + p5.toString() + "\n" + p7.toString() + "\n" + p6.toString() + "\n";
      actual = bst.toString();
      if (!actual.equals(expected))
        return false;

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Validates whether the isValidBST method in PasswordStorage works as expected with different
   * scenarios: empty BST, root node, addition of passwords
   * 
   * @return true if the isValidBST method work as expected, false otherwise
   */
  public static boolean testIsValidBST() {
    try {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      // empty tree is a valid bst
      /*
       * String expected = ""; String actual = bst.toString();
       */
      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that an empty tree is not a valid BST!");
        return false;
      }

      // size one is a bst
      Password p = new Password("1234567890", 1000);
      PasswordNode rootNode = new PasswordNode(p);

      bst.root = rootNode; // here I am manually building the tree by editing the root node
      // directly to be the node of my choosing

      if (!bst.isValidBST()) {
        System.out.println("isValidBST() says that a tree of size 1 is not a valid BST!");
        return false;
      }

      Password p2 = new Password("test", 500);
      Password p3 = new Password("iloveyou", 765);
      Password p4 = new Password("qwerty", 250);
      Password p5 = new Password("admin", 1002);
      Password p6 = new Password("password", 2232);
      Password p7 = new Password("abc123", 2090);

      // works on indentifying small obviously invalid tree
      PasswordNode p7Node = new PasswordNode(p7);
      PasswordNode p3Node = new PasswordNode(p3);
      rootNode = new PasswordNode(p, p7Node, p3Node);
      bst.root = rootNode;
      if (bst.isValidBST())
        return false;

      // tree with only one subtree being valid, other subtree has a violation a couple layers deep


      PasswordNode p4Node = new PasswordNode(p4);
      p7Node = new PasswordNode(p7);
      p3Node = new PasswordNode(p3);
      PasswordNode p6Node = new PasswordNode(p6, null, p7Node);
      PasswordNode p5Node = new PasswordNode(p5, null, p6Node);
      PasswordNode p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (bst.isValidBST()) {
        System.out
            .println("isValidBST() says that a tree with only one valid subtree is a valid bst");
        return false;
      }


      // works on valid large tree
      p4Node = new PasswordNode(p4);
      p3Node = new PasswordNode(p3);
      p7Node = new PasswordNode(p7);
      p6Node = new PasswordNode(p6, p7Node, null);
      p5Node = new PasswordNode(p5, null, p6Node);
      p2Node = new PasswordNode(p2, p4Node, p3Node);
      rootNode = new PasswordNode(p, p2Node, p5Node);
      bst.root = rootNode;

      if (!bst.isValidBST())
        return false;

      // violates search order property further down the tree and comes from a node in a left
      // subtree
      PasswordNode one = new PasswordNode(p4);
      PasswordNode three = new PasswordNode(p3, one, null);
      PasswordNode two = new PasswordNode(p2, null, three);
      bst.root = two;

      if (bst.isValidBST()) {
        System.out.println("bad bst is valid");
        return false;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }

    return true;
  }

  /**
   * Validates whether the lookupHelper method in PasswordStorage works as expected when we add
   * passwords to a BST
   * 
   * @return true if the lookupHelper method work as expected, false otherwise
   */
  public static boolean testLookup() {
    {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1", 1000);
      Password p4 = new Password("2", 150);
      Password p5 = new Password("3", 1100);
      Password p7 = new Password("4", 1600);

      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode;
      bst.addPassword(p5);
      bst.addPassword(p7);
      bst.addPassword(p4);

      Password expected = p7;
      Password actual = bst.lookup(p7);

      if (actual == null) {
        return false;
      }
      if (!(actual.compareTo(expected, bst.getComparisonCriteria()) == 0))
        return false;
    }
    {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1", 900);
      Password p2 = new Password("2", 600);
      Password p4 = new Password("3", 300);
      Password p5 = new Password("4", 1000);

      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode;
      bst.addPassword(p5);
      bst.addPassword(p2);
      bst.addPassword(p4);

      Password expected = p4;
      Password actual = bst.lookup(p4);

      if (actual == null) {
        return false;
      }
      if (!(actual.compareTo(expected, bst.getComparisonCriteria()) == 0))
        return false;
    }
    return true;
  }

  /**
   * Validates whether the addPassword method in PasswordStorage works as expected by checking every
   * node in the BST every addition
   * 
   * @return true if the addPassword method work as expected, false otherwise
   */
  public static boolean testAddPassword() {
    {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1", 900); // first
      Password p6 = new Password("2", 1900); // third
      Password p7 = new Password("3", 1500); // second

      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode;
      if (bst.root.getPassword().compareTo(p, bst.getComparisonCriteria()) != 0) {
        return false;
      }
      try {
        bst.addPassword(p7);
        PasswordNode toCheck = bst.root;
        if (toCheck.getRight().getPassword().compareTo(p7, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.addPassword(p6);
        toCheck = bst.root.getRight();
        if (toCheck.getRight().getPassword().compareTo(p6, bst.getComparisonCriteria()) != 0) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    return true; // TODO
  }

  /**
   * Validates whether the removePassword method in PasswordStorage works as expected by checking
   * whether the BSt nodes are connected properly after a node with child nodes is removed
   * 
   * @return true if the addPassword method work as expected, false otherwise
   */
  public static boolean testRemovePassword() {
    {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1", 1010); // first
      Password p6 = new Password("2", 2200); // third
      Password p7 = new Password("3", 2000); // second

      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode;
      if (bst.root.getPassword().compareTo(p, bst.getComparisonCriteria()) != 0) {
        return false;
      }
      try {
        bst.addPassword(p7);
        PasswordNode toCheck = bst.root;
        if (toCheck.getRight().getPassword().compareTo(p7, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.addPassword(p6);
        toCheck = bst.root.getRight();
        if (toCheck.getRight().getPassword().compareTo(p6, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.removePassword(p7);
        toCheck = bst.root;
        if (toCheck.getRight().getPassword().compareTo(p6, bst.getComparisonCriteria()) != 0) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    {
      PasswordStorage bst = new PasswordStorage(Attribute.OCCURRENCE);

      Password p = new Password("1", 1000); // first
      Password p5 = new Password("2", 1010); // fourth
      Password p6 = new Password("3", 2200); // third
      Password p7 = new Password("4", 2100); // second

      PasswordNode rootNode = new PasswordNode(p);
      bst.root = rootNode;
      if (bst.root.getPassword().compareTo(p, bst.getComparisonCriteria()) != 0) {
        return false;
      }
      try {
        bst.addPassword(p7);
        PasswordNode toCheck = bst.root;
        if (toCheck.getRight().getPassword().compareTo(p7, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.addPassword(p6);
        toCheck = bst.root.getRight();
        if (toCheck.getRight().getPassword().compareTo(p6, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.addPassword(p5);
        toCheck = bst.root.getRight();
        if (toCheck.getRight().getPassword().compareTo(p6, bst.getComparisonCriteria()) != 0) {
          return false;
        }
        bst.removePassword(p7);
        toCheck = bst.root;
        if (toCheck.getRight().getPassword().compareTo(p5, bst.getComparisonCriteria()) != 0) {
          return false;
        }
      } catch (NullPointerException e) {
        return false;
      }
    }
    return true;
  }

  /**
   * Implements the call to all of the tests
   * 
   * @param args none
   */
  public static void main(String[] args) {
    runAllTests();
  }

  /**
   * stores the results of each tester methods and prints the results accordingly
   * 
   * @param args none
   */
  public static boolean runAllTests() {
    boolean compareToPassed = testPasswordCompareTo();
    boolean nodeStatusPassed = testNodeStatusMethods();
    boolean basicMethodsPassed = testBasicPasswordStorageMethods();
    boolean toStringPassed = testToString();
    boolean isValidBSTPassed = testIsValidBST();
    boolean lookupPassed = testLookup();
    boolean addPasswordPassed = testAddPassword();
    boolean removePasswordPassed = testRemovePassword();

    System.out.println("Password compareTo: " + (compareToPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordNode Status Methods: " + (nodeStatusPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage Basic Methods: " + (basicMethodsPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage toString: " + (toStringPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage isValidBST: " + (isValidBSTPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage lookup: " + (lookupPassed ? "PASS" : "FAIL"));
    System.out.println("PasswordStorage addPassword: " + (addPasswordPassed ? "PASS" : "FAIL"));
    System.out
        .println("PasswordStorage removePassword: " + (removePasswordPassed ? "PASS" : "FAIL"));

    // AND ANY OTHER ADDITIONAL TEST METHODS YOU MAY WANT TO WRITE!

    return compareToPassed && nodeStatusPassed && basicMethodsPassed && toStringPassed
        && isValidBSTPassed && lookupPassed && addPasswordPassed && removePasswordPassed;
  }

}
