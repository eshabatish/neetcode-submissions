class Solution {
    private static int[][] DIRECTIONS = {
        {0,1},
        {1,0},
        {-1,0},
        {0,-1}
    };
    public int longestIncreasingPath(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int longestPath = 0;
        // it will keep longest path seen from this cell.
        int[][] memo = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                longestPath = Math.max(longestPath, dfs(matrix, i, j, memo));
            }
        }
        return longestPath;
    }
    private int dfs(int[][] matrix, int i, int j, int[][] memo){
        int n = matrix.length;
        int m = matrix[0].length;
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        int best = 1;
        
        
        for(int[] direction : DIRECTIONS){
            int rowNext = i + direction[0];
            int colNext = j + direction[1];
            if(rowNext < 0 || rowNext >= matrix.length ||
                colNext < 0 || colNext >= matrix[0].length
                || matrix[rowNext][colNext] <= matrix[i][j]){
                    continue;

            }
            best = Math.max(best, 1 + dfs(matrix,rowNext, colNext, memo));
        }
        memo[i][j] = best;
        return best;
    }
}
