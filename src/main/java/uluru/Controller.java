package uluru;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import net.sf.tweety.logics.commons.syntax.Constant;
import net.sf.tweety.logics.commons.syntax.interfaces.Term;
import net.sf.tweety.lp.asp.solver.Clingo;
import net.sf.tweety.lp.asp.syntax.DLPAtom;
import net.sf.tweety.lp.asp.syntax.DLPLiteral;
import net.sf.tweety.lp.asp.util.AnswerSet;
import net.sf.tweety.lp.asp.util.AnswerSetList;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Set;

/**
 * Created by philip on 6/10/17.
 */
public class Controller {

    @FXML
    Button okButton;
    @FXML
    TextArea answerArea;

    @FXML
    ChoiceBox wish01;
    @FXML
    ChoiceBox wish00;
    @FXML
    ChoiceBox wish02;
    @FXML
    ChoiceBox wish03;
    @FXML
    ChoiceBox wish04;
    @FXML
    ChoiceBox wish05;
    @FXML
    ChoiceBox wish06;
    @FXML
    ChoiceBox wish07;
    @FXML
    ChoiceBox wish11;
    @FXML
    ChoiceBox wish10;
    @FXML
    ChoiceBox wish12;
    @FXML
    ChoiceBox wish13;
    @FXML
    ChoiceBox wish14;
    @FXML
    ChoiceBox wish15;
    @FXML
    ChoiceBox wish16;
    @FXML
    ChoiceBox wish17;



    @FXML
    TextField whiteW;
    @FXML
    TextField pinkW;
    @FXML
    TextField yellowW;
    @FXML
    TextField orangeW;
    @FXML
    TextField redW;
    @FXML
    TextField greenW;
    @FXML
    TextField blueW;
    @FXML
    TextField schwarzW;
    @FXML
    TextField white2W;
    @FXML
    TextField pink2W;
    @FXML
    TextField yellow2W;
    @FXML
    TextField orange2W;
    @FXML
    TextField red2W;
    @FXML
    TextField green2W;
    @FXML
    TextField blue2W;
    @FXML
    TextField schwarz2W;


    ArrayList<String> wishes;

    public Controller() {

    }

    @FXML
    public void initialize() {
        wishes = new ArrayList<String>();
        wishes.add("onCrowded");
        wishes.add("onUncrowded");
        wishes.add("onLongSite");
        wishes.add("onShortSite");
        wishes.add("onCornerW");
        wishes.add("onFrontW");
        wishes.add("neighbourW");
        wishes.add("sameW");
        wishes.add("onTwoW");
        wishes.add("onNoSightW");
        wishes.add("oppositeW");

        wish00.setItems(FXCollections.observableArrayList(wishes));
        wish01.setItems(FXCollections.observableArrayList(wishes));
        wish02.setItems(FXCollections.observableArrayList(wishes));
        wish03.setItems(FXCollections.observableArrayList(wishes));
        wish04.setItems(FXCollections.observableArrayList(wishes));
        wish05.setItems(FXCollections.observableArrayList(wishes));
        wish06.setItems(FXCollections.observableArrayList(wishes));
        wish07.setItems(FXCollections.observableArrayList(wishes));
        wish10.setItems(FXCollections.observableArrayList(wishes));
        wish11.setItems(FXCollections.observableArrayList(wishes));
        wish12.setItems(FXCollections.observableArrayList(wishes));
        wish13.setItems(FXCollections.observableArrayList(wishes));
        wish14.setItems(FXCollections.observableArrayList(wishes));
        wish15.setItems(FXCollections.observableArrayList(wishes));
        wish16.setItems(FXCollections.observableArrayList(wishes));
        wish17.setItems(FXCollections.observableArrayList(wishes));
    }

    private DLPAtom getWish(String colour,TextField tf, ChoiceBox cb) {
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("w");
        colors.add("p");
        colors.add("y");
        colors.add("o");
        colors.add("r");
        colors.add("g");
        colors.add("b");
        colors.add("s");

        int  wInt = cb.getSelectionModel().getSelectedIndex();
        String wB = tf.getText();
        String wType = "";
        if(wInt != -1) {
            wType = wishes.get(wInt);
            ArrayList<Term<String>> args = new ArrayList<Term<String>>();
            Constant c1 = new Constant(colour);
            args.add(c1);
            if(colors.contains(wB)) {
                Constant c2 = new Constant(wB);
                args.add(c2);
            }
            DLPAtom wAtom = new DLPAtom(wType,args);

            //answerArea.appendText(wAtom.toString());
            return wAtom;
        }

        return null;
    }

