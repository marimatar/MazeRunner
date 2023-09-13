/* uses the tree produced by mazegenerator.MazeGenerator.mazegenerator.MazeGenerator to produce a 2d
 * character array. Your MazeManager class should use this
 * array to represent the maze.
 *
 * ====
 * YOU DO NOT NEED TO EDIT THIS FILE
 * ====
 *
 * NOTE: because of how the maze is generated, the values for "rows" and "columns"
 * are not the actual number of rows and columns -- the actual number is:
 *
 * rows (or columns) * BORDER_SIZE + 1
 *
 *
 *
 */


import mazegenerator.MazeGenerator;
import mazegenerator.Pair;

import java.util.Random;

public class Maze {

    private char[][] grid;
    private MazeGenerator mazeGenerator;
    private Random rng;

    // these shouldn't be understood as rows and columns in the display,
    // but instead as the number of possible intersections in the horizontal direction
    // and vertical direction (this is because we're using mazegenerator.UF as our generator)
    // (I'll fix this in a future revision... --Ben)
    private int rows;
    private int columns;

    public static final int BORDER_SIZE = 2;
    public static final int WALL = '#';
    public static final int EXIT = 'x';
    public static final int EMPTY = ' ';

    public int getRows(){
        return grid.length;
    }
    public int getColumns(){
        return grid[0].length;
    }

    public char getCharacterAt(int row, int column){
        return grid[row][column];
    }

    public void setCharacterAt(int row, int column, char newChar){
        grid[row][column] = newChar;
    }

    public Maze(int rows, int columns, Random rng){
        this.rows = rows;
        this.columns = columns;
        this.rng = rng;
        this.mazeGenerator = new MazeGenerator(rows, columns, rng);
        makeMaze();
    }

    public Maze(int rows, int columns){
        this(rows, columns, new Random());
    }

    public String toString(){
        String output = "";
        for (int i = 0; i < rows * (BORDER_SIZE) + 1; i++){
            for (int j = 0; j < columns * (BORDER_SIZE) + 1; j++){
                output += grid[i][j];
            }
            output += "\n";
        }
        return output;
    }


    private void initializeGrid(){
        grid = new char[rows * (BORDER_SIZE) + 1][columns * (BORDER_SIZE) + 1];
        for (int i = 0; i < rows * (BORDER_SIZE) + 1; i++){
            for (int j = 0; j < columns * (BORDER_SIZE) + 1; j++){
                grid[i][j] = EMPTY;
            }
        }
    }

    private void drawCorners(){
        for (int i = 0; i < rows * (BORDER_SIZE); i += 2){
            for (int j = 0; j < columns * (BORDER_SIZE); j += 2){
                grid[i][j] = WALL;
            }
        }
    }

    private void drawExit(){
        Pair exit = new Pair(rng.nextInt(rows), columns-1, columns);
        Pair exitLocation = getDrawingCenter(exit);
        int row = exitLocation.getRow();
        int column = exitLocation.getColumn();
        grid[row][column] = EXIT;

    }

    // gets the grid coordinates of the center of pair.
    private Pair getDrawingCenter(Pair pair){
        return new Pair(pair.getRow() * 2 + 1, pair.getColumn() * 2 + 1, columns);
    }

    private boolean isEdge(Pair first, int rowOffset, int columnOffset){
        int firstRow = first.getRow();
        int firstColumn = first.getColumn();

        int secondRow = firstRow + rowOffset;
        int secondColumn = firstColumn + columnOffset;

        if (secondRow > -1 && secondRow < mazeGenerator.getRows()
                && secondColumn > -1 && secondColumn < mazeGenerator.getColumns()){
            Pair second = new Pair(secondRow, secondColumn, columns);
            return mazeGenerator.hasEdge(first, second);
        }

        return false;
    }

    private void drawCorners(Pair center){
        int row = center.getRow();
        int column = center.getColumn();

        grid[row-1][column-1] = WALL;
        grid[row-1][column+1] = WALL;
        grid[row+1][column-1] = WALL;
        grid[row+1][column+1] = WALL;
    }

    private void drawWalls(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                Pair first = new Pair(i, j, columns);
                Pair center = getDrawingCenter(first);
                drawCorners(center);
                if (!isEdge(first, 0, -1)){
                    grid[center.getRow()][center.getColumn()-1] = WALL;
                }
                if (!isEdge(first, 0, 1)){
                    grid[center.getRow()][center.getColumn()+1] = WALL;
                }
                if (!isEdge(first, -1, 0)){
                    grid[center.getRow()-1][center.getColumn()] = WALL;
                }
                if (!isEdge(first, 1, 0)){
                    grid[center.getRow()+1][center.getColumn()] = WALL;
                }
            }
        }
    }


    private void makeMaze(){
        initializeGrid();
        drawCorners();
        drawWalls();
        drawExit();
    }

    private static int HORIZONTAL_INTERSECTIONS = 8;
    private static int VERTICAL_INTERSECTIONS = 8;


    // used for testing
    public static void main(String[] args) {
        Maze maze = new Maze(HORIZONTAL_INTERSECTIONS, VERTICAL_INTERSECTIONS);
        System.out.print(maze);
    }
}
