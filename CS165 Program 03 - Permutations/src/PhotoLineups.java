import java.util.Scanner;
import java.util.ArrayList;

public class PhotoLineups {

    // TODO: Write method to create and output all permutations of the list of names.
    public static void printAllPermutations(ArrayList<String> permList, ArrayList<String> nameList) {
        int idx = 0;
        if (nameList.isEmpty()){
            for (int i = 0; i < permList.size() - 1; i++){
                System.out.print(permList.get(i) + ", ");
                ++idx;
            }
            System.out.print(permList.get(idx) + "\n");
        }
        else {
            for (int i = 0; i < nameList.size(); i++){
                ArrayList<String> newPerm = new ArrayList<>(permList);
                ArrayList<String> newName = new ArrayList<>(nameList);
                newPerm.add(nameList.get(i));
                newName.remove(i);
                printAllPermutations(newPerm, newName);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        ArrayList<String> nameList = new ArrayList<String>();
        ArrayList<String> permList = new ArrayList<String>();
        String name = scnr.next();

        // TODO: Read a list of names into nameList; stop when -1 is read. Then call recursive method.
        while (!name.equals("-1")){
            nameList.add(name);
            name = scnr.next();
        }
        printAllPermutations(permList, nameList);
    }
}
