$("a.nav").on("click", function(e) {
	
	e.preventDefault();
	let el = $(this).closest("body");
	$(el).find("div.container").removeClass("-on");
	$(el).find("div.container." + $(this).attr("data-target")).addClass("-on");

});