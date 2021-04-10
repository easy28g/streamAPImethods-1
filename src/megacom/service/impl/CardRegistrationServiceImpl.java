package megacom.service.impl;

import megacom.models.Adress;
import megacom.models.Client;
import megacom.models.Contact;
import megacom.service.CardRegistrationService;

import java.sql.*;

import java.util.Scanner;

public class CardRegistrationServiceImpl implements CardRegistrationService {

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

    private int selectIdClient(int clientId, String firstname, String secondname){
        try{
            statement = connection.createStatement();

            String query = "SELECT id FROM clients " +
                    " WHERE client_id='"+clientId+"' " +
                    " AND firstname='"+firstname+"' " +
                    " AND secondname='"+secondname+"'";

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

    private int selectIdAdress(String city, String district, String street, String house){
        try{
            statement = connection.createStatement();

            String query = "SELECT id FROM adress " +
                    " WHERE city='"+city+"' " +
                    " AND district='"+district+"' " +
                    " AND street='"+street+"' " +
                    " AND house='"+house+"' ";

            ResultSet rs = statement.executeQuery(query);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
                //System.out.println(i);
            }

            if(i==0){
                throw new RuntimeException("Этого адреса нет в Базе Данных");
            }
            return i;
        }catch (SQLException e){
            throw new RuntimeException("Ошибка в функции получения ID адреса");
        }
    }

    private int selectIdContact(String phoneNumber, String email, int idClient){
        try{
            statement = connection.createStatement();

            String query = "SELECT id FROM contacts WHERE " +
                    " phone_number='"+phoneNumber+"' AND " +
                    " email='"+email+"' AND " +
                    " id_Client='"+idClient+"' ";
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
            throw new RuntimeException("Ошибка в функции при получении ID контакта");
        }
    }
    //----------------------------Функции получения ID клиента,адреса,контакта----------------

    //****************************************************************************************
    //****************************************************************************************


    //--------------------------Функции ввода данных клиента,адреса,контакта------------------

    private void insertClient(int clientId, String firstname, String secondname){
        try {
            String query = "INSERT INTO clients(client_id, firstname, secondname) " +
                    " VALUES('"+clientId+"', '"+firstname+"', '"+secondname+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка при вводе клиента в Базу Данных");
        }
    }

    private void insertAdress(String city, String district, String street, String house){
        try{
            String query = "INSERT INTO adress(city, district, street, house) " +
                    " VALUES('"+city+"', '"+district+"', '"+street+"', '"+house+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка при вводе адреса в Базу Данных");
        }
    }

    private void insertContact(String phoneNumber, String email, int idClient){
        try{
            String query = "INSERT INTO contacts(phone_number, email, id_Client) " +
                    " VALUES('"+phoneNumber+"', '"+email+"', '"+idClient+"')";

            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка при вводе контактов в Базу Данных");
        }
    }

    private void insertClientAdress(int idAdress, int idClient){
        try{
            String query = "INSERT INTO client_adress(id_adress, id_client) " +
                    " VALUES('"+idAdress+"', '"+idClient+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка при вводе в таблицу client_adress");
        }
    }

    //--------------------------Функции ввода данных клиента,адреса,контакта------------------


    //****************************************************************************************
    //****************************************************************************************

    @Override
    public void createNewCard() {
        System.out.println("Регистрация новой карты");

        Client client = scannerClient();
        Adress adress = scannerAdress();
        Contact contact = scannerContact();

        // передача данных КЛИЕНТА в функцию добавления внутрь БД
        insertClient(client.getClientId(), client.getFirstname(), client.getSecondname());

        // передача данных АДРЕСА в функцию добавления внутрь БД
        insertAdress(adress.getCity(), adress.getDistrict(), adress.getStreet(), adress.getHouse());

        int idClient = selectIdClient(client.getClientId(), client.getFirstname(), client.getSecondname());
        int idAdress = selectIdAdress(adress.getCity(), adress.getDistrict(), adress.getStreet(), adress.getHouse());

        //передача данных КОНТАКТА в функцию добавления внутрь БД
        insertContact(contact.getPhoneNumber(), contact.getEmail(), idClient);

        //заполнение таблицы client_adress
        insertClientAdress(idClient, idAdress);
    }

    //****************************************************************************************
    //****************************************************************************************




}
