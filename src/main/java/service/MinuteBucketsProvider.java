package service;

import model.SalesAmountBucket;

import java.util.concurrent.ConcurrentHashMap;

public class MinuteBucketsProvider {

    public static ConcurrentHashMap<Integer, SalesAmountBucket> createBuckets() {
        ConcurrentHashMap<Integer, SalesAmountBucket> minuteBuckets = new ConcurrentHashMap<>();
        for (int second = 0; second < 60; second++) {
            minuteBuckets.put(second, new SalesAmountBucket());
        }
        return minuteBuckets;
    }

}
