package com.hyf.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 狄克斯特拉算法
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class Dijkstra {

    private static final List<String> proceedPoint = new ArrayList<>();

    public static void main(String[] args) {
        dijkstra(getGraph(), getCosts(), getParents());
    }

    public static void dijkstra(Map<String, Map<String, Integer>> graph,
                                Map<String, Integer> costs,
                                Map<String, String> parents) {

        String point = null;
        // 所有节点都处理完
        while ((point = findLowestCostPoint(costs) /* 接下来要处理的 */) != null) {
            Integer cost = costs.get(point); // 当前节点的cost
            Map<String, Integer> neighborList = graph.get(point); // 当前节点的相邻节点
            for (Map.Entry<String, Integer> entry : neighborList.entrySet()) {
                String neighborPoint = entry.getKey();
                Integer neighborCost = entry.getValue();

                int newCost = cost + neighborCost;
                if (newCost < costs.get(neighborPoint)) { // cost小，更新
                    costs.put(neighborPoint, newCost);
                    parents.put(neighborPoint, point);
                }
            }
            proceedPoint.add(point);
        }

        printRoute(parents);
    }

    public static void printRoute(Map<String, String> parents) {
        String parent = "end";
        StringBuilder sb = new StringBuilder();
        while (parent != null) {
            if (sb.length() != 0) {
                sb.insert(0, " -> ");
            }
            sb.insert(0, parent);
            parent = parents.get(parent);
        }
        System.out.println(sb);
    }

    public static String findLowestCostPoint(Map<String, Integer> costs) {
        int minCost = Integer.MAX_VALUE;
        String minPoint = null;
        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            String p = entry.getKey();
            Integer c = entry.getValue();
            // 没处理过
            if (c < minCost && !proceedPoint.contains(p)) {
                minCost = c;
                minPoint = p;
            }
        }
        return minPoint;
    }

    public static Map<String, Map<String, Integer>> getGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 6);
        map.put("b", 2);
        graph.put("start", map);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("a", 3);
        map2.put("end", 5);
        graph.put("b", map2);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("end", 1);
        graph.put("a", map3);

        graph.put("end", new HashMap<>());
        return graph;
    }

    public static Map<String, Integer> getCosts() {
        Map<String, Integer> cost = new HashMap<>();
        // start cost
        cost.put("a", 6);
        cost.put("b", 2);
        cost.put("end", Integer.MAX_VALUE);
        return cost;
    }

    public static Map<String, String> getParents() {
        Map<String, String> cost = new HashMap<>();
        // start parent
        cost.put("a", "start");
        cost.put("b", "start");
        cost.put("end", null);
        return cost;
    }
}
