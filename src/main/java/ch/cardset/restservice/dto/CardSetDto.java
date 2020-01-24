package ch.cardset.restservice.dto;


public class CardSetDto {
    private Integer id;
    private String name;
    private Integer category;
    private Integer cardType;

    public CardSetDto(Integer id, String name, Integer category, Integer cardType) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cardType = cardType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }   
}
