package GUI;

import Model.Participant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by caitlincoyiuto on 2016-12-26.
 */
public class SummaryPanel extends JPanel {

    private Participant person;
    private JPanel hPanel, sPanel, blockPanel, blockPanel2;
    private JLabel hLabel, sLabel;
    private JButton sButton;
    private final Font boldFont = new Font("Geneva", Font.BOLD, 25);
    private final Font boldFont2 = new Font("Geneva", Font.BOLD, 15);
    private final String html1 = "<html>";
    private final String html2 = "<br>";

    public SummaryPanel(Participant person){

        this.person = person;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        blockPanel = new JPanel();
        blockPanel.setPreferredSize(new Dimension(600, 100));
        blockPanel.setBackground(new Color(232, 232, 232));

        add(blockPanel);

        hPanel = new JPanel();
        hPanel.setPreferredSize(new Dimension(600, 35));
        hPanel.setBackground(new Color(64, 224, 233));
        hPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        hPanel.setOpaque(true);

        hLabel = new JLabel();
        hLabel.setText("S U M M A R Y");
        hLabel.setFont(boldFont);
        hPanel.add(hLabel);

        add(hPanel);

        sPanel = new JPanel();
        sPanel.setPreferredSize(new Dimension(600, 250));
        sPanel.setBackground(new Color(255, 255, 255));
        sPanel.setOpaque(true);

        sButton = new JButton();
        sButton.setFont(boldFont2);
        sButton.addActionListener(new ButtonListener());
        sButton.setPreferredSize(new Dimension(200, 50));
        sButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        sButton.setText("Get Results!");

        sPanel.add(sButton);

        sLabel = new JLabel();
        sLabel.setFont(boldFont2);
        sLabel.setPreferredSize(new Dimension(600, 150));
        sLabel.setHorizontalAlignment(JLabel.CENTER);
        sPanel.add(sLabel);

        add(sPanel);

        blockPanel2 = new JPanel();
        blockPanel2.setPreferredSize(new Dimension(600, 80));
        blockPanel2.setBackground(new Color(232, 232, 232));

        add(blockPanel2);
    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (!person.isParticipant()){
                sLabel.setText("Must play as participant to calculate results.");

            }
            else if (!person.isTrialsAllDone()){
                sLabel.setText("Complete all trials first.");
            }
            else {
                sLabel.setText(html1 + html2 + "<center><u>Participant:</u>  " + person.getID() + html2
                        + "<u>Number of trials:</u>  " + person.getMaxTrials() + html2
                        + "<u>Number of border hits:</u>  " + person.getBorderHits() + html2
                        + "<u>Number of shape hits:</u>  " + person.getShapeHits() + html2
                        + "<u>Best score:</u>  " + person.getBestScore() + " sec");

            }
        }
    }

}
