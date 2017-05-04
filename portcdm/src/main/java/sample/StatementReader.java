package sample;

import eu.portcdm.dto.*;

import java.util.HashMap;
import java.util.List;

/**
 * Created by arono on 2017-05-04.
 */
public class StatementReader {

    HashMap<String,String> latestStatements;
    PortCall activeCall;

    public StatementReader(PortCall call) {
        activeCall = call;
        latestStatements = getAllLatestStatements(activeCall);
    }

    public void setActiveCall(PortCall call) {
        activeCall = call;
    }

    public String getStatement(String id) {
        return latestStatements.get(id);
    }

    private HashMap<String, String> getAllLatestStatements(PortCall call){
        HashMap<String, String> output = new HashMap<String, String>();
        List<ProcessStep> steps = call.getProcessSteps();
        for (ProcessStep step : steps) {
            //System.out.println("Step: " + step.getDefinitionName());
            List<SubProcess> substeps = step.getSubProcesses();
            for (SubProcess substep : substeps){
                //System.out.println("\tSubstep: " + substep.getDefinitionName());
                List<Event> events = substep.getEvents();
                for (Event event : events){
                    //System.out.println("\t\tEvent: " + event.getDefinitionName());
                    List<State> states = event.getStates();
                    for (State state : states) {
                        //System.out.println("\t\t\tState: " + state.getDefinitionName() + ", id: " + state.getStateDefinitionId());
                        String id = state.getStateDefinitionId();
                        List<Statement> statements = state.getStatements();
                        if (!statements.isEmpty()) {
                            Statement statement = statements.get(statements.size() - 1);
                            String time = statement.getTimeType().toString() + " " + statement.getTimeStatement();
                            output.put(id, time);
                        }
                    }
                }
            }
        }
        return output;
    }

}
