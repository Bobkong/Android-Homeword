package com.example.bob.lab2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private RadioButton mRadioStudent,mRadioTeacher;
    private View mView;
    private Button mLogin,mRegister;
    private TextInputLayout mIdInputlayout,mPwInputlayout;
    private EditText mId,mPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = (ImageView)findViewById(R.id.image_sysu);
        mView = findViewById(R.id.lay_root);
        mRadioStudent = (RadioButton) findViewById(R.id.student);
        mRadioTeacher = (RadioButton) findViewById(R.id.teacher);
        mLogin = (Button)findViewById(R.id.login) ;
        mRegister = (Button)findViewById(R.id.register);
        mIdInputlayout = (TextInputLayout)findViewById(R.id.edit_id);
        mPwInputlayout = (TextInputLayout)findViewById(R.id.edit_password);
        mId = (EditText) findViewById(R.id.id_text);
        mPw = (EditText) findViewById(R.id.pw_text);
        clickImage();
        clickRadioButton();
        clickLogin();
        clickRegister();


    }

    private void clickRegister() {
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mRadioStudent.isChecked())
                    Toast.makeText(MainActivity.this,getString(R.string.reg_stu),Toast.LENGTH_SHORT).show();
                if(mRadioTeacher.isChecked())
                    Toast.makeText(MainActivity.this, R.string.reg_teacher,Toast.LENGTH_SHORT).show();
            }
        });
    }

    //点击登录
    private void clickLogin() {
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mId.getText().toString().length() == 0)
                    mIdInputlayout.setError(getString(R.string.id_error));
                else if(mPw.getText().toString().length() == 0)
                    mPwInputlayout.setError(getString(R.string.pw_error));
                else if(mId.getText().toString() != getString(R.string.correct_id)||mPw.getText().toString() != getString(R.string.correct_pw)){
                    Snackbar.make(mView,getString(R.string.id_or_pw_error),Snackbar.LENGTH_SHORT)
                            .setAction(getString(R.string.commit),new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {

                                }
                            }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
                else{
                    Snackbar.make(mView, R.string.succeed,Snackbar.LENGTH_SHORT)
                            .setAction(getString(R.string.commit),new View.OnClickListener(){
                                @Override
                                public void onClick(View v) {

                                }
                            }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        });
    }

    //点击radiobutton
    private void clickRadioButton() {
        mRadioStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mView,getString(R.string.mode_student),Snackbar.LENGTH_SHORT)
                        .setAction(getString(R.string.commit),new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
        });
        mRadioTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mView, R.string.mode_teacher,Snackbar.LENGTH_SHORT)
                        .setAction(getString(R.string.commit),new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {

                            }
                        }).setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
        });
    }
    //点击image
    private void clickImage() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this).setTitle(R.string.upload_image)
                        .setItems(new String[]{getString(R.string.take_photo), getString(R.string.choose_pic)},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if (which == 0)
                                            Toast.makeText(MainActivity.this, getString(R.string.mode_takephoto), Toast.LENGTH_SHORT).show();
                                        else if (which == 1)
                                            Toast.makeText(MainActivity.this, R.string.mode_choosepic, Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, R.string.mode_cancel, Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }
}

