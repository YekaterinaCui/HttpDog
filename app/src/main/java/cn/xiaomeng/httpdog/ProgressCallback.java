package cn.xiaomeng.httpdog;

interface ProgressCallback {
    void updateProgress(int progress, long networkSpeed, boolean done);
}
