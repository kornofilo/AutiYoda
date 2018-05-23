package com.rialvik.autiyoda;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class PictogramsActivity extends AppCompatActivity {

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
    private static Habilidad habilidad = new Habilidad();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pictograms);
        feedLayout();
        System.out.println("1. " +habilidad.getName());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        System.out.println("2. " +habilidad.getName());

    }

    public void feedLayout(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(extras != null){
            habilidad.setName(extras.getString("nombre"));
            habilidad.setCantidadPasos(extras.getInt("cantidad_pasos"));
            habilidad.setPasos(extras.getStringArray("pasos"));
            habilidad.setImagenesPasos(extras.getIntArray("img_pasos"));
        }

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
            View rootView = inflater.inflate(R.layout.fragment_pictograms, container, false);

            for (int i=1; i <= getArguments().getInt(ARG_SECTION_NUMBER); i++){
                feedLayout(rootView,i-1);
            }
            /*switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    feedLayout(rootView,1);
                    break;
            }//Fin Switch-Case.*/

            return rootView;
        }

        public void feedLayout(View rootView, int position){
            String pasos[] = habilidad.getPasos();
            int img_pasos[] = habilidad.getImagenesPasos();
            TextView textViewHabilityName = rootView.findViewById(R.id.TextViewHabilityName), textViewStep = rootView.findViewById(R.id.TextViewStep);
            ImageView imageViewStep = rootView.findViewById(R.id.imageViewStep);
            textViewHabilityName.setText(habilidad.getName());
            textViewStep.setText(pasos[position]);
            imageViewStep.setImageResource(img_pasos[position]);

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
            return habilidad.getCantidadPasos();
        }
    }
}
