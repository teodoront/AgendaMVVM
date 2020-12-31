package br.com.orlando.myapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;


import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.repository.ContactsRepository;

//para pegar um contexto dentro de uma ViewModel, precia extender de AndroidViewModel
public class FormContactViewModel extends AndroidViewModel {
    private ContactsRepository mRepository;

    public FormContactViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = ContactsRepository.getInstance(application.getApplicationContext());
    }

    //Aqui trato regras de negócios

    public void save(ContactModel contact){
        this.mRepository.insert(contact);


    }

}
