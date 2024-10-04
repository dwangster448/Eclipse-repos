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
 * This (very basic) data type models a Twitter user, store information about each user's username
 * and whether they have an important blue checkmark.
 */
public class User {

  /*
   * Indicates whether the user is verified
   */
  private boolean isVerified;

  /*
   * the user's username as a String
   */
  private String username;

  /**
   * Constructs a new User object with a given username. All Users are unverified by default.
   * <p>
   * 
   * @param username the username of this User.
   */
  public User(String username) {
    if ((username.contains("*") && isVerified == false) || username.equals(null)
        || username.length() <= 0) {
      throw new IllegalArgumentException("Cannot contain *");
    }
    this.username = username;
  }

  /**
   * Accesses the username of this User
   * <p>
   * 
   * @return the username this User tweets under
   */
  public String getUsername() {
    return this.username;
  }

  /**
   * Determines whether the User is a verified user or not
   * <p>
   * 
   * @return true if this User is verified
   */
  public boolean isVerified() {
    return this.isVerified;
  }

  /**
   * Gives this User an important-looking asterisk
   * <p>
   */
  public void verify() {
    this.username += "*";
    isVerified = true;
  }

  /**
   * Takes this User's important-looking asterisk away
   * <p>
   */
  public void revokeVerification() {
    if (this.username.contains("*")) {
      int index = username.indexOf("*");
      this.username = username.substring(0, index);
    }
  }

  /**
   * Creates a String representation of this User for display. For example, if this User's username
   * is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's
   * username is "dril" and they are not verified, this method will return "@dril" (with no
   * asterisk).
   * <p>
   * 
   * @return a String representation of this User as described above
   */
  @Override
  public String toString() {
    String display = "@" + this.username;
    return display;
  }
}
