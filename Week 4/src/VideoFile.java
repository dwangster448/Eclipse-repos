////////////////////////////// UNIT QUIZ FILE HEADER ///////////////////////////
// Full Name: Daniel WAng
// Campus ID: 9084072710
// WiscEmail: dwang448@wisc.edu
// (TODO: fill this out)
////////////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////////////
// BE CAREFUL!! This file contains TWO classes. You will need to complete the
// implementation of BOTH classes with respect to the provided requirements
// in the TODO tags for full credit.
//
// When creating new exception objects, including messages within these objects 
// is optional.
////////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;

/**
 * This class models the VideoFile data type. It contains information about the title of
 * the video file, as well as the duration of the video in number of seconds.
 * 
 * NOTES:
 * You may NOT add any additional data fields to this class unless specified in the TODO tags.
 * You may NOT add ANY additional methods to this class, regardless of access modifier.
 * There is no tester or main method for this class.
 */
public class VideoFile {
  private int duration;
  // 1. Declare a private instance field of type int named duration
  private String title;
  // 2. Declare a private instance field of type String named title
  // each VideoFile's title must be unique, enforced in the constructor
  protected int watchCount;
  // 3. Declare a protected instance data field of type int named watchCount
  // watchCount represents the total number of times this file has been played
  
  // PROVIDED: a class field to store all titles of previously created VideoFiles
  private static ArrayList<String> usedTitles = new ArrayList<String>();
  
  /**
   * Creates a new VideoFile with the given title and duration
   * 
   * @param duration the duration of the video file in seconds, you may assume >= 0
   * @param title the title of the video file, must be unique across all VideoFiles
   * @throws IllegalStateException if the provided title has already been used by
   * another VideoFile
   */
  public VideoFile(int duration, String title) throws IllegalStateException{
    if (usedTitles.contains(title)) throw new IllegalStateException("title already present");
    // 4. Check whether title is already present in the usedTitles list and handle appropriately
    // Hint: use ArrayList's .contains() method
    watchCount = 0;
    usedTitles.add(title);
    // 5. Set instance data fields to the provided input parameters (watchCount begins at 0)
    
    // 6. Add this title to the list of used VideoFile titles
    
  }
  
  /////////////// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) ///////////////
  
  /**
   * Increments the watchCount field of this VideoFile (adds 1)
   */
  public void watch() {
    watchCount++;
    // 7. Complete the implementation of this mutator method
  }
  
  /**
   * Returns a formatted String of the duration as number of minutes + ":" + number of seconds.
   * Number of minutes may have any number of digits, but number of seconds must always have two,
   * so a 62 second duration should produce the result "1:02", NOT "1:2"
   * 
   * @return a formatted String version of the duration split into minutes and seconds
   */
  public String getDuration() {
    int minutes = this.duration/60;
    int seconds = this.duration%60;
    if(seconds<10) return (minutes+":0"+seconds);
    // 8. Complete the implementation of this accessor method
    
    // Hint: minutes can be calculated from seconds using integer division by 60
    
    // Hint: remaining seconds can be calculated using modulo 60
    
    // Hint: Be sure to check whether the remaining seconds are < 10 and add a leading 0 if so
    
    return (minutes+":"+seconds); // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  /**
   * Returns a string representation of this VideoFile. The returned string has the following format:
   * "title (duration)"
   * For example:
   *    lofi hip-hop beats to study to (18:09)
   *    HOUSE OF REPRESENTATIVES - Bad Lip Reading (14:28)
   *    Never Gonna Give You Up (3:32)
   *    
   * @return a string formatted as "title (duration)". 
   */
  @Override
  public String toString() {
    return(title+" "+getDuration());
    // 9. Complete the implementation of this method
    
  }
}

  /////////////// Checkpoint: MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) ///////////////

/**
 * This class models the SocialMediaPost data type, representing a video file which will be posted
 * to an unnamed social media app.
 * 
 * NOTES:
 * You may NOT add any additional data fields to this class.
 * You may NOT add ANY additional methods to this class, regardless of access modifier.
 * There is no tester or main method for this class.
 */
class SocialMediaPost extends VideoFile{ // TODO: 10. Set this class to be a subclass of the VideoFile class
  
