package com.example.dcleto.restaurantrecomendationapp.authentication.registration;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcleto.restaurantrecomendationapp.Constants;
import com.example.dcleto.restaurantrecomendationapp.R;
import com.example.dcleto.restaurantrecomendationapp.SessionManager;
import com.example.dcleto.restaurantrecomendationapp.home.HomeActivity;
import com.example.dcleto.restaurantrecomendationapp.model.User;
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
public class RegistrationActivityFragment extends Fragment {

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    TextView etFirstName, etLastName,etEmail, etPassword,etPasswordRepeat;
    AuthService service;
    ImageView pickPhoto;
    private static int SELECT_PHOTO = 100;

    public RegistrationActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView= inflater.inflate(R.layout.fragment_registration, container, false);
        etFirstName = rootView.findViewById(R.id.et_first_name);
        etLastName = rootView.findViewById(R.id.et_last_name);
        etEmail = rootView.findViewById(R.id.et_email);
        etPassword = rootView.findViewById(R.id.et_password);
        etPasswordRepeat = rootView.findViewById(R.id.et_password_repeat);
        pickPhoto = rootView.findViewById(R.id.iv_avatar);
        pickPhoto.setOnClickListener(v->{
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PHOTO);
        });
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.END_POINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(AuthService.class);
        rootView.findViewById(R.id.btn_signup).setOnClickListener(v->{
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String passwordRepeat = etPasswordRepeat.getText().toString();

            User user = new User(firstName,lastName,password,passwordRepeat,email);
            service.signUp(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(u->{
                        Toast.makeText(getActivity(),"Registro Exitoso",Toast.LENGTH_SHORT).show();
                        TokenUtil.setToken(getActivity(),u.getAccess_token());
                        SessionManager.getInstance(getActivity()).setUser(u.getUser());
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                    },e->{
                        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();

                    });

        });
        return  rootView;
    }
}
