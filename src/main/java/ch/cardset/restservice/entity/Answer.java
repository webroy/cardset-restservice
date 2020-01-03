package ch.cardset.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Answer implements Serializable {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;
    private String answer;
    private boolean isCorrect;
    
    @OneToOne
    private Card card;

    public Answer(int id, String answer){
        super();
        this.id = id;
        this.answer = answer;
    }
    
    public Answer(){
        super();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
