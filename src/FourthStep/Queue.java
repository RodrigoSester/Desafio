package FourthStep;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public interface Queue<E> {
	public boolean isEmpty();
	public boolean isFull();
	public int numElements();
	public void enqueue(E element) throws BufferOverflowException;
	public E dequeue() throws BufferUnderflowException;
	public E front() throws BufferUnderflowException;
	public E back() throws BufferUnderflowException;
}
