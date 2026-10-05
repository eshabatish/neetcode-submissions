class Solution {
    // Returns whether all courses can be completed.
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Create an adjacency list: one list of dependents per course.
        List<List<Integer>> graph = new ArrayList<>();

        // Course IDs go from 0 through numCourses - 1.
        for (int i = 0; i < numCourses; i++) {
            // Initially, each course has an empty dependent list.
            graph.add(new ArrayList<>());
        }

        // indegree[i] = number of prerequisites course i still needs.
        // All entries initially contain 0.
        int[] indegree = new int[numCourses];

        // Read each prerequisite pair.
        for (int[] prereq : prerequisites) {
            int course = prereq[0]; // Course we want to take.
            int pre = prereq[1];    // Course we must finish first.

            // Create the edge: pre → course.
            // Finishing pre will help unlock course.
            graph.get(pre).add(course);

            // Course has one more prerequisite requirement.
            indegree[course]++;
        }

        // FIFO queue containing courses ready to be taken.
        Queue<Integer> queue = new LinkedList<>();

        // Check every course for eligibility.
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                // No prerequisites needed: this course is ready.
                queue.offer(i);
            }
        }

        // Count how many courses we successfully take.
        int completed = 0;

        // Keep going while there is a ready course.
        while (!queue.isEmpty()) {
            // Remove the next ready course.
            int course = queue.poll();

            // Consider that course completed.
            completed++;

            // Visit every course that depends on the completed course.
            for (int nextCourse : graph.get(course)) {

                // One of nextCourse's prerequisites has now been completed.
                indegree[nextCourse]--;

                if (indegree[nextCourse] == 0) {
                    // All its prerequisites are completed.
                    // It is now ready to be taken.
                    queue.offer(nextCourse);
                }
            }
        }

        // True only if we managed to complete every course.
        return completed == numCourses;
    }
}