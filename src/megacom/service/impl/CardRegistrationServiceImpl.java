package megacom.service.impl;

import megacom.models.Adress;
import megacom.models.Client;
import megacom.models.Contact;
import megacom.service.CardRegistrationService;
import megacom.service.FillTableClientAdress;

import java.sql.*;

import java.util.Scanner;

public class CardRegistrationServiceImpl implements CardRegistrationService, FillTableClientAdress {

    Scanner scanner = new Scanner(System.in);

    //-----------------------------Подключение к БД-------------------------------------------
    Connection connection;
    Statement statement;
    public void ConnectionSQLite() {
        Connection connection = getConnection();
        System.out.println("Connection");
    }

    private Connection getConnection() {
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(
                    "jdbc:sqlite:D:\\Azamat\\Javaitschool\\5month\\streamAPImethods\\sqlite3.db");
            //System.out.println("Connection");
            return connection;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void close() {
        try{
            connection.close();
            System.out.println("Close connection");
        }catch(Exception e)
        {
            System.out.print(e.getMessage());
        }
    }
    //-----------------------------Подключение к БД-------------------------------------------


    //****************************************************************************************
    //****************************************************************************************


    //-----------------------------Функции ввода клиента,адреса,контакта----------------------
    private Client scannerClient(){
        System.out.print("Введите имя: ");
        String fname = scanner.next();
        System.out.print("Введите фамилию: ");
        String sname = scanner.next();

        Client client = new Client(fname, sname);
        return client;
    }

    private Adress scannerAdress(){
        System.out.print("Введите город: ");
        String city = scanner.next();
        System.out.print("Введите район: ");
        String district = scanner.next();
        System.out.print("Введите улицу: ");
        String street = scanner.next();
        System.out.print("Введите дом: ");
        String house = scanner.next();

        Adress adress = new Adress(city, district, street, house);
        return adress;
    }

    private Contact scannerContact(){
        System.out.print("Введите номер: ");
        String phoneNumber = scanner.next();
        System.out.print("Введите email: ");
        String email = scanner.next();

        Contact contact = new Contact(phoneNumber, email);
        return contact;
    }
    //-----------------------------Функции ввода клиента,адреса,контакта----------------------


    //****************************************************************************************
    //****************************************************************************************


    //----------------------------Функции получения ID клиента,адреса,контакта----------------
    /*
    private int selectIdClient(int clientId, String firstname, String secondname){
        try{
            statement = connection.createStatement();
            // scannerClient() функция ввода клиента
            Client client = scannerClient();

            System.out.print("Введите client_id: ");
            int clientId = scanner.nextInt();

            String query = "SELECT id FROM clients " +
                    " WHERE client_id='"+clientId+"' " +
                    " AND firstname='"+client.getFirstname()+"' " +
                    " AND secondname='"+client.getSecondname()+"'";

            ResultSet rs = statement.executeQuery(query);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
                //System.out.println(i);
            }
            if(i==0){
                throw new RuntimeException("Такого клиента нет в Базе Данных");
            }
            return i;
        }catch (SQLException e){
            throw new RuntimeException("Ошибка в функции получения ID клиента");
        }

    }
    */
    private int selectIdAdress(){
        try{
            statement = connection.createStatement();

            Adress adress = scannerAdress();

            String query = "SELECT id FROM adress " +
                    " WHERE city='"+adress.getCity()+"' " +
                    " AND district='"+adress.getDistrict()+"' " +
                    " AND street='"+adress.getStreet()+"' " +
                    " AND house='"+adress.getHouse()+"' ";

            ResultSet rs = statement.executeQuery(query);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
                System.out.println(i);
            }

            if(i==0){
                throw new RuntimeException("Этого адреса нет в Базе Данных");
            }
            return i;
        }catch (SQLException e){
            throw new RuntimeException("Ошибка в функции получения ID адреса");
        }
    }

    private int selectIdContact(){
        try{
            statement = connection.createStatement();

            Contact contact = scannerContact();

            String query = "SELECT id FROM contacts WHERE " +
                    " phone_number='"+contact.getPhoneNumber()+"' AND " +
                    " email='"+contact.getEmail()+"'";
            ResultSet rs = statement.executeQuery(query);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
                System.out.println(i);
            }
            return i;
        }catch (SQLException e){
            throw new RuntimeException("Ошибка в функции при получении ID контакта");
        }
    }
    //----------------------------Функции получения ID клиента,адреса,контакта----------------

    //****************************************************************************************
    //****************************************************************************************


    //--------------------------Функции ввода данных клиента,адреса,контакта------------------

    private void insertClient(int clientId, String firstname, String secondname){}

    private void insertAdress(){}

    private void insertContact(){}

    //--------------------------Функции ввода данных клиента,адреса,контакта------------------


    //****************************************************************************************
    //****************************************************************************************



    @Override
    public void createNewCard() {

    }

    //****************************************************************************************
    //****************************************************************************************



    @Override
    public void fillTableClientAdress() {
        //selectIdClient();
        selectIdAdress();
    }
}
