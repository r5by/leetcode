package leetcode.q200_islands;

class Solution {

    private int[][] directions = new int[][] { {0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;

        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j]== '0') continue; //TODO: Note here, don't forget it's a char array, not integer... if grid[][] == 0 is wrong!

                count++;
                dfs(grid, i, j);
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        if(r < 0 || r >= grid.length
                || c < 0 || c >= grid[0].length
                || grid[r][c] == '0')
            return;

        grid[r][c] = '0';
        for(int[] d : directions ) {
            dfs(grid, r + d[0], c + d[1]);
        }
    }
//    private boolean[][] visited;
//    public int numIslands(char[][] grid) {
//        if(grid == null || grid.length < 1)
//            throw new IllegalArgumentException("input e");
//
//        int rows = grid.length;
//        int cols = grid[0].length;
//
//        int count = 0;
//        visited = new boolean[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                if(grid[i][j] == '0' || visited[i][j]) continue;
//
//                count++;
//                explore(i, j, visited, grid);
//            }
//        }
//
//        return count;
//    }
//
//    private void explore(final int row, final int col, boolean[][] visited, final char[][] grid) {
//        int rows = grid.length;
//        int cols = grid[0].length;
//
//        if(visited[row][col] || grid[row][col] == '0') return;
//        visited[row][col] = true;
//
//        //visit surroundings
//        if(row + 1< rows) explore(row + 1, col, visited, grid);
//
//        if(row > 0) explore(row - 1, col, visited, grid);
//
//        if(col + 1 < cols) explore(row, col + 1, visited, grid);
//
//        if(col > 0) explore(row, col - 1, visited, grid);
//    }
}