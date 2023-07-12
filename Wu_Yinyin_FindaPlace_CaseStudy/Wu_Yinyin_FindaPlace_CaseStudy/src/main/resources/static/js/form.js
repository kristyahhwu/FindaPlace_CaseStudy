
    // logout button
    $(document).ready(function() {
        $("#logout").on("click", function(e) {
            e.preventDefault();
            document.logoutForm.submit();
        });
    });

    // cancel button
    $(document).ready(function() {
    	$("#cancel").on("click", function() {
    		window.location = moduleURL;
    	});