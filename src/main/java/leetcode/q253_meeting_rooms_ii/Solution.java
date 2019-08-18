package leetcode.q253_meeting_rooms_ii;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public boolean canAttendMeetings(int[][] intervals) {
        //sort the intervals by the start time small->large
        Arrays.sort(intervals, (x, y) -> x[0] - y[0]); //TODO: Note this lambda sort for Arrays.sort()

        for (int i = 0; i <= intervals.length - 2; i++) {
            if (intervals[i + 1][0] < intervals[i][1])
                return false;
        }

        return true;
    }

    /**
     * 画个区间图就看出来： 只要有interval的开始时间，小于目前最小的结束时间，就说明meeting time有overlapping 则需要增加房间；故对全部开始和结束时间先进行排序
     *
     * @param intervals
     * @return
     */
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0].length != 2)
            return 0;

        List<Integer> starts = new ArrayList<Integer>();
        List<Integer> ends = new ArrayList<Integer>();

        for (int[] i : intervals) {
            starts.add(i[0]);
            ends.add(i[1]);
        }

        Collections.sort(starts);
        Collections.sort(ends);

        int i = 0, j = 0, rooms = 0;
        while (i < starts.size() && j < ends.size()) {
            if (starts.get(i) < ends.get(j))
                rooms++;
            else
                j++;

            i++; //TODO: Note here, always move the pointer of start times after comparision
        }

        return rooms;
    }
}
