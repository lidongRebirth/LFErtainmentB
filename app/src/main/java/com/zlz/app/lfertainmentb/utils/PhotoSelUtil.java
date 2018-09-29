package com.zlz.app.lfertainmentb.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;

import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.filter.Filter;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnSelectedListener;
import com.zlz.app.lfertainmentb.R;
import com.zlz.app.lfertainmentb.activitys.publishactivity.GifSizeFilter;
import com.zlz.app.lfertainmentb.activitys.publishactivity.Glide4Engine;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.PermissionUtil;
import com.zlz.app.lfertainmentb.mybaselibrary.utils.ToastUtil;

import java.io.File;
import java.util.List;
import java.util.Set;

/**
 * 创建者：张磊
 * 时间：2018/7/3
 * 类描述：照片选择
 * 修改人：
 * 修改时间：2018/7/3
 * 修改备注：
 */
public class PhotoSelUtil {
    public static final int PHOTO_REQUEST_RESTURE = 300;//选择照片回调码
    private OnVideoSelClick onVideoSelClick;
    private Activity context;
    private int size;
    private Set<MimeType> mimeTypes;
    private int resultCode = 0;
    private boolean capture = false;

    public PhotoSelUtil(Activity context, int size, Set<MimeType> mimeTypes) {
        this.context = context;
        this.size = size;
        this.mimeTypes = mimeTypes;
    }

    public PhotoSelUtil(Activity context, int size, Set<MimeType> mimeTypes, int resultCode) {
        this.context = context;
        this.size = size;
        this.mimeTypes = mimeTypes;
        this.resultCode = resultCode;
    }

    public void capture() {
        this.capture = true;
    }

    //*调用的是知乎的Matisse框架
    public void sel() {
        boolean whiteandread = PermissionUtil.verifyReadAndWritePermissions(context);
        boolean takephoto = PermissionUtil.verifyTakePhoto(context);
        if (!whiteandread) {
            ToastUtil.showToast(context, "请开启读写权限");
            return;
        }
        if (!takephoto) {
            ToastUtil.showToast(context, "请开启照相和录音权限");
            return;
        }
        Matisse.from(context)
                .choose(mimeTypes)//*MimeType.ofAll()视频和照片,MimeType.ofImage()照片,MimeType.ofVideo视频
                .maxSelectable(size)
                .countable(false)
                .theme(R.style.Matisse_Dracula)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new Glide4Engine())//*为了适配Glide4.0+版本
                .capture(capture)
                .captureStrategy(new CaptureStrategy(true, "com.tzhhx.app.clanunity.fileProvider"))
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                        if (pathList.size() > 0 && !pathList.get(0).contains(".jpg") && !pathList.get(0).contains(".png")
                                && !pathList.get(0).contains(".gif") && !pathList.get(0).contains(".bmp")
                                && !pathList.get(0).contains(".webp")) {
                            if (onVideoSelClick != null) onVideoSelClick.onVideoSel(pathList);
                        }
                    }
                })
                .forResult(PHOTO_REQUEST_RESTURE);
    }

    public void selectImage() {
        boolean whiteandread = PermissionUtil.verifyReadAndWritePermissions(context);
        boolean takephoto = PermissionUtil.verifyTakePhoto(context);
        if (!whiteandread) {
            ToastUtil.showToast(context, "请开启读写权限");
            return;
        }
        if (!takephoto) {
            ToastUtil.showToast(context, "请开启照相和录音权限");
            return;
        }
        if (resultCode == 0) {
            ToastUtil.showToast(context, "返回码为空");
            return;
        }

        Matisse.from(context)
                .choose(MimeType.ofImage(), true)//MimeType.ofAll()视频和照片,MimeType.ofImage()照片,MimeType.ofVideo视频
                .countable(true)
                .maxSelectable(size)
                .countable(false)
                .theme(R.style.Matisse_Dracula)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);
    }

    public void selectAll() {
        boolean whiteandread = PermissionUtil.verifyReadAndWritePermissions(context);
        boolean takephoto = PermissionUtil.verifyTakePhoto(context);
        if (!whiteandread) {
            ToastUtil.showToast(context, "请开启读写权限");
            return;
        }
        if (!takephoto) {
            ToastUtil.showToast(context, "请开启照相和录音权限");
            return;
        }
        if (resultCode == 0) {
            ToastUtil.showToast(context, "返回码为空");
            return;
        }

        Matisse.from(context)
                .choose(MimeType.ofAll(), true)//MimeType.ofAll()视频和照片,MimeType.ofImage()照片,MimeType.ofVideo视频
                .countable(true)
                .maxSelectable(size)
                .countable(false)
                .theme(R.style.Matisse_Dracula)
                .addFilter(new GifSizeFilter(320, 320, 5 * Filter.K * Filter.K))
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .forResult(resultCode);
    }

    public void setOnVideoSelClick(OnVideoSelClick onVideoSelClick) {
        this.onVideoSelClick = onVideoSelClick;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setMimeType(Set<MimeType> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public interface OnVideoSelClick {
        void onVideoSel(List<String> pathList);
    }

    public static boolean isImage(String paht) {
        if (!paht.contains(".jpg") && !paht.contains(".png")
                && !paht.contains(".gif") && !paht.contains(".bmp")
                && !paht.contains(".webp")) {
            return false;
        }
        return true;

    }

    private static final String IMAGE_FILE_LOCATION = "file:///" + Environment.getExternalStorageDirectory().getPath() + "/temp.jpg";
    private Uri imageUri = Uri.parse(IMAGE_FILE_LOCATION);

//    public void startPhotoZoom(Uri uri) {
//        Intent intent = new Intent("com.android.camera.action.CROP");
//        intent.setDataAndType(uri, "image/*");
//        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
//        intent.putExtra("crop", "true");
//        //该参数可以不设定用来规定裁剪区的宽高比
//        intent.putExtra("aspectX", 1);
//        intent.putExtra("aspectY", 1);
//        //该参数设定为你的imageView的大小
//        intent.putExtra("outputX", 300);
//        intent.putExtra("outputY", 300);
//        intent.putExtra("scale", true);
//        //是否返回bitmap对象
//        intent.putExtra("return-data", false);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());//输出图片的格式
//        intent.putExtra("noFaceDetection", true); // 头像识别
//        startActivityForResult(intent, 3);
//    }

    public static String getVideoImg(String path, Context context) {
        File videoFile;
        long fileSize = FileUtil.getFileSize(new File(path));
        if (fileSize < 1048576 * 5) {
            Bitmap videoBitmap = null;
            try {
                videoBitmap = BitmapUtil.getVideoImg(path);
            } catch (Exception e) {
                ToastUtil.showToast(context, "该视频文件错误，无法上传");
                return "";
            }
            if (videoBitmap != null) {
                videoFile = BitmapUtil.saveBitmap(context, videoBitmap);
                String videoimg = videoFile.getAbsolutePath();
                if (!StringUtil.isEmpty(videoimg)) {
                    return videoimg;
                } else return "";
            }
        }
        return "";
    }
}
