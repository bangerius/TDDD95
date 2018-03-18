
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Mainclass {
	public static void main(String[] args) throws FileNotFoundException {
		Kattio sio = new Kattio(System.in, System.out);
		//Kattio fio = new Kattio(new FileInputStream(new File("testinput.txt")), System.out);
		Kattio io = sio; // Change this to switch between file and cl input
		long m = 0; // first number
		long n = 0; // Second number

		while (io.hasMoreTokens()) {
			m = io.getLong();
			n = io.getLong();
			if (m >= 0) {
				System.out.println(zerosTo(n) // Zeros from 0 to n
						- zerosTo(m - 1) // Zeros from 0 no m-1, as m is to be included.
				);
			}
		}

		sio.close();
		//fio.close();
		System.exit(0);
	}

	// Wrapper for edge cases
	static long zerosTo(long i) {
		if (i == 0)
			return 1;
		if (i < 0)
			return 0;
		return combinatoricSolution(i) + 1;
	}

	// Brute force solution, for testing
	static int brute(int num) {
		int zeros = 1;
		int number = 0;
		for (int i = 0; i <= num; i++) {
			number = i;
			while (number > 0) {
				if ((number % 10) == 0)
					zeros++;
				number = number / 10;
			}
		}

		return zeros;
	}

	// General solution
	static long combinatoricSolution(long n) {
		long result = 0;
		long i = 1;
		long a = 0;
		long b = 0;
		long c = 0;

		while (true) {
			c = n % i; // getting digit c
			b = n / i; // getting digits before digit c
			a = b / 10; // getting digits before b
			b = b % 10; // getting digit b

			if (a == 0)
				return result; // end of iteration
			if (b == 0)
				result += (a - 1) * i + c + 1; // Special case, not full combination
			else
				result += a * i; // General case, number of combinations given by digits to left only.
			i *= 10;
		}
	}
}

class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}

	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null)
						return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) {
			}
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}
