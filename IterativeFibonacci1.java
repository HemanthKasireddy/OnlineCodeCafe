import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;

class IterativeFibonacci1 {
  
  private static long fibonacci(int n) {
    if (n <= 1) {
      return n;
    }
    long a = 0;
    long b = 1;
    for (int i = 2; i <= n; i++) {
      long temp = a;
      a = b;
      b += temp;
    }
    return b;
  }
    
  public static void main(String[] args) {
 
    
    int n = 40;
    Stopwatch timer = Stopwatch.createStarted();
    long f = fibonacci(n);
    timer.stop();
    System.out.printf("Fibonnaci(%d) = %d\n", n, f);
    System.out.printf("Time taken: %,10d microseconds\n", timer.elapsed(TimeUnit.MICROSECONDS));
  }
}
