$(function () {
    $("#datepicker").datepicker({
        format: 'yyyy-mm-dd',
        startDate: "-150y",
        endDate: new Date(),
        todayHighlight: true,
        toggleActive: true
    });
});
