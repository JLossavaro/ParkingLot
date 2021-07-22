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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jclos
 */
public class VagaReservada implements Serializable {

    private int numVaga;
    private boolean vagaReservada;

    public String getPlaca() {
        return placa;
    }
    private String placa;
    private String entrada;

    public boolean isVagaReservada() {
        return vagaReservada;
    }

    public int getNumVaga() {
        return numVaga;
    }

    public VagaReservada(int numVaga, boolean vagaReservada, String placa, String entrada) {
        this.numVaga = numVaga;
        this.vagaReservada = vagaReservada;
        this.placa = placa;
        this.entrada = entrada;
    }

    public String getEntrada() {
        return entrada;
    }

    public VagaReservada(int numVaga, boolean vagaReservada) {
        this.numVaga = numVaga;
        this.vagaReservada = vagaReservada;
    }

    /*Metodo toString utilizado para testes*/
    @Override
    public String toString() {
        String myString = "";
        myString += "placa " + placa;
        myString += "\nnumVaga " + numVaga;
        myString += "\nentrada " + entrada;

        return myString;
    }

    
    /*Metodo responsavel por ler as vagas(ArrayList) e atribuir a um ArrayList, alem de verificar a existencia do caminho/arquivo,
    caso esteja vazio ele cria um ArrayList padrao para que nao haja conflitos*/
    
    public static ArrayList<VagaReservada> lerVagas() {

        File f = new File("./vagas.dat"); //Atribui o caminho das configurações a um File
        if (f.exists() && !f.isDirectory()) {

            ObjectInputStream inp;
            try {

                inp = new ObjectInputStream(new FileInputStream("./vagas.dat"));

                ArrayList<VagaReservada> vagas = (ArrayList) inp.readObject();

                inp.close();
                return vagas;
            } catch (IOException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        ArrayList<VagaReservada> a = new ArrayList<VagaReservada>();
        return a;
    }
}
