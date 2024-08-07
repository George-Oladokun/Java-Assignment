import java.util.Scanner;
public class GPA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        String name = scanner.nextLine();
        double[] subjectScore =  new double[5];
        String[] subjectName = {"CSE 212", "CSE 232", "CSE 251", "WFE 238", "WBE 301"};
        double totalScore = 0;
        for (int i = 0; i < subjectScore.length; i++) {
            System.out.println("Enter your course score for " + subjectName[i] + ":");
            subjectScore[i] = scanner.nextDouble();
            totalScore += subjectScore[i];
          }
          double cgpa = (totalScore / subjectScore.length) / 25.0;
  
          System.out.println("CGPA for " + name + " is: " + cgpa);
          scanner.close();
      } 
}
