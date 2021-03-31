package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.repository.ClientStockRepository;
import com.example.tsclientconnectivity.viewmodel.PortfolioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
public class StockController {

    @Autowired
    ClientStockRepository clientStockRepository;

    @GetMapping()
    public ResponseEntity<Object> allPortfolio(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        var repo=clientStockRepository.findAllByClientId(userId);
        return ResponseEntity.ok().body(repo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getstocksById(@PathVariable(name = "id")Long id){

        return ResponseEntity.ok().body("thumbs get one "+id);

    }
    @GetMapping("/{clientId}/product/{ticker}")
    public ResponseEntity<Object> getstocksByTicker(@PathVariable(name="clientId")Long clientId ,
                                                    @PathVariable(name = "ticker")String ticker){

        if(clientStockRepository.existsByTicker(clientId,ticker.toUpperCase())) return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();

    }
    
    @PostMapping("/market-change")
    public ResponseEntity<?> marketChange(){
        //check various status

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{stockId}/portfolio")
    public ResponseEntity<Object> addStocksToPortfolio(@PathVariable(name = "id")Long stockId,
                                                  @RequestBody PortfolioRequest viewModel){

        if(stockId==0L || viewModel.getPortfolioId()==0L) return ResponseEntity.badRequest().build();
        var data=clientStockRepository.findById(stockId);
        if(data.isEmpty()) return ResponseEntity.notFound().build();
        var newdata=data.get();
        newdata.setPortfolioId(viewModel.getPortfolioId());
        clientStockRepository.save(newdata);
        return ResponseEntity.ok().build();
    }


//    @PostMapping()
//    public ResponseEntity<Object> createstocks(@RequestBody stocksRequest viewModel){
//        if(viewModel.stocksName.isBlank()) return ResponseEntity.badRequest().build();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        var userId=((Client)auth.getPrincipal()).getId();
//        stocksRepository.save(new stocks(viewModel.stocksName,userId));
//        // TODO://report to reportng service
//        return ResponseEntity.ok().build();
//
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updatestocks(@PathVariable(name = "id")Long id,
//                                                  @RequestBody stocksRequest viewModel){
//
//        if(id==0L || viewModel.stocksName.isBlank()) return ResponseEntity.badRequest().build();
//        var data=stocksRepository.findById(id);
//        if(data.isEmpty()) return ResponseEntity.notFound().build();
//        var newdata=data.get();
//        newdata.setName(viewModel.stocksName);
//        stocksRepository.save(newdata);
//        return ResponseEntity.ok().build();
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> stocksDelete(@PathVariable(name = "id")Long id){
//        if(id==0L) return ResponseEntity.badRequest().build();
//        if (stocksRepository.existsById(id)){
//            stocksRepository.deleteById(id);
//            return ResponseEntity.ok().build();
//        }
//        return ResponseEntity.notFound().build();
//
//    }


}
