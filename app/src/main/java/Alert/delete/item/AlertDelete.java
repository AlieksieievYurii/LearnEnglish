package Alert.delete.item;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AlertDelete
{
    public static void showAlert(final Context context, final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Do you want to remove it?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((AlertDeleteWord)context).deleteWord(position);
            }
        });
        builder.setNegativeButton("No",null);
        builder.show();
    }
}
