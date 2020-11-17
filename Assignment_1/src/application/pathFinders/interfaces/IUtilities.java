package application.pathFinders.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import application.pathFinders.utilities.Entry;

public interface IUtilities {
	/**
	 * find the surrounded reachable neighbors to the current state
	 * 
	 * @param state:
	 *            array of integers indicates the current state
	 * @return ArrayList contains the surrounded neighbors to the current state
	 */
	public ArrayList<List<Integer>> getNeighbors(List<Integer> state);

	/**
	 * find the surrounded reachable neighbors to the current state
	 *
	 * @param state
	 *            : array of integers indicates the current state
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
	public List<Integer> swapElements(List<Integer> state, int index, String direction);

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
	public boolean checkStateStack(List<Integer> neighbor, Stack<INode> frontier);

	/**
	 * check if the given neighbor in the priority queue or not
	 *
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param frontier:
	 *            the frontier list that contains the nodes to be expanded in the A*
	 * @return true if neighbor in the list false if not found
	 */
	public boolean checkStatePQ(List<Integer> neighbor, PriorityQueue<Entry> frontier);

	/**
	 * check if the given neighbor in the queue or not
	 * 
	 * @param neighbor:
	 *            array of integers indicates the current neighbor
	 * @param frontier:
	 *            the frontier list that contains the nodes to be expanded in the
	 *            BFS
	 * @return true if neighbor in the list false if not found
	 */
	public boolean checkStateQueue(List<Integer> neighbor, Queue<INode> frontier);

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
	public boolean checkStateVisited(List<Integer> neighbor, Set<List<Integer>> expanded);

	/**
	 * check if the goal is reached or not
	 * 
	 * @param state:
	 *            array of integers indicates the current state
	 * @return true if the goal is reached
	 */
	public boolean goalTest(List<Integer> state);

	/**
	 * back track from the goal node
	 * 
	 * @param node:
	 *            the goal node
	 * @return ArrayList holds the states from initial to end
	 */
	public ArrayList<List<Integer>> getPath(INode node);

	/**
	 * calculate the Manhattan cost
	 * 
	 * h = abs(current_cell:x - goal:x) + abs(current_cell:y-goal:y)
	 * 
	 * @param current:
	 *            current state in the search
	 * 
	 */
	public int getMnhatnCost(List<Integer> current, List<Integer> goal);

	/**
	 * calculate the Euclidean distance
	 * 
	 * h = sqrt((current_cell:x - goal:x)^2+ (current_cell:y-goal:y)^2)
	 */
	public int getEucCost(List<Integer> current, List<Integer> goal);


	public int getKey(List<Integer> neighbor, PriorityQueue<Entry> frontier);
}
