package br.com.orlando.myapplication.view.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.model.ContactModel;

public class ContactsViewHolder extends RecyclerView.ViewHolder {

    private TextView mTextname;
    private TextView mTextTel;
    private TextView mTextEmail;


    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mTextname = itemView.findViewById(R.id.text_card_name);
        this.mTextTel = itemView.findViewById(R.id.text_card_telephone);
        this.mTextEmail = itemView.findViewById(R.id.text_card_email);

    }

    public void bind(ContactModel contact){
        this.mTextname.setText(contact.getName());
        this.mTextTel.setText(contact.getTelephone());
        this.mTextEmail.setText(contact.getEmail());

    }

}
