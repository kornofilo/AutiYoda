package com.rialvik.autiyoda;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
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

public class IntroductionActivity extends AppCompatActivity {

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);


        /*
      The {@link android.support.v4.view.PagerAdapter} that will provide
      fragments for each of the sections. We use a
      {@link FragmentPagerAdapter} derivative, which will keep every
      loaded fragment in memory. If this becomes too memory intensive, it
      may be best to switch to a
      {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
        SectionsPagerAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
            View rootView = inflater.inflate(R.layout.fragment_introduction, container, false);
            TextView titleTextView = rootView.findViewById(R.id.TextViewIntroductionTitle), descriptionTextView = rootView.findViewById(R.id.textView_Description);
            Button skipButton = rootView.findViewById(R.id.ButtonSkip), nextButton = rootView.findViewById(R.id.Button_Next);
            ImageView imageIntroduction = rootView.findViewById(R.id.imageViewIntroduction);
            int next = 0;


            switch (getArguments().getInt(ARG_SECTION_NUMBER)){
                case 1:
                    rootView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                    imageIntroduction.setImageResource(R.drawable.ic_happy_black_24dp);
                    titleTextView.setText(R.string.Welcome);
                    descriptionTextView.setText(R.string.welcome_description);
                    next = 1;
                break;

                case 2:
                    rootView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    imageIntroduction.setImageResource(R.drawable.ic_image_black_24dp);
                    titleTextView.setText(R.string.pictograms_introduction_title);
                    descriptionTextView.setText(R.string.pictograms_introduction_description);
                    next = 2;
                    break;
                case 3:
                    rootView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                    imageIntroduction.setImageResource(R.drawable.ic_vocabulary_black_24dp);
                    titleTextView.setText(R.string.comunication_introduction_title);
                    descriptionTextView.setText(R.string.comunication_introduction_description);
                    nextButton.setText(R.string.finish);
                    next = 3;
                break;
            }//Fin Switch-Case.

            skipButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  finishIntroduction();
                }
            });

            final int finalNext = next;
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mViewPager.setCurrentItem(finalNext, true);

                    if(finalNext == 3){
                        finishIntroduction();
                    }
                }
            });


            return rootView;
        }

        private void finishIntroduction(){
             SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putBoolean("first_time", false);
                    editor.apply();

            startActivity(new Intent(getActivity(), LoginActivity.class));
            getActivity().finish();
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
            // Show 3 total pages.
            return 3;
        }
    }
}
