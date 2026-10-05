class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /*
            build a graph: a course is a pre-rep for list of course
            pre : List of course
            0 : [1,2]
            2 : [3]
        */
        List<List<Integer>> graph = new ArrayList<>();
        for(int i = 0; i < numCourses; i++){
            graph.add(new ArrayList());
        }
        int[] indegree = new int[numCourses];
        for(int[] prereq : prerequisites){
            int course = prereq[0];
            int pre = prereq[1];
            graph.get(pre).add(course);
            indegree[course]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        int completed = 0;
        while(!queue.isEmpty()){
            int course = queue.poll();
            completed++;
            for(int nextCourse : graph.get(course)){
                indegree[nextCourse]--;
                if(indegree[nextCourse] == 0){
                    queue.offer(nextCourse);
                }
            }
        }
        return completed == numCourses;
    }
}
