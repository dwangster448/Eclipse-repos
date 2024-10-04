///////////////////////////////////////////////////////////////////////////////
//
// Title: Twitter Feed
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
 * Generics list class
 * 
 * @author danielwang
 *
 * @param <T>
 */
public interface ListADT<T> {

  /*
   * size of list
   */
  public int size();

  /**
   * tests if list is empty
   * 
   * @returns true if is empty
   */
  public boolean isEmpty();

  /**
   * finds if an object is in our list
   * 
   * @param findObject the inserted object
   * @return true if exists in the list, false otherwise
   */
  public boolean contains(T findObject);

  /**
   * returns the requested object's index in our list
   * 
   * @param findObject the inserted object
   * @return index of the object in list, null otherwise
   */
  public int indexOf(T findObject);

  /**
   * get the object at the requested index
   * 
   * @param index of the object
   * @return object or null otherwise
   */
  public T get(int index);

  /**
   * add object to the list's tail
   * 
   * @param newObject to be inserted
   */
  public void addLast(T newObject);

  /**
   * add object to the list's head
   * 
   * @param newObject to be inserted
   */
  public void addFirst(T newObject);

  /**
   * add object at the index
   * 
   * @param index     in the list
   * @param newObject to be inserted
   */
  public void add(int index, T newObject);

  /**
   * remove object at indicated index and returns it
   * 
   * @param index in the list
   * @return the object at the index
   */
  public T delete(int index);

}
