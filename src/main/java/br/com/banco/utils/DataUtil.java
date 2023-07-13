package br.com.banco.utils;

import br.com.banco.exceptions.DataIncorretaException;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class DataUtil {

    public Timestamp formataData(String data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Timestamp(dateFormat.parse(data).getTime());
        } catch (ParseException e) {
            try {
                throw new DataIncorretaException("Data inserida inválida: Erro na conversão das datas", e.getErrorOffset());
            } catch (DataIncorretaException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

