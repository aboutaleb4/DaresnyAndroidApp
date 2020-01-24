package edu.aucegypt.learningcentershub;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class add_course_frag extends Fragment implements View.OnClickListener {
    RecyclerView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_course, container, false);
        listView = view.findViewById(R.id.add_course_info);
        String [] data = getResources().getStringArray(R.array.course_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        listView.setAdapter(new rvadapter2(getContext(), data));
        Button add = view.findViewById(R.id.buttonadd);
        add.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        //add course to db
        //perform checks

    }

}

class rvadapter2 extends RecyclerView.Adapter<rvadapter2.ViewHolder3> implements View.OnClickListener{
    String[] rows;
    Context mContext;
    ArrayAdapter<String> dataAdapter;
    Calendar myCalendar, myCalendar2;
    DatePickerDialog DPD,DPD2;
    View view, view2;
    String name, category, instructor, std, end,sun,mon,tue,wed,thu,fri,sat;
    Double fees, regfees;
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
        if (viewType == 3)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checkbox, parent, false);
            return new ViewHolder3(view);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder3 holder, final int position) {
        if(position == 1)
        {
            holder.text2.setText(rows[position]);
            holder.spinner.setAdapter(dataAdapter);
            holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    category = view.toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
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
            holder.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DPD.show();

                }

            });

        }
        else if (position == 7)
        {
            holder.text1.setText(rows[position]);
            view2 = holder.editText;
            holder.editText.setInputType(InputType.TYPE_NULL);
            holder.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DPD2.show();

                }
            });
        }
        else if (position == 8)
        {
            holder.text4.setText(rows[position]);
            holder.cbsun.setInputType(InputType.TYPE_NULL);
            holder.cbsun.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbsun.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbmon.setInputType(InputType.TYPE_NULL);
            holder.cbmon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbmon.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbtue.setInputType(InputType.TYPE_NULL);
            holder.cbtue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbtue.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbwed.setInputType(InputType.TYPE_NULL);
            holder.cbwed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbwed.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbwed.setInputType(InputType.TYPE_NULL);
            holder.cbwed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbwed.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbthu.setInputType(InputType.TYPE_NULL);
            holder.cbthu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbthu.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbfri.setInputType(InputType.TYPE_NULL);
            holder.cbfri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbfri.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbsat.setInputType(InputType.TYPE_NULL);
            holder.cbsat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbsat.setText(hourOfDay + ":" + minutes);
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
        }
        else {
            holder.text1.setText(rows[position]);
            holder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    switch (position)
                    {
                        case 0:
                            name = charSequence.toString();
                            break;
                        case 5:
                            instructor = charSequence.toString();
                            break;
                            default:
                                break;
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }
    public int getItemViewType(int position) {
        // Change layout every other position
        if (position == 1)
            return 1;
        if(position == 2)
            return 2;
        if (position == 8)
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

        TextView text1, text2, text3, text4;
        Spinner spinner;
        ImageButton imageButton;
        ImageView imageView;
        EditText editText,cbsun,cbmon,cbtue,cbwed,cbthu,cbfri,cbsat;

        public ViewHolder3(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.row_text);
            text2 = itemView.findViewById(R.id.row_text2);
            spinner = itemView.findViewById(R.id.row_edit2);
            imageButton = itemView.findViewById(R.id.row_select);
            text3 = itemView.findViewById(R.id.row_text3);
            imageView = itemView.findViewById(R.id.row_edit3);
            editText = itemView.findViewById(R.id.row_edit);
            text4 = itemView.findViewById(R.id.row_text4);
            cbsun = itemView.findViewById(R.id.sunTime);
            cbmon = itemView.findViewById(R.id.monTime);
            cbtue = itemView.findViewById(R.id.tueTime);
            cbwed = itemView.findViewById(R.id.wedTime);
            cbthu = itemView.findViewById(R.id.thuTime);
            cbfri = itemView.findViewById(R.id.friTime);
            cbsat = itemView.findViewById(R.id.satTime);

        }}
}
