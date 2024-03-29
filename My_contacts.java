package shiv2.save.life.line.line2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class My_contacts extends AppCompatActivity {


    private static final int REQUEST_LOCATION = 1;
    public FirebaseFirestore mfire;


    String lattitude,longitude;
    LocationManager locationManager;

    DatabaseHelper mDatabaseHelper;

    Button b1,b2;
    public ListView mListView;
    String s1;
    String s ="";

    @ServerTimestamp
    Date Time;



    @Override
    public void onStart() {


        populateListView();

        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_contacts);
        mListView = (ListView) findViewById(R.id.list66);
        mDatabaseHelper = new DatabaseHelper(this);
        mfire = FirebaseFirestore.getInstance();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {


        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }
        ActivityCompat.requestPermissions
                (this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);







        findViewById(R.id.bty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSMS();
                Map<String, FieldValue> userMap = new HashMap<>();

                userMap.put("PANIC TIME", FieldValue.serverTimestamp());


                mfire.collection("PANIC TIME").document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(My_contacts.this,"Done",Toast.LENGTH_SHORT).show();




                    }
                });



                mfire.collection("PANIC TIME")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                        .update("LATI",lattitude);
                mfire.collection("PANIC TIME")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                        .update("LONGI",longitude);

            }
        });
        findViewById(R.id.bty2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gothelp();
                Map<String, FieldValue> userMap = new HashMap<>();


                mfire.collection("PANIC TIME")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber())
                        .update("GOT HELP TIME",FieldValue.serverTimestamp());

            }
        });

        findViewById(R.id.bty67).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(My_contacts.this,Add_contacts.class);
                startActivity(i);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();

                Cursor data = mDatabaseHelper.getItemID(name); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){





                    mDatabaseHelper.deleteName(itemID,name);
                    populateListView();


                }
                else{

                    Toast.makeText(My_contacts.this,"No",Toast.LENGTH_SHORT).show();

                }

            }
        });




    }



    public void populateListView() {


        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()) {
            String details = data.getString(1);
            int len = details.length();
            listData.add(details);
        }

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);

        mListView.setAdapter(adapter);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_HEADSETHOOK){
            //handle click
            sendSMS();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void sendSMS()
    {
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            String details = data.getString(1);
            int len = details.length();
            int i,c=0;
            for( i=len-1;i>=0;i--)
            {
                char ch=details.charAt(i);
                if(ch==1||ch==2||ch==3||ch==4||ch==5||ch==6||ch==7||ch==8||ch==9||ch==0)
                {

                    c=i-9;
                    i=-1;
                }
            }
            String str=details.substring(c);
            String e2 = "7277532033";
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(str, null, " MEDICLE HELP NEEDED . SEND AMBILANCE ASAP ! My location is: http://maps.google.com/?q=" +lattitude+","+longitude  , null, null);
            sms.sendTextMessage(e2, null, " MEDICLE HELP NEEDED . SEND AMBILANCE ASAP ! My location is: http://lodgeaway.epizy.com/locate.php?lati="+lattitude+"&longi="+longitude ,null, null);
        }





    }


    public void gothelp()
    {
        Cursor data2 = mDatabaseHelper.getData();
        while (data2.moveToNext()) {
            String details = data2.getString(1);
            int len = details.length();
            int i,c=0;
            for( i=len-1;i>=0;i--)
            {
                char ch=details.charAt(i);
                if(ch==1||ch==2||ch==3||ch==4||ch==5||ch==6||ch==7||ch==8||ch==9||ch==0)
                {

                    c=i-9;
                    i=-1;
                }
            }
            String str=details.substring(c);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(str, null, " I GOT HELP . YOU CAN CALL ME TO VERIFY IF I AM SAFE . IF I DIDNT RECIEVE CONTACT ME AT THIS LOCATION", null, null);
        }



    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(My_contacts.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (My_contacts.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(My_contacts.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);

            if (location != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);



            } else  if (location1 != null) {
                double latti = location1.getLatitude();
                double longi = location1.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);




            } else  if (location2 != null) {
                double latti = location2.getLatitude();
                double longi = location2.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);



            }else{

                Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();

            }
        }
    }



}