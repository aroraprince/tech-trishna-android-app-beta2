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
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class Register {
	EditText otherCollegeEditText;
	int collegePosition;
	View rootView;
	private ActionBarActivity activity;
	private DialogBox alertBox;

	public static final String URL_NEW_COLLEGE_STRING = "http://192.168.56.1/techtrishna/get_new_college_id.php";
	public static final String URL_NEW_REGISTRATION_STRING = "http://192.168.56.1/techtrishna/register_user.php";

	String nameString, emailIdString, contactNoString, partnerIdString,
			studentNoString, passwordString, genderString, branchString,
			yearString, tShirtString;
	String accomodationString;

	JSONParser jsonParser = new JSONParser();
	List<NameValuePair> register = new ArrayList<NameValuePair>();
	public ProgressDialog progressDialog;

	public Register(View rootView, ActionBarActivity activity) {
		this.rootView = rootView;
		this.activity = activity;
	}

	public void newRegistration() {
		Spinner collegeSpinner = (Spinner) rootView
				.findViewById(R.id.collge_list);
		otherCollegeEditText = (EditText) rootView
				.findViewById(R.id.reg_OtherCollege);
		EditText nameEditText = (EditText) rootView.findViewById(R.id.reg_Name);
		EditText studentNoEditText = (EditText) rootView
				.findViewById(R.id.reg_StudentNo);
		EditText emailEditText = (EditText) rootView
				.findViewById(R.id.reg_Email);
		EditText contactNoEditText = (EditText) rootView
				.findViewById(R.id.reg_ContactNo);
		EditText partnerIdEditText = (EditText) rootView
				.findViewById(R.id.reg_PartnerId);
		EditText passwordEditText = (EditText) rootView
				.findViewById(R.id.reg_Password);
		// EditText confirmpasswordEditText = (EditText) rootView
		// .findViewById(R.id.reg_ConfirmPassword);
		RadioGroup genderRadioGroup = (RadioGroup) rootView
				.findViewById(R.id.reg_GenderGroup);
		Spinner branchSpinner = (Spinner) rootView
				.findViewById(R.id.branch_list);
		Spinner yearSpinner = (Spinner) rootView.findViewById(R.id.year_list);
		CheckBox tShirtCheckBox = (CheckBox) rootView
				.findViewById(R.id.reg_checkBox_TShirt);
		CheckBox accomodationCheckBox = (CheckBox) rootView
				.findViewById(R.id.reg_checkBox_Accomodation);

		nameString = nameEditText.getText().toString().trim();
		emailIdString = emailEditText.getText().toString().trim();
		contactNoString = contactNoEditText.getText().toString().trim();
		partnerIdString = partnerIdEditText.getText().toString().trim();
		if (!partnerIdString.equals("")) {
			partnerIdString = partnerIdString.substring(2);
		}

		studentNoString = studentNoEditText.getText().toString().trim();
		passwordString = passwordEditText.getText().toString().trim();

		int genderRadioButtonId = genderRadioGroup.getCheckedRadioButtonId();
		View radioButton = genderRadioGroup.findViewById(genderRadioButtonId);

		genderString = Integer.toString(genderRadioGroup
				.indexOfChild(radioButton) + 1);
		branchString = Integer
				.toString(branchSpinner.getSelectedItemPosition() + 1);
		yearString = Integer
				.toString(yearSpinner.getSelectedItemPosition() + 1);

		boolean tShirt = tShirtCheckBox.isChecked();
		Log.d("tshirt", "" + tShirt);

		if (tShirt) {
			tShirtString = "1";
		} else {
			tShirtString = "0";
		}

		boolean accomodation = accomodationCheckBox.isChecked();

		if (accomodation) {
			accomodationString = "1";
		} else {
			accomodationString = "0";
		}

		int len = RegisterFragment.colleges.length;
		collegePosition = collegeSpinner.getSelectedItemPosition();
		if (collegePosition == (len - 1)) {
			new GetNewCollegeId().execute();
		} else {
			collegePosition = RegisterFragment.collegeId[collegePosition];
			Log.d("Create Response", "" + collegePosition);
			new RegisterParticipant().execute();

		}

	}

	class GetNewCollegeId extends AsyncTask<String, String, String> {

		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(rootView.getContext());
			progressDialog.setMessage("Loading colleges. Please wait...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			String otherCollege = otherCollegeEditText.getText().toString();
			params.add(new BasicNameValuePair("name", otherCollege));
			JSONObject json = jsonParser.makeHttpRequest(
					URL_NEW_COLLEGE_STRING, "POST", params);
			try {
				collegePosition = Integer.parseInt(json.getString("id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			new RegisterParticipant().execute();

		}
	}

	class RegisterParticipant extends AsyncTask<String, String, String> {
		JSONObject json;

		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(rootView.getContext());
			progressDialog.setMessage("Registering You. Please wait...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			register.add(new BasicNameValuePair("collegeId", Integer
					.toString(collegePosition)));
			register.add(new BasicNameValuePair("studentNo", studentNoString));
			register.add(new BasicNameValuePair("name", nameString));
			register.add(new BasicNameValuePair("email", emailIdString));
			register.add(new BasicNameValuePair("contactNo", contactNoString));
			register.add(new BasicNameValuePair("partnerId", partnerIdString));
			register.add(new BasicNameValuePair("password", passwordString));
			register.add(new BasicNameValuePair("gender", genderString));
			register.add(new BasicNameValuePair("branch", branchString));
			register.add(new BasicNameValuePair("year", yearString));
			register.add(new BasicNameValuePair("tShirt", tShirtString));
			register.add(new BasicNameValuePair("accomodation",
					accomodationString));
			json = jsonParser.makeHttpRequest(URL_NEW_REGISTRATION_STRING,
					"POST", register);
			return null;
		}

		protected void onPostExecute(String result) {
			progressDialog.dismiss();
			String tTIdString = "0000";
			try {
				tTIdString = json.getString("TTID");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (tTIdString.equals("0000")) {
				alertBox = new DialogBox(activity);
				alertBox.setTitle("          TECHTRISHNA ID");
				alertBox
						.setBody("\n	Your TechTrishna ID is TT" + tTIdString + ".\n	Try to save it somewhere.");
				alertBox.setButtonText("Yes Go");
				alertBox.onCreateAlertBox();
			} else {
				alertBox = new DialogBox(activity);
				alertBox.setTitle("          TECHTRISHNA ID");
				alertBox
						.setBody("\n	Your TechTrishna ID is TT" + tTIdString + ".\n	Try to save it somewhere.");
				alertBox.setButtonText("Cancel");
				alertBox.onCreateAlertBox();
			}
		}

	}

}
