package ruby.Services.Stock.Services;


import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;


import java.util.List;

import ruby.Services.Stock.Entities.Stock;

public interface IStockService {

    List<Stock> retrieveAllStock();

    Stock addStock (Stock s);

    void deleteStock (Long id);

    Stock updateStock (Stock s);

    Stock retrieveStock (Long id);

    List<Stock> listAll(String keyword);

    void status() throws MessagingException, IOException, jakarta.mail.MessagingException;

}
