import java.util.*;
import java.io.*;

public class SegmentTree {
  static class Solver {
    int n, q;
    int[] arr, tree, lazy;

    int neutral() {
      return 0;
    }

    int combine(int a, int b) {
      return a + b;
    }

    void push(int n, int l, int r) {
      if (lazy[n] != 0) {
        tree[n] += (r - l + 1) * lazy[n];
        if (l != r) {
          lazy[n + n] += lazy[n];
          lazy[n + n + 1] += lazy[n];
        }
        lazy[n] = 0;
      }
    }

    void build(int n, int l, int r) {
      if (l == r) {
        tree[n] = arr[l];
      } else {
        int m = (l + r) >> 1;
        build(n + n, l, m);
        build(n + n + 1, m + 1, r);
        tree[n] = combine(tree[n + n], tree[n + n + 1]);
      }
    }

    void update(int n, int l, int r, int s, int e, int v) {
      push(n, l, r);
      if (r < s || l > e) {
      } else if (s <= l && r <= e) {
        tree[n] += (r - l + 1) * v;
        if (l != r) {
          lazy[n + n] += v;
          lazy[n + n + 1] += v;
        }
      } else {
        int m = (l + r) >> 1;
        update(n + n, l, m, s, e, v);
        update(n + n + 1, m + 1, r, s, e, v);
        tree[n] = combine(tree[n + n], tree[n + n + 1]);
      }
    }

    int query(int n, int l, int r, int s, int e) {
      push(n, l, r);
      if (r < s || l > e) {
        return neutral();
      } else if (s <= l && r <= e) {
        return tree[n];
      } else {
        int m = (l + r) >> 1;
        return combine(
          query(n + n, l, m, s, e),
          query(n + n + 1, m + 1, r, s, e)
        );
      }
    }

    void solve() throws Exception {
      n = nextInt();
      arr = new int[n + 1];
      tree = new int[4 * n + 5];
      lazy = new int[4 * n + 5];
      for (int i = 1; i <= n; i++) {
        arr[i] = nextInt();
      }
      build(1, 1, n);
      q = nextInt();
      while (q-- > 0) {
        if (nextInt() == 1) {
          update(1, 1, n, nextInt(), nextInt(), nextInt());
        } else {
          writer.println(query(1, 1, n, nextInt(), nextInt()));
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
