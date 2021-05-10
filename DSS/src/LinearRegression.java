import org.apache.commons.math3.stat.regression.SimpleRegression;

public class LinearRegression {
	
	private double[] y;
	private SimpleRegression sr= new SimpleRegression();
	
	LinearRegression(double[] y){
		for(int i=0;i<y.length;i++) {
			this.sr.addData(i, y[i]);
		}
		double[] yc = new double[y.length];
		for(int i=0;i<y.length;i++) {
			yc[i] = this.sr.predict(i);
		}
	}
	
	public SimpleRegression getSR() {
		return this.sr;
	}
	
	public double[] getY() {
		return this.y;
	}
	
	public double getSlope() {
		return this.sr.getSlope();
	}
	
	public double getPredictY(double x) {
		double slope = this.sr.getSlope();
		double x1 = 0;
		double y1 = this.sr.getIntercept();
		double x2 = x;
		double y2 = slope*x2+y1;
		
		return y2;
	}

}
