package nl.rwslinkman.awesomeexample;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import nl.rwslinkman.awesome.ButtonAwesome;
import nl.rwslinkman.awesome.DrawableAwesome;
import nl.rwslinkman.awesome.TextAwesome;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);

        // Find components in XML
        ImageView drawableView = (ImageView) findViewById(R.id.my_awesome_imageview);
        TextAwesome textAwesome = (TextAwesome) findViewById(R.id.my_awesome_text);
        ButtonAwesome buttonAwesome = (ButtonAwesome) findViewById(R.id.my_awesome_button);

        // Build icon
        ExampleActivity activity = this;
        int color = ContextCompat.getColor(activity, android.R.color.holo_red_light);
        DrawableAwesome.DrawableAwesomeBuilder builder = new DrawableAwesome.DrawableAwesomeBuilder(activity, R.string.fa_anchor);
        builder.setColor(color);
        builder.setSize(100); // size is in dp
        DrawableAwesome myDrawable = builder.build();

        // Put icon into ImageView
        drawableView.setImageDrawable(myDrawable);
    }
}
