/**
 * Simple hash table implementation using quadratic probing.
 *
 * @author Stephan Jamieson
 * @version 24/4/2015
 */
import java.math.*;;
public class QPHashTable  extends HashTable {


    /**
     * Create an QPHashTable with DEFAULT_SIZE table.
     */
    public QPHashTable() { super(); }

    /**
     * Create an QPHashTable with the given default size table.
     */
    public QPHashTable(final int size) { super(size); }

    /**
     * Find the index for entry: if entry is in the table, then returns its position;
     * if it is not in the table then returns the index of the first free slot.
     * Returns -1 if a slot is not found (such as when the table is full under LP).
     *
     */
    protected int findIndex(String key) {


      int index = super.hashFunction(key);
      if(table[index]==null || table[index].equals(key)){
        super.incProbeCount();
        return index;
      }
      else{
        int count=1;
        while(count<super.tableSize()){
          int newHashFun = (index + (count*count))%super.tableSize();
          super.incProbeCount();

          if(table[newHashFun]==null){
            super.incProbeCount();
            return newHashFun;
          }
          else if(table[newHashFun].equals(key)){
            super.incProbeCount();
            return newHashFun;
          }
          count++;
        }
        super.incProbeCount();
      }
      return -1;
    }
}
