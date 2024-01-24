package ar.juarce.services.listeners;

import ar.juarce.models.Prayer;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class NewPrayerListener {

    private static final Logger logger = LoggerFactory.getLogger(NewPrayerListener.class);

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public NewPrayerListener(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }


    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendNewPrayerNotification(@NonNull Prayer prayer) throws FirebaseMessagingException {
        logger.info("Prayer request owner: " + prayer.getPrayerRequest().getRequester());
        final Message message = Message.builder()
//                .setToken("todo")
                .setTopic("topic")
                .putData("prayer", prayer.toString())
                .build();
        final String messageId = firebaseMessaging.send(message);
        logger.info("Message sent: " + messageId);
    }
}
