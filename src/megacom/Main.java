package megacom;

import megacom.service.impl.CardRegistrationServiceImpl;

public class Main {

    public static void main(String[] args) {

        CardRegistrationServiceImpl cardRegistrationService = new CardRegistrationServiceImpl();
        cardRegistrationService.ConnectionSQLite();
        cardRegistrationService.createNewCard();
        cardRegistrationService.close();
    }
}
