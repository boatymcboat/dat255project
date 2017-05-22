package model;

import eu.portcdm.dto.Actor;
import eu.portcdm.dto.Statement;

import java.util.*;

/**
 * Created by arono on 2017-05-21.
 */
public class TimeStampManager {

    private HashMap<String, List<Statement>> statements;

    public TimeStampManager(HashMap<String, List<Statement>> statements){
        setStatements(statements);

        for (String key :
                statements.keySet()) {
            System.out.println(statements.get(key).toString());
        }

    }

    public void setStatements(HashMap<String, List<Statement>> statements){
        this.statements = statements;
    }

    public Status checkStatements(String id){
        List<Statement> list = statements.get(id);

        if (list == null){
            return Status.NONE;
        }

        if (list.size() == 0)
            return Status.NONE;

        if (list.get(list.size()-1).getTimeType().equals(Statement.TimeTypeEnum.ACTUAL)){
            return Status.ACTUAL;
        }

        if (list.size() == 1){
            return Status.OK;
        }

        Map<Actor, Statement> uniqueStatements = new TreeMap<Actor, Statement>();
        for (Statement statement :
                list) {
            uniqueStatements.put(statement.getReportedBy(),statement);
        }
        int difftimes = 0;
        String timestamp = "";
        for (Actor actor :
                uniqueStatements.keySet()) {
            String reportedtime = uniqueStatements.get(actor).getTimeStatement();
            if ( !timestamp.equals(reportedtime) ){
                difftimes++;
                timestamp = reportedtime;
            }
        }
        if ( difftimes > 1 ){
            return Status.WARNING;
        }

        return Status.WARNING;
    }


}
