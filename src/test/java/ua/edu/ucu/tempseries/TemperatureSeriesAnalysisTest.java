package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysisTest {
    private TemperatureSeriesAnalysis myArray;
    private TemperatureSeriesAnalysis emptyArray;

    @Before
    public void setUp() {
        myArray = new TemperatureSeriesAnalysis(new double[]{-1.0, 1.0, 2.0, 3.0, 4.0, 5.0});
        emptyArray = new TemperatureSeriesAnalysis();
    }
    @Test(expected = InputMismatchException.class)
    public void testInputMismatch(){
        TemperatureSeriesAnalysis arr = new TemperatureSeriesAnalysis(new double[]{1.0, 2.0, -500});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageEmpty() {
        emptyArray.average();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinEmpty() {
        emptyArray.min();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationEmpty() {
        emptyArray.deviation();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueEmpty() {
        emptyArray.findTempClosestToValue(-5);
    }

    @Test(expected = InputMismatchException.class)
    public void testMissMatch() {
        myArray.addTemps(-300.0);
    }

    @Test
    public void testAverageWithMyArray() {
        double expResult = 2.3333333333333335;
        double actualResult = myArray.average();
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testMaxWithMyArray() {
        double expResult = 5.0;
        double actualResult = myArray.max();
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testDeviationWithMyArray() {
        double expResult = 1.9720265943665387;
        double actualResult = myArray.deviation();
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testFindTempClosestToZeroWithMyArray() {
        double expResult = 1.0;
        double actualResult = myArray.findTempClosestToZero();
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testFindTempClosestToValueWithCommonArray() {
        double expResult = -1.0;
        double actualResult = myArray.findTempClosestToValue(-5.0);
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testAddTempsWithMyArray() {
        double expResult = 27.0;
        double actualResult = myArray.addTemps(6.0, 7.0);
        assertEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testMyArrayWithSummaryStatistics() {
        double expAvg = 2.3333333333333335;
        double actualAvg = myArray.summaryStatistics().getAvgTemp();
        assertEquals(expAvg, actualAvg, 1e-8);

        double expDev = 1.9720265943665387;
        double actualDev = myArray.summaryStatistics().getDevTemp();
        assertEquals(expDev, actualDev, 1e-8);

        double expMin = -1.0;
        double actualMin = myArray.summaryStatistics().getMinTemp();
        assertEquals(expMin, actualMin, 1e-8);

        double expMax = 5.0;
        double actualMax = myArray.summaryStatistics().getMaxTemp();
        assertEquals(expMax, actualMax, 1e-8);
    }
    @Test
    public void testFindTempsGreaterThanWithMyArray() {
        double[] expResult = {3.0, 4.0, 5.0};
        double[] actualResult = myArray.findTempsGreaterThen(2.0);
        assertArrayEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testFindTempsLessThanWithMyArray() {
        double[] expResult = {-1.0};
        double[] actualResult = myArray.findTempsLessThen(0.0);
        assertArrayEquals(expResult, actualResult, 1e-8);
    }

    @Test
    public void testAddTemps() {
        myArray.addTemps(6.0);
        double expResult = 22.0;
        double actualResult = myArray.addTemps(2.0);
        assertEquals(expResult, actualResult, 1e-8);
    }
}
