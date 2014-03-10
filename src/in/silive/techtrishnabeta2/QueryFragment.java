package in.silive.techtrishnabeta2;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class QueryFragment extends Fragment {

	private static String queyUrl = "http://192.168.56.1/techtrishna/send.php";
	private ProgressDialog progressDialog;
	private JSONParser jsonParser;
	// private static String TAG_VALUE = "value";
	private static String TAG_MESSAGE = "message";
	private String message;
	private String emailText;
	private String queryText;
	private String queryTag;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.query_layout,
				container, false);
		getActivity().setTitle("QUERY US"); // set the title of the fragment
		Button submitButton = (Button) rootView.findViewById(R.id.queryButton);
		submitButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				EditText emailEditText = (EditText) rootView
						.findViewById(R.id.emailText);
				EditText queryEditText = (EditText) rootView
						.findViewById(R.id.queryText);
				RadioGroup queryRadioGroup = (RadioGroup) rootView
						.findViewById(R.id.radioQuery);
				int selectedId = queryRadioGroup.getCheckedRadioButtonId();
				RadioButton queryRadioButton = (RadioButton) rootView
						.findViewById(selectedId);
				queryTag = queryRadioButton.getTag().toString();
				emailText = emailEditText.getText().toString().trim();
				queryText = queryEditText.getText().toString().trim();
				Log.d("queryTag",queryTag);
				new SubmitQuery().execute();
			}
		});
		return rootView;
	}

	private class SubmitQuery extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Sendig Query. Please wait...");
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
			args.add(new BasicNameValuePair("query", queryText));
			args.add(new BasicNameValuePair("tag", queryTag));
			// add the JSON object
			JSONObject json = jsonParser.makeHttpRequest(queyUrl, "POST", args);
			try {
				Log.d("tag", json.toString());
				message = json.getString(TAG_MESSAGE);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return message;
		}

		@Override
		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
		}
	}
}
