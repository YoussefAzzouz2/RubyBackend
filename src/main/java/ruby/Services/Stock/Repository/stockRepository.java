package ruby.Services.Stock.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ruby.Services.Stock.Entities.Stock;

@Repository
public interface stockRepository extends CrudRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s where s.quantite< s.quantiteMin")
    List<Stock> status();

    @Query("SELECT s FROM Stock s WHERE CONCAT(s.libelleStock, ' ', s.supplier_name) LIKE %?1%")
    public List<Stock> search(String keyword);



}
