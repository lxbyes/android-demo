package me.leckie.activity;

import java.lang.ref.WeakReference;

import me.leckie.exception.ServiceRulesException;
import me.leckie.service.UserService;
import me.leckie.service.impl.UserServiceImpl;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leckie.R;
import com.leckie.R.id;
import com.leckie.R.layout;

public class LoginActivity extends Activity {

	private static final String TAG = "LoginActivity";

	private static final String LOGIN_SUCCESS_MSG = "登录成功";
	public static final String LOGIN_FAILED_MSG = "用户名|密码错误";
	private static final String LOGIN_ERROR_MSG = "登录出错";

	private static final int LOGIN_SUCESS = 1;

	private EditText txtLoginName;
	private EditText txtLoginPassword;
	private Button btnLogin;
	private Button btnReset;

	private UserService userService;

	private static ProgressDialog dialog;

	/**
	 * 初始化
	 */
	private void init() {
		this.txtLoginName = (EditText) this.findViewById(R.id.txt_login_name);
		this.txtLoginPassword = (EditText) this
				.findViewById(R.id.txt_login_password);
		this.btnLogin = (Button) this.findViewById(R.id.btn_login);
		this.btnReset = (Button) this.findViewById(R.id.btn_reset);

		userService = new UserServiceImpl();
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
				final String loginName = txtLoginName.getText().toString();
				final String loginPassword = txtLoginPassword.getText()
						.toString();

				if (dialog == null) {
					dialog = new ProgressDialog(LoginActivity.this);
				}
				dialog.setTitle("请等待");
				dialog.setMessage("正在登录...");
				dialog.setCancelable(false);
				dialog.show();

				/**
				 * 输入值的验证
				 */

				/**
				 * 副线程
				 */
				Thread thread = new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							userService.userLogin(loginName, loginPassword);
							handler.sendEmptyMessage(LOGIN_SUCESS);
						} catch (ServiceRulesException e) {
							Bundle data = new Bundle();
							data.putSerializable("errorMsg", e.getMessage());
							Message msg = new Message();
							msg.setData(data);
							handler.sendMessage(msg);
						} catch (Exception e) {
							e.printStackTrace();
							Bundle data = new Bundle();
							data.putSerializable("errorMsg", LOGIN_ERROR_MSG);
							Message msg = new Message();
							msg.setData(data);
							handler.sendMessage(msg);
						}
					}
				});
				thread.start();
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

	public void showTip(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
	}

	private IHandler handler = new IHandler(this);

	private static class IHandler extends Handler {

		private final WeakReference<Activity> mActivity;

		public IHandler(LoginActivity activity) {
			mActivity = new WeakReference<Activity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (dialog != null) {
				dialog.dismiss();
			}

			switch (msg.what) {
			case 0:
				((LoginActivity) mActivity.get()).showTip(msg.getData()
						.getString("errorMsg"));
				break;
			case LOGIN_SUCESS:
				((LoginActivity) mActivity.get()).showTip(LOGIN_SUCCESS_MSG);
				break;
			}
		}
	}
}