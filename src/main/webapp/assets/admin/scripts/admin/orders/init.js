define(['admin/orders/comment_list', 'admin/orders/shipments_list', 'admin/orders/order_list'],
    function (CommentsList, ShipmentsList, Order) {
        return {
            init: function () {
                CommentsList.init();
                ShipmentsList.init();
                Order.init();
            }
        }
    });