package question;

import java.util.Arrays;

/**
 * Created by HARISH on 7/5/2015.
 */
public class QuestionMultipleChoice extends Question{
    protected String answer;
    protected String choice[];
    protected int noOfChoice;
    protected final int asciiValueOfA = 65;

    public QuestionMultipleChoice(String qn, int point, String qnType, String answer, String[] choice, int noOfChoice) {
        super(qn, point, qnType);
        this.answer = answer;
        this.choice = choice;

        this.noOfChoice = noOfChoice;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String[] getChoice() {

        return choice;
    }

    public void setChoice(String[] choice) {
        this.choice = choice;
    }

    public int getNoOfChoice() {
        return noOfChoice;
    }
    public void printChoices(){
        for (int i = 0; i < choice.length ; i++) {
            System.out.printf("%c )", (i+asciiValueOfA) );
            System.out.println (choice[i]);
        }
    }

    public void setNoOfChoice(int noOfChoice) {
        this.noOfChoice = noOfChoice;
    }
    public boolean checkAnswer(String userAnser){
        return (answer.equals(userAnser));
    }
    public void printQuestion(){

        super.printQuestion();
        System.out.println();
        printChoices();


    }

}
