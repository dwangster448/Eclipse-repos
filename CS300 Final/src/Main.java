
public class Main {

  /**
   * Inserts newNode to the subtree rooted at current.
   * @param newNode a reference to a new BSTNode to be inserted to 
   *                the BST rooted at current
   * @param current root of a subtree of a binary search tree
   * @throws IllegalStateException if trying to insert a duplicate item
   */ 
//  public static void insertHelper(BSTNode<String> newNode, BSTNode<String> current) {
//    if (current == null) {
//      current = newNode;
//    } else {
//      int c = newNode.getData().compareTo(current.getData());
//      if (c == 0)
//        throw new IllegalStateException();
//      else if (c < 0)
//        insertHelper(newNode, current.leftChild());
//      else
//        insertHelper(newNode, current.rightChild());
//    }
//  }
  
  private static String[] myDataStructure;

  public static void main(String[] args) {
    for (String s : myDataStructure) { 
      System.out.println(s);
    }
  }

}
