package br.edu.ifpb.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

@Singleton
public class ReaderCSV {

     public List<String> readCSV(String caminho){

        List<String> retorno = new ArrayList<>();

        String csvArquivo = caminho;

        BufferedReader conteudoCsv = null;

        String linha = "";

        try {
            conteudoCsv = new BufferedReader(new FileReader(csvArquivo));
            while ((linha = conteudoCsv.readLine()) != null) {
                retorno.add(linha);
            }
            return retorno;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: \n"+e.getMessage());
            return retorno;
        } catch (IOException e) {
            System.out.println("IO erro: \n"+e.getMessage());
            return retorno;
        } finally {
            if (conteudoCsv != null){
                try {
                    conteudoCsv.close();
                } catch (IOException e) {
                    System.out.println("IO erro: \n"+e.getMessage());
                }
            }
        }
    }
}