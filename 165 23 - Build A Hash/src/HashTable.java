import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private ArrayList<List<String>> table;
    private int numBuckets;
    private int numElements;
    private String hashFunction;

    public HashTable(int numBuckets, String hashFunction) {
        this.numBuckets = numBuckets;
        this.hashFunction = hashFunction;
        this.numElements = 0;
        table = new ArrayList<List<String>>(numBuckets);
        for(int i = 0; i < numBuckets; i++){
            table.add(new ArrayList<String>());
        }
    }

    /**TODO - Complete This Function
     * This function should insert the key at the index given by the hash.
     * To do this:
     * The hash has been calculated for you, so to get the index take the hash % numBuckets
     * The index is the bucket to insert into.
     *
     * check to make sure that item is not already in the bucket. We don't want to insert a duplicate
     *
     * @param key - item to insert into the table
     * @return True if the item could be inserted or false if it is a duplicate
     */
    public boolean insert(String key){
        int hash;
        switch(hashFunction){
            case "ASCII":
                hash = asciiHash(key);
                break;
            case "PRIME":
                hash = primeHash(key);
                break;
            case "MYHASH":
                hash = myHash(key);
                break;
            default:
                hash = key.hashCode();
        }
        //YOUR CODE HERE
        int idx = hash % numBuckets;
        List<String> insert = table.get(idx);
        if (insert.contains(key)){
            return false;
        }
        insert.add(key);
        //table.add(idx, insert);
        return true;
        // if (table.get(idx).size() == 0){
        //     List<String> insert = new ArrayList<String>(1);
        //     insert.add(key);
        //     table.add(idx, insert);
        // } else {
        //     int insertSize = table.get(idx).size();
        //     List<String> insert = new ArrayList<String>(insertSize + 1);
        //     for (String s: table.get(idx)){
        //         insert.add(s);
        //     }
        //     insert.add(key);
        //     table.set(idx, insert);
        // }
    }

    /**TODO - Complete This Function
     * To compute the hash add the ASCII values of all the characters in the string
     *
     * Make sure the hash is never negative!(Hint: Absolute Value)
     * @param key
     * @return an integer representing the hash of the given item
     */
    public int asciiHash(String key){
        int hash = 0;
        for (char c : key.toCharArray()){
            hash += (int) c;
        }
        hash = Math.abs(hash);
        return hash;
    }
    
    /**TODO - Complete This Function
     * Initialize the hash to 9 and then iterate through the string and multiply the ASCII value of each character of
     * the string by 17 and add it to the hash
     *
     *
     * Make sure the hash is never negative!(Hint: Absolute Value)
     * @param key
     * @return
     */
    public int primeHash(String key){
        int hash = 9;
        for (char c : key.toCharArray()){
            hash += (int) c * 17;
        }
        hash = Math.abs(hash);
        return hash;
    }
    /**TODO - Complete This Function
     * get the ASCII value of the first character and multiply it by 18
     *
     * Make sure the hash is never negative!(Hint: Absolute Value)
     * @param key
     * @return
     */
    public int myHash(String key){
        int hash = 0;
        hash += key.charAt(0);
        hash *= 18;
        hash = Math.abs(hash);
        return hash;
    }

    public String toString(){
        StringBuilder ret = new StringBuilder("");
        for(int i = 0; i < table.size(); i++){
            List<String> bucket = table.get(i);
            ret.append(String.format("bucket %d: %s\n", i, bucket.toString()));
        }
        return ret.toString();
    }
    public static void main(String[] args){
       HashTable test = new HashTable(7, "ASCII");
       /* Hash Tests */
       System.out.println("Testing asciiHash...");
       int ans = 387;
       int hash1 = test.asciiHash("Liam");
       System.out.printf("Expected: %d, Actual: %d\n", ans, hash1);
       if(ans != hash1){
           System.out.println("FAILURE -> Something is wrong with your asciiHash!");
       }
       System.out.println("");
       HashTable test2 = new HashTable(7, "PRIME");

       int ans2 = 15887;
       int hash2 = test2.primeHash("Charlotte");
       System.out.println("Testing primeHash...");
       System.out.printf("Expected: %d, Actual: %d\n", ans2, hash2);
       if(ans2 != hash2){
           System.out.println("FAILURE -> Something is wrong with your primeHash!");
       }
       System.out.println("");
       HashTable test3 = new HashTable(7, "MYHASH");
       int ans3 = 1296;
       int hash3 = test3.myHash("Harper");
       System.out.println("Testing myHash...");
       System.out.printf("Expected: %d, Actual: %d\n", 1296, test.myHash("Harper"));
       if(ans3 != hash3){
           System.out.println("FAILURE -> Something is wrong with your myHash!");
       }
       System.out.println("");

        /* Table tests */
        String[] names = {"Liam", "Noah", "William", "James","Oliver",
                "Benjamin", "Charlotte", "Mia", "Evelyn", "Harper",
                "Lucas", "Mason", "Lucas"};
        HashTable[] tables = new HashTable[3];
        tables[0] = new HashTable(5, "ASCII");
        tables[1] = new HashTable(5, "PRIME");
        tables[2] = new HashTable(5, "MYHASH");
        for(HashTable table: tables){
            //insert each name in names into the each table in hashtables
            for(String name : names){
                table.insert(name);
            }
        }
        int i = 0;
        for(HashTable table: tables){
            System.out.println("TABLE " + i);
            System.out.println(table);
            i++;
        }
    }
}
