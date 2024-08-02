import java.util.Scanner;
import java.util.LinkedList;

public class ShoppingList{
   public static void main (String[] args) {
      Scanner scnr = new Scanner(System.in);
      LinkedList<ListItem> shoppingList = new LinkedList<ListItem>();

      String item;

      item = scnr.nextLine();
      while (!item.equals("-1")) {
        shoppingList.add(new ListItem(item));
        item = scnr.nextLine();
      }
    
      for (int i = 0; i < shoppingList.size(); ++i){
        shoppingList.get(i).printNodeData();
      }
   }
}