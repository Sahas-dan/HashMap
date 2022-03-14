import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * This class implements a hash map using an array of linkedlists. The class has put, remove, clear,
 * size, get, containsKey methods. Additionally it also a private method to increase the size of the
 * array.
 * 
 * This class contains no mutator methods, only accessors.
 * 
 * TODO: implement the Comparable<Order> interface
 */
public class HashtableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

  private LinkedList<elements>[] map; // private array of linkedlists which implements a linkedlist
  // of type elements which is used to implement the hash map.

  /**
   * This is a constructor which sets the size of the array of linkedlists named map to a capacity
   * value
   * 
   * 
   * @param capacity which serves as the size of the array of linkedlists named map
   */
  @SuppressWarnings("unchecked")
  public HashtableMap(int capacity) {
    map = (LinkedList<elements>[]) new LinkedList[capacity]; // here is the size when given a number
                                                             // -> a constructor
  }

  /**
   * This is a default constructor which sets the size of the array of linkedlists to 20 elements
   * 
   * 
   */
  @SuppressWarnings("unchecked")
  public HashtableMap() {
    map = (LinkedList<elements>[]) new LinkedList[20]; // default constructor
  }

  /**
   * This method is called grow and it is a helper method. This method is there to double the size
   * of the array if the array reaches the load factor that calls for rehashing.
   * 
   * @param map which is the array of linkedlists which needs its size to be doubled
   * 
   * 
   */
  @SuppressWarnings("unchecked")
  private LinkedList<elements>[] grow(LinkedList<elements>[] map) { // private method that is used
    // to grow the size of
    // the linked list
    LinkedList<elements>[] newMap;
    newMap = new LinkedList[map.length * 2];
    for (int i = 0; i < map.length; i++) {
      newMap[i] = map[i]; // copies all the old elements into the new array which is double the size
                          // of the old one
    }
    return newMap;
  }


  /**
   * This method is called put and it inserts elements into the hash map. If the key of the element
   * is null or already exits in the map then false is returned by the method. If not then the
   * element is added into the array by feeding the param's into the elements class which creates a
   * new node which is inputed into the array at the correct index. The index is determined by get
   * the absolute value of the key's hashcode and taking the modulo of that value with the number of
   * elements in the array.
   * 
   * @param key   which is the key of the value to be inserted into the array of linkedlists
   * @param value which is the value of the key, this value will also be inserted into the array of
   *              linkedlists
   * 
   */
  @SuppressWarnings("unused")
  @Override
  public boolean put(KeyType key, ValueType value) {
    int index = Math.abs(key.hashCode()) % map.length; // the index



    if (key == null) {
      return false; // false if null
    }

    if (containsKey(key) == true) {
      return false; // if the key is already in the array returns false.
    }
    if (map[index] == null) { // if that element is empty then you need to create a new linkednode
      // elements at that node because there is no linkednode at that index
      map[index] = new LinkedList<elements>();
    }
    map[index].add(new elements(key, value)); // adds the node to that index

    if (((double) this.size() / (double) map.length) >= .8) {
      map = grow(map); // map grows if goes above the percent
    }

    return true;
  }


  /**
   * This method is called get and it gets the value of a given key from the array. It searches
   * through the array for an element with that same key and gets the value from that key and
   * returns that value. If the key is not found in the array the hold will still remain null at
   * which point the method will throw a NoSuchElementException.
   * 
   * @exception NoSuchElementException if the key cannot be found in the array
   * @param key which is the key of the value to be got from the
   */
  @SuppressWarnings("unchecked")
  @Override
  public ValueType get(KeyType key) throws NoSuchElementException {
    ValueType hold = null;

    int index = Math.abs(key.hashCode()) % map.length; // the index


    if (map[index] != null) {
      for (int i = 0; i < map[index].size(); i++) {
        if (map[index] != null) {
          if (map[index].get(i).getkeyType() == key) {
            hold = (ValueType) map[index].get(i).getValueType(); // gets the value at the given key
                                                                 // if
                                                                 // in
            // the array
          }
        }
      }
    }

    if (hold == null) {
      throw new NoSuchElementException(); // if there is no value assigned to the hold variable then
      // the key is not in the array. Meaning that there is no
      // such element in the array.
    }
    return hold;
  }

  /**
   * This method is called size and gets the number of elements in the array. It returns this
   * number.
   * 
   * 
   */
  @Override
  public int size() {
    int number = 0; // for each

    for (int i = 0; i < map.length; i++) {
      if (map[i] != null) { // makes sure that the specific index is not null
        for (int j = 0; j < map[i].size(); j++) { // iterates through all elements in the array
          if (map[i].get(j) != null) { // makes sure that the specific element within the linkedlist
                                       // is not null
            number++; // adds it to the number of elements in the list counter.
          }
        }
      }

    }
    return number;
  }

  /**
   * This method is called containsKey and it is a method which searches to check if a key exists
   * inside the array of linkedlists. If it exists the method returns true if not the method returns
   * false.
   * 
   * @param key which is the key to be searched for
   * 
   * 
   */
  @Override
  public boolean containsKey(KeyType key) {

    int index = Math.abs(key.hashCode()) % map.length; // the index


    if (map[index] != null) {
      for (int i = 0; i < map[index].size(); i++) { // iterates through the linkedlist within that
                                                    // specific index
        if (map[index].get(i).getkeyType() == key) {
          return true; // gets the value at the given key if // in the array
        }
      }
    }

    return false;
  }

  /**
   * This method is called remove and it removes a key from the array of linkedlists. Once the
   * method finds the key it gets the value of that key and then it removes that key and its value
   * from the array.
   * 
   * @param key which is the key to be removed from the array of linkedlists
   * 
   * 
   */
  @SuppressWarnings("unchecked")
  @Override
  public ValueType remove(KeyType key) {
    ValueType hold = null;

    if (key == null) {
      return null; // if the key is null then just return null
    }
    int index = Math.abs(key.hashCode()) % map.length; // gets the index

    if (map[index] != null) {
      for (int i = 0; i < map[index].size(); i++) { // iterates through the linkedlist at the given
                                                    // index
        if (map[index].get(i) != null) { // makes sure that the linkednode specified is not null
          if (map[index].get(i).getkeyType().equals(key)) {
            hold = (ValueType) map[index].get(i).getValueType(); // gets the value at the given key
                                                                 // if in the array
            map[index].remove(i);
          }
        }
      }

      return hold;
    }

    return null;
  }

  /**
   * This method is called clear and it clears all the elements of the array.
   * 
   * 
   */
  @SuppressWarnings("unchecked")
  @Override
  public void clear() {
    LinkedList<elements>[] newList = new LinkedList[map.length];

    for (int i = 0; i < map.length; i++) {
      map[i] = newList[i]; // sets every element of the map array to empty
    }

  }



}
