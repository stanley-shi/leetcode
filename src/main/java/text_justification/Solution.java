package text_justification;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by demshi on 12/4/15.
 */
public class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        if(null==words ||words.length==0){
            return new ArrayList<String>();
        }
        List<String> result = new ArrayList<String>();
        List<Integer> stop = new ArrayList<Integer>();
        int currentLength = 0;
        for(int i=0;i<words.length;i++){
            currentLength+=words[i].length() +1;
            if(currentLength-1>maxWidth){
                // break to next string;
                stop.add(i);
                currentLength = words[i].length()+1;
            }
        }
        int prev=0;
        for(int i =0;i<stop.size();i++){
            int ind = stop.get(i);
            result.add(construct(words, prev, ind, maxWidth, false));
            prev=ind;
        }
        result.add(construct(words, prev, words.length, maxWidth, true));

        return result;
    }

    private String construct(String[] words, int start, int end, int maxWidth, boolean isLastline) {
        int totallen=0;
        for(int i=start;i<end; i++)totallen += words[i].length();

        StringBuffer sb = new StringBuffer();
        sb.append(words[start]);
        if(end-start>1) {
            if(!isLastline) {
                int even = (maxWidth - totallen) / (end - start - 1);
                int pre = maxWidth - even * (end - start - 1) - totallen;
                for (int i = start + 1; i < end; i++) {
                    if (i - start <= pre) {
                        sb.append(" ");
                    }
                    appendNumSpace(sb, even);
                    sb.append(words[i]);
                }
            }else{
                for(int i = start+1;i<end;i++){
                    sb.append(" ").append(words[i]);
                }
                appendNumSpace(sb, maxWidth-totallen-(end-start-1));
            }
        } else {
            appendNumSpace(sb, maxWidth-totallen);
        }
        return sb.toString();
    }

    private void appendNumSpace(StringBuffer sb, int even) {
        for(int i =0;i<even;i++){
            sb.append(" ");
        }
    }

    @Test
    public void test(){
//        List<String> result = fullJustify(new String[]{"This", "is", "an", "example", "of", "text", "justification."}, 16);
//        List<String> result = fullJustify(new String[]{"What","must","be","shall","be."}, 16);
        List<String> result = fullJustify(new String[]{""}, 2);
        for(String st: result){
            System.out.println(st);
        }
    }
}
