/**
 * 初始化ajax form的验证
 */
define(["jquery_validator_messages"], function() {
	"use strict";

	var addFormValidMethod = function() {
		//手机号码验证
		jQuery.validator.addMethod("phone", function(val, element) {
	        var tel = /^0{0,1}(1[3,4,5,8][0-9])\d{8}$/;
	        return this.optional(element) || (tel.test(val));
	    }, "请填写正确的手机号码");
	};

	var handleFormValid = function() {
 		$('form.valid').validate({
 			errorElement: 'span',
            errorClass: 'help-block',
			focusCleanup: true,
			errorPlacement: function(error, element) {
				error.appendTo(element.closest('.form-group > div'));
			},
            invalidHandler: function(event, validator) { 
                App.toastError("信息验证有误，请正确填写信息");
				validator.focusInvalid();
            },
			highlight: function(element, errorClass) {
                $(element).closest('.form-group').addClass('has-error');
			},
            success: function(label, errorClass) {
				label.closest('.form-group').removeClass('has-error');
				label.remove();
            }
 		});
	};
	
	return {
		init: function() {
			addFormValidMethod();
			handleFormValid();
		}
	};
});