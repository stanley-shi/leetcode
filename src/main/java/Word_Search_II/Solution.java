package Word_Search_II;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Trie
 */
public class Solution {
    public static class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];
        TrieNode parent;
        boolean isLeaf;
        String word;
        List<int[]> routes;
    }

    public static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        void addWord(String word) {
            TrieNode cnode = root;
            for (int i = 0; i < word.length(); i++) {
                int ind = word.charAt(i) - 'a';
                if (cnode.children[ind] == null) {
                    TrieNode node = new TrieNode();
                    node.val = word.charAt(i);
                    node.parent = cnode;
                    cnode.children[ind] = node;

                }
                cnode = cnode.children[ind];
            }
            cnode.isLeaf = true;
            cnode.word = word;
        }

    }

    public List<String> findWords(char[][] board, String[] words) {
        Trie trie = new Trie();
        Map<Character, List<Integer>> charPosMap = new HashMap<Character, List<Integer>>();
        for (String word : words) {
            trie.addWord(word);
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (charPosMap.containsKey(c)) continue;
                List<Integer> pos = getCharPositions(board, word.charAt(i));
                charPosMap.put(c, pos);
            }
        }
        for (TrieNode nc : trie.root.children) {
            if (nc == null) continue;
            nc.routes = new ArrayList<int[]>();
            if (!charPosMap.containsKey(nc.val)) {
                // char not exist
                continue;
            } else {
                for (Integer pos : charPosMap.get(nc.val)) {
                    nc.routes.add(new int[]{pos});
                }
            }
        }
        for (TrieNode nc : trie.root.children) {
            if (nc == null || nc.routes == null) continue;
            traverseMatch(nc, charPosMap, board);
        }

        return getWords(trie);

    }

    private List<String> getWords(Trie trie) {
        List<String> res = new ArrayList<String>();
        traverse(trie.root, res);
        return res;
    }

    private void traverse(TrieNode node, List<String> res) {
        if (node == null) return;
        if (node.val != 0 && node.routes == null) return;
        if (node.isLeaf && node.routes.size() > 0) {
            res.add(node.word);
        }
        for (TrieNode nc : node.children) {
            if (null != nc) traverse(nc, res);
        }
    }

    private void traverseMatch(TrieNode node, Map<Character, List<Integer>> charPosMap, char[][] board) {
        for (int[] route : node.routes) {
            // for each adjacency node on board;
            int lastCharPos = route[route.length - 1];
            int i = lastCharPos / board[0].length;
            int j = lastCharPos % board[0].length;

            if (i > 0) {
                char c = board[i - 1][j];
                int pos = (i - 1) * board[0].length + j;
                addRoute(node, route, c, pos);
            }
            if (i < board.length - 1) {
                char c = board[i + 1][j];
                int pos = (i + 1) * board[0].length + j;
                addRoute(node, route, c, pos);
            }
            if (j > 0) {
                char c = board[i][j - 1];
                int pos = (i) * board[0].length + j - 1;
                addRoute(node, route, c, pos);
            }
            if (j < board[0].length - 1) {
                char c = board[i][j + 1];
                int pos = (i) * board[0].length + j + 1;
                addRoute(node, route, c, pos);
            }

        }

        for (TrieNode nc : node.children) {
            if (nc == null || nc.routes == null) continue;
            traverseMatch(nc, charPosMap, board);
        }

    }

    private void addRoute(TrieNode node, int[] route, char c, int pos) {
        if (!contains(route, pos)) {
            TrieNode child = node.children[c - 'a'];
            if (child != null) {
                if (null == child.routes) {
                    child.routes = new ArrayList<int[]>();
                }
                int[] newroute = new int[route.length + 1];
                System.arraycopy(route, 0, newroute, 0, route.length);
                newroute[route.length] = pos;
                child.routes.add(newroute);
            }
        }
    }

    private boolean contains(int[] route, int pos) {
        for (int i : route) {
            if (i == pos) return true;
        }
        return false;
    }


    private List<Integer> getCharPositions(char[][] board, char c) {
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == c) res.add(i * board[0].length + j);
            }
        }
        return res;
    }

    @Test
    public void test() {
        char[][] board = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> res = findWords(board, words);
        System.out.println(StringUtils.join(res, ","));
    }

}
