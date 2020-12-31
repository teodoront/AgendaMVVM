package br.com.orlando.myapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.view.adapter.ContactAdapter;
import br.com.orlando.myapplication.viewmodel.ShowContactsViewModel;


public class ShowContactsActivity extends AppCompatActivity {

    private ShowContactsViewModel mViewModel;
    private ViewHolder mViewHolder = new ViewHolder();
    private ContactAdapter mAdapter = new ContactAdapter();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_show_contacts);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Minha Agenda");
        String title = actionBar.getTitle().toString();

        this.mViewModel = new ViewModelProvider(this).get(ShowContactsViewModel.class);


        this.mViewHolder.recyclerViewContacts = findViewById(R.id.recycler_list);
        this.mViewHolder.recyclerViewContacts.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.mViewHolder.recyclerViewContacts.setAdapter(this.mAdapter);


        this.observers();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Infle o menu de adicionar na action bar;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.miCompose) {
            Intent intent = new Intent(ShowContactsActivity.this, FormContactActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.mViewModel.getList();
    }

    private void observers() {
        this.mViewModel.contactList.observe(this, new Observer<List<ContactModel>>() {
            @Override
            public void onChanged(List<ContactModel> list) {
                mAdapter.attachList(list);
            }
        });

    }


    private static class ViewHolder {

        RecyclerView recyclerViewContacts;
    }


}



