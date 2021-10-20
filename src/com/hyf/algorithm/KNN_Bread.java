package com.hyf.algorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * k最近邻算法-面包-回归
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class KNN_Bread {

    public static void main(String[] args) {
        Sample[] bread = bread(new Sample(4, 1, 0, 0), 4);
        double count = Arrays.stream(bread).mapToDouble(sample -> sample.count).sum() / (double) bread.length;
        System.out.println(count);
    }

    public static Sample[] bread(Sample sample, int k) {
        Sample[] samples = getSamples();

        //  _______________________
        // V (x1-x2)^2 + (y1-y2)^2

        Map<Sample, Double> sampleMap = new HashMap<>();
        for (Sample s : samples) {
            // 平方
            double pow_weather = Math.pow((sample.weather - s.weather), 2); // (sample.weather - s.weather) * (sample.weather - s.weather)
            double pow_holiday = Math.pow((sample.holiday - s.holiday), 2);
            double pow_event = Math.pow((sample.event - s.event), 2);

            // 平方根
            double sqrt = Math.sqrt(pow_weather + pow_holiday + pow_event); // 越小越相似
            sampleMap.put(s, sqrt);
        }

        // 回归

        List<Double> sqrtList = sampleMap.values().stream().sorted().limit(k).collect(Collectors.toList());
        List<Sample> resultList = new ArrayList<>();
        for (Double sq : sqrtList) {
            for (Map.Entry<Sample, Double> entry : sampleMap.entrySet()) {
                Sample key = entry.getKey();
                Double value = entry.getValue();
                if (sq.equals(value)) {
                    resultList.add(key);
                }
            }
        }

        return resultList.toArray(new Sample[0]);
    }

    public static Sample[] getSamples() {
        return new Sample[]{
                new Sample(5, 1, 0, 300),
                new Sample(3, 1, 1, 225),
                new Sample(1, 1, 0, 75),
                new Sample(4, 0, 1, 200),
                new Sample(4, 0, 0, 150),
                new Sample(2, 0, 0, 50),
        };
    }

    public static class Sample {
        public int weather;
        public int holiday;
        public int event;
        public int count;

        public Sample(int weather, int holiday, int event, int count) {
            this.weather = weather;
            this.holiday = holiday;
            this.event = event;
            this.count = count;
        }
    }
}
