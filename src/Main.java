import javax.swing.*;
import java.awt.*;
/*import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;*/
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class WerWirdMillionaerSwing {

    // FragenundAntworten
    private static final String[][] FRAGEN = {
            {"Welches Schlüsselwort wird verwendet, um eine Klasse in Java zu definieren?", "A) class", "B) def", "C) struct", "D) function", "A"},
            {"Welche Methode wird verwendet, um ein Java-Programm auszuführen?", "A) run()", "B) start()", "C) execute()", "D) main()", "D"},
            {"Welche primitive Datentypen gibt es in Java?", "A) int, float, double", "B) integer, string, array", "C) void, bool, char", "D) number, object, array", "A"}

    };

    private static final int[] PREISGELDER = {100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};

    private int frageNummer = 0;
    private int gewonnenesGeld = 0;
    private List<Integer> zufaelligeFragen = new ArrayList<>();

    private JLabel frageLabel;
    private JButton antwortA;
    private JButton antwortB;
    private JButton antwortC;
    private JButton antwortD;
    private JLabel gewinnLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WerWirdMillionaerSwing().erstelleGUI());
    }

    private void erstelleGUI() {
        //ZufälligeFragenlisteErstellen
        for (int i = 0; i < FRAGEN.length; i++) {
            zufaelligeFragen.add(i);
        }
        Collections.shuffle(zufaelligeFragen);

        //    JFrameerstellen
        JFrame frame = new JFrame("Wer wird Millionär");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        //Hauptpanel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        //FrageLabel
        frageLabel = new JLabel("Willkommen bei Wer wird Millionär!");
        frageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(frageLabel, BorderLayout.NORTH);

        // ButtonAntworten
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        antwortA = new JButton();
        antwortB = new JButton();
        antwortC = new JButton();
        antwortD = new JButton();

        buttonPanel.add(antwortA);
        buttonPanel.add(antwortB);
        buttonPanel.add(antwortC);
        buttonPanel.add(antwortD);

        antwortA.addActionListener(e -> handleAntwort("A"));
        antwortB.addActionListener(e -> handleAntwort("B"));
        antwortC.addActionListener(e -> handleAntwort("C"));
        antwortD.addActionListener(e -> handleAntwort("D"));

        panel.add(buttonPanel, BorderLayout.CENTER);

        // GewinnLabel
        gewinnLabel = new JLabel("Gewinn: 0€");
        gewinnLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gewinnLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(gewinnLabel, BorderLayout.SOUTH);

        // Erste Frage
        ladeNaechsteFrage();

        frame.add(panel);
        frame.setVisible(true);
    }

    private void ladeNaechsteFrage() {
        if (frageNummer >= FRAGEN.length) {
            frageLabel.setText("Herzlichen Glückwunsch! Du hast 1.000.000€ gewonnen!");
            deaktivierenButtons();
            return;
        }

        int aktuelleFrageIndex = zufaelligeFragen.get(frageNummer);
        frageLabel.setText("<html>Frage " + (frageNummer + 1) + " für " + PREISGELDER[frageNummer] + "€:<br>" + FRAGEN[aktuelleFrageIndex][0] + "</html>");
        antwortA.setText(FRAGEN[aktuelleFrageIndex][1]);
        antwortB.setText(FRAGEN[aktuelleFrageIndex][2]);
        antwortC.setText(FRAGEN[aktuelleFrageIndex][3]);
        antwortD.setText(FRAGEN[aktuelleFrageIndex][4]);
    }

    private void handleAntwort(String antwort) {
        int aktuelleFrageIndex = zufaelligeFragen.get(frageNummer);
        if (antwort.equals(FRAGEN[aktuelleFrageIndex][5])) {
            gewonnenesGeld = PREISGELDER[frageNummer];
            gewinnLabel.setText("Gewinn: " + gewonnenesGeld + "€");
            frageNummer++;
            ladeNaechsteFrage();
        } else {
            frageLabel.setText("Falsch! Die richtige Antwort wäre " + FRAGEN[aktuelleFrageIndex][5] + " gewesen. Du gehst mit " + (frageNummer >= 5 ? 1000 : 0) + "€ nach Hause.");
            deaktivierenButtons();
        }
    }

    private void deaktivierenButtons() {
        antwortA.setEnabled(false);
        antwortB.setEnabled(false);
        antwortC.setEnabled(false);
        antwortD.setEnabled(false);
    }
}
