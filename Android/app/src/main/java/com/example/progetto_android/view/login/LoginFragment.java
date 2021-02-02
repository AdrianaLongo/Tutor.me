package com.example.progetto_android.view.login;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.progetto_android.R;
import com.example.progetto_android.conndata.Connector;
import com.example.progetto_android.databinding.FragmentLoginBinding;
import com.example.progetto_android.globals.GlobalValue;
import com.example.progetto_android.globals.ShareDataViewModel;

public class LoginFragment extends Fragment {

    private ShareDataViewModel sd;
    private LoginViewModel vm;
    private Connector connector;
    private FragmentLoginBinding loginBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //istanzio il ViewModel che tiene in memoria il login dell'utente
        sd = ShareDataViewModel.getInstance();
        //classe usata per collegarsi alle servlet
        connector = Connector.getInstance(getContext());
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //viewModel che si occupa di gestire il collegamente tra layout e fragment
        vm = new ViewModelProvider(this).get(LoginViewModel.class);
        //permette di comunicare con le view in modo più semplice
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View view = loginBinding.getRoot();
        loginBinding.setBd(vm);
        loginBinding.setLifecycleOwner(this);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button login = view.findViewById(R.id.login);
        Button guest = view.findViewById(R.id.guest);
        //listener del login
        login.setOnClickListener(view1 -> {
            //nascondo la tastiera una volta che l'utente ha finito di usarla
            hideKeyboardFrom(requireContext(), view);

            //leggo username e password dalla view per mandarla alla servlet
            String username = vm.getUsername().getValue();
            String password = vm.getPassword().getValue();

            //controllo se i campi non sono vuoti
            if (checkValues(username, password, getContext())) {
                //mi collego alla servlet, mandando i dati necessari
                connector.getLoginRep().login(username, password, result -> {
                    try {
                        //controllo se l'utente è presente nel db
                        if (result.getObject() != null) {
                            //inserisco risultato nella ViewModel
                            sd.setLoggedIn(true);

                            //messaggio di benvenuto
                            Toast.makeText(getContext(), "Benvenuto " + username, Toast.LENGTH_LONG).show();

                            //metto in una variabile globali l'id della sessione
                            GlobalValue.setjSessionId(result.getObject());

                            //serve per recuperare il navController e muoversi tra i fragment
                            NavHostFragment.findNavController(LoginFragment.this)
                                    .navigate(R.id.action_login_to_menu_course);
                        } else {
                            //messaggio di errore, usernamen o password mancanti
                            Toast.makeText(getContext(), "Username e/o Password errati", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        //errore da parte della servlet
                        Toast.makeText(getActivity(), "Login non riuscito: controllare dati e connessione a internet", Toast.LENGTH_LONG).show();
                        //inserisco risultato nella ViewModel
                        sd.setLoggedIn(false);
                        //azzero i campi del login così che l'utente possa riscriverli
                        loginBinding.username.setText("");
                        loginBinding.password.setText("");
                    }
                });
            }
        });
        //listener per l'ospite
        guest.setOnClickListener(view1 -> NavHostFragment.findNavController(LoginFragment.this)
                .navigate(R.id.action_login_to_menu_course));
    }

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

    //metodo che serve per far sparire la tastiera
    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}