package ch.cardset.restservice.dto;


public class CardDto {
    private Integer id;
    private String img;
    private String originalSrc;
    private String question;
    private Integer cardSetId;

    public CardDto(Integer id, String img, String originalSrc, String question, Integer cardSetId) {
        this.id = id;
        this.img = img;
        this.originalSrc = originalSrc;
        this.question = question;
        this.cardSetId = cardSetId;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getCardSetId() {
        return cardSetId;
    }

    public void setCardSetId(Integer cardSetId) {
        this.cardSetId = cardSetId;
    }
    
}
