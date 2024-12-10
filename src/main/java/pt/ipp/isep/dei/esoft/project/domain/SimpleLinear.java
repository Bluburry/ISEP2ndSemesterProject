package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import java.text.DecimalFormat;

public class SimpleLinear {

    private final DecimalFormat df = new DecimalFormat("#.###");
    private final double SIGNIFICANCE_LEVEL = 0.05;

    private SimpleRegression regression;

    private SummaryStatistics stats;

    private double[][] parameters;

    private int n;
    private double Sxx;

    private double Syy;

    private double Sxy;

    private double SE;

    private double SR;

    private double avgX;

    private double avgY;

    private double slope;

    private double intercept;

    private double squareR;


    public SimpleLinear(SimpleRegression regression, int n, double Sxx, double Syy, double Sxy, double SE, double SR, double avgX, double avgY, double squareR, double slope, double intercept, double[][] parameters) {
        this.regression=regression;
        this.n=n;
        this.Sxx = Sxx;
        this.Syy = Syy;
        this.Sxy = Sxy;
        this.SE = SE;
        this.SR = SR;
        this.avgX = avgX;
        this.avgY = avgY;
        this.squareR = squareR;
        this.slope = slope;
        this.intercept = intercept;
        this.parameters = parameters;
    }

    public SimpleLinear() {
        regression = new SimpleRegression();
        stats = new SummaryStatistics();
    }

    public double getSE() {
        return SE;
    }

    private double calculateAverage(double[][] array, int columnIndex) {
        double total=0;

        for (int i = 0; i < array.length; i++) {
            total = array[i][columnIndex] + total;
        }

        return total/ array.length;
    }
    public void addValues(double[][] parameters){
        this.parameters = parameters;
        regression.addData(parameters);
        for (int i=0;i< parameters.length;i++){
            stats.addValue(parameters[i][1]);
        }

        n = parameters.length;
        avgX = calculateAverage(parameters, 0);
        avgY = calculateAverage(parameters,1);
        Sxx = regression.getXSumSquares();
        Syy = regression.getTotalSumSquares();
        Sxy = regression.getSumOfCrossProducts();
        SE = Syy-Math.pow((Sxy/Sxx),2)*Sxx;
        SR = stats.getSecondMoment();
        squareR = regression.getRSquare();
        slope = regression.getSlope();
        intercept = regression.getIntercept();

    }

    @Override
    public String toString() {
        return "-----RegressionModel-----" +
                "\nSxx= " + df.format(Sxx) +
                "\nSyy= " + df.format(Syy) +
                "\nSxy= " + df.format(Sxy) +
                "\nSE= " + df.format(SE) +
                "\nSR= " + df.format(SR) +
                "\nST= " +df.format(SR+SE) +
                "\navgX= " + df.format(avgX) +
                "\navgY= " + df.format(avgY) +
                "\nslope= " + df.format(slope) +
                "\nintercept= " + df.format(intercept) +
                "\nR^2 = " + df.format(squareR) +
                "\nR =" + df.format(Math.sqrt(squareR));
    }
    public String anovaSingificanceModel(){


        double SR = this.SR;
        double SE = this.SE;

        double MSR = SR;
        double MSE = SE/(this.n-2);
        double f = MSR/MSE;

        FDistribution fd = new FDistribution(1, this.n-2);
        Double fSnedecor = fd.inverseCumulativeProbability(SIGNIFICANCE_LEVEL);



        String message = ("\n||-=-=-=- Significance Model With ANOVA -=-=-=-||"
                + "\n MSR:" + df.format(MSR)
                + "\n MSE:" + df.format(MSE)
                + "\n F0 :" + df.format(f)
                + "\n F de Snedecore : " + df.format(fSnedecor)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : b = b0"
                + "\nH1 : b != b0"
                + "\n-=-=-=--=-=-=-");

        if(f>fSnedecor){
            message = message + "\nF0 > F de Snedecor\nH0 is rejected -> regression model is significant";
        }else{
            message = message + "\nF0 < F de Snedecor\nH0 is accepted -> regression model is not significant";
        }

        return message;
    }
    public String calculateInterceptConfidenceInterval(){
        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - SIGNIFICANCE_LEVEL / 2);
        double marginOfError = criticalValue * this.regression.getInterceptStdErr();
        double confidenceIntervalMin = this.regression.getIntercept() - marginOfError;
        double confidenceIntervalMax = this.regression.getIntercept() + marginOfError;


