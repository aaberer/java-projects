import java.util.Scanner;

public class DescendingOrder {
    public static void selectionSortDescendTrace(int [] numbers, int numElements) {
        int tmp;
        int max;
        for(int i = 0; i < numElements-1; ++i){
            max = i;
            for(int j = i + 1; j < numElements; ++j){
                if(numbers[max] < numbers[j]){
                    max = j;
                }
            }
            tmp = numbers[i];
            numbers[i] = numbers[max];
            numbers[max] = tmp;
            for (int idx = 0; idx < numElements; idx++) {
                System.out.print(numbers[idx] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);

        int input = 0;
        int numElements = 0;
        int [] numbers = new int[10];
        int idx = 0;

        while(scnr.hasNextInt()) {
            input = scnr.nextInt();
            if (input == -1){break;}
            if (numElements > 9){break;}
            numbers[idx] = input;
            numElements++;
            idx++;
        }
        selectionSortDescendTrace(numbers, numElements);
    }
}