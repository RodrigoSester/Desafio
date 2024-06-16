package FourthStep;

public class Node<T> {
   private T data;    
   private Node<T> nextNode;
   private Node<T> previousNode;

   public Node( T element ) { 
      this( element, null ); 
   }

   public Node( T element, Node<T> node ) {
      data = element;    
      nextNode = node;  
   }

   public T getData()  { return data; }
   
   public void setData (T element){
       data = element;
   }

   public Node<T> getNext() { return nextNode; }
   
   public void setNext(Node<T> n) { 
      nextNode = n; 
   }

   public Node<T> getPrevious() { return previousNode; }
   
   public void setPrevious(Node<T> n) { 
      previousNode = n; 
   }
} 
