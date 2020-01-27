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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.aucegypt.learningcentershub.Network.APIcall.url;


public class add_course_frag extends Fragment implements View.OnClickListener {
    RecyclerView listView;
    rvadapter2 rv;
    String id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.add_course, container, false);
        listView = view.findViewById(R.id.add_course_info);
        String [] data = getResources().getStringArray(R.array.course_info);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(layoutManager);
        rv =new rvadapter2(getContext(), data);
        listView.setAdapter(rv);
        Button add = view.findViewById(R.id.buttonadd);
        add.setText("Add Course");
        add.setOnClickListener(this);
        if (getArguments() != null) {
            Bundle b = getArguments();
            id = b.getString("LCID");
        }
        return view;

    }

    @Override
    public void onClick(View view) {
        //add course to db
        //perform checks
        rv.Network_add_crse(id);
        CharSequence text = "Course Added";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(getContext(), text, duration);
        toast.show();
    }

}

class rvadapter2 extends RecyclerView.Adapter<rvadapter2.ViewHolder3> implements View.OnClickListener{
    String[] rows;
    Context mContext;
    ArrayAdapter<String> dataAdapter;
    Calendar myCalendar, myCalendar2;
    DatePickerDialog DPD,DPD2;
    View view, view2;
    String name, category, instructorFname, instructorBio,instructorLname, std, end,sun,mon,tue,wed,thu,fri,sat,sun1,mon1,tue1,wed1,thu1,fri1,sat1;
    boolean sunday = false, monday = false, tuesday = false,wednesday =false, thursay =false, friday =false ,saturday = false;
    Double fees, regfees;
     DatePickerDialog.OnDateSetListener date,date2;

