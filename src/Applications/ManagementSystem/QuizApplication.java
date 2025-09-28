import java.util.*;

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == correctAnswer;
    }

    public void displayQuestion() {
        System.out.println("\n" + question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public void displayCorrectAnswer() {
        System.out.println("Correct answer: " + (correctAnswer + 1) + ". " + options[correctAnswer]);
    }
}

public class QuizApplication {
    private static List<Question> questions = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static int score = 0;

    public static void main(String[] args) {
        initializeQuestions();

        System.out.println("=== Welcome to the Quiz Application ===");
        System.out.println("You will be presented with " + questions.size() + " questions.");
        System.out.println("Enter the number of your choice (1-4).\n");

        for (int i = 0; i < questions.size(); i++) {
            Question currentQuestion = questions.get(i);
            System.out.println("Question " + (i + 1) + ":");
            currentQuestion.displayQuestion();

            System.out.print("Your answer: ");
            int userAnswer = scanner.nextInt() - 1;

            if (currentQuestion.checkAnswer(userAnswer)) {
                System.out.println("✓ Correct!");
                score++;
            } else {
                System.out.println("✗ Wrong!");
                currentQuestion.displayCorrectAnswer();
            }
        }

        displayResults();
    }

    private static void initializeQuestions() {
        questions.add(new Question(
                "What is the capital of France?",
                new String[]{"London", "Berlin", "Paris", "Madrid"},
                2
        ));

        questions.add(new Question(
                "Which programming language is known as 'write once, run anywhere'?",
                new String[]{"Python", "C++", "Java", "JavaScript"},
                2
        ));

        questions.add(new Question(
                "What is 2 + 2 * 2?",
                new String[]{"6", "8", "4", "12"},
                0
        ));

        questions.add(new Question(
                "Which of these is not a primitive data type in Java?",
                new String[]{"int", "String", "boolean", "double"},
                1
        ));
    }

    private static void displayResults() {
        System.out.println("\n=== Quiz Results ===");
        System.out.println("Total Questions: " + questions.size());
        System.out.println("Correct Answers: " + score);
        System.out.println("Incorrect Answers: " + (questions.size() - score));
        System.out.println("Percentage: " + (score * 100 / questions.size()) + "%");

        if (score == questions.size()) {
            System.out.println("Excellent! Perfect score!");
        } else if (score >= questions.size() / 2) {
            System.out.println("Good job!");
        } else {
            System.out.println("Keep practicing!");
        }
    }
}