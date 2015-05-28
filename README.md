# W2VReader
A Word2Vec vector file reader written in Java.

## How do I use it?

### Test it
1. Use word2vec to **train your model** (output it as a .txt)
<pre>./word2vec -train wiki_fr.xml -output vectors/wiki-small.txt -size 50 -window 3 -binary 0 -min-count 10</pre>
2. Run the `distance.jar` script
<pre>java -jar distance.jar vectors/wiki-small.txt</pre>
3. **Type a word** to see the closest word or `word,k` to see the k closest
<pre>Enter a word after the '$', type EXIT to quit.
Type `word,k` to see the k closest words
$ manger
boire	(0.9227190060515705)
$ penser,3
supposer	(0.9250960642638857)
croire	(0.9132860538727967)
dire	(0.8389610728779925)
</pre>

### Integrate it to your application

The goal of this program is to be integrated in another Java application.
By adding w2vreader_lib.jar to your classpath, you will be able to use the library to fetch close words to the one you request.
For more information, please see the documentation on the website.
Check out this simple example:

<pre><code>
public class Main {
	public static void main(String args[]){
		// Create a new parser with a vector file
		Parser p = new Parser("vectors.txt",false);
		
		// Get an ArrayList of words from the file
		ArrayList<Word> al = new ArrayList<Word>();
		try {
			al = p.parse();
		} catch (IOException e) {	
			e.printStackTrace();
		}
		// Create the document with this ArrayList
		Document doc = new Document(al);
		
		/* = = = GET K CLOSEST = = = */
		for (Pair<String, Double> s: doc.getClosest("météo",3)){
			System.out.println(s);
		}
	}
}
</code></pre>

## How does it work?

1. The program reads the input file (see below for a small example)
<pre>
5 20
météo 0.007724 -0.019013 -0.020479 0.024848 0.019006 0.015450 -0.003020 0.020078 -0.002475 0.003674 -0.000746 -0.014916 0.006561 -0.006687 -0.019127 0.023771 0.021896 -0.005476 -0.001131 0.002546
température -0.018188 -0.020036 0.022774 0.000315 -0.012912 -0.015211 -0.015382 0.008485 0.001007 0.006655 -0.021068 -0.019039 -0.000650 0.005718 0.012749 -0.015850 0.020398 0.004635 0.005598 -0.003042
moche 0.014932 -0.011439 -0.010487 0.010792 -0.003766 0.005154 0.009023 -0.020443 -0.009915 0.014568 0.021159 0.019660 -0.015234 -0.010538 -0.004546 0.010007 -0.018942 0.014989 0.013939 -0.007995
pluie -0.007758 -0.011192 0.021144 0.019559 -0.005899 0.021075 0.024101 0.009077 0.024915 -0.015556 -0.007658 -0.010537 -0.014536 0.014074 0.007896 0.002125 -0.010968 0.007619 0.015297 0.000968
</pre>
2. The data is stored as a `data.Document`
3. Then you can use functions such as `getClosest`

## Comparing to Word2Vec's distance

Word2Vec provides a distance program written in C.
The output is not exactly the same. I will be working on a small script to evaluate the difference and will continue working on my program to make it better.


## Features to be implemented

- A binary file parser
- Maybe a wrapper for running W2V directly in Java
