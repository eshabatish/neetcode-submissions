class Solution {
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        int elapsedMins = 0;
        int freshFruits = 0;
        // will hold the co-ordinates of cell where we have a rotten fruit
        Queue<int[]> queue = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1){
                    freshFruits++;
                }
                if(grid[i][j] == 2){
                    queue.offer(new int[]{i,j});
                }
            }
        }
        // Multi-Source BFS
        while(!queue.isEmpty() && freshFruits > 0){
            // while loop is every min
            int size = queue.size();
            for(int k = 0; k < size; k++){
                int[] coordinates = queue.poll();
                int iCoordinate = coordinates[0];
                int jCoordinate = coordinates[1];
                // while processing a rotten fruit at the current min, we need to check all 4 directional 
                freshFruits -= processFruits(grid,iCoordinate,jCoordinate, queue);
            }
            elapsedMins++;
        }
        return freshFruits == 0? elapsedMins : -1;
    }
    private int processFruits(int[][] grid,int iCoordinate,int jCoordinate, Queue<int[]> queue){
        int newRottenFruits = 0;

        int[][] directions = new int[][] {{1,0}, {-1,0}, {0, 1}, {0, -1}};
        for(int[] direction : directions){
            int r = iCoordinate + direction[0];
            int c = jCoordinate + direction[1];
            if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] != 1){
                continue;
            }
            grid[r][c] = 2;
            queue.offer(new int[]{r, c});
            newRottenFruits++;
        }
        return newRottenFruits;
    }
}
