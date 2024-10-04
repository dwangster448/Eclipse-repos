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

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list. Note that while we CALL this
 * "reverse chronological" order, this is NOT enforced. You can add Tweets anywhere in the list
 * relative to each other, regardless of their respective timestamps.
 */
public class TwitterFeed<T> implements ListADT<Tweet>, Iterable<Tweet> {

  /*
   * The node containing the most recent tweet
   */
  private TweetNode head;

  /*
   * The node containing the oldest tweet
   */
  private TweetNode tail;

  /*
   * The number of tweets in this linked list
   */
  private int size;

  /*
   * The iteration mode for the timeline display
   */
  private TimelineMode mode;

  /*
   * The ratio of likes to total engagement that we want to see; set to .5 by default
   */
  private static double ratio = 0.5;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default
   * values, and the timeline mode to CHRONOLOGICAL.
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
  }

  /**
   * Accessor for the size of the feed
   * <p>
   * 
   * @return the number of TweetNodes in this TwitterFeed
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Determines whether this feed is empty. Recommend checking MORE than just the size field to get
   * this information, though if all methods are implemented correctly the size field's value will
   * be sufficient.
   * <p>
   * 
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  @Override
  public boolean isEmpty() {
    return (size == 0 && head == null && tail == null);
  }

  /**
   * Determines whether a given Tweet is present in the TwitterFeed. Use Tweet's equals() method!
   * <p>
   * 
   * @param
   * @return true if there are NO TweetNodes in this TwitterFeed, false otherwise
   */
  @Override
  public boolean contains(Tweet findObject) {
    if (isEmpty()) {
      return false;
    }
    TweetNode toCheck = this.head;
    while (toCheck != null) {
      if (toCheck.getTweet().equals(findObject)) {
        return true;
      }
      toCheck = toCheck.getNext();
    }
    return false;
  }

  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed!
   * <p>
   * 
   * @param findObject the Tweet to search for
   * @return the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  @Override
  public int indexOf(Tweet findObject) {
    if (isEmpty()) {
      return -1;
    }
    TweetNode toCheck = this.head;
    int index = 0;
    while (toCheck != null) {
      if (toCheck.getTweet() == findObject) {
        return index;
      }
      toCheck = toCheck.getNext();
      index++;
    }
    return -1;
  }

  /**
   * Accessor method for the Tweet at a given index
   * <p>
   * 
   * @param index - the index of the Tweet in question
   * @return the Tweet object at that index (NOT its TweetNode!
   * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index of
   *                                   the TwitterFeed
   */
  @Override
  public Tweet get(int index) {
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    TweetNode toCheck = this.head;
    int copyOf = 0;
    while (toCheck != null) {
      if (copyOf == index) {
        return toCheck.getTweet();
      }
      toCheck = toCheck.getNext();
      copyOf++;
    }
    return null;
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   * <p>
   * 
   * @return the Tweet object at the head of the linked list
   * @throws NoSuchElementException if the TwitterFeed is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return this.head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   * <p>
   * 
   * @return the Tweet object at the tail of the linked list
   * @throws NoSuchElementException if the TwitterFeed is empty
   */
  public Tweet getTail() throws NoSuchElementException {
    if (isEmpty()) {
      throw new NoSuchElementException("TwitterFeed is empty");
    }
    return this.tail.getTweet();
  }

  /**
   * Adds the given Tweet to the tail of the linked list
   * <p>
   * 
   * @return newObject the Tweet to add
   */
  @Override
  public void addLast(Tweet newObject) {
    if (isEmpty() == true) {
      this.head = new TweetNode(newObject);
      this.tail = new TweetNode(newObject);
      size++;
    } else {
      TweetNode newNode = new TweetNode(newObject, null);
      this.tail.setNext(newNode);
      this.tail = newNode;
      size++;
    }

  }

  /**
   * Adds the given Tweet to the head of the linked list
   * <p>
   * 
   * @param newObject the Tweet to add
   */
  @Override
  public void addFirst(Tweet newObject) {
    if (isEmpty() == true) {
      this.head = new TweetNode(newObject);
      this.tail = new TweetNode(newObject);
      size++;
    } else {
      TweetNode newNode = new TweetNode(newObject, head);
      TweetNode temp = this.head;
      newNode.setNext(temp);
      this.head = newNode;
      size++;
    }
  }

  /**
   * Adds the given Tweet to a specified position in the linked list
   * <p>
   * 
   * @param index     the position at which to add the new Tweet
   * @param newObject the Tweet to add
   * @throws IndexOutOfBoundsException if the index is negative or greater than the size of the
   *                                   linked list
   */
  @Override
  public void add(int index, Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (index > size || index < 0) {
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    if (isEmpty()) {
      this.head = newNode;
      this.tail = newNode;
      size++;
    } else if (index == size()) {
      // add to the back, we already wrote the instructions, call them
      // connect end to new node
      addLast(newObject);
    } else if (index == 0) {
      // add to the front -> similar to adding to back
      // Draw a diagram to visualize this!
      addFirst(newObject);
    } else {
      // add between nodes in list
      // find the node before the one I want to insert
      TweetNode current = this.head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      TweetNode next = current.getNext();
      current.setNext(newNode);
      newNode.setNext(next);
      size++;
    }
  }

  /**
   * Removes and returns the Tweet at the given index
   * <p>
   * 
   * @param index the position of the Tweet to remove
   * @return the Tweet object that was removed from the list
   * @throws IndexOutOfBoundsException if the index is negative or greater than the largest index
   *                                   currently present in the linked list
   */
  @Override
  public Tweet delete(int index) throws IndexOutOfBoundsException {
    if (index > (size - 1) || index < 0) {
      throw new IndexOutOfBoundsException("Index is invalid");
    }
    if (size() == 1) {
      TweetNode toReturn = this.head;
      this.head.setNext(null);
      this.head = null;
      this.tail = null;
      size = 0;
      return toReturn.getTweet();
    } else if (index == (size() - 1)) {
      TweetNode newTail = this.head;
      int indexSize = 0;
      while (indexSize < size() - 2) {
        newTail = newTail.getNext();
        indexSize++;
      }
      TweetNode toReturn = this.tail;
      this.tail.setNext(null);
      this.tail = newTail;
      newTail.setNext(null);
      size--;
      return toReturn.getTweet();
    } else {
      TweetNode toCheck = this.head;
      int findIndex = 0;
      while (toCheck != null) {
        if (findIndex + 1 == index && toCheck.getNext() != null) {
          TweetNode nextNode = toCheck.getNext().getNext();
          TweetNode toReturn = toCheck.getNext();
          toCheck.setNext(nextNode);
          size--;
          return toReturn.getTweet();
        }
        toCheck = toCheck.getNext();
        findIndex++;
      }
    }
    return null;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   * <p>
   * 
   * @param m the iteration mode; any value from the TimelineMode enum
   */
  public void setMode(TimelineMode m) {
    this.mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   * <p>
   * 
   * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the
   *         head of this TwitterFeed list
   */
  @Override
  public Iterator<Tweet> iterator() {
    if (mode.equals(mode.CHRONOLOGICAL)) {
      return new ChronoTwiterator<Tweet>(this.head);
    } else if (mode.equals(mode.VERIFIED_ONLY)) {
      return new VerifiedTwiterator<Tweet>(this.head);
    } else if (mode.equals(mode.LIKE_RATIO)) {
      return new RatioTwiterator<Tweet>(this.head, ratio);
    }
    return null;
  }

}