        return "\n\n||-=-=-=- Intercept Confidence Interval -=-=-=-||"+
                "\n Intercept Standard Error: " + df.format(this.regression.getInterceptStdErr()) +
                "\n Intercept Confidence Interval (" + ((1-SIGNIFICANCE_LEVEL)*100) + ") -> ] " + df.format(confidenceIntervalMin) + "; " + df.format(confidenceIntervalMax) + "[";
    }

    public String calculateSlopeConfidenceInterval(){

        TDistribution tDistribution = new TDistribution(this.n - 2);
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - SIGNIFICANCE_LEVEL / 2);
        double marginOfError = criticalValue * this.regression.getSlopeStdErr();
        double confidenceIntervalMin = this.regression.getSlope() - marginOfError;
        double confidenceIntervalMax = this.regression.getSlope() + marginOfError;


        return "\n\n||-=-=-=- Slope Confidence Interval -=-=-=-||"+
                "\n Slope Standard Error: " + df.format(this.regression.getSlopeStdErr()) +
                "\n Slope Confidence Interval (" + ((1-SIGNIFICANCE_LEVEL)*100) + ") -> ] " + df.format(confidenceIntervalMin) + "; " + df.format(confidenceIntervalMax) + "[";
    }

    public String calculateInterceptHyopthesisTest(){

        double t = this.intercept / this.regression.getInterceptStdErr();

        TDistribution tDist = new TDistribution(this.n-2);
        double tc = tDist.inverseCumulativeProbability(1 - 0.05/2);

        double s = Math.sqrt(this.SE / this.n -2);

        // Perform hypothesis test
        String message = ("\n\n||-=-=-=- Intercept Hypothesis Test -=-=-=-||"
                + "\n s :" + df.format(s)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : a = a0"
                + "\nH1 : a != a0"
                + "\n-=-=-=--=-=-=-")
                + "\n t = " + df.format(t)
                + "\n tc =" + df.format(tc);

        if (t <= tc) {
            message = message + ("\nt <= tc \n -> H0 accepted");
        } else {
            message = message + ("\nt > tc \n -> H0 rejected");
        }


        return message;
    }

    public String getForecastList(){
        String message ="\nForecast | Sale";

        for (int i = 0; i < parameters.length; i++) {
            String forecast = String.valueOf(df.format(regression.predict(parameters[i][0])));
            message = message + "\n" + forecast + " | " + df.format(parameters[i][1]);
        }
        return message;
    }


    public String calculateSlopeHyopthesisTest(){

        double t = this.slope / this.regression.getSlopeStdErr();
        double s = Math.sqrt(this.SE / this.n -2);
        TDistribution tDist = new TDistribution(this.n-2);

        double tc = tDist.inverseCumulativeProbability(1 - 0.05/2);


        String message = ("\n\n||-=-=-=- Slope Hypothesis Test -=-=-=-||"
                + "\n s :" + df.format(s)
                + "\n-=-=-=--=-=-=-"
                + "\nH0 : b = b0"
                + "\nH1 : b != b0"
                + "\n-=-=-=--=-=-=-")
                + "\n t = " + df.format(t)
                + "\n tc =" + df.format(tc);

        if (t <= tc) {
            message = message + ("\nt <= tc \n -> H0 accepted");
        } else {
            message = message + ("\nt > tc \n -> H0 rejected");
        }


        return message;
    }
    public String generateAnalysisReport(){
        return this.toString() + this.calculateInterceptConfidenceInterval() + this.calculateSlopeConfidenceInterval() + this.calculateInterceptHyopthesisTest() + this.calculateSlopeHyopthesisTest() + this.anovaSingificanceModel() + this.getForecastList();
    }
}
