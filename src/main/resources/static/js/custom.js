//This function allows the navbar hamburger menu to pop out
$( document ).ready(function() {
     $('.leftmenutrigger').on('click', function(e) {
     $('.side-nav').toggleClass("open");
     e.preventDefault();
    });
});


$( document ).ready(function() {
    $("#descriptionInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#workRequestData tr").filter(function(){
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

$( document ).ready(function() {
    $('.buildingSelect').select2({
        placeholder: "Select a building",
        allowClear: true
    });
});


