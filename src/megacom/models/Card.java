package megacom.models;

import megacom.enums.CardType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Card {
    private int cardId;
    private String cardNumber;
    private String cardPassword;
    private Calendar startDate;
    private Calendar endDate;
    private CardType cardType;

    Calendar calendar = new GregorianCalendar(3000, 0, 1);

    public Card(String  cardNumber, String  cardPassword, CardType cardType) {
        this.cardId = (int)(Math.random()*(9999999-1000000))-1000000;
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.startDate = Calendar.getInstance();
        this.endDate = calendar;
        this.cardType = cardType;
    }

    public int getCardId() {
        return cardId;
    }

    public String  getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String  cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String  getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(String  cardPassword) {
        this.cardPassword = cardPassword;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }
}
