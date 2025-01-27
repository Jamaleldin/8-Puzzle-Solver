package application.pathFinders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import application.pathFinders.interfaces.IPathFinder;

public class test {
	public static void main(String[] args) {

		IPathFinder pathFinder = new AStar();
		List<Integer> initialState = Arrays.asList(1, 8, 2, 0, 4, 3, 7, 6, 5);

		ArrayList<List<Integer>> path = new ArrayList<>();
		path = pathFinder.findPath(initialState, "mnhatn");
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
