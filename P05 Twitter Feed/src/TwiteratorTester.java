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

/*
 * verifies that various classes in the program works
 */
public class TwiteratorTester {

  /**
   * tests that User is correct with an invalid username and a valid username
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testUser() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      try {
        User test = new User("*");
      } catch (IllegalArgumentException e) {
        return true;
      }
    }
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      User firstTest2 = new User("first");
      firstTest2.verify();
      if (firstTest2.getUsername() != "first*") {
        return false;
      }
    }
    return true;
  }

  /**
   * tests that Tweet is correct with an valid tweet
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testTweet() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      User toInsert = new User("test");
      Tweet test = new Tweet(toInsert, "testing");
      return test.getText() == "testing";
    }
  }

  /**
   * tests that TweetNode is correct with a valid Tweetnode object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testNode() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TweetNode test = new TweetNode(new Tweet(new User("test"), "testing"));
      TweetNode test2 = new TweetNode(new Tweet(new User("test2"), "testing2"));
      TweetNode test3 = new TweetNode(new Tweet(new User("test3"), "testing2"), test);
      test.setNext(test2);
      if (test3.getNext() != test) {
        return false;
      }
      TweetNode actual = new TweetNode(new Tweet(new User("test"), "testing"));
      Tweet actual2 = new Tweet(new User("test2"), "testing2");
      return test.getTweet().equals(actual.getTweet()) && test.getNext().getTweet().equals(actual2);
    }
  }

  /**
   * tests that TwitterFeed is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testAddTweet() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      if (test.isEmpty() != true) {
        return false;
      }
      Tweet expected = new Tweet(new User("test1"), "testing1");
      Tweet actual = new Tweet(new User("test1"), "testing1");
      test.addFirst(actual);

      if (!(test.getHead().equals(expected) && test.size() == 1 && test.contains(expected)
          && test.get(0).equals(expected) && test.getHead().equals(expected)
          && test.getTail().equals(expected))) {
        return false;
      }
    }
    return true;
  }

  /**
   * tests that TwitterFeed's add method is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testInsertTweet() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      test.add(0, new Tweet(new User("test"), "testing"));
      test.add(1, new Tweet(new User("test2"), "testing2"));
      Tweet expected = new Tweet(new User("test"), "testing");
      Tweet expected2 = new Tweet(new User("test2"), "testing2");
      if (!(test.get(1).equals(expected2) && test.size() == 2 && test.getHead().equals(expected)
          && test.getTail().equals(expected2))) {
        return false;
      }
      return true;
    }
  }

  /**
   * tests that TwitterFeed's delete method is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testDeleteTweet() {
    {
      Calendar testDate = Calendar.getInstance();
      testDate.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testDate);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      test.add(0, new Tweet(new User("test"), "testing"));
      test.add(1, new Tweet(new User("test2"), "testing2"));
      test.add(2, new Tweet(new User("test3"), "testing3"));
      test.add(3, new Tweet(new User("test4"), "testing4"));
      test.add(4, new Tweet(new User("test5"), "testing5"));
      Tweet expected = new Tweet(new User("test5"), "testing5");
      Tweet actual = test.delete(4);
      if (!(actual.getText().equals(expected.getText()))) {
        return false;
      }
      return true;
    }
  }

  /**
   * tests that ChronoTwiterator is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testChronoTwiterator() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      test.setMode(TimelineMode.CHRONOLOGICAL);
      test.add(0, new Tweet(new User("test"), "testing"));
      test.add(1, new Tweet(new User("test2"), "testing"));
      test.add(2, new Tweet(new User("test3"), "testing"));
      for (Tweet object : test) {
        String expected = "testing";
        if (object.getText() != expected) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * tests that VerifiedTwiterator is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testVerifiedTwiterator() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      test.setMode(TimelineMode.VERIFIED_ONLY);
      test.add(0, new Tweet(new User("test"), "testing"));
      test.add(1, new Tweet(new User("test2"), "testing"));
      User test3 = new User("test3");
      test3.verify();
      test.add(2, new Tweet(test3, "testing3"));
      for (Tweet object : test) {
        String expected = "testing3";
        if (object.getText() != expected) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * tests that RatioTwiterator is correct with a valid TwitterFeed object
   * 
   * @returns true if all tests are passed, false otherwise
   */
  public static boolean testRatioTwiterator() {
    {
      Calendar testing = Calendar.getInstance();
      testing.set(2012, Calendar.MAY, 22, 14, 46, 03);
      Tweet.setCalendar(testing);
      TwitterFeed<Tweet> test = new TwitterFeed<Tweet>();
      test.setMode(TimelineMode.LIKE_RATIO);
      test.add(0, new Tweet(new User("test"), "testing"));
      test.add(1, new Tweet(new User("test2"), "testing"));
      User test3 = new User("test3");
      test.add(2, new Tweet(test3, "testing3"));
      test.get(2).like();
      test.get(2).like();
      test.get(2).retweet();
      for (Tweet object : test) {
        String expected = "testing3";
        if (object.getText() != expected) {
          return false;
        }
      }
      return true;
    }
  }

  /**
   * prints out all results for each tester method
   */
  public static void main(String[] args) {
    System.out.println("test user: " + testUser());
    System.out.println("test tweet: " + testTweet());
    System.out.println("test node: " + testNode());
    System.out.println("test add tweet: " + testAddTweet());
    System.out.println("test insert tweet: " + testInsertTweet());
    System.out.println("test delete tweet: " + testDeleteTweet());
    System.out.println("test chronotwitter: " + testChronoTwiterator());
    System.out.println("test verifiedtwitter: " + testVerifiedTwiterator());
    System.out.println("test ratiotwitter: " + testRatioTwiterator());
  }

}
