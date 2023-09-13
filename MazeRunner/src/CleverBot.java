import java.util.HashMap;
import java.util.Random;

import static java.lang.Integer.MAX_VALUE;

public class CleverBot implements MazeRunner {
    private MazeManager manager;
    private int row;
    private int column;
    private int turnExited = 0;
    private HashMap<Integer,Integer> frequency = new HashMap<>();
    //using a hashmap of integers to keep track of positions in order to check for the frequency of how many times those areas have been explored.
    private String name = "CleverBot";

    public CleverBot(int row, int column, MazeManager manager){
        this.manager = manager;
        this.row = row;
        this.column = column;
        for(int i = 0; i < manager.getColumns() * manager.getRows(); i++){
            frequency.put(i,0);
        }
    }
    private int getOffset(Direction dir){
        switch(dir){
            case EAST:
                return 1;
            case WEST:
                return -1;
            case SOUTH:
                return manager.getColumns();
            case NORTH:
                return -manager.getColumns();
            default:
                return 0;
        }
    }
    public Direction takeTurn(){
        int size = 0;
        Direction[] available = new Direction[4];

        for (Direction dir : new Direction[]{Direction.EAST,Direction.WEST,Direction.SOUTH,Direction.NORTH}){
            if(this.manager.look(this, dir) == MazeSquare.EMPTY || this.manager.look(this, dir) == MazeSquare.EXIT){
                available[size++] = dir;
            }
        }

        if(size == 0){
            return Direction.HERE;
        }
        else{
            int curMin = MAX_VALUE;
            Direction curDir = Direction.HERE;
            for(int i = 0; i < size; i++){
                Direction dir = available[i];
                int next = row * manager.getColumns() + column + getOffset(dir);
                if(curMin > frequency.get(next)){
                    curMin = frequency.get(next);
                    curDir = dir;
                }
            }
            int next = row * manager.getColumns() + column + getOffset(curDir);
            frequency.put(next, frequency.get(next)+1);
            return curDir;
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
