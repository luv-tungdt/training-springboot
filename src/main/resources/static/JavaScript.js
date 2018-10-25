$(document).ready(function () {
    console.log("ready!");
    //fire_ajax_submit(companyId);
    $('#flagCompany1').attr("checked", "true");
    ///$('#flagCompany2').attr("checked", "flase");
    $(".btn_wider").click(function () {
        console.log("ready!5555555555");
        window.location.href = "exportCSV";
    });
    // if(  $(".oldCompany1").val()!=0){
    //     var companyId = $(".oldCompany1").val();
    //     fire_ajax_submit(companyId);
    //
    // }
    var companyId = $(".oldCompany1").val();
    fire_ajax_submit(companyId);
    $(".addUser").click(function () {
        // fire_ajax_submit(companyId);
        console.log("ready!5555555555");
        window.location.href = "add";
    });
    $("._datepicker").datepicker();
    $('#newCompany').hide();
    $(".info_old").show();
    $('input:radio[name=flagCompany]').change(function () {
        console.log("BBBB");
        var val = $(this).val();
        if (val == "old") {
            $('#newCompany').hide();
            $(".info_old").show();
        } else {
            $('#newCompany').show();
            $(".info_old").hide();
        }
        console.log("AAAÂ1", val);
    });
    $(".oldCompany1").change(function () {
        var companyId = $(this).val();
        console.log(companyId);
        fire_ajax_submit(companyId);

    });

});

function fire_ajax_submit(companyId) {
    var url = "/getCompanyInformationById/" + companyId;
    $.ajax({
        type: "GET",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (data) {
            console.log("77777777777777", data);
            $("#_name").html(data.name);
            $("#_address").html(data.address);
            $("#_email").html(data.email);
            $("#_tel").html(data.tel);





            // var json = "<h4>Ajax Response</h4><pre>"
            //     + JSON.stringify(data, null, 4) + "</pre>";
            // $('#feedback').html(json);
            //
            // console.log("SUCCESS : ", data);
            // $("#btn-search").prop("disabled", false);


        }
        // error: function (e) {
        //
        //     var json = "<h4>Ajax Response</h4><pre>"
        //         + e.responseText + "</pre>";
        //     $('#feedback').html(json);
        //
        //     console.log("ERROR : ", e);
        //     $("#btn-search").prop("disabled", false);
        //
        // }
    });

}


