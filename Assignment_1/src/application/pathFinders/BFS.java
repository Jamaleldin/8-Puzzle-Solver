package application.pathFinders;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.util.*;

public class BFS implements IPathFinder {

    private ArrayList<int[]> visitedStates;
    private Queue<INode> frontier;
    private IUtilities utilities;
    private int depth;
    private long time;
    private int cost;

    public BFS() {
        visitedStates = new ArrayList<>();
        frontier = new LinkedList<>();
        utilities = new Utilities();
        depth = 0;
        cost = 0;
    }

    @Override
    public ArrayList<int[]> findPath(int[] initialState, String options) {

//        tick
        time = new Date().getTime();

//        initiate the frontier with the initial state
        frontier = new LinkedList<>();
        frontier.add(new Node(initialState, 0));
        visitedStates = new ArrayList<>();

        while (!frontier.isEmpty()) {
//            poll from the queue
            INode state = frontier.poll();
            depth = ((Node) state).getDepth();
            visitedStates.add(state.getItem());

//            check for goal
            if (utilities.goalTest(state.getItem())) {
//                that's it
                time = new Date().getTime() - time;
                ArrayList<int[]> path = utilities.getPath(state);
                cost = path.size();
                return path;
            }

            ArrayList<int[]> neighbors = utilities.getNeighbors(state.getItem());

            for (int[] neighbor : neighbors) {
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
    public ArrayList<int[]> nodesExpanded() {
        return visitedStates;
    }
}
