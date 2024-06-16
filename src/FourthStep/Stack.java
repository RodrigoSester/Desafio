package FourthStep;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public interface Stack<E> {
	public boolean isEmpty();
	public boolean isFull();
	public int numElements();
	public void push(E element) throws BufferOverflowException;
	public E pop() throws BufferUnderflowException;
	public E top() throws BufferUnderflowException;
}
