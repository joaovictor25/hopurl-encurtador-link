package br.com.axionweb.hopurl.repository;

import br.com.axionweb.hopurl.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository<id> extends JpaRepository<Link, id> {
    Link findByCode(String code);
}
