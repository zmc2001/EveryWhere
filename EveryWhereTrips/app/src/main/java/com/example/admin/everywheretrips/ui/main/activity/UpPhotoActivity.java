package com.example.admin.everywheretrips.ui.main.activity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.everywheretrips.R;
import com.example.admin.everywheretrips.base.BaseActivity;
import com.example.admin.everywheretrips.bean.UpLoadBean;
import com.example.admin.everywheretrips.presenter.EmptyPresenter;
import com.example.admin.everywheretrips.util.ImageLoader;
import com.example.admin.everywheretrips.util.OtherUtils;
import com.example.admin.everywheretrips.view.main.EmptyView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UpPhotoActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView, View.OnClickListener {

    private static final int REQUEST_CAMERA = 0;
    private static final int REQUEST_ALBUM_OK = 1;
    private static final String TAG = "UpPhotoActivity";
    @BindView(R.id.upphoto_tb)
    Toolbar mUpphotoTb;
    @BindView(R.id.upphoto_img)
    ImageView mUpphotoImg;
    @BindView(R.id.upphote_tb_open)
    ImageView mUpphoteTbOpen;
    @BindView(R.id.upphote_ll)
    LinearLayout mUpphoteLl;
    @BindView(R.id.update_photo_back)
    ImageView mUpdatePhotoBack;
    private PopupWindow mPopupWindow;
    private View mInflate;
    private File mTmpFile;
    private Uri mImageUri;

    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_up_photo;
    }

    @Override
    protected void initView() {
        //亮色模式,会将状态栏文字修改为黑色
        StatusBarUtil.setLightMode(this);
        mUpphotoTb.setTitle("");
        setSupportActionBar(mUpphotoTb);

        String oldphoto = getIntent().getStringExtra("oldphoto");
        ImageLoader.setImage(this,oldphoto,mUpphotoImg,R.mipmap.ic_launcher);
    }

    @OnClick({R.id.upphote_tb_open,R.id.update_photo_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.upphote_tb_open:
                openPopWindown();
                break;
            case R.id.update_photo_back:
                finish();
                break;
        }
    }

    private void openPopWindown() {
        mInflate = LayoutInflater.from(this).inflate(R.layout.open_camera, null);
        LinearLayout open_camera_ll = mInflate.findViewById(R.id.open_camera_ll);
        mPopupWindow = new PopupWindow(mInflate,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //点击外部消失
        mPopupWindow.setBackgroundDrawable(null);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.showAtLocation(open_camera_ll, Gravity.CENTER, 0, 0);
        mInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        Button open_camera_camera = mInflate.findViewById(R.id.open_camera_camera);
        Button open_camera_album = mInflate.findViewById(R.id.open_camera_album);
        Button open_camera_cancel = mInflate.findViewById(R.id.open_camera_cancel);
        open_camera_camera.setOnClickListener(this);
        open_camera_album.setOnClickListener(this);
        open_camera_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.open_camera_camera:
                openCamera();
                mPopupWindow.dismiss();
                break;
            case R.id.open_camera_album:
                openAlbum();
                mPopupWindow.dismiss();
                break;
            case R.id.open_camera_cancel:
                mPopupWindow.dismiss();
                break;
            case R.id.open_camera_ll:
                mPopupWindow.dismiss();
                break;
        }
    }

    //打开相册
    private void openAlbum() {
        //启动相册
        Intent albumIntent = new Intent(Intent.ACTION_PICK, null);
        albumIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(albumIntent, REQUEST_ALBUM_OK);
    }

    private void openCamera() {
        //创建文件用于保存图片
        mTmpFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mTmpFile.exists()) {
            try {
                mTmpFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //适配7.0,  等到对应的mImageUri路径地址
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mTmpFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mTmpFile);
        }

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CAMERA:
                //请求相机
                if (mTmpFile != null) {
                   /* Log.d(TAG, "onActivityResult: 请求相机： " + mTmpFile.getAbsolutePath());*/

                    Glide.with(this).load(mTmpFile).into(mUpphotoImg);
                    uploadFile(mTmpFile);
                }
                break;
            case REQUEST_ALBUM_OK:
                //获取到相册选中后的图片URI路径
                Uri imageUri = data.getData();
                File file = getFileFromUri(imageUri, this);

                if (file.exists()){
                    uploadFile(file);
                }
                break;

        }
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    private void uploadFile(File tmpFile) {
        String url = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();


        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mTmpFile);

        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808C")
                .addFormDataPart("file", mTmpFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(string, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean!=null){
                            if (upLoadBean.getCode() == 200){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = getIntent();
                                        String imgurl = upLoadBean.getData().getUrl();
                                        intent.putExtra("imageurl",imgurl);
                                        setResult(13,intent);
                                        finish();
                                    }
                                });
                            }else{
                                Toast.makeText(UpPhotoActivity.this,upLoadBean.getRes(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }

}
