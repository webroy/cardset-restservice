package ch.cardset.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Card {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;
    private String img;
    private String question;

    @ManyToOne
    private CardSet cardSet;
    
    public Card(int id, String img, String question){
        super();
        this.id = id;
        this.img = img;
        this.question = question;
    }
    
    public Card(){
        super();
    }
    
    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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