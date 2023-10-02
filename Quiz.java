import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Quiz {

    // Constants
    private static final String RESTART_TEXT = "Restart The Quiz";
    private static final float PASS_THRESHOLD = 0.5f;
    // Mocked GUI components
    private final JLabel lblQuestion = new JLabel();
    private final JRadioButton jRadioButton1 = new JRadioButton();
    private final JRadioButton jRadioButton2 = new JRadioButton();
    private final JRadioButton jRadioButton3 = new JRadioButton();
    private final JRadioButton jRadioButton4 = new JRadioButton();
    private final JPanel jPanelQContainer = new JPanel();
    private final JButton jButtonNextQ = new JButton();

    // Question and option model
    private static class Question {
        String questionText;
        String[] options;
        String correctAnswer;

        Question(String questionText, String[] options, String correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private final Question[] questions = {
           new Question("10 + 1 = ?", new String[]{"100", "9", "11", "0"}, "11"),
            new Question("28 - 3 = ?", new String[]{"15", "25", "0", "11"}, "25"),
            new Question("5 * 10 = ?", new String[]{"50", "7", "44", "11"}, "50"),
            new Question("which city is the capitol of france?", new String[]{"berlin", "paris", "jerusalem", "tokyo"}, "paris"),
            new Question("which color you get from blue and yellow = ?", new String[]{"green", "red", "purple", "blue"}, "green"),
            new Question("(3)^4 = ?", new String[]{"12", "9", "58", "64"}, "64"),
    };

    private int currentIndex = 0, correctAnswersCount = 0;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public Quiz() {
        initComponents();
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
        displayNextQuestion();
    }

    private void initComponents() {
        // Registering action listeners for the components
        JFrame frame = new JFrame("Quiz");
        frame.setLayout(new BorderLayout());
        jPanelQContainer.setLayout(new BoxLayout(jPanelQContainer, BoxLayout.Y_AXIS));
        jPanelQContainer.add(lblQuestion);
        jPanelQContainer.add(jRadioButton1);
        jPanelQContainer.add(jRadioButton2);
        jPanelQContainer.add(jRadioButton3);
        jPanelQContainer.add(jRadioButton4);
        frame.add(jPanelQContainer, BorderLayout.CENTER);
        frame.add(jButtonNextQ, BorderLayout.SOUTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        jButtonNextQ.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonNextQActionPerformed(evt);
            }
        });

        jButtonNextQ.setText("Next");

        // Additional component setup (layout, positioning, etc.) would go here.
        // This would also involve creating a JFrame, adding components to it,
        // and then displaying the JFrame.
    }
    private void displayNextQuestion() {
        if (currentIndex < questions.length) {
            Question currentQuestion = questions[currentIndex];
            lblQuestion.setText(currentQuestion.questionText);
            jRadioButton1.setText(currentQuestion.options[0]);
            jRadioButton2.setText(currentQuestion.options[1]);
            jRadioButton3.setText(currentQuestion.options[2]);
            jRadioButton4.setText(currentQuestion.options[3]);
            enableRadioButtons(true);  // Enable radio buttons for the new question
        } else {
            // Show results or handle quiz completion
            lblQuestion.setText("Your Score: " + correctAnswersCount + " / " + questions.length);
            if (correctAnswersCount >= questions.length * PASS_THRESHOLD) {
                jPanelQContainer.setBackground(Color.green);
            } else {
                jPanelQContainer.setBackground(Color.red);
            }
            jButtonNextQ.setText("Restart The Quiz");
        }
    }

    private void checkAnswer(JRadioButton radioButton) {
        if (radioButton.getText().equals(questions[currentIndex].correctAnswer)) {
            correctAnswersCount++;
        }
        currentIndex++;
       // enableRadioButtons(false);
    }

    private void enableRadioButtons(boolean enable) {
        jRadioButton1.setEnabled(enable);
        jRadioButton2.setEnabled(enable);
        jRadioButton3.setEnabled(enable);
        jRadioButton4.setEnabled(enable);
    }

    // GUI action handlers
    private void jButtonNextQActionPerformed(ActionEvent evt) {
        if (RESTART_TEXT.equals(jButtonNextQ.getText())) {
            currentIndex = 0;
            correctAnswersCount = 0;
            enableRadioButtons(true);
            displayNextQuestion();
            return;  // Return early since we are restarting the quiz
        }

        // Check the answer if a question is still pending
        if (currentIndex < questions.length) {
            JRadioButton selectedOption = getSelectedRadioButton();
            if (selectedOption != null) {
                checkAnswer(selectedOption);
                buttonGroup.clearSelection();  // Clear the previous selection
            }
        }

        // Display the next question or the result if the quiz is over
        displayNextQuestion();
    }




    private JRadioButton getSelectedRadioButton() {
        if (jRadioButton1.isSelected()) return jRadioButton1;
        if (jRadioButton2.isSelected()) return jRadioButton2;
        if (jRadioButton3.isSelected()) return jRadioButton3;
        if (jRadioButton4.isSelected()) return jRadioButton4;
        return null;
    }





    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Quiz();
            }
        });
    }
}
