package megacom.models;

public class Adress {
    private String city;
    private String district;
    private String street;
    private String house;

    public Adress(String city, String district, String street, String house) {
        this.city = city;
        this.district = district;
        this.street = street;
        this.house = house;
    }

    public String getCity() {
        if(city.length()<1){
            return "City name must > 1";
        }else {
            return city;
        }
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        if(district.length()<2){
            return "District name > 2";
        } else {
            return district;
        }
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
