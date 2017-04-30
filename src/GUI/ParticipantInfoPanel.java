package GUI;

import Model.Participant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by caitlincoyiuto on 2016-12-25.
 */
public class ParticipantInfoPanel extends JPanel {

    private Participant person;
    private JPanel pPanel, idPanel, datePanel, trialPanel, blockPanel, blockPanel2, bPanel, mPanel;
    private JLabel pLabel, idLabel, dateLabel, trialLabel, mLabel;
    private JTextField IDField, dateField, trialField;
    private JButton sButton, rButton;
    private final Font boldFont = new Font("Geneva", Font.BOLD, 27);
    private final Font boldFont2 = new Font("Geneva", Font.BOLD, 15);

    public ParticipantInfoPanel(Participant person) {
        this.person = person;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        blockPanel2 = new JPanel();
        blockPanel2.setPreferredSize(new Dimension(600, 80));
        blockPanel2.setBackground(new Color(232, 232, 232));

        add(blockPanel2);

        pPanel = new JPanel();
        pPanel.setPreferredSize(new Dimension(600, 35));
        pPanel.setBackground(new Color(64, 224, 233));
        pPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        pPanel.setOpaque(true);

        pLabel = new JLabel();
        pLabel.setText("P A R T I C I P A N T   I N F O");
        pLabel.setFont(boldFont);
        pPanel.add(pLabel);

        add(pPanel);

        mPanel = new JPanel();
        mPanel.setPreferredSize(new Dimension(600, 27));
        mPanel.setBackground(new Color(255, 255, 255));
        mPanel.setOpaque(true);

        mLabel = new JLabel();
        mLabel.setFont(boldFont2);
        mLabel.setText("Enter participant information.");

        mPanel.add(mLabel);

        add(mPanel);

        idPanel = new JPanel();
        idPanel.setLayout(new FlowLayout());
        idPanel.setPreferredSize(new Dimension(600, 30));
        idPanel.setBackground(new Color(255, 255, 255));
        idPanel.setOpaque(true);

        idLabel = new JLabel();
        idLabel.setFont(boldFont2);
        idLabel.setText("ID Number / Name:");
        idLabel.setPreferredSize(new Dimension(150, 30));
        idPanel.add(idLabel);

        IDField = new JTextField("", 15);
        IDField.setFont(boldFont2);
        IDField.setHorizontalAlignment(JTextField.CENTER);
        idPanel.add(IDField);

        add(idPanel);

        datePanel = new JPanel();
        datePanel.setLayout(new FlowLayout());
        datePanel.setPreferredSize(new Dimension(600, 30));
        datePanel.setBackground(new Color(255, 255, 255));
        datePanel.setOpaque(true);

        dateLabel = new JLabel();
        dateLabel.setFont(boldFont2);
        dateLabel.setText("Date:");
        dateLabel.setPreferredSize(new Dimension(150, 30));
        datePanel.add(dateLabel);

        dateField = new JTextField("dd/mm/yyyy", 15);
        dateField.setHorizontalAlignment(JTextField.CENTER);
        dateField.setFont(boldFont2);
        datePanel.add(dateField);

        add(datePanel);

        trialPanel = new JPanel();
        trialPanel.setLayout(new FlowLayout());
        trialPanel.setPreferredSize(new Dimension(600, 30));
        trialPanel.setBackground(new Color(255, 255, 255));
        trialPanel.setOpaque(true);

        trialLabel = new JLabel();
        trialLabel.setFont(boldFont2);
        trialLabel.setText("Trials:");
        trialLabel.setPreferredSize(new Dimension(150, 30));
        trialPanel.add(trialLabel);

        trialField = new JTextField("", 15);
        trialField.setHorizontalAlignment(JTextField.CENTER);
        trialField.setFont(boldFont2);
        trialPanel.add(trialField);

        add(trialPanel);

        bPanel = new JPanel();
        bPanel.setLayout(new FlowLayout());
        bPanel.setPreferredSize(new Dimension(600, 70));
        bPanel.setBackground(new Color(255, 255, 255));

        sButton = new JButton();
        sButton.setText("S A V E");
        sButton.setFont(boldFont2);
        sButton.addActionListener(new ButtonListener());
        sButton.setPreferredSize(new Dimension(200, 50));
        sButton.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        //sButton.setBorderPainted(false);
        bPanel.add(sButton);

        rButton = new JButton();
        rButton.setEnabled(false);
        rButton.setText("U N D O");
        rButton.setFont(boldFont2);
        rButton.addActionListener(new UndoButtonListener());
        rButton.setPreferredSize(new Dimension(200, 50));
        rButton.setBackground(new Color(244, 137, 68));
        rButton.setBorder(BorderFactory.createLineBorder(new Color(120, 67, 33), 2));
        rButton.setOpaque(true);
        //rButton.setBorderPainted(false);
        bPanel.add(rButton);

        add(bPanel);

        blockPanel = new JPanel();
        blockPanel.setPreferredSize(new Dimension(600, 100));
        blockPanel.setBackground(new Color(232, 232, 232));

        add(blockPanel);


    }

    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (sButton.isEnabled()) {
                if (IDField.getText().equals("") || IDField.getText().equals("Input an ID.")) {
                    IDField.setText("Input an ID.");
                    mLabel.setText("Incomplete info.");
                    mPanel.setBackground(new Color(244, 137, 68));

                } else if (trialField.getText().equals("") || trialField.getText().equals("Input trial number.")) {
                    trialField.setText("Input trial number.");
                    mLabel.setText("Incomplete info.");
                    mPanel.setBackground(new Color(244, 137, 68));

                } else if (dateField.getText().equals("") || dateField.getText().equals("dd/mm/yyyy")) {
                    dateField.setText("dd/mm/yyyy");
                    mLabel.setText("Incomplete info.");
                    mPanel.setBackground(new Color(244, 137, 68));

                } else {
                    try {
                        person.setParticipantData(IDField.getText(), dateField.getText(), Integer.parseInt(trialField.getText()));
                        sButton.setText("S A V E D");
                        sButton.setBackground(new Color(91, 233, 29));
                        sButton.setOpaque(true);

                        rButton.setEnabled(true);

                        mLabel.setText("Participant info saved.");
                        mPanel.setBackground(new Color(91,233, 29));
                    }
                    catch(NumberFormatException e){
                        mLabel.setText("Trial number should not contain letters.");
                        mPanel.setBackground(new Color(244, 137, 68));
                        trialField.setText("Input trial number.");
                    }

                }
            }

        }
    }

    private class UndoButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            if (rButton.isEnabled()){
                mLabel.setText("Participant info deleted.");
                mPanel.setBackground(new Color(244, 137, 68));
                sButton.setText("S A V E");

                IDField.setText("");
                trialField.setText("");
                dateField.setText("dd/mm/yyyy");

                person.resetParticipant();
            }
        }
    }

}