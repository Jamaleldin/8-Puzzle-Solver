package application.pathFinders.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IUtilities;

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
		return path;
	}
}
