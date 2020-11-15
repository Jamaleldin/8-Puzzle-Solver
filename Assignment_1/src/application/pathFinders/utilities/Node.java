package application.pathFinders.utilities;

import application.pathFinders.interfaces.INode;

public class Node implements INode {
	private int[] arr;
	private INode prev;
	private int depth;

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Node() {
		arr = new int[9];
		prev = null;
	}

	public Node(int[] state) {
		arr = state;
		prev = null;
	}

	public Node(int[] state, int depth) {
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
	public int[] getItem() {
		return arr;
	}

}
