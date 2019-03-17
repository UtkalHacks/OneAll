package shiv2.save.life.line.line2;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class upload_bills extends AppCompatActivity {


     Button mbut,muplod;
    TextView t5;
    private EditText e1, e10;
    int j=1;
    public static final  int GALARY_INTENT =2;
    String ph9;
    int o=1;
    ImageView img,img2;
    public FirebaseFirestore mfire;
    String s23,s24;


    String type1;
    public StorageReference mstore;
    ImageView ik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_bills);

        mfire = FirebaseFirestore.getInstance();
        mbut = findViewById(R.id.button4);
        mstore = FirebaseStorage.getInstance().getReference();
        ph9 = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        img2 = findViewById(R.id.imageView3);

        ik = findViewById(R.id.imageView890);
        ik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent r = new Intent(Intent.ACTION_PICK);
                r.setType("image/*");
                startActivityForResult(r, GALARY_INTENT);


            }
        });




        mbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                mstore.child(ph9+"/"+ j).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        s24 = uri.toString();

                        Toast.makeText(upload_bills.this,"Loading photos and documents",Toast.LENGTH_LONG).show();

                        Picasso.get().load(s24).into(img2);
                        img2.setVisibility(View.VISIBLE);





                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mbut.setText("Uploading...Please Wait");


        if(requestCode==GALARY_INTENT && resultCode==RESULT_OK)
        {
            Uri uri = data.getData();
            final int[] i = {0};

            if (i[0] ==0)
            {

                StorageReference filepath  = mstore.child(ph9+"/"+ j);
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        mbut.setText("Uploading Complete");
                        j++;

                    }
                });
            }


        }
    }

}
