package cs250.hw2;
import java.util.*;

public class Memory {
    public static volatile long runningTotalV;

    public static void task1(String sizeIn, String experimentsIn){
        int size = Integer.parseInt(sizeIn);
        int experiments = Integer.parseInt(experimentsIn);
        long returnRegular = 0;
        long returnVolatile = 0;
        long runningStartRegular = 0;
        long runningFinishRegular = 0;
        long runningStartVolatile = 0;
        long runningFinishVolatile = 0;
        long addSubRegular = 0;
        long addSubVolatile = 0;
        for (int i = 0; i < experiments; ++i){
            runningStartRegular = System.nanoTime();
            addSubRegular = task1AddSubtractRegular(size);
            runningFinishRegular = System.nanoTime();

            returnRegular += (runningFinishRegular - runningStartRegular);

            runningStartVolatile = System.nanoTime();
            addSubVolatile = task1AddSubtractVolatile(size);
            runningFinishVolatile = System.nanoTime();

            returnVolatile += (runningFinishVolatile - runningStartVolatile);
        }
        double avgRegTime = (double) returnRegular / (double) experiments / 1e9;
        double avgVolTime = (double) returnVolatile / (double) experiments / 1e9;
        System.out.printf("Regular: %.5f seconds%n", (double) avgRegTime);
        System.out.printf("Volatile: %.5f seconds%n", (double) avgVolTime);
        System.out.printf("Avg regular sum: %.2f %n" , (double) addSubRegular);
        System.out.printf("Avg volatile sum: %.2f %n", (double) addSubVolatile);
    }
    public static long task1AddSubtractRegular(int size){
        long runSumR = 0;
        for (int i = 0; i < size; i++){
            if (i % 2 == 0){
                runSumR += i;
            } else {
                runSumR -= i;
            }
        }
        return runSumR;
    }
    public static long task1AddSubtractVolatile(int size){
        long runSumV = 0;
        for (runningTotalV = 0; runningTotalV < size; runningTotalV++){
            if (runningTotalV % 2 == 0){
                runSumV += runningTotalV;
            } else {
                runSumV -= runningTotalV;
            }
        }
        return runSumV;
    }

    public static void task2(String sizeIn, String experimentsIn, String seedIn){
        int size = Integer.parseInt(sizeIn);
        int experiments = Integer.parseInt(experimentsIn);
        Random rand = new Random(Integer.parseInt(seedIn));
        Integer[] intArr = new Integer[size];
        int sum = 0;
        long runTimeFirst = 0;
        long runTimeLast = 0;
        for (int i = 0; i < size; i++){
            intArr[i] = rand.nextInt();
        }
        for (int j = 0; j < experiments; j++){
            for (int k = 0; k < size / 10; k++){
                long startTimeFirst = System.nanoTime();
                Integer elementFirst = intArr[k];
                long endTimeFirst = System.nanoTime();
                runTimeFirst += (endTimeFirst - startTimeFirst);
                sum += elementFirst;
            }
            int lastRandIdx = size - rand.nextInt(size / 10);
            long startTimeLast = System.nanoTime();
            int elementLast = intArr[lastRandIdx];
            long endTimeLast = System.nanoTime();
            runTimeLast += (endTimeLast - startTimeLast);
            sum += elementLast;
        }
        runTimeFirst /= (size / 10);
        double avgFirstTime = (double) runTimeFirst / (double) experiments;
        double avgLastTime = (double) runTimeLast / (double) experiments;
        double avgSum = (double) sum / (double) experiments;
        System.out.printf("Avg time to access known element: %.2f nanoseconds%n", avgFirstTime);
        System.out.printf("Avg time to access random element: %.2f nanoseconds%n", avgLastTime);
        System.out.printf("Sum: %.2f%n", avgSum);
    }

    public static void task3(String sizeIn, String experimentsIn){
        int size = Integer.parseInt(sizeIn);
        int experiments = Integer.parseInt(experimentsIn);
        long runTimeStart = 0;
        long runTimeFinish = 0;
        long totalTimeTree = 0;
        long totalTimeList = 0;
        Random rand = new Random();
        TreeSet<Integer> treeSet = new TreeSet<>();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++){
            treeSet.add(i);
            linkedList.add(i);
        }
        for (int k = 0; k < experiments; k++){
            int random = rand.nextInt();
            runTimeStart = System.nanoTime();
            treeSet.contains(random);
            runTimeFinish = System.nanoTime();
            totalTimeTree += (runTimeFinish - runTimeStart);

            runTimeStart = 0;
            runTimeFinish = 0;

            runTimeStart = System.nanoTime();
            linkedList.contains(random);
            runTimeFinish = System.nanoTime();
            totalTimeList += (runTimeFinish - runTimeStart);
        }
        double avgTree = (double) totalTimeTree / (double) experiments;
        double avgList = (double) totalTimeList / (double) experiments;
        System.out.printf("Avg time to find in set: %.2f nanoseconds%n", avgTree);
        System.out.printf("Avg time to find in list: %.2f nanoseconds%n", avgList);
    }

    public static void main(String args[]){
        if (args.length != 3){
            System.out.println("Incorrect number fof arguments have been provided. Program Terminating!");
            System.exit(0);
        }
        String arg1String = args[0]; // size
        String arg2String = args[1]; // experiments
        String arg3String = args[2]; // seed

        System.out.println("Task 1");
        task1(arg1String, arg2String);

        System.out.println("Task 2");
        task2(arg1String, arg2String, arg3String);
        
        System.out.println("Task 3");
        task3(arg1String, arg2String);
    }
}