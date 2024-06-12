package ruby.Services.Order.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ruby.Services.Order.entities.*;

@Repository
public interface FactureRepository extends CrudRepository<Facture, Long> {

    //@Query(value = "SELECT * FROM facture WHERE type = ?1", nativeQuery = true)
    Facture findByType(String type);

    @Query(value = "SELECT * FROM facture WHERE etat_livraison = ?1", nativeQuery = true)
    Facture findByetat_livraison(String etat_livraison);

    //@Query(value = "SELECT * FROM facture WHERE date = ?1", nativeQuery = true)
    Facture findByDate(Date date);

    List<Facture> findByUser(User user);

}

