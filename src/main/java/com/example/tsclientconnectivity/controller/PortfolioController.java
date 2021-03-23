package com.example.tsclientconnectivity.controller;

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
    private final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    //var userId=((Client)auth.getPrincipal()).getId();
    @GetMapping()
    public ResponseEntity<Object> portfolio(){
        
        return ResponseEntity.ok().body("thumbs");
    
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> portfolio(@PathVariable(name = "id")String id){

        return ResponseEntity.ok().body("thumbs get one "+id);

    }

    @PostMapping()
    public ResponseEntity<Object> portfolio(@RequestBody PortfolioRequest viewModel){
        if(viewModel.portfolioName.isBlank()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body("post");

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> portfolio(@PathVariable(name = "id")String id,
                                            @RequestBody PortfolioRequest viewModel){

        if(id.isBlank() || viewModel.portfolioName.isBlank()) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok().body("put update");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> portfolioDelete(@PathVariable(name = "id")String id){
        if(id.isBlank()) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body("thumbs delete mapping  "+id);

    }

}
