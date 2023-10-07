public class HangmanMain {

    public static void main(String[] args){
        HangmanGame game = new HangmanGame();
        HangmanView view = new HangmanView();

        view.printScreen();
        String x = view.getInput("Would you like easy or hard words?");
        game.initializeWords(game.selectDifficulty(x));
        game.wordSelect();
        view.getZero();
        game.printWordProgress();
        while(!(game.finished)){
            String guess = view.getInput("Guess a letter: ");
            game.guess(guess);
        }

    }

}