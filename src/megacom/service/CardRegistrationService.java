package megacom.service;


import megacom.service.impl.CardRegistrationServiceImpl;

public interface CardRegistrationService {
    CardRegistrationService INSTANCE = new CardRegistrationServiceImpl();
    void createNewCard();
}
