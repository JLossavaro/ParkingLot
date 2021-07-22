/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author jclos
 */
public class Relogio {


    /*Este metodo utiliza a biblioteca Calendar para conseguir data e horário, em seguida coloca em um date para em seguida formatar utilizando o 
  DateFormat, e assim retornando uma hora como String, o que ajuda a passar como paramentro para a thread atualizar o horário  */
    public static String getTimeNow() {
        Calendar c = Calendar.getInstance();
        Date data = c.getTime();
        DateFormat hora = DateFormat.getTimeInstance();
        String horas = hora.format(data);
        return horas;
    }

    /*Metodo que recupera a Data atual pela biblioteca Date, tambem a formatando utilizando o SImpleDateFormat, e assim só retornando uma String,
    facilita bastante caso seja necessário utilizar a data em algum outro campo, já que fica bem facil de recuperar com este método */
    public static String getDateNow() {
        Date data = new Date();
        SimpleDateFormat dateForm = new SimpleDateFormat("dd/MM/Y");
        String dataa = dateForm.format(data);
        return dataa;
    }

}
