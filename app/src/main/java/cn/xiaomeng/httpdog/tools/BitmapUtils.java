package cn.xiaomeng.httpdog.tools;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.xiaomeng.httpdog.tools.io.FileUtils;

/**
 * 类名：BitmapUtils
 * 编辑时间：2018/4/4
 * 编辑人：崔婧
 * 简介：Bitmap工具类
 */
public class BitmapUtils {

    /**
     * convert Bitmap to byte array
     *
     * @param b 图片
     * @return 字节
     */
    public static byte[] bitmapToByte(Bitmap b) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b 图片
     * @return 字节
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * 把bitmap转换成Base64编码String
     *
     * @param bitmap 图片
     * @return 字节
     */
    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), Base64.DEFAULT);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param drawable 图片
     * @return 字节
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param bitmap 图片
     * @return 字节
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }

    /**
     * scale image
     *
     * @param org       图片
     * @param newWidth  宽
     * @param newHeight 高
     * @return 图片
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     *
     * @param src         图片
     * @param scaleWidth  宽
     * @param scaleHeight 高
     * @return 图片
     */
    public static Bitmap scaleImage(Bitmap src, float scaleWidth, float scaleHeight) {
        if (src == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
    }

    /**
     * 圆bitmap
     *
     * @param bitmap 图片
     * @return 图片
     */
    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 生成bitmap缩略图
     *
     * @param bitmap      图片
     * @param needRecycle 是否释放bitmap原图
     * @param newHeight   目标宽度
     * @param newWidth    目标高度
     * @return 图片
     */
    public static Bitmap createBitmapThumbnail(Bitmap bitmap, boolean needRecycle, int newHeight, int newWidth) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (needRecycle)
            bitmap.recycle();
        return newBitMap;
    }

    /**
     * 保存Bitmap到文件
     *
     * @param bitmap 图片
     * @param target 目标文件
     */
    public static void saveBitmap(Bitmap bitmap, File target) {
        if (target.exists()) {
            target.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(target);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存Bitmap到文件
     *
     * @param bitmap  图片
     * @param quality 保存质量 0..100
     * @param target  目标文件
     */
    public static void saveBitmap(Bitmap bitmap, int quality, File target) {
        if (target.exists()) {
            target.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(target);
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩bitmp到目标大小（质量压缩）
     *
     * @param bitmap      图片
     * @param needRecycle 是否回收
     * @param maxSize     最大尺寸
     * @return 图片
     */
    public static Bitmap compressBitmap(Bitmap bitmap, boolean needRecycle, long maxSize) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        int options = 100;
        while (baos.toByteArray().length > maxSize) {
            baos.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bm = BitmapFactory.decodeStream(isBm, null, null);
        if (needRecycle) {
            bitmap.recycle();
        }
        bitmap = bm;
        return bitmap;
    }

    /**
     * 等比压缩（宽高等比缩放）
     *
     * @param bitmap       图片
     * @param needRecycle  是否需要回收
     * @param targetWidth  最终宽度
     * @param targetHeight 最终高度
     * @return 图片
     */
    public static Bitmap compressBitmap(Bitmap bitmap, boolean needRecycle, int targetWidth, int targetHeight) {
        float sourceWidth = bitmap.getWidth();
        float sourceHeight = bitmap.getHeight();

        float scaleWidth = targetWidth / sourceWidth;
        float scaleHeight = targetHeight / sourceHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight); //长和宽放大缩小的比例
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (needRecycle) {
            bitmap.recycle();
        }
        bitmap = bm;
        return bitmap;
    }

    public static Bitmap compressBitmap(String imageFile, boolean qualityCompress, long maxSize, int targetWidth, int targetHeight) {
        return compress(imageFile, null, false, qualityCompress, maxSize, targetWidth, targetHeight);
    }

    private static Bitmap compress(String imageFile, String targetFile, boolean isSave, boolean qualityCompress, long maxSize, int targetWidth, int targetHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options); //加载图片信息
        int sourceWidth = options.outWidth;
        int sourceHeight = options.outHeight;
        options.inJustDecodeBounds = false;
        //计算inSampleSize
        int inSampleSize = 1;
        //先根据宽度进行缩小
        while (sourceWidth / inSampleSize > targetWidth) {
            inSampleSize++;
        }
        //然后根据高度进行缩小
        while (sourceHeight / inSampleSize > targetHeight) {
            inSampleSize++;
        }

        if (inSampleSize <= 0) {
            inSampleSize = 1;
        }
        options.inSampleSize = inSampleSize;
        Bitmap bitmap = BitmapFactory.decodeFile(imageFile, options);//加载真正bitmap

        bitmap = compressBitmap(bitmap, false, targetWidth, targetHeight); //等比缩放
        if (qualityCompress) {
            bitmap = compressBitmap(bitmap, true, maxSize); //压缩质量
        }

        if (isSave) {
            String savePath = imageFile;
            if (!StringUtils.isEmpty(targetFile)) {
                savePath = targetFile;
            }

            saveBitmap(bitmap, new File(savePath));//保存图片
        }

        return bitmap;
    }

    /**
     * 压缩某张图片(执行步骤sampleSize压缩->等比压缩->质量压缩)
     *
     * @param imageFile       图片文件
     * @param targetFile      保存目标，为空表示源地址保存
     * @param qualityCompress 是否做质量压缩
     * @param maxSize         目标图片大小
     * @param targetWidth     最终宽度
     * @param targetHeight    最终高度
     */
    public static void compressImage(String imageFile, String targetFile, boolean qualityCompress, long maxSize, int targetWidth, int targetHeight) {
        Bitmap bitmap = compress(imageFile, targetFile, true, qualityCompress, maxSize, targetWidth, targetHeight);
        bitmap.recycle();
    }

    public static void compressImage(String imageFile, boolean qualityCompress, long maxSize, int targetWidth, int targetHeight) {
        compressImage(imageFile, null, qualityCompress, maxSize, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile    图片文件
     * @param targetWidth  最终宽度
     * @param targetHeight 最终高度
     */
    public static void compressImage(String imageFile, int targetWidth, int targetHeight) {
        compressImage(imageFile, null, false, 0L, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile    图片文件
     * @param targetWidth  最终宽度
     * @param targetHeight 最终高度
     * @return 图片
     */
    public static Bitmap compressBitmap(String imageFile, int targetWidth, int targetHeight) {
        return compressBitmap(imageFile, false, 0L, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile 图片文件
     * @param scale     图片缩小倍速
     */
    public static void compressImageSmall(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / scale;
        int targetHeight = options.outHeight / scale;
        compressImage(imageFile, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile 图片文件
     * @param scale     图片缩小倍速
     * @return 图片
     */
    public static Bitmap compressBitmapSmall(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / scale;
        int targetHeight = options.outHeight / scale;
        return compressBitmap(imageFile, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile 图片文件
     * @param scale     图片放大倍速
     */
    public static void compressImageBig(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth * scale;
        int targetHeight = options.outHeight * scale;
        compressImage(imageFile, targetWidth, targetHeight);
    }

    /**
     * 图片缩放-尺寸缩放
     *
     * @param imageFile 图片文件
     * @param scale     图片放大倍速
     * @return 图片
     */
    public static Bitmap compressBitmapBig(String imageFile, int scale) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth * scale;
        int targetHeight = options.outHeight * scale;
        return compressBitmap(imageFile, targetWidth, targetHeight);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile       图片文件
     * @param targetFile      最终文件
     * @param qualityCompress 压缩质量
     * @param maxSize         最大尺寸
     */
    public static void compressImage(String imageFile, String targetFile, boolean qualityCompress, long maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / 2;
        int targetHeight = options.outHeight / 2;
        compressImage(imageFile, targetFile, qualityCompress, maxSize, targetWidth, targetHeight);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile       图片文件
     * @param qualityCompress 压缩质量
     * @param maxSize         最大尺寸
     */
    public static void compressImage(String imageFile, boolean qualityCompress, long maxSize) {
        compressImage(imageFile, null, qualityCompress, maxSize);
    }

    /**
     * 质量压缩图片
     *
     * @param imageFile       图片文件
     * @param qualityCompress 压缩质量
     * @param maxSize         最大尺寸
     * @return 图片
     */
    public static Bitmap compressBitmap(String imageFile, boolean qualityCompress, long maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageFile, options);
        int targetWidth = options.outWidth / 2;
        int targetHeight = options.outHeight / 2;
        return compressBitmap(imageFile, qualityCompress, maxSize, targetWidth, targetHeight);
    }

    /**
     * 质量压缩图片-压缩在maxSize以内
     *
     * @param imageFile 图片文件
     * @param maxSize   最大尺寸
     */
    public static void compressImage(String imageFile, long maxSize) {
        compressImage(imageFile, true, maxSize);
    }

    /**
     * 质量压缩图片-压缩在maxSize以内
     *
     * @param imageFile 图片文件
     * @param maxSize   最大尺寸
     * @return 图片
     */
    public static Bitmap compressBimap(String imageFile, long maxSize) {
        return compressBitmap(imageFile, true, maxSize);
    }

    /**
     * 质量压缩图片-压缩在1M以内
     *
     * @param imageFile 图片文件
     */
    public static void compressImage(String imageFile) {
        compressImage(imageFile, true, (long) (1 * FileUtils.MB));
    }

    /**
     * 质量压缩图片-压缩在1M以内
     *
     * @param imageFile 图片文件
     * @return
     */
    public static Bitmap compressBitmap(String imageFile) {
        return compressBitmap(imageFile, true, (long) (1 * FileUtils.MB));
    }

    /**
     * 旋转bitmap
     *
     * @param bitmap        图片文件
     * @param rotationAngle 旋转角度
     * @param needRecycle   是否回收
     * @return 图片
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int rotationAngle, boolean needRecycle) {
        Matrix m = new Matrix();
        m.postRotate(rotationAngle);
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
        if (needRecycle) {
            bitmap.recycle();
        }
        return bm;
    }

    /**
     * 根据path
     *
     * @param path 路径
     * @return 旋转角度
     */
    public final static int getDegress(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


}
