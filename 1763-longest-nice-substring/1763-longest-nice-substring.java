class Solution {
    public String longestNiceSubstring(String s) {
        if (s.length() < 2) {
            return "";
        }
        
        char[] chars = s.toCharArray();
        boolean[] present = new boolean[128];
        for (char c : chars) {
            present[c] = true;
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char counterpart = Character.isLowerCase(c) ? Character.toUpperCase(c) : Character.toLowerCase(c);
            
        
            if (!present[counterpart]) {
                String left = longestNiceSubstring(s.substring(0, i));
                String right = longestNiceSubstring(s.substring(i + 1));
                
                return left.length() >= right.length() ? left : right;
            }
        }
        



        return s;
    }
}