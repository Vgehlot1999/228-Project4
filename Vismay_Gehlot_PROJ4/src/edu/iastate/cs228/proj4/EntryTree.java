package edu.iastate.cs228.proj4;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 
 * @author Vismay Gehlot
 *
 *
 * An entry tree class.
 *
 *
 */
public class EntryTree<K, V> 
{
 // Dummy root node. 
 // Made public for grading.
 public Node root;
	
 /**
  * 
  * You are allowed to add at most TWO more data fields to 
  * EntryTree class of int type ONLY if you need to.
  *  
  */
 
 
 // All made public for grading.
 public class Node implements EntryNode<K, V> 
 {
  public Node child; // reference to the first child node
  public Node parent; // reference to the parent node
  public Node prev; // reference to the previous sibling
  public Node next; // reference to the next sibling
  public K key; // the key for this node
  public V value; // the value at this node

  public Node(K aKey, V aValue) 
  {
   key = aKey;
   value = aValue;
   child = null;
   parent = null;
   prev = null;
   next = null;
  }

  @Override
  public EntryNode<K, V> parent() 
  {
   // TODO
   return parent;
  }

  @Override
  public EntryNode<K, V> child() 
  {
   // TODO
   return child;
  }

  @Override
  public EntryNode<K, V> next() 
  {
   // TODO
	  return next;
  }

  @Override
  public EntryNode<K, V> prev() 
  {
   // TODO
	  return prev;
  }

  @Override
  public K key() 
  {
   // TODO
	  return key;
  }

  @Override
  public V value() 
  {
   // TODO
	  return value;
  }
 }

 public EntryTree() 
 {
	 root = new Node(null, null);
 }

 /**
  * Returns the value of the entry with a specified key sequence, 
  * or {@code null} if this tree contains no entry with this key 
  * sequence.
  * 
  * This method returns {@code null} if {@code keyarr} is null or 
  * if its length is {@code 0}. If any element of {@code keyarr} 
  * is {@code null}, then the method throws a 
  * {@code NullPointerException}. The method returns the value of 
  * the entry with the key sequence in {@code keyarr} or {@code null} 
  * if this tree contains no entry with this key sequence. An example 
  * is given in provided sample input and output files to illustrate 
  * this method.  
  *
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public V search(K[] keyarr) 
 {
	 if (keyarr.length == 0 || keyarr == null)
	 {
		 return null;
	 }
	 
	 Node currentNode = root;
	 
	 for (int i = 0; i < keyarr.length; i++)
	 {
		 if (currentNode.child == null)
		 {
			 return null;
		 }
		 
		 currentNode = currentNode.child;
		 
		 if (keyarr[i] == null)
		 {
			 throw new NullPointerException();
		 }
		 
		 while (keyarr[i] != currentNode.key)
		 {
			 if (currentNode.next == null)
			 {
				 return null;
			 }
			 currentNode = currentNode.next;
		 }
	 }
	 return currentNode.value;
 }

 /**
  * 
  * This method returns an array of type {@code K[]} with the longest 
  * prefix of the key sequence specified in {@code keyarr} such that 
  * the keys in the prefix label the nodes on the path from the root 
  * to a node. The length of the returned array is the length of the 
  * longest prefix.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null}, 
  * or if its length is {@code 0}. If any element of {@code keyarr} is
  * {@code null}, then the method throws a {@code NullPointerException}. 
  * A prefix of the array {@code keyarr} is a key sequence in the subarray 
  * of {@code keyarr} from index {@code 0} to any index {@code m>=0}, 
  * i.e., greater than or equal to; the corresponding suffix is a key
  * sequence in the subarray of {@code keyarr} from index {@code m+1} to
  * index {@code keyarr.length-1}. The method returns an array of type
  * {@code K[]} with the longest prefix of the key sequence specified in
  * {@code keyarr} such that the keys in the prefix are, respectively,
  * with the nodes on the path from the root to a node. The lngth of the
  * returned array is the length of the longest prefix. Note that if the
  * length of the longest prefix is {@code 0}, then the method returns 
  * {@code null}. This method can be used to select a shorted key sequence
  * for an {@code add} command to create a shorter path of nodes in the
  * tree. An example is given in the attachment to illustrate how this
  * method is used with the {@code #add(K[] keyarr, V aValue)} method.  
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public K[] prefix(K[] keyarr) 
 {
	 if (keyarr.length == 0 || keyarr == null)
	 {
		 return null;
	 }
	 
	 Node currentNode = root;
	 int counter = 0;
	 
	 for (int i = 0; i < keyarr.length; i++)
	 { 
		 if (currentNode.child == null)
		 {
			 return Arrays.copyOf(keyarr, counter);
		 }
		 currentNode = currentNode.child;
		 
		 while (!keyarr[i].equals(currentNode.key))
		 {
			 if (currentNode.next == null)
			 {
				 return Arrays.copyOf(keyarr, counter);
			 }
			 currentNode = currentNode.next;
		 }
		 counter++;
	 }
	 return keyarr;
 }

 /**
  * 
  * This method returns {@code false} if {@code keyarr} is {@code null}, 
  * its length is {@code 0}, or {@code aValue} is {@code null}. If any 
  * element of {@code keyarr} is {@code null}, then the method throws a
  * {@code NullPointerException}.
  * 
  * This method locates the node {@code P} corresponding to the longest 
  * prefix of the key sequence specified in {@code keyarr} such that the 
  * keys in the prefix label the nodes on the path from the root to the node. 
  * If the length of the prefix is equal to the length of {@code keyarr}, 
  * then the method places {@code aValue} at the node {@code P} (in place of 
  * its old value) and returns {@code true}. Otherwise, the method creates a 
  * new path of nodes (starting at a node {@code S}) labelled by the 
  * corresponding suffix for the prefix, connects the prefix path and suffix 
  * path together by making the node {@code S} a child of the node {@code P}, 
  * and returns {@code true}. An example input and output files illustrate 
  * this method's operation.
  *
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @param Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public boolean add(K[] keyarr, V aValue) 
 {
	 if (keyarr.length == 0 || keyarr == null || aValue == null)
	 {
		 return false;
	 }
	 
	 Node startNode = root;
	 
	 for(int i = 0; i < keyarr.length; i++) 
	 {
		 if(keyarr[i] == null) 
		 {
			 throw new NullPointerException();
		 }
		 
		 Node childNode = childWithKey(startNode, keyarr[i]);
		 
		 if(childNode == null) 
		 {
			 childNode = new Node(keyarr[i], null);
			 childNode.parent = startNode;
			 
			 if(startNode.child == null) 
			 {
				 childNode.parent.child = childNode;
			 } 
			 
			 else 
			 {
				 Node sibling = startNode.child;
				 
				 while(sibling.next != null) 
				 {
					 sibling = sibling.next;
				 }
				 
				 sibling.next = childNode;
				 childNode.prev = sibling;
			 }
		 }
		 startNode = childNode;
	 }
	 startNode.value = aValue;
	 return true;
 }
 
 /**
  * Helper method that iterates through the tree using a while loop
  * to check if a given key is in the tree or not. If it's there then
  * the method returns the node that has that key value, otherwise it
  * returns null
  * 
  * @param currentNode	current node being used	 
  * @param key			key that must equal a child key
  * @return	null if the key doesn't match anything, else a child node with the key value 
  */
 private Node childWithKey(Node currentNode, K key)
 {
	 if (currentNode.child == null)
	 {
		 return null;
	 }
	 
	 currentNode = currentNode.child;
	 
	 while (currentNode != null && !currentNode.key.equals(key))
	 {
		 currentNode = currentNode.next;
	 }
	 return currentNode;
 }
 
 

