public class TwentyQuestionsView {

    public void splash() {
        System.out.println("Welcome to Twenty Questions");
    }

    public void welcome(){
        System.out.println("Welcome to Twenty Questions.\nPlease enter player name: ");
    }


    public void tooHigh(){
        System.out.println("Too high.");
    }

    public void tooLow(){
        System.out.println("Too low.");
    }


    public void winnerMessage(){
        System.out.println("Your guessed correctly! Congratulations!\n");
    }


    public void loserMessage(){
        System.out.println("You ran out of guesses. You lose. Better luck next time!\n");
    }


    public void exitGame() {
        System.out.println("Thank you for playing!\n");
    }


}