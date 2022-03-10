package api;

public enum StatusCode {

    CODE_200(200,""),
    CODE_201(201,""),
    CODE_500(500,"")
    CODE_400(400,"Missing required field: name");

    public final int code;
    public final String msg;

    StatusCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
