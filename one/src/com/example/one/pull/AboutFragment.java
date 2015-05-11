package com.example.one.pull;
import com.example.one.R;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class AboutFragment extends DialogFragment {

	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.dialog_about,container, false);
		WebView webView = 	(WebView)	v.findViewById(R.id.web);

		webView.loadUrl("file:///android_asset/licenses.html");

		return v;
	}
	 */

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		View v = layoutInflater.inflate(R.layout.dialog_about, null);
		WebView webView = 	(WebView)	v.findViewById(R.id.web);
		webView.loadUrl("file:///android_asset/licenses.html");

		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setPositiveButton(android.R.string.cancel, new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).create();
	}
}
