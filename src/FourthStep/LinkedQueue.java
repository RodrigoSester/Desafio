package FourthStep;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class LinkedQueue<E> implements Queue<E> {

    private Node<E> front;
    private Node<E> rear;

    public LinkedQueue() {
        front = null;
        rear = null;
    }

    @Override
    public boolean isEmpty() {
        return front == null;
    }

    @Override
    public boolean isFull() {
        // Linked Queues don't have a fixed size, so technically they are never full.
        return false;
    }

    @Override
    public int numElements() {
        int count = 0;
        Node<E> current = front;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public void enqueue(E element) throws BufferOverflowException {
        if (isFull()) {
            throw new BufferOverflowException();
        }
        Node<E> newNode = new Node<>(element);
        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    @Override
    public E dequeue() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        E data = front.getData();
        front = front.getNext();
        
        if (front == null) {
            rear = null;
        }
        return data;
    }

    @Override
    public E front() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return front.getData();
    }    
    
    @Override
    public E back() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }

        Node<E> current = front;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        return current.getData();
    }

}
