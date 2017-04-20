package cn.xiaomeng.httpdog;

import android.os.AsyncTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import cn.xiaomeng.httpdog.tools.io.FileUtils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class FileDownloadTask extends AsyncTask<Void, Long, Boolean> {

    private OkHttpClient okHttpClient;
    private cn.xiaomeng.httpdog.FileDownloadCallback callback;
    private String url;
    private File target;
    //开始下载时间，用户计算加载速度
    private long previousTime;

    public FileDownloadTask(String url, File target, FileDownloadCallback callback) {
        this.url = url;
        this.okHttpClient = HttpDog.getInstance().getOkHttpClientBuilder().build();
        this.callback = callback;
        this.target = target;

        FileUtils.mkdirs(target.getParentFile());
        if (target.exists()) {
            target.delete();
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        previousTime = System.currentTimeMillis();
        if (callback != null) {
            callback.onStart();
        }
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        //构造请求
        final Request request = new Request.Builder()
                .url(url)
                .build();

        boolean suc = false;
        try {
            Response response = okHttpClient.newCall(request).execute();
            long total = response.body().contentLength();
            saveFile(response);
            if (total == target.length()) {
                suc = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            suc = false;
        }

        return suc;
    }

    @Override
    protected void onProgressUpdate(Long... values) {
        super.onProgressUpdate(values);
        if (callback != null && values != null && values.length >= 2) {
            long sum = values[0];
            long total = values[1];

            int progress = (int) (sum * 100.0f / total);
            //计算下载速度
            long totalTime = (System.currentTimeMillis() - previousTime)/1000;
            if ( totalTime == 0 ) {
                totalTime += 1;
            }
            long networkSpeed = sum / totalTime;
            callback.onProgress(progress, networkSpeed);
        }
    }

    @Override
    protected void onPostExecute(Boolean suc) {
        super.onPostExecute(suc);
        if (suc) {
            if ( callback != null ) {
                callback.onDone();
            }
        } else {
            if ( callback != null ) {
                callback.onFailure();
            }
        }
    }

    public String saveFile(Response response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            final long total = response.body().contentLength();
            long sum = 0;

            FileUtils.mkdirs(target.getParentFile());

            fos = new FileOutputStream(target);
            while ((len = is.read(buf)) != -1) {
                sum += len;
                fos.write(buf, 0, len);

                if (callback != null) {
                    publishProgress(sum, total);
                }
            }
            fos.flush();

            return target.getAbsolutePath();
        } finally {
            try {
                if (is != null) { is.close(); }
            } catch (IOException e) {
            }
            try {
                if (fos != null) { fos.close(); }
            } catch (IOException e) {
            }
        }
    }
}
