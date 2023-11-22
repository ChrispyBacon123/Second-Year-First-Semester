// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTreeNode<dataType>
{
   dataType data;
   BinaryTreeNode<dataType> left;
   BinaryTreeNode<dataType> right;
   
   /**
    * A constructor to create the BinaryTreeNode object 
    * @param d the object to be stored in the node 
    * @param l the BinaryTreeNode object that will be this BinaryTreeNode's left node 
    * @param r the BinaryTreeNode object that will be this BinaryTreeNode's right node 
    */
   public BinaryTreeNode ( dataType d, BinaryTreeNode<dataType> l, BinaryTreeNode<dataType> r )
   {
      data = d;
      left = l;
      right = r;
   }

   /**
    * Gets the BinaryTreeNode object that is this BinaryTreeNode's left node
    * @return the BinaryTreeNode object that is this BinaryTreeNode's left node
    */
   BinaryTreeNode<dataType> getLeft () { return left; }

   /**
    * Gets the BinaryTreeNode object that is this BinaryTreeNode's right node
    * @return the BinaryTreeNode object that is this BinaryTreeNode's right node
    */
   BinaryTreeNode<dataType> getRight () { return right; }
}
