$(document).ready(function(){
    $('#overviewTable').DataTable({
		searching: true,
		ordering: false,
		bInfo: false,
		sDom: '<"top"f>rt<"bottom"p><"clear">',
		pageLength: 2,
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
    	if(this.id == 'userStories'){
    		$('#overviewTable_wrapper').toggle();
    		$(this).toggleClass("active");
    	}
    });
});