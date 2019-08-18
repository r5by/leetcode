package algs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author ruby_
 * @create 2019-08-09-12:30
 */

public class TSP {

    public static void main(String[] args) {
        int[][] adjMatrix = new int[][]{
                {0, 3, 6, 7},
                {5, 0, 2, 3},
                {6, 4, 0, 2},
                {3, 7, 5, 0}
        };

        TSPSolution solver = new TSP().solve(adjMatrix);
        int shortestPathTSP = solver._dist;

        //print out the shortest path
        Deque<Integer> pathStack = new ArrayDeque<>();
        int[][] path = solver._path;
        int s = (1 << (adjMatrix.length - 1)) - 1;
        int k = 0;

        while (s != 0) {
            pathStack.push(k);
            k = path[k][s];
            s ^= 1 << (k - 1);
        }
        pathStack.push(k); //don't forget the first city leaving from 0 move to

        System.out.println("Shortest path distance: " + shortestPathTSP);

        System.out.println("The shortest path is (starting from 0): ");
        while (!pathStack.isEmpty()) {
            int p = pathStack.pop();
            if(p != 0) System.out.print(p + " --> ");
            else System.out.print(p);
        }
    }

    public TSPSolution solve(int[][] c) {
        int n = c.length; //num of cities

        int x = 1 << (n - 1);

        int[][] dp = new int[n][1 << (n - 1)]; //dp[i][S] is the state of dp, which represents the shortest distances starting from city 0, traveling all cities in subset S, then reach city i.
        int[][] path = new int[n][1 << (n - 1)]; //path[i][S] saves the city k to form the min path end at i passing through subset S
        int cols = dp[0].length;

        //TSP fill by column -> row
        for (int S = 0; S < cols; S++) {//subset of cities, e.g. {0011} -> city 1 + city 2
            for (int i = 0; i < n; i++) {//city id (stopped city)

                if (S == 0) {//init
                    Arrays.fill(dp[i], Integer.MAX_VALUE);
                    dp[i][S] = c[0][i];
                } else {
                    for (int k = 1; k < n; k++) {
                        int selected = 1 << (k - 1); //transfer city that is being selected to its position in '0011' format, e.g. city 1 => 0001, 0001 & 0011 == 1, means city 1 is within the current state
                        if ((selected & S) == 0) continue; //skip city not in the subset
                        int min = dp[k][selected ^ S] + c[k][i];
                        if (min < dp[i][S]) {
                            dp[i][S] = min;

                            //record the last passed city that is selected for building shortest path
                            path[i][S] = k;
                        }
                    }
                }
            }
        }
        return new TSPSolution(dp[0][cols - 1], path);
    }

    private class TSPSolution {//wrap up solution returns
        private int _dist;
        private int[][] _path;

        TSPSolution(int dist, int[][] path) {
            _dist = dist;
            _path = path;
        }
    }
}
