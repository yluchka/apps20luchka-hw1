package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public TempSummaryStatistics(TemperatureSeriesAnalysis seriesAnalysis) {
        avgTemp = seriesAnalysis.average();
        devTemp = seriesAnalysis.deviation();
        minTemp = seriesAnalysis.min();
        maxTemp = seriesAnalysis.max();
    }
}