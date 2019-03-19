import java.util.List;
import java.util.Objects;

class Student
{
   private final String surname;
   private final String givenName;
   private final int age;
   private final List<CourseSection> currentCourses;

   public Student(final String surname, final String givenName, final int age,
      final List<CourseSection> currentCourses)
   {
      this.surname = surname;
      this.givenName = givenName;
      this.age = age;
      this.currentCourses = currentCourses;
   }

   public String getSurname()
   {
      return this.surname;
   }

   public String getGivenName()
   {
      return this.givenName;
   }

   public int getAge() {
      return this.age;
   }

   public List<CourseSection> getCurrentCourses()
   {
      return this.currentCourses;
   }

   public boolean equals(Object other)
   {
      return Objects.equals(this, other);
   }

   public int hashCode()
   {
      return Objects.hash(this.surname, this.givenName, this.age, this.currentCourses);
   }


}
