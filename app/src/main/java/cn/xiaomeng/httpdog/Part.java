package cn.xiaomeng.httpdog;

import android.text.TextUtils;

public final class Part {
    private String key;
    private String value;
    private cn.xiaomeng.httpdog.FileWrapper fileWrapper;

    public Part(String key, String value) {
        setKey(key);
        setValue(value);
    }

    public Part(String key, cn.xiaomeng.httpdog.FileWrapper fileWrapper) {
        setKey(key);
        this.fileWrapper = fileWrapper;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public FileWrapper getFileWrapper() {
        return fileWrapper;
    }

    protected void setKey(String key) {
        if(key == null) {
            this.key = "";
        } else {
            this.key = key;
        }
    }

    protected void setValue(String value) {
        if(value == null) {
            this.value = "";
        } else {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof cn.xiaomeng.httpdog.Part)){
            return false;
        }
        cn.xiaomeng.httpdog.Part part = (cn.xiaomeng.httpdog.Part) o;
        if (part == null){
            return false;
        }
        if (TextUtils.equals(part.getKey(), getKey()) && TextUtils.equals(part.getValue(), getValue())){
            return true;
        }
        return false;
    }
}