package application.pathFinders.utilities;

import java.awt.*;
import java.util.*;

import application.pathFinders.Entry;
import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IUtilities;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class Utilities implements IUtilities {
	private int[] goalState = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };

	@Override
	public ArrayList<int[]> getNeighbors(int[] state) {
		ArrayList<int[]> neighbors = new ArrayList<int[]>();

		for (int i = 0; i < state.length; i++) {
			if (state[i] == 0) {
				int[] rightNeighbor = swapElements(state, i, "right");
				int[] downNeighbor = swapElements(state, i, "down");
				int[] leftNeighbor = swapElements(state, i, "left");
				int[] upNeighbor = swapElements(state, i, "up");

				if (!Arrays.equals(rightNeighbor, state))
					neighbors.add(rightNeighbor);

				if (!Arrays.equals(downNeighbor, state))
					neighbors.add(downNeighbor);

				if (!Arrays.equals(leftNeighbor, state))
					neighbors.add(leftNeighbor);

				if (!Arrays.equals(upNeighbor, state))
					neighbors.add(upNeighbor);
			}
		}
		return neighbors;
	}

	@Override
	public ArrayList<Entry> getNeighbors(Entry state, String options) {
		ArrayList<Entry> neighbors = new ArrayList< Entry>();

		for (int i = 0; i < state.getValue().length; i++) {
			int [] stateVal = state.getValue();
			if (stateVal[i] == 0) {
				int[] rightNeighbor = swapElements(stateVal, i, "right");
				int[] downNeighbor = swapElements(stateVal, i, "down");
				int[] leftNeighbor = swapElements(stateVal, i, "left");
				int[] upNeighbor = swapElements(stateVal, i, "up");

				if (!Arrays.equals(rightNeighbor, stateVal)){
					int cost = options == "mnhatn" ?
							getMnhatnCost(stateVal,rightNeighbor)+
							getMnhatnCost(rightNeighbor,goalState):
							getEucCost(stateVal,rightNeighbor)+getEucCost(rightNeighbor,goalState);
					neighbors.add(new Entry(cost,rightNeighbor ));
				}

				if (!Arrays.equals(downNeighbor, stateVal)){
					int cost = options == "mnhatn" ?
							getMnhatnCost(stateVal,downNeighbor)+
									getMnhatnCost(downNeighbor,goalState):
							getEucCost(stateVal,downNeighbor)+getEucCost(downNeighbor,goalState);
					neighbors.add(new Entry(cost,downNeighbor ));
				}


				if (!Arrays.equals(leftNeighbor, stateVal)){
					int cost = options == "mnhatn" ?
							getMnhatnCost(stateVal,leftNeighbor)+
									getMnhatnCost(leftNeighbor,goalState):
							getEucCost(stateVal,leftNeighbor)+getEucCost(leftNeighbor,goalState);
					neighbors.add(new Entry(cost,leftNeighbor ));
				}


				if (!Arrays.equals(upNeighbor, stateVal)){
					int cost = options == "mnhatn" ?
							getMnhatnCost(stateVal,upNeighbor)+
									getMnhatnCost(upNeighbor,goalState):
							getEucCost(stateVal,upNeighbor)+getEucCost(upNeighbor,goalState);
					neighbors.add(new Entry(cost,upNeighbor ));
				}

			}
		}
		return neighbors;
	}

	@Override
	public int[] swapElements(int[] state, int index, String direction) {
		int[] neighbor = state.clone();
		if (direction.equals("right")) {
			if ((index + 1) % 3 != 0) {
				int temp = neighbor[index];
				neighbor[index] = neighbor[index + 1];
				neighbor[index + 1] = temp;
			}
		} else if (direction.equals("down")) {
			if (index + 3 < neighbor.length) {
				int temp = neighbor[index];
				neighbor[index] = neighbor[index + 3];
				neighbor[index + 3] = temp;
			}
		} else if (direction.equals("left")) {
			if (index % 3 != 0) {
				int temp = neighbor[index];
				neighbor[index] = neighbor[index - 1];
				neighbor[index - 1] = temp;
			}
		} else if (direction.equals("up")) {
			if (index - 3 >= 0) {
				int temp = neighbor[index];
				neighbor[index] = neighbor[index - 3];
				neighbor[index - 3] = temp;
			}
		}
		return neighbor;
	}

	@Override
	public boolean checkStateStack(int[] neighbor, Stack<INode> frontier) {
		for (INode arr : frontier) {
			if (Arrays.equals(arr.getItem(), neighbor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkStatePQ(int[] neighbor, PriorityQueue<Entry> frontier) {
		for (Entry arr : frontier) {
			if (Arrays.equals(arr.getItem(), neighbor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkStateQueue(int[] neighbor, Queue<INode> frontier) {
		for (INode arr : frontier) {
			if (Arrays.equals(arr.getItem(), neighbor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkStateVisited(int[] neighbor, ArrayList<int[]> expanded) {
		for (int[] arr : expanded) {
			if (Arrays.equals(arr, neighbor)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean goalTest(int[] state) {
		return Arrays.equals(state, goalState);
	}

	@Override
	public ArrayList<int[]> getPath(INode node) {
		ArrayList<int[]> path = new ArrayList<int[]>();
		while (node != null) {
			path.add(0, node.getItem());
			node = node.getPrev();

		}

		System.out.println("");
		return path;
	}

	public int getMnhatnCost(int[] current, int[] goal){
		int result = 0;
		for(int i = 0; i<9; i++){
			Point currentPos = position(current[i]);
			Point goalPos = position(goal[i]);
			result += abs(currentPos.x - goalPos.x) + abs(currentPos.y - goalPos.y);
		}
		return result;
	}

	public int getEucCost(int [] current, int [] goal){
		int result = 0;
		for(int i = 0; i<9; i++){
			Point currentPos = position(current[i]);
			Point goalPos = position(goal[i]);
			result += sqrt(pow(currentPos.x - goalPos.x,2) + pow(currentPos.y - goalPos.y,2));
		}
		return result;
	}

	private Point position(int index){
		return new Point(index/3,index%3);
	}
}
