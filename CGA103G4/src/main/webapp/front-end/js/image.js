let count = 0;
let width1 = -150;
let width2 = -50;
let width3 = 50;
let width4 = -250;
let width5 = 150;
let width6 = -450;
let width7 = -350;
let width8 = 250;
let width9 = 350;

$(document).ready(function () {

	$("#left").click(function () {
		if (count > -2) {

			width1 -= 100;
			width2 -= 100;
			width3 -= 100;
			width4 -= 100;
			width5 -= 100;
			width6 -= 100;
			width7 -= 100;
			width8 -= 100;
			width9 -= 100;
			count--;
			$("#image01").css({
				transform: 'translateX(' + width1 + '%)'
			})
			$("#image02").css({
				transform: 'translateX(' + width2 + '%)'
			})
			$("#image03").css({
				transform: 'translateX(' + width3 + '%)'
			})
			$("#image04").css({
				transform: 'translateX(' + width4 + '%)'
			})
			$("#image05").css({
				transform: 'translateX(' + width5 + '%)'
			})
			$("#image06").css({
				transform: 'translateX(' + width6 + '%)'
			})
			$("#image07").css({
				transform: 'translateX(' + width7 + '%)'
			})
			$("#image08").css({
				transform: 'translateX(' + width8 + '%)'
			})
			$("#image09").css({
				transform: 'translateX(' + width9 + '%)'
			})
		}
	})

	$("#right").click(function () {
		if (count < 2) {

			width1 += 100;
			width2 += 100;
			width3 += 100;
			width4 += 100;
			width5 += 100;
			width6 += 100;
			width7 += 100;
			width8 += 100;
			width9 += 100;
			count++;
			$("#image01").css({
				transform: 'translateX(' + width1 + '%)'
			})
			$("#image02").css({
				transform: 'translateX(' + width2 + '%)'
			})
			$("#image03").css({
				transform: 'translateX(' + width3 + '%)'
			})
			$("#image04").css({
				transform: 'translateX(' + width4 + '%)'
			})
			$("#image05").css({
				transform: 'translateX(' + width5 + '%)'
			})
			$("#image06").css({
				transform: 'translateX(' + width6 + '%)'
			})
			$("#image07").css({
				transform: 'translateX(' + width7 + '%)'
			})
			$("#image08").css({
				transform: 'translateX(' + width8 + '%)'
			})
			$("#image09").css({
				transform: 'translateX(' + width9 + '%)'
			})
		}
	})
})