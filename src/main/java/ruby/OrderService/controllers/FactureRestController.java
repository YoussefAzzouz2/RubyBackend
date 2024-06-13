package ruby.OrderService.controllers;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ruby.OrderService.entities.*;
import ruby.OrderService.services.*;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/facture")
public class FactureRestController {

    @Autowired
    IFactureService factureService;


    IPaiementService PaiService;

    @Autowired
    IPanierProduitService panierProduitService;


    //done
    // http://localhost:8080/SpringMVC/facture/retrieve-all-facture
    @GetMapping("/retrieve-all-facture")
    @ResponseBody
    public List<Facture> getFacture() {
        List<Facture> listFacture = factureService.retrieveAllFacture();
        return listFacture;
    }


    //http://localhost:8080/SpringMVC/facture/retrieve-facture/{facture-id}
    @GetMapping("/retrieve-facture/{facture-id}")
    @ResponseBody
    public Facture retrieveFacture(@PathVariable("facture-id") Long factureId) {
        return factureService.retrieveFacture(factureId);
    }

    //http://localhost:8080/SpringMVC/facture/retrieve-facture/{user-id}
    @GetMapping("/retrieve-factures-by-user/{user-id}")
    @ResponseBody
    public List<Facture> retrieveFacturesByUser(@PathVariable("user-id") Long userId) {
        return factureService.retrieveFacturesByUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
        modelMap.put("facture", factureService.retrieveAllFacture());
        return "facture/index";
    }

    //done
    //http://localhost:8080/SpringMVC/facture/add-facture
    @PostMapping("/add-facture/{Paiement-id}")
    @ResponseBody
    public Facture addFacture(@RequestBody Facture f,@PathVariable("Paiement-id") Long idPaiement){
        Facture Facture = factureService.addFacture(f,idPaiement);
        return Facture;
    }

    //done
    //http://localhost:8080/SpringMVC/facture/remove-facture/{facture-id}
    @DeleteMapping("/remove-facture/{facture-id}")
    @ResponseBody
    public void removeFacture(@PathVariable("facture-id") Long factureId) {
        factureService.retrieveFacture(factureId);
    }

    //done
    //http://localhost:4200/SpringMVC/facture/update-facture
    @PutMapping("/update-facture")
    @ResponseBody
    public Facture updateFacture(@RequestBody Facture f){
        Facture Facture = factureService.updateFacture(f);
        return Facture;
    }


    //http://localhost:8080/SpringMVC/facture/printPDF/{facture-id}
    @RequestMapping(value = "/printPDF/{facture-id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> factureReport(@PathVariable("facture-id") Long idFacture) {

        Facture f = factureService.retrieveFacture(idFacture);
        List<panierProduit> panier = PaiService.detailPanier(f.getPaiement().getIdPaiement());
        ByteArrayInputStream bis = GeneratePdfReport.factureReport(f,panier);

        var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=facture.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}


