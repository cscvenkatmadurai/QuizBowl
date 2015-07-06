package application;

import question.Question;
import question.QuestionMultipleChoice;
import question.QuestionShortAnswer;
import question.QuestionTrueFalse;
import user.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by HARISH on 7/5/2015.
 */
public class QuizBowl {
    protected Player player;
    protected String fileName;
    protected ArrayList<Question> questionArray;
    protected int maxNoQuestions;
    protected BufferedReader fileReader;
    protected int noOfQnWishToAnswer;
    protected static BufferedReader kb;
    protected final String regexPatternForInteger = "[0-9]+",skip = "SKIP";
    protected boolean qnasked[];

    static{
        kb = new BufferedReader(new InputStreamReader(System.in ));
    }

    QuizBowl(String fName,String lName,String fileName) throws IOException{
        player = new Player(fName,lName);
        this.fileName = fileName;
        getFilePointer();
        maxNoQuestions = getMaxNoOfQuestion();
        questionArray = new ArrayList<>();
        readAllQuestion();
        getNoOfQuestionWishToAnswer();
        qnasked = new boolean[maxNoQuestions];


    }
    protected int getNoOfQuestionWishToAnswer()throws IOException{
        boolean flag = false;
        String no;
        do{
            System.out.println("How Many Question would you like to answer (out of "+maxNoQuestions+" )");
            no = kb.readLine();
            if ( validateNoOfQnWishToAnswer(no) ){
                flag = true;
            }


        }while (!flag);
        return  0;
    }

    protected boolean validateNoOfQnWishToAnswer(String no) {
        if(checkWhetherInteger(no)){
            int n = Integer.parseInt(no);
            if(checkWhetherIpLessthanMaxNoOfQuestion(n)){
                noOfQnWishToAnswer = n;
                return true;

            }else{
                System.out.println("Sorry,that is too many");
            }
        }else {
            System.out.println("Sorry,this is invalid");

        }
        return false;
    }

    protected boolean checkWhetherInteger(String no){
        return no.matches(regexPatternForInteger);

    }
    protected boolean checkWhetherIpLessthanMaxNoOfQuestion(int no){
        return (no <= maxNoQuestions);

    }
    protected void readAllQuestion() throws IOException{
        for (int i = 0; i < maxNoQuestions ; i++) {
            readSingleQuestion();
        }
    }
    protected void readSingleQuestion() throws IOException{
        String[] line ;
        String qnType,qn;
        int point;
        line = fileReader.readLine().split(" ");

        qnType = line[0];
        point = Integer.parseInt(line[1]);
        qn = fileReader.readLine();
        if(Question.checkMultipleAnswer(qnType) ){
            questionArray.add(readMultipleChoiceQuestion(qnType, qn, point));
        }else if( Question.checkShortAnswer(qnType ) ) {
            questionArray.add(getQuestion(readShortAnswerQuestion(qnType, qn, point)));

        }else {//question is true or False question
            questionArray.add(readTrueOrFalseQuestion(qnType, qn, point));

        }

    }

    private QuestionShortAnswer getQuestion(QuestionShortAnswer question) {
        return question;
    }


    protected QuestionTrueFalse readTrueOrFalseQuestion(String qnType, String qn, int point) throws IOException {
        String ans = fileReader.readLine();

        return new QuestionTrueFalse(qn,point,qnType,QuestionTrueFalse.convertAnswerToBoolean(ans));
    }

    protected QuestionShortAnswer readShortAnswerQuestion(String qnType, String qn, int point) throws IOException {
        String ans = fileReader.readLine();

        return new QuestionShortAnswer(qn,point,qnType,ans);
    }

    protected QuestionMultipleChoice readMultipleChoiceQuestion(String qnType, String qn, int point) throws IOException {
        int noOfChoices;
        String choices[ ];
        String ans;
        noOfChoices = Integer.parseInt(fileReader.readLine());
        choices = new String[noOfChoices];
        for (int i = 0; i < noOfChoices ; i++) {
            choices[i] = fileReader.readLine();
        }
        ans = fileReader.readLine();
        return new QuestionMultipleChoice(qn,point,qnType,ans,choices,noOfChoices);
    }

    protected int getMaxNoOfQuestion() {
        int noOfQn = 0;
        try{

            noOfQn =  Integer.parseInt(fileReader.readLine());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return noOfQn;

    }

    protected void getFilePointer()  {

        boolean fileNameCheck = false;
        do {
            try {
                fileReader = new BufferedReader(new FileReader(fileName));
                fileNameCheck = true;
            } catch (FileNotFoundException e) {

                try {
                    System.out.println("please Enter a valid fileName\n");
                    fileName = kb.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }


                e.getMessage();
            }
        }while (!fileNameCheck);

    }
    protected void closeFileReader(){
        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void quiz() throws IOException{
        int qnCount = 0,randomQnNo;

        Random r = new Random();
        while (qnCount < noOfQnWishToAnswer) {
            randomQnNo = r.nextInt(maxNoQuestions);
         //   System.out.println(randomQnNo+"randomNo");
            if (!checkWheatherGivenQnAsked(randomQnNo)) {
                String userAns;
                qnCount++;
                qnasked[randomQnNo] = true;
                Question question = questionArray.get(randomQnNo);
                question.printQuestion();
                userAns = kb.readLine();
                if ( checkSkip(userAns) ){
                    System.out.println("You have elected SKIP option");
                }else{
                    validateAnswerAndAddPoint(userAns, question);


                }


                System.out.println();
            }
        }
        finalMessagePrompt();
        closeFileReader();

    }

    private void validateAnswerAndAddPoint(String userAns, Question question) {
        if( question.checkAnswer(userAns) ){
            System.out.println("Correct you got " + question.getPoint());
            player.addPoints(question.getPoint());

        }else{
            System.out.println("Incorrect, the answer is "+question.getAnswer()+" you lose "+question.getPoint() );
            player.addPoints(question.getPoint() * -1);

        }
    }

    private void finalMessagePrompt() {
        System.out.println(player.getFirstName()+" "+player.getLastName()+","+" your game is over!");
        System.out.println("Your finalScore is "+player.getPoints()+" points.");
        System.out.println("Better luck next Time !");
    }

    protected boolean checkSkip(String userAns){
     return (userAns.equals(skip));

    }

    protected boolean checkWheatherGivenQnAsked(int qnNo){
        return qnasked[qnNo];
    }

    public static void main(String[] args) {
        try {
            QuizBowl q = new QuizBowl("Arup","Guha","C:\\Users\\HARISH\\IdeaProjects\\QuizBowl\\src\\application\\sample.txt");
            q.quiz();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
