// Hussein's Binary Search Tree Edited by Christopher Blignaut 
// 17 April 2023
// Christopher Blignaut

public class BinarySearchTree<dataType extends Comparable<? super dataType>> extends BinaryTree<dataType>
{
   /**
    * Inserts an object into the root node if the tree is empty, otherwise isnerts the object into the tree 
    * @param d the object to be inserted into the tree
    */
   public void insert ( dataType d )
   {
      if (root == null)
         root = new BinaryTreeNode<dataType> (d, null, null);
      else
         insert (d, root);
   }

   /**
    *  Inserts an object into the tree
    * @param d
    * @param node
    */
   public void insert ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (d.compareTo (node.data) <= 0)
      {
         if (node.left == null)
            node.left = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.left);
      }
      else
      {
         if (node.right == null)
            node.right = new BinaryTreeNode<dataType> (d, null, null);
         else
            insert (d, node.right);
      }
   }
   
   /**
    * Finds the location of the node containing the object 
    * @param d the object that the user wishes to find in the tree
    * @return the node that the object is stored at or null if the tree is empty
    */
   public BinaryTreeNode<dataType> find ( dataType d )
   {
      if (root == null)
         return null;
      else
         return find (d, root);
   }

   /**
    * Finds the location of the node containing the object, otherwise, checks children recursivley
    * @param d the object that the user wishes to find in the tree
    * @param node the node that is currently being checked to see if it contains the datatype 
    * @return the node that the object is stored at, or null if the object is not found 
    */
   public BinaryTreeNode<dataType> find ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (d.compareTo (node.data) == 0) 
         return node;
      else if (d.compareTo (node.data) < 0)
         return (node.left == null) ? null : find (d, node.left);
      else
         return (node.right == null) ? null : find (d, node.right);
   }
   
   /**
    *  Deletes an object from the tree
    * @param d the object that the user wished to delete 
    */
   public void delete ( dataType d )
   {
      root = delete (d, root);
   }   

   /**
    * Finds the location of the node containing the object, deletes it, and changes the 
    * children so that the structure is sound, otherwise, checks children recursivley 
    * @param d the object that the user wished to delete
    * @param node the object that is currently being checked
    * @return the node which is to be deleted 
    */
   public BinaryTreeNode<dataType> delete ( dataType d, BinaryTreeNode<dataType> node )
   {
      if (node == null) return null;
      if (d.compareTo (node.data) < 0)
         node.left = delete (d, node.left);
      else if (d.compareTo (node.data) > 0)
         node.right = delete (d, node.right);
      else if (node.left != null && node.right != null )
      {
         node.data = findMin (node.right).data;
         node.right = removeMin (node.right);
      }
      else
         if (node.left != null)
            node = node.left;
         else
            node = node.right;
      return node;
   }
   
   /**
    * Finds the minimum value in the tree from the node provided
    * @param node a node in the tree that serves as the basepoint to find the smallest value related to the node 
    * @return the node that has the minimum value in the tree
    */
   public BinaryTreeNode<dataType> findMin ( BinaryTreeNode<dataType> node )
   {
      if (node != null)
         while (node.left != null)
            node = node.left;
      return node;
   }

   /**
    * Finds and removes the node with the smallest value from the node given in the parameter
    * @param node the node in the tree that will serve as the basepoint to find the smallest value related to the node 
    * @return the node that is removed
    */
   public BinaryTreeNode<dataType> removeMin ( BinaryTreeNode<dataType> node )
   {
      if (node == null)
         return null;
      else if (node.left != null)
      {
         node.left = removeMin (node.left);
         return node;
      }
      else
         return node.right;
   }
   
}
