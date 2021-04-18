package com.example.task3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_Add = 1;
    private static final int REQ_Edit = 2;
    private static final int REQ_Delete = 3;

    List<Contact> Contacts = null;
    ContactAdapter adapter = null;

    /*private List<Contact> contacts;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise data collection
        contacts = Contact.getContacts();

        // Create an adapter to map single data element -> single item view
        adapter = new ContactAdapter(contacts);

        // How will the individual items be laid out in the collection view?
        //RecyclerView.LayoutManager layoutManager;
        //layoutManager = new LinearLayoutManager(getApplicationContext());

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Assign new layout manager to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.lstPeople);
        lstPeople.setLayoutManager(layoutManager);

        // Assign adapter to "list" viewer
        //RecyclerView lstPeople = findViewById(R.id.lstPeople);
        //lstPeople.setLayoutManager(layoutManager);
        lstPeople.setAdapter(adapter);

        // Set extra parameters if needed.
        // This decorator adds extra spacing around all items in the recycle view.
        //lstPeople.addItemDecoration(new EqualSpaceItemDecoration(18));

        // Attach item selected event handler
        RecyclerView.OnItemTouchListener listener = new RecyclerView.SimpleOnItemTouchListener();
        lstPeople.addOnItemTouchListener(listener);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Get the buttons and items from layout
        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        final Button btnDelete = (Button) findViewById(R.id.btnDelete);
        //RadioButton btnSortCell = (RadioButton) findViewById(R.id.rbCell);
        final ListView listViewContacs = (ListView) findViewById(R.id.Contactlist);

        //List of Contacts with Test Data
        Contacts = Contact.getContacts();
        Contacts = new ArrayList<Contact>();
        adapter = new ContactAdapter(this, Contacts);

        /*Contacts.add(new Contact("Luthando", "Mbaza", "0738315319", "ezmbaza@gmail.com"));
        Contacts.add(new Contact("Micahel", "Selby", "0758348139", "mselby@yahoo.com"));
        Contacts.add(new Contact("Sumaiya", "Moses", "0838327919", "smoses@gmail.com"));
        Contacts.add(new Contact("Damian", "Cairncross", "0728312949", "dcairncross@live.com"));
        Contacts.add(new Contact("Liezl", "Swigelaar", "0848583319", "lswigelaar@hotmail.com"));
*/
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new GridLayoutManager(getApplicationContext(),2);

        // Assign new layout manager to "list" viewer
        RecyclerView lstPeople = findViewById(R.id.Contactlist);
        lstPeople.setLayoutManager(layoutManager);
        listViewContacs.setAdapter(adapter);


        //Select an item from list and show it in edit screen in view mode
        listViewContacs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int pos = i;
                final Contact Current = (Contact) listViewContacs.getItemAtPosition(pos);

                Intent intent = new Intent(MainActivity.this, Edit_Activity.class);
                intent.putExtra("Contact", Current);
                intent.putExtra("Editing", false);
                intent.putExtra("Pos", i);
                startActivityForResult(intent, REQ_Edit);

            }
        });
        //Sends intent to Edit activity with editing set to true so editviews are visible
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Edit_Activity.class);
                intent.putExtra("Action", "Add");
                intent.putExtra("Editing", true);
                startActivityForResult(intent, REQ_Add);
            }
        });

        //Sets list view in order of surname when pressed
        /*btnSortSurname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.sort(new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contact, Contact t1) {
                        return contact.surname.compareTo(t1.surname);
                    }
                });
            }
        });
        //Sets list view in order of cell number when pressed
        btnSortCell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.sort(new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contact, Contact t1) {
                        return contact.cellNumber.compareTo(t1.cellNumber);
                    }
                });
            }
        });
        //Sets list view in order of email address when pressed
        btnSortEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.sort(new Comparator<Contact>() {
                    @Override
                    public int compare(Contact contact, Contact t1) {
                        return contact.emailAddress.compareTo(t1.emailAddress);
                    }
                });
            }
        });*/


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Bundle extras = data.getExtras();

            if (extras != null) {
                Contact Current = (Contact) extras.getSerializable("Contact");
                Boolean Edited = extras.getBoolean("Edit");
                Boolean Deleted = extras.getBoolean("Delete");
                Integer Pos = extras.getInt("Pos");

                if ((Edited == false) && (Deleted == false)) {//If the contact was not edited or deleted, add it
                    Contacts.add(Current);
                    adapter.notifyDataSetChanged();
                } else {
                    if (Deleted == false) { //otherwise if it was not deleted, find that contact and edit it with the info of the new contact
                        Contact Old = adapter.getItem(Pos);

                        if (Old != null) {
                            Old.setName(Current.getName());
                            Old.setCellNumber(Current.getCellNumber());
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        if (Deleted == true) {//otherwise delete the contact
                            Contact remove = adapter.getItem(Pos);
                            adapter.remove(remove);
                        }
                    }
                }
            }
        }

    }
    }




    /*public void onAddClicked(View view) {
        // Add a new person via the adapter.
        adapter.add(new Contact("Someone", 18, -1));
    }

    public void onDeleteClicked(View view) {
        // Delete the last person (if there is one).
        if(adapter.getItemCount() > 0)
            adapter.remove(adapter.getItemCount() - 1);
    }*/







