package com.hyf.algorithm.dijkstra;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baB_hyf
 * @date 2021/10/10
 */
public class A extends Dijkstra {

    public static void main(String[] args) {
        new A().invoke();
    }

    @Override
    protected Map<String, Map<String, Integer>> getGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 5);
        map.put("b", 2);
        graph.put("start", map);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("c", 4);
        map2.put("d", 2);
        graph.put("a", map2);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("a", 8);
        map3.put("d", 7);
        graph.put("b", map3);

        Map<String, Integer> map4 = new HashMap<>();
        map4.put("end", 3);
        map4.put("d", 6);
        graph.put("c", map4);

        Map<String, Integer> map5 = new HashMap<>();
        map5.put("end", 1);
        graph.put("d", map5);

        graph.put("end", new HashMap<>());
        return graph;
    }

    @Override
    protected Map<String, Integer> getCosts() {
        Map<String, Integer> cost = new HashMap<>();
        // start cost
        cost.put("a", 5);
        cost.put("b", 2);
        cost.put("c", Integer.MAX_VALUE);
        cost.put("d", Integer.MAX_VALUE);
        cost.put("end", Integer.MAX_VALUE);
        return cost;
    }

    @Override
    protected Map<String, String> getParents() {
        Map<String, String> cost = new HashMap<>();
        // start parent
        cost.put("a", "start");
        cost.put("b", "start");
        cost.put("c", null);
        cost.put("d", null);
        cost.put("end", null);
        return cost;
    }
}
