package ruby.Services.Order.services;

import java.util.List;

import ruby.Services.Order.entities.*;

public interface IFactureService {

    List<Facture> retrieveAllFacture();

    Facture updateFacture(Facture f);

    Facture retrieveFacture(Long id);

    void removeFacture(Long id);

    void imprimerFacture(Long FactureId);

    Facture addFacture(Facture f, Long idPaiement);

    List<Facture> retrieveFacturesByUser(Long idUser);

}

