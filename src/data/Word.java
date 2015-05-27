package data;

public class Word {
	
	String w;
	double[] vec;
	
	public Word(String w, double[] vec){
		this.w = w;
		this.vec = vec;
	}
	
	public String getW() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public double[] getVec() {
		return vec;
	}

	public void setVec(double[] vec) {
		this.vec = vec;
	}

	@Override
	public String toString(){
		return w;
	}
	
	
	
}
