/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Control.*;

/**
 *
 * @author rodolfolottin
 */
public class AtorNetGames implements OuvidorProxy{
    
    private Game game;
    private Proxy proxy;
    
    public AtorNetGames(Game game) {
       super();
       this.game = game; 
       this.proxy = proxy.getInstance();
       proxy.addOuvinte(this);
    }
    
    public boolean conectar(String nome, String servidor) {
        
        boolean retorno = true;
        try {
            proxy.conectar(servidor, nome);
        } catch (JahConectadoException ex) {
            ex.printStackTrace();
            retorno = false;
        } catch (NaoPossivelConectarException ex) {
            ex.printStackTrace();
            retorno = false;
        } catch (ArquivoMultiplayerException ex) {
            ex.printStackTrace();
            retorno = false;
        }
        
        return retorno;
    }
    
    public void desconectar() {
        try {
            proxy.desconectar();
        } catch (NaoConectadoException ex) {
           // game.exibeMensagem(ex.getMessage());
        }
    }
    
    public void iniciarPartida() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            //game.exibeMensagem(ex.getMessage());
        }
    }
    
    public void enviarJogada(Jogada jogada) {
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException ex) {
            //game.exibeMensagem(ex.getMessage());
        }
    }
    
//    public List<Jogador> getJogadores() {
//        List<Jogador> jogadores = new ArrayList<Jogador>();
//        if (game.getJogadorAtual().getNome().equals(proxy.obterNomeAdversario(1))) {
//            for (int i = 1; i <= 2; i++) {
//                Jogador jogador = null;
//                try {
//                    jogador = new Jogador(i, proxy.obterNomeAdversario(i));
//                    jogadores.add(jogador);
//                } catch (Exception e){
//                    System.out.println("Sem jogadores suficientes");
//                }
//            }
//        } else {
//            Jogador jogador = game.getJogadorAtual();
//            jogadores.add(jogador);
//            jogador = new Jogador(proxy.obterNomeAdversario(2));
//            jogadores.add(jogador);
//        }
//        
//        return jogadores;
//    }
    
    @Override
    public void iniciarNovaPartida(Integer posicao) {
    	this.game.startNewMatch(posicao);
    	//throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void finalizarPartidaComErro(String message) {
       //game.exibeMensagem(message);
      // game.limparTodosCampos();
    }

    @Override
    public void receberMensagem(String msg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberJogada(Jogada jogada) {
        game.receiveMove(jogada);
    }

    @Override
    public void tratarConexaoPerdida() {
       //game.exibeMensagem("Conexão foi perdida!");
       //game.limparTodosCampos();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        //game.exibeMensagem(message);
    }
    
}