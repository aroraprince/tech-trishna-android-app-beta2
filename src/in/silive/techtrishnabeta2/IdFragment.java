package in.silive.techtrishnabeta2;

import in.silive.techtrishnabeta2.network.DialogBox;
import in.silive.techtrishnabeta2.network.JSONParser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class IdFragment extends Fragment {
	private ProgressDialog progressDialog;
	private TextView setIdText;

	private JSONParser jsonParser;
	private DialogBox dialogBox;

	private static String idUrl = "http://192.168.56.1/techtrishna/id.php";
	private static String TAG_SUCCESS = "success";
	private static String TAG_MESSAGE = "message";
	private String message;
	private String emailText;
	private int viewCase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.id_layout, container,
				false);
		getActivity().setTitle("GET YOUR TT-ID");
		setIdText = (TextView) rootView.findViewById(R.id.setIdText);
		Button getIdButton = (Button) rootView.findViewById(R.id.getIdButton);
		getIdButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EditText getEmailIdText = (EditText) rootView
						.findViewById(R.id.getEmailIdText);
				emailText = getEmailIdText.getText().toString();
				new GetId().execute();
			}
		});
		return rootView;
	}

	private class GetId extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Sendig Email Id. Please wait...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// initialise the JSONParser object
			jsonParser = new JSONParser();
			// list to send the values
			List<NameValuePair> args = new ArrayList<NameValuePair>();
			args.add(new BasicNameValuePair("email", emailText));
			// add the JSON object
			JSONObject json = jsonParser.makeHttpRequest(idUrl, "POST", args);
			try {
				message = json.getString(TAG_MESSAGE);
				viewCase = json.getInt(TAG_SUCCESS);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return message;
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			if (viewCase == 0) {
				dialogBox = new DialogBox((ActionBarActivity) getActivity(), null, null,1);
				dialogBox.setTitle("REGISTER FIRST");
				dialogBox
						.setBody("It seems that you are not registered for TechTrishna. Do you want to register for TechTrishna 2014?");
				dialogBox.setButtonText("Yes Let's Go");
				dialogBox.onCreateDialogBox();
			} else {
				setIdText.setText(result);
			}
		}
	}

}
