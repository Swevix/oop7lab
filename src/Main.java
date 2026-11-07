import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public final class Main {
    public static JFrame createWindow() {
        PlayersView view = new PlayersView();
        new PlayersController(new PlayersModel(), view);
        JFrame frame = new JFrame("Лабораторная № 7 — Список игроков");
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setJMenuBar(view.menu);
        frame.setContentPane(view);
        Runnable close = () -> {
            if (JOptionPane.showConfirmDialog(frame, "Вы действительно хотите выйти?",
                "Подтверждение выхода", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)
                frame.dispose();
        };
        view.exit.addActionListener(e -> close.run());
        frame.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(WindowEvent e) { close.run(); }
        });
        frame.pack();
        frame.setMinimumSize(new Dimension(780, 600));
        frame.setLocationRelativeTo(null);
        return frame;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createWindow().setVisible(true));
    }
}
