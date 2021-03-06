require.config(mssRequireConfig);
require(['bootstrap', 'jquery_validate', 'jquery_uniform'], function () {
    "use strict";
    $(document).ready(function () {
        var Login = function () {

            var handleLogin = function () {
                $('#loginForm').validate({
                    errorElement: 'span', //default input error message container
                    errorClass: 'help-block', // default input error message class
                    focusInvalid: false, // do not focus the last invalid input
                    rules: {
                        username: {
                            required: true
                        },
                        password: {
                            required: true
                        }
                    },

                    messages: {
                        username: {
                            required: "账号不能为空"
                        },
                        password: {
                            required: "密码不能为空"
                        }
                    },

                    invalidHandler: function (event, validator) {
                        //$('.alert-error', $('.login-form')).show();
                    },

                    highlight: function (element) {
                        $(element).closest('.form-group').addClass('has-error');
                    },

                    success: function (label) {
                        label.closest('.form-group').removeClass('has-error');
                        label.remove();
                    },

                    errorPlacement: function (error, element) {
                        error.insertAfter(element.closest('.input-icon'));
                    },

                    submitHandler: function (form) {
                        form.submit();
                    }
                });

                $('#loginForm input').keypress(function (e) {
                    if (e.which == 13) {
                        $('#loginForm').submit();
                    }
                });
            };

            // Handles custom checkboxes & radios using jQuery Uniform plugin
            var handleUniform = function () {
                if (!jQuery().uniform) {
                    return;
                }
                var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
                if (test.size() > 0) {
                    test.each(function () {
                        if ($(this).parents(".checker").size() == 0) {
                            $(this).show();
                            $(this).uniform();
                        }
                    });
                }
            };

            return {
                init: function () {
                    handleUniform();
                    handleLogin();
                }
            };

        }();
        Login.init();
    });
});
