package sample;

import se.viktoria.stm.portcdm.connector.common.*;
import se.viktoria.util.*;

import java.util.Map;
import java.util.function.Predicate;

public class Controller {
    public static void connect(){
        Configuration configuration;
        // * Read the configuration file

        configuration = new Configuration("config.conf","/",new Predicate<Map.Entry<Object,Object>>() {
            @Override
            public boolean test(Map.Entry<Object,Object> objectObjectEntry) {
                return !objectObjectEntry.getKey().toString().equals("pass");
            }
        });
        configuration.reload();


        // ** Create a submission service and add connectors
        SubmissionService submissionService;
        submissionService = new SubmissionService();
        submissionService.addConnectors(configuration);

        // ** Create a list of port call messages (somehow)
        // List<PortCallMessage> messages;
        // messages = createMessages();
        // ** Submit the messages
        //submissionService.submitUpdates(messages);
    }

}
