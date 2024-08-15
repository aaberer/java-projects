public class TwoDArraysUtility {

    /** Student Self-Explanation
     * check to see if space is already occupied by a piece
     * returning boolean true or false if move is applicable
     */
    public boolean play(int player, int column, int[][] board) {
        int row = board.length - 1;
        int col = column - 1;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        --row;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        --row;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        --row;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        --row;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        --row;
        if (board[row][col] == 0) {
            board[row][col] = player;
            return true;
        }
        if (board[row][col] != 0) {
            return false;
        }
        return true;
    }

    /** Student Self-Explanation
     * Check 2D Array for diagonal horizontal or vertical win
     * start with vertical check, then horizontal, then diagonal
     * three nested for loops can be used
     */
    public int checkForWin(int[][] board) {
        //vertical check
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 0; col < board[0].length; col++) {
                int check = board[row][col];
                if (check == 0) {continue;}
                if (check == board[row+1][col] && check == board[row+2][col] && check == board[row+3][col]){
                    return check;
                }
            }
        }
        //horizontal check
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[0].length - 4; col++) {
                int check = board[row][col];
                if (check == 0) {continue;}
                if (check == board[row][col+1] && check == board[row][col+2] && check == board[row][col+3]){
                    return check;
                }
            }
        }
        //dia check L>R
        for (int row = 0; row <= board.length - 4; row++) {
            for (int col = 0; col < board[0].length - 4; col++) {
                int check = board[row][col];
                if (check == 0) {continue;}
                if (check == board[row+1][col+1] && check == board[row+2][col+2] && check == board[row+3][col+3]){
                    return check;
                }
            }
        }
        //dia check R>L
        for (int row = 3; row < board.length; row++) {
            for (int col = 0; col <= board[0].length - 4; col++) {
                int check = board[row][col];
                if (check == 0) {continue;}
                if (check == board[row-1][col+1] && check == board[row-2][col+2] && check == board[row-3][col+3]){
                    return check;
                }
            }
        }
        return 0;
    }
}
