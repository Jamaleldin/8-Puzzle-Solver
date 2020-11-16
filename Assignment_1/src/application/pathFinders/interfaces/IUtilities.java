package application.pathFinders.interfaces;

import application.pathFinders.Entry;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public interface IUtilities {
	/**
	 * find the surrounded reachable neighbors to the current state
	 * 
	 * @param state:
	 *            array of integers indicates the current state
	 * @return ArrayList contains the surrounded neighbors to the current state
	 */
	public ArrayList<int[]> getNeighbors(int[] state);

	/**
	 * find the surrounded reachable neighbors to the current state
	 *
	 * @param state :
	 *            array of integers indicates the current state
	 * @return ArrayList contains the surrounded neighbors to the current state
	 */
	public ArrayList<Entry> getNeighbors(Entry state, String option);

	/**
	 * swap the current number with the given direction
	 * 
	 * @param state:
	 *            the current state
	 * @param index:
	 *            the index of the number that needed to be swapped
	 * @param direction:
	 *            the direction of swapping in the 8-puzzle(up,down,left,right)
	 * @return the resulted state after moving the number in the giving direction
	 */
	public int[] swapElements(int[] state, int index, String direction);

	/**
	 * check if the given neighbor in the stack or not
	 * 
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param frontier:
	 *            the frontier list that contains the nodes to be expanded in the
	 *            DFS
	 * @return true if neighbor in the list false if not found
	 */
	public boolean checkStateStack(int[] neighbor, Stack<INode> frontier);

	/**
	 * check if the given neighbor in the priority queue or not
	 *
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param frontier:
	 *            the frontier list that contains the nodes to be expanded in the
	 *            A*
	 * @return true if neighbor in the list false if not found
	 */
	public boolean checkStatePQ(int[] neighbor, PriorityQueue<Entry> frontier);

	/**
	 * check if the given neighbor in the queue or not
	 * 
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param frontier:
	 *            the frontier list that contains the nodes to be expanded in the
	 *            BFS or A_Star
	 * @return true if neighbor in the list false if not found
	 */
	public boolean checkStateQueue(int[] neighbor, Queue<INode> frontier);

	/**
	 * check if the given neighbor in the expanded list or not
	 * 
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param expanded:
	 *            the expanded list that contains the nodes that had been visited
	 *            before in the search
	 * @return true if neighbor is in the expanded set
	 */
	public boolean checkStateVisited(int[] neighbor, ArrayList<int[]> expanded);

	/**
	 * check if the goal is reached or not
	 * 
	 * @param state:
	 *            array of integers indicates the current state
	 * @return true if the goal is reached
	 */
	public boolean goalTest(int[] state);

	/**
	 * back track from the goal node
	 * 
	 * @param node:
	 *            the goal node
	 * @return ArrayList holds the states from initial to end
	 */
	public ArrayList<int[]> getPath(INode node);

	int getMnhatnCost(int[] initialState, int[] goalState);

}
