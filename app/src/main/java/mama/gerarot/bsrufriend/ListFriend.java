package mama.gerarot.bsrufriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

public class ListFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);

        ListView listView = (ListView) findViewById(R.id.livFriend);

        //Create ListView
        try {
            GetUser getUser = new GetUser(ListFriend.this);
            MyConstant myConstant = new MyConstant();
            getUser.execute(myConstant.getUrlPHPString());
            String strJSON = getUser.get();

            JSONArray jsonArray = new JSONArray(strJSON);
            String[] nameString = new String[jsonArray.length()];
            String[] imageString = new String[jsonArray.length()];
            String[] latString = new String[jsonArray.length()];
            String[] lngString = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                nameString[i] = jsonObject.getString("Name");
                imageString[i] = jsonObject.getString("Image");
                latString[i] = jsonObject.getString("Lat");
                lngString[i] = jsonObject.getString("Lng");
            }   //for

            FriendAdapter friendAdapter = new FriendAdapter(ListFriend.this,
                    imageString, nameString);
            listView.setAdapter(friendAdapter);

        } catch (Exception e) {
            Log.d("17febV3", "e ListView ==>" + e.toString());
        }

    }   //Main Method
}   //MainClass
