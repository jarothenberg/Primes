public class PrimeFinder5 {

	public static int getPrimes(int end) {
		boolean[] numbersBool = new boolean[end + 1];
		for (int p = 2; p * p <= end; p++) {
			if (numbersBool[p] == false) {
				for (int i = p * 2; i <= end; i += p) {
					numbersBool[i] = true;
				}
			}
		}
		int count = -2;
		for (boolean b : numbersBool) {
			if (!b) {
				count++;
			}
		}
		return count;
	}

	public static void main(String args[]) {
		int end = 1000000000;	//the number in which the program will return the number of primes which come before and including it.
						//WARNING: do not enter any negative number
		System.out.println(getPrimes(end));
	}
}