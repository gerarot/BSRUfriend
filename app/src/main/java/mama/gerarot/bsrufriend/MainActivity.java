package mama.gerarot.bsrufriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // Explicit การประกาศตัวแปร
    private Button signInButton, signUpButton;
    private EditText userEditText, passEditText;
    private String userString, passString;
    private String[] loginStrings;
    private static final String urlPHP = "http://swiftcodingthai.com/bsru/get_user_artsist.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind Widget คือ การ Initial Var กับ View บน XML
        signInButton = (Button) findViewById(R.id.button);
        signUpButton = (Button) findViewById(R.id.button2);
        userEditText = (EditText) findViewById(R.id.editText2);
        passEditText = (EditText) findViewById(R.id.editText3);

        // Button Controller
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Check Space and get Value Form Edit Text
                userString = userEditText.getText().toString().trim();
                passString = passEditText.getText().toString().trim();
                if (userString.equals("") || passString.equals("")) {
                    MyAlert myAlert = new MyAlert(MainActivity.this);
                    myAlert.myDialog("คุณมีช่องว่างนะ","คุณต้องกรอกข้อมูลให้ครบนะ");
                } else {
                    //No Space
                    checkUserPass();
                }

            }   //onClick
        });

    } // Main Method

    private void checkUserPass() {

        try {

            GetUser getUser = new GetUser(MainActivity.this);
            getUser.execute(urlPHP);
            String strJSON = getUser.get();
            Log.d("16febV1", "strJSON ==>" + strJSON);


        } catch (Exception e) {
            Log.d("16febV1", "e checkUserPass ==>" + e.toString());
        }

    }   //checkUserPass
}   // Maim Class นี่คือ คลาสหลัก
