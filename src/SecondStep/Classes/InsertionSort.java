package SecondStep.Classes;

import java.util.Comparator;

public class InsertionSort<T> {
	public void sort(T[] a, Comparator<T> comparator) {
		for (int i = 0; i < a.length; i++) {
			int j = i;
			T element = a[i];
			
			while (j > 0 && comparator.compare(a[j - 1], element) > 0) {
				a[j] = a[j - 1];
				j--;
			}
			
			a[j] = element;
		}
	}
}
