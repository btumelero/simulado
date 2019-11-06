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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author bruno.191196
 */
public class Roteador {
  Map<Integer, LinkedList> portas;
  
  public Mensagem leMensagem (String linha) {
    Mensagem mensagem = new Mensagem();
    mensagem.id = Integer.parseInt(String.valueOf(linha.charAt(0)));
    linha = linha.substring(linha.indexOf(" ") + 1);
    mensagem.portaOrigem = Integer.parseInt(String.valueOf(linha.charAt(0)));
    linha = linha.substring(linha.indexOf(" ") + 1);
    mensagem.portaDestino = Integer.parseInt(String.valueOf(linha.charAt(0)));
    linha = linha.substring(linha.indexOf(" ") + 1);
    mensagem.texto = linha;
    return mensagem;
  }
  
  public void recebeMensagem (Mensagem mensagem) {
    portas.get(mensagem.portaDestino).add(mensagem);
    System.out.println("Mensagem " + mensagem.id + " chegando na porta " + mensagem.portaOrigem + ", com destino porta " + mensagem.portaDestino + ", com texto: " + mensagem.texto);
    if (portas.get(mensagem.portaDestino).size() > 4) {
      System.out.println("Mensagem " + ((Mensagem) portas.get(mensagem.portaDestino).peek()).id + " perdida devido a estouro da fila.");
      portas.get(mensagem.portaDestino).poll();
    }
  }
  
  public void enviaMensagem (LinkedList filaMensagens) {
    for (int i = 0; i < 2 && filaMensagens.isEmpty() == false; i ++) {
      System.out.println("Mensagem " + ((Mensagem) filaMensagens.peek()).id + " sendo enviada através da porta "
                       +((Mensagem) filaMensagens.peek()).portaDestino + ", com texto: " + ((Mensagem) filaMensagens.peek()).texto);
      filaMensagens.poll();
    }
  }
  
  public Roteador () {
    this.portas = new HashMap<>();
    for (int i = 0; i < 4; i++) {
      this.portas.put(i, new LinkedList<>());
    }
  }
}
