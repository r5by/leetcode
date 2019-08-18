package leetcode.q30_substring_with_concatenation_of_words;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ruby_
 * @create 2018-12-07-8:57 PM
 */

class Solution {
    private int wordlength;
    public List<Integer> findSubstring(String s, String[] words) {

        List<Integer> result = new ArrayList<Integer>();

        if(words.length == 0)
            throw new IllegalArgumentException("Words cannot be empty!");

        wordlength = words[0].length();
        if(wordlength == 0)
            throw new IllegalArgumentException("Words can't be empty strings!");
        else if(wordlength * words.length > s.length())
            return result;//Return an empty list if words are larger than s

        for(int i = 0; i < s.length() - wordlength * words.length + 1; i++) {
            ArrayList<String> wordsList = new ArrayList<String>(Arrays.asList(words));
            if(hasSubString(s, i, wordsList))//!!TODO: The method parameter passing will be pass by reference, thus a new wordlist must be created each time!!
                result.add(i);
        }

        return result;
    }

    private boolean hasSubString(String s, int i, ArrayList<String> wl) {
        String cs = s.substring(i, i + wordlength);
        if(wl.contains(cs)) {
            wl.remove(cs);
            if(wl.isEmpty())//!!TODO: The end condition for true mustn't be forgot!!
                return true;
            else
                return hasSubString(s, i + wordlength, wl);
        } else
            return false;
    }
}
