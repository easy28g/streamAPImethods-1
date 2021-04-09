package megacom;

import megacom.models.Contact;
import megacom.service.AddClientService;
import megacom.service.CardRegistrationService;
import megacom.service.impl.CardRegistrationServiceImpl;

public class Main {

    public static void main(String[] args) {
//        CardRegistrationService cardRegistrationService = CardRegistrationServiceImpl.INSTANCE;
//        cardRegistrationService.addCard();


        CardRegistrationServiceImpl addClientService = new CardRegistrationServiceImpl();

        addClientService.ConnectionSQLite(); // connection DB

        //Добавление Клиента
        //addClientService.addClient();
        //addClientService.selectClientFromFS();


        addClientService.close(); // close DB
    }
}
