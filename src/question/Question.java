package question;

/**
 * Created by HARISH on 7/5/2015.
 */
public abstract class Question {
    protected String qn,qnType;
    protected int point;


    protected static String multipleChoice,trueOrFalse,shortAnswer;
    static{
        multipleChoice = "MC";
        trueOrFalse = "TF";
        shortAnswer = "SA";
    }


    public Question(String qn, int point, String qnType) {
        this.qn = qn;
        this.point = point;
        this.qnType = qnType;
    }
    public abstract String getAnswer();

    public String getQn() {
        return qn;
    }


    public void setQn(String qn) {
        this.qn = qn;
    }

    public int getPoint() {
        return point;
    }
     public void  printQuestion(){
         System.out.println("Points "+point);
         System.out.println("Question :"+qn);
     }
    abstract  public boolean checkAnswer(String userAns);



    public void setPoint(int point) {
        this.point = point;
    }

    public String getQnType() {
        return qnType;
    }

    public void setQnType(String qnType) {
        this.qnType = qnType;
    }
    private static boolean checkEqual(String qntype,String userQnType){

        return  (userQnType.equals(qntype));
    }
   public static boolean checkTrueOrFalseQn(String userQntype){
       return checkEqual(trueOrFalse,userQntype);
   }
    public static boolean checkShortAnswer(String userQntype){
        return checkEqual(shortAnswer,userQntype);
    }
    public static  boolean checkMultipleAnswer(String userQntype){
        return checkEqual(multipleChoice,userQntype);
    }
}
