package upc.eetac.dsa.tracks.android;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;


public class NewTrkDialog extends DialogFragment {

    public interface OnDialogListener{
        void OnPositiveButtonClicked();
        void OnNegativeButtonClicked();

    }

    private OnDialogListener onDialogListener;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        onDialogListener = (OnDialogListener) getActivity();
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        builder.setTitle("Add a new Track")
                .setMessage("Fill the fields and click confirm.").setView(inflater.inflate(R.layout.new_track_layout, null))
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogListener.OnPositiveButtonClicked();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onDialogListener.OnNegativeButtonClicked();
                    }
                });

        return builder.create();

    }
}
