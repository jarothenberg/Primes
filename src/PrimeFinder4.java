import java.util.ArrayList;

public class PrimeFinder4 {

	public static ArrayList<Boolean> getPrimes(int end) {
		boolean[] numbersBool = new boolean[end + 1];
		for (int i = 2; i < end; i++) {
			numbersBool[i] = true;
		}
		for (int p = 2; p * p <= end; p++) {
			if (numbersBool[p] == true) {
				for (int i = p * 2; i <= end; i += p) {
					numbersBool[i] = false;
				}
			}
		}
		ArrayList<Boolean> primes = new ArrayList<Boolean>();
		for (boolean b : numbersBool) {
			if (b) {
				primes.add(b);
			}
		}
		return primes;
	}

	public static void main(String args[]) {
		int end = 1000000000;	//the number in which the program will return the number of primes which come before and including it.
						//WARNING: do not enter any negative number
		System.out.println(getPrimes(end).size());
	}
}