    public void askOracle() {
        answerArea.clear();

        ArrayList<String> fl = new ArrayList<String>();

        fl.add("/home/philip/IdeaProjects/asp4uluru/src/main/java/uluru.lp");
        fl.add("/home/philip/IdeaProjects/asp4uluru/src/main/java/uluru.facts");

        //get Wishes
        ArrayList<DLPAtom> allWishes = new ArrayList<DLPAtom>();

        DLPAtom atomW = getWish("w",whiteW,wish00);
        DLPAtom atom2W = getWish("w",white2W,wish10);
        if(atomW != null) {
            allWishes.add(atomW);
        }
        if(atom2W != null) {
            allWishes.add(atom2W);
        }
        DLPAtom atomP = getWish("p",pinkW,wish01);
        DLPAtom atom2P = getWish("p",pink2W,wish11);
        if(atomP != null) {
            allWishes.add(atomP);
        }
        if(atom2P != null) {
            allWishes.add(atom2P);
        }
        DLPAtom atomY = getWish("y",yellowW,wish02);
        DLPAtom atom2Y = getWish("y",yellow2W,wish12);
        if(atomY != null) {
            allWishes.add(atomY);
        }
        if(atom2Y != null) {
            allWishes.add(atom2Y);
        }
        DLPAtom atomO = getWish("o",orangeW,wish03);
        DLPAtom atom2O = getWish("o",orange2W,wish13);
        if(atomO != null) {
            allWishes.add(atomO);
        }
        if(atom2O != null) {
            allWishes.add(atom2O);
        }
        DLPAtom atomR = getWish("r",redW,wish04);
        DLPAtom atom2R = getWish("r",red2W,wish14);
        if(atomR != null) {
            allWishes.add(atomR);
        }
        if(atom2R != null) {
            allWishes.add(atom2R);
        }
        DLPAtom atomG = getWish("g",greenW,wish05);
        DLPAtom atom2G = getWish("g",green2W,wish15);
        if(atomG != null) {
            allWishes.add(atomG);
            allWishes.add(atom2G);
        }
        if(atom2G != null) {
            allWishes.add(atom2G);
        }
        DLPAtom atomB = getWish("b",blueW,wish06);
        DLPAtom atom2B = getWish("b",blue2W,wish16);
        if(atomB != null) {
            allWishes.add(atomB);
        }
        if(atom2B != null) {
            allWishes.add(atom2B);
        }
        DLPAtom atomS = getWish("s",schwarzW,wish07);
        DLPAtom atom2S = getWish("s",schwarz2W,wish17);
        if(atomS != null) {
            allWishes.add(atomS);
        }
        if(atom2S != null) {
            allWishes.add(atom2S);
        }
        try{
            PrintWriter writer = new PrintWriter("/home/philip/IdeaProjects/asp4uluru/src/main/java/wishes.facts", "UTF-8");
            //Print your wishes
            for(int i=0; i < allWishes.size(); i++) {
                writer.println(allWishes.get(i).toString() + ".");
            }
            writer.close();
        } catch (IOException e) {
            // do something
        }
        fl.add("/home/philip/IdeaProjects/asp4uluru/src/main/java/wishes.facts");

        /*File wf = new File("wishes.facts");
        try {
            InputStreamReader ir = new InputStreamReader(new FileInputStream(wf));
            char[] b = {'a','b','c','d','e','f'};
            ir.read(b);
            System.out.println(new String(b));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //get Wishes END
        // process answer
        Clingo clingo = new Clingo4("/usr/bin/clingo");

        AnswerSetList asl = null;
        try {
            asl = clingo.computeModels(fl, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        AnswerSet as = asl.get(0);
        Set<DLPLiteral> onF = asl.getFactsByName("onField");
        answerArea.appendText(onF.toString());
        // process answer END

    }

}
