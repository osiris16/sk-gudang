(function( factory ) {
	if ( typeof define === "function" && define.amd ) {
		define( ["jquery", "../jquery.validate"], factory );
	} else {
		factory( jQuery );
	}
}(function( $ ) {

/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ID (Indonesia; Indonesian)
 */
$.extend($.validator.messages, {
	required: " Harus diisi.",
	remote: " Kesalahan isian data.",
	email: " Kesalahan alamat email.",
	url: " Kesalahan alamat URL.",
	date: " Kesalahan tanggal.",
	dateISO: " Kesalahan tanggal ISO.",
	number: " Kesalahan angka.",
	digits: " Hanya menerima angka.",
	creditcard: " Hanya menerima nomor kartu kredit.",
	equalTo: " Harus sama dengan sebelumnya.",
	maxlength: $.validator.format(" Dibatasi hanya {0} karakter."),
	minlength: $.validator.format(" Tidak boleh kurang dari {0} karakter."),
	rangelength: $.validator.format(" Dibatasi antara {0} dan {1} karakter."),
	range: $.validator.format(" Hanya menerima nilai antara {0} dan {1}."),
	max: $.validator.format(" Hanya menerima lebih kecil atau sama dengan {0}."),
	min: $.validator.format(" Hanya menerima lebih besar atau sama dengan {0}.")
});

}));