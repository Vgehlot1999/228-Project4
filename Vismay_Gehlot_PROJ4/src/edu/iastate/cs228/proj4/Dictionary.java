package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author Vismay Gehlot
 * 
 * 
 * An application class that uses EntryTree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the EntryTree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the EntryTree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the EntryTree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the EntryTree class on each command line 
 * specifies that the public method should be called from the EntryTree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of EntryTree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */
public class Dictionary 
{
	public static void main(String[] args) 
	{
		File f = new File(args[0]);
		
		try
		{
			Scanner s = new Scanner(f);
			Scanner temp = null;
			String currentLine;
			String thisLine;
			EntryTree<Character, String> tree = new EntryTree<Character, String>();
			String[] commandList = new String[] {"search", "prefix", "add", "remove", "showTree"};
			int x = 0;
			
			while (s.hasNextLine())
			{
				currentLine = s.nextLine();
				temp = new Scanner(currentLine);
				thisLine = temp.next();
				
				for (int i = 0; i < commandList.length; i++)
				{
					if (thisLine.equals(commandList[i]))
					{
						x = i;
					}
				}
				
				System.out.println("Commands: " + commandList[x]);
				System.out.println("Result from  a " + commandList[x] + ":");
				
				//search method
				if (x == 0)
				{
					System.out.println(tree.search(toCharArray(temp.next())));
				}
				
				//prefix method
				if (x == 1)
				{
					System.out.println(tree.prefix(toCharArray(temp.next())));
				}
				
				//add method
				if (x == 2)
				{
					System.out.println(tree.add(toCharArray(temp.next()), temp.next()));
				}
				
				//remove method
				if (x == 3)
				{
					System.out.println(tree.remove(toCharArray(temp.next())));
				}
				
				//showTree method
				if (x == 4)
				{
					tree.showTree();
				}
				System.out.println("");
			}
			s.close();
			temp.close();
		}
		catch (FileNotFoundException e)
		{
		}
	}
	
	private static Character[] toCharArray(String s)
	{
		Character[] c = new Character[s.length()];
		for (int i = 0; i < s.length(); i++)
		{
			c[i] = s.charAt(i);
		}
		return c;
	}
}
