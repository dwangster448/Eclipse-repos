// --== CS400 Fall 2023 File Header Information ==--
// Name: Daniel Wang
// Email: dwang448@wisc.edu
// Group: G45
// TA: ZHEYANG XIONG
// Lecturer: Florian Heimerl

import java.util.LinkedList;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  // Use LinkedList for chaining
  protected LinkedList<Pair> table[];

  protected class Pair {

    public KeyType key;
    public ValueType value;

    public Pair(KeyType key, ValueType value) {
      this.key = key;
      this.value = value;
    }

  }

  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    // Initialize the table with the specified capacity
    table = new LinkedList[capacity];
  }

  @SuppressWarnings("unchecked")
  public HashtableMap() {
    // Initialize the table with default capacity = 32
    table = new LinkedList[32];
  }

  @Override
  public void put(KeyType key, ValueType value) throws IllegalArgumentException, 
  NullPointerException {
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }
    if (containsKey(key)) {
      throw new IllegalArgumentException("Key cannot be a duplicate");
    }

    int index = Math.abs(key.hashCode()) % table.length;

    // Check if resizing is needed before adding the new pair
    checkAndResize();

    if (table[index] == null) {
      table[index] = new LinkedList<>();
    }
    
    table[index].add(new Pair(key, value));

  }

  @Override
  public boolean containsKey(KeyType key) {
    int index = Math.abs(key.hashCode()) % table.length;

    if (table[index] != null) {
      for (Pair pair : table[index]) {
        if (pair.key.equals(key)) {
          return true;
        }
      }
    }

    return false;
  }

  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode()) % table.length;

    if (table[index] != null) {
      LinkedList<Pair> chain = table[index];

      for (Pair pair : chain) {
        if (pair.key.equals(key)) {
          return pair.value; // Return the value associated with the specified key
        }
      }
    }
    // If the key is not found, throw a NoSuchElementException
    throw new NoSuchElementException("Key not found in the hashtable");
  }

  @Override
  public ValueType remove(KeyType key) throws NoSuchElementException {
    int index = Math.abs(key.hashCode()) % table.length;

    if (table[index] != null) {
      LinkedList<Pair> chain = table[index];

      for (Pair pair : chain) {
        if (pair.key.equals(key)) {
          chain.remove(pair);
          // Return value associated with the removed key
          return pair.value;
        }
      }
    }

    // If the key is not found, throw a NoSuchElementException
    throw new NoSuchElementException("Key not found in the hashtable");
  }

  @Override
  public void clear() {
    // Remove all key-value pairs without changing the array capacity
    for (int i = 0; i < table.length; i++) {
      if (table[i] != null) {
        table[i].clear();
      }
    }
  }

  @Override
  public int getSize() {
    // Retrieve the number of key-value pairs stored in the HashtableMap
    int size = 0;

    for (LinkedList<Pair> chain : table) {
      if (chain != null) {
        size += chain.size();
      }
    }

    return size;
  }

  @Override
  public int getCapacity() {
    return table.length;
  }

  private void checkAndResize() {
    double loadFactor = (double) getSize() / getCapacity();

    if (loadFactor >= 0.75) {
      int newCapacity = table.length * 2;
      rehash(newCapacity);
    }
  }

  @SuppressWarnings("unchecked")
  private void rehash(int newCapacity) {
    LinkedList<Pair>[] newTable = new LinkedList[newCapacity];

    // Rehash all existing key-value pairs
    for (LinkedList<Pair> chain : table) {
      if (chain != null) {
        for (Pair pair : chain) {
          int newIndex = Math.abs(pair.key.hashCode()) % newCapacity;
          //Any 
          if (newTable[newIndex] == null) {
            newTable[newIndex] = new LinkedList<>();
          }

          newTable[newIndex].add(pair);
        }
      }
    }

    // Update the table reference
    table = newTable;
  }
  
  /**
   * Expects hashtable to fulfill the same data structure before, during, and
   * after one simple insertion
   */
  @Test
  public void testOneNodeInsertion() {
    HashtableMap<String, String> map = new HashtableMap<>();
    // check capacity before any manipulation to hashtable
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertEquals(0, map.getSize());

    map.put("a", "1");
 
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("a")); 
    Assertions.assertEquals(1, map.getSize()); 
    Assertions.assertEquals("1", map.get("a"));
   
  }
  
  /**
   * Expects hashmap to handle duplicate keys
   */
  @Test
  public void testDuplicateKeys() {
    HashtableMap<String, String> map = new HashtableMap<>();
    // check capacity before any manipulation to hashtable
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertEquals(0, map.getSize());

    map.put("a", "1");
 
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("a")); 
    Assertions.assertEquals(1, map.getSize()); 
    Assertions.assertEquals("1", map.get("a"));
    
    //Test multiple insertions of duplicate keys
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> map.put("a", "2") //Expecting IllegalArgumentException 
        );
    
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> map.put("a", "3") //Expecting IllegalArgumentException 
        );
   
    
    //Check that the hashtable remains the same
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("a")); 
    Assertions.assertEquals(1, map.getSize()); 
    Assertions.assertEquals("1", map.get("a"));
    }
  
  /**
   * Expects hashtable to upgrade capacity upon 75% filled entries in hashtable
   */
  @Test
  public void testLoadFactor() {
    HashtableMap<String, String> map = new HashtableMap<>(8);
    // check capacity before any manipulation to hashtable
    Assertions.assertEquals(8, map.getCapacity()); 
    Assertions.assertEquals(0, map.getSize());

    map.put("a", "1"); 
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(8, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("a")); 
    Assertions.assertEquals(1, map.getSize()); 
    Assertions.assertEquals("1", map.get("a"));
    
    map.put("b", "2");   
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(8, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("b")); 
    Assertions.assertEquals(2, map.getSize()); 
    Assertions.assertEquals("2", map.get("b"));
    
    map.put("c", "3");
    map.put("d", "4");
    map.put("e", "5");
    map.put("f", "6");
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(8, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("f")); 
    Assertions.assertEquals(6, map.getSize()); 
    Assertions.assertEquals("6", map.get("f"));
    
    map.put("g", "7");
    
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(16, map.getCapacity()); //expect change in capacity
    Assertions.assertTrue(map.containsKey("g")); 
    Assertions.assertEquals(7, map.getSize()); 
    Assertions.assertEquals("7", map.get("g"));
   
  }
  
  /**
   * Expects hashtable to fulfill the same data structure before and
   * after one deletion on a size 3 hashtable
   */
  @Test
  public void testDeletion() {
    HashtableMap<String, String> map = new HashtableMap<>();
    // check capacity before any manipulation to hashtable
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertEquals(0, map.getSize());

    map.put("a", "1");
    map.put("b", "2");
    map.put("c", "3");
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("c")); 
    Assertions.assertEquals(3, map.getSize()); 
    Assertions.assertEquals("3", map.get("c"));
    
    map.remove("a");
    // check hashtable properly deleted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertFalse(map.containsKey("a")); 
    Assertions.assertTrue(map.containsKey("b")); 
    Assertions.assertTrue(map.containsKey("c")); 
    Assertions.assertEquals(2, map.getSize()); 
   
  }
    
  /**
   * Expects hashtable to fulfill the same data structure before, during, and
   * after series of insertion, deletion then expects hashtable to properly clear all entries
   */
  @Test
  public void testAllMethods() {
    HashtableMap<String, String> map = new HashtableMap<>();
    // check capacity before any manipulation to hashtable
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertEquals(0, map.getSize());

    map.put("a", "1");
 
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("a")); 
    Assertions.assertEquals(1, map.getSize()); 
    Assertions.assertEquals("1", map.get("a"));
    
    map.put("b", "2");
    
    // check hashtable properly inserted one entry by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertTrue(map.containsKey("b")); 
    Assertions.assertEquals(2, map.getSize()); 
    Assertions.assertEquals("2", map.get("b"));
    
    map.put("c", "3");
    map.put("d", "4");
    map.remove("c");
    // check hashtable properly handles two insertion 
    //followed by one deletion by verifying all form of available properties
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertFalse(map.containsKey("c")); 
    Assertions.assertTrue(map.containsKey("d")); 
    Assertions.assertEquals(3, map.getSize()); 
    Assertions.assertEquals("4", map.get("d"));
    
    //Expects map to handle clearing of all nodes
    map.clear();
    Assertions.assertEquals(32, map.getCapacity()); 
    Assertions.assertFalse(map.containsKey("a")); 
    Assertions.assertFalse(map.containsKey("b")); 
    Assertions.assertFalse(map.containsKey("d")); 
    Assertions.assertEquals(0, map.getSize());    
  }

}
