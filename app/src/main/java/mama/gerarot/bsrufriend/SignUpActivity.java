package mama.gerarot.bsrufriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class SignUpActivity extends AppCompatActivity {

    //Explicit การประกาศตัวแปร
    private EditText nameEditText, userEditText, passEditText;
    private ImageView imageView;
    private RadioGroup radioGroup;
    private Button button;
    private String nameString, userString, passString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        bindwidget();

        // Button Controller
        bottoncontroller();

    }   //Main Method

    private void bottoncontroller() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get Value From Edit text
                nameString = nameEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();

                //Check Space
                if (nameString.equals("") || userString.equals("") || passString.equals("")) {
                    //True ==> Have Space
                    MyAlert myAlert = new MyAlert(SignUpActivity.this);
                    myAlert.myDialog("มีช่องว่าง","กรุณากรอกให้ครบทุกช่องเว้ย");
                }
            }   //onClick
        });
    }   // buttonController

    private void bindwidget() {
        nameEditText = (EditText) findViewById(R.id.editText);
        userEditText = (EditText) findViewById(R.id.editText4);
        passEditText = (EditText) findViewById(R.id.editText6);
        imageView = (ImageView) findViewById(R.id.imageView4);
        radioGroup = (RadioGroup) findViewById(R.id.ragAvata);
        button = (Button) findViewById(R.id.button3);
    }   // baindwidget
}   // Main Class
