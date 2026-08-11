class Solution {
    public String longestCommonPrefix(String[] strs) {
        String prefix = strs[0];
        for(int i=0; i < prefix.length(); i++){
            char expected = prefix.charAt(i);
            for(int j = 1; j < strs.length; j++){
                // current string ended OR character doesn't match
                if (i >= strs[j].length() ||
                    strs[j].charAt(i) != expected) {

                    return prefix.substring(0, i);
                }
            }
        }
        return prefix;
    }
}