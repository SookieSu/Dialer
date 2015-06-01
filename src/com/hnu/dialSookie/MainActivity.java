package com.hnu.dialSookie;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setContentView(R.layout.add_friends);
	}//������ͼ

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}//�����˵�
	
	public void inputNumber(View view)
	{
		//������ּ�����Ҫ��������
		//Toast.makeText(this, "aaaaa", 1000).show();
        EditText et_number = (EditText)findViewById(R.id.editText1);
		String num = view.getTag().toString();
		et_number.append(num);
	}
	public void deleteNumber(View view)
	{
		EditText et_number = (EditText)findViewById(R.id.editText1);
		String number = et_number.getText().toString();
		if(number.length()!=0)
		{
			number = number.substring(0, number.length()-1);
			et_number.setText(number);
		}
		else
			Toast.makeText(this, "��������绰����", 1000).show();
	}
	public void call(View view)
	{
		EditText et_number = (EditText)findViewById(R.id.editText1);
		String number = et_number.getText().toString();
		Pattern pattern = Pattern.compile("1\\d{10}");
		Matcher matcher = pattern.matcher(number);
		if(matcher.matches()){
		//��ͼIntent
		if((number!=null)&&(!"".equals(number.trim())))
		{
		Intent callIntent = new Intent();
		callIntent.setAction(Intent.ACTION_CALL);
		callIntent.setData(Uri.parse("tel:"+number));
		startActivity(callIntent);
		}
		}
		else
		{
			Toast.makeText(this, "�����գ�������ȷ�ĵ绰�������0-0", 1000).show();
		}
	}
	public void addContact(View view)
	//�����ϵ��
	{
		EditText et_number = (EditText)findViewById(R.id.editText1);
		String number = et_number.getText().toString();
		Intent it =new Intent(Intent.ACTION_INSERT,Uri.withAppendedPath(
				Uri.parse("content://com.android.contacts"), "contacts"));
		it.setType("vnd.android.cursor.dir/person");
		if(number.length()!=0)
		{
			it.putExtra(
		    android.provider.ContactsContract.Intents.Insert.SECONDARY_PHONE,
			number);
		}
		startActivity(it);
	}

}
