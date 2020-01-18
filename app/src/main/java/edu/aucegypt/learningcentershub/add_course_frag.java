package edu.aucegypt.learningcentershub;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class add_course_frag extends Fragment {
    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_course, container, false);
        listView = view.findViewById(R.id.add_course_info);
        String [] data = getResources().getStringArray(R.array.course_info);
        ArrayList<String> Data = new ArrayList<String>();
        for (int i = 0 ; i<data.length;i++) {
            Data.add(data[i]);
        }
        listView.setAdapter(new rowAdapter(getActivity(), Data));

        return view;

    }
}

class rowAdapter extends ArrayAdapter<String> {
    public rowAdapter(Context context, ArrayList<String> row ){
        super(context,0,row);

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String row = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.row_text);
        textView.setText(row);
        return convertView;
    }
}