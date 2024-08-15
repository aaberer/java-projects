import java.util.List;

public class DoubleTable implements ITable{
    String[] table;
    int numItems;
    public DoubleTable(int numSlots){
        if(numSlots < 7){
            System.out.println("ERROR: the table must have size of at least 7 so it was set automatically!!");
            table = new String[7];
        }else{
            table = new String[numSlots];
        }
        this.numItems = 0;
    }
    /**
     * TODO - Complete This Function
     * This function should insert the key at the index given by the hash.
     * To do this:
     * 1. compute hash() and get index using modulo. insert the element if the slot is empty
     * 2. if the slot is not empty, first check to make sure it's not a duplicate and then
     * insert it if not. We don't want to insert a duplicate.
     * 3. if the slot is full and it's not a duplicate key then find the next index using
     * (hash() + i*hashTwo()) % numSlots
     * 4. keep incrementing i by 1 until the new index == hash(key) and then you know that the table is full
     * and you can return false.
     *
     *
     * @param key - item to insert into the table
     * @return True if the item could be inserted or false if it is a duplicate
     */
    @Override
    public boolean insert(String key) {
        int hash = hash(key);
        int idx = hash % table.length;
        int i = 0;
        if (table[idx] == null){
            table[idx] = key;
            return true;
        }
        if (table[idx].equals(key)){
            return false;
        } else {
            while (idx != hash(key) && i < table.length){
                idx = (hash(key) + i * hashTwo(key) % table.length) % table.length;
                if (table[idx] == null){
                    table[idx] = key;
                    return true;
                }
                ++i;
            }
        }
        return false;
    }

    /**
     * This method should return True or False if there is no matching element
     * To do this following similar steps as insert until you find the key or if you find an empty spot then
     * you know the key is not in the table and you can return false.
     * @param key
     * @return True if the element was found or False if it wasn't
     */
    @Override
    public boolean search(String key) {
        for (String s : table){
            if (s != null){
                if (s.equals(key)){
                return true;
                }
            }
        }
        return false;
    }
    /**
     * a hash function which simply uses Java's built in function for hashing strings.
     * @param key
     * @return
     */
    public int hash(String key){
        return Math.abs(key.hashCode());
    }
    /**
     * a second hash function to be used to deal with collisions
     * @param key
     */
    public int hashTwo(String key){
        return 7 - (Math.abs(key.hashCode()) % 7);
    }
    /**
     * Should return a string representation of the table like:
     * slot 1: item or list of items
     * slot 2: item or list of items
     * ...
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < table.length; i++){
            ret.append(String.format("Bucket %d: %s\n", i, table[i]));
        }
        return ret.toString();
    }
    public static void main(String[] args){
        /* Table tests */
        String[] names = {"Liam", "Noah", "William", "James","Oliver",
                "Benjamin", "Charlotte", "Mia", "Evelyn", "Harper",
                "Lucas", "Mason", "Lucas"};
        DoubleTable table = new DoubleTable(names.length-2);
        for(String name : names){
            table.insert(name);
        }
        System.out.println(table);
        /* search() tests */
        String name = "Liam";
        boolean search = table.search(name);
        System.out.printf("Testing search(%s)...\n", name);
        if(!search){
            System.out.print("FAILURE -> ");
        }else{
            System.out.print("PASSED -> ");
        }
        System.out.printf("Expected: %b, Actual: %b\n", true, search);

        String name2 = "Joshua";
        boolean search2 = table.search(name2);
        System.out.printf("Testing search(%s)...\n", name2);
        if(search2){
            System.out.print("FAILURE -> ");
        }else{
            System.out.print("PASSED -> ");
        }
        System.out.printf("Expected: %b, Actual: %b\n", false, search2);
    }
}
