/**
 * Created by Qin_Yikai on 2018-09-29.
 */
function getFormObj(selector) {
    var obj = {};
    var formAry = $(selector).serializeArray();
    $.each(formAry, function () {
        obj[this.name] = this.value;
    });
    return obj;
}
