package com.flux.kituri.app.business.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flux.kituri.R;
import com.flux.kituri.app.flux.ActionsCreator;
import com.flux.kituri.app.flux.BaseStoreChangeEvent;
import com.flux.kituri.app.flux.Store;
import com.flux.kituri.app.ui.activity.MainActivity;
import com.flux.kituri.app.ui.base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    static public final String TAG = "LoginActivity";
    private EditText et_account, et_password;
    private Button btn_login, btn_register;

    private LoginActionsCreator actionsCreator;

    @Override
    public ArrayList<Store> initFlux() {
        actionsCreator = (LoginActionsCreator) ActionsCreator.get(getDispatcher(), LoginActionsCreator.class);
        ArrayList<Store> stores = new ArrayList<Store>();
        stores.add(new LoginStore());
        return stores;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionsCreator.autoLogin(this);
        //假装用户输入好了用户名和密码
        et_account.setText("aaa@bbb.com");
        et_password.setText("cccccc");
    }

    private void initView(){
        et_account = (EditText) findViewById(R.id.et_account);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        btn_register = (Button)findViewById(R.id.btn_register);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    private boolean checkInput() {
        if (et_account.getText().length() == 0) {
            return false;
        }
        if (et_password.getText().length() == 0) {
            return false;
        }
        return true;
    }

    private void hideKeyBroad() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onClick(View v) {
        hideKeyBroad();
        switch (v.getId()){
            case R.id.btn_login:
                if (checkInput()) {
                    actionsCreator.login(this, et_account.getText().toString(), et_password.getText().toString());
                } else {
                    Toast.makeText(this, R.string.msg_login_input_your_account_password, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_register:

                break;
        }
    }

    @Subscribe
    public void onActionEvent(BaseStoreChangeEvent storeChangeEvent) {
        if (storeChangeEvent == null) {
            return;
        }
        super.actionEvent(storeChangeEvent);
        if (storeChangeEvent.status == BaseStoreChangeEvent.EVENT_SUCCESS) {
            if (storeChangeEvent instanceof LoginStore.LoginChangeEvent) {
                navigationMain();
            }
        } else if(storeChangeEvent.status == LoginStore.LoginChangeEvent.EVENT_NEED_LOGIN){
            Toast.makeText(this, R.string.msg_need_login, Toast.LENGTH_SHORT).show();
            dismiss();
        }
    }

    private void navigationMain(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
