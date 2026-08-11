class Solution {
    public int lengthOfLongestSubstring(String s) {
        // sliding window
        HashSet<Character> set = new HashSet<>();
        int len = s.length();
        int left = 0;
        int maxLen = 0;
        for(int right = 0; right < len ; right++){
            char c = s.charAt(right);
            while(set.contains(c)){
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            maxLen = Math.max(maxLen, (right - left)+ 1);
        }
        return maxLen;
    }
}
