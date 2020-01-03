package ch.cardset.restservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CardType implements Serializable {

    @Id
    @GeneratedValue( strategy=GenerationType.AUTO )
    private int id;
    private String type;

    public CardType(int id, String type){
        super();
        this.id = id;
        this.type = type;
    }
    
    public CardType(){
        super();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
