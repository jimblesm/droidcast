package com.swick.droidcast;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridLayout.Spec;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        
        FeedParser parser = new FeedParser();
        parser.parseXml(this);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            
            GridLayout gridLayout = (GridLayout) rootView.findViewById(R.id.grid);
            
            createCoverView(gridLayout, 0, 0, R.drawable.cover_bugle, "1. The Bugle", "The Bugle");
            createCoverView(gridLayout, 0, 1, R.drawable.cover_deathsexandmoney, "2. Death, Sex and Money", "By WNYC, New York Public Radio");
            createCoverView(gridLayout, 1, 0, R.drawable.cover_marketplace, "3. APM: Marketplace", "American Public Media");
            createCoverView(gridLayout, 1, 1, R.drawable.cover_thenerdist, "4. The Nerdist", "Chris Hardwick");
            createCoverView(gridLayout, 2, 0, R.drawable.cover_thisamericanlife, "5. This American Life", "Chicago Public Media");
            createCoverView(gridLayout, 2, 1, R.drawable.cover_freshair, "6. NPR Programs: Fresh Air Podcast", "NPR");
            
            return rootView;
        }

		private void createCoverView(GridLayout gridLayout, int row, int column, int cover_resource, String podcastName, String authorName) {
			View v = getActivity().getLayoutInflater().inflate(R.layout.podcast_cover, null);
            
            GridLayout.LayoutParams viewLayoutParams = new GridLayout.LayoutParams();
            viewLayoutParams.topMargin = 5;
            viewLayoutParams.bottomMargin = 5;
            viewLayoutParams.leftMargin = 5;
            viewLayoutParams.rightMargin = 5;
            Spec row1 = GridLayout.spec(row);
            Spec column1 = GridLayout.spec(column);
            viewLayoutParams.rowSpec = row1;
            viewLayoutParams.columnSpec = column1;
            
            ImageView cover = (ImageView) v.findViewById(R.id.cover);
            cover.setImageDrawable(getActivity().getResources().getDrawable(cover_resource));
            v.setLayoutParams(viewLayoutParams);
            
            TextView podcastTextBox = (TextView) v.findViewById(R.id.podcast_name);
            TextView authorTextBox = (TextView) v.findViewById(R.id.author_name);
            
            podcastTextBox.setText(podcastName);
            Typeface proxima = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Proxima Nova Reg.otf");
            podcastTextBox.setTypeface(proxima);
            authorTextBox.setText(authorName);
            authorTextBox.setTypeface(proxima);
            
            gridLayout.addView(v);
		}
    }

}
