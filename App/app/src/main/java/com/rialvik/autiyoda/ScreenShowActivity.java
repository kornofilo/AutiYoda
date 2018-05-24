package com.rialvik.autiyoda;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class ScreenShowActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int MY_DATA_CHECK_CODE = 0;
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private static SectionElements sectionElements = new SectionElements();
    private static TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_screen_show);
        tts = new TextToSpeech(this, this);
        feedLayout();
        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }

    public void feedLayout(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            sectionElements.setTitle(extras.getString("name"));
            sectionElements.setNumElements(extras.getInt("num_elements"));
            sectionElements.setElements(extras.getStringArray("elements"));
            sectionElements.setImgElements(extras.getIntArray("img_elements"));
        }

    }


    @Override
    public void onInit(int status) {
        tts.setLanguage(new Locale(Locale.getDefault().getLanguage()));
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView= inflater.inflate(R.layout.fragment_screen_show, container, false);

            for (int i=1; i <= getArguments().getInt(ARG_SECTION_NUMBER); i++){
                feedLayout(rootView,i-1);
            }
            return rootView;
        }

        public void feedLayout(View rootView, final int position){
            final String pasos[] = sectionElements.getElements();
            int img_pasos[] = sectionElements.getImgElements();
            TextView textViewHabilityName = rootView.findViewById(R.id.TextViewHabilityName);
            Button ButtonStep = rootView.findViewById(R.id.ButtonStep);
            ImageView imageViewStep = rootView.findViewById(R.id.imageViewStep);
            textViewHabilityName.setText(sectionElements.getTitle());
            ButtonStep.setText(pasos[position]);

            imageViewStep.setImageResource(img_pasos[position]);
            imageViewStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tts.speak(pasos[position], TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });

            ButtonStep.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tts.speak(pasos[position], TextToSpeech.QUEUE_FLUSH, null, null);
                }
            });


        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return sectionElements.getNumElements();
        }
    }
}
