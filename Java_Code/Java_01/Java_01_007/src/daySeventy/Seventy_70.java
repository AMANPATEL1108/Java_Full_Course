package daySeventy;

import java.util.*;

public class Seventy_70 {
    public static void main(String[] args) {
        char[][] a1 = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        solve(a1);
        List<String> lista2 = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        String begingWorkd = "hit";
        String endw = "cog";
        System.out.println(ladderLength(begingWorkd, endw, lista2));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(beginWord, 1));
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {
            String word = queue.peek().getKey();
            int steps = queue.poll().getValue();

            if (word.equals(endWord)) return steps;

            for (int i = 0; i < word.length(); i++) {
                char[] wordArr = word.toCharArray();
                char original = wordArr[i];
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    wordArr[i] = ch;
                    String newWord = new String(wordArr);
                    if (wordSet.contains(newWord)) {
                        wordSet.remove(newWord);
                        queue.offer(new Pair<>(newWord, steps + 1));
                    }
                }
            }
        }

        return 0;
    }

    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }
    public static void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;
        int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        Deque<Integer> dq = new ArrayDeque<>();
        for (int I = 0; I < n; I++) {
            for (int J = 0; J < m; J++) {
                if (board[I][J] == 'X' || board[I][J] == 'o' || (I != 0 && I != n - 1 && J != 0 && J != m - 1))
                    continue;
                dq.offer(I * m + J);
                board[I][J] = 'o';
                while (!dq.isEmpty()) {
                    int key = dq.poll();
                    int i = key / m;
                    int j = key % m;
                    for (int[] d : dir) {
                        if (i + d[0] < n && i + d[0] >= 0 && j + d[1] < m && j + d[1] >= 0 && board[i + d[0]][j + d[1]] == 'O') {
                            int k = (i + d[0]) * m + j + d[1];
                            dq.offer(k);
                            board[i + d[0]][j + d[1]] = 'o';
                        }
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'o') board[i][j] = 'O';
            }
        }
    }
}
