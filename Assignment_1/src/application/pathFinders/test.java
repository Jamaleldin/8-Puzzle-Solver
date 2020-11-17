package application.pathFinders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.pathFinders.interfaces.IPathFinder;

public class test {
	public static void main(String[] args) {

		IPathFinder pathFinder = new BFS();
		List<Integer> initialState = Arrays.asList(5, 1, 2, 3, 4, 8, 6, 7, 0);

		ArrayList<List<Integer>> path = new ArrayList<>();
		path = pathFinder.findPath(initialState, null);
		for (int i = 0; i < path.size(); i++) {
			System.out.println("State " + (i + 1) + ": " + path.get(i));
		}
		System.out.println("");
		System.out.println("No. Moves: " + pathFinder.CostOfPath() + " moves");
		System.out.println("No. Nodes Expanded: " + pathFinder.nodesExpanded().size());
		System.out.println("Time: " + pathFinder.timeColapsed() + " milliseconds");
		System.out.println("depth of goal: " + pathFinder.depthOfsearch());
		System.out.println("Max depth reached: " + pathFinder.maxDepthReached());


	}
}
