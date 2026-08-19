class Solution {
    public String minRemoveToMakeValid(String s) {
        
        int leftCount = 0;
        int rightCount = 0;

        
        Stack<Character> stack = new Stack<>();

        
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            
            if (currentChar == '(') {
                leftCount++;
            }
            
            if (currentChar == ')') {
                rightCount++;
            }

            
            if (rightCount > leftCount) {
                rightCount--; 
                continue;     
            } else {
                stack.push(currentChar); 
            }
        }

        
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            char currentChar = stack.pop();
            
            if (leftCount > rightCount && currentChar == '(') {
                leftCount--; 
                
            } else {
                result.append(currentChar); 
            }
        }

    
        return result.reverse().toString();
    }
}