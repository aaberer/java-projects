//  Andrew Aberer | aaberer@colosate.edu
public class TwentyQuestions {

    public String nameIntroduction(String playerName){
        playerName = playerName.toUpperCase();
        String message = "";
        if (playerName.indexOf(0) < 78) {
            message = "Hi" + playerName;
        }
        else {
            message = "Hello " + playerName;
        }
        if (playerName.length() <= 5) {
            message = message + ", are you up to the challenge?";
        }
        else {
            message = message + ", good luck!";
        }

        return message;
    }


    public int playGame(int guess, int num) {
        if (guess == num) {
            return 0;
        }
        else if (guess < num) {
            System.out.println("Too low.");
            return -1;
        }
        else if (guess > num) {
            System.out.println("Too high.");
            return 1;
        }

        return 0;
    }

    public String numberInfo(int number) {
        if (number > 0) {
            
        }
        if (number == 7) {
            return "lucky sevens!";
            }
        if (number < 10) {
            return "single digit.";
            }
        if (number == 42) {
            return "the answer to life the unicerse and everything.";
            }
        else {
            return "a positive number";
        }
    }
        
}