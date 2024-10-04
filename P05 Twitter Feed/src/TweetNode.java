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

/*
 * A container for a Tweet in a singly-linked list
 */
public class TweetNode {

  /*
   * The tweet contained in this node
   */
  private Tweet tweet;

  /*
   * The next TweetNode in this linked list
   */
  private TweetNode nextTweet;

  /**
   * Constructs a singly-linked node containing a tweet
   * <p>
   * 
   * @param tweet the tweet to put in this node
   * @param next  the next tweet in the linked list
   */
  public TweetNode(Tweet newObject, TweetNode next) {
    this.tweet = newObject;
    this.nextTweet = next;
  }

  /**
   * Constructs a singly-linked node containing a tweet, with no successor tweet
   * <p>
   * 
   * @param tweet the tweet to put in this node
   */
  public TweetNode(Tweet tweet) {
    this.tweet = tweet;
    this.nextTweet = null;
  }

  /**
   * Accesses the tweet in this node
   * <p>
   * 
   * @return the tweet in this node
   */
  public Tweet getTweet() {
    return this.tweet;
  }

  /**
   * Accesses the next TweetNode in the list
   * <p>
   * 
   * @return the successor TweetNode
   */
  public TweetNode getNext() {
    return this.nextTweet;
  }

  /**
   * Links this node to another node
   * <p>
   * 
   * @param next the successor TweetNode (can be null)
   */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }
}
