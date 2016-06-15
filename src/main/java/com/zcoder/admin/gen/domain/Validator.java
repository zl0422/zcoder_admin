package com.zcoder.admin.gen.domain;

import lombok.Data;

/**
 * Created by lin on 2016-06-14.
 */
@Data
public class Validator {

    private String validatorData;

    private String required;

    private String dataValidateLengthRange;

    private String dataValidateLength;

    private String dataValidateMinMax;

    private String dataValidateWords;

    private String dataValidateLinked;

    private String [] dataValidatePattern = {"numeric","alphanumeric"};

    public enum ValidatorData{

        email("email"),tel("tel"),url("url"),number("number"),date("date");

        ValidatorData(String value){
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
