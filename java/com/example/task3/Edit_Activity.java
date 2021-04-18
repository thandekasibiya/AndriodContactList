package com.example.task3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.jar.Attributes;

public class Edit_Activity extends AppCompatActivity {

    EditText txtEDName;
    EditText txtEDSurname;
    EditText txtEDCell;
    EditText txtEDEmail;

    TextView txtVName;
    TextView txtVSurname;
    TextView txtVCell;
    TextView txtVEmail;

    Integer Pos = -1;
    Boolean Editing = false;
    Contact Current = null;

    //If editing is false it sets view mode and if true, sets edit mode
    public void setFields() {
        if (Editing == true) {
            txtEDName.setVisibility(View.VISIBLE);
            txtEDName.setText(Current.getName());
            txtEDCell.setVisibility(View.VISIBLE);
            txtEDCell.setText(Current.getCellNumber());


            txtVName.setVisibility(View.INVISIBLE);

            txtVCell.setVisibility(View.INVISIBLE);

        } else {
            txtEDName.setVisibility(View.INVISIBLE);

            txtEDCell.setVisibility(View.INVISIBLE);


            txtVName.setVisibility(View.VISIBLE);
            txtVName.setText(Current.name);
            txtVCell.setVisibility(View.VISIBLE);
            txtVCell.setText(Current.cellNumber);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_);

        txtEDName = (EditText) findViewById(R.id.txtEDName);
        txtEDSurname = (EditText) findViewById(R.id.txtEDSurname);
        txtEDCell = (EditText) findViewById(R.id.txtEDCell);
        txtEDEmail = (EditText) findViewById(R.id.txtEDEmail);

        txtVName = (TextView) findViewById(R.id.txtVName);
        txtVSurname = (TextView) findViewById(R.id.txtVSurname);
        txtVCell = (TextView) findViewById(R.id.txtVCell);
        txtVEmail = (TextView) findViewById(R.id.txtVEmail);

        Button btnEdit = (Button) findViewById(R.id.btnEdit);
        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        Button btnSave = (Button) findViewById(R.id.btnSave);

        //Allows change of modes
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Editing == false) {
                    Editing = true;
                    setFields();
                } else {
                    Editing = false;
                    setFields();
                }
            }
        });

        //Create alert dialog to delete a contact
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Edit_Activity.this);
                builder.setMessage(R.string.alertMessage);
                builder.setTitle(R.string.alertTitle);
                builder.setPositiveButton(R.string.alertPositive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent result = new Intent();
                        result.putExtra("Contact", Current);
                        result.putExtra("Edit", false);
                        result.putExtra("Delete", true);
                        result.putExtra("Pos", Pos);
                        setResult(Activity.RESULT_OK, result);
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        Intent intent = getIntent();

        if (intent != null) {

            Bundle extra = intent.getExtras();

            if (extra != null) {


                Current = (Contact) extra.getSerializable("Contact");
                Editing = extra.getBoolean("Editing");
                Pos = extra.getInt("Pos");

                if (Current == null) {//If no contact is sent, create a new one and send it back.
                    btnSave.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //remobePos
                            Contact NewOne = new Contact(txtEDName.getText().toString(), txtEDCell.getText().toString(),Pos);
                            Intent result = new Intent();
                            result.putExtra("Contact", NewOne);
                            result.putExtra("Edit", false);
                            result.putExtra("Delete", false);
                            setResult(Activity.RESULT_OK, result);
                            finish();
                        }
                    });
                } else {
                    setFields();
                    btnSave.setOnClickListener(new View.OnClickListener() {//Edit the current contact and send back a contact containing the edited info.
                        @Override
                        public void onClick(View view) {
                            //removePos
                            Contact NewOne = new Contact(txtEDName.getText().toString(), txtEDCell.getText().toString(),Pos);
                            Intent result = new Intent();
                            result.putExtra("Contact", NewOne);
                            result.putExtra("Edit", true);
                            result.putExtra("Delete", false);
                            result.putExtra("Pos", Pos);
                            setResult(Activity.RESULT_OK, result);
                            finish();
                        }
                    });


                }
            }
        }
    }

}
