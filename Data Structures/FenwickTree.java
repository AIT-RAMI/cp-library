import java.util.*;
import java.io.*;

public class FenwickTree {
  static class Solver {
    int n, q;
    int[] arr, tree;

    void build(int i) {
      if (i <= n) {
        update(i, arr[i]);
        build(i + 1);
      }
    }

    void update(int i, int x) {
      if (i <= n) {
        tree[i] += x;
        update(i + Integer.lowestOneBit(i), x);
      }
    }

    int query(int i) {
      return (i == 0 ? 0 : tree[i] + query(i - Integer.lowestOneBit(i)));
    }

    int query(int l, int r) {
      return query(r) - query(l - 1);
    }

    void solve() throws Exception {
      n = nextInt();
      arr = new int[n + 1];
      tree = new int[n + 1];
      for (int i = 1; i <= n; i++) {
        arr[i] = nextInt();
      }
      build(1);
      q = nextInt();
      while (q-- > 0) {
        if (nextInt() == 1) {
          update(nextInt(), nextInt());
        } else {
          writer.println(query(nextInt(), nextInt()));
        }
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
