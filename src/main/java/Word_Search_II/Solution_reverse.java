package Word_Search_II;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.*;

public class Solution_reverse {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<String>();
        Map<Character, List<Integer>> charPosMap = new HashMap<Character, List<Integer>>();
        for (String word : words) {
            if (canFind(board, word, charPosMap)) {
                res.add(word);
            }
        }
        Collections.sort(res);
        return res;
    }

    private boolean canFind(char[][] board, String word, Map<Character, List<Integer>> charPosMap) {
        long start=System.currentTimeMillis();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (charPosMap.containsKey(c)) continue;
            List<Integer> pos = getCharPositions(board, word.charAt(i));
            charPosMap.put(c, pos);
        }

        if(word.length()==1){
            return charPosMap.containsKey(word.charAt(0));
        }

        List<Integer>[] charList= shrinkMap(charPosMap, word);

        List<Integer> usedPos = new ArrayList<Integer>();
        boolean res = canFindRest(word, 0, -1, charPosMap, usedPos, board[0].length);
        long end=System.currentTimeMillis();
        System.out.println("check word["+word+"] done with time: "+(end-start));
        return res;

    }

    @SuppressWarnings("unchecked")
    private List<Integer>[] shrinkMap(Map<Character, List<Integer>> charPosMap, String word) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Collections.fill(res,null);
        for(int i =0;i<word.length()-1;i++){
            if(res.get(i)==null)
            res.set(i,new ArrayList<Integer>(charPosMap.get(word.charAt(i))));
            if(res.get(i+1)==null)
            res.set(i+1, new ArrayList<Integer>(charPosMap.get(word.charAt(i+1))));
            List<Integer> l1 = res.get(i);
            List<Integer> l2 = res.get(2);



        }
        return (List<Integer>[]) res.toArray();
    }

    private boolean canFindRest(String word, int ind, int prevPos, Map<Character, List<Integer>> charPos, List<Integer> usedPos, int width) {
        if (ind == word.length()) return true;
        char cc = word.charAt(ind);
        for (Integer pos : charPos.get(cc)) {
            if(usedPos.contains(pos))continue;
            if (isNear(prevPos, pos, width)) {
                usedPos.add(pos);
                boolean res = canFindRest(word, ind + 1, pos, charPos, usedPos, width);
                if (res) {
                    usedPos.remove(Integer.valueOf(pos));
                    return true;
                }
                else {
                    usedPos.remove(Integer.valueOf(pos));
                }
            }
        }
        return false;
    }

    private boolean isNear(int prevPos, Integer pos, int width) {
        if (prevPos == -1) return true;
        else {
            if (pos - prevPos == 1 || pos - prevPos == -1 || pos - prevPos == width || pos - prevPos == 0 - width)
                return true;
            else return false;
        }
    }

    /**
     * Which positions contains the char
     *
     * @param board
     * @param c
     * @return
     */
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

    @Test
    public void test2() {
        String[] board = {"aaaa", "aaaa", "aaaa", "aaaa", "bcde", "fghi", "jklm", "nopq", "rstu", "vwxy", "zzzz"};
        String[] words2= {"aaaaaaaaaaaaaaaf"};
        String[] words = {
                "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaac",
                "aaaaaaaaaaaaaaad", "aaaaaaaaaaaaaaae", "aaaaaaaaaaaaaaaf",
                "aaaaaaaaaaaaaaag", "aaaaaaaaaaaaaaah", "aaaaaaaaaaaaaaai",
                "aaaaaaaaaaaaaaaj", "aaaaaaaaaaaaaaak", "aaaaaaaaaaaaaaal",
                "aaaaaaaaaaaaaaam", "aaaaaaaaaaaaaaan", "aaaaaaaaaaaaaaao",
                "aaaaaaaaaaaaaaap", "aaaaaaaaaaaaaaaq", "aaaaaaaaaaaaaaar",
                "aaaaaaaaaaaaaaas", "aaaaaaaaaaaaaaat", "aaaaaaaaaaaaaaau",
                "aaaaaaaaaaaaaaav", "aaaaaaaaaaaaaaaw", "aaaaaaaaaaaaaaax",
                "aaaaaaaaaaaaaaay", "aaaaaaaaaaaaaaaz", "aaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaac", "aaaaaaaaaaaaaaad",
                "aaaaaaaaaaaaaaae", "aaaaaaaaaaaaaaaf", "aaaaaaaaaaaaaaag",
                "aaaaaaaaaaaaaaah", "aaaaaaaaaaaaaaai", "aaaaaaaaaaaaaaaj",
                "aaaaaaaaaaaaaaak", "aaaaaaaaaaaaaaal", "aaaaaaaaaaaaaaam",
                "aaaaaaaaaaaaaaan", "aaaaaaaaaaaaaaao", "aaaaaaaaaaaaaaap",
                "aaaaaaaaaaaaaaaq", "aaaaaaaaaaaaaaar", "aaaaaaaaaaaaaaas",
                "aaaaaaaaaaaaaaat", "aaaaaaaaaaaaaaau", "aaaaaaaaaaaaaaav",
                "aaaaaaaaaaaaaaaw", "aaaaaaaaaaaaaaax", "aaaaaaaaaaaaaaay",
                "aaaaaaaaaaaaaaaz", "aaaaaaaaaaaaaaba", "aaaaaaaaaaaaaabb",
                "aaaaaaaaaaaaaabc", "aaaaaaaaaaaaaabd", "aaaaaaaaaaaaaabe",
                "aaaaaaaaaaaaaabf", "aaaaaaaaaaaaaabg", "aaaaaaaaaaaaaabh",
                "aaaaaaaaaaaaaabi", "aaaaaaaaaaaaaabj", "aaaaaaaaaaaaaabk", "aaaaaaaaaaaaaabl",
                "aaaaaaaaaaaaaabm", "aaaaaaaaaaaaaabn", "aaaaaaaaaaaaaabo", "aaaaaaaaaaaaaabp",
                "aaaaaaaaaaaaaabq", "aaaaaaaaaaaaaabr", "aaaaaaaaaaaaaabs", "aaaaaaaaaaaaaabt",
                "aaaaaaaaaaaaaabu", "aaaaaaaaaaaaaabv", "aaaaaaaaaaaaaabw", "aaaaaaaaaaaaaabx",
                "aaaaaaaaaaaaaaby", "aaaaaaaaaaaaaabz", "aaaaaaaaaaaaaaca", "aaaaaaaaaaaaaacb",
                "aaaaaaaaaaaaaacc", "aaaaaaaaaaaaaacd", "aaaaaaaaaaaaaace", "aaaaaaaaaaaaaacf",
                "aaaaaaaaaaaaaacg", "aaaaaaaaaaaaaach", "aaaaaaaaaaaaaaci", "aaaaaaaaaaaaaacj",
                "aaaaaaaaaaaaaack", "aaaaaaaaaaaaaacl", "aaaaaaaaaaaaaacm", "aaaaaaaaaaaaaacn",
                "aaaaaaaaaaaaaaco", "aaaaaaaaaaaaaacp", "aaaaaaaaaaaaaacq", "aaaaaaaaaaaaaacr",
                "aaaaaaaaaaaaaacs", "aaaaaaaaaaaaaact", "aaaaaaaaaaaaaacu", "aaaaaaaaaaaaaacv",
                "aaaaaaaaaaaaaacw", "aaaaaaaaaaaaaacx", "aaaaaaaaaaaaaacy", "aaaaaaaaaaaaaacz",
                "aaaaaaaaaaaaaada", "aaaaaaaaaaaaaadb", "aaaaaaaaaaaaaadc", "aaaaaaaaaaaaaadd",
                "aaaaaaaaaaaaaade", "aaaaaaaaaaaaaadf", "aaaaaaaaaaaaaadg", "aaaaaaaaaaaaaadh",
                "aaaaaaaaaaaaaadi", "aaaaaaaaaaaaaadj", "aaaaaaaaaaaaaadk", "aaaaaaaaaaaaaadl",
                "aaaaaaaaaaaaaadm", "aaaaaaaaaaaaaadn", "aaaaaaaaaaaaaado", "aaaaaaaaaaaaaadp",
                "aaaaaaaaaaaaaadq", "aaaaaaaaaaaaaadr", "aaaaaaaaaaaaaads", "aaaaaaaaaaaaaadt",

                "aaaaaaaaaaaaaadu", "aaaaaaaaaaaaaadv", "aaaaaaaaaaaaaadw", "aaaaaaaaaaaaaadx",
                "aaaaaaaaaaaaaady", "aaaaaaaaaaaaaadz", "aaaaaaaaaaaaaaea", "aaaaaaaaaaaaaaeb",
                "aaaaaaaaaaaaaaec", "aaaaaaaaaaaaaaed", "aaaaaaaaaaaaaaee", "aaaaaaaaaaaaaaef",
                "aaaaaaaaaaaaaaeg", "aaaaaaaaaaaaaaeh", "aaaaaaaaaaaaaaei", "aaaaaaaaaaaaaaej",
                "aaaaaaaaaaaaaaek", "aaaaaaaaaaaaaael", "aaaaaaaaaaaaaaem", "aaaaaaaaaaaaaaen",
                "aaaaaaaaaaaaaaeo", "aaaaaaaaaaaaaaep", "aaaaaaaaaaaaaaeq", "aaaaaaaaaaaaaaer",
                "aaaaaaaaaaaaaaes", "aaaaaaaaaaaaaaet", "aaaaaaaaaaaaaaeu", "aaaaaaaaaaaaaaev",
                "aaaaaaaaaaaaaaew", "aaaaaaaaaaaaaaex", "aaaaaaaaaaaaaaey", "aaaaaaaaaaaaaaez",
                "aaaaaaaaaaaaaafa", "aaaaaaaaaaaaaafb", "aaaaaaaaaaaaaafc", "aaaaaaaaaaaaaafd",
                "aaaaaaaaaaaaaafe", "aaaaaaaaaaaaaaff", "aaaaaaaaaaaaaafg", "aaaaaaaaaaaaaafh",
                "aaaaaaaaaaaaaafi", "aaaaaaaaaaaaaafj", "aaaaaaaaaaaaaafk", "aaaaaaaaaaaaaafl",
                "aaaaaaaaaaaaaafm", "aaaaaaaaaaaaaafn", "aaaaaaaaaaaaaafo", "aaaaaaaaaaaaaafp",
                "aaaaaaaaaaaaaafq", "aaaaaaaaaaaaaafr", "aaaaaaaaaaaaaafs", "aaaaaaaaaaaaaaft",
                "aaaaaaaaaaaaaafu", "aaaaaaaaaaaaaafv", "aaaaaaaaaaaaaafw", "aaaaaaaaaaaaaafx",
                "aaaaaaaaaaaaaafy", "aaaaaaaaaaaaaafz", "aaaaaaaaaaaaaaga", "aaaaaaaaaaaaaagb",
                "aaaaaaaaaaaaaagc", "aaaaaaaaaaaaaagd", "aaaaaaaaaaaaaage", "aaaaaaaaaaaaaagf",
                "aaaaaaaaaaaaaagg", "aaaaaaaaaaaaaagh", "aaaaaaaaaaaaaagi", "aaaaaaaaaaaaaagj",
                "aaaaaaaaaaaaaagk", "aaaaaaaaaaaaaagl", "aaaaaaaaaaaaaagm", "aaaaaaaaaaaaaagn",
                "aaaaaaaaaaaaaago", "aaaaaaaaaaaaaagp", "aaaaaaaaaaaaaagq", "aaaaaaaaaaaaaagr",
                "aaaaaaaaaaaaaags", "aaaaaaaaaaaaaagt", "aaaaaaaaaaaaaagu", "aaaaaaaaaaaaaagv",
                "aaaaaaaaaaaaaagw", "aaaaaaaaaaaaaagx", "aaaaaaaaaaaaaagy", "aaaaaaaaaaaaaagz",
                "aaaaaaaaaaaaaaha", "aaaaaaaaaaaaaahb", "aaaaaaaaaaaaaahc", "aaaaaaaaaaaaaahd",
                "aaaaaaaaaaaaaahe", "aaaaaaaaaaaaaahf", "aaaaaaaaaaaaaahg", "aaaaaaaaaaaaaahh",
                "aaaaaaaaaaaaaahi", "aaaaaaaaaaaaaahj", "aaaaaaaaaaaaaahk", "aaaaaaaaaaaaaahl",
                "aaaaaaaaaaaaaahm", "aaaaaaaaaaaaaahn", "aaaaaaaaaaaaaaho", "aaaaaaaaaaaaaahp",
                "aaaaaaaaaaaaaahq", "aaaaaaaaaaaaaahr", "aaaaaaaaaaaaaahs", "aaaaaaaaaaaaaaht",
                "aaaaaaaaaaaaaahu", "aaaaaaaaaaaaaahv", "aaaaaaaaaaaaaahw", "aaaaaaaaaaaaaahx",
                "aaaaaaaaaaaaaahy", "aaaaaaaaaaaaaahz", "aaaaaaaaaaaaaaia", "aaaaaaaaaaaaaaib",
                "aaaaaaaaaaaaaaic", "aaaaaaaaaaaaaaid", "aaaaaaaaaaaaaaie", "aaaaaaaaaaaaaaif", "aaaaaaaaaaaaaaig", "aaaaaaaaaaaaaaih", "aaaaaaaaaaaaaaii", "aaaaaaaaaaaaaaij", "aaaaaaaaaaaaaaik", "aaaaaaaaaaaaaail", "aaaaaaaaaaaaaaim", "aaaaaaaaaaaaaain", "aaaaaaaaaaaaaaio", "aaaaaaaaaaaaaaip", "aaaaaaaaaaaaaaiq", "aaaaaaaaaaaaaair", "aaaaaaaaaaaaaais", "aaaaaaaaaaaaaait", "aaaaaaaaaaaaaaiu", "aaaaaaaaaaaaaaiv", "aaaaaaaaaaaaaaiw", "aaaaaaaaaaaaaaix", "aaaaaaaaaaaaaaiy", "aaaaaaaaaaaaaaiz", "aaaaaaaaaaaaaaja", "aaaaaaaaaaaaaajb", "aaaaaaaaaaaaaajc", "aaaaaaaaaaaaaajd", "aaaaaaaaaaaaaaje", "aaaaaaaaaaaaaajf", "aaaaaaaaaaaaaajg", "aaaaaaaaaaaaaajh", "aaaaaaaaaaaaaaji", "aaaaaaaaaaaaaajj", "aaaaaaaaaaaaaajk", "aaaaaaaaaaaaaajl", "aaaaaaaaaaaaaajm", "aaaaaaaaaaaaaajn", "aaaaaaaaaaaaaajo", "aaaaaaaaaaaaaajp", "aaaaaaaaaaaaaajq", "aaaaaaaaaaaaaajr", "aaaaaaaaaaaaaajs", "aaaaaaaaaaaaaajt", "aaaaaaaaaaaaaaju", "aaaaaaaaaaaaaajv", "aaaaaaaaaaaaaajw", "aaaaaaaaaaaaaajx", "aaaaaaaaaaaaaajy", "aaaaaaaaaaaaaajz", "aaaaaaaaaaaaaaka", "aaaaaaaaaaaaaakb", "aaaaaaaaaaaaaakc", "aaaaaaaaaaaaaakd", "aaaaaaaaaaaaaake", "aaaaaaaaaaaaaakf", "aaaaaaaaaaaaaakg", "aaaaaaaaaaaaaakh", "aaaaaaaaaaaaaaki", "aaaaaaaaaaaaaakj", "aaaaaaaaaaaaaakk", "aaaaaaaaaaaaaakl", "aaaaaaaaaaaaaakm", "aaaaaaaaaaaaaakn", "aaaaaaaaaaaaaako", "aaaaaaaaaaaaaakp", "aaaaaaaaaaaaaakq", "aaaaaaaaaaaaaakr", "aaaaaaaaaaaaaaks", "aaaaaaaaaaaaaakt", "aaaaaaaaaaaaaaku", "aaaaaaaaaaaaaakv", "aaaaaaaaaaaaaakw", "aaaaaaaaaaaaaakx", "aaaaaaaaaaaaaaky", "aaaaaaaaaaaaaakz", "aaaaaaaaaaaaaala", "aaaaaaaaaaaaaalb", "aaaaaaaaaaaaaalc", "aaaaaaaaaaaaaald", "aaaaaaaaaaaaaale", "aaaaaaaaaaaaaalf", "aaaaaaaaaaaaaalg", "aaaaaaaaaaaaaalh", "aaaaaaaaaaaaaali", "aaaaaaaaaaaaaalj", "aaaaaaaaaaaaaalk", "aaaaaaaaaaaaaall", "aaaaaaaaaaaaaalm", "aaaaaaaaaaaaaaln", "aaaaaaaaaaaaaalo", "aaaaaaaaaaaaaalp", "aaaaaaaaaaaaaalq", "aaaaaaaaaaaaaalr", "aaaaaaaaaaaaaals", "aaaaaaaaaaaaaalt", "aaaaaaaaaaaaaalu", "aaaaaaaaaaaaaalv", "aaaaaaaaaaaaaalw", "aaaaaaaaaaaaaalx", "aaaaaaaaaaaaaaly", "aaaaaaaaaaaaaalz", "aaaaaaaaaaaaaama", "aaaaaaaaaaaaaamb", "aaaaaaaaaaaaaamc", "aaaaaaaaaaaaaamd", "aaaaaaaaaaaaaame", "aaaaaaaaaaaaaamf", "aaaaaaaaaaaaaamg", "aaaaaaaaaaaaaamh", "aaaaaaaaaaaaaami", "aaaaaaaaaaaaaamj", "aaaaaaaaaaaaaamk", "aaaaaaaaaaaaaaml", "aaaaaaaaaaaaaamm", "aaaaaaaaaaaaaamn", "aaaaaaaaaaaaaamo", "aaaaaaaaaaaaaamp", "aaaaaaaaaaaaaamq", "aaaaaaaaaaaaaamr", "aaaaaaaaaaaaaams", "aaaaaaaaaaaaaamt", "aaaaaaaaaaaaaamu", "aaaaaaaaaaaaaamv", "aaaaaaaaaaaaaamw", "aaaaaaaaaaaaaamx", "aaaaaaaaaaaaaamy", "aaaaaaaaaaaaaamz", "aaaaaaaaaaaaaana", "aaaaaaaaaaaaaanb", "aaaaaaaaaaaaaanc", "aaaaaaaaaaaaaand", "aaaaaaaaaaaaaane", "aaaaaaaaaaaaaanf", "aaaaaaaaaaaaaang", "aaaaaaaaaaaaaanh", "aaaaaaaaaaaaaani", "aaaaaaaaaaaaaanj", "aaaaaaaaaaaaaank", "aaaaaaaaaaaaaanl", "aaaaaaaaaaaaaanm", "aaaaaaaaaaaaaann", "aaaaaaaaaaaaaano", "aaaaaaaaaaaaaanp", "aaaaaaaaaaaaaanq", "aaaaaaaaaaaaaanr", "aaaaaaaaaaaaaans", "aaaaaaaaaaaaaant", "aaaaaaaaaaaaaanu", "aaaaaaaaaaaaaanv", "aaaaaaaaaaaaaanw", "aaaaaaaaaaaaaanx", "aaaaaaaaaaaaaany", "aaaaaaaaaaaaaanz", "aaaaaaaaaaaaaaoa", "aaaaaaaaaaaaaaob", "aaaaaaaaaaaaaaoc", "aaaaaaaaaaaaaaod", "aaaaaaaaaaaaaaoe", "aaaaaaaaaaaaaaof", "aaaaaaaaaaaaaaog", "aaaaaaaaaaaaaaoh", "aaaaaaaaaaaaaaoi", "aaaaaaaaaaaaaaoj", "aaaaaaaaaaaaaaok", "aaaaaaaaaaaaaaol", "aaaaaaaaaaaaaaom", "aaaaaaaaaaaaaaon", "aaaaaaaaaaaaaaoo", "aaaaaaaaaaaaaaop", "aaaaaaaaaaaaaaoq", "aaaaaaaaaaaaaaor", "aaaaaaaaaaaaaaos", "aaaaaaaaaaaaaaot", "aaaaaaaaaaaaaaou", "aaaaaaaaaaaaaaov", "aaaaaaaaaaaaaaow", "aaaaaaaaaaaaaaox", "aaaaaaaaaaaaaaoy", "aaaaaaaaaaaaaaoz", "aaaaaaaaaaaaaapa", "aaaaaaaaaaaaaapb", "aaaaaaaaaaaaaapc", "aaaaaaaaaaaaaapd", "aaaaaaaaaaaaaape", "aaaaaaaaaaaaaapf", "aaaaaaaaaaaaaapg", "aaaaaaaaaaaaaaph", "aaaaaaaaaaaaaapi", "aaaaaaaaaaaaaapj", "aaaaaaaaaaaaaapk", "aaaaaaaaaaaaaapl", "aaaaaaaaaaaaaapm", "aaaaaaaaaaaaaapn", "aaaaaaaaaaaaaapo", "aaaaaaaaaaaaaapp", "aaaaaaaaaaaaaapq", "aaaaaaaaaaaaaapr", "aaaaaaaaaaaaaaps", "aaaaaaaaaaaaaapt", "aaaaaaaaaaaaaapu", "aaaaaaaaaaaaaapv", "aaaaaaaaaaaaaapw", "aaaaaaaaaaaaaapx", "aaaaaaaaaaaaaapy", "aaaaaaaaaaaaaapz", "aaaaaaaaaaaaaaqa", "aaaaaaaaaaaaaaqb", "aaaaaaaaaaaaaaqc", "aaaaaaaaaaaaaaqd", "aaaaaaaaaaaaaaqe", "aaaaaaaaaaaaaaqf", "aaaaaaaaaaaaaaqg", "aaaaaaaaaaaaaaqh", "aaaaaaaaaaaaaaqi", "aaaaaaaaaaaaaaqj", "aaaaaaaaaaaaaaqk", "aaaaaaaaaaaaaaql", "aaaaaaaaaaaaaaqm", "aaaaaaaaaaaaaaqn", "aaaaaaaaaaaaaaqo", "aaaaaaaaaaaaaaqp", "aaaaaaaaaaaaaaqq", "aaaaaaaaaaaaaaqr", "aaaaaaaaaaaaaaqs", "aaaaaaaaaaaaaaqt", "aaaaaaaaaaaaaaqu", "aaaaaaaaaaaaaaqv", "aaaaaaaaaaaaaaqw", "aaaaaaaaaaaaaaqx", "aaaaaaaaaaaaaaqy", "aaaaaaaaaaaaaaqz", "aaaaaaaaaaaaaara", "aaaaaaaaaaaaaarb", "aaaaaaaaaaaaaarc", "aaaaaaaaaaaaaard", "aaaaaaaaaaaaaare", "aaaaaaaaaaaaaarf", "aaaaaaaaaaaaaarg", "aaaaaaaaaaaaaarh", "aaaaaaaaaaaaaari", "aaaaaaaaaaaaaarj", "aaaaaaaaaaaaaark", "aaaaaaaaaaaaaarl", "aaaaaaaaaaaaaarm", "aaaaaaaaaaaaaarn", "aaaaaaaaaaaaaaro", "aaaaaaaaaaaaaarp", "aaaaaaaaaaaaaarq", "aaaaaaaaaaaaaarr", "aaaaaaaaaaaaaars", "aaaaaaaaaaaaaart", "aaaaaaaaaaaaaaru", "aaaaaaaaaaaaaarv", "aaaaaaaaaaaaaarw", "aaaaaaaaaaaaaarx", "aaaaaaaaaaaaaary", "aaaaaaaaaaaaaarz", "aaaaaaaaaaaaaasa", "aaaaaaaaaaaaaasb", "aaaaaaaaaaaaaasc", "aaaaaaaaaaaaaasd", "aaaaaaaaaaaaaase", "aaaaaaaaaaaaaasf", "aaaaaaaaaaaaaasg", "aaaaaaaaaaaaaash", "aaaaaaaaaaaaaasi", "aaaaaaaaaaaaaasj", "aaaaaaaaaaaaaask", "aaaaaaaaaaaaaasl", "aaaaaaaaaaaaaasm", "aaaaaaaaaaaaaasn", "aaaaaaaaaaaaaaso", "aaaaaaaaaaaaaasp", "aaaaaaaaaaaaaasq", "aaaaaaaaaaaaaasr", "aaaaaaaaaaaaaass", "aaaaaaaaaaaaaast", "aaaaaaaaaaaaaasu", "aaaaaaaaaaaaaasv", "aaaaaaaaaaaaaasw", "aaaaaaaaaaaaaasx", "aaaaaaaaaaaaaasy", "aaaaaaaaaaaaaasz", "aaaaaaaaaaaaaata", "aaaaaaaaaaaaaatb", "aaaaaaaaaaaaaatc", "aaaaaaaaaaaaaatd", "aaaaaaaaaaaaaate", "aaaaaaaaaaaaaatf", "aaaaaaaaaaaaaatg", "aaaaaaaaaaaaaath", "aaaaaaaaaaaaaati", "aaaaaaaaaaaaaatj", "aaaaaaaaaaaaaatk", "aaaaaaaaaaaaaatl", "aaaaaaaaaaaaaatm", "aaaaaaaaaaaaaatn", "aaaaaaaaaaaaaato", "aaaaaaaaaaaaaatp", "aaaaaaaaaaaaaatq", "aaaaaaaaaaaaaatr", "aaaaaaaaaaaaaats", "aaaaaaaaaaaaaatt", "aaaaaaaaaaaaaatu", "aaaaaaaaaaaaaatv", "aaaaaaaaaaaaaatw", "aaaaaaaaaaaaaatx", "aaaaaaaaaaaaaaty", "aaaaaaaaaaaaaatz", "aaaaaaaaaaaaaaua", "aaaaaaaaaaaaaaub", "aaaaaaaaaaaaaauc", "aaaaaaaaaaaaaaud", "aaaaaaaaaaaaaaue", "aaaaaaaaaaaaaauf", "aaaaaaaaaaaaaaug", "aaaaaaaaaaaaaauh", "aaaaaaaaaaaaaaui", "aaaaaaaaaaaaaauj", "aaaaaaaaaaaaaauk", "aaaaaaaaaaaaaaul", "aaaaaaaaaaaaaaum", "aaaaaaaaaaaaaaun", "aaaaaaaaaaaaaauo", "aaaaaaaaaaaaaaup", "aaaaaaaaaaaaaauq", "aaaaaaaaaaaaaaur", "aaaaaaaaaaaaaaus", "aaaaaaaaaaaaaaut", "aaaaaaaaaaaaaauu", "aaaaaaaaaaaaaauv", "aaaaaaaaaaaaaauw", "aaaaaaaaaaaaaaux", "aaaaaaaaaaaaaauy", "aaaaaaaaaaaaaauz", "aaaaaaaaaaaaaava", "aaaaaaaaaaaaaavb", "aaaaaaaaaaaaaavc", "aaaaaaaaaaaaaavd", "aaaaaaaaaaaaaave", "aaaaaaaaaaaaaavf", "aaaaaaaaaaaaaavg", "aaaaaaaaaaaaaavh", "aaaaaaaaaaaaaavi", "aaaaaaaaaaaaaavj", "aaaaaaaaaaaaaavk", "aaaaaaaaaaaaaavl", "aaaaaaaaaaaaaavm", "aaaaaaaaaaaaaavn", "aaaaaaaaaaaaaavo", "aaaaaaaaaaaaaavp", "aaaaaaaaaaaaaavq", "aaaaaaaaaaaaaavr", "aaaaaaaaaaaaaavs", "aaaaaaaaaaaaaavt", "aaaaaaaaaaaaaavu", "aaaaaaaaaaaaaavv", "aaaaaaaaaaaaaavw", "aaaaaaaaaaaaaavx", "aaaaaaaaaaaaaavy", "aaaaaaaaaaaaaavz", "aaaaaaaaaaaaaawa", "aaaaaaaaaaaaaawb", "aaaaaaaaaaaaaawc", "aaaaaaaaaaaaaawd", "aaaaaaaaaaaaaawe", "aaaaaaaaaaaaaawf", "aaaaaaaaaaaaaawg", "aaaaaaaaaaaaaawh", "aaaaaaaaaaaaaawi", "aaaaaaaaaaaaaawj", "aaaaaaaaaaaaaawk", "aaaaaaaaaaaaaawl", "aaaaaaaaaaaaaawm", "aaaaaaaaaaaaaawn", "aaaaaaaaaaaaaawo", "aaaaaaaaaaaaaawp", "aaaaaaaaaaaaaawq", "aaaaaaaaaaaaaawr", "aaaaaaaaaaaaaaws", "aaaaaaaaaaaaaawt", "aaaaaaaaaaaaaawu", "aaaaaaaaaaaaaawv", "aaaaaaaaaaaaaaww", "aaaaaaaaaaaaaawx", "aaaaaaaaaaaaaawy", "aaaaaaaaaaaaaawz", "aaaaaaaaaaaaaaxa", "aaaaaaaaaaaaaaxb", "aaaaaaaaaaaaaaxc", "aaaaaaaaaaaaaaxd", "aaaaaaaaaaaaaaxe", "aaaaaaaaaaaaaaxf", "aaaaaaaaaaaaaaxg", "aaaaaaaaaaaaaaxh", "aaaaaaaaaaaaaaxi", "aaaaaaaaaaaaaaxj", "aaaaaaaaaaaaaaxk", "aaaaaaaaaaaaaaxl", "aaaaaaaaaaaaaaxm", "aaaaaaaaaaaaaaxn", "aaaaaaaaaaaaaaxo", "aaaaaaaaaaaaaaxp", "aaaaaaaaaaaaaaxq", "aaaaaaaaaaaaaaxr", "aaaaaaaaaaaaaaxs", "aaaaaaaaaaaaaaxt", "aaaaaaaaaaaaaaxu", "aaaaaaaaaaaaaaxv", "aaaaaaaaaaaaaaxw", "aaaaaaaaaaaaaaxx", "aaaaaaaaaaaaaaxy", "aaaaaaaaaaaaaaxz", "aaaaaaaaaaaaaaya", "aaaaaaaaaaaaaayb", "aaaaaaaaaaaaaayc", "aaaaaaaaaaaaaayd", "aaaaaaaaaaaaaaye", "aaaaaaaaaaaaaayf", "aaaaaaaaaaaaaayg", "aaaaaaaaaaaaaayh", "aaaaaaaaaaaaaayi", "aaaaaaaaaaaaaayj", "aaaaaaaaaaaaaayk", "aaaaaaaaaaaaaayl", "aaaaaaaaaaaaaaym", "aaaaaaaaaaaaaayn", "aaaaaaaaaaaaaayo", "aaaaaaaaaaaaaayp", "aaaaaaaaaaaaaayq", "aaaaaaaaaaaaaayr", "aaaaaaaaaaaaaays", "aaaaaaaaaaaaaayt", "aaaaaaaaaaaaaayu", "aaaaaaaaaaaaaayv", "aaaaaaaaaaaaaayw", "aaaaaaaaaaaaaayx", "aaaaaaaaaaaaaayy", "aaaaaaaaaaaaaayz", "aaaaaaaaaaaaaaza", "aaaaaaaaaaaaaazb", "aaaaaaaaaaaaaazc", "aaaaaaaaaaaaaazd", "aaaaaaaaaaaaaaze", "aaaaaaaaaaaaaazf", "aaaaaaaaaaaaaazg", "aaaaaaaaaaaaaazh", "aaaaaaaaaaaaaazi", "aaaaaaaaaaaaaazj", "aaaaaaaaaaaaaazk", "aaaaaaaaaaaaaazl", "aaaaaaaaaaaaaazm", "aaaaaaaaaaaaaazn", "aaaaaaaaaaaaaazo", "aaaaaaaaaaaaaazp", "aaaaaaaaaaaaaazq", "aaaaaaaaaaaaaazr", "aaaaaaaaaaaaaazs", "aaaaaaaaaaaaaazt", "aaaaaaaaaaaaaazu", "aaaaaaaaaaaaaazv", "aaaaaaaaaaaaaazw", "aaaaaaaaaaaaaazx", "aaaaaaaaaaaaaazy", "aaaaaaaaaaaaaazz", "aaaaaaaaaaaaaaaa", "aaaaaaaaaaaaaaab", "aaaaaaaaaaaaaaac", "aaaaaaaaaaaaaaad", "aaaaaaaaaaaaaaae", "aaaaaaaaaaaaaaaf", "aaaaaaaaaaaaaaag", "aaaaaaaaaaaaaaah", "aaaaaaaaaaaaaaai", "aaaaaaaaaaaaaaaj", "aaaaaaaaaaaaaaak", "aaaaaaaaaaaaaaal", "aaaaaaaaaaaaaaam", "aaaaaaaaaaaaaaan", "aaaaaaaaaaaaaaao", "aaaaaaaaaaaaaaap", "aaaaaaaaaaaaaaaq", "aaaaaaaaaaaaaaar", "aaaaaaaaaaaaaaas", "aaaaaaaaaaaaaaat", "aaaaaaaaaaaaaaau", "aaaaaaaaaaaaaaav", "aaaaaaaaaaaaaaaw", "aaaaaaaaaaaaaaax", "aaaaaaaaaaaaaaay", "aaaaaaaaaaaaaaaz", "aaaaaaaaaaaaaaba", "aaaaaaaaaaaaaabb", "aaaaaaaaaaaaaabc", "aaaaaaaaaaaaaabd", "aaaaaaaaaaaaaabe", "aaaaaaaaaaaaaabf", "aaaaaaaaaaaaaabg", "aaaaaaaaaaaaaabh", "aaaaaaaaaaaaaabi", "aaaaaaaaaaaaaabj", "aaaaaaaaaaaaaabk", "aaaaaaaaaaaaaabl", "aaaaaaaaaaaaaabm", "aaaaaaaaaaaaaabn", "aaaaaaaaaaaaaabo", "aaaaaaaaaaaaaabp", "aaaaaaaaaaaaaabq", "aaaaaaaaaaaaaabr", "aaaaaaaaaaaaaabs", "aaaaaaaaaaaaaabt", "aaaaaaaaaaaaaabu", "aaaaaaaaaaaaaabv", "aaaaaaaaaaaaaabw", "aaaaaaaaaaaaaabx", "aaaaaaaaaaaaaaby", "aaaaaaaaaaaaaabz", "aaaaaaaaaaaaaaca", "aaaaaaaaaaaaaacb", "aaaaaaaaaaaaaacc", "aaaaaaaaaaaaaacd", "aaaaaaaaaaaaaace", "aaaaaaaaaaaaaacf", "aaaaaaaaaaaaaacg", "aaaaaaaaaaaaaach", "aaaaaaaaaaaaaaci", "aaaaaaaaaaaaaacj", "aaaaaaaaaaaaaack", "aaaaaaaaaaaaaacl", "aaaaaaaaaaaaaacm", "aaaaaaaaaaaaaacn", "aaaaaaaaaaaaaaco", "aaaaaaaaaaaaaacp", "aaaaaaaaaaaaaacq", "aaaaaaaaaaaaaacr", "aaaaaaaaaaaaaacs", "aaaaaaaaaaaaaact", "aaaaaaaaaaaaaacu", "aaaaaaaaaaaaaacv", "aaaaaaaaaaaaaacw", "aaaaaaaaaaaaaacx", "aaaaaaaaaaaaaacy", "aaaaaaaaaaaaaacz", "aaaaaaaaaaaaaada", "aaaaaaaaaaaaaadb", "aaaaaaaaaaaaaadc", "aaaaaaaaaaaaaadd", "aaaaaaaaaaaaaade", "aaaaaaaaaaaaaadf", "aaaaaaaaaaaaaadg", "aaaaaaaaaaaaaadh", "aaaaaaaaaaaaaadi", "aaaaaaaaaaaaaadj", "aaaaaaaaaaaaaadk", "aaaaaaaaaaaaaadl", "aaaaaaaaaaaaaadm", "aaaaaaaaaaaaaadn", "aaaaaaaaaaaaaado", "aaaaaaaaaaaaaadp", "aaaaaaaaaaaaaadq", "aaaaaaaaaaaaaadr", "aaaaaaaaaaaaaads", "aaaaaaaaaaaaaadt", "aaaaaaaaaaaaaadu", "aaaaaaaaaaaaaadv", "aaaaaaaaaaaaaadw", "aaaaaaaaaaaaaadx", "aaaaaaaaaaaaaady", "aaaaaaaaaaaaaadz", "aaaaaaaaaaaaaaea", "aaaaaaaaaaaaaaeb", "aaaaaaaaaaaaaaec", "aaaaaaaaaaaaaaed", "aaaaaaaaaaaaaaee", "aaaaaaaaaaaaaaef", "aaaaaaaaaaaaaaeg", "aaaaaaaaaaaaaaeh", "aaaaaaaaaaaaaaei", "aaaaaaaaaaaaaaej", "aaaaaaaaaaaaaaek", "aaaaaaaaaaaaaael", "aaaaaaaaaaaaaaem", "aaaaaaaaaaaaaaen", "aaaaaaaaaaaaaaeo", "aaaaaaaaaaaaaaep", "aaaaaaaaaaaaaaeq", "aaaaaaaaaaaaaaer", "aaaaaaaaaaaaaaes", "aaaaaaaaaaaaaaet", "aaaaaaaaaaaaaaeu", "aaaaaaaaaaaaaaev", "aaaaaaaaaaaaaaew", "aaaaaaaaaaaaaaex", "aaaaaaaaaaaaaaey", "aaaaaaaaaaaaaaez", "aaaaaaaaaaaaaafa", "aaaaaaaaaaaaaafb", "aaaaaaaaaaaaaafc", "aaaaaaaaaaaaaafd", "aaaaaaaaaaaaaafe", "aaaaaaaaaaaaaaff", "aaaaaaaaaaaaaafg", "aaaaaaaaaaaaaafh", "aaaaaaaaaaaaaafi", "aaaaaaaaaaaaaafj", "aaaaaaaaaaaaaafk", "aaaaaaaaaaaaaafl", "aaaaaaaaaaaaaafm", "aaaaaaaaaaaaaafn", "aaaaaaaaaaaaaafo", "aaaaaaaaaaaaaafp", "aaaaaaaaaaaaaafq", "aaaaaaaaaaaaaafr", "aaaaaaaaaaaaaafs", "aaaaaaaaaaaaaaft", "aaaaaaaaaaaaaafu", "aaaaaaaaaaaaaafv", "aaaaaaaaaaaaaafw", "aaaaaaaaaaaaaafx", "aaaaaaaaaaaaaafy", "aaaaaaaaaaaaaafz", "aaaaaaaaaaaaaaga", "aaaaaaaaaaaaaagb", "aaaaaaaaaaaaaagc", "aaaaaaaaaaaaaagd", "aaaaaaaaaaaaaage", "aaaaaaaaaaaaaagf", "aaaaaaaaaaaaaagg", "aaaaaaaaaaaaaagh", "aaaaaaaaaaaaaagi", "aaaaaaaaaaaaaagj", "aaaaaaaaaaaaaagk", "aaaaaaaaaaaaaagl", "aaaaaaaaaaaaaagm", "aaaaaaaaaaaaaagn", "aaaaaaaaaaaaaago", "aaaaaaaaaaaaaagp", "aaaaaaaaaaaaaagq", "aaaaaaaaaaaaaagr", "aaaaaaaaaaaaaags", "aaaaaaaaaaaaaagt", "aaaaaaaaaaaaaagu", "aaaaaaaaaaaaaagv", "aaaaaaaaaaaaaagw", "aaaaaaaaaaaaaagx", "aaaaaaaaaaaaaagy", "aaaaaaaaaaaaaagz", "aaaaaaaaaaaaaaha", "aaaaaaaaaaaaaahb", "aaaaaaaaaaaaaahc", "aaaaaaaaaaaaaahd", "aaaaaaaaaaaaaahe", "aaaaaaaaaaaaaahf", "aaaaaaaaaaaaaahg", "aaaaaaaaaaaaaahh", "aaaaaaaaaaaaaahi", "aaaaaaaaaaaaaahj", "aaaaaaaaaaaaaahk", "aaaaaaaaaaaaaahl", "aaaaaaaaaaaaaahm", "aaaaaaaaaaaaaahn", "aaaaaaaaaaaaaaho", "aaaaaaaaaaaaaahp", "aaaaaaaaaaaaaahq", "aaaaaaaaaaaaaahr", "aaaaaaaaaaaaaahs", "aaaaaaaaaaaaaaht", "aaaaaaaaaaaaaahu", "aaaaaaaaaaaaaahv", "aaaaaaaaaaaaaahw", "aaaaaaaaaaaaaahx", "aaaaaaaaaaaaaahy", "aaaaaaaaaaaaaahz", "aaaaaaaaaaaaaaia", "aaaaaaaaaaaaaaib", "aaaaaaaaaaaaaaic", "aaaaaaaaaaaaaaid", "aaaaaaaaaaaaaaie", "aaaaaaaaaaaaaaif", "aaaaaaaaaaaaaaig", "aaaaaaaaaaaaaaih", "aaaaaaaaaaaaaaii", "aaaaaaaaaaaaaaij", "aaaaaaaaaaaaaaik", "aaaaaaaaaaaaaail", "aaaaaaaaaaaaaaim", "aaaaaaaaaaaaaain", "aaaaaaaaaaaaaaio", "aaaaaaaaaaaaaaip", "aaaaaaaaaaaaaaiq", "aaaaaaaaaaaaaair", "aaaaaaaaaaaaaais", "aaaaaaaaaaaaaait", "aaaaaaaaaaaaaaiu", "aaaaaaaaaaaaaaiv", "aaaaaaaaaaaaaaiw", "aaaaaaaaaaaaaaix", "aaaaaaaaaaaaaaiy", "aaaaaaaaaaaaaaiz", "aaaaaaaaaaaaaaja", "aaaaaaaaaaaaaajb", "aaaaaaaaaaaaaajc", "aaaaaaaaaaaaaajd", "aaaaaaaaaaaaaaje", "aaaaaaaaaaaaaajf", "aaaaaaaaaaaaaajg", "aaaaaaaaaaaaaajh", "aaaaaaaaaaaaaaji", "aaaaaaaaaaaaaajj", "aaaaaaaaaaaaaajk", "aaaaaaaaaaaaaajl", "aaaaaaaaaaaaaajm", "aaaaaaaaaaaaaajn", "aaaaaaaaaaaaaajo", "aaaaaaaaaaaaaajp", "aaaaaaaaaaaaaajq", "aaaaaaaaaaaaaajr", "aaaaaaaaaaaaaajs", "aaaaaaaaaaaaaajt", "aaaaaaaaaaaaaaju", "aaaaaaaaaaaaaajv", "aaaaaaaaaaaaaajw", "aaaaaaaaaaaaaajx", "aaaaaaaaaaaaaajy", "aaaaaaaaaaaaaajz", "aaaaaaaaaaaaaaka", "aaaaaaaaaaaaaakb", "aaaaaaaaaaaaaakc", "aaaaaaaaaaaaaakd", "aaaaaaaaaaaaaake", "aaaaaaaaaaaaaakf", "aaaaaaaaaaaaaakg", "aaaaaaaaaaaaaakh", "aaaaaaaaaaaaaaki", "aaaaaaaaaaaaaakj", "aaaaaaaaaaaaaakk", "aaaaaaaaaaaaaakl", "aaaaaaaaaaaaaakm", "aaaaaaaaaaaaaakn", "aaaaaaaaaaaaaako", "aaaaaaaaaaaaaakp", "aaaaaaaaaaaaaakq", "aaaaaaaaaaaaaakr", "aaaaaaaaaaaaaaks", "aaaaaaaaaaaaaakt", "aaaaaaaaaaaaaaku", "aaaaaaaaaaaaaakv", "aaaaaaaaaaaaaakw", "aaaaaaaaaaaaaakx", "aaaaaaaaaaaaaaky", "aaaaaaaaaaaaaakz", "aaaaaaaaaaaaaala", "aaaaaaaaaaaaaalb", "aaaaaaaaaaaaaalc", "aaaaaaaaaaaaaald", "aaaaaaaaaaaaaale", "aaaaaaaaaaaaaalf", "aaaaaaaaaaaaaalg", "aaaaaaaaaaaaaalh", "aaaaaaaaaaaaaali", "aaaaaaaaaaaaaalj", "aaaaaaaaaaaaaalk", "aaaaaaaaaaaaaall"};
        char[][] cboard = new char[board.length][];
        for (int i = 0; i < cboard.length; i++) {
            cboard[i] = board[i].toCharArray();
        }
        long start = System.currentTimeMillis();
        List<String> list = findWords(cboard, words);
        long end = System.currentTimeMillis();
        System.out.println("finished in : " + (end - start));
        System.out.println(StringUtils.join(list, ","));
    }
}