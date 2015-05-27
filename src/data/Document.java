package data;

import java.util.ArrayList;

import functions.CosineDistance;

public class Document {

	private ArrayList<Word> words;
	private CosineDistance c;

	public Document(ArrayList<Word> w){
		this.words = w;
		this.c = new CosineDistance();
	}
	
	/**
	 * Look up a word 'w' in the vocabulary
	 * @param w
	 * @return The vector associated to this word
	 */
	public double[] getWordRepresentation(String w){
		// for each word we know
		for(Word word: words){
			// if that's the word you're looking for
			if (word.getW().equals(w)){
				// give the vector
				return word.getVec();
			}
		}
		return null;
	}
	
	/**
	 * For a word w, print all the other words and their distance to w.
	 * You shouldn't use this for big documents...
	 * @param w
	 */
	public void printList(String w){
		for(Word word: words){
			// if that's the word you're looking for
			if (word.getW().equals(w)){
				// for all different words
				for(Word second: words){
					if (!second.getW().equals(w)){
						double dist = c.distance(word.getVec(), second.getVec());
						System.out.println(second.getW()+"\t\t"+dist+"");
					}
				}
			}
		}
	}


	/**
	 * Get the closest word to w
	 * @param w
	 * @return The closest word and the cosine distance
	 */
	public Pair<String,Double> getClosest(String w){
		
		double max = Double.MIN_VALUE;
		double dist = 0;
		
		String toReturn = "<???>";
		
		for(Word word: words){
			// if that's the word you're looking for
			if (word.getW().equals(w)){
				for(Word second: words){
					if (!second.getW().equals(w)){
						dist = c.distance(word.getVec(), second.getVec());
						if (dist > max){
							max = dist;
							toReturn = second.getW();
						}
					}
				}
			}
		}
		
		return new Pair<String,Double>(toReturn, max);
	}
	
	/**
	 * Get k closest words to 'w'
	 * @param w
	 * @param k
	 * @return A list formatted as a String
	 */
	// TODO change the output to an ArrayList of Pair<>
	public ArrayList<Pair<String,Double>> getClosest(String w, int k){
		
		double[] max = new double[k];
		for (int i = 0; i < k; i++) max[i] = Double.MIN_VALUE;
		
		String[] toReturn = new String[k];
		for (int i = 0; i < k; i++) toReturn[i] = "<???>";
		
		double dist = 0;
		
		int nbw = 0;

		for(Word word: words){
			// if that's the word you're looking for
			if (word.getW().equals(w)){
				
				// For every word different than the one we're analysing
				for(Word second: words){
					if (!second.getW().equals(w)){
						
						// We calculate the distance
						dist = c.distance(word.getVec(), second.getVec());
						
						// by default, we'll insert them at the end of the array
						int insertAt = k-1-nbw;
						
						// We check if it can be inserted in the array
						for(int i = 0; i < k; i++){
							if (dist > max[i]){
								insertAt = i;
							}
						}
						
						// If it can, we insert it (and move the ones inside)
						if (insertAt > 0){
							for(int d=0; d < insertAt; d++){
								max[d] = max[d+1];
								toReturn[d] = toReturn[d+1];
							}
							
							max[insertAt] = dist;
							toReturn[insertAt] = second.getW();
							nbw++;
						}
					}
				}
				
			}
		}
		
		// Return a simple string
		ArrayList<Pair<String,Double>> al = new ArrayList<Pair<String,Double>>();
		for(int i = k-1; i >= 0; i--){
			al.add(new Pair<String,Double>(toReturn[i],max[i]));
		}
		
		return al;
	}
	
}
