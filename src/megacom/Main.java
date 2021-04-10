package megacom;

import megacom.service.CardRegistrationService;
import megacom.service.FillTableClientAdress;
import megacom.service.impl.CardRegistrationServiceImpl;

public class Main {

    public static void main(String[] args) {

        FillTableClientAdress cardRegistrationService = new CardRegistrationServiceImpl();
        ((CardRegistrationServiceImpl) cardRegistrationService).ConnectionSQLite();
        cardRegistrationService.fillTableClientAdress();
        ((CardRegistrationServiceImpl) cardRegistrationService).close();
    }
}
