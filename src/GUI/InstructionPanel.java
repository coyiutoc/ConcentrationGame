package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by caitlincoyiuto on 2016-12-22.
 */
public class InstructionPanel extends JPanel {

    private JPanel blockPanel, hPanel, iPanel, textPanel;
    private JLabel hLabel, i1Label, i2Label, textLabel, i1TextLabel;
    private final Font boldFont = new Font("Geneva", Font.BOLD, 27);
    private final Font boldFont2 = new Font("Geneva", Font.BOLD, 13);
    private final String html1 = "<html>";
    private final String html2 = "<br>";

    public InstructionPanel(){

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        blockPanel = new JPanel();
        blockPanel.setPreferredSize(new Dimension(600, 20));
        blockPanel.setBackground(new Color(232, 232, 232));

        add(blockPanel);

        hPanel = new JPanel();
        hPanel.setPreferredSize(new Dimension(600, 35));
        hPanel.setBackground(new Color(64, 224, 233));
        hPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        hPanel.setOpaque(true);

        hLabel = new JLabel();
        hLabel.setText("I N S T R U C T I O N S");
        hLabel.setFont(boldFont);
        hPanel.add(hLabel);

        add(hPanel);

        textPanel = new JPanel();
        textPanel.setBackground(new Color(255, 255, 255));

        textLabel = new JLabel();
        textLabel.setFont(boldFont2);
        textLabel.setText("<html><center>" +
                "Welcome to the concentration game! To have your information saved, please input <br>" +
                "information in the <i>'Participant Info'</i> panel before beginning your game. Otherwise,<br>" +
                "the game can be played without your info, but your overall score will not be calculated. Go<br>" +
                "to the <i>'Summary'</i> Panel once done playing to see how you did! <br><br>" +
                "The objective of the game is to move your cursor away from the moving orange shapes<br>" +
                "and to avoid hitting the red border. Take note that over time, the speed that the shapes move<br>" +
                "at will increase over time!" +
                "</html>");
        textPanel.add(textLabel);

        add(textPanel);

        iPanel = new JPanel();
        iPanel.setPreferredSize(new Dimension(600, 200));
        iPanel.setBackground(new Color(255, 255, 255));
        iPanel.setOpaque(true);
        iPanel.setLayout(new FlowLayout());

        ImageIcon image1 = new ImageIcon(this.getClass().getResource("instruct1.jpg"));
        i1Label = new JLabel(image1);
        iPanel.add(i1Label);

        ImageIcon image2 = new ImageIcon(this.getClass().getResource("instruct2.jpg"));
        i2Label = new JLabel(image2);
        iPanel.add(i2Label);

        add(iPanel);

    }
}
