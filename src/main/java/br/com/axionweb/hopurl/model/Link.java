package br.com.axionweb.hopurl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="link")
@Entity
public class Link {
//    private Integer id;
    @Id
    private String code;

    @Column(name="url_original")
    private String urlOriginal;


    public String getUrlOriginal(){
        return urlOriginal;
    }
    public String getCode(){
        return code;
    }
    public void setCode(String code){
        this.code = code;
    }
}
