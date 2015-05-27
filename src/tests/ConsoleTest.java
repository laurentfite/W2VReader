package tests;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;

import data.Document;
import data.Pair;
import data.Word;

import functions.Parser;

public class ConsoleTest {

	public static void main(String args[]){
		
		if(args.length < 1){
			System.out.println("You need to give the vector file as parameter.");
			System.exit(1);
		}
		
		Parser p = new Parser(args[0],false);
		ArrayList<Word> al = new ArrayList<Word>();
		
		try {
			al = p.parse();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		
		Document doc = new Document(al);
		Console console = System.console();
		
		System.out.println("Enter a word after the '$', type EXIT to quit.");
		System.out.println("Type `word,k` to see the k closest words");
		System.out.println("==============================================");
		
		int k = 0;
		while(true){
			k=0;
			String input = console.readLine("$ ");
			if(input.equals("EXIT")){
				System.exit(0);
			}
			if (input.contains(",")){
				String parts[] = input.split(",");
				input = parts[0];
				k = Integer.parseInt(parts[1]);
			}
			if (k == 0)
				System.out.println(doc.getClosest(input));
			else{
				for (Pair<String, Double> s: doc.getClosest(input,k)){
					System.out.println(s);
				}
			}
			
			System.out.println("");
		}
		
	}
	
		
}
