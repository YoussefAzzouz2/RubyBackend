package ruby.Services.Paiement.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ruby.Services.Paiement.entities.*;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Long> {
	
	List<Paiement> findByUser(User user);

}