package fraction_to_recurring_decimal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demshi on 12/3/15.
 */
public class Solution {
    public static class LongPair {
        public static LongPair get(long i1, long i2) {
            LongPair p = new LongPair();
            p.first = i1;
            p.second = i2;
            return p;
        }

        long first;
        long second;
    }

    public String fractionToDecimal(int numerator, int denominator) {
        StringBuffer sb = new StringBuffer();
        if((numerator<0&&denominator>0)||(numerator>0&&denominator<0))sb.append("-");
        long newnum=numerator>0?numerator:(0L-numerator);
        long newden=denominator>0?denominator:(0L-denominator);
        long firstpart = newnum/ newden;
        sb.append(Long.toString(firstpart));
        long remaining = newnum % newden;
        if (remaining == 0) return sb.toString();
        sb.append(".");

        Set<Long> mark = new HashSet<Long>();
        List<LongPair> list = new ArrayList<LongPair>();

        while (!mark.contains(remaining) && remaining != 0) {
            long val = remaining * 10 / newden;
            long rem = remaining * 10 % newden;
            list.add(LongPair.get(remaining, val));
            mark.add(remaining);
            remaining = rem;
        }
        if (remaining ==0){
            // done
            for(LongPair p: list){
                sb.append(p.second);
            }
        } else {
            // recurring
            for(LongPair p: list){
                if(p.first == remaining){
                    sb.append("(");
                }
                sb.append(p.second);
            }
            sb.append(")");
        }

        return sb.toString();
    }

    public static void main(String[] args){
        Solution s = new Solution();
//System.out.println(        s.fractionToDecimal(1,5));
//        System.out.println(        s.fractionToDecimal(1,3));
//        System.out.println(        s.fractionToDecimal(1,7));
        System.out.println(        s.fractionToDecimal(-50,8));
//        System.out.println(        s.fractionToDecimal(-1,-2147483648));
    }
}
