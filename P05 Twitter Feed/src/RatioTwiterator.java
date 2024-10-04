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
 * This is an iterator that moves through tweets in reverse chronological order by high like ratio
 * only
 */
public class RatioTwiterator<T> implements Iterator<Tweet> {

  /*
   * The TweetNode containing the next tweet to be returned in the iteration
   */
  private TweetNode next;

  /*
   * The minimum threshold value for the ratio of likes to total engagement
   */
  private final double THRESHOLD;

  /**
   * Constructs a new twiterator at the given starting node
   * <p>
   * 
   * @param startNode the node to begin the iteration at
   * @param threshold the minimum threshold value for the ratio of likes to total engagement,
   *                  assumed to be between 0.0 and 1.0 thanks to range checking in Timeline
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {
    this.next = startNode;
    this.THRESHOLD = threshold;
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
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet with
   * a likes ratio in excess of the given threshold
   * <p>
   * 
   * @throw sNoSuchElementException if there is not a next tweet to return
   */
  @Override
  public Tweet next() {
    if (hasNext() == false) {
      throw new NoSuchElementException("no next tweet exist");
    }
    while (this.next != null) {
      if (this.next.getTweet().getLikesRatio() >= this.THRESHOLD) {
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
