package br.com.orlando.myapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.view.viewholder.ContactsViewHolder;

public class ContactAdapter extends RecyclerView.Adapter<ContactsViewHolder> {
    private List<ContactModel> mList = new ArrayList<>();



    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.contacts_item, parent, false);

        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {

        holder.bind(this.mList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.mList.size();
    }


    //trazer a lista
    public void attachList(List<ContactModel> list){
        this.mList = list;
        notifyDataSetChanged();
    }
}
