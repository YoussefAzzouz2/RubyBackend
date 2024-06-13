package ruby.OrderService.services;

import java.util.List;

import ruby.OrderService.entities.*;

public interface IPaiementService {

    List<Paiement> retrieveAllPaiement();

    List<Paiement> retrievePaiementByUser(Long id);

    Paiement retrievePaiement(Long id);

    Paiement addPaiement(Paiement pa, Long id);

    List<panierProduit> detailPanier(Long Paiement_id);

}

