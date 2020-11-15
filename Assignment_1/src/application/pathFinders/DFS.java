package application.pathFinders;

import java.util.Stack;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.util.ArrayList;
import java.util.Date;

public class DFS implements IPathFinder {
	private ArrayList<int[]> visitedStates;
	private Stack<INode> frontier;
	private IUtilities utilities;
	private int depth;
	private long time;
	private int cost;

	DFS() {
		visitedStates = new ArrayList<int[]>();
		frontier = new Stack<INode>();
		utilities = new Utilities();
		depth = 0;
		cost = 0;
	}

	@Override
	public ArrayList<int[]> findPath(int[] initialState, String options) {
		time = new Date().getTime();
		visitedStates.clear();
		frontier.clear();
		frontier.push(new Node(initialState));
		while (!frontier.isEmpty()) {
			INode state = frontier.pop();
			depth =  ((Node) state).getDepth();
			visitedStates.add(state.getItem());

			if (utilities.goalTest(state.getItem())) {
				time = new Date().getTime() - time;
				ArrayList<int[]> path = utilities.getPath(state);
				cost = path.size();
				return path;
			}

			ArrayList<int[]> neighbors = utilities.getNeighbors(state.getItem());

			for (int[] neighbor : neighbors) {
				boolean inFrontier = utilities.checkStateStack(neighbor, frontier);
				boolean inVisited = utilities.checkStateVisited(neighbor, visitedStates);

				if (!inFrontier && !inVisited) {
					INode nextNeighbor = new Node(neighbor, depth + 1);
					nextNeighbor.setPrev(state);
					frontier.push(nextNeighbor);
				}
			}
		}
		return null;
	}

	@Override
	public int CostOfPath() {
		return cost;
	}

	@Override
	public int timeColapsed() {
		return (int) time;
	}

	@Override
	public int depthOfsearch() {
		return depth;
	}

	@Override
	public ArrayList<int[]> nodesExpanded() {
		return visitedStates;
	}
}