  private ArrayList<String> tags; // a list of tags associated with this video
  private int numLikes;           // the number of likes/upvotes/positive engagement on this post
  
  /**
   * Creates a new SocialMediaPost with the provided values
   * 
   * @param duration the duration of the video file in seconds, you may assume >= 0
   * @param title the title of the video file, must be unique across all VideoFiles
   * 
   * @throws IllegalStateException if the provided title has already been used by
   * another VideoFile
   */
  public SocialMediaPost(int duration, String title) throws IllegalStateException {
    super(duration,title);
    // 11. Call the constructor of the super class with required arguments. Do not catch any
    // exceptions it may throw.
    tags = new ArrayList<String>();
    numLikes = 0;
    // 12. Initialize the data fields of this class appropriately
    // numLikes begins at 0 and tags is an empty ArrayList of Strings
  }
  
  /**
   * Adds the given tag to the tag list of this SocialMediaPost.
   * 
   * @param tag the tag to add to this list
   * @throws IllegalArgumentException if the tag is null or blank
   */
  public void addTag(String tag) throws IllegalArgumentException {
    if (tag == null) throw new IllegalArgumentException("illegal tag");
    if (tag.length() <= 0) throw new IllegalArgumentException("blank tag");
    this.tags.add(tag);
    // 13. Check the validity of the input parameter and handle appropriately
    
    // 14. Complete the implementation of this mutator method
  }
  
  /**
   * Calculates and returns the "clout" of this post as numLikes/watchCount; if watchCount is 0,
   * method should return 0 instead of NaN. Remember to cast your int values to doubles before 
   * dividing!
   *  
   * @return the ratio of likes to views of this SocialMediaPost, must be between 0.0 and 1.0 
   * inclusive
   */
  public double getClout() {
    if (watchCount <= 0) return 0;
    // 15. Complete the implementation of this accessor method
    return ((double)numLikes)/((double)watchCount);
  }
  
  /**
   * Returns a string representation of this SocialMediaPost. The returned string has the following
   * format for SocialMediaPosts with clout > .5:
   *    [!] title (duration): [tag, tag, tag]
   * and for SocialMediaPosts with clout <= .5:
   *    title (duration): [tag, tag, tag]
   * Where the "[!]" is present only for posts with the getClout() ratio > 0.5. The tags list is
   * represented as the result of ArrayList's toString().
   * 
   * Examples:
   *    [!] POTATO DONUTS (1:03): [fyp, baking, donuts, 100k, bdylanhollis]
   *    my cat chasing a leaf (0:37): [catsofinstagram, tripodcat]
   *    [!] Tiny Burger Combo (1:14): []
   * 
   * @return a string formatted as described above
   */
  @Override
  public String toString() {
    String original = super.toString();
    String post = new String();
    if (getClout()>0.5) {
      post = post.concat("[!] ");
      post = post.concat(original);
      post = post.concat(": [");
      if (tags.size() > 0) {
        post = post.concat(this.tags.get(0));
        for (int i = 1; i<tags.size();i++) {
          post = post.concat(", "+tags.get(i));
        }
      }
      post = post.concat("]");
    }
    else {
      post = post.concat(original);
      post = post.concat(": [");
      if (tags.size() > 0) {
        post = post.concat(this.tags.get(0));
        for (int i = 1; i<tags.size();i++) {
          post = post.concat(", "+tags.get(i));
        }
      }
      post = post.concat("]");
    }
    
    // 16. Complete the implementation of this method
    
    // Note: call the super class implementation of toString() to get the title and duration
    // of this social media post
    
    return post; // default return statement added to avoid compiler errors. Feel free to change it.
  }
  
  // MAKE SURE TO SAVE YOUR SOURCE FILE (Ctrl/Cmd + S) before submitting it to Gradescope
}
