package ruby.OrderService.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruby.OrderService.entities.Facture;
import ruby.OrderService.repositories.*;
import ruby.OrderService.entities.*;

@Service
public class FactureServiceImpl implements IFactureService{

    @Autowired
    FactureRepository FactureRepo;

    @Autowired
    UserRepository UserRepo;

    @Autowired
    PaiementRepository PaiementRepo;

    @Override
    public List<Facture> retrieveAllFacture() {
        // TODO Auto-generated method stub
        return (List<Facture>) FactureRepo.findAll();
    }

    @Override
    public Facture updateFacture(Facture f) {
        Facture fa = FactureRepo.findById(f.getIdFacture()).orElse(null);
        fa.setType(f.getType());
        fa.setEtat_livraison(f.getEtat_livraison());
        return FactureRepo.save(f);
    }

    @Override
    public Facture addFacture(Facture f, Long idPaiement) {
        // TODO Auto-generated method stub
        Paiement p = PaiementRepo.findById(idPaiement).orElse(null);
        f.setPaiement(p);
        f.setUser(p.getUser());
        f.setType(p.getNature());
        f.setEtat_livraison("Non Livré");
        f.setDate(new Date());
        FactureRepo.save(f);
        return f;
    }

    @Override
    public Facture retrieveFacture(Long idFacture) {
        // TODO Auto-generated method stub
        return FactureRepo.findById(idFacture).orElse(null);
    }

    @Override
    public List<Facture> retrieveFacturesByUser(Long idUser) {
        // TODO Auto-generated method stub
        User u = UserRepo.findById(idUser).orElse(null);
        List<Facture> Factures = FactureRepo.findByUser(u);
        return Factures;
    }

    @Override
    public void removeFacture(Long idFacture) {
        // TODO Auto-generated method stub
        FactureRepo.deleteById(idFacture);
    }

    @Override
    public void imprimerFacture(Long idFacture) {
        // TODO Auto-generated method stub

    }

}

