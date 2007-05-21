public class StatsUtil {

   // return maximum value (-infinity if no such value)
   public static double max(double[] a) {
      double max = Double.NEGATIVE_INFINITY;
      for (int i = 0; i < a.length; i++)
         if (a[i] > max) max = a[i];
      return max;
   }

   // return minimum value (+infinity if no such value)
   public static double min(double[] a) {
      double min = Double.POSITIVE_INFINITY;
      for (int i = 0; i < a.length; i++)
         if (a[i] < min) min = a[i];
      return min;
   }

   // return average value (NaN if no such value)
   public static double mean(double[] a) {
      double sum = 0.0;
      for (int i = 0; i < a.length; i++)
         sum = sum + a[i];
      return sum / a.length;
   }

   // return variance (NaN if no such value)
   public static double var(double[] a) {
      double avg = mean(a);
      double sum = 0.0;
      for (int i = 0; i < a.length; i++)
         sum += (a[i] - avg) * (a[i] - avg);
      return sum / a.length;
   }

   // return standard deviation (NaN if no such value)
   public static double stddev(double[] a) {
      return Math.sqrt(var(a));
      //return Math.sqrt( squareSum/count - mean*mean );
   }

   // return maximum value (overloaded for int values)
   public static int max(int[] a) {
      int max = Integer.MIN_VALUE;
      for (int i = 0; i < a.length; i++)
         if (a[i] > max) max = a[i];
      return max;
   }

   // return sum of all values (0 if no elements)
   public static int sum(int[] a) {
      int sum = 0;
      for (int i = 0; i < a.length; i++)
         sum += a[i];
      return sum;
   }
} 
