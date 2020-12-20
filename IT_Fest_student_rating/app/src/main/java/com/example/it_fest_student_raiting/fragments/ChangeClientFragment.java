package com.example.it_fest_student_raiting.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.it_fest_student_raiting.MainActivity;
import com.example.it_fest_student_raiting.R;
import com.example.it_fest_student_raiting.db.ClientDbHelper;
import com.example.it_fest_student_raiting.model.Client;

import java.time.LocalDate;

public class ChangeClientFragment extends Fragment {

    EditText etLastName,
            etFirstName,
            etArrivalDate,
            etAmountOfDays;

    CheckBox cbStatus;
    private boolean isEvicted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_client, container, false);

        etLastName = view.findViewById(R.id.et_last_name);
        etFirstName = view.findViewById(R.id.et_first_name);
        etArrivalDate = view.findViewById(R.id.et_arrival_date);
        etAmountOfDays = view.findViewById(R.id.et_amount_of_days);

        cbStatus = view.findViewById(R.id.cb_status);

        Client client = (Client) (getArguments().getSerializable(MainActivity.MSG_NAME));

        etLastName.setText(client.getLastName());
        etFirstName.setText(client.getFirstName());
        etArrivalDate.setText(client.getArrivalDate().toString());
        etAmountOfDays.setText(String.valueOf( client.getAmountOfDays() ));

        cbStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isEvicted = b;
            }
        });

        AppCompatButton btn_add = (AppCompatButton) view.findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClientDbHelper dbHelper = new ClientDbHelper(getContext());

                Client.Status status;
                if (isEvicted){
                    status = Client.Status.EVICTED;
                }else {
                    status = Client.Status.RESIDES;
                }

                LocalDate arrivalDate =  LocalDate.parse(etArrivalDate.getText().toString());
                Integer amountOfDays = Integer.parseInt(etAmountOfDays.getText().toString());
                LocalDate checkOutDate =  arrivalDate.plusDays(amountOfDays);

                Client testClient = new Client(
                        client.getId(),
                        etLastName.getText().toString(),
                        etFirstName.getText().toString(),
                        arrivalDate,
                        amountOfDays,
                        checkOutDate,
                        status
                );
                dbHelper.changeStudent(testClient);

                getActivity().getSupportFragmentManager().beginTransaction().remove(ChangeClientFragment.this).commit();

                dbHelper.close();
            }
        });

//        et_name = view.findViewById(R.id.et_name);
//        et_group = view.findViewById(R.id.et_group);
//        et_score = view.findViewById(R.id.et_score);
//
//        et_name.setText( client.getName() );
//        et_group.setText( client.getGr() );
//        et_score.setText( client.getScore().toString() );
//
//        btn_save = (AppCompatButton) view.findViewById(R.id.btn_save);
//        btn_save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ClientDbHelper dbHelper = new ClientDbHelper( getContext() );
//
//
//
//                Client testClient = new Client( client.getId(),
//                        et_name.getText().toString(),
//                        et_group.getText().toString(),
//                        Integer.valueOf( et_score.getText().toString() ));
//
//                dbHelper.changeStudent(testClient);
//
//                getActivity().getSupportFragmentManager().beginTransaction().remove(ChangeStudentFragment.this).commit();
//
//                dbHelper.close();
//            }
//        });

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).update();
    }

}