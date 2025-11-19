package daySeventyTwo;

import java.util.*;

public class SeventyTwo_72 {
    public static void main(String[] args) {
        List<List<String>> lista1 = Arrays.asList(
                Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"),
                Arrays.asList("Mary", "mary@mail.com"),
                Arrays.asList("John", "johnnybravo@mail.com")
        );
        System.out.println(accountsMerge(lista1));

        int[][] lis2 = {{1, 2}, {1, 3}, {2, 4}};
        int n2 = 4;
        System.out.println(possibleBipartition(n2, lis2));
    }

    public static boolean possibleBipartition(int n, int[][] dislikes) {
        int[] colour = new int[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] dislike : dislikes) {
            graph.get(dislike[0] - 1).add(dislike[1] - 1);
            graph.get(dislike[1] - 1).add(dislike[0] - 1);
        }
        for (int i = 0; i < n; i++) {
            if (colour[i] == 0) {
                if (!bypass(colour, i, graph, queue)) return false;
            }
        }
        return true;
    }

    public static boolean bypass(int[] colour, int n, List<List<Integer>> graph, Queue<Integer> queue) {
        colour[n] = 1;
        queue.add(n);
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            for (int node : graph.get(poll)) {
                if (colour[node] == colour[poll]) {
                    return false;
                } else if (colour[node] == 0) {
                    colour[node] = colour[poll] == 1 ? 2 : 1;
                    queue.add(node);
                }
            }
        }
        return true;
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        Map<String, Integer> map = new HashMap<>();
        DisjointSet ds = new DisjointSet(accounts.size());
        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String curr = accounts.get(i).get(j);
                if (map.containsKey(curr)) {
                    ds.unionBySize(i, map.get(curr));
                } else {
                    map.put(curr, i);
                }
            }
        }
        List<List<String>> temp = new ArrayList<>();
        for (int i = 0; i < n; i++) temp.add(new ArrayList<>());
        for (String key : map.keySet()) {
            int up = ds.findParent(map.get(key));
            temp.get(up).add(key);
        }
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> currList = temp.get(i);
            if (currList.size() == 0) continue;
            Collections.sort(currList);
            currList.add(0, accounts.get(i).get(0));
            ans.add(currList);
        }
        return ans;
    }


}


class DisjointSet {
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findParent(int node) {
        if (parent[node] == node) return node;
        int up = findParent(parent[node]);
        parent[node] = up;
        return parent[node];
    }

    public void unionBySize(int a, int b) {
        int upa = findParent(a);
        int upb = findParent(b);
        if (upa == upb) return;
        if (size[upa] < size[upb]) {
            parent[upa] = upb;
            size[upb] += size[upa];
        } else {
            parent[upb] = upa;
            size[upa] += size[upb];
        }
    }
}