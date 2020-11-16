package application.pathFinders.utilities;

import java.util.ArrayList;
import java.util.List;

import application.pathFinders.interfaces.INode;

public class Entry implements Comparable<Entry>, INode {
	private int key;
	private List<Integer> value;
	private INode prev;
	private int depth;

	public Entry() {
		this.value = new ArrayList<Integer>();
		this.key = 0;
		prev = null;
	}

	public Entry(int key, List<Integer> value) {
		this.key = key;
		this.value = value;
		prev = null;
	}

	public Entry(int key, List<Integer> value, int depth) {
		this.key = key;
		this.value = value;
		this.depth = depth;
		prev = null;
	}

	// getters

	@Override
	public int compareTo(Entry other) {
		if (this.getKey() < (other.getKey()))
			return 0;
		return 1;
	}

	public int getKey() {
		return key;
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
		return value;
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
