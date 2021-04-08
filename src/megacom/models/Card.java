package megacom.models;

import megacom.enums.CardType;

import java.util.Date;

public class Card {
    private int cardId;
    private int cardNumber;
    private int cardPassword;
    private Date startDate;
    private Date endDate;
    private CardType cardType;

    public Card(int cardNumber, int cardPassword, Date startDate, Date endDate, CardType cardType) {
        this.cardId = (int)(Math.random()*(9999999-1000000))-1000000;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cardType = cardType;
    }

    public int getCardId() {
        return cardId;
    }

    public int getCardNumber() {
        if(cardNumber<10000){
            System.out.println("CardNumber < 10000");
        }
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPassword() {
        if(cardPassword<1000){
            System.out.println("CardPassword < 1000");
        }
        return cardPassword;
    }

    public void setCardPassword(int cardPassword) {
        this.cardPassword = cardPassword;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
