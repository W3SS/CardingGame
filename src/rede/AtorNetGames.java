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
import Control.EndStatus;
import Control.Game;


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
        } catch (NaoConectadoException ex) {}
        game.setNotConnected();
    }
    
    public void iniciarPartida() {
        try {
            proxy.iniciarPartida(2);
        } catch (NaoConectadoException ex) {
            game.setNotConnected();
        }
    }
    
    public void enviarJogada(Jogada jogada) {
        try {
            proxy.enviaJogada(jogada);
        } catch (NaoJogandoException ex) {}
    }
     
    @Override
    public void iniciarNovaPartida(Integer posicao) {
    	this.game.startNewMatch(posicao);
    }

    @Override
    public void finalizarPartidaComErro(String message) {
//    	game.alert(message);
    	game.endMatch(EndStatus.NETGAMES_PROBLEM);
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
    	game.alert("Conexão perdida");
       game.setNotConnected();
    }

    @Override
    public void tratarPartidaNaoIniciada(String message) {
        //game.exibeMensagem(message);
    }
    
}