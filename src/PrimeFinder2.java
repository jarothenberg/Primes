import java.util.ArrayList;

public class PrimeFinder2 {
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static int number = 3;
	private static int count = 0;

	public static ArrayList<Integer> getPrimes(int end) {
		if (list.isEmpty()) {
			list.add(2);
		}
		boolean prime = true;
		int i = list.get(count);
		if (i != number && number % i != 0) {
			for (int j = 0; j <= list.size() / 4.0; j++) {
				if (number % list.get(j) == 0) {
					prime = false;
					break;
				}
			}
			if (prime == true) {
				list.add(number);
				count++;
			}
		}
		number += 2;
		if (number < end) {
			getPrimes(end);
		}
		return list;
	}

	public static void main(String[] args) {
		getPrimes(1000000); // starts sometimes not working around 8700
		System.out.println(list.size());
	}
}