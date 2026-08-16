package br.com.axionweb.hopurl.service;

import br.com.axionweb.hopurl.model.Link;
import br.com.axionweb.hopurl.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LinkService {

    @Autowired
    LinkRepository repository;

    private static final String BASE62 =
            "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public String encurtarLink(Link url){
        String codeGerado = gerarCode(url.getUrlOriginal());
        url.setCode(codeGerado);
        repository.saveAndFlush(url);
        return "";
    }

    public static String gerarCode(String url) {
        try {
            // 1. Gera o hash da URL
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(url.getBytes(StandardCharsets.UTF_8));

            // 2. Converte o hash para número
            BigInteger numero = new BigInteger(1, hash);

            // 3. Converte para Base62
            String base62 = converterBase62(numero);

            // 4. Retorna somente 7 caracteres
            return base62.substring(0, 7);

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String converterBase62(BigInteger numero) {
        StringBuilder resultado = new StringBuilder();
        BigInteger base = BigInteger.valueOf(62);
        while (numero.compareTo(BigInteger.ZERO) > 0) {

            BigInteger[] divisao = numero.divideAndRemainder(base);

            resultado.append(
                    BASE62.charAt(divisao[1].intValue())
            );

            numero = divisao[0];
        }

        return resultado.reverse().toString();
    }

    public String buscarUrlOriginal(String code){
        Link link = repository.findByCode(code);
        return link.getUrlOriginal();
    }


}
