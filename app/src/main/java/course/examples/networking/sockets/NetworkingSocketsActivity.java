package course.examples.networking.sockets;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class NetworkingSocketsActivity extends Activity implements RetainedFragment.OnFragmentInteractionListener{
    private TextView mTextView;
    private RetainedFragment mRetainedFragment;
    private final static String MTEXTVIEW_TEXT_KEY = "MTEXTVIEW_TEXT_KEY";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTextView = findViewById(R.id.textView);

        if (null != savedInstanceState) {
            mRetainedFragment = (RetainedFragment) getFragmentManager()
                    .findFragmentByTag(RetainedFragment.TAG);
            mTextView.setText(savedInstanceState.getCharSequence(MTEXTVIEW_TEXT_KEY));
        } else {
            ActivityGet.setActivity(this);
            mRetainedFragment = new RetainedFragment();
            getFragmentManager().beginTransaction()
                    .add(mRetainedFragment, RetainedFragment.TAG)
                    .commit();
        }
    }

    public void onClick(@SuppressWarnings("unused") View v) throws IOException {
    }

    public void Bind_Device(@SuppressWarnings("unused") View v)
    {
        mRetainedFragment.bind_pressed();
    }

    @Override
    public void onDownloadFinished(final String result) {
        this.runOnUiThread(() -> { mTextView.setText(result);});
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence(MTEXTVIEW_TEXT_KEY,mTextView.getText());
    }
}