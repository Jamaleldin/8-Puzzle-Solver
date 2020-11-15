package application.pathFinders.utilities;

import application.pathFinders.interfaces.INode;

public class Node implements INode {
	private int[] arr;
	private INode prev;

	public Node() {
		arr = new int[9];
		prev = null;
	}

	public Node(int[] state) {
		arr = state;
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
