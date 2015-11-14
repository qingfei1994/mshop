define(['admin/logistics/waybilltemplate_edit', 'admin/logistics/transportation_edit'],
    function (WaybilltemplateEdit, TransportationEdit) {
        return {
            init: function () {
                WaybilltemplateEdit.init();
                TransportationEdit.init();
            }
        }
    });