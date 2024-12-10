package pt.ipp.isep.dei.esoft.project.domain;

import org.apache.commons.math3.distribution.FDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

import java.text.DecimalFormat;
import java.util.Arrays;


public class MultiLinear {

    private final DecimalFormat df = new DecimalFormat("#.###");
    private final double SIGNIFICANCE_LEVEL = 0.05;
    private OLSMultipleLinearRegression regression;
    private double[][] parameters;
    private double[] dependent;
    private double[][] independent;
    private int n;
    private double[] B;
    private int k;
    private double SE;
    private double ST;
    private double SR;
    private double squareR;
    private double squareRAdjusted;
    private double[] parametersStdErr;



    public MultiLinear(OLSMultipleLinearRegression regression, double[][] parameters){
        this.regression = regression;
        this.n = parameters[0].length;
        this.parameters = parameters;
        this.B = regression.estimateRegressionParameters();
        this.k = B.length;
        this.SE = regression.calculateResidualSumOfSquares();
        this.ST = regression.calculateTotalSumOfSquares();
        this.squareR = regression.calculateRSquared();
        this.squareRAdjusted = regression.calculateAdjustedRSquared();
        this.parametersStdErr = regression.estimateRegressionParametersStandardErrors();

    }

    public double[] getB() {
        return B;
    }

    public MultiLinear() {
        regression = new OLSMultipleLinearRegression();
    }

    public void addValues(double[][] parameters){
        this.parameters = parameters;
        dependent = new double[parameters.length];
        independent = new double[parameters.length][5];
        for(int i =0;i<parameters.length;i++){
            dependent[i]=parameters[i][0];
            independent[i][0] = parameters[i][1];
            independent[i][1] = parameters[i][2];
            independent[i][2] = parameters[i][3];
            independent[i][3] = parameters[i][4];
            independent[i][4] = parameters[i][5];
        }
        regression.newSampleData(dependent,independent);
        n=dependent.length;
        B = regression.estimateRegressionParameters();
        k = 5;
        ST= regression.calculateTotalSumOfSquares();
        SR= regression.calculateResidualSumOfSquares();
        SE= ST-SR;
        squareR = regression.calculateRSquared();
        squareRAdjusted = regression.calculateAdjustedRSquared();
        parametersStdErr = regression.estimateRegressionParametersStandardErrors();
    }

    public String getForecastList(){
        String message ="\nForecast | Sale";

        for (int i = 0; i < parameters.length; i++) {
            String forecast = String.valueOf(this.B[0] + parameters[i][0]*this.B[1] + parameters[i][1]*this.B[2] + parameters[i][2]*this.B[3] + parameters[i][3]*this.B[4] + parameters[i][4]*this.B[5]);
            message = message + "\n" + forecast + " | " + parameters[i][0];
        }
        return message;
    }

    @Override
    public String toString() {
        return "\nMultiLinear{" +
                "\nSIGNIFICANCE_LEVEL=" + SIGNIFICANCE_LEVEL +
                "\nn=" + n +
                "\nB=" + Arrays.toString(B) +
                "\nk=" + k +
                "\nSQE=" + SE +
                "\nSQT=" + ST +
                "\nSQR=" + SR +
                "\nsquareR=" + squareR +
                "\nsquareRAdjusted=" + squareRAdjusted +
                "\nparametersStdErr=" + Arrays.toString(parametersStdErr) +
                "\n\n\n Equation : Y = (" + df.format(this.B[1]) + ")X\u2081 +(" + df.format(this.B[2]) + ")X\u2082 +(" + df.format(this.B[3]) + ")X\u2083 +(" + df.format(this.B[4]) + ")X\u2084 +(" + df.format(this.B[5]) + ")X\u2085 + "+ df.format(this.B[0]);

    }

    public String anovaSingificanceModel(){

        double MQR = SR / this.k;
        double MQE = this.SE/(this.n-(this.k+1));
        double f = MQR/MQE;

        FDistribution fd = new FDistribution(this.k, this.n-(this.k+1));
        Double fSnedecor = fd.inverseCumulativeProbability(SIGNIFICANCE_LEVEL);



        String message = ("\n||-=-=-=- Significance Model With ANOVA -=-=-=-||"
                + "\n MQR:" + df.format(MQR)
                + "\n MQE:" + df.format(MQE)
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
        //System.out.println(message);
        return message;
    }


    public String calculateCoefficientCondifenceIntervals(){

        TDistribution tDistribution = new TDistribution(this.n - (this.k + 1));
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - (SIGNIFICANCE_LEVEL / 2));
        // Calculate the confidence intervals
        double[] lowerBounds = new double[this.B.length];
        double[] upperBounds = new double[this.B.length];
        for (int i = 0; i < this.B.length; i++) {
            lowerBounds[i] = this.B[i] - criticalValue * this.parametersStdErr[i];
            upperBounds[i] = this.B[i] + criticalValue * this.parametersStdErr[i];
        }
        String message = "\n-=-=-=-=-=-= Confidence Intervals (" + (1-SIGNIFICANCE_LEVEL)*100 +  "%) -=-=-=-=-=-=";
        // Add confidence intervals to message
        message = message + "\nIntercept Confidence Interval: [" + df.format(lowerBounds[0]) + ", " + df.format(upperBounds[0]) + "]"
                + "\nParameter : " + df.format(this.B[0]) + "\nStandard Error: " + df.format(this.parametersStdErr[0]);
        for (int i = 1; i < this.B.length; i++) {
            message = message + "\n\nParameter " + (i) + " Confidence Interval: [" + df.format(lowerBounds[i]) + ", " + df.format(upperBounds[i]) + "]"
                    + "\nParameter : " + df.format(this.B[i]) + "\nStandard Error: " + df.format(this.parametersStdErr[i]);
        }
        //System.out.println(message);

        return message;
    }

    public String calculateHypothesisTests(){
        TDistribution tDistribution = new TDistribution(this.n - (this.k + 1));
        double criticalValue = tDistribution.inverseCumulativeProbability(1 - (SIGNIFICANCE_LEVEL / 2));
        String message = "\n\n -=-=-=-=-=- Hypothesis Tests -=-=-=-=-=- \n Test : H0 : B = 0 \n         H1 : B =|= 0";

        double[] tVals= new double[this.B.length];
        for (int i = 0; i < this.B.length; i++) {
            tVals[i] = this.B[i] / this.parametersStdErr[i];
            message = message + "\n----------------\n Parameter " + i + "\nobserved t -> " + df.format(tVals[i]) + "\n tc ->" + df.format(criticalValue);

            if(tVals[i] <= criticalValue){
                message = message + "\nobserved t <= tc, Accepts H0";
            }else {
                message = message + "\nobserved t > tc, Rejects H0";
            }
        }
        return message;
    }

    public String generateAnalysisReport(){
        return this.toString() + this.calculateCoefficientCondifenceIntervals()  + this.calculateHypothesisTests() + this.anovaSingificanceModel() + this.getForecastList();
    }

}
