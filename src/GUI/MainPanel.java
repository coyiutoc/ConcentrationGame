package GUI;

import Model.CircleX;
import Model.Participant;
import Model.RectangleX;
import Model.ShapeX;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;

/**
 * Created by caitlincoyiuto on 2016-12-22.
 */
public class MainPanel extends JPanel{

    private Participant person;

    // UI VARIABLES:
    private JPanel bPanel, gPanel, sPanel, mPanel;
    private JLabel score, message, trials;
    private JButton b;
    private int trialNum;
    private boolean startOn;
    private int sec = 0;
    private Robot r;
    private final static int gWidth = 600;
    private final static int gHeight = 400;
    private final static int borderSize = 30;
    private final String html1 = "<html><body style='width: ";
    private final String html2 = "px'>";
    private final Font boldFont = new Font("Geneva", Font.BOLD, 22);
    private final Font boldFont2 = new Font("Geneva", Font.BOLD, 15);

    // SHAPE VARIABLES:
    private double spdIncrement = 1;

    private Graphics2D g2;
    private CircleX circleX1, circleX2;
    private RectangleX rectangleX1, rectangleX2;

    private ArrayList<ShapeX> Shapes;
    private ArrayList<Ellipse2D> circles = new ArrayList<>();
    private ArrayList<Rectangle2D> rectangles = new ArrayList<>();

    private Timer posTimer, scoreTimer, movTimer, incrTimer;

    public MainPanel(Participant person){

        this.person = person;

        // Adding shapes:
        Shapes = new ArrayList<>();
        circleX1 = new CircleX(80, 80, 2, 2, 0, 0, 1);
        circleX2 = new CircleX(80, 100, 2, 2, 500, 300, 1);
        rectangleX1 = new RectangleX(150, 50, 2, 2, 400, 0, 1);
        rectangleX2 = new RectangleX(50, 150, 2, 2, 0, 150, 1);
        Shapes.add(circleX1);
        Shapes.add(circleX2);
        Shapes.add(rectangleX1);
        Shapes.add(rectangleX2);

        // For mouse detection:
        try {
            r = new Robot();
        }
        catch(AWTException e){
            System.out.println(e);
        }

        // Timers:
        posTimer = new Timer(10, new PositionListener());
        scoreTimer = new Timer(1000, new ScoreListener());
        movTimer = new Timer(1, new MovementListener());
        incrTimer = new Timer(5000, new IncrementListener());

        startOn = false;

        // Panels:
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setPreferredSize(new Dimension(600,500));
        setBackground(Color.white);

        gPanel = new JPanel();
        gPanel.setPreferredSize(new Dimension(gWidth, gHeight));
        gPanel.setBackground(Color.white);

        gPanel.add(new MyGraphics());
        gPanel.setBorder(BorderFactory.createLineBorder(Color.red, borderSize));

        add(gPanel);

        bPanel = new JPanel();
        bPanel.setPreferredSize(new Dimension(600,65));
        bPanel.setBackground(new Color(0, 0, 0));
        bPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        bPanel.setLayout(new FlowLayout());

        b = new JButton();
        b.setText("S T A R T");
        b.setEnabled(true);
        b.setFont(boldFont);
        b.setBackground(new Color(91,233, 29));
        b.setOpaque(true);
        b.addActionListener(new ButtonListener());
        b.setPreferredSize(new Dimension(180,48));
        b.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        bPanel.add(b);

        add(bPanel);

        sPanel = new JPanel();
        sPanel.setPreferredSize(new Dimension(600, 40));
        sPanel.setBackground(new Color(255, 255, 255));
        sPanel.setLayout(new FlowLayout());

        score = new JLabel();
        score.setFont(boldFont);
        score.setText("Score: " + sec + " sec  ");
        score.setForeground(Color.red);
        sPanel.add(score);

        trials = new JLabel();
        trials.setFont(boldFont);
        trialNum = 0;
        trials.setText("Trial number: " + trialNum);
        sPanel.add(trials);

        add(sPanel);

        mPanel = new JPanel();
        mPanel.setPreferredSize(new Dimension(600, 30));
        mPanel.setBackground(new Color(255, 255, 255));

        message = new JLabel();
        message.setFont(boldFont2);
        message.setText("");
        mPanel.add(message);

        add(mPanel);

    }

    public class MyGraphics extends JComponent {

        public MyGraphics() {
            setPreferredSize(new Dimension(gWidth, gHeight));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g2 = (Graphics2D) g;

            circles.clear();
            rectangles.clear();

            Ellipse2D circle;
            Rectangle2D rectangle;

            for (ShapeX shape : Shapes){
                g2.setPaint(new Color(244, 164, 0));
                if (shape.getObject() instanceof Ellipse2D){
                    circle = new Ellipse2D.Double(shape.getPosX(), shape.getPosY(), shape.getW(), shape.getH());
                    circles.add(circle);
                    g2.fill(circle);
                    g2.setColor(new Color(195, 97, 67));
                    g2.setStroke(new BasicStroke(3));
                    g2.draw(circle);
                }
                else if (shape.getObject() instanceof Rectangle2D){
                    rectangle = new Rectangle2D.Double(shape.getPosX(), shape.getPosY(), shape.getW(), shape.getH());
                    rectangles.add(rectangle);
                    g2.fill(rectangle);
                    g2.setColor(new Color(195, 97, 67));
                    g2.setStroke(new BasicStroke(3));
                    g2.draw(rectangle);

                }
            }
        }
    }

