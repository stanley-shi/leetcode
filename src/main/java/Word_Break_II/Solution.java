
package Word_Break_II;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class Solution {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        return null;
    }

    @Test
    public void test1(){
        String s = "catsanddog";
                Set<String> dict = new HashSet<String>();
        dict.addAll(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> res = wordBreak(s, dict);
        assertEquals(2, res.size());
    }
}