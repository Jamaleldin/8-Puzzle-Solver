package application.pathFinders.utilities;

import java.util.ArrayList;
import java.util.List;

import application.pathFinders.interfaces.INode;

public class Node implements INode {
	private List<Integer> arr;
	private INode prev;
	private int depth;

	public Node() {
		arr = new ArrayList<Integer>();
		prev = null;
	}

	public Node(List<Integer> state) {
		arr = state;
		prev = null;
	}

	public Node(List<Integer> state, int depth) {
		arr = state;
		this.depth = depth;
		prev = null;
	}

	@Override
	public void setPrev(INode node) {
		prev = node;
	}

	@Override
	public INode getPrev() {
		return prev;
	}

	@Override
	public List<Integer> getItem() {
		return arr;
	}

	@Override
	public int getDepth() {
		return depth;
	}

	@Override
	public void setDepth(int depth) {
		this.depth = depth;
	}

}
