# Quiz Application
This is a simple Java Swing application that presents the user with multiple-choice quiz questions.
## Features:
Presents multiple-choice questions to the user.

Checks the user's answers.

Displays the user's score at the end of the quiz.

Highlights the score in green if the user passes or red if the user fails (based on a defined threshold).

Provides an option to restart the quiz after completion.

## Dependencies:
Java SE Development Kit (JDK)

### How to Run:
Ensure you have the JDK installed on your machine.
Compile the Quiz.java file:
javac Quiz.java
Run the compiled Java program:
java Quiz

### How the Application Works: 
The application initializes with the first question displayed.

Users select one of the four radio button options and click the "Next" button.

If the user's answer is correct, their score increases.

The next question appears.

After answering all questions, the user's score is displayed,
and the background color indicates their performance (green for passing and red for failing based on a set threshold).

The user has an option to restart the quiz.

## Customizing the Quiz:
You can add or edit questions by modifying the questions array inside the Quiz class.
Each question is represented as an instance of the inner Question class which includes the question text,
possible answer options, and the correct answer.

For example, to add a new question:
new Question("What is the capital of France?", new String[]{"Berlin", "Madrid", "Paris", "London"}, "Paris")

### Code Overview:
The Quiz class is the main class that initializes and manages the GUI components and the quiz flow.
The inner Question class represents each individual quiz question with its options and correct answer.
GUI components such as JLabel, JRadioButton, JPanel, and JButton are used to build the user interface.
ActionListeners are utilized to handle user interactions like button clicks.

##### Note:
This is a basic quiz application. You can extend its functionalities and enhance the user interface by adding more features like timer, animations, storing scores, etc.




