package hack4fi.musefun;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.nfc.FormatException;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = NfcAdapter.getDefaultAdapter(this);
// Create a generic PendingIntent that will be deliver to this activity. The NFC stack
        // will fill in the intent with the details of the discovered tag before delivering to
        // this activity.
        mPendingIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        mFilters = new IntentFilter[] {
                //    td
                //   ndef
        };

        mTechLists = new String[][] { new String[] {Ndef.class.getName() }}; // new String[] { NfcF.class.getName(),Ndef.class.getName() } , NfcA.class.getName()


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void nextPage() {
        final int pages = mViewPager.getAdapter().getCount();
        final int nextPage = mViewPager.getCurrentItem() + 1;
        if(nextPage < pages) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
          //  TextView textView = (TextView) rootView.findViewById(R.id.section_label);
          //  textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            ImageView imageView=(ImageView)rootView.findViewById(R.id.imageView);
            //imageView.setImageBitmap(BitmapFactory.decodeFile("/data/data/com.myapp/files/someimage.jpg"));

            Drawable myIcon = getResources().getDrawable( R.drawable.screen1);
            imageView.setImageDrawable(myIcon);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final Fragment introFragment = IntroFragment.newInstance();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position == 0) {
                return introFragment;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
            Log.i("Foreground dispatch", "onResume" + mAdapter.isEnabled());
        }
    }
    @Override
    public void onNewIntent(Intent intent) {
        Log.i("Foreground dispatch", "Discovered tag with intent: " + intent);

        Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Log.i("Foreground dispatch", "Discovered tag with intent: " + tagFromIntent.getTechList().length);
        Log.i("Foreground dispatch", "Discovered tag with intent: " + tagFromIntent.getTechList()[0]);
        Log.i("Foreground dispatch", "Discovered tag with intent: " + tagFromIntent.getTechList()[1]);
        Log.i("Foreground dispatch", "Discovered tag with intent: " + tagFromIntent.getTechList()[2]);

        Ndef ndef= Ndef.get(tagFromIntent);
        try {
            ndef.connect();
            Log.i("Foreground dispatch", "Discovered tag with intent: " + ndef.canMakeReadOnly());

            Log.i("Foreground dispatch", "Discovered tag with intent: " + new String(ndef.getNdefMessage().getRecords()[0].getPayload()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        //mText.setText("Discovered tag " + ++mCount + " with intent: " + intent);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mAdapter != null) mAdapter.disableForegroundDispatch(this);
    }
}