 /**
  * Removes the entry for a key sequence from this tree and returns its value
  * if it is present. Otherwise, it makes no change to the tree and returns
  * {@code null}.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null} or its
  * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
  * the method throws a {@code NullPointerException}. The method returns 
  * {@code null} if the tree contains no entry with the key sequence specified
  * in {@code keyarr}. Otherwise, the method finds the path with the key sequence,
  * saves the value field of the node at the end of the path, sets the value field
  * to {@code null}.
  * 
  * The following rules are used to decide whether the current node and higher
  * nodes on the path need to be removed. The root cannot be removed. Any node 
  * whose value is not {@code null} cannot be removed. Consider a non-root node 
  * whose value is {@code null}. If the node is a leaf node (has no children),
  * then the node is removed. Otherwise, if the node is the parent of a single
  * child and the child node is removed, then the node is removed. Finally, the
  * method returns the saved old value.
  * 
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  *   
  */
 public V remove(K[] keyarr) 
 {
	 return null;
 }
 

 /**
  * 
  * This method prints the tree on the console in the output format 
  * shown in provided sample output file. If the tree has no entry, 
  * then the method just prints out the line for the dummy root node.
  * 
  */
 public void showTree() 
 {
	 printChildren(root, 0);
 }
 
 /**
  * recursive method that prints the children of a node and goes
  * down the given tree until there are no children left. It iterates
  * from the top of the tree down.
  * 
  * @param current 	starting node
  * @param level	the level of the tree
  */
 private void printChildren(Node current, int level)
 {
	 if (current == null)
	 {
		 return;
	 }
	 
	 while (current != null)
	 {
		 while(current != null) {
			 for(int i = 0; i < level; i++) {
				 System.out.print(".");
			 }
			 System.out.println(current.key + " :: " + current.value);
			 printChildren(current.child, level + 1);
			 current = current.next;
		 }
	 }
 }
 
 
 /**
  * 
  * Returns all values in this entry tree together with their keys.
  * The order of outputs would be similar to level order traversal,
  * i.e., first you would get all values together with their keys in
  * first level from left to right, then second level, and so on.
  * If tree has no values then it would return {@code null}.
  *
  * For the example image given in description, the 
  * returned String[][] would look as follows:
  * 
  *  {{"IA","Grow"}, {"ISU","CS228"}}   
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.LinkedList}.
  * 
  *  
  */	
 public String[][] getAll()
 {
	 LinkedList<Node> nodeList = new LinkedList<Node>();
	 LinkedList<Node> nodeQueue = new LinkedList<Node>();
	 Node tempNode;
	 
	 nodeQueue.addLast(root);
	 String[][] returnString = new String[nodeList.size()][2];
	 
	 while (!nodeQueue.isEmpty())
	 {
		 tempNode = nodeQueue.getFirst();
		 nodeQueue.removeFirst();
		 
		 if (tempNode.value != null)
		 {
			 nodeList.add(tempNode);
		 }
		 
		 tempNode = tempNode.child;
		 
		 while (tempNode != null)
		 {
			 nodeQueue.addLast(tempNode);
			 tempNode = tempNode.next;
		 }
	 }
	 
	 String s;
	 
	 for (int i = 0; i < returnString.length; i++)
	 {
		 s = null;
		 
		 tempNode = nodeList.get(i);
		 while (tempNode.key != null)
		 {
			 s = tempNode.toString() + s;
			 tempNode = tempNode.parent;
		 }
		 returnString[i][0] = s;
		 returnString[i][1] = nodeList.get(i).value.toString();
	 }
	 return returnString;	 
 }
}
