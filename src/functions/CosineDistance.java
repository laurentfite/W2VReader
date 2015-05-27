package functions;

public class CosineDistance {
	public CosineDistance(){
		
	}
	
	public double distance(double[] v1, double[] v2){
		
		double dotProduct = 0.0;
        double magnitude1 = 0.0;
        double magnitude2 = 0.0;
        double cosineSimilarity = 0.0;
 
        for (int i = 0; i < v1.length; i++) //v1 and v2 must be of same length
        {
            dotProduct += v1[i] * v2[i];  //a.b
            magnitude1 += Math.pow(v1[i], 2);  //(a^2)
            magnitude2 += Math.pow(v2[i], 2); //(b^2)
        }
 
        magnitude1 = Math.sqrt(magnitude1);//sqrt(a^2)
        magnitude2 = Math.sqrt(magnitude2);//sqrt(b^2)
 
        if (magnitude1 != 0.0 | magnitude2 != 0.0)
        {
            cosineSimilarity = dotProduct / (magnitude1 * magnitude2);
        } 
        else
        {
            return 0.0;
        }
        return cosineSimilarity;
		
	}
}
