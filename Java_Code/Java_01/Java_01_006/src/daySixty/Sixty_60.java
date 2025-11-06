package daySixty;

import java.util.*;

public class Sixty_60 {
    public static void main(String[] args) {
        String ip = "25525511135";
        System.out.println(restoreIpAddresses(ip));
        String begin = "hit", end = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        System.out.println(findLadders(begin, end, wordList));
    }

    static Set<String> set = new HashSet<>();
    static String beginWord, endWord;
    static Map<String, Integer> dist = new HashMap<>();
    static List<List<String>> res;

    public static List<List<String>> findLadders(String b, String e, List<String> wordList) {
        beginWord = b;
        endWord = e;
        res = new ArrayList<>();
        set.clear();
        dist.clear();
        for (String word : wordList) set.add(word);
        short_path();
        if (!dist.containsKey(endWord)) return res;
        List<String> path = new ArrayList<>();
        path.add(endWord);
        dfs(endWord, path);
        return res;
    }

    private static void short_path() {
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        dist.put(beginWord, 0);
        while (!q.isEmpty()) {
            String cur = q.poll();
            if (cur.equals(endWord)) break;
            char[] charCur = cur.toCharArray();
            for (int i = 0; i < cur.length(); i++) {
                char c = charCur[i];
                for (char j = 'a'; j <= 'z'; j++) {
                    charCur[i] = j;
                    String s = new String(charCur);
                    if (set.contains(s) && !dist.containsKey(s)) {
                        dist.put(s, dist.get(cur) + 1);
                        q.offer(s);
                    }
                }
                charCur[i] = c;
            }
        }
    }

    private static void dfs(String word, List<String> path) {
        if (word.equals(beginWord)) {
            List<String> list = new ArrayList<>(path);
            Collections.reverse(list);
            res.add(list);
            return;
        }
        char[] charCur = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            char c = charCur[i];
            for (char j = 'a'; j <= 'z'; j++) {
                charCur[i] = j;
                String s = new String(charCur);
                if (dist.containsKey(s) && dist.get(s) + 1 == dist.get(word)) {
                    path.add(s);
                    dfs(s, path);
                    path.remove(path.size() - 1);
                }
            }
            charCur[i] = c;
        }
    }

    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(String s, int index, List<String> list, List<String> result) {
        if (index == s.length() && list.size() == 4) {
            result.add(String.join(".", list));
            return;
        }
        if (list.size() >= 4) return;
        for (int i = 1; i <= 3 && index + i <= s.length(); i++) {
            String str = s.substring(index, index + i);
            int ip = Integer.parseInt(str);
            if ((str.startsWith("0") && str.length() > 1) || ip > 255) continue;
            list.add(str);
            backtrack(s, index + i, list, result);
            list.remove(list.size() - 1);
        }
    }
}
