package edu.iastate.cs228.proj4.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.iastate.cs228.proj4.EntryTree;

/**
 * 
 * @author Vismay Gehlot
 *
 */
public class testEntryTree {
	private PrintStream prev;
	protected ByteArrayOutputStream output;

	@Before
	public void setUpStreams() {
		output = new ByteArrayOutputStream();
		PrintStream stream = new PrintStream(output);
		prev = System.out;
		System.setOut(stream);
	}

	@After
	public void cleanUpStreams() {
		System.setOut(prev);
	}

	EntryTree<Integer, String> tree = new EntryTree<Integer, String>();

	@Test
	public void testCreate() 
	{
		//test initial node
		assertEquals(tree.root.parent(), null);
		assertEquals(tree.root.child(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
	}

	@Test
	public void testOneChild() {
		Integer[] entry = new Integer[1];
		entry[0] = (Integer) 1;
		tree.add(entry, "child 1");
		
		//test for parent node
		assertEquals(tree.root.parent(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		
		//test for child node
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.child().parent(), tree.root);
		assertEquals(tree.root.child().child(), null);
		assertEquals(tree.root.child().next(), null);
		assertEquals(tree.root.child().prev(), null);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "child 1");
	}

	@Test
	public void testAddSibling() {
		Integer[] entry = new Integer[1];

		entry[0] = 1;
		tree.add(entry, "child 1");

		entry[0] = 2;
		tree.add(entry, "child 2");

		//test parent node
		assertEquals(tree.root.parent(), null);
		assertEquals(tree.root.next(), null);
		assertEquals(tree.root.prev(), null);
		assertEquals(tree.root.key(), null);
		assertEquals(tree.root.value(), null);
		
		//test first child node
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.child().parent(), tree.root);
		assertEquals(tree.root.child().child(), null);
		assertNotEquals(tree.root.child().next(), null);
		assertEquals(tree.root.child().prev(), null);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "child 1");
		
		//test second child node
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.child().next().parent(), tree.root);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "child 1");
		
		
		assertEquals(tree.root.child().next().prev(), tree.root.child());

	}
	
	@Test
	public void testAddAnotherSibling() {
		Integer[] entry1 = new Integer[1];

		entry1[0] = 1;
		tree.add(entry1, "child 1");

		entry1[0] = 2;
		tree.add(entry1, "child 2");
		
		entry1[0] = 3;
		tree.add(entry1, "child 3");
		
		
		assertNotEquals(tree.root.child(), null);
		assertEquals(tree.root.child().parent(), tree.root);
		assertEquals(tree.root.child().child(), null);
		assertNotEquals(tree.root.child().next(), null);
		assertEquals(tree.root.child().prev(), null);
		assertEquals(tree.root.child().key(), (Integer) 1);
		assertEquals(tree.root.child().value(), "child 1");
		
		assertEquals(tree.root.child().next().value(), "child 2");
		assertEquals(tree.root.child().next().key(), (Integer) 2);
		
		assertEquals(tree.root.child().next().next().value(), "child 3");
		assertEquals(tree.root.child().next().next().key(), (Integer) 3);
		assertNotEquals(tree.root.child().next().next().key(), (Integer) 1);
	}

	@Test
	public void testPrint() {
		tree.showTree();
		System.out.flush();
		assertEquals("null :: null\n", output.toString());
	}

	@Test
	public void testPrintAdd() {
		Integer[] entry = new Integer[1];
		entry[0] = (Integer) 1;
		tree.add(entry, "node");
		tree.showTree();
		System.out.flush();
		assertEquals("null :: null\n.1 :: node", output.toString());
	}


	@Test
	public void testPrefix() {
		Integer[] entry1 = { 1, 2, 3 };
		Integer[] entry2 = { 1, 2, 4, 5, 6, 8 };
		Integer[] entryPrefix = { 1, 2 };
		
		Integer[] entry3 = { 1, 2, 5, 6, 789, 56 };
		Integer[] entry4 = { 1, 2, 5, 6 , 44, 55, 6, 2 };
		Integer[] entryPrefix2 = { 1, 2, 5, 6 };
		
		tree.add(entry1, "node");
		tree.add(entry3, "node2");
		
		assertEquals(entryPrefix2, tree.prefix(entry4));
		assertEquals(entryPrefix, tree.prefix(entry2));
		assertNotEquals(entryPrefix, tree.prefix(entry4));
		assertNotEquals(entryPrefix, tree.prefix(entry3));
	}

	@Test
	public void testSearch() {
		Integer[] entry1 = { 1, 2, 4 };
		Integer[] entry2 = { 1, 2 };
		Integer[] entry3 = { 1, 3};
		Integer[] entry4 = { 5, 6, 7 };
		Integer[] entry5 = { 65, 4, 3 };
		Integer[] entry6 = { 3, 6};
		
		tree.add(entry1, "node 1");
		tree.add(entry2, "node 2");
		tree.add(entry3, "node 3");
		tree.add(entry4, "node 4");
		tree.add(entry5, "node 5");
		
		assertEquals("node 1", tree.search(entry1));
		assertEquals("node 2", tree.search(entry2));
		assertNotEquals("node 3", tree.search(entry4));
		assertEquals(null, tree.search(entry6));
	}
}