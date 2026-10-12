class Solution {
    public String removeDuplicates(String s, int k) {
        // [char, count]
        Stack<int[]> stack = new Stack<>();
        for(char c: s.toCharArray()){
            // if stack is not empty and the top char matches with the char c then increment the count
            if(!stack.isEmpty() && stack.peek()[0] == c){
                stack.peek()[1]++;
            }else{
                stack.push(new int[]{c, 1});
            }
            if(stack.peek()[1] == k){
                stack.pop();
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            int[] top = stack.pop();
            sb.append(String.valueOf((char)top[0]).repeat(top[1]));
        }
        return sb.reverse().toString();
    }
}