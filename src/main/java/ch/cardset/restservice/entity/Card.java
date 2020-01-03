package ch.cardset.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Card implements Serializable {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;
    private String img;
    private String question;
    private String originalSrc;

    @ManyToOne
    private CardSet cardSet;
    
    public Card(int id, String img, String originalSrc, String question){
        super();
        this.id = id;
        this.img = img;
        this.question = question;
        this.originalSrc = originalSrc;
    }
    
    public Card(){
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
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


    // Reference table
    
    public CardSet getCardSet() {
        return this.cardSet;
    }

    public void setCardSet(CardSet cardSet) {
        this.cardSet = cardSet;
    }
}