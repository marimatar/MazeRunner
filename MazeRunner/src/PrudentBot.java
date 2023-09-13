import java.util.Random;
public class PrudentBot implements MazeRunner {
    private MazeManager manager;
    private int row;
    private int column;
    private int turnExited = 0;
    private String name = "PrudentBot";

    public PrudentBot(int row, int column, MazeManager manager){
        this.manager = manager;
        this.row = row;
        this.column = column;
    }

    public Direction takeTurn(){
        Random rand = new Random();
        int size = 0;
        Direction[] available = new Direction[4];
        for(Direction dir : new Direction[] {Direction.NORTH,Direction.SOUTH,Direction.EAST,Direction.WEST}){
            if(this.manager.look(this, dir) == MazeSquare.EMPTY || this.manager.look(this, dir) == MazeSquare.EXIT){
                available[size++] = dir;
            }
        }
        if(size == 0){
            return Direction.HERE;
        }
        else{
            return available[rand.nextInt(size)];
        }
    }
    public void setExited(int exitTurn){
        this.turnExited = exitTurn;
    }

    public boolean hasExited(){
        return turnExited!=0;
    }
    public int turnExited(){
        return turnExited;
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
    public String getName(){
        return name;
    }

}
