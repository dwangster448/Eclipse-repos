import BinarySearchTree.Node;

public class ChatDump {
//Helper method to get the uncle node
 private Node<T> getUncle(Node<T> node) {
   Node<T> parent = node.getParent();
   Node<T> grandparent = parent != null ? parent.getParent() : null;

   if (grandparent == null) {
     return null;
   }

   if (parent == grandparent.getLeft()) {
     return grandparent.getRight();
   } else {
     return grandparent.getLeft();
   }
 }
}


// Case 1: The new node is the root, and it is colored black.
Node<T> parent = newNode.getParent();
if (parent == null) {
    // Case 1: The new node is the root, and it is colored black.
    newNode.setColor(Node.BLACK);
} else if (parent.getColor() == Node.BLACK) {
    // Case 2: The parent is black, so no violations.
    return;
} else {
    Node<T> grandparent = parent.getParent();
    Node<T> uncle = getUncle(newNode);

    if (uncle != null && uncle.getColor() == Node.RED) {
        // Case 3: Parent and uncle are red, so we need to recolor.
        parent.setColor(Node.BLACK);
        uncle.setColor(Node.BLACK);
        grandparent.setColor(Node.RED);

        // Continue enforcing properties with the grandparent.
        enforceRBTreePropertiesAfterInsert(grandparent);
    } else {
        // Case 4: Parent is red, but uncle is black or null.
        if (newNode == parent.getRight() && parent == grandparent.getLeft()) {
            // Left rotation needed.
            rotateLeft(parent);
            newNode = newNode.getLeft();
        } else if (newNode == parent.getLeft() && parent == grandparent.getRight()) {
            // Right rotation needed.
            rotateRight(parent);
            newNode = newNode.getRight();
        }

        // Case 5: Recolor and rotate.
        parent = newNode.getParent();
        grandparent = parent.getParent();

        parent.setColor(Node.BLACK);
        grandparent.setColor(Node.RED);

        if (newNode == parent.getLeft()) {
            // Right rotation.
            rotateRight(grandparent);
        } else {
            // Left rotation.
            rotateLeft(grandparent);
        }
    }
}
}

// Helper method to get the uncle node
private Node<T> getUncle(Node<T> node) {
Node<T> parent = node.getParent();
Node<T> grandparent = parent != null ? parent.getParent() : null;

if (grandparent == null) {
    return null;
}

if (parent == grandparent.getLeft()) {
    return grandparent.getRight();
} else {
    return grandparent.getLeft();
}
}
