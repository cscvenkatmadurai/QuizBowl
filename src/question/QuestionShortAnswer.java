package question;

/**
 * Created by HARISH on 7/5/2015.
 */
public class QuestionShortAnswer extends Question{
    String answer;

    public QuestionShortAnswer(String qn, int point, String qnType, String answer) {
        super(qn, point, qnType);
        this.answer = answer;
    }
    public void printQuestion(){
        super.printQuestion();
        System.out.println();

    }

    public boolean checkAnswer(String userAnser){
        return (answer.equals(userAnser));
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
