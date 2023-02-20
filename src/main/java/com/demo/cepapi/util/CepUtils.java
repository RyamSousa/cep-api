package com.demo.cepapi.util;

import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.c;

@Slf4j
public class CepUtils {

    private CepUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String CEP_REGEX = "^[0-9]{5}-[0-9]{3}";

    public static boolean validCep(String cep){
        String cepReplaced = cep.replace("-", "");
        StringBuffer finalCep = new StringBuffer(cepReplaced);
        finalCep.insert(5, "-");

        Pattern pattern = Pattern.compile(CEP_REGEX);
        Matcher matcher = pattern.matcher(finalCep);

        log.info(String.format("cep: %s, valid? %s",  finalCep, matcher.matches()));
        return matcher.matches();
    }
}
