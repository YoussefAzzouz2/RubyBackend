package ruby.Services.Panier.Services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ruby.Services.User.Entities.User;
import ruby.Services.Panier.Entities.panierProduit;
import ruby.Services.Produit.Entities.produit;
import ruby.Services.Panier.Repository.PanierProduitRepository;
import ruby.Services.User.Repository.UserRepository;
import ruby.Services.Produit.Repository.produitRepository;
@Service
public class panierProduitServiceImpl implements IPanierProduitService{
	
	@Autowired
	PanierProduitRepository PanierProdRepo;
	
	@Autowired
	UserRepository UserRepo;
	
	@Autowired
	produitRepository ProduitRepo;


	@Override
	public panierProduit addProduit(panierProduit pp, Long idProduit, int quantity, Long id) {
		// TODO Auto-generated method stub
		produit p = ProduitRepo.findById(idProduit).get();
		User u = UserRepo.findById(id).get();
		int addedQte = quantity;
		
		pp = PanierProdRepo.findByUserAndProduit(u, p);
		if (pp != null) {
			addedQte = pp.getQuantity() + quantity;
			pp.setQuantity(addedQte);
			pp.setSomme(pp.getSomme()+(p.getPrix()*quantity));
		}else {
			pp = new panierProduit();
			pp.setQuantity(quantity);
			pp.setProduit(p);
			pp.setUser(u);
			pp.setSomme(p.getPrix()*quantity);
		}
		PanierProdRepo.save(pp);
	    return pp;
	}

	
	@Override
	public void removeProduit(User user,Long idProduit) {
		// TODO Auto-generated method stub
		produit p = ProduitRepo.findById(idProduit).get();
		//User u = UserRepo.findById(id).get();
		PanierProdRepo.deleteByUserAndProduit(user.getId(),idProduit);
	}

	@Override
	public panierProduit updateQte(panierProduit pp) {
		// TODO Auto-generated method stub
		panierProduit pproduit = PanierProdRepo.findById(pp.getIdPanierProduit()).get();
		produit p = ProduitRepo.findById(pp.getProduit().getIdProduit()).get();
		
		pproduit.setQuantity(pp.getQuantity());
		//pproduit.setSomme(p.getPrix()*pp.getQuantity());
		pproduit.setUser(pp.getUser());
		pproduit.setProduit(pp.getProduit());
		PanierProdRepo.save(pp);
	    return pp;
	}

	
	@Override
	public List<panierProduit> detailPanier(Long user_id) {
		User u = UserRepo.findById(user_id).get();
		List<panierProduit> panier = PanierProdRepo.findByUser(u);
		return panier;
	}
	
	@Override
	public panierProduit retrievePanierProduit(Long idPanierProduit) {
		panierProduit panier = PanierProdRepo.findById(idPanierProduit).orElse(null);
		return panier;
	}

	
}