     public rvadapter2(Context context, String[] Names) {
        this.rows = Names;
        this.mContext = context;
         setHasStableIds(true);
         final String[][] CATS = {{null}};// = new String[1][1];// = mContext.getResources().getStringArray(R.array.category_4);
         String url2 = url+"myroute/getCategories";
         OkHttpClient client = new OkHttpClient();
         final MediaType JSON = MediaType.get("application/json; charset=utf-8");
         final Request request = new Request.Builder()
                 .url(url2)
                 .build();
         client.newCall(request).enqueue(new Callback() {
             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                 if (response.isSuccessful()){
                     final String myResponse = response.body().string();
                     JSONArray myResponseReader;
                     if (myResponse != "") {
                         try {
                             myResponseReader = new JSONArray(myResponse);
                             ArrayList<String> cats = new ArrayList<>();
                             for (int i = 0; i<myResponseReader.length();i++) {
                                 JSONObject jsonObject = myResponseReader.getJSONObject(i);
                                cats.add(jsonObject.getString("CatName"));

                             }
                             CATS[0] = cats.toArray(new String[cats.size()]);
                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }

             }

             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {

             }});

         dataAdapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, CATS[0]);
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
        if (viewType == 5)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checkbox2, parent, false);
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
                    category = (String) adapterView.getItemAtPosition(position);
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
            holder.editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    switch (position)
                    {
                        case 3:
                            fees = new Double(charSequence.toString());
                            break;
                        case 4:
                            regfees = new Double(charSequence.toString());
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
        else if (position == 8 )
        {
            holder.text1.setText(rows[position]);
            view = holder.editText;
            holder.editText.setInputType(InputType.TYPE_NULL);
            holder.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DPD.show();
                    std = holder.editText.getText().toString();
                }

            });

        }
        else if (position == 9)
        {
            holder.text1.setText(rows[position]);
            view2 = holder.editText;
            holder.editText.setInputType(InputType.TYPE_NULL);
            holder.editText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DPD2.show();
                    std = holder.editText.getText().toString();

                }
            });
        }
        else if (position == 10)
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
                            sun = hourOfDay + ":" + minutes;
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
                            mon = hourOfDay + ":" + minutes;
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
                            tue = hourOfDay + ":" + minutes;
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
                            wed = hourOfDay + ":" + minutes;
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
                            thu = hourOfDay + ":" + minutes;
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
                            fri = hourOfDay + ":" + minutes;
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
                            sat = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.sun1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sunday = ! sunday;
                }
            });
            holder.mon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monday = ! monday;
                }
            });
            holder.tue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tuesday = ! tuesday;
                }
            });
            holder.wed1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    wednesday = ! wednesday;
                }
            });
            holder.thu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    thursay = ! thursay;
                }
            });
            holder.fri1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    friday = ! friday;
                }
            });
            holder.sat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saturday = ! saturday;
                }
            });
            holder.cbsun1.setInputType(InputType.TYPE_NULL);
            holder.cbsun1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbsun1.setText(hourOfDay + ":" + minutes);
                            sun1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbmon1.setInputType(InputType.TYPE_NULL);
            holder.cbmon1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbmon1.setText(hourOfDay + ":" + minutes);
                            mon1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbtue1.setInputType(InputType.TYPE_NULL);
            holder.cbtue1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbtue1.setText(hourOfDay + ":" + minutes);
                            tue1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbwed1.setInputType(InputType.TYPE_NULL);
            holder.cbwed1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbwed1.setText(hourOfDay + ":" + minutes);
                            wed1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbthu1.setInputType(InputType.TYPE_NULL);
            holder.cbthu1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbthu1.setText(hourOfDay + ":" + minutes);
                            thu1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbsat1.setInputType(InputType.TYPE_NULL);
            holder.cbsat1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbsat1.setText(hourOfDay + ":" + minutes);
                            sat1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
            holder.cbfri1.setInputType(InputType.TYPE_NULL);
            holder.cbfri1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes ) {
                            holder.cbfri1.setText(hourOfDay + ":" + minutes);
                            fri1 = hourOfDay + ":" + minutes;
                        }
                    }, myCalendar.get(Calendar.HOUR_OF_DAY), myCalendar.get(Calendar.MINUTE),true );
                    timePickerDialog.show();
                }
            });
        }
        else if (position == 13)
        {
            holder.text6.setText(rows[position]);

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
                            instructorFname = charSequence.toString();
                            break;
                        case 6:
                            instructorLname = charSequence.toString();
                            break;
                        case 7:
                            instructorBio = charSequence.toString();
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
        if (position == 10)
            return 3;
        if (position == 13)
            return 5;
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
        if (view.getId() == R.id.row_select2)
            ((Admin_home)mContext).chooseVideo();

    }
    void setDate(View view)
    {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        ((EditText)view).setText(sdf.format(myCalendar.getTime()));
    }


    public class ViewHolder3 extends RecyclerView.ViewHolder{

        TextView text1, text2, text3, text4,text5, text6;
        Spinner spinner;
        ImageButton imageButton, imageButton2;
        VideoView videoview;
        ImageView imageView;
        EditText editText,cbsun,cbmon,cbtue,cbwed,cbthu,cbfri,cbsat,cbsun1,cbmon1,cbtue1,cbwed1,cbthu1,cbfri1,cbsat1;
        CheckBox sun1,mon1,tue1,wed1,thu1,fri1,sat1 ;

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
            cbsun1 = itemView.findViewById(R.id.sunTimeEnd);
            cbmon1 = itemView.findViewById(R.id.monTimeEnd);
            cbtue1 = itemView.findViewById(R.id.tueTimeEnd);
            cbwed1 = itemView.findViewById(R.id.wedTimeEnd);
            cbthu1 = itemView.findViewById(R.id.thuTimeEnd);
            cbfri1 = itemView.findViewById(R.id.friTimeEnd);
            cbsat1 = itemView.findViewById(R.id.satTimeEnd);
            videoview = itemView.findViewById(R.id.row_edit4);
            text5 = itemView.findViewById(R.id.row_text5);
            text6 = itemView.findViewById(R.id.row_text6);
            imageButton2 = itemView.findViewById(R.id.row_select2);
            sun1 = itemView.findViewById(R.id.checkBox1);
            mon1 = itemView.findViewById(R.id.checkBox2);
            tue1 = itemView.findViewById(R.id.checkBox3);
            wed1 = itemView.findViewById(R.id.checkBox4);
            thu1 = itemView.findViewById(R.id.checkBox5);
            fri1 = itemView.findViewById(R.id.checkBox6);
            sat1 = itemView.findViewById(R.id.checkBox7);

        }}
     void Network_add_crse(String id){
         String url2 = url+"myroute/AddCourse";
         OkHttpClient client = new OkHttpClient();
         String dt = "";
         if (sunday)
         {
             dt +="\"sun\":[\""+sun+"\",\""+sun1+"\"]";
         }
         if (monday)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"mon\":[\""+mon+"\",\""+mon1+"\"]";
         }
         if (tuesday)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"tue\":[\""+tue+"\",\""+tue1+"\"]";
         }
         if (wednesday)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"wed\":[\""+wed+"\",\""+wed1+"\"]";
         }
         if (thursay)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"thu\":[\""+thu+"\",\""+thu1+"\"]";
         }
         if (friday)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"fri\":[\""+sun+"\",\""+fri1+"\"]";
         }
         if (saturday)
         {
             if (dt != "")
                 dt+=",";
             dt +="\"sat\":[\""+sat+"\",\""+sat1+"\"]";
         }
         final MediaType JSON = MediaType.get("application/json; charset=utf-8");
         String json = "{\"name\":\""+name+"\", \"price\":"+fees+", \"regfees\": "+regfees
                 +", \"std\":\""+std+"\", \"end\": \""+end
                 +"\",\"id\":"+id+", \"cat\":\""+category+"\", \"insFname\":\""+instructorFname
                 +"\", \"insLname\": \""+instructorLname+"\", \"insBio\":\""+instructorBio+"\"";
         if (dt =="")
             json +="}";
         else
             json+=","+dt+"{";
         //instructor - schedule
         final RequestBody body = RequestBody.create(json,JSON);
         final Request request = new Request.Builder()
                 .url(url2)
                 .post(body)
                 .build();


         client.newCall(request).enqueue(new Callback() {

             @Override
             public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                 if (response.isSuccessful()){
                     final String myResponse = response.body().string();
                     JSONObject myResponseReader;
                     if (myResponse != "") {
                         try {
                             myResponseReader = new JSONObject(String.valueOf(new JSONArray(myResponse).getJSONObject(0)));

                         } catch (JSONException e) {
                             e.printStackTrace();
                         }
                     }
                 }

             }

             @Override
             public void onFailure(@NotNull Call call, @NotNull IOException e) {

             }});


     }
}
