package model;

import eu.portcdm.dto.Actor;
import eu.portcdm.dto.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Analyses Statements.
 */
public class TimeStampManager {

    private HashMap<String, List<Statement>> statements;

    /**
     * Creates an instance of the manager.
     *
     * @param statements a HashMap with the StateDefinitionIDs as keys and lists of Statements as entries
     */
    public TimeStampManager(HashMap<String, List<Statement>> statements){
        setStatements(statements);
    }

    /**
     * Updates the active Statements.
     *
     * @param statements a HashMap with the StateDefinitionIDs as keys and lists of Statements as entries
     */
    public void setStatements(HashMap<String, List<Statement>> statements){
        this.statements = statements;
    }

    /**
     * Analyses all Statements with a given StateDefinitionID.
     * @param id the requested StateDefinitionID
     * @return a Status Enum
     */
    public Status checkStatements(String id){
        List<Statement> list = statements.get(id);

        // Om inga statements finns
        if (list == null){
            return Status.NONE;
        }
        if (list.size() == 0)
            return Status.NONE;

        // Om senaste statement är en ACTUAL
        if (list.get(list.size()-1).getTimeType().equals(Statement.TimeTypeEnum.ACTUAL)){
            return Status.ACTUAL;
        }

        // Om det bara finns en statement
        if (list.size() == 1){
            return Status.OK;
        }

        // Tar fram alla statements från unika aktörer
        Map<Actor, Statement> uniqueStatements = new HashMap<>();
        for (Statement statement :
                list) {
            uniqueStatements.put(statement.getReportedBy(),statement);
        }

        // Om bara en aktör lämnat statements
        if (uniqueStatements.size() == 1) {
            return Status.OK;
        }

        // Om flera aktörer, finns flera olika statements?
        int difftimes = 0;
        String timestamp = "";
        for (Map.Entry<Actor, Statement> entry :
                uniqueStatements.entrySet()) {
            String reportedTime = entry.getValue().getTimeStatement();
            if (!timestamp.equals(reportedTime) ){
                difftimes++;
                timestamp = reportedTime;
            }
        }
        if ( difftimes > 1 ){
            return Status.WARNING;
        } else {
            return Status.OK;
        }
    }


}
