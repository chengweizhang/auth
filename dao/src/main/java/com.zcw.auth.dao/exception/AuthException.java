package com.zcw.auth.dao.exception;

import org.apache.commons.lang3.StringUtils;

public class AuthException extends RuntimeException {

    public enum CommonBaExceptionEnum {
        NO_LOGIN_INFO(11022, "用户信息不存在！"),
        ENTERPRISE_STATUS_ERROR(10100, "业务状态错误！"),

        ENTERPRISE_NAME_ERROR(11001, "企业名称不合法"),
        ENTERPRISE_SETUP_INFO_ERROR(11002, "设立信息不存在"),
        SOCIAL_NUM_NO_VALID(11024, "统一社会信用代码不合法"),
        ORG_CODE_NO_VALID(11020, "组织机构代码不合法"),
        ACCOUNT_HAVE_EXIST(10108, "与已有账号冲突，请联系管理员处理"),
        ACCOUNT_HAVE_ADDRESS_EXIST(10112, "与已有账号地址冲突！"),
        FILE_ADDRESS_FORMAT_INVALID(10109, "文件上传格式不合法！"),


        MOBILE_NO_VALID(11011, "手机号格式不合法"),
        IDENTITY_NO_VALID(11012, "身份证格式不合法"),
        MAIL_NO_VALID(11013, "邮箱格式不合法！"),
        PARTNER_NUM_ERROR(11005, "合伙人数错误"),
        PARTNER_BUSINESS_NO_VALID(11019, "营业执照号码格式错误"),
        PARTNER_LIMIT_ERROR(11017, "有限合伙人书必须小于合伙人书"),
        WORKER_NUM_ERROR(11018, "从业人数必须大于等于8"),
        ENTERPRISE_AUTHORITY_TIME_ERROR(11014, "委托期限错误"),
        TELEPHONE_NUM_ERROR(11016, "固定号码格式错误！"),
        INFORMATION_CANNOT_NULL(11023, "数据必填"),
        ENT_NAME_ERROR(11026, "企业设立信息中：经营场所和生产经营营地必须以”浙江省/宁波市/镇海区/“开头！"),
        CANNOT_FIND_IN_ZIP_FILE(11025, "在压缩包内未找到"),

        PARTNER_CHECK_PERCENT_ERROR(11027, "合伙人合伙比例需要为100%"),
        PARTNER_CHECK_CONTRIBUTION_ERROR(11028, "全体合伙人认缴额必须为"),
        ACCOUNT_TYPE_ERROR(11029, "用户类型不能即是企业联系人又是自然人"),
        AGREEMENT_IS_NULL_NO_VALID(11030, "合伙协议不能为空！"),
        SING_NOT_ALLOWED(11031, "非签署状态，不可签署！"),
        ZIP_FILE_ERROR(11032, "压缩文件错误"),


        MSG_CODE_NO_VALID(11107, "校验短信验证码失败"),


        ERROR(11111, "");

        private Integer errCode;
        private String baseMessage;

        CommonBaExceptionEnum(Integer errCode, String baseMessage) {
            this.errCode = errCode;
            this.baseMessage = baseMessage;
        }

        public AuthException create(String... detailMsg) {
            return create(true, detailMsg);
        }

        public AuthException create(boolean isServerError, String... detailMsg) {
            String msg = baseMessage;
            if (detailMsg != null) {
                msg += "信息：" + StringUtils.join(detailMsg, "，");
            }
            return new AuthException(errCode, msg, isServerError);
        }

        public Integer getErrCode() {
            return errCode;
        }

        public CommonBaExceptionEnum setErrCode(Integer errCode) {
            this.errCode = errCode;
            return this;
        }

        public String getBaseMessage() {
            return baseMessage;
        }

        public CommonBaExceptionEnum setBaseMessage(String baseMessage) {
            this.baseMessage = baseMessage;
            return this;
        }
    }

    /**
     * 错误码
     */
    private Integer errCode;

    private boolean isServerError = true;

    public AuthException() {

    }

    /**
     * @param errCode--错误码
     * @param message--错误消息
     */
    public AuthException(Integer errCode, String message) {
        super(message);
        this.errCode = errCode;
    }

    public AuthException(Integer errCode, String message, boolean isServerError) {
        super(message);
        this.errCode = errCode;
        this.isServerError = isServerError;
    }

    public AuthException(String message) {
        super(message);
    }

    public Integer getErrCode() {
        return errCode;
    }

    public AuthException setErrCode(Integer errCode) {
        this.errCode = errCode;
        return this;
    }

    public boolean isServerError() {
        return isServerError;
    }

    public AuthException setServerError(boolean serverError) {
        isServerError = serverError;
        return this;
    }
}
