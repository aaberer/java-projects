import java.util.ArrayList;
import java.util.List;

public class ChainedTable implements ITable{
    private ArrayList<List<String>> table;
    private int numBuckets;
    private int numElements;

    public ChainedTable(int numBuckets){
        this.numBuckets = numBuckets;
        this.numElements = 0;
        table = new ArrayList<List<String>>();
        for(int i = 0; i < numBuckets; i++){
            table.add(new ArrayList<String>());
        }
    }
    /**
     * TODO - Complete This Function
     * This function should insert the key at the index given by the hash.
     * To do this:
     * 1. use hash() to calculate the index of the bucket to insert into using modulo
     * 2. check if the bucket contains the key and if so return false. if it does not then insert
     * the key into the bucket and return true.
     * <p>
     * check to make sure that item is not already in the bucket. We don't want to insert a duplicate
     *
     * @param key - item to insert into the table
     * @return True if the item could be inserted or false if it is a duplicate
     */
    @Override
    public boolean insert(String key) {
        int hash = hash(key);
        int idx = hash % numBuckets;
        List<String> insert = table.get(idx);
        if (insert.contains(key)){
            return false;
        }
        insert.add(key);
        return true;
    }

    /**
     * This method should return True or False if there is no matching element
     * To do this find the bucket using the hash function and modulo and then check if that bucket contains
     * the key
     * @param key
     * @return True if the element was found or False if it wasn't
     */
    @Override
    public boolean search(String key) {
        int hash = hash(key);
        int idx = hash % numBuckets;
        List<String> insert = table.get(idx);
        if (insert.contains(key)){
            return true;
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
     * Should return a string representation of the table like:
     * slot 1: item or list of items
     * slot 2: item or list of items
     * ...
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("");
        for(int i = 0; i < table.size(); i++){
            List<String> bucket = table.get(i);
            ret.append(String.format("Bucket %d: %s\n", i, bucket.toString()));
        }
        return ret.toString();
    }
    public static void main(String[] args){
        /* Table tests */
        String[] names = {"Liam", "Noah", "William", "James","Oliver",
                "Benjamin", "Charlotte", "Mia", "Evelyn", "Harper",
                "Lucas", "Mason", "Lucas"};
        ChainedTable table = new ChainedTable(names.length-2);
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
