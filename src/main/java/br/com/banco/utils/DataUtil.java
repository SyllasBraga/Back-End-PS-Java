package br.com.banco.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DataUtil {

    public Timestamp formataData(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Timestamp dataFormatada = new Timestamp(dateFormat.parse(data).getTime());
            return dataFormatada;
        } catch (ParseException e) {
            try {
                throw new ParseException("Erro na conversão das datas", e.getErrorOffset());
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
