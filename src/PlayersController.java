import javax.swing.*;

public final class PlayersController {
    private final PlayersModel model;
    private final PlayersView view;
    public PlayersController(PlayersModel model, PlayersView view) {
        this.model = model;
        this.view = view;
        view.add.addActionListener(e -> transfer(true, false));
        view.addAll.addActionListener(e -> transfer(true, true));
        view.remove.addActionListener(e -> transfer(false, false));
        view.removeAll.addActionListener(e -> transfer(false, true));
        view.reset.addActionListener(e -> {
            model.reset();
            refresh();
            view.summary.setText("Состав пока не сохранён.");
            view.status.setText("Reset: исходный список восстановлен");
        });
        view.save.addActionListener(e -> {
            String text = model.selected().isEmpty() ? "Никто не выбран."
                : String.join(", ", model.selected());
            view.summary.setText("Сохранённый состав (" + model.selected().size() + "): " + text);
            view.status.setText("Save: состав сохранён в текущем сеансе");
            JOptionPane.showMessageDialog(view, model.selected().isEmpty() ? text
                : String.join("\n", model.selected()), "Выбраны следующие игроки",
                JOptionPane.INFORMATION_MESSAGE);
        });
        view.available.addListSelectionListener(e -> updateButtons());
        view.selected.addListSelectionListener(e -> updateButtons());
        refresh();
    }
    private void transfer(boolean toTeam, boolean all) {
        if (all) model.moveAll(toTeam);
        else model.move(toTeam, (toTeam ? view.available : view.selected).getSelectedIndices());
        refresh();
        view.status.setText("Состав изменён • в команде: " + model.selected().size()
            + " • нажмите Save для сохранения");
    }
    private void refresh() {
        view.availableModel.clear();
        model.available().forEach(view.availableModel::addElement);
        view.selectedModel.clear();
        model.selected().forEach(view.selectedModel::addElement);
        view.leftTitle.setText("Доступные игроки · " + model.available().size());
        view.rightTitle.setText("В команде · " + model.selected().size());
        updateButtons();
    }
    private void updateButtons() {
        view.add.setEnabled(!view.available.isSelectionEmpty());
        view.remove.setEnabled(!view.selected.isSelectionEmpty());
        view.addAll.setEnabled(!view.availableModel.isEmpty());
        view.removeAll.setEnabled(!view.selectedModel.isEmpty());
    }
}
