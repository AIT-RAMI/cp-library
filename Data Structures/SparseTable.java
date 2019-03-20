import java.util.*;
import java.io.*;

public class SparseTable {
  static class Solver {
    int n, k, q;
    int[] arr;
    int[][] sparse;
    
    int log2(int n) {
      return Integer.numberOfTrailingZeros(Integer.highestOneBit(n));
    }

    int combine(int a, int b) {
      return Math.min(a, b);
    }

    void build() {
      System.arraycopy(arr, 0, sparse[0], 0, n);
      for (int i = 1; i < k; i++) {
        for (int j = 0; j + (1 << i) <= n; j++) {
          sparse[i][j] = combine(sparse[i - 1][j], sparse[i - 1][j + (1 << (i - 1))]);
        }
      }
    }

    int query(int l, int r) {
      int i = log2(r - l + 1);
      return combine(sparse[i][l], sparse[i][r - (1 << i) + 1]);
    }

    void solve() throws Exception {
      n = nextInt();
      k = log2(n) + 1;
      arr = new int[n];
      sparse = new int[k][n];
      for (int i = 0; i < n; i++) {
        arr[i] = nextInt();
      }
      build();
      q = nextInt();
      while (q-- > 0) {
        writer.println(query(nextInt(), nextInt()));
      }
    }
  }

  // -------------------------------------------------
  
  static BufferedReader reader;
  static StringTokenizer tokenizer;
  static PrintWriter writer;

  static int nextInt() throws Exception {
    while (tokenizer == null || !tokenizer.hasMoreTokens()) {
      tokenizer = new StringTokenizer(reader.readLine());
    }
    return Integer.parseInt(tokenizer.nextToken());
  }

  public static void main(String...args) throws Exception {
    reader = new BufferedReader(new InputStreamReader(System.in));
    writer = new PrintWriter(System.out);
    new Solver().solve();
    writer.close();
    reader.close();
  }
}
