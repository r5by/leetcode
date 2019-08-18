package leetcode.q943_find_the_shortest_superstring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();

//        String[] input = new String[]{"alex","loves","leetcode"};

//        String[] input = new String[]{"catg","ctaagt","gcta","ttca","atgcatc"};
//        int lenExpect = "gctaagttcatgcatc".length();

        String[] input = new String[]{"lugeuklyt","thwokzob","rilsthwokz","onkrilsthw","kzobvtr","krilsthwo"};
        int lenExpect = "onkrilsthwokzobvtrlugeuklyt".length();

        String superString = s.shortestSuperstring(input);
        int lenRes = superString.length();
        for(String str: input){
            if(!superString.contains(str))
                logger.debug("error");
        }

        logger.debug("");
    }
}
