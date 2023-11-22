// Hussein's Binary Tree
// 26 March 2017
// Hussein Suleman

public class BinaryTree<dataType>
{
   BinaryTreeNode<dataType> root;
   
   /**
    * A default constructor to create an empty binary tree 
    */
   public BinaryTree ()
   {
      root = null;
   }
   
   /**
    * Gets the height of the tree
    * @return the height of the tree
    */
   public int getHeight ()
   {
      return getHeight (root);
   }   

   /**
    * Gets the height of the tree from the node given in the parameter or -1 if the node is empty
    * @param node the node from which the height of the tree will be measured 
    * @return the height of the tree from that node, in the form of an integer 
    */
   public int getHeight ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return -1;
      else
         return 1 + Math.max (getHeight (node.getLeft ()), getHeight (node.getRight ()));
   }
   
   /**
    * Gets the number of nodes in the tree 
    * @return the number of nodes in the tree
    */
   public int getSize ()
   {
      return getSize (root);
   }   

   /**
    * Gets the number of nodes in the tree recursivley from the node in the parameter  
    * @param node the node from which the counting of the nodes must start 
    * @return the number of nodes in the tree
    */
   public int getSize ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return 0;
      else
         return 1 + getSize (node.getLeft ()) + getSize (node.getRight ());
   }
   
   /**
    * Prints out the specified node's data 
    * @param node the node which must have it's data printed out 
    */
   public void visit ( BinaryTreeNode<dataType> node )
   {
      System.out.println (node.data);
   }
   
   /**
    * Basecase of a recursive method to organize the tree 
    */
   public void preOrder ()
   {
      preOrder (root);
   }
   
   /**
    * Recursivley prints out the tree in order 
    * @param node the node which must print out its data at that point 
    */
   public void preOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         visit (node);
         preOrder (node.getLeft ());
         preOrder (node.getRight ());
      }   
   }

   /**
    * Uses recursion to print out the data of the tree in decending order ofthe objects
    */
   public void postOrder ()
   {
      postOrder (root);
   }

   /**
    * Prints out the data from each node in the tree in decending order of the objects 
    * @param node the node which is currently having its data printed out
    */
   public void postOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         postOrder (node.getLeft ());
         postOrder (node.getRight ());
         visit (node);
      }   
   }

   /**
    * Uses recursion to print out the data of the tree in ascending order ofthe objects
    */
   public void inOrder ()
   {
      inOrder (root);
   }
   
   /**
    * Prints out the data from each node in the tree in ascending order of the objects 
    * @param node the node which is currently having its data printed out
    */
   public void inOrder ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
      {
         inOrder (node.getLeft ());
         visit (node);
         inOrder (node.getRight ());
      }   
   }

   /**
    * Prints out the data from each node in the tree in level order 
    */
   public void levelOrder ()
   {
      if (root == null)
         return;
      BTQueue<dataType> q = new BTQueue<dataType> ();
      q.enQueue (root);
      BinaryTreeNode<dataType> node;
      while ((node = q.getNext ()) != null)
      {
         visit (node);
         if (node.getLeft () != null)
            q.enQueue (node.getLeft ());
         if (node.getRight () != null)
            q.enQueue (node.getRight ());
      }
   }
}
