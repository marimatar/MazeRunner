import java.util.Random;

public class RandomBot implements MazeRunner {
    private MazeManager manager;
    private int row;
    private int column;
    private int turnExited = 0;
    private String name = "RandomBot";

    public RandomBot(int row, int column, MazeManager manager){
        this.manager = manager;
        this.row = row;
        this.column = column;
    }

    //adding these in from mazerunner.java for a successful inheritance and am able to succesfully implement mazerunner

    public Direction takeTurn(){
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.SOUTH;
            case 2:
                return Direction.WEST;
            default:
                return Direction.EAST;
        }
    }
    public int getRow(){
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

    public boolean hasExited(){
        return turnExited!=0;
    }
    public void setExited(int exitTurn){
        this.turnExited= exitTurn;
    }
    public int turnExited(){
        return turnExited;
    }
    public String getName(){
        return name;
    }
}
