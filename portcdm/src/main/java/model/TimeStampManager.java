package model;

import eu.portcdm.dto.Actor;
import eu.portcdm.dto.Statement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by arono on 2017-05-21.
 */
public class TimeStampManager {

    private HashMap<String, List<Statement>> statements;

    public TimeStampManager(HashMap<String, List<Statement>> statements){
        setStatements(statements);
    }

    public void setStatements(HashMap<String, List<Statement>> statements){
        this.statements = statements;
    }


    /*
    Kollar statements efter rimlighet. Indata: namnet på State Definition som skall tas fram. Utdata: Status-enums
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
        Map<Actor, Statement> uniqueStatements = new HashMap<Actor, Statement>();
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
        for (Actor actor :
                uniqueStatements.keySet()) {
            // TODO: Kolla om detta går att lösa på annat sätt. Detta borde vara mest effektivt men tydligen inte enligt FindBugs
            String reportedtime = uniqueStatements.get(actor).getTimeStatement();
            if ( !timestamp.equals(reportedtime) ){
                difftimes++;
                timestamp = reportedtime;
            }
        }
        if ( difftimes > 1 ){
            return Status.WARNING;
        } else {
            return Status.OK;
        }
    }


}
