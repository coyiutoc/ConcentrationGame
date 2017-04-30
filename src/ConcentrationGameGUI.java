import GUI.InstructionPanel;
import GUI.MainPanel;
import GUI.ParticipantInfoPanel;
import GUI.SummaryPanel;
import Model.Participant;
import Model.ParticipantsManager;

import javax.swing.*;
import javax.jnlp.*;

/**
 * Created by caitlincoyiuto on 2016-12-22.
 */
public class ConcentrationGameGUI{

    //static BasicService basicService = null;

    public static void main (String[] args) {

        JFrame frame = new JFrame("Concentration Game");
        JTabbedPane tp = new JTabbedPane();

//        try {
//            basicService = (BasicService)
//                    ServiceManager.lookup("javax.jnlp.BasicService");
//        } catch (UnavailableServiceException e) {
//            System.err.println("Lookup failed: " + e);
//        }

        Participant p = new Participant();

        tp.add("Instructions", new InstructionPanel());
        tp.add("Participant Info", new ParticipantInfoPanel(p));
        tp.add("Concentration Game", new MainPanel(p));
        tp.add("Summary", new SummaryPanel(p));
        frame.add(tp);

        //frame.setSize(600,635);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();

    }
}

