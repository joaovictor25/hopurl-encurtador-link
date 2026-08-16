package br.com.axionweb.hopurl.controller;

import br.com.axionweb.hopurl.model.Link;
import br.com.axionweb.hopurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class LinkController {

    @Autowired
    LinkService linkService;


    @PostMapping("/api/links")
    public String encurtarLink(@RequestBody Link url){
        linkService.encurtarLink(url);
        return "";
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> buscarUrlOriginal(@PathVariable String code) {
        String urlOriginal = linkService.buscarUrlOriginal(code);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(urlOriginal));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }
}
