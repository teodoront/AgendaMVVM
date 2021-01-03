package br.com.orlando.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.orlando.myapplication.R;
import br.com.orlando.myapplication.constants.ContactConstants;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.viewmodel.FormContactViewModel;


public class FormContactActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private FormContactViewModel mViewModel;
    private int mContactId = 0;


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
        this.setObservers();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            this.mContactId = bundle.getInt(ContactConstants.CONTACT_ID);
            this.mViewModel.load(this.mContactId);
        }

    }

    private void setObservers() {
        //observando criar
        this.mViewModel.contact.observe(this, new Observer<ContactModel>() {
            @Override
            public void onChanged(ContactModel model) {
                mViewHolder.editName.setText(model.getName());
                mViewHolder.editEmail.setText(model.getEmail());
                mViewHolder.editTel.setText(model.getTelephone());

            }
        });
        //observando salvar e atualizar
        this.mViewModel.validation.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == true) {
                    if (mContactId != 0) {
                        Toast.makeText(getApplicationContext(), "Atualizado com sucesso", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Inserido com sucesso", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Houve erro ao salvar", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void setListeners() {
        this.mViewHolder.btnSaveCont.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_save_cont) {
            this.handleSave();
        }
    }

    private void handleSave() {

        String name = this.mViewHolder.editName.getText().toString();
        String email = this.mViewHolder.editEmail.getText().toString();
        String telephone = this.mViewHolder.editTel.getText().toString();

        ContactModel contact = new ContactModel(this.mContactId, name, email, telephone);
        if (mViewModel.validationValues(contact) == false) {
            this.mViewModel.save(contact);
            finish();
        } else {
            mViewHolder.editName.requestFocus();
        }


    }


    private static class ViewHolder {

        EditText editName;
        EditText editEmail;
        EditText editTel;
        Button btnSaveCont;
    }

}