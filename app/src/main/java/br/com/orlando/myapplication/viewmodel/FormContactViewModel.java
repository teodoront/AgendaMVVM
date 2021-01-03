package br.com.orlando.myapplication.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import br.com.orlando.myapplication.constants.ContactConstants;
import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.repository.ContactsRepository;

//para pegar um contexto dentro de uma ViewModel, precia extender de AndroidViewModel
public class FormContactViewModel extends AndroidViewModel {
    private ContactsRepository mRepository;
    private Context context = getApplication().getApplicationContext();

    private MutableLiveData<ContactModel> mContact = new MutableLiveData<>();
    public LiveData<ContactModel> contact = this.mContact;

    private MutableLiveData<Boolean> mValidation = new MutableLiveData<>();
    public LiveData<Boolean> validation = this.mValidation;

    public FormContactViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = ContactsRepository.getInstance(application.getApplicationContext());
    }

    //Aqui trato regras de negócios

    public void save(ContactModel contact) {

        if (validationValues(contact) == false) {
            if (contact.getId() == 0) {
                this.mValidation.setValue(this.mRepository.insert(contact));
            } else {
                this.mValidation.setValue(this.mRepository.update(contact));
            }
        }


    }


    public void load(int id) {
        ContactModel contactModel = this.mRepository.load(id);
        this.mContact.setValue(contactModel);
    }

    public boolean validationValues(ContactModel contact) {
        if (contact.getName().isEmpty() || contact.getName().equals("") || contact.getEmail().isEmpty() || contact.getEmail().equals("")
                || contact.getTelephone().isEmpty() || contact.getTelephone().equals("")) {
            Toast.makeText(context, "Todos os campos devem estar preenchidos", Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }


}
