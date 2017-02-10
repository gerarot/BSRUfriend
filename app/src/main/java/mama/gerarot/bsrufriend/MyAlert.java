package mama.gerarot.bsrufriend;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Gerarot-PC on 10/2/2560.
 */

public class MyAlert {
    //Explicrt
    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }   // Constructor

    public void myDialog(String strTitle, String strMessage) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.bird48);
        builder.setTitle(strTitle);
        builder.setMessage(strMessage);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();

            }
        });
        builder.show();
    }   //myDialog

} //Main Class
