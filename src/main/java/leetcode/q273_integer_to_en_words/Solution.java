package leetcode.q273_integer_to_en_words;

import java.util.HashMap;

class Solution {
    HashMap<Integer, String> map = new HashMap<Integer, String>(){{
        put(0, "Zero");
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7, "Seven");
        put(8, "Eight");
        put(9, "Nine");
        put(10, "Ten");
        put(11, "Eleven");
        put(12, "Twelve");
        put(13, "Thirteen");
        put(14, "Fourteen");
        put(15, "Fifteen");
        put(16, "Sixteen");
        put(17, "Seventeen");
        put(18, "Eighteen");
        put(19, "Nineteen");
        put(20, "Twenty");
        put(30, "Thirty");
        put(40, "Forty");
        put(50, "Fifty");
        put(60, "Sixty");
        put(70, "Seventy");
        put(80, "Eighty");
        put(90, "Ninety");
    }};

    public String numberToWords(int num) {
        String res = "";
        int b = 0, m = 0, t = 0;
        if(num >= 1000000000) {
            b = num / 1000000000;
            res += (convert(b) + " Billion ");
        }

        if (num >= 1000000) {
            m = (num - b * 1000000000) / 1000000;
            res += (convert(m) + " Million ");
        }

        if (num >= 1000) {
            t = (num - b * 1000000000 - m * 1000000) / 1000;
            res += (convert(t) + " Thousand ");
        }

        int remaining = num - b * 1000000000 - m * 1000000 - t * 1000;
        res += convert(remaining);

        return res;
    }

    /* Convert number less than 1,000 */
    private String convert(int num) {
        String res = "";
        int h = num/100;
        int t = (num - h * 100)/10;
        int s = num - h * 100 - t * 10;

        if(h > 0) res += (map.get(h) + " hundred ");
        if(t > 0) res += (map.get(t * 10) + " ");
        if(s > 0) res += map.get(s);
        return res;
    }

}
