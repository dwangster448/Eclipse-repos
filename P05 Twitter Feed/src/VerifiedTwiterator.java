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
 * This is an iterator that moves through tweets in reverse chronological order by verified users
 * only
 */
public class VerifiedTwiterator<T> implements Iterator<Tweet> {

  /*
   * The TweetNode containing the next tweet to be returned in the iteration
   */
  private TweetNode next;

  /**
   * Constructs a new twiterator at the given starting node
   * <p>
   * 
   * @param startNode the node to begin the iteration at
   */
  public VerifiedTwiterator(TweetNode startNode) {
    this.next = startNode;
  }

  /**
   * Checks whether there is a next tweet to return
   * <p>
   * 
   * @return true if there is a next tweet, false if the value of next is null
   */
  @Override
  public boolean hasNext() {
    return next != null;
  }

  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet by a
   * verified user
   * <p>
   * 
   * @throws NoSuchElementException if there is not a next tweet to return
   */
  @Override
  public Tweet next() {
    if (hasNext() == false) {
      throw new NoSuchElementException("no next tweet exist");
    }
    while (this.next != null) {
      if (this.next.getTweet().isUserVerified()) {
        Tweet toReturnTweet = this.next.getTweet();
        this.next = this.next.getNext();
        return toReturnTweet;
      } else {
        this.next = this.next.getNext();
      }
    }
    throw new NoSuchElementException("no next tweet exist");
  }
}
