package src;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class TestMain{
   public static void main(String[] args) {
         IQueue<Integer> q = new ArrayQueue<>(10);
        
        System.out.println("Offering to Queue:");
        System.out.println("     Offer(2) -> " + q.offer(2));
        System.out.println("     Offer(3) -> " + q.offer(3));
        System.out.println("     Offer(4) -> " + q.offer(4));
        System.out.println("     size() should be 3 -> " + q.size());
        System.out.println("     contains(2) should be true -> " + q.contains(2));

        System.out.println("Adding more elements using add():");
        System.out.println("     add(4);  add(5); add(6); add(7); add(8); add(9); add(10);");
        q.add(4);  q.add(5); q.add(6); q.add(7); q.add(8); q.add(9); q.add(10);
        System.out.println("     size() should be 10 -> " + q.size());
        
        System.out.println("Trying to offer to a full queue:");
        System.out.println("     offer(11) should return false -> " + q.offer(11));
        System.out.println("     size() should still be 10 -> " + q.size());
        
        
        System.out.println("Polling and removing some elements:");
        System.out.println("     poll() should be 2 -> " + q.poll());
        System.out.println("     remove() should be 3 -> " + q.remove());
        System.out.println("Testing clear:");
        q.clear();
        System.out.println("     size() should be 0 -> " + q.size());
        
        System.out.println("Trying to poll from an empty queue:");
        System.out.println("     poll() should return null -> " + q.poll());
        System.out.println("     size() should still be 0 -> " + q.size());
        
        System.out.println("Testing contains() with TrainCar objects:");
        ArrayQueue<TrainCar> qCar = new ArrayQueue<>(10);
        qCar.offer(new TrainCar("Engine", "orange", 80523));
        qCar.offer(new TrainCar("Passenger", "blue", 24601));
        qCar.offer(new TrainCar("Caboose", "red", 12345));
        TrainCar toTest = new TrainCar("Caboose", "red", 12345);
        System.out.println("     contains(" + toTest + ") should be true -> " + qCar.contains(toTest));
        System.out.println();

        // final testing uncomment the following line to get comprehensive testing.
        //Note: the jar has hundred_thousand and million for these test_counts, but zyBooks is not able to run 100000 tests.
        final int thousand = 1000;
        QueueTestProgram.printFailedTests(thousand, ArrayBlockingQueue::new,ArrayQueue::new);
    }
}