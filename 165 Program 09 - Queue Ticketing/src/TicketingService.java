import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

public class TicketingService {

   public static void main (String[] args) {
    Scanner scnr = new Scanner(System.in);
    String personName = "";
    int counter = 0;
    int youPosition = -1;

    Queue<String> peopleInQueue = new LinkedList<String>();
    
    personName = "";
    while (!personName.equals("-1")) {
        personName = scnr.nextLine();
        peopleInQueue.add(personName);
        ++counter;
        if (personName.equals("You")){
            youPosition = counter;
            break;
        }
    }

    System.out.println("Welcome to the ticketing service... ");
    System.out.println("You are number " + youPosition + " in the queue.");

    // In a loop, remove head person from peopleInQueue,                                    
    // output their name and that they have purchased a ticket,                             
    // then output your position in the queue. When you are at                              
    // the head, output that you can purchase your ticket.   
    
    while (!peopleInQueue.isEmpty()) {
        personName = peopleInQueue.remove();
        --counter;
        if (personName.equals("You")) {
            youPosition = counter;
            System.out.println("You can now purchase your ticket!");
            break;
        }
        else {
            System.out.println(personName + " has purchased a ticket.");
            youPosition = counter;
            System.out.println("You are now number " + (youPosition));
        }
        
    } 
    scnr.close();
   }
}
