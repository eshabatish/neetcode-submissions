class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList<>());
        }
        int[] indegree = new int[numCourses];
        int[] order = new int[numCourses];
        int index = 0;

        for(int[] preq : prerequisites){
            int course = preq[0];
            int pre = preq[1];
            graph.get(pre).add(course);
            indegree[course]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        while(!queue.isEmpty()){
            int course = queue.poll();
            order[index++] = course;
            for(int c : graph.get(course)){
                indegree[c]--;
                if(indegree[c] == 0){
                    queue.offer(c);
                }
            }
        }
        return index == numCourses? order : new int[0];
    }
}
