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

/**
 *
 * @author jclos
 */
public class Fatura implements Serializable {

    private String data;
    private String placa;
    private int qtdHoras;
    private double faturaTotal;

    public String getData() {
        return data;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public double getFatura() {
        return faturaTotal;
    }

    public Fatura(String data, String placa, int qtdHoras, double fatura) {
        this.data = data;
        this.placa = placa;
        this.qtdHoras = qtdHoras;
        this.faturaTotal = fatura;
    }

    @Override
    public String toString() {
        return "Fatura{" + "data=" + data + ", placa=" + placa + ", qtdHoras=" + qtdHoras + ", faturaTotal=" + faturaTotal + '}';
    }


    /*Metodo que verifica se o arquivo/diretorio existe e serializa um Arraylist, em seguida armazena as informacoes do ArrayList de Fatura em um outro ArrayList e o retorna,
    caso contrario retorna um arraylist vazio para ser listado no jList*/
    public static ArrayList<Fatura> lerFaturas() {

        File f = new File("./" + Relogio.getDateNow().replaceAll("/", "") + "faturas.dat"); //Atribui o caminho das configurações a um File
        if (f.exists() && !f.isDirectory()) {

            ObjectInputStream inp;
            try {

                inp = new ObjectInputStream(new FileInputStream("./" + Relogio.getDateNow().replaceAll("/", "") + "faturas.dat"));

                ArrayList<Fatura> faturas = (ArrayList) inp.readObject();

                inp.close();
                return faturas;
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        ArrayList<Fatura> a = new ArrayList<Fatura>();//ArrayList devolvido caso não exista o aquivo
        return a;
    }

    public static ArrayList<Fatura> lerFaturasP(String data) {

        File f = new File("./" + Relogio.getDateNow().replaceAll("/", "") + "faturas.dat"); //Atribui o caminho das configurações a um File
        if (f.exists() && !f.isDirectory()) {

            ObjectInputStream inp;
            try {
                inp = new ObjectInputStream(new FileInputStream("./" + data.replaceAll("/", "") + "faturas.dat"));

                ArrayList<Fatura> faturas = (ArrayList) inp.readObject();

                inp.close();
                return faturas;
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

    public static ArrayList<Fatura> lerFaturasPM(String data, String data2) {
        File f = new File("./" + Relogio.getDateNow().replaceAll("/", "") + "faturas.dat"); //Atribui o caminho das configurações a um File
        if (f.exists() && !f.isDirectory()) {

            ObjectInputStream inp;
            try {
                inp = new ObjectInputStream(new FileInputStream("./" + data.replaceAll("/", "") + data2.replaceAll("/", "") + "faturas.dat"));
                ArrayList<Fatura> faturas = (ArrayList) inp.readObject();

                inp.close();
                return faturas;
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return null;
    }

}
