package ch.cardset.restservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Answer {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;
    private String answer;
    private boolean isCorrect;
    
    @OneToOne
    private Card card;

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(boolean isCorrect) {
        this.isCorrect = isCorrect;
    }
    
    
    // Reference table
    
    public Card getCard() {
        return this.card;
    }

    public void setCardSet(Card card) {
        this.card = card;
    }
}
