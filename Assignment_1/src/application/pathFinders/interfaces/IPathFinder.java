package application.pathFinders.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IPathFinder {
	/**
	 * finds the solution of the giving puzzle with the selected algorithm
	 * 
	 * @param initialState:
	 *            array of index indicates the given puzzle
	 * @param options:
	 *            string to choose mnhatn or euclidean distance in A_Star search
	 *            algorithm
	 * @return an array list contains the shortest states from initial to goal
	 */
	public ArrayList<List<Integer>> findPath(List<Integer> initialState, String options);

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
	 * the maximum depth reached by the algorithm
	 * 
	 * @return integer that indicates the max depth ever visited by the search
	 */
	public int maxDepthReached();

	/**
	 * finds all nodes that the search technique has passed
	 *
	 * @return Set contains all the visited nodes
	 */
	public Set<List<Integer>> nodesExpanded();
}
