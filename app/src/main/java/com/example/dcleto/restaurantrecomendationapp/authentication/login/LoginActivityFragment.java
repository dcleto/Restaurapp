package com.example.dcleto.restaurantrecomendationapp.authentication.login;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.authentication.registration.RegistrationActivity;
import com.example.dcleto.restaurantrecomendationapp.home.HomeActivity;
import com.example.dcleto.restaurantrecomendationapp.services.AuthService;
import com.example.dcleto.restaurantrecomendationapp.utils.TokenUtil;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginActivityFragment extends Fragment {

    Button btn_login,btn_register;
    EditText et_username;
    EditText et_password;

    AuthService service;
    public LoginActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.fragment_login, container, false);
        String token = TokenUtil.getToken(getActivity());
        if(token != null && token!=""){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            return rootView;
         }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.END_POINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AuthService.class);

        btn_login = (Button) rootView.findViewById(R.id.btn_login);
        btn_register = (Button) rootView.findViewById(R.id.btn_register);
        et_username =(EditText) rootView.findViewById(R.id.et_username);
        et_password =(EditText) rootView.findViewById(R.id.et_password);


        et_username.setText("pietro2@gmail.com");
        et_password.setText("Segur@2017");
        btn_register.setOnClickListener(v->{
            Intent intent = new Intent(getActivity(), RegistrationActivity.class);
            startActivity(intent);
        });
        btn_login.setOnClickListener(v-> logIn());

        return  rootView;
    }

    private void logIn(){

        String username = et_username.getText().toString();
        String password = et_password.getText().toString();
                service.Get(username,password,"password")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(v-> {
                    String token = v.getAccess_token();
                    TokenUtil.setToken(getActivity(),token);
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    startActivity(i);
                    getActivity().finish();
                },e->{
                    Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_LONG).show();
                });
    }
}
