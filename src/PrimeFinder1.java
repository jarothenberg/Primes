import java.util.ArrayList;

public class PrimeFinder1 {

	public static ArrayList<Integer> PrimeLister(int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(2);
		int num = 2;
		for (int i = 2; i <= end; i++) {
			int count = 1;
			for (int j : list) {
				if (num % j != 0 && count > list.size() / 4.0 && num > 1) {
					count++;
					list.add(num);
					num++;
					break;
				} else {
					count++;
					if (num % j == 0) {
						num++;
						break;
					} else if (num == 1) {
						num++;
					}
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		System.out.println(PrimeLister(1000000).size());
	}
}