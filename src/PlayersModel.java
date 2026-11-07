import java.util.ArrayList;
import java.util.List;

public final class PlayersModel {
    private static final List<String> PLAYERS = List.of(
        "Иванов", "Сидоров", "Месси", "Пеле", "Зидан",
        "Роналду", "Бекхэм", "Роналдиньо", "Семёнов", "Коби Брайант");
    private final List<String> available = new ArrayList<>();
    private final List<String> selected = new ArrayList<>();

    public PlayersModel() { reset(); }
    public List<String> available() { return List.copyOf(available); }
    public List<String> selected() { return List.copyOf(selected); }
    public void reset() {
        available.clear();
        available.addAll(PLAYERS);
        selected.clear();
    }
    public void move(boolean toTeam, int[] indices) {
        List<String> source = toTeam ? available : selected;
        List<String> target = toTeam ? selected : available;
        int previous = -1;
        for (int index : indices) {
            if (index <= previous || index >= source.size())
                throw new IllegalArgumentException("Индексы должны быть уникальными и возрастающими");
            previous = index;
        }
        for (int index : indices) target.add(source.get(index));
        for (int i = indices.length - 1; i >= 0; i--) source.remove(indices[i]);
    }
    public void moveAll(boolean toTeam) {
        List<String> source = toTeam ? available : selected;
        List<String> target = toTeam ? selected : available;
        target.addAll(source);
        source.clear();
    }
}
