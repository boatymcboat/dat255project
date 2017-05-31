package model;

import eu.portcdm.dto.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Reads Statements and StateDefinitionIDs from PortCalls.
 */
public class StatementReader {

    // Stores the latest Statements and the active PortCall
    private HashMap<String, String> latestStatements;
    private PortCall activeCall;

    /**
     * Creates an instance of the Reader and refreshes the list of latest statements.
     *
     * @param call the PortCall for which statements are to be read.
     */
    public StatementReader(PortCall call) {
        activeCall = call;
        latestStatements = getAllLatestStatements(activeCall);
    }

    /**
     * Changes the active PortCall and gets the list of latest statements for it.
     *
     * @param call the new PortCall
     * @return False if the new PortCall is null, True otherwise.
     */
    public boolean setActiveCall(PortCall call) {
        if (call == null){
            return false;
        }
        activeCall = call;
        latestStatements = getAllLatestStatements(activeCall);
        return true;
    }

    /**
     * Gets the latest Statement for a given StateDefinitionID.
     *
     * @param id the requested StateDefinitionID
     * @return the latest Statement
     */
    public String getStatement(String id) {
        return latestStatements.get(id);
    }

    /**
     * Gets all Statements for a given StateDefinitionID.
     *
     * @param id the requested StateDefinitionID
     * @return a String like: 'TimeType: TimeStatement from ReportedBy'
     */
    public String getStatements(String id) { // TODO: Make this return a list of Statements and create separate toString method
        StringBuilder buf = new StringBuilder();
        for (Statement statement :
                getAllStatements().get(id)) {
            buf.append(new StringBuilder().append(statement.getTimeType()).append(": ").append(statement.getTimeStatement()).append(" from ").append(statement.getReportedBy().getName()).append("\n").toString());
        }
        return buf.toString();
    }

    /**
     * Gets a list of StateDefinitionIDs for the currently active PortCall
     *
     * @return a list of StateDefinitionIDs
     */
    public List<String> getStateDefinitions() {
        List<String> output = new LinkedList<>();
        for (ProcessStep processStep :
                activeCall.getProcessSteps()) {
            for (SubProcess subProcess :
                    processStep.getSubProcesses()) {
                for (Event event :
                        subProcess.getEvents()) {
                    for (State state :
                            event.getStates()) {
                        output.add(state.getStateDefinitionId());
                    }
                }
            }
        }
        return output;
    }

    /**
     * Gets all Statements for the currently active PortCall.
     *
     * @return a HashMap with the StateDefinitionIDs as keys and a list of Statements for each as entries.
     */
    public HashMap<String, List<Statement>> getAllStatements(){
        return getAllStatements(activeCall);
    }

    // Gets all Statements for a given PortCall.
    private HashMap<String, List<Statement>> getAllStatements(PortCall call) {
        HashMap<String, List<Statement>> output = new HashMap<>();

        for (ProcessStep processStep :
                call.getProcessSteps()) {
            for (SubProcess subProcess :
                    processStep.getSubProcesses()) {
                for (Event event :
                        subProcess.getEvents()) {
                    for (State state :
                            event.getStates()) {
                        List<Statement> statements = state.getStatements();
                        output.put(state.getStateDefinitionId(),statements);

                    }
                }
            }
        }

        return output;
    }

    // Lägger in alla senaste statements i en HashMap med statens ID som key och timeType + timeStatement som value.
    private HashMap<String, String> getAllLatestStatements(PortCall call){

        // Skapar ny hashmap
        HashMap<String, String> output = new HashMap<>();

        // Tar ut alla ProcessSteps från callen
        List<ProcessStep> steps = call.getProcessSteps();

        for (ProcessStep step : steps) {

            //System.out.println("Step: " + step.getDefinitionName());

            // Tar ut alla SubProcesses från ProcessStepen
            List<SubProcess> substeps = step.getSubProcesses();

            for (SubProcess substep : substeps){

                //System.out.println("\tSubstep: " + substep.getDefinitionName());

                // Tar ut alla Events från SubProcessen
                List<Event> events = substep.getEvents();

                for (Event event : events){

                    //System.out.println("\t\tEvent: " + event.getDefinitionName());

                    // Tar ut alla States från SubProcessen
                    List<State> states = event.getStates();

                    for (State state : states) {

                        //System.out.println("\t\t\tState: " + state.getDefinitionName() + ", id: " + state.getStateDefinitionId());

                        String id = state.getStateDefinitionId();

                        // Tar ut alla Statements från Staten
                        List<Statement> statements = state.getStatements();

                        // Kollar om de finns
                        if (!statements.isEmpty()) {

                            // Tar fram senaste uppdateringen
                            Statement statement = statements.get(statements.size() - 1);

                            // Kombinerar timeType och timeStatement till en metod
                            String time = statement.getTimeType().toString() + " " + statement.getTimeStatement();

                            // Lägger in den i hashmapen
                            output.put(id, time);
                        }
                    }
                }
            }
        }

        // Returnerar alla statements som hittades
        return output;
    }

}
