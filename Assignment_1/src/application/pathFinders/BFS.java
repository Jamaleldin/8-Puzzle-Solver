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

    public BFS() {
        visitedStates = new ArrayList<>();
        frontier = new LinkedList<>();
        utilities = new Utilities();
        depth = 0;
    }

    @Override
    public ArrayList<int[]> findPath(int[] initialState, String options) {

//        tick
        time = new Date().getTime();

//        initiate the frontier with the initial state
        frontier = new LinkedList<>();
        frontier.add(new Node(initialState));
        visitedStates = new ArrayList<>();

        while (!frontier.isEmpty()) {
//            poll from the queue
            INode state = frontier.poll();
            visitedStates.add(state.getItem());

//            check for goal
            if (utilities.goalTest(state.getItem())) {
//                that's it
                time = new Date().getTime() - time;
                return utilities.getPath(state);
            }

            ArrayList<int[]> neighbors = utilities.getNeighbors(state.getItem());

            for (int[] neighbor : neighbors) {
                boolean inFrontier = utilities.checkStateQueue(neighbor, frontier);
                boolean inVisited = utilities.checkStateVisited(neighbor, visitedStates);

                if (!inFrontier && !inVisited) {
                    INode nextNeighbor = new Node(neighbor);
                    nextNeighbor.setPrev(state);
                    frontier.add(nextNeighbor);
                }
            }

        }

        return null;
    }

    @Override
    public int CostOfPath() {
        return 0;
    }

    @Override
    public int timeColapsed() {
        return (int) time;
    }

    @Override
    public int depthOfsearch() {
        return 0;
    }

    @Override
    public ArrayList<int[]> nodesExpanded() {
        return visitedStates;
    }
}
