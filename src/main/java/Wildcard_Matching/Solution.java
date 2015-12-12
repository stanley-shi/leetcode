package Wildcard_Matching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

   public boolean isMatch(String s, String p) {

        long start = System.currentTimeMillis();

        String[] pts = splitPattern(p);

        int[][] matrix = new int[s.length()+1][];

        for (int i = 0; i < s.length()+1; i++) {

            matrix[i] = new int[pts.length+1];

            Arrays.fill(matrix[i], 0);

        }

        matrix[0][0] = 1;

        boolean reb = matching(s, 0, pts, 0, matrix);

        long end = System.currentTimeMillis();

        System.out.println("running time: " + (end - start));

        return reb;

    }





    /**

     * patterns like "?*", "*?", "**" can be normalized to "*";

     *

     * @param ptn

     * @return

     * || ptn.charAt(i) == '*'

     */

    String[] splitPattern(String ptn) {

        List<String> res = new ArrayList<String>();

        StringBuffer cBuf = new StringBuffer();

        int prevType = -1; // ? -> type 1; * -> type 2; else type 3

        //FIXME ? * should be splitted

        for (int i = 0; i < ptn.length(); i++) {

            char cc = ptn.charAt(i);

            if (cc == '?') {

                if (prevType == 1) {

                    // same as previous

                    cBuf.append(cc);

                } else {

                    // not the same as previous

                    if (cBuf.length() != 0) {

                        if(prevType==2)

                            res.add("*");

                        else

                            res.add(cBuf.toString());

                        cBuf.setLength(0);

                    }

                    cBuf.append(cc);

                    prevType = 1;

                }

            } else if(cc =='*') {

                if (prevType == 2) {

                    cBuf.append(cc);

                } else {

                    if (cBuf.length() != 0) {

                        res.add(cBuf.toString());

                        cBuf.setLength(0);

                    }

                    cBuf.append(cc);

                    prevType = 2;

                }

            } else {

                if(prevType ==3){

                    cBuf.append(cc);

                }

                else {

                    if(cBuf.length()!=0){

                        if(prevType==2)

                            res.add("*");

                        else

                            res.add(cBuf.toString());

                        cBuf.setLength(0);

                    }

                    cBuf.append(cc);

                    prevType=3;

                }

            }

        }

        if (cBuf.length() > 0) {

            String str = cBuf.toString();

            if (str.contains("*"))

                res.add("*");

            else res.add(str);

        }

        return res.toArray(new String[0]);

    }



    private boolean matching(String s, int ss, String[] p, int sp, int[][] matrix) {

        if(ss>s.length()||sp>p.length)return false;

        if (matrix[s.length() - ss][p.length - sp] != 0) {

            return matrix[s.length() - ss][p.length - sp] == 1 ? true : false;

        }

        boolean res = false;

        if (s.length() - ss == 0) {

            if (p.length - sp == 0 || (p.length - sp == 1) && p[p.length - 1].equals("*")) {

                res = true;

            } else res = false;

        } else {

            if(p.length==sp)

                res=false;

            else {

                if (p[sp].equals("*")) {

                    res = matching(s, ss + 1, p, sp + 1, matrix) ||

                            matching(s, ss, p, sp + 1, matrix) ||

                            matching(s, ss + 1, p, sp, matrix);

                } else if (p[sp].startsWith("?")) {

                    res = matching(s, ss + p[sp].length(), p, sp + 1, matrix);

                } else {

                    if(ss+p[sp].length()>s.length())

                        res=false;

                    else

                    res = s.substring(ss, ss + p[sp].length()).equals(p[sp])

                            && matching(s, ss + p[sp].length(), p, sp + 1, matrix);

                }

            }

        }



        matrix[s.length() - ss][p.length - sp] = res ? 1 : -1;

        return res;

    }

}
