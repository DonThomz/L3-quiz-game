package org.farmas.model.questions;

import java.util.Iterator;
import java.util.List;

public class ListQuestions implements Iterable<Question>{

    private List<Question> questionList;
    private int indicatorQuestion;

    public ListQuestions(List<Question> questionList) {
        this.questionList = questionList;
        this.indicatorQuestion = 0;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public int getIndicatorQuestion() {
        return indicatorQuestion;
    }

    private void setIndicatorQuestion(int indicatorQuestion) {
        this.indicatorQuestion = indicatorQuestion;
    }

    public void removeQuestion(int idQuestion){
        this.getQuestionList().remove(idQuestion);
    }

    public void addQuestion(Question question){
        try {
            this.getQuestionList().add((Question) question.clone());
        }catch (CloneNotSupportedException cloneNotSupportedException){
            cloneNotSupportedException.printStackTrace();
        }
    }

    public Question selectQuestion(String theme, Level level){
        this.setIndicatorQuestion(indicatorQuestion + 1);
        return this.getQuestionList().get(indicatorQuestion - 1);
    }

    @Override
    public Iterator<Question> iterator() {
        return questionList.iterator();
    }

    public void displayList(){
        this.getQuestionList().forEach(System.out::println);
    }

}
