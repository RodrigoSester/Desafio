package ThirdStep;

import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

public class StaticList<E> implements List<E> {
	private E[] elements;
	private int numElements;

	public StaticList(int maxSize) {
		elements = (E[])new Object[maxSize];
		numElements = 0;
	}

	public int numElements() {
		return numElements;
	}

	public boolean isEmpty() {
		return numElements == 0;
	}

	public boolean isFull() {
		return numElements == elements.length;
	}

	public void insert(E element, int pos) throws BufferOverflowException, IndexOutOfBoundsException{
		// verifica se há espaço na lista
		if (isFull())
			throw new BufferOverflowException();

		// verifica se a posição é válida
		if (pos < 0  ||  pos > numElements)
			throw new IndexOutOfBoundsException();
		
		// desloca para a direita os elementos necessários,
		// abrindo espaço para o novo
		for (int i = numElements-1; i >= pos; i--)
			elements[i+1] = elements[i];
		
		// armazena o novo elemento e ajusta o total
		elements[pos] = element;
		numElements++;
	}

	public E remove(int pos) throws BufferUnderflowException, IndexOutOfBoundsException{
		if(isEmpty())
			throw new BufferUnderflowException();
		
		// verifica se a posição é válida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();
		
		// guarda uma refer�ncia temporária ao elemento removido
		E element = elements[pos];
		
		// desloca para a esquerda os elementos necessários,
		// sobrescrevendo a posição do que está sendo removido
		for (int i = pos; i < numElements-1; i++)
			elements[i] = elements[i+1];
		
		// define para null a posição antes ocupada pelo último,
		// para que a coleta de lixo possa atuar, e ajusta o total
		elements[numElements-1] = null;
		numElements--;

		return element;
	}

	public E get(int pos) throws IndexOutOfBoundsException{
		// verifica se a posição é válida
		if (pos < 0  ||  pos >= numElements)
			throw new IndexOutOfBoundsException();

		return elements[pos];
	}

	public int search(E element) {
		for (int i = 0; i < numElements; i++)
			if (element.equals(elements[i]))
				return i;
		
		// se chegar até aqui, é porque não encontrou
		return -1;
	}
	
	/**
	 * Retorna uma representação String da lista.
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = "";
		for (int i = 0; i < numElements; i++)
			s += elements[i] + " ";
		return s;
	}

	public int contaElementos(E element) throws IndexOutOfBoundsException {
		return countElementInList(elements, element, 0, 0);
	}
	
	private int countElementInList(E[] elements, E element, int counter, int quantity) throws IndexOutOfBoundsException {
		if (counter >= elements.length) return quantity;

		if (element.equals(elements[counter])) {
			counter += 1;
			quantity += 1;
			return countElementInList(elements, element, counter, quantity++);
		}
		
		counter += 1;
		return countElementInList(elements, element, counter, quantity);
	}
}