import java.util.*;
import java.io.*;

public class mainQueue {
    public static void main(String[] args) throws IOException {
        System.out.println("Directorio de trabajo: " + System.getProperty("user.dir"));
        SequentialFile<Jugador> file = new SequentialFile<>("player_request.csv");
        file.readLine();//me salto el encabezado

        Queue<Jugador> premiumLargo = new PriorityQueue<>();
        Queue<Jugador> premiumCorto = new PriorityQueue<>();
        Queue<Jugador> normalLargo = new LinkedList<>();
        Queue<Jugador> normalCorto = new LinkedList<>();

        System.out.println("Reading player requests");
        String line;
        while ((line = file.readLine()) != null) {
            Jugador player = new Jugador();
            player.readData(line);
            System.out.println("Processing player: " + player);

            if (player.isPremium()) {
                if (player.getMatchType() == 'S') premiumCorto.offer(player);
                else premiumLargo.offer(player);
            } else {
                if (player.getMatchType() == 'S') normalCorto.offer(player);
                else normalLargo.offer(player);
            }
        }

        System.out.println("\ncreating  matches:");
        Jugador p1, p2;

        while (premiumLargo.size() > 1 || premiumCorto.size() > 1 || normalLargo.size() > 1 || normalCorto.size() > 1) {

            for (int i = 0; i < 2 && premiumLargo.size() > 1; i++) {
                p1 = premiumLargo.poll();
                p2 = premiumLargo.poll();
                System.out.println(matchInfo("Premium Long", p1, p2));
            }

            for (int i = 0; i < 2 && premiumCorto.size() > 1; i++) {
                p1 = premiumCorto.poll();
                p2 = premiumCorto.poll();
                System.out.println(matchInfo("Premium Short", p1, p2));
            }

            if (normalLargo.size() > 1) {
                p1 = normalLargo.poll();
                p2 = normalLargo.poll();
                System.out.println(matchInfo("Normal Long", p1, p2));
            }

            if (normalCorto.size() > 1) {
                p1 = normalCorto.poll();
                p2 = normalCorto.poll();
                System.out.println(matchInfo("Normal Short", p1, p2));
            }
        }

        file.close();
    }

    private static String matchInfo(String type, Jugador j1, Jugador j2) {
        return "Processing " + type + " match:\n" +"  Player 1 ID: " + j1.getPlayerID() + " Skill " + j1.getSkillLevel() + "\n" +"  Player 2 ID: " + j2.getPlayerID() + " Skill " + j2.getSkillLevel() + "\n";
    }
}
