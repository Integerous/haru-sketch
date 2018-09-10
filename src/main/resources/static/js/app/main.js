var main = {
    init : function () {
        var _this = this;
        $('#btn-join').on('click', function () {
            _this.save();
        });
    },
    save : function () {
        var data = {
            email: $('#email').val(),
            pwd: $('#pwd').val(),
            name: $('#name').val(),
            phone: $('#phone').val(),
            address: $('#address').val()
        };

        $.ajax({
            type: 'POST',
            url: '/join',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원가입 성공!');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

main.init();