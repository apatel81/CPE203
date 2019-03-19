import java.time.LocalTime;

class CourseSection
{
   private final String prefix;
   private final String number;
   private final int enrollment;
   private final LocalTime startTime;
   private final LocalTime endTime;

   public CourseSection(final String prefix, final String number,
      final int enrollment, final LocalTime startTime, final LocalTime endTime)
   {
      this.prefix = prefix;
      this.number = number;
      this.enrollment = enrollment;
      this.startTime = startTime;
      this.endTime = endTime;
   }

   // additional likely methods not defined since they are not needed for testing

   public String getPrefix()
   {
      return this.prefix;
   }

   public String getNumber()
   {
      return this.number;
   }

   public int getEnrollment()
   {
      return this.enrollment;
   }

   public LocalTime getStartTime() {
      return this.startTime;
   }

   public LocalTime getEndTime() {
      return this.endTime;
   }

   public boolean equals(Object other)
   {
      if (other == null)
      {
         return false;
      }

      if (getClass() != other.getClass())
      {
         return false;
      }

      CourseSection cs = (CourseSection) other;

      return this.prefix.equals(cs.getPrefix()) &&
             this.number.equals(cs.getNumber()) &&
             this.enrollment == (cs.getEnrollment()) &&
             this.startTime.equals(cs.getStartTime()) &&
             this.endTime.equals(cs.getEndTime());
   }

   public int hashCode()
   {
      int hash = 0;

      if (this.prefix != null)
      {
         hash += this.prefix.hashCode();
      }

      if (this.number != null)
      {
         hash += this.number.hashCode();
      }

      if (this.enrollment != 0)
      {
         hash += this.enrollment;
      }

      if (this.startTime != null)
      {
         hash += this.startTime.hashCode();
      }

      if (this.endTime != null)
      {
         hash += this.endTime.hashCode();
      }

      return hash;
   }

}
