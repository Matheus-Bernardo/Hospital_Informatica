
package br.edu.colegio.hospitalinformatica.util;

import java.awt.Component;
import javax.swing.JOptionPane;

public class Mensagens {
    public static void showMessageWarning(Component parent, String mensagem) {
        JOptionPane.showMessageDialog(parent, mensagem, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
    public static void showMessageError(Component parent, String mensagem) {
        JOptionPane.showMessageDialog(parent, mensagem, "Falha", JOptionPane.ERROR_MESSAGE);
    }
    public static void showMessageInfo(Component parent, String mensagem) {
        JOptionPane.showMessageDialog(parent, mensagem, "Informação", JOptionPane.INFORMATION_MESSAGE);
    }
    public static int showMessageQuestion(Component parent, String mensagem) {
        return JOptionPane.showConfirmDialog(parent, mensagem, "Confirmação", JOptionPane.YES_NO_OPTION);
    }

}
