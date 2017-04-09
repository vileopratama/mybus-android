package id.co.vileo.my_bus.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import id.co.vileo.my_bus.R;

public class HomeFragment extends Fragment {
    FragmentActivity activity;
    SliderLayout sliderShow;
    DefaultSliderView defaultSliderView;
    EditText editTextDateDeparture;
    private DatePickerDialog dateDepartureDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        findViewById(view);
        return view;
    }

    private void findViewById(View view) {
        sliderShow = (SliderLayout) view.findViewById(R.id.slider);

        HashMap<String,String> url_maps = new HashMap<String, String>();
        url_maps.put("Hannibal", "https://da8hvrloj7e7d.cloudfront.net/imageResource/2017/04/06/1491472578824-c2a2536b10fa1fbaeadfe683b2b774d8.jpeg");
        url_maps.put("Big Bang Theory", "https://da8hvrloj7e7d.cloudfront.net/imageResource/2017/03/24/1490358222239-0d536bb026172a390fb0e1b834c12714.jpeg");
        url_maps.put("House of Cards", "https://da8hvrloj7e7d.cloudfront.net/imageResource/2016/05/19/1463657700722-e8ebfa794d4aeac7e4d12f5a32dbb37a.png");
        url_maps.put("Game of Thrones", "https://da8hvrloj7e7d.cloudfront.net/imageResource/2017/03/24/1490358222239-0d536bb026172a390fb0e1b834c12714.jpeg");

        for(String name : url_maps.keySet()){
            defaultSliderView = new DefaultSliderView(activity);
            // initialize a SliderLayout
            defaultSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(activity,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
                        }
                    });

            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra",name);

            sliderShow.addSlider(defaultSliderView);
        }

        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setDuration(4000);
        sliderShow.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("Slider Demo", "Page Changed: " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        editTextDateDeparture = (EditText) view.findViewById(R.id.date_departure);
        editTextDateDeparture.setInputType(InputType.TYPE_NULL);
        editTextDateDeparture.requestFocus();
        editTextDateDeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateDepartureDialog.show();
            }
        });

        Calendar newCalendar = Calendar.getInstance();
        dateDepartureDialog = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDateDeparture.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


    }

    @Override
    public void onStop() {
        sliderShow.stopAutoCycle();
        super.onStop();
    }



}
