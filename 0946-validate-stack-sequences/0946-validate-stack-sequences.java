class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>(); 
        
        int j = 0; 
        
        for(int val : pushed){
            st.push(val); 
            while(!st.isEmpty() && st.peek() == popped[j]){ // if st.peek() values equal to popped[j];
                st.pop(); 
                j++; 
            }
        }
        return st.isEmpty(); 
    }
}