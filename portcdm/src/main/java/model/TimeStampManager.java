package model;

import eu.portcdm.dto.Statement;

import java.util.HashMap;
import java.util.List;

/**
 * Created by arono on 2017-05-21.
 */
public class TimeStampManager {

    private HashMap<String, List<Statement>> statements;

    public TimeStampManager(HashMap<String, List<Statement>> statements){
        setStatements(statements);
        /*
        for (String key :
                statements.keySet()) {
            System.out.println(key);
        }
        */
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

        return Status.WARNING;
    }


}
