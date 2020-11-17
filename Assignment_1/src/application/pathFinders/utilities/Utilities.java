package application.pathFinders.utilities;

import java.awt.*;
import java.util.*;
import java.util.List;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IUtilities;

import static java.lang.Math.*;
import static java.lang.Math.pow;

public class Utilities implements IUtilities {
	private List<Integer> goalState = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

	@Override
	public ArrayList<List<Integer>> getNeighbors(List<Integer> state) {
		ArrayList<List<Integer>> neighbors = new ArrayList<List<Integer>>();

		for (int i = 0; i < state.size(); i++) {
			if (state.get(i) == 0) {
				List<Integer> rightNeighbor = swapElements(state, i, "right");
				List<Integer> downNeighbor = swapElements(state, i, "down");
				List<Integer> leftNeighbor = swapElements(state, i, "left");
				List<Integer> upNeighbor = swapElements(state, i, "up");

				if (!rightNeighbor.equals(state))
					neighbors.add(rightNeighbor);

				if (!downNeighbor.equals(state))
					neighbors.add(downNeighbor);

				if (!leftNeighbor.equals(state))
					neighbors.add(leftNeighbor);

				if (!upNeighbor.equals(state))
					neighbors.add(upNeighbor);
			}
		}
		return neighbors;
	}

	@Override
	public ArrayList<Entry> getNeighbors(Entry state, String options) {
		ArrayList<Entry> neighbors = new ArrayList<Entry>();
		List<Integer> stateVal = state.getItem();

		for (int i = 0; i < state.getItem().size(); i++) {
			if (stateVal.get(i) == 0) {
				List<Integer> rightNeighbor = swapElements(stateVal, i, "right");
				List<Integer> downNeighbor = swapElements(stateVal, i, "down");
				List<Integer> leftNeighbor = swapElements(stateVal, i, "left");
				List<Integer> upNeighbor = swapElements(stateVal, i, "up");

				if (!rightNeighbor.equals(stateVal)) {
					int cost = options == "mnhatn"
							? getMnhatnCost(stateVal, rightNeighbor) + getMnhatnCost(rightNeighbor, goalState)
							: getEucCost(stateVal, rightNeighbor) + getEucCost(rightNeighbor, goalState);
					neighbors.add(new Entry(cost, rightNeighbor));
				}

				if (!downNeighbor.equals(stateVal)) {
					int cost = options == "mnhatn"
							? getMnhatnCost(stateVal, downNeighbor) + getMnhatnCost(downNeighbor, goalState)
							: getEucCost(stateVal, downNeighbor) + getEucCost(downNeighbor, goalState);
					neighbors.add(new Entry(cost, downNeighbor));
				}

				if (!leftNeighbor.equals(stateVal)) {
					int cost = options == "mnhatn"
							? getMnhatnCost(stateVal, leftNeighbor) + getMnhatnCost(leftNeighbor, goalState)
							: getEucCost(stateVal, leftNeighbor) + getEucCost(leftNeighbor, goalState);
					neighbors.add(new Entry(cost, leftNeighbor));
				}

				if (!upNeighbor.equals(stateVal)) {
					int cost = options == "mnhatn"
							? getMnhatnCost(stateVal, upNeighbor) + getMnhatnCost(upNeighbor, goalState)
							: getEucCost(stateVal, upNeighbor) + getEucCost(upNeighbor, goalState);
					neighbors.add(new Entry(cost, upNeighbor));
				}

			}
		}
		return neighbors;
	}

	@Override
	public List<Integer> swapElements(List<Integer> state, int index, String direction) {
		List<Integer> neighbor = new ArrayList<>();
		neighbor.addAll(state);
		if (direction.equals("right")) {
			if ((index + 1) % 3 != 0) {
				int temp = neighbor.get(index);
				neighbor.set(index, neighbor.get(index + 1));
				neighbor.set(index + 1, temp);
			}
		} else if (direction.equals("down")) {
			if (index + 3 < neighbor.size()) {
				int temp = neighbor.get(index);
				neighbor.set(index, neighbor.get(index + 3));
				neighbor.set(index + 3, temp);
			}
		} else if (direction.equals("left")) {
			if (index % 3 != 0) {
				int temp = neighbor.get(index);
				neighbor.set(index, neighbor.get(index - 1));
				neighbor.set(index - 1, temp);
			}
		} else if (direction.equals("up")) {
			if (index - 3 >= 0) {
				int temp = neighbor.get(index);
				neighbor.set(index, neighbor.get(index - 3));
				neighbor.set(index - 3, temp);
			}
		}
		return neighbor;
	}

	@Override
	public boolean checkStateStack(List<Integer> neighbor, Stack<INode> frontier) {
		for (INode arr : frontier) {
			if (neighbor.equals(arr.getItem())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkStatePQ(List<Integer> neighbor, PriorityQueue<Entry> frontier) {
		for (Entry arr : frontier) {
			if (neighbor.equals(arr.getItem())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getKey(List<Integer> neighbor, PriorityQueue<Entry> frontier) {
		for (Entry arr : frontier) {
			if (neighbor.equals(arr.getItem())) {
				return arr.getKey();
			}
		}
		return 0;
	}

	@Override
	public boolean checkStateQueue(List<Integer> neighbor, Queue<INode> frontier) {
		for (INode arr : frontier) {
			if (neighbor.equals(arr.getItem())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkStateVisited(List<Integer> neighbor, Set<List<Integer>> expanded) {
		if (expanded.contains(neighbor)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean goalTest(List<Integer> state) {
		return state.equals(goalState);
	}

	@Override
	public ArrayList<List<Integer>> getPath(INode node) {
		ArrayList<List<Integer>> path = new ArrayList<List<Integer>>();
		while (node != null) {
			path.add(0, node.getItem());
			node = node.getPrev();
		}
		return path;
	}

	@Override
	public int getMnhatnCost(List<Integer> current, List<Integer> goal) {
		int result = 0;
		for (int i = 0; i < 9; i++) {
			Point currentPos = position(current.get(i));
			Point goalPos = position(goal.get(i));
			result += abs(currentPos.x - goalPos.x) + abs(currentPos.y - goalPos.y);
		}
		return result;
	}

	@Override
	public int getEucCost(List<Integer> current, List<Integer> goal) {
		int result = 0;
		for (int i = 0; i < 9; i++) {
			Point currentPos = position(current.get(i));
			Point goalPos = position(goal.get(i));
			result += sqrt(pow(currentPos.x - goalPos.x, 2) + pow(currentPos.y - goalPos.y, 2));
		}
		return result;
	}

	private Point position(int index) {
		return new Point(index / 3, index % 3);
	}
}
