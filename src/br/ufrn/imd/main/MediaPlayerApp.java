package br.ufrn.imd.main;

import javax.swing.SwingUtilities;
import br.ufrn.imd.gui.*;

public class MediaPlayerApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crie uma instância de MainGUI


            // Crie uma instância de LoginGUI e configure a MainGUI no construtor
            LoginGUI loginGUI = new LoginGUI();
            
            //inicializar tela (login)
            //login leva pra mainGUI(passando o objeto do usuario logado), maingui deve ter 2 botoes para acessar as telas de player e playlist
            //se o usuario for vip, maingui pode levar para playlist gui passando o objeto do usuario logado
            //por padrao pode acessar o playergui, pasasndo o objeto do usuario logado
            //playergui o usuario pode adicionar diretorios ou selecionar musica em um diretorio
            //playlist gui o usuario pode adicionar musica a playlist, remover musica da playlist ou tocar uma playlist

        });
    }
}
