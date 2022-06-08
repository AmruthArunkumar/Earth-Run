package amruth.in.earthrun;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorManager;
import android.os.Vibrator;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.squareup.seismic.ShakeDetector;

import java.util.Random;

import pl.droidsonroids.gif.GifImageButton;

public class MainActivity extends AppCompatActivity implements ShakeDetector.Listener {

    String[] response = {
            "Cool",
            "Nice",
            "Ok",
            "Interesting",
            "Wow",
            "Amazing"
    };

    Vibrator vibrator;

    String[] facts = {
            "Did you know, Earth is the third planet from the sun",
            "Did you know, We are traveling around the sun at an average velocity of 107,182 km per hour",
            "Did you know, Earth receives between 100 and 300 metric tons of cosmic dust every day",
            "Did you know, water accounts for less than 1% of Earth's mass",
            "Did you know, Earth is slightly larger than Venus",
            "Did you know, Earth formed some 4.54 billion years ago",
            "Did you know, life came to existence on Earth some 4.1 billion years ago",
            "Did you know, Tides in oceans happen due to the force of gravitation between the Earth and the Moon",
            "Did you know, The Moon is thought to have formed from Earth after the impact of an object named Theia",
            "Did you know, 99% of all species that ever existed on the Earth are now extinct",
            "Did you know, Earth is the only known planet that supports life",
            "Did you know, Earth is the only planet that is not named after a Greek or Roman god",
            "Did you know, The speed of rotation of Earth is gradually slowing down",
            "Did you know, Earth was once believed to be the center of the universe",
            "Did you know, Because of the presence of inner Nickel-Iron core, Earth has a strong magnetic field",
            "Did you know, Earth's inner core has a temperature between 5400 and 6000 degrees celcius",
            "Did you know, Earth's inner core is actually hotter than the surface of the sun",
            "Did you know, The thickest of all the four layers of the Earth is the mantle",
            "Did you know, The thinnest of all the four layers of the Earth is the Crust",
            "Did you know, Earth's core is almost 85-88% iron",
            "Did you know, Earth's crust is almost 47% oxygen",
            "Did you know, There are five layers to Earth's atmosphere",
            "Did you know, Light from the sun reaches the Earth in approximately 8 minutes and 20 seconds",
            "Did you know, Earth's ozone layer protects the Earth from powerful and harmful UV rays",
            "Did you know, Earth is the densest planet in the Solar System"};

    int fc = 0;

    static TextView tvPoints;
    ProgressBar pbProgress, pbProgress2;
    GifImageButton gif;

    int point;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private int text3;

    public static final String SHARED_PREFS3 = "sharedPrefs3";
    public static final String TEXT3 = "text3";

    private int text;

    public static final String SHARED_PREFS2 = "sharedPrefs2";
    public static final String TEXT2 = "text2";

    private int text2;

    public String ss;
    boolean start = false;
    int message = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        ShakeDetector shakeDetector = new ShakeDetector(this);

        shakeDetector.start(sensorManager);

        tvPoints = findViewById(R.id.tvPoints);
        pbProgress = findViewById(R.id.pbProgress);
        pbProgress2 = findViewById(R.id.pbProgress2);
        gif = findViewById(R.id.gif);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getInt(TEXT, 0);

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        text3 = sharedPreferences3.getInt(TEXT3, 1);

        message = text3;
        point = text;
        tvPoints.setText(String.valueOf(point));

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        text2 = sharedPreferences2.getInt(TEXT2, 0);
        fc = text2;

        if (message == 1){
            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("How to play and Tips");
            alert.setMessage("This app can track your steps. Your goal is to reach 25K steps. Every 1,000th step you make, will trigger a fact about the Earth. If the app can't sense your steps accurately then try to move your hand more when you run. Make sure to keep the app open when running and make sure your screen's auto-tilt setting is switched off.");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    message = 1;
                }
            });
            alert.setNegativeButton("Don't show me this again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    message = 0;
                }
            });
            alert.create().show();
        }

    }

    @Override
    protected void onDestroy() {

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(TEXT, point).commit();

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

        editor2.putInt(TEXT2, fc).commit();

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();

        editor3.putInt(TEXT3, message).commit();

        super.onDestroy();


    }


    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(TEXT, point).commit();

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

        editor2.putInt(TEXT2, fc).commit();

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();

        editor3.putInt(TEXT3, message).commit();

    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(TEXT, point).commit();

        SharedPreferences sharedPreferences2 = getSharedPreferences(SHARED_PREFS2, MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharedPreferences2.edit();

        editor2.putInt(TEXT2, fc).commit();

        SharedPreferences sharedPreferences3 = getSharedPreferences(SHARED_PREFS3, MODE_PRIVATE);
        SharedPreferences.Editor editor3 = sharedPreferences3.edit();

        editor3.putInt(TEXT3, message).commit();

    }


    /**
     * Called on the main thread when the device is shaken.
     */
    @Override
    public void hearShake() {

        if (point < 25000) {
            point = point + 1;

            tvPoints.setText(String.valueOf(point));

            ss = facts[fc];
            Random random = new Random();
            int index = random.nextInt(response.length);

            if (point % 1000 == 0) {

                vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

                vibrator.vibrate(500);

                AlertDialog.Builder alert2 = new AlertDialog.Builder(MainActivity.this);
                alert2.setTitle("New Fact");
                alert2.setMessage(ss);
                alert2.setPositiveButton("Interesting", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        message = 1;
                    }
                });
                alert2.create().show();


                fc += 1;
            }

            if (point > 1000) {

                pbProgress2.setProgress(point % 1000);
            }
            else
            {
                pbProgress2.setProgress(point);
            }

            pbProgress.setProgress(point);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            );

        }

        if (point == 25000) {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("Earth Run: Congratulations!")
                    .setContentText("You have reached 25K Steps!");

            Intent notificationIntent = new Intent(this, MainActivity.class);
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(contentIntent);

            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }


    }
}