package in.silive.techtrishnabeta2.network;

import in.silive.techtrishnabeta2.R;
import in.silive.techtrishnabeta2.RegisterFragment;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class DialogBox {
	private String action;
	private Uri uri;
	private ActionBarActivity activity;
	private Intent intent;
	private String title;
	private String body;
	private String pButtonTxt;
	private int viewCase;

	public DialogBox(ActionBarActivity activity) {
		this.activity = activity;
	}

	public DialogBox(ActionBarActivity activity, String action, Uri uri,
			int viewCase) {
		this.activity = activity;
		this.action = action;
		this.uri = uri;
		this.viewCase = viewCase;
		setIntent();
	}

	public void onCreateDialogBox() {
		// create an object of the type dialog
		final Dialog builder = new Dialog(activity);
		// remove the title of the dialog
		builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// set the layout of the dialog
		builder.setContentView(R.layout.dialog_layout);
		// get a reference to the dialog box title and set its text
		TextView dHead = (TextView) builder.findViewById(R.id.dHead);
		dHead.setText(getTitle());
		// get a reference to the dialog box body and set its text
		TextView dBody = (TextView) builder.findViewById(R.id.dBody);
		dBody.setText(getBody());
		// get a reference to the dialog box positive button set the onClick
		// event and also its text
		Button pButton = (Button) builder.findViewById(R.id.pButton);
		pButton.setText(getButtonText());
		pButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (viewCase) {
				case 0:
					activity.startActivity(getIntent());
					break;
				case 1:
					Fragment fragment = new RegisterFragment();
					FragmentManager fragmentManager = activity
							.getSupportFragmentManager();
					fragmentManager.beginTransaction()
							.replace(R.id.content_frame, fragment).commit();
					builder.dismiss();
				}
			}
		});
		// get a reference to the dialog box negative button set the onClick
		// event and also its text
		Button nButton = (Button) builder.findViewById(R.id.nButton);
		nButton.setText("Cancel");
		nButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				builder.dismiss();
			}
		});
		builder.show();
	}

	// create alert box
	public void onCreateAlertBox() {
		// create an object of the type dialog
		final Dialog builder = new Dialog(activity);
		// remove the title of the dialog
		builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// set the layout of the dialog
		builder.setContentView(R.layout.alert_layout);
		// get a reference to the dialog box title and set its text
		TextView aHead = (TextView) builder.findViewById(R.id.aHead);
		aHead.setText(getTitle());
		// get a reference to the dialog box body and set its text
		TextView aBody = (TextView) builder.findViewById(R.id.aBody);
		aBody.setText(getBody());
		// get a reference to the dialog box positive button set the onClick
		// event and also its text
		Button aButton = (Button) builder.findViewById(R.id.aButton);
		aButton.setText(getButtonText());
		aButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				builder.dismiss();
			}
		});
		builder.show();
	}

	// set the intent to start the activity on positive button click
	public void setIntent() {
		if (uri == null) {
			intent = new Intent(action);
		} else {
			intent = new Intent(action, uri);
		}
	}

	// get the intent to launch the activity on positive button click
	public Intent getIntent() {
		return this.intent;
	}

	// set the title of the dialog box
	public void setTitle(String title) {
		this.title = title;
	}

	// get the title of the dialog box
	public String getTitle() {
		return this.title;
	}

	// set the content of the dialog
	public void setBody(String body) {
		this.body = body;
	}

	// get the content of the dialog
	public String getBody() {
		return this.body;
	}

	// set the text of the positive button
	public void setButtonText(String pButtonTxt) {
		this.pButtonTxt = pButtonTxt;
	}

	// return the value of the button text
	public String getButtonText() {
		return this.pButtonTxt;
	}
}
