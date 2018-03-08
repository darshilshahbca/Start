package usercomms.joemarini.example.com.usercommunications;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;

public class NotificationActivity extends AppCompatActivity
    implements View.OnClickListener{

    private static final int NOTIFY_ID = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findViewById(R.id.btnNotification).setOnClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNotification() {
        // TODO: create the NotificationCompat Builder
        Notification.Builder builder = new Notification.Builder (this);

        // TODO: Create the intent that will start the ResultActivity when the user
        Intent intent = new Intent (this, NotificationResultActivity.class);
        intent.putExtra ("notifyID", NOTIFY_ID);
        PendingIntent pendingIntent = PendingIntent.getActivity (this, NOTIFY_ID, intent,PendingIntent.FLAG_CANCEL_CURRENT);
        // taps the notification or chooses an action button

        // Store the notification ID so we can cancel it later in the ResultActivity

        // TODO: Set the three required items all notifications must have
        builder.setSmallIcon (R.drawable.ic_stat_sample_notification);
        builder.setContentTitle ("Sample Notification");
        builder.setContentText ("This is a Sample Notification");


        // TODO: Set the notification to cancel when the user taps on it
        builder.setAutoCancel (true);


        // TODO: Set the large icon to be our app's launcher icon
        builder.setLargeIcon (BitmapFactory.decodeResource (getResources (),R.mipmap.ic_launcher));


        // TODO: Set the small subtext message
        builder.setSubText ("Tap To View");


        // TODO: Set the content intent to launch our result activity
        builder.setContentIntent (pendingIntent);


        // TODO: Add an expanded layout to the notification
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle ();
        bigTextStyle.setBigContentTitle ("This is a Big Notification");
        bigTextStyle.bigText (getResources ().getString (R.string.LongMsg));
        builder.setStyle (bigTextStyle);


        // TODO: Add action buttons to the Notification if they are supported
        // Use the same PendingIntent as we use for the main notification action
        builder.addAction (R.mipmap.ic_launcher, "Action 1", pendingIntent);
        builder.addAction (R.mipmap.ic_launcher, "Action 2", pendingIntent);

        // TODO: Set the lock screen visibility of the notification
        builder.setVisibility (Notification.VISIBILITY_PRIVATE  );


        // TODO: Build the finished notification and then display it to the user
        Notification notification = builder.build ();
        NotificationManager mgr = (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
        mgr.notify (NOTIFY_ID, notification);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnNotification) {
            createNotification();
        }
    }
}
