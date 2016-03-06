package nl.rwslinkman.awesomeexample;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import nl.rwslinkman.awesome.ButtonAwesome;
import nl.rwslinkman.awesome.DrawableAwesome;
import nl.rwslinkman.awesome.TextAwesome;

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "ExampleActivity";
    private ArrayList<AwesomeExample> mExamples;
    private AwesomeExample mNextExample;
    private TextAwesome mTextAwesome;
    private ImageView mDrawableView;
    private TextView mValueView;
    private ButtonAwesome mButtonAwesome;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // Find components in XML
        mDrawableView = (ImageView) findViewById(R.id.my_awesome_imageview);
        mTextAwesome = (TextAwesome) findViewById(R.id.my_awesome_text);
        mValueView = (TextView) findViewById(R.id.example_value);
        mButtonAwesome = (ButtonAwesome) findViewById(R.id.my_awesome_button);
        mButtonAwesome.setOnClickListener(this);

        TextView githubLink = (TextView) findViewById(R.id.example_github_link);
        githubLink.setPaintFlags(githubLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        githubLink.setOnClickListener(this);

        // Gather all example icons
        mExamples = getAllIcons();

        AwesomeExample firstExample = getDifferentExample(null);
        mNextExample  = getDifferentExample(firstExample);

        // Put values
        mTextAwesome.setText(firstExample.getAwesomeRes());
        DrawableAwesome myDrawable = buildAwesomeDrawable(firstExample);
        mDrawableView.setImageDrawable(myDrawable);

        // Button with next value
        String valueText = getValueText(firstExample);
        mValueView.setText(valueText);
        mButtonAwesome.setText(mNextExample.getAwesomeRes());
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() == R.id.my_awesome_button)
        {
            // TODO: Renew icon
            // Set button example icon to other views
            mTextAwesome.setText(mNextExample.getAwesomeRes());
            DrawableAwesome myDrawable = buildAwesomeDrawable(mNextExample);
            mDrawableView.setImageDrawable(myDrawable);
            String valueText = getValueText(mNextExample);
            mValueView.setText(valueText);

            // Determine new "next example" and put on button
            AwesomeExample next = getDifferentExample(mNextExample);
            mButtonAwesome.setText(next.getAwesomeRes());
            mNextExample = next;
        }
        else if(v.getId() == R.id.example_github_link)
        {
            // Visit project's github
            String url = "https://github.com/rwslinkman/awesome";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }

    private String getValueText(AwesomeExample mCurrentExample)
    {
        return "@string/" + mCurrentExample.getAwesomeName();
    }

    private DrawableAwesome buildAwesomeDrawable(AwesomeExample example)
    {
        // Build icon
        ExampleActivity activity = this;
        int color = ContextCompat.getColor(activity, R.color.default_grey);
        DrawableAwesome.DrawableAwesomeBuilder builder = new DrawableAwesome.DrawableAwesomeBuilder(activity, example.getAwesomeRes());
        builder.setColor(color);
        builder.setSize(100); // size is in dp
        // More options available
        return builder.build();
    }

    private AwesomeExample getDifferentExample(AwesomeExample example)
    {
        int examplePos = new Random().nextInt(mExamples.size());
        AwesomeExample nextExample = mExamples.get(examplePos);
        if(example != null && example.equals(nextExample))
        {
            nextExample = getDifferentExample(nextExample);
        }
        return nextExample;
    }

    public ArrayList<AwesomeExample> getAllIcons()
    {
        ArrayList<AwesomeExample> result = new ArrayList<>();
        // Find all strings
        Field[] fields = R.string.class.getFields();
        for(final Field field : fields)
        {
            String name = field.getName(); //name of string
            if(name.startsWith("fa_"))
            {
                try {
                    // Add string to list of options
                    int id = field.getInt(R.string.class); //id of string
                    result.add(new AwesomeExample(name, id));
                }
                catch (Exception ex) {
                    // Error grabbing string
                    Log.e(TAG, "Could not get string: " + ex.getMessage());
                }
            }
        }
        return result;
    }
}
