package io.cloudly.bd.googlefitdialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by supto on 9/26/17.
 */

public class MyDialog extends MyBottomSheetDialogFragment {
    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public static MyDialog newInstance(@NonNull Context context) {
        MyDialog myDialog = new MyDialog(context);
        return myDialog;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
        setContentView(view);
    }
}
