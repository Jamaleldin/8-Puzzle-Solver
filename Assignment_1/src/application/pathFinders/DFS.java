package application.pathFinders;

import java.util.Stack;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DFS implements IPathFinder {
	private Set<List<Integer>> visitedStates;
	private Stack<INode> frontier;
	private IUtilities utilities;
	private int depth;
	private int maxDepth;
	private long time;
	private int cost;

	DFS() {
		visitedStates = new HashSet<List<Integer>>();
		frontier = new Stack<INode>();
		utilities = new Utilities();
		depth = 0;
		cost = 0;
		maxDepth = 0;
	}

	@Override
	public ArrayList<List<Integer>> findPath(List<Integer> initialState, String options) {
		time = new Date().getTime();
		visitedStates.clear();
		frontier.clear();
		frontier.push(new Node(initialState));
		while (!frontier.isEmpty()) {
			INode state = frontier.pop();
			depth = ((Node) state).getDepth();
			maxDepth = Math.max(depth, maxDepth);
			visitedStates.add(state.getItem());

			if (utilities.goalTest(state.getItem())) {
				time = new Date().getTime() - time;
				ArrayList<List<Integer>> path = utilities.getPath(state);
				cost = path.size();
				return path;
			}

			ArrayList<List<Integer>> neighbors = utilities.getNeighbors(state.getItem());

			for (List<Integer> neighbor : neighbors) {
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
	public Set<List<Integer>> nodesExpanded() {
		return visitedStates;
	}

	@Override
	public int maxDepthReached() {
		return maxDepth;
	}
}
