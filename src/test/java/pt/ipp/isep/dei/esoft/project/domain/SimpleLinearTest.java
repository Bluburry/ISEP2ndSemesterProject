package pt.ipp.isep.dei.esoft.project.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleLinearTest {
    @Test
    public void testGenerateAnalysisReport() {
        double[][] parameters = {
                {1.0, 2.0},
                {2.0, 3.0},
                {3.0, 4.0},
                {4.0, 5.0},
                {5.0, 6.0}
        };

        SimpleLinear simpleLinear = new SimpleLinear();
        simpleLinear.addValues(parameters);

        String expectedReport = "-----RegressionModel-----\n" +
                "Sxx= 10\n" +
                "Syy= 10\n" +
                "Sxy= 10\n" +
                "SE= 0\n" +
                "SR= 10\n" +
                "ST= 10\n" +
                "avgX= 3\n" +
                "avgY= 4\n" +
                "slope= 1\n" +
                "intercept= 1\n" +
                "R^2 = 1\n" +
                "R =1\n" +
                "\n" +
                "||-=-=-=- Intercept Confidence Interval -=-=-=-||\n" +
                " Intercept Standard Error: 0\n" +
                " Intercept Confidence Interval (95.0) -> ] 1; 1[\n" +
                "\n" +
                "||-=-=-=- Slope Confidence Interval -=-=-=-||\n" +
                " Slope Standard Error: 0\n" +
                " Slope Confidence Interval (95.0) -> ] 1; 1[\n" +
                "\n" +
                "||-=-=-=- Intercept Hypothesis Test -=-=-=-||\n" +
                " s :NaN\n" +
                "-=-=-=--=-=-=-\n" +
                "H0 : a = a0\n" +
                "H1 : a != a0\n" +
                "-=-=-=--=-=-=-\n" +
                " t = ∞\n" +
                " tc =3,182\n" +
                "t > tc \n" +
                " -> H0 rejected\n" +
                "\n" +
                "||-=-=-=- Slope Hypothesis Test -=-=-=-||\n" +
                " s :NaN\n" +
                "-=-=-=--=-=-=-\n" +
                "H0 : b = b0\n" +
                "H1 : b != b0\n" +
                "-=-=-=--=-=-=-\n" +
                " t = ∞\n" +
                " tc =3,182\n" +
                "t > tc \n" +
                " -> H0 rejected\n" +
                "||-=-=-=- Significance Model With ANOVA -=-=-=-||\n" +
                " MSR:10\n" +
                " MSE:0\n" +
                " F0 :∞\n" +
                " F de Snedecore : 0,005\n" +
                "-=-=-=--=-=-=-\n" +
                "H0 : b = b0\n" +
                "H1 : b != b0\n" +
                "-=-=-=--=-=-=-\n" +
                "F0 > F de Snedecor\n" +
                "H0 is rejected -> regression model is significant\n" +
                "Forecast | Sale\n" +
                "2 | 2\n" +
                "3 | 3\n" +
                "4 | 4\n" +
                "5 | 5\n" +
                "6 | 6";

        assertEquals(expectedReport, simpleLinear.generateAnalysisReport());
    }
}