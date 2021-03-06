package co.gorapido.rapido;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by rapido on 6/17/15.
 */
public class FacebookPhoneDialogFragment extends DialogFragment {
    public static final String ENTER_DETAILS = "Enter Phone Number...";
    private EditText ETphoneNumber;
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View v = inflater.inflate(R.layout.dialog_phone, null);
        ETphoneNumber = (EditText)v.findViewById(R.id.edit_text_phone_number_fbk_dialog);
        builder.setView(v);
        builder.setMessage(ENTER_DETAILS)
                .setPositiveButton(R.string.ok_button, null);
        // Create the AlertDialog object and return it
        return builder.create();
    }
    @Override
    public void onStart() {
        super.onStart();
        AlertDialog dialog = (AlertDialog)getDialog();
        Button positiveButton = (Button) dialog.getButton(Dialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = ETphoneNumber.getText().toString();
                if (ParseHelper.isPhoneValid(phoneNumber)) {
                    ParseHelper.setStringForCurrentUser(ParseHelper.PHONE_NUMBER, phoneNumber);
                    ParseHelper.saveCurrentUser();
                    dismiss();
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    getActivity().startActivity(i);
                    Toast.makeText(getActivity(), R.string.success, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), R.string.general_entry_error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
