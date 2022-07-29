package com.yantailor.bookhub.bean;




import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;


public class R {
//    @ApiModelProperty(value = "是否成功")
    private Boolean success;

//    @ApiModelProperty(value = "返回码")
    private Integer code;

//    @ApiModelProperty(value = "返回消息")
    private String message;

//    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new LinkedHashMap<String, Object>();

    //构造器私有化
    public R() {
    }

    //成功静态方法
    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(200);
        r.setMessage("成功");
        return r;
    }

    //失败静态方法
    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(400);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
//
//    public R data(Map<String, Object> map) {
//        this.setData(map);
//        return this;
//    }

//    public static R errorCode(ErrorCode errorCode){
//        R r = new R();
//        r.setCode(errorCode.getCode());
//        r.setMessage(errorCode.getMessage());
//        return r;
//    }
//    public static R errorCode(int code , String message){
//        R r = new R();
//        r.setCode(code);
//        r.setMessage(message);
//        return r;
//    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        R r = (R) o;
        return Objects.equals(success, r.success) &&
                Objects.equals(code, r.code) &&
                Objects.equals(message, r.message) &&
                Objects.equals(data, r.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, code, message, data);
    }

    @Override
    public String toString() {
        return "R{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
