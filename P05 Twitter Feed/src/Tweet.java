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

import java.util.Calendar;
import java.util.Date;

/*
 * This data type models a tweet - a short text post made on the social media service Twitter
 */
public class Tweet {

  /*
   * A shared Calendar object for this class to use to generate consistent dates.
   */
  private static Calendar dateGenerator;

  /*
   * A value determining the maximum length of a tweet.
   */
  private static final int MAX_LENGTH = 280;

  /*
   * The number of likes this tweet has
   */
  private int numLikes;

  /*
   * The number of retweets this tweet has
   */
  private int numRetweets;

  /*
   * The text of this tweet
   */
  private String text;

  /*
   * The date and time this tweet was posted, a Date object created by calling
   * dateGenerator.getTime() at construction time
   */
  private Date timestamp;

  /*
   * The User associated with this tweet
   */
  private User user;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its
   * timestamp should be set to the current time.
   * <p>
   * 
   * @param user the User posting this tweet
   * @param text the text of the tweet
   * @throws IllegalArgumentException if the tweet's text exceeds MAX_LENGTH characters
   * @throws NullPointerException     if the provided text or user are null
   * @throws IllegalStateException    if the dateGenerator field has not yet been initialized
   */
  public Tweet(User user, String text) {
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("text exceeds MAX LENGTH");
    }
    if (text == null || user == null) {
      throw new NullPointerException("text or null are null");
    }
    if (dateGenerator == null) {
      throw new IllegalStateException("dateGenerator has not been initialized");
    }
    this.user = user;
    this.text = text;
    this.timestamp = dateGenerator.getTime();
  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object. For tests which do
   * not require a consistent date, you can use Calendar.getInstance() to get a Calendar set to the
   * current time.
   * <p>
   * 
   * @param c the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = Calendar.getInstance();
    dateGenerator.set(2001, 9, 03, 9, 13, 15);
  }

  /**
   * Accesses the text of this tweet
   * <p>
   * 
   * @return the text of this tweet
   */
  public String getText() {
    return this.text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * <p>
   * 
   * @return the total engagement of this tweet
   */
  public int getTotalEngagement() {
    return this.numLikes + this.numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * <p>
   * 
   * @return true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    return (user.isVerified() == true);
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do
   * positive, uplifting ratios around here.
   * <p>
   * 
   * @return the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the
   *         total engagement is 0.
   */
  public double getLikesRatio() {
    double value = (double) this.numLikes / (this.numLikes + this.numRetweets);
    return value;
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    this.numLikes++;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    this.numRetweets++;
  }

  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
   * that contains the same text posted at the same time by the same User (use the toString() method
   * from User to compare these!) then the two Tweets are considered equal regardless of their
   * respective likes/retweets.
   * <p>
   * 
   * @param o the object to compare this Tweet to
   * @return true if this Tweet is equivalent to the provided object, false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Tweet) {
      Tweet object = (Tweet) o;
      return (this.text.equals(object.getText()));
    }
    return false;
  }

  /**
   * A string representation of this tweet.
   * <p>
   * 
   * @return a formatted string representation of this tweet
   */
  @Override
  public String toString() {
    String toReturn = "tweet from " + this.user + " at " + this.timestamp;
    toReturn += ":\n-- " + this.text;
    toReturn += "\n-- likes: " + this.numLikes;
    toReturn += "\n-- retweets: " + this.numRetweets;
    return toReturn;
  }
}
