package ruby.Services.Paiement.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruby.Services.Paiement.entities.*;
import ruby.Services.Paiement.repositories.*;


@Service 
public class PaiementServiceImpl implements IPaiementService{
	
	@Autowired
	panierProduitRepository PanierProdRepo;
	
	@Autowired
	UserRepository UserRepo;
	
	@Autowired
	PaiementRepository PaiRepo;
	
	@Autowired
	StockRepository StockRepo;
	
	@Override
	public List<Paiement> retrieveAllPaiement() {
		// TODO Auto-generated method stub
		return (List<Paiement>) PaiRepo.findAll();
	}

	@Override
	public Paiement addPaiement(Paiement pa, Long id) {
		// TODO Auto-generated method stub
		User u = UserRepo.findById(id).orElse(null);
		List<panierProduit> produits = PanierProdRepo.findByUser(u);
		pa.setNature(pa.getNature());
		pa.setSommetotal(pa.getSommetotal());
		pa.setDate(new Date());
		pa.setCard(pa.getCard());
		pa.setCvc(pa.getCvc());
		pa.setExp(pa.getExp());
		pa.setUser(u);
		pa.setProduits(produits);
		PaiRepo.save(pa);
		return pa;
	}

	@Override
	public Paiement retrievePaiement(Long idPaiement) {
		// TODO Auto-generated method stub
		return PaiRepo.findById(idPaiement).orElse(null);
	}
	
	
	@Override
	public List<Paiement> retrievePaiementByUser(Long id) {
		// TODO Auto-generated method stub
		User u = UserRepo.findById(id).orElse(null);
		return PaiRepo.findByUser(u);
	}
	
	@Override
	public List<panierProduit> detailPanier(Long paiement_id) {
		Paiement p = PaiRepo.findById(paiement_id).get();
		List<panierProduit> panier = p.getProduits();
		return panier;
	}

	
}