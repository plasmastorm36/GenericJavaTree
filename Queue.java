/*
 * Author: Noah Rouse
 * Email: plasmastormfire@gmail.com
 * Description: Uses a linked structure to ensure a first in first out structure
 */
public final class Queue<E> {
   private static final class Node<E> {
      /*
       * Node structure used to make the queue
       * One node holds an element, and a pointer to the next node
       */
      private final E e;
      private Node<E> next = null;
      private Node (final E element) {
         e = element;
      }
   }

   private Node<E> head = null; // points to the first point in queue
   private Node<E> tail = null; // points to last point in queue
   private static int size = 0;

   public E enqueue (final E element) {
      /*
       * Adds an element to the end of the queue
       * Parameters:
       * element: element being added
       */
      if (size == 0) {
         head = new Node<E>(element);
         tail = head;
         size++;
         return element;
      }

      tail.next = new Node<E>(element);
      tail = tail.next;
      size++;
      return element;
   }

   public E dequeue () {
      /*
       * removes an element from the first point in the queue
       * Parameters:
       * None
       */
      if (size == 0) {
         return null;
      }

      final E removed = head.e;
      head = head.next;
      size--;
      return removed;
   }

   public E first () {
      /*
       * returns first item in the queue but does not remove it
       * Parameters:
       * None
       */
      if (size == 0) {
         return null;
      }
      return head.e;
   }

   public int size () {
      /*
       * returns the size of the queue
       * Parameters:
       * None
       */
      return size;
   }

   public boolean isEmpty () {
      /*
       * returns true if the queue is empty
       * Parameters:
       * None
       */
      return size == 0;
   }
}
