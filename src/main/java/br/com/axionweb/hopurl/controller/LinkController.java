package br.com.axionweb.hopurl.controller;

import br.com.axionweb.hopurl.model.Link;
import br.com.axionweb.hopurl.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

    @Autowired
    LinkService linkService;


    @PostMapping("/api/urloriginal")
    public String encurtarLink(@RequestBody Link url){
        linkService.encurtarLink(url);
        return "";
    }
}
