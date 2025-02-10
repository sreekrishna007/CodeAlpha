import java.util.ArrayList;
import java.util.Scanner;

class Student{
  String name;
  double grade;

  public Student(String name,double grade){
    this.name=name;
    this.grade=grade;
    }
  }

public class GradeTracker{
  public static void main(String[]args){
    Scanner scanner=new Scanner(System.in);
    ArrayList<Student>students=new ArrayList<>();

    System.out.print("Enter the number of students:");
    int numStudents=scanner.nextInt();
    scanner.nextLine();

    for(int i=0;i<numStudents;i++){
      System.out.print("Enter student name:");
      String name=scanner.nextLine();

      System.out.print("Enter"+name+"s grade:");
      double grade=scanner.nextDouble();
      scanner.nextLine();

      students.add(new Student(name,grade));
   }

   double total=0,highest=students.get(0).grade,lowest=students.get(0).grade;
   String topStudent=students.get(0).name,lowStudent=students.get(0).name;

   for(Student student:students){
     total+=student.grade;

     if(student.grade>highest){
       highest=student.grade;
       topStudent=student.name;
     }

     if(student.grade<lowest){
       lowest=student.grade;
       lowStudent=student.name;
     }
   }

   double average=total/students.size();

   System.out.print("\n===Student Grades Summary===");
   for(Student student:students){
     System.out.println(student.name+":"+student.grade);
   }
   System.out.println("---------------");
   System.out.println("Average Score:"+average);
   System.out.println("Highest Score:"+highest+"(by"+topStudent+")");
   System.out.println("Lowest Score:"+lowest+"(by"+lowStudent+")");

   scanner.close();
  }
 }