import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        
        int groupCount = m;
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) {
                group[i] = groupCount++;
            }
        }


        List<List<Integer>> itemGraph = new ArrayList<>();
        int[] itemInDegree = new int[n];
        for (int i = 0; i < n; i++) {
            itemGraph.add(new ArrayList<>());
        }

        List<List<Integer>> groupGraph = new ArrayList<>();
        int[] groupInDegree = new int[groupCount];
        for (int i = 0; i < groupCount; i++) {
            groupGraph.add(new ArrayList<>());
        }

        
        for (int curr = 0; curr < n; curr++) {
            int currGroup = group[curr];
            for (int prev : beforeItems.get(curr)) {
                int prevGroup = group[prev];

                
                itemGraph.get(prev).add(curr);
                itemInDegree[curr]++;

                
                if (currGroup != prevGroup) {
                    groupGraph.get(prevGroup).add(currGroup);
                    groupInDegree[currGroup]++;
                }
            }
        }

        
        List<Integer> itemOrder = topoSort(itemGraph, itemInDegree, n);
        List<Integer> groupOrder = topoSort(groupGraph, groupInDegree, groupCount);

        if (itemOrder.isEmpty() || groupOrder.isEmpty()) {
            return new int[0];
        }

        
        Map<Integer, List<Integer>> groupToItems = new HashMap<>();
        for (int item : itemOrder) {
            groupToItems.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        
        int[] result = new int[n];
        int idx = 0;
        for (int grp : groupOrder) {
            List<Integer> items = groupToItems.get(grp);
            if (items != null) {
                for (int item : items) {
                    result[idx++] = item;
                }
            }
        }

        return result;
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] inDegree, int nodeCount) {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < nodeCount; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int neighbor : graph.get(curr)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        return order.size() == nodeCount ? order : Collections.emptyList();
    }
}