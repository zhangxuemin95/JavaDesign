
public class Solution {
    public static void main(String[] args) {
        new Solution().sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});

    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 1) return new TreeNode(nums[0], null, null);
        int i = 0, j = nums.length - 1;
        int m = (i + j) / 2;
        TreeNode root = new TreeNode(nums[m], null, null);
        buildChild(root, m, i, j, nums);
        return root;
    }

    public void buildChild(TreeNode node, int identity, int r, int l, int[] nums)
    {
        //构造左子树
        if(identity != r)
        {
            if(identity - 1 == r)
            {
                node.left = new TreeNode(nums[r], null, null);
            }
            else
            {
                int m = (identity + r) / 2;
                node.left = new TreeNode(nums[m], null, null);
                buildChild(node.left, m, r, identity - 1, nums);
            }
        }
        {
            if(identity != l)
            {
                if(identity + 1 == l)
                {
                    node.right = new TreeNode(nums[l], null, null);
                }
                else
                {
                    int n = (l + identity) / 2 + 1;
                    node.right = new TreeNode(nums[n], null, null);
                    buildChild(node.right, n, identity + 1, l, nums);
                }
            }
        }
    }
}



 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }