Part I:  Accessing a Node's Parent 
-----------------------------------

parent() is implemented in SibTreeNde.java

implementation:

check if "this" is a valid node
  if "this" is root of myTree, return new SibTreeNode, which is an invalid TreeNode
  else return parent
else throw InvalidNodeException

Part II:  Inserting New Children 
---------------------------------

insertChild() is implemented in SibTreeNode.java

Implementation: 

if myTree is not null, then
  instantiate and declare a child with the item and myTree
  assign chil.parent to "this" node
  instantiate and declare a node to traverse the tree

   if c < 1 or the firstChild is null, then assign firstChild to child
   and assign child.nextSibling to traverse node.
   else 
     create a nex SibTreeNode and assign it to firstChild.nextSibling
     find the nextSibling of the cth node by using a for loop from i = 2 to 
     i < c and next is not null, set traverse to traverse.nextSibling and next 
     to next.Sibling

     Then assign traver.nextSibling to child and chil.nextSibling to next
   
   increment the size of myTree

else throw an InvalidNodeException

   


BONUS Part III:  Removing a Leaf 
---------------------------------

removeLeaf() is implemented in SibTreeNode.java

Implementation: 

if "this" is a valid node, then
  if the node is leaf
     set myTree.root to null if "this" is the myTree.root
     else 
       create a SibTreeNode called isMyParent to find myParent
       if the firstChild of isMyParent is "this", then set the firtsChild of isMyParent to 
        this.nextSibling
       else 
          create a SibTreeNode prevSib to find the previous sibling
          while prevSib.nextSibling is not "this", do prevSib = prevSib.nextSibling
          assign prevSib.nextSibling to nextSibling

   set valid = false and decrement the size of myTree
else
  throw an invalidNodeException
   





