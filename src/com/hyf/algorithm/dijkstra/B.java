package com.hyf.algorithm.dijkstra;

import java.util.HashMap;
import java.util.Map;

/**
 * 有环图
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class B extends Dijkstra {

    public static void main(String[] args) {
        new B().invoke();
    }

    @Override
    protected Map<String, Map<String, Integer>> getGraph() {
        Map<String, Map<String, Integer>> graph = new HashMap<>();

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        graph.put("start", map);

        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 2);
        graph.put("a", map2);

        Map<String, Integer> map3 = new HashMap<>();
        map3.put("end", 3);
        map3.put("c", 1);
        graph.put("b", map3);

        Map<String, Integer> map4 = new HashMap<>();
        map4.put("a", 1);
        graph.put("c", map4);

        graph.put("end", new HashMap<>());
        return graph;
    }

    @Override
    protected Map<String, Integer> getCosts() {
        Map<String, Integer> cost = new HashMap<>();
        // start cost
        cost.put("a", 1);
        cost.put("b", Integer.MAX_VALUE);
        cost.put("c", Integer.MAX_VALUE);
        cost.put("end", Integer.MAX_VALUE);
        return cost;
    }

    @Override
    protected Map<String, String> getParents() {
        Map<String, String> cost = new HashMap<>();
        // start parent
        cost.put("a", "start");
        cost.put("b", null);
        cost.put("c", null);
        cost.put("end", null);
        return cost;
    }
}
