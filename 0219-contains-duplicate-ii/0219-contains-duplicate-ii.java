class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        int left = 0;
        int right = k;
       // int flag = 0;
       int i =0;
       if(nums.length < 2)
        return false;
       while(i<=k && i<nums.length){
            if(set.contains(nums[i])){
                return true;
            }
            else{
                System.out.println(nums[i]);
                set.add(nums[i]);
                i++;
            }
       }
       right = i;
       while(right < nums.length){
            set.remove(nums[left]);
            if(set.contains(nums[right])){
                return true;
            }
            set.add(nums[right]);
            left++;
            right++;
       }
       return false;
       }   
    }