    private void initializeShapeLocn(){
        for (ShapeX shape: Shapes){
            shape.resetPos();
        }
    }

    private class MovementListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            int x =  (int) (MouseInfo.getPointerInfo().getLocation().getX() - gPanel.getLocationOnScreen().getX());
            int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - gPanel.getLocationOnScreen().getY());
            Point p = new Point(x, y);

            if (!b.isEnabled() && (sec > 0) && (x < borderSize || x > (gWidth - borderSize)
                    || y < borderSize || y > (gHeight - borderSize))){

                if (person.isParticipant()) {
                    person.addBorderHit();
                    person.addScore(sec);
                }

                if (person.isParticipant() && (trialNum == person.getMaxTrials())){
                    b.setEnabled(false);
                }

                startOn = false;

                posTimer.stop();
                scoreTimer.stop();
                movTimer.stop();
                incrTimer.stop();

                b.setEnabled(true);
                b.setText("S T A R T");

                message.setText("Outside border! ");
                mPanel.setBackground(new Color(244, 137, 68));
                mPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

                sec = 0;
                spdIncrement = 1;

            }
            else{
                for (Rectangle2D rect : rectangles) {
                    if (rect.contains(p)) {

                        if (person.isParticipant()) {
                            person.addShapeHit();
                            person.addScore(sec);
                        }

                        if (person.isParticipant() && (trialNum == person.getMaxTrials())){
                            b.setEnabled(false);
                        }

                        startOn = false;

                        posTimer.stop();
                        scoreTimer.stop();
                        movTimer.stop();
                        incrTimer.stop();

                        message.setText("Touched shape!");
                        mPanel.setBackground(new Color(244, 137, 68));
                        mPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

                        b.setText("S T A R T");
                        b.setEnabled(true);
                        b.setBackground(new Color(91,233, 29));
                        b.setOpaque(true);

                        sec = 0;
                        spdIncrement = 1;

                        break;
                    }
                }

                for (Ellipse2D circ : circles){
                    if (circ.contains(p)){

                        if (person.isParticipant()) {
                            person.addShapeHit();
                            person.addScore(sec);
                        }

                        if (person.isParticipant() && (trialNum == person.getMaxTrials())){
                            b.setEnabled(false);
                        }

                        startOn = false;

                        posTimer.stop();
                        scoreTimer.stop();
                        movTimer.stop();
                        incrTimer.stop();

                        message.setText("Touched shape!");
                        mPanel.setBackground(new Color(244, 137, 68));
                        mPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

                        b.setText("S T A R T");
                        b.setEnabled(true);
                        b.setBackground(new Color(91,233, 29));
                        b.setOpaque(true);

                        sec = 0;
                        spdIncrement = 1;

                        break;
                    }
                }
            }
        }
    }

    private class PositionListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            for (ShapeX shape : Shapes){
                shape.calcVel(gWidth, gHeight);
            }

            repaint(); //every 5ms will repaint
        }
    }


    private class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (b.isEnabled()) {

                if (person.isParticipant() && (trialNum == person.getMaxTrials())){
                    b.setEnabled(false);
                }

                else {
                    startOn = true;
                    posTimer.start();
                    scoreTimer.start();
                    movTimer.start();
                    incrTimer.start();

                    sec = 0;
                    trialNum++;

                    if (person.isParticipant()) {
                        person.addTrialDone();
                        trials.setText("Trial number: " + trialNum + " / " + person.getMaxTrials());
                    } else {
                        trials.setText("Trial number: " + trialNum);
                    }

                    //b.setText("S T O P");
                    b.setEnabled(false);
                    b.setBackground(new Color(233, 75, 44));
                    b.setOpaque(true);

                    message.setText("");
                    mPanel.setBackground(new Color(255, 255, 255));

                    score.setText("Score: " + sec + " sec  ");

                    r.mouseMove((int) gPanel.getLocationOnScreen().getX() + 300, (int) gPanel.getLocationOnScreen().getY() + 250);

                    initializeShapeLocn();

                    for (ShapeX shape : Shapes) {
                        shape.setSpdIncrement(spdIncrement);
                    }

                    repaint();
                }
            }
            else if (!b.isEnabled()){
                startOn = false;
                posTimer.stop();
                scoreTimer.stop();
                movTimer.stop();
                incrTimer.stop();

                b.setText("S T A R T");
                b.setBackground(new Color(91,233, 29));
                b.setOpaque(true);
                message.setText("");
            }

        }
    }

    private class ScoreListener implements ActionListener{

        public void actionPerformed(ActionEvent event){
            sec++;
            score.setText("Score: " + sec + " sec  ");
        }
    }

    private class IncrementListener implements ActionListener{

        public void actionPerformed(ActionEvent event){

            spdIncrement = spdIncrement + 0.5;

            for (ShapeX shape : Shapes) {
                shape.setSpdIncrement(spdIncrement);
            }

        }
    }

}
