package in.silive.techtrishnabeta2;

import java.util.regex.Pattern;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class ValidateRegistrartion {

	RegisterFragment context;
	boolean ret;

	
	public boolean validate(View view) {

		Spinner collegeSpinner = (Spinner) view.findViewById(R.id.collge_list);
		EditText otherCollegeEditText = (EditText) view
				.findViewById(R.id.reg_OtherCollege);
		EditText nameEditText = (EditText) view.findViewById(R.id.reg_Name);
		EditText studentNoEditText = (EditText) view
				.findViewById(R.id.reg_StudentNo);
		EditText emailEditText = (EditText) view.findViewById(R.id.reg_Email);
		EditText contactNoEditText = (EditText) view
				.findViewById(R.id.reg_ContactNo);
		EditText partnerIdEditText = (EditText) view
				.findViewById(R.id.reg_PartnerId);
		EditText passwordEditText = (EditText) view
				.findViewById(R.id.reg_Password);
		EditText confirmpasswordEditText = (EditText) view
				.findViewById(R.id.reg_ConfirmPassword);
		// RadioGroup genderRadioGroup = (RadioGroup) view
		// .findViewById(R.id.reg_GenderGroup);
		// Spinner branchSpinner = (Spinner)
		// view.findViewById(R.id.branch_list);
		// Spinner yearSpinner = (Spinner) view.findViewById(R.id.year_list);
		// CheckBox tShirtCheckBox = (CheckBox) view
		// .findViewById(R.id.reg_checkBox_TShirt);
		// CheckBox accomodationCheckBox = (CheckBox) view
		// .findViewById(R.id.reg_checkBox_Accomodation);

		String studentNoRegexString = "\\d{7}[d|D|l|D]?";
		String emailRegexString = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		String phNoRegexString = "[7|8|9]\\d{9}";
		String partnerIdRegexString = "[T|t][T|t]\\d{4}";

		String nameString = nameEditText.getText().toString().trim();
		String studentNoString = studentNoEditText.getText().toString().trim();
		String emailString = emailEditText.getText().toString().trim();
		String contactString = contactNoEditText.getText().toString().trim();
		String passwordString = passwordEditText.getText().toString().trim();
		String confirmPasswordString = confirmpasswordEditText.getText()
				.toString().trim();
		String partnerIdString = partnerIdEditText.getText().toString().trim();
		String otherCollegeString = otherCollegeEditText.getText().toString()
				.trim();

		boolean studentno, emailId, contact, partnerId;

		int position = collegeSpinner.getSelectedItemPosition();

		if (position == 0) {
			studentno = Pattern.matches(studentNoRegexString, studentNoString);
			emailId = Pattern.matches(emailRegexString, emailString);
			contact = Pattern.matches(phNoRegexString, contactString);

			if (studentno == true && emailId == true && contact == true
					&& nameString != "" && passwordString != ""
					&& confirmPasswordString != ""
					&& passwordString.equals(confirmPasswordString)) {
				// Toast.makeText(view.getContext(), "true",
				// Toast.LENGTH_SHORT).show();
				ret = true;
			} else {
				// Toast.makeText(view.getContext(), "false",
				// Toast.LENGTH_SHORT).show();
				ret = false;
			}
		}

		int l = RegisterFragment.colleges.length;

		if (position != 0 && position != (l - 1)) {
			emailId = Pattern.matches(emailRegexString, emailString);
			contact = Pattern.matches(phNoRegexString, contactString);
			partnerId = Pattern.matches(partnerIdRegexString, partnerIdString);

			if (emailId == true
					&& ((partnerId == true) || (partnerIdString.equals("")))
					&& contact == true && nameString != ""
					&& passwordString != "" && confirmPasswordString != ""
					&& passwordString.equals(confirmPasswordString)) {
				// Toast.makeText(view.getContext(), "true",
				// Toast.LENGTH_SHORT).show();
				ret = true;
			} else {
				// Toast.makeText(view.getContext(), "false",
				// Toast.LENGTH_SHORT).show();
				ret = false;
			}

		}

		if (position == (l - 1)) {
			emailId = Pattern.matches(emailRegexString, emailString);
			contact = Pattern.matches(phNoRegexString, contactString);
			partnerId = Pattern.matches(partnerIdRegexString, partnerIdString);

			if (!otherCollegeString.equals("") && emailId == true
					&& ((partnerId == true) || (partnerIdString.equals("")))
					&& contact == true && nameString != ""
					&& passwordString != "" && confirmPasswordString != ""
					&& passwordString.equals(confirmPasswordString)) {
				// Toast.makeText(view.getContext(),
				// "true"+otherCollegeString+" -", Toast.LENGTH_SHORT).show();
				ret = true;
			} else {
				// Toast.makeText(view.getContext(), "false",
				// Toast.LENGTH_SHORT).show();
				ret = false;
			}
		}

		return ret;
	}

}
