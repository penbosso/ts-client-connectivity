package com.example.tsclientconnectivity.controller;

import com.example.tsclientconnectivity.model.Client;
import com.example.tsclientconnectivity.model.Portfolio;
import com.example.tsclientconnectivity.repository.PortfolioRepository;
import com.example.tsclientconnectivity.viewmodel.PortfolioRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
@AllArgsConstructor
public class PortfolioController {
    
    private final PortfolioRepository portfolioRepository;
    //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //var userId=((Client)auth.getPrincipal()).getId();

    @GetMapping()
    public ResponseEntity<Object> allPortfolio(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        var repo=portfolioRepository.findAllByClientId(userId);
        return ResponseEntity.ok().body(repo);
    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPortfolioById(@PathVariable(name = "id")Long id){

        return ResponseEntity.ok().body("thumbs get one "+id);

    }

    @PostMapping()
    public ResponseEntity<Object> createPortfolio(@RequestBody PortfolioRequest viewModel){
        if(viewModel.portfolioName.isBlank()) return ResponseEntity.badRequest().build();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        var userId=((Client)auth.getPrincipal()).getId();
        portfolioRepository.save(new Portfolio(viewModel.portfolioName,userId));
        return ResponseEntity.ok().build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePortfolio(@PathVariable(name = "id")Long id,
                                            @RequestBody PortfolioRequest viewModel){

        if(id==0L || viewModel.portfolioName.isBlank()) return ResponseEntity.badRequest().build();
        var data=portfolioRepository.findById(id);
        if(data.isEmpty()) return ResponseEntity.notFound().build();
        var newdata=data.get();
        newdata.setName(viewModel.portfolioName);
        portfolioRepository.save(newdata);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> portfolioDelete(@PathVariable(name = "id")Long id){
        if(id==0L) return ResponseEntity.badRequest().build();
        if (portfolioRepository.existsById(id)){
            portfolioRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();

    }

}
