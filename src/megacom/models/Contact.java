package megacom.models;

public class Contact {
    private String phoneNumber;
    private String email;

    public Contact(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        if(phoneNumber.length()<10){
            return "Phone number size < 10";
        } else {
            return phoneNumber;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        //do{
            this.phoneNumber = phoneNumber;
        //}while (phoneNumber.length()<10);
    }

    public String getEmail() {
        if(email.length()<10){
            return "email size < 10";
        }else {
            return email;
        }
    }

    public void setEmail(String email) {
        //do{
            this.email = email;
        //}while (email.length()<10);
    }
}
