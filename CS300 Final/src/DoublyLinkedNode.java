

/**
 * This class models LinkedNodes used to build a doubly linked list
 *
 * @param <T> Type of the element stored within this list
 */
public class DoublyLinkedNode<T> {
  private T data; // data carried by this node
  private DoublyLinkedNode<T> previous; // reference to the previous node
  private DoublyLinkedNode<T> next; // reference to the next node

  /**
   * Creates a new DoublyLinkedNode with a given data and no previous or next nodes in the chain of
   * linked nodes
   * 
   * @param data data to be carried by this node
   */
  public DoublyLinkedNode(T data) {
    this.data = data;
  }

  /**
   * Creates a new DoublyLinkedNode with a given data, previous, and next nodes
   * 
   * @param data data to be carried by this node
   * @param prev reference to the previous node
   * @param next reference to the next node
   */
  public DoublyLinkedNode(T data, DoublyLinkedNode<T> prev, DoublyLinkedNode<T> next) {
    this.data = data;
    this.previous = prev;
    this.next = next;
  }

  /**
   * Returns the data carried by this node
   * 
   * @return the data
   */
  public T getData() {
    return this.data;
  }

  /**
   * Sets the data of this node
   *
   * @param data the data to set
   */
  public void setData(T data) {
    this.data = data;
  }

  /**
   * Returns the previous node of this node
   *
   * @return the previous node
   */
  public DoublyLinkedNode<T> getPrevious() {
    return this.previous;
  }

  /**
   * Sets the previous node of this node
   *
   * @param prev the previous to set
   */
  public void setPrevious(DoublyLinkedNode<T> prev) {
    this.previous = prev;
  }

  /**
   * Gets the next node of this node
   * 
   * @return the next node
   */
  public DoublyLinkedNode<T> getNext() {
    return this.next;
  }

  /**
   * Sets the next node of this node
   * 
   * @param next the next to set
   */
  public void setNext(DoublyLinkedNode<T> next) {
    this.next = next;
  }

}
