package application.pathFinders;

import application.pathFinders.interfaces.INode;

public class Entry implements Comparable<Entry>, INode {
    private int key;
    private int[] value;
    private INode prev;
    private int depth;

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public Entry() {
        this.value = new int[9];
        this.key = 0;
        prev = null;
    }

    public Entry(int key, int[] value) {
        this.key = key;
        this.value = value;
        prev = null;
    }

    public Entry(int key, int[] value, int depth) {
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

    public int[] getValue() {
        return value;
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
        return value;
    }
}
