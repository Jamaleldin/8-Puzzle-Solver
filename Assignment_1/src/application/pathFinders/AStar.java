package application.pathFinders;


import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Utilities;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class AStar implements IPathFinder {
    private final ArrayList<int[]> visitedStates;
    private final PriorityQueue<Entry> frontier;
    private final IUtilities utilities;
    private int depth;
    private long time;
    private int cost;

    int[] goalState = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    AStar() {
        visitedStates = new ArrayList<>();
        frontier = new PriorityQueue<>();
        utilities = new Utilities();
        depth = 0;
        cost = 0;
    }

    @Override
    public ArrayList<int[]> findPath(int[] initialState, String options) {
        time = new Date().getTime();
        visitedStates.clear();
        frontier.clear();
        frontier.add(new Entry(utilities.getMnhatnCost(initialState,goalState), initialState,0));
        while (!frontier.isEmpty()) {
            Entry state = frontier.poll();
            visitedStates.add(state.getItem());
            depth = state.getDepth();

            if (utilities.goalTest(state.getItem())) {
                time = new Date().getTime() - time;
                ArrayList<int[]> path = utilities.getPath(state);
                cost = path.size();
                return path;
            }

            ArrayList<Entry> neighbors = utilities.getNeighbors(state, options);

            for (Entry neighbor : neighbors) {
                boolean inFrontier = utilities.checkStatePQ(neighbor.getValue(), frontier);
                boolean inVisited = utilities.checkStateVisited(neighbor.getValue(), visitedStates);

                if (!inFrontier && !inVisited) {
                    neighbor.setDepth(depth++);
                    neighbor.setPrev(state);
                    frontier.add(neighbor);
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
    public ArrayList<int[]> nodesExpanded() {
        return visitedStates;
    }

}

