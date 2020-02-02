package ch.cardset.restservice.dto;

import ch.cardset.restservice.entity.Answer;
import java.util.List;


public class CardAnswerDto {
    private Integer id;
    private String question;
    private String img;
    private String originalSrc;
    private List<Answer> answer;
    private Integer cardSetId;

    public CardAnswerDto(Integer id, String img, String originalSrc, String question, Integer cardSetId, List<Answer> answer) {
        this.id = id;
        this.img = img;
        this.originalSrc = originalSrc;
        this.question = question;
        this.cardSetId = cardSetId;
        this.answer = answer;
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
    
    public Integer getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(Integer cardSetId) {
        this.cardSetId = cardSetId;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }
}
