package shiv2.save.life.line.line2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class heatmap extends Activity {


    public FirebaseFirestore mfire;
    public EditText ed1, ed2;
    public String a1,a2,a3,a4;
    public Button b1;
    public TextView trr,m1,m2,m3,m4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heatmap);

        mfire = FirebaseFirestore.getInstance();

        ed1 = findViewById(R.id.et11);

        b1 = findViewById(R.id.button);
        trr = findViewById(R.id.tv00);
        m1 = findViewById(R.id.tv3);
        m2 = findViewById(R.id.tv2);
        m3= findViewById(R.id.tv);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mfire.collection("track")
                        .document("anum").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                                {

                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    if (documentSnapshot.exists())

                                    {


                                        String a2 = documentSnapshot.getData().get("num").toString();
                                        String a3 = documentSnapshot.getData().get("cond").toString();
                                        String a5 = documentSnapshot.getData().get("age").toString();
                                        String a4 = documentSnapshot.getData().get("med").toString();
                                        String a6 = documentSnapshot.getData().get("name").toString();
                                        a1 = ed1.getText().toString();


                                        if (a1.contentEquals(a2.toString()))


                                        {

                                            trr.setText(a3);
                                            m2.setText(a6);
                                            m3.setText(a5);
                                            m1.setText(a4);
                                        }
                                    }
                                }

                            }
                        });

            }
        });






    }
}
