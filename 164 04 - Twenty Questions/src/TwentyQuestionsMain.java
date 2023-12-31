import java.util.Random;
import java.util.Scanner;

public class TwentyQuestionsMain {

    /**
     * Main driver for the program.
     * The main function starts with a scanner function to collect the users input
     * to gather some basic information about the player.
     * Calling functions throughout to either play the game or
     * display messages to the user.
     */
    public static void main(String[] args) {
        // These are the different objects the control needs to work
        TwentyQuestionsView mainView = new TwentyQuestionsView();
        TwentyQuestions game = new TwentyQuestions();

        mainView.splash();
        mainView.welcome();
        Scanner scanner = new Scanner(System.in);
        String playerName = scanner.nextLine();
        System.out.println(game.nameIntroduction(playerName));
        Random random = new Random();
        int num = random.nextInt(99) + 1;
        int guessCounter = 0;
        System.out.println("A number between 1-100 has been chosen.");
        while(guessCounter < 20){
            System.out.println("Enter a guess: ");
            int guess = scanner.nextInt();
            guessCounter++;
            if(game.playGame(guess, num) == 0){
                mainView.winnerMessage();
                break;
            }
            if(game.playGame(guess, num) == -1){
                mainView.tooLow();
            }
            else{
                mainView.tooHigh();
            }
        }
        if(guessCounter >= 20){
            mainView.loserMessage();
        }
        System.out.println("The number was " + num + ", " + game.numberInfo(num));
        mainView.exitGame();
        scanner.close();
    }

}