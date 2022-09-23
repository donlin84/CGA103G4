 $('#the_file').on('change', function (e) {
            const file = this.files[0];
            const fr = new FileReader();
            fr.onload = function (e) {
                $('#showPic').attr('src', e.target.result);
            };
            fr.readAsDataURL(file);
});




