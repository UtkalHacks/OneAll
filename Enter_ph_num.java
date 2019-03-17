package shiv2.save.life.line.line2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Enter_ph_num extends AppCompatActivity {

    private EditText edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_ph_num);

        edit = findViewById(R.id.editTextMobile);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = edit.getText().toString().trim();








                Intent intent = new Intent(Enter_ph_num.this, verify.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
            }

        });
    }
}
