package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    static final double DELTA = 1e-8;
    static final double MAGIC_NUM = -273.0;
    private double[] temperatures;
    private int size = 0;
    public TemperatureSeriesAnalysis(double[] temperatures) {
        size = temperatures.length;
        this.temperatures = temperatures;
        for (double temp: temperatures) {
            if (temp < MAGIC_NUM) {
                throw new InputMismatchException();
            }
        }
    }
    public TemperatureSeriesAnalysis() {
        this.temperatures = new double[1];
    }
    private void checkIllegalArgumentException() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        checkIllegalArgumentException();
        double sum = 0;
        for (double temp: temperatures) {
            sum += temp;
        }
        return sum / size;
    }

    public double deviation() {
        checkIllegalArgumentException();
        double dev = 0;
        double avg = average();
        for (double temp: temperatures) {
            dev += (temp - avg) * (temp - avg);
        }
        return Math.sqrt(dev / size);
    }
    private double compare(String sign) {
        double min = temperatures[0];
        double max = temperatures[0];
        for (double temperature : temperatures) {
            min = Math.min(min, temperature);
            max = Math.max(max, temperature);
        }
        if (sign.equals("max")) {
            for (double temperature : temperatures) {
                max = Math.max(max, temperature);
            }
            return max;
        }
        else {
            for (double temperature : temperatures) {
                min = Math.min(min, temperature);
            }
            return min;
        }
    }

    public double min() {
        checkIllegalArgumentException();
        return compare("min");
    }

    public double max() {
        return compare("max");
    }

    public double findTempClosestToValue(double tempValue) {
        checkIllegalArgumentException();
        double closestTemp = temperatures[0];
        double current = Math.abs(temperatures[0] - tempValue);
        for (int i = 0; i < size; i++) {
            if (Math.abs(current - Math.abs(temperatures[i] - tempValue))
                    <= DELTA && temperatures[i] > tempValue) {
                closestTemp = temperatures[i];
            } else if (current > Math.abs(temperatures[i] - tempValue)) {
                current = Math.abs(temperatures[i] - tempValue);
                closestTemp = temperatures[i];
            }
        }
        return closestTemp;
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkIllegalArgumentException();
        int count = 0;
        for (double temp: temperatures) {
            if (temp > tempValue) {
                count++;
            }
        }
        double[] greaterTemps = new double[count];
        for (double temp : temperatures) {
            if (temp > tempValue) {
                greaterTemps[greaterTemps.length - count] = temp;
                count--;
            }
        }
        return greaterTemps;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkIllegalArgumentException();
        int count = 0;
        for (double temp: temperatures) {
            if (temp < tempValue) {
                count++;
            }
        }
        double[] lessTemps = new double[count];
        for (double temp : temperatures) {
            if (temp < tempValue) {
                lessTemps[lessTemps.length - count] = temp;
                count--;
            }
        }
        return lessTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this);
    }

    public double addTemps(double... temps) {
        if (temperatures.length - size >= temps.length) {
            int i = size;
            for (double temp: temps) {
                temperatures[size + i - 1] = temps[size - i];
                i++;
            }
        }
        else {
            double[] resultArray = new double[temperatures.length * 2];
            for (int i = 0; i < temperatures.length + temps.length; i++) {
                if (i < temperatures.length) {
                    resultArray[i] = temperatures[i];
                }
                else {
                    resultArray[i] = temps[i-temperatures.length];
                }
            }
            temperatures = resultArray;
        }
        double sum = 0;
        for (double temp: temperatures) {
            sum += temp;
    }
        for (double temp: temperatures) {
            if (temp < MAGIC_NUM) {
                throw new InputMismatchException();
            }
        }
        return sum;
    }
}
