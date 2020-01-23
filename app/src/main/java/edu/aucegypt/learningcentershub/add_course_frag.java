package edu.aucegypt.learningcentershub;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static androidx.core.app.ActivityCompat.startActivityForResult;

public class add_course_frag extends Fragment {
    RecyclerView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_course, container, false);
        listView = view.findViewById(R.id.add_course_info);
        String [] data = getResources().getStringArray(R.array.course_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter2(getContext(), data));
        return view;

    }
}

class rvadapter2 extends RecyclerView.Adapter<rvadapter2.ViewHolder3> implements View.OnClickListener {
    String[] rows;
    Context mContext;
    ArrayAdapter<String> dataAdapter;
    Calendar myCalendar, myCalendar2;
    DatePickerDialog DPD,DPD2;
    View view, view2;
     DatePickerDialog.OnDateSetListener date,date2;
    public rvadapter2(Context context, String[] Names) {
        this.rows = Names;
        this.mContext = context;
        String [] categories = mContext.getResources().getStringArray(R.array.category_4);
        dataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view1, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(view);

            }};
        DPD = new DatePickerDialog(mContext, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        myCalendar2 = Calendar.getInstance();
        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view1, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setDate(view2);

            }};
        DPD2 = new DatePickerDialog(mContext, date2, myCalendar2.get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH), myCalendar2.get(Calendar.DAY_OF_MONTH));

    }

    @NonNull
    @Override
    public ViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_dropdown, parent, false);
            return new ViewHolder3(view);
        }
        if (viewType == 2)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_image, parent, false);
            return new ViewHolder3(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder3 holder, int position) {
        if(position == 1)
        {
            holder.text2.setText(rows[position]);
            holder.spinner.setAdapter(dataAdapter);
        }
        else if (position == 2)
        {
            holder.imageButton.setOnClickListener(this);
            holder.text3.setText(rows[position]);
            holder.imageView.setImageResource(R.drawable.mainlogo);
        }
        else if (position == 3 || position==4)
        {
            holder.editText.setRawInputType(Configuration.KEYBOARD_12KEY);
            holder.text1.setText(rows[position]);

        }
        else if (position == 6 )
        {
            holder.text1.setText(rows[position]);
            view = holder.editText;
            holder.editText.setInputType(InputType.TYPE_NULL);
            holder.editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    DPD.show();
                    return true;
                }
            });

        }
        else if (position == 7)
        {
            holder.text1.setText(rows[position]);
            view2 = holder.editText;
            holder.editText.setInputType(InputType.TYPE_NULL);
            holder.editText.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    DPD2.show();
                    return true;
                }
            });
        }
        else {
            holder.text1.setText(rows[position]);
        }
    }
    public int getItemViewType(int position) {
        // Change layout every other position
        if (position == 1)
            return 1;
        if(position == 2)
            return 2;
        if (position == 6)
            return 3;
        return 0;
    }
    @Override
    public int getItemCount() {
        return rows.length;
    }
    @Override
    public void onClick(View view)
    {
        if (view.getId() == R.id.row_select)
            ((Admin_home)mContext).chooseImage();

    }
    void setDate(View view)
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ((EditText)view).setText(sdf.format(myCalendar.getTime()));
    }


    public class ViewHolder3 extends RecyclerView.ViewHolder{

        TextView text1, text2, text3;
        Spinner spinner;
        ImageButton imageButton;
        ImageView imageView;
        EditText editText;

        public ViewHolder3(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.row_text);
            text2 = itemView.findViewById(R.id.row_text2);
            spinner = itemView.findViewById(R.id.row_edit2);
            imageButton = itemView.findViewById(R.id.row_select);
            text3 = itemView.findViewById(R.id.row_text3);
            imageView = itemView.findViewById(R.id.row_edit3);
            editText = itemView.findViewById(R.id.row_edit);

        }}
}
