package companies;

import java.util.List;

public interface BoutiqueCompany {
    void requestConsultant(String name);

    void bookFittingRoom(int roomId);

    List<String> listPaymentMethods();
}
