class Solution {
    public int subarraySum(int[] nums, int k) {
    /* Step 1: Define a HashMap where key is the running sum seen and   value would be how many time it has seen
    */
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);
    int count = 0, sum = 0;
    for(int n : nums){
       sum += n ;
       if(map.containsKey(sum - k)){
        count += map.get(sum - k);
       }
       map.put(sum, map.getOrDefault(sum, 0)+1);
    }
    return count;
    }
    
}