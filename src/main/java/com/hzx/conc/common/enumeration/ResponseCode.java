package com.hzx.conc.common.enumeration;

/**
 * @author zhangzhan
 * @since 2023/9/1
 */
public enum ResponseCode {
    success(10000, "操作成功"),
    error(20000, "服务器错误"),
    bug(21000, "Bug异常"),
    save_order_error(22001, "下单失败，请重试"),
    cancel_order_error(22002, "退单失败，请重试"),
    unknown_account(20001, "账号或密码错误"),
    forbidden_account(20002, "账号已禁用"),
    password_incorrect(20003, "账号或密码错误"),
    verify_captcha_error(20004, "验证码错误,请重新刷新并滑动验证码!"),
    unauthorized(20005, "无权限"),
    can_not_edit(20006, "该条记录无法编辑"),
    unauthenticated(20007, "登录超时，请重新登录"),
    forbidden_ip(20008, "非法请求"),
    not_found_url(20009, "url不存在"),
    unknown_user(20010, "用户未注册"),
    param_format_error(30001, "参数格式错误"),
    missing_parameter(30002, "缺少参数"),
    name_already_exist(30003, "该名称已存在"),
    data_not_exist(30004, "该记录不存在"),
    login_name_already_exist(30005, "该登录名已存在"),
    code_already_exist(30006, "该编码已存在"),
    format_error(30008, "文件类型错误"),
    fullname_already_exist(30007, "该全称已存在"),
    relation_already_exist(30009,"关联已存在"),
    error_enterprise_nature(30011, "企业性质错误"),
    token_unauthenticated(40001, "登录超时，请重新登录"),
    token_forbidden(40002, "您的账号已在另一个设备登录，若非本人操作，请尽快修改密码。"),
    token_parameter(40003, "缺少token参数"),
    token_error(40004, "token错误"),
    sign_key_parameter(40005, "缺少signKey参数"),
    sign_key_error(40006, "signKey错误"),
    sign_parameter(40007, "缺少sign参数"),
    sign_error(40008, "sign错误"),
    sign_expire(40009, "sign过期"),
    sign_invalid(40010, "sign已失效"),
    logout_error(50003, "用户登出失败"),

    token_check_fail(50004, "无系统访问权限"),
    password_is_weak(8888, "密码安全性较弱，请及时修改！！！"),
    ;
    private int code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
