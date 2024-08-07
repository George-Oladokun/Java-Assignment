import java.util.Scanner;

public class CBTExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the CBT Exam!");
        System.out.print("Enter the number of students: ");
        int numStudents = scanner.nextInt();
        scanner.nextLine(); 

        String[] firstNames = new String[numStudents];
        String[] lastNames = new String[numStudents];
        String[] matricNumbers = new String[numStudents];

        registerStudents(scanner, numStudents, firstNames, lastNames, matricNumbers);

        System.out.println("--- CBT Exam ---");
        int[] answers = { 1, 2, 3, 4, 1, 2, 3, 4, 1, 2 };
        String[] questions = { "1 + 0", "1 + 1", "6 - 3", "8 - 4", "5 - 4", "2 * 1", "7 - 4", "2 + 2", "2 - 1",
                "8 / 4" };
        int[] scores = conductExam(scanner, numStudents, firstNames, lastNames, questions, answers);

        displayWinner(firstNames, lastNames, matricNumbers, scores);

        scanner.close();
    }

    public static void registerStudents(Scanner scanner, int numStudents, String[] firstNames, String[] lastNames, String[] matricNumbers) {
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Student " + (i + 1) + " Registration:");
            System.out.print("Enter first name: ");
            firstNames[i] = scanner.nextLine();
            System.out.print("Enter last name: ");
            lastNames[i] = scanner.nextLine();
            System.out.print("Enter matriculation number: ");
            matricNumbers[i] = scanner.nextLine();
        }
    }

    public static int[] conductExam(Scanner scanner, int numStudents, String[] firstNames, String[] lastNames, String[] questions, int[] answers) {
        int[] scores = new int[numStudents];

        for (int s = 0; s < numStudents; s++) {
            System.out.println("Student " + (s + 1) + ": " + firstNames[s] + " " + lastNames[s]);
            int score = 0;
            for (int q = 0; q < 10; q++) {
                System.out.println("Question " + (q + 1) + ": " + questions[q]);
                int studentAnswer = scanner.nextInt();
                if (studentAnswer == answers[q]) {
                    score++;
                }
            }
            scores[s] = score;
            System.out.println("Score: " + score + "/10\n");
        }
        return scores;
    }

    public static void displayWinner(String[] firstNames, String[] lastNames, String[] matricNumbers, int[] scores) {
        System.out.println("\n--- Winner ---");
        int highestScore = scores[0];
        int winnerIndex = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > highestScore) {
                highestScore = scores[i];
                winnerIndex = i;
            }
        }
        System.out.println("Congratulations to the winner:");
        System.out.println("Name: " + firstNames[winnerIndex] + " " + lastNames[winnerIndex]);
        System.out.println("Matriculation Number: " + matricNumbers[winnerIndex]);
        System.out.println("Score: " + highestScore);
    }
}
