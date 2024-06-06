/*
 * Author: Noah Rouse
 * Email: plasmastormfire@gmail.com
 * Description: A linked based structure using an arrayList as holder for the node pointers
 * follows the tree ADT
 */
import java.util.ArrayList;
public final class Tree<E> implements Iterable<E> {
   public static class Node<E> {
      /*
       * Node class contains exactly one element, an ArrayList that points to the child 
       * nodes from the tree, and a pointer to it's parent node.
       */
      private final E element;
      private final ArrayList<Node<E>> children = new ArrayList<Node<E>>();
      private Node<E> parent = null;
      private Node (final E element) {
         this.element = element;
      }

      public E getElement () {
         /*
          * restores element stored at this node in the tree
          * Parameters:
          * None
          */
         return element;
      }
   }

   private static class Iterator<E> implements java.util.Iterator<E> {
      private final ArrayList<Node<E>> list;
      private int i = -1; // index
      public Iterator (final ArrayList<Node<E>> treeArrayList) {
         list = treeArrayList;
      }

      public E next () {
         /*
          * returns the next item in the collection for the iterator
          * Parameters:
          * None
          */
         i++;
         return list.get(i).element;
      }

      public boolean hasNext () {
         /*
          * returns true if there is anything left in the collection
          * Parameters:
          * None
          */
         return i >= list.size();
      }
   }

   private int size = 0;
   private Node<E> root = null;
   private Node<E> p = null;

   public Node<E> root () {
      /*
       * returns the position of the root (the node that stores it)
       * Parameters:
       * None
       */
      return root;
   }

   public E addRoot (final E element) throws Exception {
      /*
       * sets the root if not already made
       * Parameters:
       * element: element being set to the root
       */
      if (root == null) {
         root = new Node<E>(element);
         p = root;
         size++;
         return element;
      }
      throw new Exception("Root already exists");
   }

   public E setPosition (final Node<E> node) throws Exception {
      /*
       * sets position to the tree in a node
       * Parameters:
       * node: new position node in the tree to replace the current position node
       */
      if (p == null) {
         throw new Exception("Invalid Argument");
      }

      p = node;
      return p.element;
   }

   public E addChild (final E element) {
      /*
       * adds a child to the current position
       * Parameters:
       * element: element being added in the game
       */
      final Node<E> newNode = new Node<E>(element);
      p.children.add(newNode);
      size++;
      return element;
   }

   public void addChildren (final E[] elements) {
      /*
       * Adds all children to current position node
       * Parameters:
       * elements: an array of the elements being added
       */
      size += elements.length;
      for (final E i: elements) {
         final Node<E> newNode = new Node<E>(i);
         p.children.add(newNode);
      }
   }

   public Node<E> parent () {
      /*
       * returns the parent node of the position given
       * returns null if the node is the root
       * Parameters:
       * p: node representing the position on the tree
       */
      if (p == null) {
         return null;
      }
      return p.parent;
   }

   public ArrayList<Node<E>> children () {
      /*
       * returns an ArrayList of the children node of the position p
       * Parameters:
       * p: node representing the position on the tree
       */
      return p.children;
   }

   public int numChildren () {
      /*
       * returns the number of children p has
       * Parameters:
       * p: node representing the position on the tree
       */
      if (p == null) {
         return -1;
      }

      return p.children.size();
   }

   public boolean isInternal () {
      /*
      * returns true if the node has children
      * assumes position node is not null
      * Parameters:
      * p: position node for the tree
      */
      return numChildren() > 0;
   }

   public boolean isExternal () {
      /*
      * returns true if position p has no children
      * Parameters:
      * p: position node for Tree
      */
      return numChildren() == 0;
   }

   public boolean isRoot () {
      /*
       * returns true if position node is the root
       * Parameters:
       * p: position node of the tree
       */
      if (size == 0) {
         return true;
      }

      return p.parent == null;
   }

   public int size () {
      /*
       * returns size of tree
       * Parameters:
       * None
       */
      return size;       
   }

   public boolean isEmpty () {
      /*
       * returns true if Tree is empty
       * Parameters:
       * None
       */
      return size == 0;
   }

   public ArrayList<Node<E>> toArrayList () {
      /*
       * returns an ArrayList of all positions in the tree
       * sets p to the last node in the tree
       * Parameters:
       * p: position node of the tree
       */
      if (root == null) {
         return new ArrayList<Node<E>>();
      }
      final ArrayList<Node<E>> positions = new ArrayList<Node<E>>();
      final Queue<Node<E>> q = new Queue<Node<E>>();
      q.enqueue(root);
      while(!q.isEmpty()) {
         p = q.dequeue();
         positions.add(p);
         for (final Node<E> i: p.children) {
            q.enqueue(i);
         }
      }
      return positions;
   }

   public Iterator<E> iterator () {
      /*
       * returns an iterator for the tree
       * Parameters:
       * None
       */
      return new Iterator<E>(toArrayList());
   }

   public ArrayList<Node<E>> positions () {
      /*
       * returns an ArrayList of all positions
       */
      return toArrayList();
   }
}
