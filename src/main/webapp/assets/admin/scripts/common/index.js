require.config(mssRequireConfig);
require(['toastr', 'app', 'bootstrap_hover_dropdown', 'jquery_uniform', 'jquery_cookie', 'jquery_blockui', 'jquery_slimscroll'], function (Toastr) {
    window.Toastr = Toastr;
    $(document).ready(function () {
        App.init();
    });
});