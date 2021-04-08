package megacom.models;

public class Client {
    private int clientId;
    private String firstname;
    private String secondname;

    public Client(String firstname, String secondname) {
        this.clientId = (int)(Math.random()*(9999999-1000000))-1000000;
        this.firstname = firstname;
        this.secondname = secondname;
    }

    public int getClientId() {
        return clientId;
    }

    public String getFirstname() {
        if(firstname.length()<3){
            return "firstname < 3";
        } else {
            return firstname;
        }
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        if(secondname.length()<3){
            return "secondname < 3";
        } else {
            return secondname;
        }
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }
}
