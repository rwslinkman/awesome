AndroidAwesome
=======

Use FontAwesome icons in your Android app with Awesome

Author: [Rick Slinkman](<http://rwslinkman.nl>)

Use all the FontAwesome icons you know from the websites in your Android app.  
You can create Buttons, TextViews and Drawables with this library.  
Credits go to FontAwesome for their incredible work.

<http://fortawesome.github.io/Font-Awesome/>

Integrating with Android Studio
-------------------------------

The AndroidAwesome library comes as a Gradle bundle via jcenter.

```
dependencies {
	compile 'nl.rwslinkman.awesome:awesome:1.3'
}
```

Story behind this library
-------------------------

I found this code some time ago and reused it the other day.  
Hoping to find the original author, I searched the web for a repository with
this code.  
Unfortunately, I have not found him or her.  
I decided to republish this code as an open source library,  
hoping the original author makes himself known.

How to use
----------
AndroidAwesome can be used in XML. You can use a ButtonAwesome or a TextAwesome

```
<nl.rwslinkman.awesome.ButtonAwesome 
	android:id="@+id/my_awesome_button"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="@string/fa_cloud_upload"
	android:textColor="@android:color/white"
	android:textSize="20sp" />
```
	
```
<nl.rwslinkman.awesome.TextAwesome
	android:id="@+id/my_awesome_text"
	android:layout_width="wrap_content"
	android:layout_height="wrap_content"
	android:text="@string/fa_file_image_o"
	android:textColor="@android:color/white"
	android:textSize="20sp" />
```

It is also possible to create a DrawableAwesome using the DrawableAwesomeBuilder

```
// Find components in XML
ImageView drawableView = (ImageView) findViewById(R.id.my_awesome_imageview);

// Build icon
ExampleActivity activity = this;
int color = ContextCompat.getColor(activity, android.R.color.holo_red_light);
DrawableAwesome.DrawableAwesomeBuilder builder = new DrawableAwesome.DrawableAwesomeBuilder(activity, R.string.fa_anchor);
builder.setColor(color);
builder.setSize(100); // size is in dp
DrawableAwesome myDrawable = builder.build();

// Put icon into ImageView
drawableView.setImageDrawable(myDrawable);
```

The icon to be used is set through the String resources. All icons have a `fa-` prefix, as they do on the website.