import java.util.ArrayList;

public class PrimeFinderPrint {
	
	public static ArrayList<Integer> getPrimesList(int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		int number = 3;
		int count = 0;
		list.add(2);
		while (number <= end) {
			boolean prime = true;
			int i = list.get(count);
			if (i != number && number % i != 0) {
				for (int j = 0; j <= list.size()/4; j++) {
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
		}
		return list;
	}

	public static void main(String[] args) {
		int end = 100;
		ArrayList<Integer> list = getPrimesList(end);
		System.out.println(list);
	}
}