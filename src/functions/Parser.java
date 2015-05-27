package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import data.Word;

public class Parser {
	
	private String infile;
	private boolean b;
	
	public Parser(String file, boolean binary){
		this.infile = file;
		this.b = binary;
	}
	
	/**
	 * Reads a vector file as formatted by Word2Vec
	 * @return An ArrayList of Word (pair of term - vector)
	 * @throws IOException
	 */
	private ArrayList<Word> parseText() throws IOException{
		
		// Create an arraylist of documents that will be returned
		ArrayList<Word> doc = new ArrayList<Word>();
		
		// BufferedReader to read the file
		BufferedReader br = new BufferedReader(new FileReader(this.infile));
		String line;
		
		// Split the first line with space
		String[] parts = br.readLine().split(" ");
		// The size of the vectors is the second part
		int size_vec = Integer.parseInt(parts[1]);
		
		// Read the file, line by line, and split with space
		while ((line = br.readLine()) != null) {
			parts = line.split(" ");
			
			double[] vec = new double[size_vec];
			// Read all the values for the vector
			for(int i = 0; i < size_vec ; i++){
				vec[i] = Double.parseDouble(parts[i+1]);
			}
			
			// Create a new Word with the vocable and the vector
			doc.add(new Word(parts[0], vec));
		}
		
		br.close();
		
		return doc;
	}
	
	// TODO
	private ArrayList<Word> parseBin() throws IOException{
		
		// Create an arraylist of documents that will be returned
		ArrayList<Word> doc = new ArrayList<Word>();
		
		// BufferedReader to read the file
		BufferedReader br = new BufferedReader(new FileReader(this.infile));
		String line;
		
		// Split the first line with space
		String[] parts = br.readLine().split(" ");
		// The size of the vectors is the second part
		int size_vec = Integer.parseInt(parts[1]);
		
		// Read the file, line by line, and split with space
		while ((line = br.readLine()) != null) {
			parts = line.split(" ");
			
			double[] vec = new double[size_vec];
			// Read all the values for the vector
			for(int i = 0; i < size_vec ; i++){
				vec[i] = Double.parseDouble(parts[i+1]);
			}
			
			// Create a new Word with the vocable and the vector
			doc.add(new Word(parts[0], vec));
		}
		
		br.close();
		
		return doc;
	}
	
	/**
	 * Parses the file, depending on the format
	 * @return
	 * @throws IOException
	 */
	public ArrayList<Word> parse() throws IOException{
		if (this.b) return parseBin();
		else return parseText();
	}
	
}
