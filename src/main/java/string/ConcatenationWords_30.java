package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 * Level: Hard
 */
public class ConcatenationWords_30 {

    public static void main(String[] args) {
        ConcatenationWords_30 problem = new ConcatenationWords_30();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word","good","best","good"};
        System.out.println(problem.findSubstring(s, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for(String word: words) {
            int existing = map.getOrDefault(word, 0);
            map.put(word, existing + 1);
        }

        int wordsLen = words[0].length();

        int len = words.length * wordsLen;

        List<Integer> result = new ArrayList<>();

        for(int i = 0; i <= s.length() - len ; i++) {
            HashMap<String, Integer> cloned = (HashMap<String, Integer>) map.clone();
            process(result, s.substring(i, i + len), cloned, wordsLen, i);
        }

        return result;
    }

    private void process(List<Integer> result, String s, HashMap<String, Integer> map, int wordsLen, int index) {
        int i = 0;
        while(i <= s.length() - wordsLen) {
            String subString = s.substring(i, i + wordsLen);
            boolean contains = map.containsKey(subString);
            if (!contains) break;
            else {
                int existing = map.get(subString);
                if (existing == 1) map.remove(subString);
                else map.put(subString, existing - 1);
            }
            i += wordsLen;
        }

        if (map.isEmpty()) {
            result.add(index);
        }
    }

}
