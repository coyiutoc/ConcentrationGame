package Model;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by caitlincoyiuto on 2016-12-25.
 */
public class Participant {

    private String ID, date;
    private ArrayList<Integer> scores;
    private int borderHits, shapeHits, trialNum, maxTrial;
    private boolean trialsDone, isParticipant;

    public Participant(){
        ID = "";
        date = "";
        scores = new ArrayList<>();
        borderHits = 0;
        shapeHits = 0;
        maxTrial = 0;
        trialNum = 0;
        trialsDone = false;
        isParticipant = false;
    }

    public void setParticipantData(String ID, String date, int maxTrial){
        this.ID = ID;
        this.date = date;
        this.maxTrial = maxTrial;
        isParticipant = true;

    }

    public void resetParticipant(){
        ID = "";
        date = "";
        maxTrial = 0;
        isParticipant = false;
    }

    public boolean isParticipant(){
        return isParticipant;
    }

    public void addScore(int s){
        scores.add(s);
    }

    public void addBorderHit(){
        borderHits++;
    }

    public int getBorderHits(){
        return borderHits;
    }

    public void addShapeHit(){
        shapeHits++;
    }

    public int getShapeHits(){
        return shapeHits;
    }

    public int getBestScore(){
        return Collections.max(scores);
    }

    public int getMaxTrials(){
        return maxTrial;
    }

    public void addTrialDone(){
        if (maxTrial != trialNum){
            trialNum ++;

            if (maxTrial == trialNum){
                trialsDone = true;
            }
        }
    }

    public boolean isTrialsAllDone(){
        return trialsDone;
    }

    public String getID(){
        return ID;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;
        if (o.getClass() != this.getClass() || o == null) return false;

        Participant p = (Participant) o;

        if (p.getID().equals(this.getID())) return true;

        return false;
    }

    @Override
    public int hashCode(){
        return this.getID().hashCode();
    }

    public static void main(String[] args){
        Participant p = new Participant();
        p.setParticipantData("S2344", "2016", 2);

        p.addTrialDone();
        p.addTrialDone();

        System.out.println("Max trial: " + p.maxTrial);
        System.out.println("Is trial done: " + p.trialsDone);
        System.out.println("Trials done: " + p.trialNum);
        System.out.println("Is trial done: " + p.trialsDone);

        System.out.println(p.isTrialsAllDone());

    }
}
