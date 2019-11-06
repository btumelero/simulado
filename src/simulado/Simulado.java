/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author bruno.191196
 */
public class Simulado {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Roteador roteador = new Roteador();
    String arquivo = "mensagens.txt";
    try {
      FileReader fReader = new FileReader(arquivo);
      BufferedReader bReader = new BufferedReader(fReader);
      String linha;
      while ((linha = bReader.readLine()) != null) {
        roteador.recebeMensagem(roteador.leMensagem(linha));
      }
      bReader.close();
    }
    catch (FileNotFoundException ex) {
      System.out.println("Não foi possível abrir o aquivo '" + arquivo + "'");
    }
    catch (IOException e) {
      System.out.println("Erro ao ler o arquivo " + arquivo + "'");
    }
    byte filasNulas;
    do {
      filasNulas = 0;
      for (int i = 0; i < 4; i++) {
        roteador.enviaMensagem(roteador.portas.get(i));
        if (roteador.portas.get(i).isEmpty()) {
          filasNulas++;
        }
      }
    } while (filasNulas > 3);
  }
  
}
