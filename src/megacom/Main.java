package megacom;

import megacom.service.CardRegistrationService;
import megacom.service.impl.CardRegistrationServiceImpl;

public class Main {

    public static void main(String[] args) {

        CardRegistrationService cardRegistrationService = CardRegistrationServiceImpl.INSTANCE;
        cardRegistrationService.createNewCard();
    }
}
