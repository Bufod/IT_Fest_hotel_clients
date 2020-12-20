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

public class ChangeStudentFragment extends Fragment {

    AppCompatButton btn_save;

    EditText et_name;
    EditText et_group;
    EditText et_score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_change_student, container, false);

        Client client = (Client) (getArguments().getSerializable(MainActivity.MSG_NAME));
        /*StudentDbHelper dbHelper = new StudentDbHelper(getContext());
        dbHelper.changeStudent(student);


*/
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
        ((MainActivity)getActivity()).update();
    }

}