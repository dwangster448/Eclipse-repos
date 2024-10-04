import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

/**
* This class extends the RedBlackTree class to run submission checks on it.
*/
public class P13SubmissionChecker extends RedBlackTree {

	/**
	* Submission check that checks if the root node of a newly created tree is null.
	* @return true is check passes, false if it fails
	*/
	@Test
	public void submissionCheckerSmallTree() {
    	RedBlackTree<String> tree = new RedBlackTree<>();
		tree.insert("lion");
    	Assertions.assertTrue(tree.toLevelOrderString().equals("[ lion ]"));
    	tree.insert("tiger");
    	Assertions.assertTrue(tree.toLevelOrderString().equals("[ lion, tiger ]"));
	}

	@Test
	public void submissionCheckerRedAuntRightSide() {
		RBTNode<Integer> root = new RBTNode<>(54);
		root.blackHeight = 1;
		root.down[0] = new RBTNode<>(12);
		root.down[0].up = root;
		root.down[1] = new RBTNode<>(77);
		root.down[1].up = root;
		RedBlackTree<Integer> tree = new RedBlackTree<>();
		tree.root = root;
		tree.size = 3;
		//System.out.println("level-order before insert 24: " + tree.toLevelOrderString());
		tree.insert(24);
		//System.out.println("level-order after insert 24: " + tree.toLevelOrderString());
		Assertions.assertEquals("[ 54, 12, 77, 24 ]", tree.toLevelOrderString());
	}

	@Test
	public void submissionCheckerRedAuntLeftSide() {
        RBTNode<Integer> root = new RBTNode<>(54);
        root.blackHeight = 1;
        root.down[0] = new RBTNode<>(12);
        root.down[0].up = root;
        root.down[1] = new RBTNode<>(77);
        root.down[1].up = root;
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.root = root;
        tree.size = 3;
        //System.out.println("level-order before 65: " + tree.toLevelOrderString());
		tree.insert(65);
		//System.out.println("level-order after 65: " + tree.toLevelOrderString());
		//System.out.println("location of 65: " + toPrint.data);
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).blackHeight);
		
//		System.out.println("54's color: " + root.blackHeight);
//		System.out.println("12's color: " + root.getDownLeft().blackHeight);
//		System.out.println("77's color: " + root.getDownRight().blackHeight);
//		System.out.println("65's color: " + root.getDownRight().getDownLeft().blackHeight);
		
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).getDownLeft().blackHeight);
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownRight().getDownLeft().blackHeight);
	}

	@Test
	public void submissionCheckerNullAuntOppositeSide() {
		RBTNode<Integer> root = new RBTNode<>(34);
        root.blackHeight = 1;
        root.down[0] = new RBTNode<>(9);
        root.down[0].up = root;
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.root = root;
        tree.size = 2;		
//        System.out.println("level-order before 3: " + tree.toLevelOrderString());
//        System.out.println("34's color: " + root.blackHeight);
//        System.out.println("9's color: " + root.getDownLeft().blackHeight);
		tree.insert(3);
//		System.out.println("level-order after 3: " + tree.toLevelOrderString());
		Assertions.assertEquals("[ 9, 3, 34 ]", tree.toLevelOrderString());
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownLeft().blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
	}

	@Test
	public void submissionCheckerBlackAuntOppositeSide() {
		RBTNode<Integer> root = new RBTNode<>(34);
        root.blackHeight = 1;
        root.down[0] = new RBTNode<>(9);
        root.down[0].up = root;
		root.down[1] = new RBTNode<>(63);
		root.down[1].up = root;
		((RBTNode<Integer>)root.down[1]).blackHeight = 1;
        RedBlackTree<Integer> tree = new RedBlackTree<>();
        tree.root = root;
        tree.size = 3;
		
		tree.insert(7);
		Assertions.assertEquals("[ 9, 7, 34, 63 ]", tree.toLevelOrderString());
		Assertions.assertEquals(1, ((RBTNode<Integer>)tree.root).blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownLeft().blackHeight);
		Assertions.assertEquals(0, ((RBTNode<Integer>)tree.root).getDownRight().blackHeight);
	}

}
