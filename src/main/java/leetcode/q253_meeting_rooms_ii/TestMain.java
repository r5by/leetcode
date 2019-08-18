package leetcode.q253_meeting_rooms_ii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals = new int[][] {
                {15, 20},{5, 10},{0, 30}
        };
        boolean can = s.canAttendMeetings(intervals);
        logger.debug("");
    }
}
