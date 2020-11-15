package application.pathFinders;

import java.util.Stack;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.util.ArrayList;

public class DFS implements IPathFinder {
	private ArrayList<int[]> visitedStates;
	private Stack<INode> frontier;
	private IUtilities utilities;

	DFS() {
		visitedStates = new ArrayList<int[]>();
		frontier = new Stack<INode>();
		utilities = new Utilities();
	}

	@Override
	public ArrayList<int[]> findPath(int[] initialState) {
		visitedStates.clear();
		frontier.clear();
		frontier.push(new Node(initialState));
		while (!frontier.isEmpty()) {
			INode state = frontier.pop();
			visitedStates.add(state.getItem());

			if (utilities.goalTest(state.getItem()))
				return utilities.getPath(state);

			ArrayList<int[]> neighbors = utilities.getNeighbors(state.getItem());

			for (int[] neighbor : neighbors) {
				boolean inFrontier = utilities.checkStateStack(neighbor, frontier);
				boolean inVisited = utilities.checkStateVisited(neighbor, visitedStates);

				if (!inFrontier && !inVisited) {
					INode nextNeighbor = new Node(neighbor);
					nextNeighbor.setPrev(state);
					frontier.push(nextNeighbor);
				}
			}
		}
		return null;
	}

	@Override
	public int CostOfPath() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int timeColapsed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int depthOfsearch() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<int[]> nodesExpanded() {
		return visitedStates;
	}
}
