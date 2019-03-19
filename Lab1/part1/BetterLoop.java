class BetterLoop
{
   public static boolean contains(int [] values, int v)
   {
      /* TO DO: if value v is in the array, return true.
         If not, return false.  Use a "foreach" loop.
      */

      //boolean answer = false;
      for (int x: values){
          if (v == x) {
             //answer = true;
             return true;
          }
      }             
   //return answer;
   return false;
   } 
}
