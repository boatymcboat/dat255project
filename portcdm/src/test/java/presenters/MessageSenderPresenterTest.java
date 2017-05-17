package presenters;

import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by teodor on 2017-05-17.
 */
class MessageSenderPresenterTest {
    @Test
    void sendlocationstate() {
        MessageSenderPresenter p = new MessageSenderPresenter();
        p.sendlocationstate(new ActionEvent());
    }

    @Test
    void sendservicestate() {
        MessageSenderPresenter p = new MessageSenderPresenter();
        p.sendservicestate(new ActionEvent());
    }

}