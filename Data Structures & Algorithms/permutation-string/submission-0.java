class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        Map<Character, Integer> s1Count = new HashMap<>();
        Map<Character, Integer> windowCount = new HashMap<>();

        // Frequency map for s1
        for (char c : s1.toCharArray()) {
            s1Count.put(c, s1Count.getOrDefault(c, 0) + 1);
        }

        int left = 0;

        for (int right = 0; right < s2.length(); right++) {
            char rightChar = s2.charAt(right);

            windowCount.put(
                rightChar,
                windowCount.getOrDefault(rightChar, 0) + 1
            );

            // Keep window size equal to s1.length()
            if (right - left + 1 > s1.length()) {
                char leftChar = s2.charAt(left);

                windowCount.put(
                    leftChar,
                    windowCount.get(leftChar) - 1
                );

                if (windowCount.get(leftChar) == 0) {
                    windowCount.remove(leftChar);
                }

                left++;
            }

            // Same frequencies => permutation found
            if (windowCount.equals(s1Count)) {
                return true;
            }
        }

        return false;
    }
}