package com.qiangshijituan.zzq_jianbao.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qiangshijituan.zzq_jianbao.R;
import com.qiangshijituan.zzq_jianbao.bean.Register_Bean;
import com.qiangshijituan.zzq_jianbao.utils.OkHttpUtils;

import java.io.File;
import java.util.HashMap;

/**
 * Created by 赵自强 on 2016/12/10.
 */

public class RegisterActivity extends Activity implements View.OnClickListener {

    private static final int TAKE_PICTURE = 1; // 图片
    private static final int SCALE = 5;//照片缩小比例

    private ImageView iv_zhuCe_card;
    private EditText et_zhuCe_name;
    private EditText et_zhuCe_code;
    private EditText et_zhuCe_mobile;
    private EditText et_zhuCe_password;
    private RadioButton rb_zhuce_nn;
    private TextView tv_chuce_chuce;
    private String name;
    private String code;
    private String mobile;
    private String password;
    private String gender_nan;
    private String gender_nv;
    private TextView tv_zhuCe_text;
    private Bitmap photo;// 选择好的图片的bitmap形式
    private Uri uri;
    private HashMap<String, String> zhuce_map;
    private RadioGroup rg_zhuce_rg;
    private String gender;
    private String p_uri;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jianbao_register);
        initView();

        rg_zhuce_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                rb_zhuce_nn = (RadioButton) findViewById(i);
                gender = rb_zhuce_nn.getText().toString();

                Toast.makeText(RegisterActivity.this, gender, Toast.LENGTH_SHORT).show();
            }
        });
        initButton();
    }

    @Override
    public void onClick(View view) {


    }

    private void initView() {

        iv_zhuCe_card = (ImageView) findViewById(R.id.iv_chuce_imgs);// 照片
        tv_zhuCe_text = (TextView) findViewById(R.id.tv_chuce_text);
        et_zhuCe_name = (EditText) findViewById(R.id.et_zhuce_name);//名字
        et_zhuCe_code = (EditText) findViewById(R.id.et_zhuce_yqm);//邀请码
        et_zhuCe_mobile = (EditText) findViewById(R.id.et_zhuce_number);//手机号
        et_zhuCe_password = (EditText) findViewById(R.id.et_zhuce_mima);//mima
        rg_zhuce_rg = (RadioGroup) findViewById(R.id.rg_zhuce_rg); // 选择男女按钮
        //rg_chuce_nan = (RadioButton) findViewById(R.id.rb_zhuce_nan); // 男
        //rg_chuce_nv = (RadioButton) findViewById(R.id.rb_zhuce_nv); // 女
        tv_chuce_chuce = (TextView) findViewById(R.id.tv_zhuce_zhuce); // 注册

        ImgsButton();// 点击头像获取相机相册
    }

    private void initButton() {

        tv_chuce_chuce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initData();
              /*  if ("".equals(uri)) {
                    Toast.makeText(RegisterActivity.this, "亲，一定要有你的照片", Toast.LENGTH_SHORT).show();
                } else if ("".equals(name)) {
                    Toast.makeText(RegisterActivity.this, "亲，一定要有你的名字", Toast.LENGTH_SHORT).show();
                } else if ("".equals(code)) {
                    Toast.makeText(RegisterActivity.this, "亲，输入你的邀请码", Toast.LENGTH_SHORT).show();
                } else if ("".equals(mobile)) {
                    Toast.makeText(RegisterActivity.this, "亲，输入你的手机号", Toast.LENGTH_SHORT).show();
                } else if ("".equals(password)) {
                    Toast.makeText(RegisterActivity.this, "亲，输入你的密码", Toast.LENGTH_SHORT).show();
                } else if ("".equals(gender)) {
                    Toast.makeText(RegisterActivity.this, "亲，输入你的性别", Toast.LENGTH_SHORT).show();
                } else {*/
                initPost();
                // }
            }
        });

    }


    private void initPost() {
        OkHttpUtils.setGetEntiydata(new OkHttpUtils.EntiyData() {
            @Override
            public void getEntiy(Object obj) {
                Register_Bean chuce_bean = (Register_Bean) obj;
                String status = chuce_bean.getStatus();
                if (status.equals(200)) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegisterActivity.this, "注册成功，宝贝", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

        OkHttpUtils.postMuti(zhuce_map, "http://192.168.4.188/Goods/"
                + "app/common/register.json", RegisterActivity.this, Register_Bean.class, p_uri);

    }

    private void initData() {
        // 获取照片的路径

        name = et_zhuCe_name.getText().toString();
        code = et_zhuCe_code.getText().toString();
        mobile = et_zhuCe_mobile.getText().toString();
        password = et_zhuCe_password.getText().toString();
        p_uri = imageUri.toString();


        zhuce_map = new HashMap<>();
        zhuce_map.put("code", "61B4A64BE6");
        zhuce_map.put("mobile", mobile);
        zhuce_map.put("name", name);
        zhuce_map.put("password", password);
        zhuce_map.put("gender", gender);
    }


    private void ImgsButton() {
        iv_zhuCe_card.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // 获取SD卡储蓄路径
                String state = Environment.getExternalStorageState();
                if (state.equals(Environment.MEDIA_MOUNTED)) {// 如果存储卡可用
                    Intent intent = new Intent(
                            MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, TAKE_PICTURE);
                    imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "image.jpg"));
                    //指定照片保存路径（SD卡），image.jpg为一个临时文件，每次拍照后这个图片都会被替换
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                } else {
                    Toast.makeText(getApplicationContext(), "存储卡不可用",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 取得回传的数据
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null && requestCode == TAKE_PICTURE) { // 拍照上传时
            Bundle extras = data.getExtras();
            if (extras != null) {
                photo = (Bitmap) extras.get("data");
                iv_zhuCe_card.setImageBitmap(photo);
            } else {
                Toast.makeText(RegisterActivity.this, "未找到图片", Toast.LENGTH_LONG)
                        .show();
            }
        }
        if (resultCode == Activity.RESULT_OK) {
            // 当选择的图片不为空的话，在获取到图片的途径
            uri = data.getData();
            try {
                String[] pojo = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = this.getContentResolver();
                    int colunm_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(colunm_index);
                    /***
                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
                     * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                     */
                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        photo = BitmapFactory.decodeStream(cr
                                .openInputStream(uri));
                        iv_zhuCe_card.setImageBitmap(photo);
                    } else {
                    }
                } else {
                }
            } catch (Exception e) {
            }
        }

    }


}