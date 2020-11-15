package application.pathFinders.interfaces;

public interface INode {
	/**
	 * set the previous node to the given node
	 * 
	 * @param node:
	 *            the previous node
	 */
	public void setPrev(INode node);

	/**
	 * get the previous node
	 * 
	 * @return the previous node or null if there is no previous
	 */
	public INode getPrev();

	/**
	 * get the item that the node holds
	 * 
	 * @return array of integers indicating to the state held by this node
	 */
	public int[] getItem();

}
