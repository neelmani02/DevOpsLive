$(document).ready(function(){
    $('#overviewTable').DataTable({
		searching: true,
		ordering: false,
		bInfo: false,
		sDom: '<"top"f>rt<"bottom"p><"clear">',
		pageLength: 5,
		language: {
			search: "",
			searchPlaceholder: 'Search User Story Here...',
			paginate: {
				previous: '&lsaquo;',
				next: '&rsaquo;'
			}
		}
	});
    $('#overviewTable_wrapper').hide();
    $('.tile-wrapper').on('click', function(e){
    	$('.tile-wrapper').addClass("inactive");
    	if(this.id == 'userStories'){
    		$('#overviewTable_wrapper').toggle();
    		$(this).removeClass("inactive");
    		$(this).toggleClass("active");
    		$('html, body').animate({
    	        scrollTop: $("#overviewTable_wrapper").offset().top
    	    }, 2000);
    	}
    });
});