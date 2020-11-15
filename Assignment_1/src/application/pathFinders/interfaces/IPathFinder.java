package application.pathFinders.interfaces;

import java.util.ArrayList;

public interface IPathFinder {
	/**
	 * finds the solution of the giving puzzle with the selected algorithm
	 * 
	 * @param initialState:
	 *            array of index indicates the given puzzle
	 * @return an array list contains the shortest states from initial to goal
	 */
	public ArrayList<int[]> findPath(int[] initialState, String options);

	/**
	 * counting the number of moves done from initial state to the goal
	 * 
	 * @return an integer indicates the total number of moves have been done during
	 *         the search
	 */
	public int CostOfPath();

	/**
	 * calculates the time taken from initial state to the goal
	 * 
	 * @return number of seconds taken to reach to the goal
	 */
	public int timeColapsed();

	/**
	 * the depth you took in the search tree to find the goal from the initial state
	 * 
	 * @return integer that indicates the depth that the search technique reached
	 */
	public int depthOfsearch();

	/**
	 * finds all nodes that the search technique has passed
	 *
	 * @return an array list contains all the visited nodes
	 */
	public ArrayList<int[]> nodesExpanded();
}
