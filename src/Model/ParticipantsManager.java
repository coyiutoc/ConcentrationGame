package Model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by caitlincoyiuto on 2016-12-25.
 */
public class ParticipantsManager {

    private HashMap<String, Participant> participants;
    private static ParticipantsManager instance;

    private ParticipantsManager(){
        participants = new HashMap<>();
    }

    public static ParticipantsManager getInstance(){
        if (instance == null){
            instance = new ParticipantsManager();
        }

        return instance;
    }

    public Participant getParticipant(int ID){
        Participant p = null;
        if (participants.containsKey(ID)){
            p = participants.get(ID);
        }
        return p;
    }

    public void addParticipant(Participant p){
        if (!participants.containsKey(p.getID())){
            participants.put(p.getID(), p);
        }
    }

    public void removeParticipant(Participant p){
        if (participants.containsKey(p.getID())){
            participants.remove(p.getID());
        }
    }

    public void clearParticipants(){
        if (instance != null){
            instance = null;
            participants.clear();
        }
    }
}
