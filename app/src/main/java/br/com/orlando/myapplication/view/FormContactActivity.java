package br.com.orlando.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.viewmodel.FormContactViewModel;


public class FormContactActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private FormContactViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_form_contact);

        this.mViewModel = new ViewModelProvider(this).get(FormContactViewModel.class);

        this.mViewHolder.editName = findViewById(R.id.edt_name_cont);
        this.mViewHolder.editEmail = findViewById(R.id.edt_email_cont);
        this.mViewHolder.editTel = findViewById(R.id.edt_tel_cont);
        this.mViewHolder.btnSaveCont = findViewById(R.id.btn_save_cont);

        this.setListeners();

    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_save_cont){
            this.handleSave();
        }

    }

    private void setListeners(){
        this.mViewHolder.btnSaveCont.setOnClickListener(this);

    }

    private void handleSave(){
        int id = 0;
        String name = this.mViewHolder.editName.getText().toString();
        String email = this.mViewHolder.editEmail.getText().toString();
        String telephone = this.mViewHolder.editTel.getText().toString();

        ContactModel contact = new ContactModel(id, name, email, telephone);
        this.mViewModel.save(contact);
        finish();

    }


    private static class ViewHolder{

        EditText editName;
        EditText editEmail;
        EditText editTel;
        Button btnSaveCont;
    }

}