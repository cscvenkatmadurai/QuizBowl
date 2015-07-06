package question;

/**
 * Created by HARISH on 7/5/2015.
 */
public class QuestionTrueFalse extends Question{
    protected boolean answer;
    protected final static  String trueValue = "true",falseValue = "false";

    public QuestionTrueFalse(String qn, int point, String qnType, boolean answer) {
        super(qn, point, qnType);
        this.answer = answer;
    }



    public boolean checkAnswer(String userAnser){
       boolean userAns = (userAnser.equals(trueValue));
        return answer==userAns;
    }

    public String getAnswer() {
        return (answer)?trueValue:falseValue;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
    public static boolean convertAnswerToBoolean(String ans){
        return (ans.equals(trueValue));
    }
}
