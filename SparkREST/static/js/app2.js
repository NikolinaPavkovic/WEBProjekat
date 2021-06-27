var app2 = new Vue ({
	el: '#registration',
	data: {
		newUser : {},
		mode : "BROWSE"
	},
	methods: {
		register : function() {
			axios
				.post("rest/register/", newUser);
			this.mode = "LoggedIn";
		}
	}
});