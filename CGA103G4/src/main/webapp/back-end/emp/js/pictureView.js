window.addEventListener("load", function () {

    var the_file_element = document.getElementById("the_file");
    the_file_element.addEventListener("change", function () {

			
        for (let i = 0; i < this.files.length; i++) {
            let reader = new FileReader();
            reader.readAsDataURL(this.files[i]);
            reader.addEventListener('load', function () {
                let li_str = `
               <li id="test1">
                   <img src="${reader.result}" class="preview">    
               </li>
               `;
                let ul_el = document.getElementsByClassName("picture_list")[i];
                ul_el.insertAdjacentHTML("beforeend", li_str);
            })
    	}
	})
});