package application.pathFinders;

import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Entry;
import application.pathFinders.utilities.Utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar implements IPathFinder {
	private final Set<List<Integer>> visitedStates;
	private final PriorityQueue<Entry> frontier;
	private final IUtilities utilities;
	private int depth;
	private int maxDepth;
	private long time;
	private int cost;

	List<Integer> goalState = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);

	AStar() {
		visitedStates = new HashSet<>();
		frontier = new PriorityQueue<>();
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

		if (options.equals("mnhatn"))
			frontier.add(new Entry(utilities.getMnhatnCost(initialState, goalState), initialState, 0));
		else
			frontier.add(new Entry(utilities.getEucCost(initialState, goalState), initialState, 0));

		while (!frontier.isEmpty()) {
			Entry state = frontier.poll();
			if (!utilities.checkStateVisited(state.getItem(), visitedStates)) {
				visitedStates.add(state.getItem());
				depth = state.getDepth();
				maxDepth = Math.max(depth, maxDepth);

				if (utilities.goalTest(state.getItem())) {
					time = new Date().getTime() - time;
					ArrayList<List<Integer>> path = utilities.getPath(state);
					cost = path.size();
					return path;
				}

				ArrayList<Entry> neighbors = utilities.getNeighbors(state, options);

				for (Entry neighbor : neighbors) {
					boolean inVisited = utilities.checkStateVisited(neighbor.getItem(), visitedStates);

					if (!inVisited) {
						neighbor.setDepth(depth++);
						neighbor.setPrev(state);
						frontier.add(neighbor);
					}
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
		return ((int) time);
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
