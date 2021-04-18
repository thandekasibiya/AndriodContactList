package com.example.task3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.PersonViewHolder>
{

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        public TextView lblName;
        public TextView lblAge;
        public ImageView imgAvatar;
        public Contact contact;

        public PersonViewHolder(@NonNull View view) {
            super(view);

            // Get references to commonly used Views in the layout.
            lblName = view.findViewById(R.id.lblName);
            lblAge = view.findViewById(R.id.lblAge);
            imgAvatar = view.findViewById(R.id.imgAvatar);
        }

        public void setPerson(Contact contact) {
            this.contact = contact;

            lblName.setText(contact.name);
            lblAge.setText("Age: " + contact.age + " years"); // force conversion to string

            // Set image. Might not exist depending on the layout used. So check for null
            // values before setting.
            if(imgAvatar != null) {
                // Set image.
                switch(contact.iconIndex) {
                    case 0:
                        imgAvatar.setImageResource(R.drawable.avatar_01);
                        break;
                    case 1:
                        imgAvatar.setImageResource(R.drawable.avatar_02);
                        break;
                    case 2:
                        imgAvatar.setImageResource(R.drawable.avatar_03);
                        break;
                    case 3:
                        imgAvatar.setImageResource(R.drawable.avatar_04);
                        break;
                    case 4:
                        imgAvatar.setImageResource(R.drawable.avatar_05);
                        break;
                    case 5:
                        imgAvatar.setImageResource(R.drawable.avatar_06);
                        break;
                    case 6:
                        imgAvatar.setImageResource(R.drawable.avatar_07);
                        break;
                    case 7:
                        imgAvatar.setImageResource(R.drawable.avatar_08);
                        break;
                    case 8:
                        imgAvatar.setImageResource(R.drawable.avatar_09);
                        break;
                    default:
                        imgAvatar.setImageResource(R.drawable.avatar_pokemon);
                }
            }

        }
    }





    // The collection of data that this adapter is currently displaying.
    private final List<Contact> contacts;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This method is called by Android when it needs a brand new View to display
        // a single person. The ViewHolder will hold a reference to this newly created View.

        // Inflate (create) the UI scenegraph from the layout xml resource.
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activity_contact,
                        //.inflate(R.layout.recyclerview_person_simple_details,
                        parent, false);

        // Put it into a View Holder object and return this.
        PersonViewHolder pvh = new PersonViewHolder(view);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        // Given the View Holder and an index to the View to be used to display it,
        // fill the data item's values into the view.

        // Get the data to be displayed
        Contact person = contacts.get(position);

        // Fill the data from person into the view.
        holder.setPerson(person);
    }





    @Override
    public int getItemCount() {
        return contacts.size();
    }




}

/*public class ContactAdapter extends ArrayAdapter<Contact> {

    public ContactAdapter(Context context, List<Contact> objects) {
        super(context,R.layout.activity_contact, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // get the inflater that will convert the contact_layout.xml file into an
        // actual object
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //create a view to display the contacts's info
        View contactView = inflater.inflate(R.layout.activity_contact, parent, false);

        // keep track of contact this view is working with
        contactView.setTag(getItem(position));

        // get text views that will hold strings
        TextView txtName = (TextView) contactView.findViewById(R.id.Name);
        TextView txtCellNo = (TextView) contactView.findViewById(R.id.cellnumber);


        // set text field
        txtName.setText(getItem(position).getName());
        txtCellNo.setText(getItem(position).getCellNumber());

        return contactView;
    }
}*/
