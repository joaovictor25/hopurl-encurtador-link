package br.com.axionweb.hopurl.model;

import jakarta.persistence.*;

@Table(name="link")
@Entity
public class Link {
//    private Integer id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name="code")
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
