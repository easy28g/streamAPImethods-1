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

    private int getIdClient(){
        try{
            statement = connection.createStatement();

            // scannerClient() функция ввода клиента
            Client client = scannerClient();

            String query = "SELECT id FROM clients WHERE client_id='"+client.getClientId()+"' AND " +
                    " firstname='"+client.getFirstname()+"' AND secondname='"+client.getSecondname()+"'";
            ResultSet rs = statement.executeQuery(query);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
            }
            if(i==0){
                throw new RuntimeException("Такого клиента нет в Базе Данных");
            }
            return i;
        }catch (SQLException e){
            throw new RuntimeException("Ошибка в функции получения ID клиента");
        }

    }

    @Override
    public void createNewCard() {
        Client client = scannerClient();
        Adress adress = scannerAdress();
        Contact contact = scannerContact();
    }

    @Override
    public void fillTableClientAdress() {
        getIdClient();
    }
}
