package com.example.it_fest_student_raiting.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.it_fest_student_raiting.MainActivity;
import com.example.it_fest_student_raiting.R;
import com.example.it_fest_student_raiting.db.ClientDbHelper;
import com.example.it_fest_student_raiting.model.Client;

import java.time.LocalDate;

public class AddClientFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_add_client, container, false);



        AppCompatButton btn_add = (AppCompatButton) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientDbHelper dbHelper = new ClientDbHelper( getContext() );

                EditText etLastName = getView().findViewById(R.id.et_last_name);
                EditText etFirstName = getView().findViewById(R.id.et_first_name);
                EditText etArrivalDate = getView().findViewById(R.id.et_arrival_date);
                EditText etAmountOfDays = getView().findViewById(R.id.et_amount_of_days);
                Client testClient = new Client(
                        0,
                        etLastName.getText().toString(),
                        etFirstName.getText().toString(),
                        LocalDate.parse(etArrivalDate.getText().toString()),
                        Integer.parseInt(etAmountOfDays.getText().toString())
                );
                dbHelper.addStudent(testClient);

                getActivity().getSupportFragmentManager().beginTransaction().remove(AddClientFragment.this).commit();

                dbHelper.close();
            }
        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity)getActivity()).update();
    }
}