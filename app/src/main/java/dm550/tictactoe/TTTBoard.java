package dm550.tictactoe;

/** represents a tic tac toe board of a given size */
public class TTTBoard {
    
    /** 2-dimensional array representing the board
     * coordinates are counted from top-left (0,0) to bottom-right (size-1, size-1)
     * board[x][y] == 0   signifies free at position (x,y)
     * board[x][y] == i   for i > 0 signifies that Player i made a move on (x,y)
     */
    private int[][] board;
    
    /** size of the (quadratic) board */
    private int size;
    
    /** constructor for creating a copy of the board
     * not needed in Part 1 - can be viewed as an example 
     */
    public TTTBoard(TTTBoard original) {
        this.size = original.size;
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                this.board[y][x] = original.board[y][x];
            }
        }
    }
    
    /** constructor for creating an empty board for a given number of players */
    public TTTBoard(int numPlayers) {
        this.size = numPlayers+1;
        this.board = new int[this.getSize()][this.getSize()];
    }
    
    /** checks whether the board is free at the given position */
    public boolean isFree(Coordinate c) {
        // TODO
        if (board[c.getX()][c.getY()] == 0){
            return true;
        } return false;
    }

    /** returns the players that made a move on (x,y) or 0 if the positon is free */
    public int getPlayer(Coordinate c) {
        // TODO
        if (board[c.getX()][c.getY()] == 0) {
            return 0;
        } return board[c.getX()][c.getY()];
    }

    /** record that a given player made a move at the given position
     * checks that the given positions is on the board
     * checks that the player number is valid
     */
    public void addMove(Coordinate c, int player) {
        // TODO
        if (c.checkBoundaries(getSize(),getSize()) && 0 < player && player < TTTBoard.this.size){
            board[c.getX()][c.getY()] = player;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    /** returns true if, and only if, there are no more free positions on the board */
    public boolean checkFull() {
        // TODO
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                if (this.board[y][x] == 0){
                    return false;
                }
            }
        } return true;
    }

    /** returns 0 if no player has won (yet)
     * otherwise returns the number of the player that has three in a row
     */
    public int checkWinning() {
        // TODO
        for (int y = 0; y < getSize(); y++){
            for (int x = 0; x < getSize()-2; x++){
                //System.out.println("Size"+ getSize()+ "coords" +x+ " " +y);
                Coordinate c = new XYCoordinate(x,y);
                if (checkSequence(c,1,0) != 0) {return checkSequence(c,1,0);}
                }
            }
        for (int x = 0; x < getSize(); x++){
            for (int y = 0; y < getSize()-2; y++){
                //System.out.println("Size"+ getSize()+ "coords" +x+ " " +y);
                Coordinate c = new XYCoordinate(x,y);
                if (checkSequence(c,0,1) != 0) {return checkSequence(c,0,1);}
            }
        }
        for (int x = 0; x < getSize()-2; x++){
            for (int y = 2; y < getSize(); y++){
                System.out.println("Size"+ getSize()+ "coords" +x+ " " +y);
                Coordinate c = new XYCoordinate(x,y);
                if (checkSequence(c,1,-1) != 0) {return checkSequence(c,1,-1);}
            }
        }
        for (int x = 0; x < getSize()-2; x++){
            for (int y = 0; y < getSize()-2; y++){
                System.out.println("Size"+ getSize()+ "coords" +x+ " " +y);
                Coordinate c = new XYCoordinate(x,y);
                if (checkSequence(c,1,1) != 0) {return checkSequence(c,1,1);}
            }
        }
        return 0;
    }

    /** internal helper function checking one row, column, or diagonal */
    private int checkSequence(Coordinate start, int dx, int dy) {
        // TODO
        System.out.println("X:"+start.getX()+" Y:"+start.getY()+" Board:"+this.board[start.getX()][start.getY()]);
        Coordinate start1 = start.shift(dx,dy);
        Coordinate start2 = start.shift(2*dx,2*dy);
        if (this.board[start.getX()][start.getY()] == this.board[start1.getX()][start1.getY()] && this.board[start.getX()][start.getY()] == this.board[start2.getX()][start2.getY()]){
            return this.board[start.getX()][start.getY()];
        }
        return 0;
    }
    
    /** getter for size of the board */
    public int getSize() {
        return this.size;
    }
    
    /** pretty printing of the board
     * usefule for debugging purposes
     */
    public String toString() {
        String result = "";
        for (int y = 0; y < this.size; y++) {
            for (int x = 0; x < this.size; x++) {
                result += this.board[y][x]+" ";
            }
            result += "\n";
        }
        return result;
    }

}
