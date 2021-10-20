package com.hyf.algorithm.dijkstra;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author baB_hyf
 * @date 2021/10/10
 */
public abstract class Dijkstra {

    private final List<String> proceedPoint = new ArrayList<>();

    public void invoke() {
        dijkstra(getGraph(), getCosts(), getParents());
    }

    protected abstract Map<String, Map<String, Integer>> getGraph();

    protected abstract Map<String, Integer> getCosts();

    protected abstract Map<String, String> getParents();

    private void dijkstra(Map<String, Map<String, Integer>> graph,
                         Map<String, Integer> costs,
                         Map<String, String> parents) {

        String point = null;
        // 所有节点都处理完
        while ((point = findLowestCostPoint(costs) /* 接下来要处理的 */) != null) {
            Integer cost = costs.get(point); // 当前几点的cost
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

    private String findLowestCostPoint(Map<String, Integer> costs) {
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

    private void printRoute(Map<String, String> parents) {
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
}
