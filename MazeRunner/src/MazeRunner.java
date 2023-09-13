/* this interface should be implemented by all your classes that represent
 * moving objects within the maze.
 *
 * the takeTurn() method should return the direction the MazeRunner wants to move,
 * using the Direction enumerated type. During takeTurn() the MazeRunner can do
 * various things to help it decide on what direction it wants to return,
 * Notably, it can call the look() method defined in the MazeManager as
 * many times as it wants.
 *
 * When called, setExited() should mark the runner as having exited the maze on a particular turn. Exited
 * runners should never take actions.
 * hasExited returns true if the Runner has exited, false otherwise
 * turnExited returns the turn the Runner exited if the Runner has exited, 0 otherwise
 *
 * get/set Row/Column do what you expect them to do.
 * getName does what you expect it to do. The names of your runners should just be
 * the name of the class they're defined in.
 */

public interface MazeRunner {
    public Direction takeTurn();
    public void setExited(int exitTurn);

    public boolean hasExited();
    public int turnExited();
    public int getRow();
    public int getColumn();
    public void setRow(int row);
    public void setColumn(int column);
    public String getName();
}
