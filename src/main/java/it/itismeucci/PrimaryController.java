package it.itismeucci;

import java.util.Random;

import it.itismeucci.model.CampoComputer;
import it.itismeucci.model.CampoUtente;
import it.itismeucci.model.Navi;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;
import javafx.geometry.VPos;

public class PrimaryController {

    @FXML
    private GridPane playerGrid;
    @FXML
    private GridPane computerGrid;
    @FXML
    private Button bottoneGioca;
    @FXML
    private TextField inputX;
    @FXML
    private TextField inputY;
    @FXML
    private Label outputTestoUtente;
    @FXML
    private Label outputTestoComputer;

    Navi navi = new Navi(4, 3, 3, 3, 2, 2, 2, 1, 1);

    CampoComputer campoComputer = new CampoComputer(navi);
    CampoUtente campoUtente = new CampoUtente(navi);

    boolean turnoUtente = true; // Indica di chi è il turno: true per l'utente, false per il computer


    @FXML
    public void creaBottoni() {
        generaCampoBottoni(playerGrid);
        generaCampoBottoni(computerGrid);
    }

    @FXML
    public void iniziaGioco() {
        campoComputer.piazzaNaviCasuali();
        campoUtente.piazzaNaviCasuali();
        creaBottoni();
    }

    public static void generaCampoBottoni(GridPane griglia) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Button btn = new Button(" ");
                griglia.add(btn, i + 1, j + 1);
                GridPane.setHalignment(btn, HPos.CENTER);
                GridPane.setValignment(btn, VPos.CENTER);
            }
        }
    }

    @FXML
    public void utenteSparaAllaCoordinata() {
        if (!turnoUtente) return; // Se non è il turno dell'utente, esci dalla funzione
    
        String stringX = inputX.getText();
        String stringY = inputY.getText();
    
        Integer x = Integer.parseInt(stringX);
        Integer y = Integer.parseInt(stringY);
    
        String risultato = campoComputer.sparare(x, y);
        Button b = (Button) getNodeByRowColumnIndex(x + 1, y + 1, computerGrid);
        if (risultato.equals("ACQUA")) {
            b.setText("-");
            outputTestoUtente.setText("Hai sparato all'acqua");
        } else if (risultato.equals("COLPITO")) {
            b.setText("X");
            outputTestoUtente.setText("Hai colpito una nave del computer");
        } else if (risultato.equals("GIOCO FINITO")) {
            outputTestoUtente.setText("Hai vinto! Tutte le navi del computer sono state affondate");
        }
    
        inputX.clear();
        inputY.clear();
        turnoUtente = false; // setta il turno utente a false quindi tocca al computer a sparare
        computerSparaAllaCoordinata(); // è il turno del computer
    }
    


    public void computerSparaAllaCoordinata() {
        Random numRandom = new Random();

        if (turnoUtente) return; //se non è il turno del computer, esce dalla funzione, sennò la esegue e spara il computer

        int x, y;

            x = numRandom.nextInt(10);
            y = numRandom.nextInt(10);
  
        Button btn = (Button) getNodeByRowColumnIndex(x + 1, y + 1, playerGrid);

        String risultato = campoUtente.sparare(x, y);

        if (risultato.equals("ACQUA")) {
            btn.setText("-");
            outputTestoComputer.setText("Il computer ha sparato all'acqua");
        } else if (risultato.equals("COLPITO")) {
            btn.setText("X");
            outputTestoComputer.setText("Il computer ha colpito una tua nave");
        }

        turnoUtente = true; //setta il turno utente a true, quindi passa il turno all'utente 
    }



    public Node getNodeByRowColumnIndex(final int row, final int column, GridPane gridPane) {
        Node result = null;
        ObservableList<Node> childrens = gridPane.getChildren();
        for (Node node : childrens) {
            if (gridPane.getRowIndex(node) != null && gridPane.getRowIndex(node) == row &&
                    gridPane.getColumnIndex(node) != null && gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }
        return result;
    }

}
