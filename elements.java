/**
 * This class is called elements and it is a class which creates nodes filled with values of keyType
 * and ValueType it is used to implement the hash map which uses an array of linkedlists.
 * 
 * @author Sahas
 */
public class elements<KeyType,ValueType> {
  private KeyType key;
  private ValueType value;
  private elements next;

  /**
   * This is a constructor which sets the values which are going to go inside of the node. These
   * values are of type keyType and ValueType. This constructor also creates the node. 
   * 
   * 
   * @param key which is of type keyType and will be stored as a value inside the element node 
   * @param value which is of type ValueType and will be stored as a value inside the element node
   */
  public elements(KeyType key, ValueType value) {
    this.key = key;
    this.value = value;
  }
  /**
   * This method is a getter method which gets the keyType value from the node 
   */
  public KeyType getkeyType() {
    return this.key;
  }
  /**
   * This method is a getter method which gets the ValueType value from the node 
   */
  public ValueType getValueType() {
    return this.value;
  }
  
  

}

