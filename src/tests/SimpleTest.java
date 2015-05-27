package tests;

import java.io.IOException;
import java.util.ArrayList;

import data.Document;
import data.Pair;
import data.Word;

import functions.Parser;

/**
 * Simple test class to run tests from an IDE
 * @author laurentfite
 *
 */
public class SimpleTest {

	public static void main(String args[]){

		// Create a new parser with a vector file
		Parser p = new Parser("data/vectors.txt",false);
		
		// Get an ArrayList of words from the file
		ArrayList<Word> al = new ArrayList<Word>();
		try {
			al = p.parse();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		// Create the document with this ArrayList
		Document doc = new Document(al);
		
		// - - - - - - - - - - - - - - - - - - - - - -
		
		/* = = = GET CLOSEST = = = */
		//System.out.println(doc.getClosest("météo"));
		/*
		System.out.println(doc.getClosest("pluie"));
		System.out.println(doc.getClosest("amour"));
		System.out.println(doc.getClosest("penser"));
		*/
		
		/* = = = GET K CLOSEST = = = */
		for (Pair<String, Double> s: doc.getClosest("météo",10)){
			System.out.println(s);
		}

		/* = = = PRINT LIST = = = */
		// Not to be used with huge files but useful for debugging
		//doc.printList("météo");
		
	}
	
		
}
