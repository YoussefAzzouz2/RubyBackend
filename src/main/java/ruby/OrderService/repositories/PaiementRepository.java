package ruby.OrderService.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ruby.OrderService.entities.*;

@Repository
public interface PaiementRepository extends CrudRepository<Paiement, Long> {

	List<Paiement> findByUser(User user);

}
