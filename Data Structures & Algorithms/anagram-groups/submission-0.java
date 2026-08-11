class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<String, List<String>> anagramMap = new HashMap<>();
        for(String str: strs){
            // 1. Add freq for each string in count[]
            int[] count = new int[26];
            for(char c : str.toCharArray()){
                count[c - 'a']++;
            }
            // 2. generate the hashkey for each string
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < 26 ; i++){
                sb.append('#');
                sb.append(count[i]);
                // "a#bc#d"
            }
            String key = sb.toString();
            anagramMap.computeIfAbsent(key, k->new ArrayList<>()).add(str);
        }
        return new ArrayList<>(anagramMap.values());
    }
}
