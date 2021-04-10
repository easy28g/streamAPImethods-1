package megacom.service.impl;

import megacom.models.Adress;
import megacom.models.Client;
import megacom.service.AddClientService;
import megacom.service.CardRegistrationService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CardRegistrationServiceImpl
        implements CardRegistrationService, AddClientService {

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
    //----------------------------------------------------------------------------------------



    @Override
    public void addCard() {

    }

    //------------------------------Таблица clients-------------------------------------------
    //Вывод клиента по Имени и Фамилии
    @Override
    public void selectClientFromFS(){
        System.out.print("Введите имя: ");
        String fname = scanner.next();
        System.out.print("Введите фамилию: ");
        String sname = scanner.next();

        ArrayList<HashMap> fio = selectClient(fname,sname);
        //fio.forEach(System.out::println);

        // Получения HashMap<Key, Values> из списка

        for(int i=0; i<fio.size(); i++){
            HashMap<Integer, String> fioMap = fio.get(i);
            for(Map.Entry entry: fioMap.entrySet()){
                System.out.println("ID-"+entry.getKey()+": "+entry.getValue());
            }
        }

    }

    // Получение ID клиентов из Базы данных по Имени и Фамилии
    public ArrayList<HashMap> selectClient(String firstname, String secondname) {
        try {
            statement = connection.createStatement();

            String query = "SELECT client_id, firstname, secondname FROM clients WHERE " +
                    "firstname = '"+firstname+"' AND secondname = '"+secondname+"'";
            ResultSet rs = statement.executeQuery(query);

            int i=0; String fname, sname; // Инициализация переменных

            HashMap<Integer,String> idFSname = new HashMap<>();
            ArrayList<HashMap> idFSnameList = new ArrayList<>();
            //ArrayList<Integer> idClient = new ArrayList<>();
            //ArrayList<String> fsname = new ArrayList<>();

            while (rs.next()){
                i = rs.getInt("client_id");
                fname = rs.getString("firstname");
                sname = rs.getString("secondname");
                //idClient.add(i); // Список ID клиентов в классе
                //fsname.add(fname+" "+sname); // Список Имен и Фамилий клиентов
                //idFSname = new HashMap<>(); // Обновляю
                idFSname.putIfAbsent(i,fname+" "+sname); // Мап из ID клиента и значения ФИО
                idFSnameList.add(idFSname); // Список мапов из списков
            }
            if(i==0){
                System.out.println("Такого клиента нет в Базе Данных!");
            }
            rs.close();
            return idFSnameList;
        }catch (SQLException throwables){
            throw new RuntimeException("Ошибка при запросе выводе 'Клиента'");
        }
    }



    //------------------------------Таблица client_adress-------------------------------------
    // Внутренний метод добавления адреса
    private void addAdress() {
        try {
            System.out.print("Введите город: ");
            String city = scanner.next();
            System.out.print("Введите район: ");
            String district = scanner.next();
            System.out.print("Введите улицу: ");
            String street = scanner.next();
            System.out.print("Введите дом: ");
            String house = scanner.next();

            Adress adress = new Adress(city, district, street, house);

            String query = "INSERT INTO adress(city, district, street, house) " +
                    " VALUES('"+adress.getCity()+"', '"+adress.getDistrict()+"', " +
                    " '"+adress.getStreet()+"', '"+adress.getHouse()+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);

            fillClientAdressTable(city, district, street, house);

        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении 'Адресса'");
            //throwables.printStackTrace();
        }
    }

    private int getIdAdress(String city, String district, String street, String house){

        try {
            statement = connection.createStatement();
            String queryIdAdress = "SELECT id FROM adress WHERE" +
                    " city='"+city+"' AND district='"+district+"' AND" +
                    " street='"+street+"', house='"+house+'"';
            ResultSet rs = statement.executeQuery(queryIdAdress);
            //ArrayList<Integer> listIdAdress = new ArrayList<>();
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
                //listIdAdress.add(i);
            }
            return i;
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
            throw new RuntimeException("Ошибка при получении ID адреса в методе 'getIdAdress'");
        }
    }

    // Внутренний метод добавления клиента в Базу Данных
    private int addClient() {

        try {
            System.out.print("Введите имя: ");
            String fname = scanner.next();
            System.out.print("Введите фамилию: ");
            String sname = scanner.next();

            Client client = new Client(fname,sname);

            String query = "INSERT INTO clients(client_id, firstname, secondname)" +
                    " VALUES ('"+client.getClientId()+"','"+client.getFirstname()+"', '"+client.getSecondname()+"')";

            String queryIdClient = "SELECT id FROM clients WHERE" +
                    " client_id='"+client.getClientId()+"' AND " +
                    " firstname='"+client.getFirstname()+"' AND " +
                    " secondname='"+client.getSecondname()+"'";
            ResultSet rs = statement.executeQuery(queryIdClient);
            int i=0;
            while (rs.next()){
                i = rs.getInt("id");
            }

            statement = connection.createStatement();
            statement.executeUpdate(query);
            return i;

        } catch (SQLException throwables) {
            throw new RuntimeException("Ошибка при добавлении 'Клиента'");
        }

    }

    // Связывание клиента и адреса в общей таблице по их ID
    private void fillClientAdressTable(String city, String district, String street, String house) {

        int idAdress = getIdAdress(city, district, street, house);

        try {
            String query = "INSERT INTO client_adress(id_adress, id_client)" +
                    " VALUES('"+idAdress+"')";

            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            System.out.println("Ошибка в методе fillClientAdressTable");
        }
    }
    //----------------------------------------------------------------------------------------


}
