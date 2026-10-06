class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        int rows = times.length;
        int cols = times[0].length;
        Map<Integer, List<Node>> graph = new HashMap<>();
        for(int i = 0; i < rows; i++){
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            graph.computeIfAbsent(u, key -> new ArrayList<>()).add(new Node(v, w));
        }
        // n+1 because nodes start from 1 not, 0. The index would be graph nodes and value would be the best minimum distance from the node i. 
        int[] distance = new int[n+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        // since k is the ith index from where signal is being sent 
        distance[k] = 0;
        // use minHeap to 
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        //{node, totalTimeFromStart}
        pq.offer(new int[]{k, 0});
        while(!pq.isEmpty()){
            int[] currentNode = pq.poll();
            int node = currentNode[0];
            int weight = currentNode[1];
            if(weight > distance[node]){
                continue;
            }
            // examn outgoing edges from current node
            for(Node edge : graph.getOrDefault(node, Collections.emptyList())){
                int nextNode = edge.v;
                int newWeight = edge.w + weight;
                if(newWeight < distance[nextNode]){
                    distance[nextNode] = newWeight;
                    pq.offer(new int[] {nextNode, newWeight});
                }
            }
        }
        int answer = 0;
        for(int node = 1; node <= n; node++){
            if(distance[node] == Integer.MAX_VALUE){
                return -1;
            }
            answer = Math.max(answer, distance[node]);
        }
        return answer;
    }
}
class Node{
    int v;
    int w;
    public Node(int v, int w){
        this.v = v;
        this.w = w;
    }
}
