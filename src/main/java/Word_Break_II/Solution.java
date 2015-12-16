
package Word_Break_II;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class Solution {

    public static class TrieNode {
        char val;
        TrieNode[] children = new TrieNode[26];
        TrieNode parent;
        boolean isLeaf;
        String word;
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

    public List<String> wordBreak(String s, Set<String> wordDict) {
        Trie trie = new Trie();
        Iterator<String> itor = wordDict.iterator();
        while (itor.hasNext()) {
            trie.addWord(itor.next());
        }

        Map<Integer, List<String>> pos2wordsMap = new HashMap<Integer, List<String>>();
        List<String> res = breakWords(s, 0, trie, pos2wordsMap);
        return res;
    }

    private List<String> breakWords(String s, int i, Trie trie, Map<Integer, List<String>> pos2wordsMap) {
        if (pos2wordsMap.containsKey(i))
            return pos2wordsMap.get(i);
        String[] preWords = findPrefix(s, i, trie);
        if (null == preWords) {
            // no words found
            return null;
        }
        List<String> result = new ArrayList<String>();
        for (String word : preWords) {
            if (word.length() + i == s.length()) {
                // last word
                result.add(word);
            } else {
                List<String> leftOvers = breakWords(s, i+word.length(), trie, pos2wordsMap);
                if (leftOvers != null) {
                    for (String left : leftOvers) {
                        result.add(word + " " + left);
                    }
                }
            }
        }
        pos2wordsMap.put(i, result);
        return result;
    }

    private String[] findPrefix(String s, int startIndex, Trie trie) {
        List<String> res = new LinkedList<String>();
        TrieNode cnode = trie.root;
        for (int i = startIndex; i < s.length(); i++) {
            TrieNode tn = cnode.children[s.charAt(i) - 'a'];
            if (null == tn) {
                break;
            }
            if (tn.isLeaf) {
                res.add(tn.word);
            }
            cnode = tn;
        }
        return res.toArray(new String[res.size()]);
    }

    @Test
    public void test1() {
        String s = "catsanddog";
        Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> res = wordBreak(s, dict);
        System.out.println(StringUtils.join(res, "\n"));
        assertEquals(2, res.size());
    }
}