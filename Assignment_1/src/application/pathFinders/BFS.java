package application.pathFinders;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.util.*;

public class BFS implements IPathFinder {

	private Set<List<Integer>> visitedStates;
	private Queue<INode> frontier;
	private IUtilities utilities;
	private int depth;
	private int maxDepth;
	private long time;
	private int cost;

	public BFS() {
		visitedStates = new HashSet<>();
		frontier = new LinkedList<>();
		utilities = new Utilities();
		depth = 0;
		cost = 0;
		maxDepth = 0;
	}

	@Override
	public ArrayList<List<Integer>> findPath(List<Integer> initialState, String options) {

		// tick
		time = new Date().getTime();

		// initiate the frontier with the initial state
		frontier = new LinkedList<>();
		frontier.add(new Node(initialState, 0));
		visitedStates.clear();

		while (!frontier.isEmpty()) {
			// poll from the queue
			INode state = frontier.poll();
			depth = state.getDepth();
			maxDepth = Math.max(depth, maxDepth);
			visitedStates.add(state.getItem());

			// check for goal
			if (utilities.goalTest(state.getItem())) {
				// that's it
				time = new Date().getTime() - time;
				ArrayList<List<Integer>> path = utilities.getPath(state);
				cost = path.size();
				return path;
			}

			ArrayList<List<Integer>> neighbors = utilities.getNeighbors(state.getItem());

			for (List<Integer> neighbor : neighbors) {
				boolean inFrontier = utilities.checkStateQueue(neighbor, frontier);
				boolean inVisited = utilities.checkStateVisited(neighbor, visitedStates);

				if (!inFrontier && !inVisited) {
					INode nextNeighbor = new Node(neighbor, depth + 1);
					nextNeighbor.setPrev(state);
					frontier.add(nextNeighbor);
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
