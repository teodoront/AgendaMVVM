package br.com.orlando.myapplication.view.viewholder;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.view.listener.OnListClick;

public class ContactsViewHolder extends RecyclerView.ViewHolder {

    private TextView icon;
    private TextView mTextname;
    private TextView mTextTel;
    private TextView mTextEmail;
    private Context mContext;


    public ContactsViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mContext = itemView.getContext();
        this.icon = itemView.findViewById(R.id.text_icon);
        this.mTextname = itemView.findViewById(R.id.text_card_name);
        this.mTextTel = itemView.findViewById(R.id.text_card_telephone);
        this.mTextEmail = itemView.findViewById(R.id.text_card_email);


    }

    public void bind(final ContactModel contact, final OnListClick listener) {
        this.mTextname.setText(contact.getName());
        this.mTextTel.setText(contact.getTelephone());
        this.mTextEmail.setText(contact.getEmail());


        this.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onClick(contact.getId());
            }
        });

        this.icon.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(mContext)
                        .setTitle("Remoção de contato")
                        .setMessage("Deseja realmente remover o contato?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onDelete(contact.getId());
                            }
                        })
                        .setNeutralButton("Não", null)
                        .show();
                return false;
            }
        });

    }


}
