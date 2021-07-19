Vue.component("delivererNotifications", {
	data: function() {
		return {
			notifications: null,
			empty: ""
		}
	},
	template: `
	<div>
		<div class="table-body">
			</br></br></br>
			<img class="logo" alt="" src="./images/logo1.png">
			<div>
				<h1 class="item-name"> {{this.empty}}</h1>
	  		</div>
			<table class="table-all" id="customer-table" v-bind:hidden="isEmpty(this.empty)">
                <thead >
                    <tr class="header">
                        <th>ID porudžbine</th>
                        <th>Sadržaj</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="(n, index) in notifications">
                        <td>{{n.orderId}}</td>
                        <td>{{n.content}}</td>
                        <td><button v-on:click="editNotificationStatus(n)">OK</button></td>
                    </tr>
                </tbody>
            </table>
		</div>
	</div>
	`,
	mounted() {
		axios
			.get('/rest/getDelivererNotifications')
			.then(response => {
				if(response.data == 'Empty') {
					this.empty = "Nemate novih obaveštenja"
				} else {
					this.notifications = response.data
				}
			});
	},
	methods: {
		editNotificationStatus: function(notification) {
			axios
				.put('/rest/editNotificationStatus', notification)
				.then(response => (this.$router.go()));
		},
		isEmpty: function(empty) {
			if(empty == '') {
				return false;
			} else {
				return true;
			}
		}
		
	}
});