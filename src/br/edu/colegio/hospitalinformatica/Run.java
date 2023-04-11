package br.edu.colegio.hospitalinformatica;

import br.edu.colegio.hospitalinformatica.view.FramePrincipal;
import javax.swing.JFrame;

public class Run {

    public static void main(String[] args) {
        
        FramePrincipal framePrincipal = new FramePrincipal();
        framePrincipal.setLocationRelativeTo(null);
        framePrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        framePrincipal.setVisible(true);
    }

}
