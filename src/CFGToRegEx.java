import java.util.Random;

import regex.*;

public class CFGToRegEx {
	int[][] edges;

	public CFGToRegEx() {
		// TODO Auto-generated constructor stub
	}

	public CFGToRegEx(int n) {
		edges = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				edges[i][j] = (int)Math.round(Math.random());
			}
		}
	}

	public String getRegEx(int n) {
		IRegularExpression[][] A = new IRegularExpression[n + 1][n + 1];
		IRegularExpression[] B = new IRegularExpression[n + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n; j++) {
				A[i][j] = new Terminal("o");
			}
			B[i] = new Terminal("o");
		}

		A[0][1] = new Terminal("0");
		B[n] = new Terminal("e");

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (this.edges[i][j] == 1) {
					A[i + 1][j + 1] = new Terminal(String.valueOf(j));
				}
			}
		}

		for (int k = n; k >= 0; k--) {
			B[k] = new Concatenation(new Star(A[k][k]), B[k]);

			for (int j = 0; j < k; j++) {
				A[k][j] = new Concatenation(new Star(A[k][k]), A[k][j]);
			}

			for (int i = 0; i < k; i++) {
				B[i] = new Alternation(B[i], new Concatenation(A[i][k], B[k]));

				for (int j = 0; j < k; j++) {
					A[i][j] = new Alternation(A[i][j], new Concatenation(A[i][k], A[k][j]));
				}
			}

			for (int i = 0; i < k; i++) {
				A[i][k] = new Terminal("o");
			}
		}

		return B[0].value();
	}

	public static void main(String[] args) {
		CFGToRegEx cfgToRegEx = new CFGToRegEx(4);
		System.out.println(cfgToRegEx.getRegEx(4));
	}
}