import java.util.LinkedList;
import java.util.Stack;


/**
 * Binary Search Tree implementation with a Node inner class for representing
 * the nodes of the tree. We will turn this Binary Search Tree into a self-balancing
 * tree as part of project 1 by modifying its insert functionality.
 * In week 0 of project 1, we will start this process by implementing tree rotations.
 */
public class BinarySearchTree<T extends Comparable<T>> implements SortedCollectionInterface<T> {

    /**
     * This class represents a node holding a single value within a binary tree.
     */
    protected static class Node<T> {
        

        public T data;

        // up stores a reference to the node's parent
        public Node<T> up;
        // The down array stores references to the node's children:
        // - down[0] is the left child reference of the node,
        // - down[1] is the right child reference of the node.
        // The @SupressWarning("unchecked") annotation is use to supress an unchecked
        // cast warning. Java only allows us to instantiate arrays without generic
        // type parameters, so we use this cast here to avoid future casts of the
        // node type's data field.
        @SuppressWarnings("unchecked")
        public Node<T>[] down = (Node<T>[])new Node[2];
        public Node(T data) { this.data = data; }
        
        /**
         * @return true when this node has a parent and is the right child of
         * that parent, otherwise return false
         */
        public boolean isRightChild() {
            return this.up != null && this.up.down[1] == this;
        }
        
        

    }


    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Inserts a new data value into the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if is was in the tree already
     * @throws NullPointerException when the provided data argument is null
     */
    public boolean insert(T data) throws NullPointerException {
        if (data == null)
			throw new NullPointerException("Cannot insert data value null into the tree.");
		return this.insertHelper(new Node<>(data));
    }

