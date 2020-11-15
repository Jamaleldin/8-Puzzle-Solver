package application.pathFinders;

import application.pathFinders.interfaces.INode;
import application.pathFinders.interfaces.IPathFinder;
import application.pathFinders.interfaces.IUtilities;
import application.pathFinders.utilities.Node;
import application.pathFinders.utilities.Utilities;

import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

import static java.lang.Math.*;

public class AStar implements IPathFinder {
    private ArrayList<int[]> visitedStates;
    private PriorityQueue<Entry> frontier;
    private IUtilities utilities;
    private int cost = 0;
    private int depth = 0;

    int[] goalState = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    AStar() {
        visitedStates = new ArrayList<int[]>();
        frontier = new PriorityQueue<>();
        utilities = new Utilities();
    }

    @Override
    public ArrayList<int[]> findPath(int[] initialState, String options) {
        visitedStates.clear();
        frontier.clear();
        frontier.add(new Entry(utilities.getMnhatnCost(initialState,goalState), initialState));
        while (!frontier.isEmpty()) {
            Entry state = frontier.poll();
            visitedStates.add(state.getItem());

            if (utilities.goalTest(state.getItem()))
                return utilities.getPath(state);

            ArrayList<Entry> neighbors = utilities.getNeighbors(state, options);

            for (Entry neighbor : neighbors) {
                boolean inFrontier = utilities.checkStatePQ(neighbor.getValue(), frontier);
                boolean inVisited = utilities.checkStateVisited(neighbor.getValue(), visitedStates);

                if (!inFrontier && !inVisited) {
                    Entry nextNeighbor = neighbor;
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
        return 0;
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

