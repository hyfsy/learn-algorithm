package com.hyf.algorithm;

import java.util.*;

/**
 * 广度优先搜索
 *
 * @author baB_hyf
 * @date 2021/10/10
 */
public class BreadthFirstSearch {

    public static void main(String[] args) {
        System.out.println(breathFirstSearch(getGraph()));
    }

    public static String breathFirstSearch(Map<String, List<String>> graph) {
        Queue<String> queue = new LinkedList<>();

        // 中心搜索
        queue.addAll(graph.get("my"));

        // 防止重复检查
        Set<String> searched = new HashSet<>();

        while (!queue.isEmpty()) {
            String name = queue.poll();

            if (searched.contains(name)) {
                continue;
            }

            if (isSearch(name)) {
                return name;
            }

            // 添加二、三等等的度关系
            queue.addAll(graph.get(name));
            searched.add(name);
        }

        return null;
    }

    public static boolean isSearch(String name) {
        return name.startsWith("t");
    }

    public static Map<String, List<String>> getGraph() {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("my", Arrays.asList("bob", "claire", "alice"));
        graph.put("bob", Arrays.asList("anuj", "peggy"));
        graph.put("alice", Arrays.asList("peggy"));
        graph.put("peggy", Arrays.asList());
        graph.put("anuj", Arrays.asList());
        graph.put("claire", Arrays.asList("thom", "jonny"));
        graph.put("thom", Arrays.asList());
        graph.put("jonny", Arrays.asList());
        return graph;
    }
}