    /**
     * Performs a naive insertion into a binary search tree: adding the new node
     * in a leaf position within the tree. After this insertion, no attempt is made
     * to restructure or balance the tree.
     * @param node the new node to be inserted
     * @return true if the value was inserted, false if is was in the tree already
     * @throws NullPointerException when the provided node is null
     */
    protected boolean insertHelper(Node<T> newNode) throws NullPointerException {
        if(newNode == null) throw new NullPointerException("new node cannot be null");

        if (this.root == null) {
            // add first node to an empty tree
            root = newNode;
            size++;
            return true;
        } else {
            // insert into subtree
            Node<T> current = this.root;
            while (true) {
                int compare = newNode.data.compareTo(current.data);
                if (compare == 0) {
                	return false;
				} else if (compare < 0) {
                    // insert in left subtree
                    if (current.down[0] == null) {
                        // empty space to insert into
                        current.down[0] = newNode;
                        newNode.up = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.down[0];
                    }
                } else {
                    // insert in right subtree
                    if (current.down[1] == null) {
                        // empty space to insert into
                        current.down[1] = newNode;
                        newNode.up = current;
                        this.size++;
                        return true;
                    } else {
                        // no empty space, keep moving down the tree
                        current = current.down[1]; 
                    }
                }
            }
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a left child of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * right child of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    protected void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException { 
      if (child.isRightChild() == true) { // if child is right child of parent, left rotation
        if (root.equals(parent)) { //checks if parent is the root
          root = child;
        }
        else { //if parent is not the root... check which child the parent is to the grandparent
          //checks if parent is a left child to grandparent
          if (parent.up != null && parent.up.down[0] == parent) { 
            child.up = parent.up; //grandparent becomes the new parent to child
            child.up.down[0] = child; //grandparent's left child is the child
          }
          //checks if parent is a right child to grandparent
          else if (parent.up != null && parent.up.down[1] == parent) { 
            child.up = parent.up; //grandparent becomes the new parent to child
            child.up.down[1] = child; //grandparent's right child is the child
          }
        } 
        parent.up = child; //child becomes the 'parent' to parent
        if (child.down[0] != null) { //check if current child has left child
          child.down[0].up = parent; //child's new 'parent' is parent
          parent.down[1] = child.down[0]; // parent's new 'child' is child's left child
        } else {
          parent.down[1] = null; // if there is no grandchild, set parent's down[1] to null
        }
        child.down[0] = parent; //link the switched child and parent node
      } 
      //if child is left child of parent, perform right rotation
      else if (child.up != null && child.up.down[0] == child) { 
        if (root.equals(parent)) { //checks if parent is the root
          root = child;
        }
        else { //if parent is not the root... check which child the parent is to the grandparent
          //checks if parent is a left child to grandparent
          if (parent.up != null && parent.up.down[0] == parent) { 
            child.up = parent.up; //grandparent becomes the new parent to child
            child.up.down[0] = child; //grandparent's left child is the child
          }
          //checks if parent is a right child to grandparent
          else if (parent.up != null && parent.up.down[1] == parent) { 
            child.up = parent.up; //grandparent becomes the new parent to child
            child.up.down[1] = child; //grandparent's right child is the child
          }
        } 
        parent.up = child; //child becomes the 'parent' to parent
        
        if (child.down[1] != null) { //check if current child has right child
          child.down[1].up = parent; // if yes, child's new 'parent' is parent
          parent.down[0] = child.down[1]; // parent's new 'child' is child's left child
        } else {
          parent.down[0] = null; // if there is no grandchild, set parent's down[0] to null
        }
        child.down[1] = parent; //link the switched child and parent node
      }
      else {
        throw new IllegalArgumentException("provided child and parent "
            + "node references are not related");
      }
    }

	/**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() returns 0, false if this.size() != 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data a comparable for the data value to check for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(Comparable<T> data) {
        // null references will not be stored within this tree
        if (data == null) {
            throw new NullPointerException("This tree cannot store null references.");
        } else {
            Node<T> nodeWithData = this.findNode(data);
            // return false if the node is null, true otherwise
            return (nodeWithData != null);
        }
    }

    /**
     * Removes all keys from the tree.
     */
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Helper method that will return the node in the tree that contains a specific
     * key. Returns null if there is no node that contains the key.
     * @param data the data value for which we want to find the node that contains it
     * @return the node that contains the data value or null if there is no such node
     */
    protected Node<T> findNode(Comparable<T> data) {
        Node<T> current = this.root;
        while (current != null) {
            int compare = data.compareTo(current.data);
            if (compare == 0) {
                // we found our value
                return current;
            } else if (compare < 0) {
                if (current.down[0] == null) {
                    // we have hit a null node and did not find our node
                    return null;
                }
                // keep looking in the left subtree
                current = current.down[0];
            } else {
                if (current.down[1] == null) {
                    // we have hit a null node and did not find our node
                    return null;
                }
                // keep looking in the right subtree
                current = current.down[1];
            }
        }
        return null;
    }

    /**
     * This method performs an inorder traversal of the tree. The string 
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations 
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            Stack<Node<T>> nodeStack = new Stack<>();
            Node<T> current = this.root;
            while (!nodeStack.isEmpty() || current != null) {
                if (current == null) {
                    Node<T> popped = nodeStack.pop();
                    sb.append(popped.data.toString());
                    if(!nodeStack.isEmpty() || popped.down[1] != null) sb.append(", ");
                    current = popped.down[1];
                } else {
                    nodeStack.add(current);
                    current = current.down[0];
                }
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.down[0] != null) q.add(next.down[0]);
                if(next.down[1] != null) q.add(next.down[1]);
                sb.append(next.data.toString());
                if(!q.isEmpty()) sb.append(", ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * This is a tester method to test right rotation being applied to BST
     * 
     * @return true if expected equals actual
     */
    public static boolean test1() {
        //Create, insert Integers into empty tree
        BinarySearchTree<Integer> tester = new BinarySearchTree<Integer>(); 
        tester.insert(10);
        tester.insert(9);
        tester.insert(8);
        Node<Integer> ten = tester.root;
        Node<Integer> nine = ten.down[0];
        //call rotate method
        tester.rotate(nine, ten);
        //Test expected to actual
        if (!tester.toLevelOrderString().equals("[ 9, 8, 10 ]")) {
          System.out.println("After inserting 10, 9, 8 into an empty tree and rotating 9 and 10,"
              + " level order is not [ 9, 8, 10 ], but should be");
          return false;
        }
        return true;
    }
    
    /**
     * This is a tester method to test left rotation being applied to BST
     * 
     * @return true if expected equals actual
     */
    public static boolean test2() {
      //Create, insert Integers into empty tree
      BinarySearchTree<Integer> tester = new BinarySearchTree<Integer>(); 
      tester.insert(3);
      tester.insert(1);
      tester.insert(2);
      tester.insert(4);
      Node<Integer> three = tester.root;
      Node<Integer> one = three.down[0];
      Node<Integer> two = one.down[1];
      Node<Integer> four = three.down[1];
      //call rotate method
      tester.rotate(one, three);
      //Test expected to actual
      if (!tester.toLevelOrderString().equals("[ 1, 3, 2, 4 ]")) {
        System.out.println("After inserting 1, 3, 2, 4 into an empty tree and rotating 1 and 3,"
            + " level order is not [ 1, 3, 2, 4 ], but should be");
        return false;
      }
      return true;
    }
    
    /**
     * This is a tester method to test an IllegalArgumentException applied to BST
     * 
     * @return true if expected equals actual
     */
    public static boolean test3() {
        //Create dummy node
        Node<Integer> one = new Node<Integer>(1);
        //Create, insert Integers into empty tree
        BinarySearchTree<Integer> tester = new BinarySearchTree<Integer>();
        tester.insert(2);
        tester.insert(3);
        Node<Integer> two = tester.root;
        //Test expected to Actual
        try {
          tester.rotate(one, two);
        } catch (IllegalArgumentException e) {
          return true;
        }
        return false;
    }
    
    /**
     * Main method to run tests. If you'd like to add additional test methods, add a line for each
     * of them.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test 1 passed: " + test1());
        System.out.println("Test 2 passed: " + test2());
        System.out.println("Test 3 passed: " + test3());
    }

}
