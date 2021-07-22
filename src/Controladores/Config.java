/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author jclos
 */
public class Config implements Serializable {

    private double ValorCHora;
    private int tolerancia;
    private DefaultListModel<String> models;

    public Config(double ValorCHora, int tolerancia, DefaultListModel<String> models) {
        this.ValorCHora = ValorCHora;
        this.tolerancia = tolerancia;
        this.models = models;
    }

    public DefaultListModel<String> getModels() {
        return models;
    }

    public double getValorCHora() {
        return ValorCHora;
    }

    public int getTolerancia() {
        return tolerancia;
    }

    /*Este metodo recebe duas variavéis de horário, exemplo: 12:15:23 separa e armazena em um vetor e transforma de STRING para DOUBLE, em seguida pega o valor
    em seguida faz a diferenca de horas entre o horario de entrada e saida e multiplica pelo preco da hora e adiciona tolerancia, caso a condicao seja verdadeira
     */
    public double calculaTolerancia(String entrada, String saida) {
        String entrada1[] = new String[3];
        entrada1 = entrada.split(":");
        String saida1[] = new String[3];
        saida1 = saida.split(":");

        double minutos = ((Double.parseDouble(saida1[0]) - Double.parseDouble(entrada1[0]))) * 60 + (Double.parseDouble(saida1[1]) - Double.parseDouble(entrada1[1]));
        //

        double resultado = (minutos - (minutos % 60)) / 60 * ValorCHora;

        if (minutos % 60 > tolerancia) {
            resultado += ValorCHora;
        }

        return resultado;

    }

    /*Quase a mesma funcao que a de cima, so que retorna um inteiro somente com as horas contabilizadas, ou seja hora + tolerancia(caso tenha ultrapassado)*/
    public int calculaTempo(String entrada, String saida) {
        String entrada1[] = new String[3];
        entrada1 = entrada.split(":");
        String saida1[] = new String[3];
        saida1 = saida.split(":");

        int minutos = ((Integer.parseInt(saida1[0]) - Integer.parseInt(entrada1[0]))) * 60 + (Integer.parseInt(saida1[1]) - Integer.parseInt(entrada1[1]));
        //

        int resultado = (minutos - (minutos % 60)) / 60;

        if (minutos % 60 > tolerancia) {
            resultado++;
        }

        return resultado;

    }

    /*Metodo que verifica se o arquivo/diretorio existe, em seguida armazena as informacoes da classe Config em uma variavel config do tipo Config*/
    public static Config lerConfig() {

        File f = new File("./config.dat"); //Atribui o caminho das configurações a um File
        if (f.exists() && !f.isDirectory()) {

            ObjectInputStream inp;
            try {
                inp = new ObjectInputStream(new FileInputStream("./config.dat"));
                Config config = (Config) inp.readObject();
                inp.close();
                return config;
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return null;
    }

}
