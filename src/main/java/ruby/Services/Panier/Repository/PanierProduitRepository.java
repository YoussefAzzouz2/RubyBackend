package ruby.Services.Panier.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ruby.Services.User.Entities.User;
import ruby.Services.Produit.Entities.produit;
import ruby.Services.Panier.Entities.panierProduit;

@Repository
@Transactional
public interface PanierProduitRepository extends CrudRepository<panierProduit, Long> {

	List<panierProduit> findByUser(User user);
	
	panierProduit findByUserAndProduit(User user, produit Produit);	
	
	@Query("DELETE FROM panierProduit pp WHERE pp.user.id = ?1 AND pp.produit.idProduit = ?2")
	@Modifying
	void deleteByUserAndProduit(Long id, Long idProduit);

}