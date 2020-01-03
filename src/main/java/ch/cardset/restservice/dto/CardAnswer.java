package ch.cardset.restservice.dto;

import java.util.List;


public class CardAnswer {
    private Integer id;
    private String question;
    private String img;
    private String originalSrc;
    private List<String> answer;

    public CardAnswer(Integer id, String question) {
        this.id = id;
        this.question = question;
    }
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getOriginalSrc() {
        return originalSrc;
    }

    public void setOriginalSrc(String originalSrc) {
        this.originalSrc = originalSrc;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
    
}
