import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OnlineQuizApp {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();

        // Create and add questions to the quiz
        Question question1 = new Question("Which country is known as the 'Land of the Rising Sun'?",
                new String[]{"A. China", "B. Japan", "C. South Korea", "D. India"}, 1);
        Question question2 = new Question("What is the smallest prime number?",
                new String[]{"A. 0", "B. 1", "C. 2", "D. 3"}, 2);
        Question question3 = new Question("Who is the author of 'To Kill a Mockingbird'?",
                new String[]{"A. Harper Lee", "B. J.K. Rowling", "C. Charles Dickens", "D. F. Scott Fitzgerald"}, 0);
        Question question4 = new Question("What is the currency of Brazil?",
                new String[]{"A. Peso", "B. Real", "C. Rupiah", "D. Lira"}, 1);
        Question question5 = new Question("Which gas is most abundant in the Earth's atmosphere?",
                new String[]{"A. Nitrogen", "B. Oxygen", "C. Carbon Dioxide", "D. Hydrogen"}, 0);

        quiz.addQuestion(question1);
        quiz.addQuestion(question2);
        quiz.addQuestion(question3);
        quiz.addQuestion(question4);
        quiz.addQuestion(question5);

        int score = conductQuiz(quiz);

        // Display user score
        System.out.println("Quiz completed! Your score is: " + score + "/" + quiz.getTotalQuestions());
    }

    public static int conductQuiz(Quiz quiz) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < quiz.getTotalQuestions(); i++) {
            Question currentQuestion = quiz.getQuestion(i);
            if (currentQuestion != null) {
                System.out.println("Question " + (i + 1) + ": " + currentQuestion.getQuestionText());
                String[] options = currentQuestion.getOptions();
                for (String option : options) {
                    System.out.println(option);
                }
                System.out.print("Enter your choice (A, B, C, D): ");
                String userAnswer = scanner.nextLine().toUpperCase();

                // Validate user input and calculate score
                if (userAnswer.length() == 1 && userAnswer.matches("[A-D]")) {
                    int userChoice = userAnswer.charAt(0) - 'A';
                    if (userChoice == currentQuestion.getCorrectAnswerIndex()) {
                        score++;
                    }
                } else {
                    System.out.println("Invalid input! Skipping this question.");
                }
            }
        }

        return score;
    }
}

class Question {
    private String questionText;
    private String[] options;
    private int correctAnswerIndex;

    public Question(String questionText, String[] options, int correctAnswerIndex) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}

class Quiz {
    private List<Question> questions;

    public Quiz() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public Question getQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public int getTotalQuestions() {
        return questions.size();
    }
}