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

import java.util.List;

import br.com.orlando.myapplication.model.ContactModel;
import br.com.orlando.myapplication.model.Feedback;
import br.com.orlando.myapplication.repository.ContactsRepository;


//Aqui trato regras de negócios;
public class ShowContactsViewModel extends AndroidViewModel {


    private ContactsRepository mRepository;
    private Context context;

    private MutableLiveData<List<ContactModel>> mContactList = new MutableLiveData<>();
    public LiveData<List<ContactModel>> contactList = this.mContactList;

    private MutableLiveData<Feedback> mFeedback = new MutableLiveData<>();
    public LiveData<Feedback> feedback = this.mFeedback;


    public ShowContactsViewModel(@NonNull Application application) {
        super(application);
        this.mRepository = ContactsRepository.getInstance(application.getApplicationContext());
    }


    public void getList() {

        List<ContactModel> list = mRepository.getList();
        this.mContactList.setValue(list);

    }

    public void delete(int id) {
        if (this.mRepository.delete(id)) {
            this.mFeedback.setValue(new Feedback("Contato removido com sucesso!!!!"));
        }else {
            this.mFeedback.setValue(new Feedback("Houve erro ao remover contato!!!!", false));
        }
    }

}
