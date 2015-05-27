package tests;

import java.io.IOException;
import java.util.ArrayList;

import data.Document;
import data.Pair;
import data.Word;

import functions.Parser;

public class SimpleTest {

	public static void main(String args[]){

		Parser p = new Parser("data/vectors.txt",false);
		ArrayList<Word> al = new ArrayList<Word>();
		
		try {
			al = p.parse();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		Document doc = new Document(al);
		
		//System.out.println(doc.getClosest("météo"));
		/*
		System.out.println(doc.getClosest("pluie"));
		System.out.println(doc.getClosest("amour"));
		System.out.println(doc.getClosest("penser"));
		*/
		
		for (Pair<String, Double> s: doc.getClosest("météo",10)){
			System.out.println(s);
		}
		/*
		System.out.println(doc.getClosest("penser",3));
		System.out.println(doc.getClosest("pluie",3));
		*/
		//doc.printList("météo");
		
	}
	
		
}
