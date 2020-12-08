package com.example.progetto_android.view.login;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Dao;
import com.example.progetto_android.databinding.FragmentLoginBinding;
import com.example.progetto_android.globals.ShareDataViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private ShareDataViewModel sd;
    private LoginViewModel vm;
    private Dao dao;
    private FragmentLoginBinding loginbinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sd = ShareDataViewModel.getInstance();
        //sd = new ViewModelProvider(requireActivity()).get(ShareDataViewModel::getInstance());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        loginbinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = loginbinding.getRoot();
        loginbinding.setBd(vm);
        loginbinding.setLifecycleOwner(this);
        dao = Dao.getInstance(getContext());
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button login = view.findViewById(R.id.login);
        Button guest = view.findViewById(R.id.guest);
        loginClickListener(login);
        guestClickListener(guest);

    }

    public void loginClickListener(Button login) {
        login.setOnClickListener(view1 -> {
            String username = vm.getUsername().getValue();
            String password = vm.getPassword().getValue();

            if (!checkValues(username, password, getContext())) return;

            //TODO modificare inviare il nome della servlet String
            dao.getLoginRep().login(username, password, result -> {
                sd.setLogin(result);
                Log.i("Username in Login", username);
                if (result == null) {
                    Toast.makeText(getContext(), "Controllare connessione a internet", Toast.LENGTH_LONG).show();
                    return;
                }
                if (result.getLoggedIn()) {
                    Toast.makeText(getContext(), "Benvenuto " + username, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "Username e/o Password errati", Toast.LENGTH_LONG).show();
                }
            });

            //serve per recuperare il navController e muoversi tra i fragment
            NavHostFragment.findNavController(LoginFragment.this)
                    .navigate(R.id.action_login_to_menu_course);
        });

    }

    public void guestClickListener(Button guest) {
        //serve per recuperare il navController e muoversi tra i fragment
        guest.setOnClickListener(view1 ->
                NavHostFragment.findNavController(LoginFragment.this)
                        .navigate(R.id.action_login_to_menu_course));
    }


    //se c'è da inserire signup

     /*public void onSignupButtonClicked(View view) {
        String username = vm.getUsername().getValue();
        String password = vm.getPassword().getValue();

        if(!checkValues(username, password, this)) return;

        dao.getLoginData().signup(username, password, result -> {
            if (result != null) {
                switch (result) {
                    case 0: // Success
                        model.setLoginData(new Login(true, username, false));
                        Toast.makeText(this, getString(R.string.success_msg_login, username), Toast.LENGTH_LONG).show();
                        finish();
                        break;
                    case 2: // Lo username è già in uso da un altro utente
                        Toast.makeText(this, getString(R.string.error_msg_username_not_unique), Toast.LENGTH_LONG).show();
                        break;
                    default: // Unknown error
                        Toast.makeText(this, getString(R.string.error_msg_generic), Toast.LENGTH_LONG).show();
                        break;
                }
            } else {
                // Problemi di connessione
                Toast.makeText(this, getString(R.string.error_msg_internet_offline), Toast.LENGTH_LONG).show();
            }
        });
    }*/

    //controllo che username e password non siano vuoti
    public boolean checkValues(String username, String password, Context context) {
        if (username.isEmpty()) {
            Toast.makeText(context, "Lo username non può essere vuoto", Toast.LENGTH_LONG).show();
            return false;
        } else if (password.isEmpty()) {
            Toast.makeText(context, "La password non può essere vuota", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
}