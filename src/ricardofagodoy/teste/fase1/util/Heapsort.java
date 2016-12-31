package ricardofagodoy.teste.fase1.util;

import java.util.Collections;
import java.util.List;

/*
 *  http://www.code2learn.com/2011/09/heapsort-array-based-implementation-in.html
 *  used as support for this implementation
 */

public class Heapsort<T extends Comparable<T>> {
	
	private List<T> list;
	
	private int n;
	private int left;
	private int right;
	private int largest;

	public Heapsort(List<T> list) {
		this.list = list;
	}

	public void sort() {

		buildHeap();

		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			maxHeap(0);
		}
	}

	public void maxHeap(int i) {

		left = 2 * i;
		right = 2 * i + 1;

		if (left <= n && list.get(left).compareTo(list.get(i)) > 0)
			largest = left;
		else
			largest = i;

		if (right <= n && list.get(right).compareTo(list.get(largest)) > 0)
			largest = right;

		if (largest != i) {
			exchange(i, largest);
			maxHeap(largest);
		}
	}

	public void buildHeap() {

		this.n = list.size() - 1;

		for (int i = n / 2; i >= 0; i--)
			maxHeap(i);
	}

	public void exchange(int i, int j) {
		Collections.swap(list, i, j);
	}
}