package FourthStep;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class LinkedStack<E> implements Stack<E> {

    private Node<E> top;

    public LinkedStack() {
        top = null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public boolean isFull() {
        // Linked Stacks don't have a fixed size, so technically they are never full.
        return false;
    }

    @Override
    public int numElements() {
        int count = 0;
        Node<E> current = top;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    @Override
    public void push(E element) throws BufferOverflowException {
        // Not technically full with a Linked Stack, but throw for consistency
        if (isFull()) {
            throw new BufferOverflowException();
        }
        Node<E> newNode = new Node<>(element);
        newNode.setNext(top);
        top = newNode;
    }

    @Override
    public E pop() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        E data = top.getData();
        top = top.getNext(); // Update top to point to the next node
        return data;
    }

    @Override
    public E top() throws BufferUnderflowException {
        if (isEmpty()) {
            throw new BufferUnderflowException();
        }
        return top.getData();
    }
}
