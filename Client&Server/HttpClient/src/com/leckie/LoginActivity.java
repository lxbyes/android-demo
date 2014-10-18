package com.leckie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	
	private EditText txtLoginName;
	private EditText txtLoginPassword;
	private Button btnLogin;
	private Button btnReset;
	
	/**
	 * 初始化
	 */
	private void init() {
		this.txtLoginName = (EditText) this.findViewById(R.id.txt_login_name);
		this.txtLoginPassword = (EditText) this.findViewById(R.id.txt_login_password);
		this.btnLogin = (Button) this.findViewById(R.id.btn_login);
		this.btnReset = (Button) this.findViewById(R.id.btn_reset);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		
		// 初始化控件
		this.init();
		
		// 点击登录
		this.btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String loginName = txtLoginName.getText().toString();
				String loginPassword = txtLoginPassword.getText().toString();
			}
		});
		
		// 点击重置
		this.btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtLoginName.setText("");
				txtLoginPassword.setText("");
			}
		});
		
	}

}