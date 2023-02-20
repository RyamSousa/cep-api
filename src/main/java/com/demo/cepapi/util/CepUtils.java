package com.demo.cepapi.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CepUtils {

    private CepUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String CEP_REGEX = "^[0-9]{5}-[0-9]{3}";

    public static boolean validCep(String cep) {
        String cepReplaced = cep.replace("-", "");
        StringBuffer finalCep = new StringBuffer(cepReplaced);
        finalCep.insert(5, "-");

        Pattern pattern = Pattern.compile(CEP_REGEX);
        Matcher matcher = pattern.matcher(finalCep);

        return matcher.matches();
    }
}
