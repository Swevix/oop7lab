import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public final class PlayersView extends JPanel {
    final DefaultListModel<String> availableModel = new DefaultListModel<>();
    final DefaultListModel<String> selectedModel = new DefaultListModel<>();
    final JList<String> available = new JList<>(availableModel);
    final JList<String> selected = new JList<>(selectedModel);
    final JButton add = new JButton(">"), addAll = new JButton(">>");
    final JButton remove = new JButton("<"), removeAll = new JButton("<<");
    final JButton save = new JButton("Save — сохранить"), reset = new JButton("Reset — сбросить");
    final JMenuItem exit = new JMenuItem("Выход");
    final JLabel leftTitle = new JLabel(), rightTitle = new JLabel();
    final JLabel status = new JLabel("Выберите игроков слева и нажмите >");
    final JTextArea summary = new JTextArea("Состав пока не сохранён.");
    final JMenuBar menu = new JMenuBar();

    public PlayersView() {
        setLayout(new BorderLayout(0, 16));
        setBorder(new EmptyBorder(22, 26, 20, 26));
        setBackground(new Color(242, 245, 249));
        JMenu file = new JMenu("Файл");
        file.add(exit);
        menu.add(file);
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("Выбор состава команды");
        title.setFont(new Font("SansSerif", Font.BOLD, 25));
        header.add(title, BorderLayout.NORTH);
        JLabel subtitle = new JLabel("Лабораторная работа № 7 • Работа с данными через интерфейс");
        subtitle.setBorder(new EmptyBorder(9, 0, 0, 0));
        subtitle.setForeground(new Color(80, 96, 115));
        header.add(subtitle, BorderLayout.SOUTH);
        add(header, BorderLayout.NORTH);

        JPanel lists = new JPanel(new GridBagLayout());
        lists.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = 0; c.fill = GridBagConstraints.BOTH; c.weighty = 1;
        c.gridx = 0; c.weightx = 1;
        lists.add(listCard(leftTitle, available), c);
        JPanel arrows = new JPanel(new GridLayout(4, 1, 0, 12));
        arrows.setOpaque(false);
        arrows.setBorder(new EmptyBorder(65, 18, 65, 18));
        JButton[] buttons = {add, addAll, remove, removeAll};
        String[] tips = {"Добавить выбранных игроков", "Добавить всех игроков",
                         "Вернуть выбранных игроков", "Вернуть всех игроков"};
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setFont(new Font("SansSerif", Font.BOLD, 18));
            buttons[i].setToolTipText(tips[i]);
            arrows.add(buttons[i]);
        }
        c.gridx = 1; c.weightx = 0;
        lists.add(arrows, c);
        c.gridx = 2; c.weightx = 1;
        lists.add(listCard(rightTitle, selected), c);
        add(lists, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 12));
        bottom.setOpaque(false);
        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        actions.setOpaque(false);
        actions.add(save);
        actions.add(Box.createHorizontalStrut(12));
        actions.add(reset);
        bottom.add(actions, BorderLayout.NORTH);
        summary.setEditable(false);
        summary.setLineWrap(true);
        summary.setWrapStyleWord(true);
        summary.setFont(new Font("SansSerif", Font.PLAIN, 14));
        summary.setBorder(new EmptyBorder(10, 12, 10, 12));
        JScrollPane savedPane = new JScrollPane(summary);
        savedPane.setPreferredSize(new Dimension(100, 76));
        bottom.add(savedPane, BorderLayout.CENTER);
        status.setFont(new Font("SansSerif", Font.PLAIN, 13));
        bottom.add(status, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);
        setPreferredSize(new Dimension(880, 620));
    }

    private JPanel listCard(JLabel title, JList<String> list) {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setOpaque(false);
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        panel.add(title, BorderLayout.NORTH);
        list.setFont(new Font("SansSerif", Font.PLAIN, 17));
        list.setFixedCellHeight(32);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectionBackground(new Color(48, 99, 190));
        list.setSelectionForeground(Color.WHITE);
        list.setBorder(new EmptyBorder(6, 8, 6, 8));
        panel.add(new JScrollPane(list), BorderLayout.CENTER);
        return panel;
    }
}
