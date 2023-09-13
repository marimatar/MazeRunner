/* This is a runner that doesn't run -- whenever it's asked to move, it stays where it's
 * currently at. Name is inspired by the main character in the classic Herman Melville short story
 * "Bartleby the Scrivener"
 * https://en.wikipedia.org/wiki/Bartleby,_the_Scrivener
 *
 * A good first step is to write a MazeManager that can
 * 1) set up the maze
 * 2) Add BartlebyBots to that maze
 * 3) Correctly display the maze with the BartlebyBots on it
 *
 * The BartlebyBot implementation may be a helpful reference for writing your own MazeRunners
 */

public class BartlebyBot implements MazeRunner {
    private MazeManager manager;    // not actually used by BartlebyBot, because
                                    // BartlebyBot doesn't ever need to ask the manager
                                    // for anything.
    private int row;
    private int column;
    private int turnExited = 0;
    private String name = "BartlebyBot";


    public BartlebyBot(int row, int column, MazeManager manager){
        this.manager = manager;
        this.row = row;
        this.column = column;
    }


    // When this method is called on other types of MazeRunner, they'll figure out where they want to move to
    // (by whatever means they determine) and then request to move in that direction. BartlebyBot already knows
    // that it would prefer not to move, so it simply returns Direction.HERE
    public Direction takeTurn(){
        return Direction.HERE;
    }

    public int getRow() {
        return row;
    }
    public int getColumn(){
        return column;
    }

    public void setRow(int row){
        this.row = row;
    }

    public void setColumn(int column){
        this.column = column;
    }

    public boolean hasExited() {
        return turnExited != 0;
    }
    public void setExited(int exitTurn){
        this.turnExited = exitTurn;
    }
    public int turnExited(){
        return turnExited;
    }
    public String getName(){ return name; }

}
